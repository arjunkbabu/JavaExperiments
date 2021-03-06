<%-- 
    Document   : itempage1.jsp
    Created on : 26 Apr, 2015, 7:28:09 PM
    Author     : arjun
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Shopping </title>
    </head>
    <body>
        <%!
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            String insQuery;
        %>
        <h1> Watches </h1>
        
        <%
            ArrayList shopItems = new ArrayList();
            session.setAttribute("purchase", shopItems);
            
            insQuery = "select id, model, manufacturer, price, stock from inventory where category LIKE ? order by manufacturer";
            
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/ShoppingCart", "keeper", "key");
            ps = con.prepareStatement(insQuery);
            
            //set up the JDBC objects as session variables
            session.setAttribute("connection", con);
            session.setAttribute("statement", ps);
            session.setAttribute("insertQuery", insQuery);
            
            ps.setString(1, "watch");
            
            rs = ps.executeQuery();     
        %>
        
        <form action = "itempage2.jsp"> 
            <table>
                <tr>
                    <th> Select </th>
                    <th> Model Name </th>
                    <th> Manufacturer </th>
                    <th> Price </th>
                    <th> Stock </th>
                </tr>
                
        <%
            //dynamically populate data from inventory
            while( rs.next() )  {
        %>
                <tr>
                    <td> 
                        <input type="checkbox" name ="watch" value= <%= rs.getString(1) %> />
                    </td>
                    <td> <%= rs.getString(2) %> </td>
                    <td> <%= rs.getString(3) %> </td>
                    <td> <%= rs.getInt(4) %> </td>
                    <td> <%= rs.getString(5) %> </td>
                </tr>
        <%
            }
        %>
          
                <tr>
                    <td> </td>
                    <td> </td>
                    <td> </td>
                    <td> 
                        <input type="submit" value ="Continue Shopping" />
                    </td>
                    <td> </td>
                </tr>
            </table>
        </form>
    </body>
</html>
