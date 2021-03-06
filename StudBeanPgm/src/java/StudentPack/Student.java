/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentPack;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arjun
 */


public class Student {
    
    private int roll;
    private String name;
    private int marks;
    
    private Connection con;
    private PreparedStatement ps;
    private ResultSet result;
    
    private int updateCount;
    private String insQuery;
    private String topperQuery;
    
    
    public Student()   {
        roll = 0;
        name = null;
        marks = 0;

        ps = null;
        result = null;
        
        updateCount = 0;
        insQuery = null;
        topperQuery = null;
        
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/StudBeanDB", "teacher", "password");
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setRoll(int roll)   {
        this.roll = roll;
    }
    
    public void setName(String name)    {
        this.name = name;
    }
    
    public void setMarks(int marks) {
        this.marks = marks;
    }
    
    public int getRoll()   {
        return roll;
    }
    
    public String getName() {
        return name;
    }
    
    public int getMarks()   {
        return marks;
    }
    
    public int getUpdateCount() {
        return updateCount;
    }
    
    public void writeToDB()   {
        insQuery = "insert into markstable(roll, name, marks) values(?, ?, ?)";
        
        try {
            
            ps = con.prepareStatement(insQuery);
            
            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setInt(3, marks);
       
            updateCount = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("\n Oops! Something went wrong!");
            ex.printStackTrace();
        }
    }
    
    public void getTopper() {
        topperQuery = "select roll, name, marks from markstable order by marks desc";
        try {
            ps = con.prepareStatement(topperQuery);
            ps.setMaxRows(1);
            result = ps.executeQuery();
            
            if( result.next() ) {   //move to first row
                roll = result.getInt("roll");
                name = result.getString("name");
                marks = result.getInt("marks");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public int foo()   {
        return (marks + 20);
    }
}
