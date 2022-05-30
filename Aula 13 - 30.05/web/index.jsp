<%-- 
    Document   : index
    Created on : 16 de mai. de 2022, 13:38:16
    Author     : rlarg
--%>

<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception indexException = null;
    int usersCount = -1;
    try{
        usersCount = User.getAllUsers().size();
    }catch(Exception ex){
        indexException = ex;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Início - MyTasks</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
        <h2>Início</h2>
        <%if(indexException != null){%>
        <div style="color: red">
            <%= indexException.getMessage() %>
        </div><hr/>
        <%}%>
        <h3>
            Uuários: 
            <a href="users.jsp">
                <%= (usersCount>-1) ? usersCount : '-' %>
            </a>
        </h3>
    </body>
</html>