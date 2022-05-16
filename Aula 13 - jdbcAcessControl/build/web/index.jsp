<%-- 
    Document   : index
    Created on : 16 de mai. de 2022, 13:48:22
    Author     : Fatec
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio - MyTasks</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
        <h2 style="color: blue"> Inicio </h2>
        <h3>Usuarios</h3>
         <table border="6">
            <tr><th>Username</th><th>Name</th></tr>
            <%for(User u: User.getAllUsers()){%>
            <tr>
                <td><%= u.getUsername() %></td>
                <td><%= u.getName() %></td>
            </tr>
            <%}%>
        </table>
        
    </body>
</html>
