<%-- 
    Document   : index
    Created on : 16 de mai. de 2022, 13:38:16
    Author     : rlarg
--%>

<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception usersException = null;
    ArrayList<User> users = new ArrayList<>();
    try {
        users = User.getAllUsers();
    } catch (Exception ex) {
        usersException = ex;
    }
    try {
        if (request.getParameter("user-add") != null) {
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            User user = new User(username, (username + password).hashCode(), name);

            User.addUser(user);
            response.sendRedirect(request.getRequestURI());
        } else if (request.getParameter("user-delete") != null) {
            String username = request.getParameter("username");
            response.sendRedirect(request.getRequestURI());
            
        } else if (request.getParameter("user-delete") != null) {
            String username = request.getParameter("username");
            String newPassword = request.getParameter("newPassword");
            User.changePassword(username, (username + newPassword).hashCode());
            response.sendRedirect(request.getRequestURI());
        }
    } catch (Exception ex) {
        usersException = ex;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuários - MyTasks</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
        <h2>Início</h2>
        <h3>Lista de Usuários</h3>
        <%if (usersException != null) {%>
        <div style="color: red">
            <%= usersException.getMessage()%>
        </div><hr/>
        <%}%>
        <%if (session.getAttribute("user") == null) {%>
        Você deve fazer login para visualizar este conteúdo.
        <%} else {%>
        <form>
            <fieldset>
                <legend>Adicionar usuário</legend>
                Username:<br/>
                <input type="text" name="username"/><br/>
                Nome:<br/>
                <input type="text" name="name"/><br/>
                Senha:<br/>
                <input type="password" name="password"/><br/><br/>
                <input type="submit" name="user-add" value="Adicionar"/>
            </fieldset>
        </form>
        <table border="6">
            <tr><th>Username</th><th>Name</th><th>Remover</th><th>Alterar</th></tr>
                    <%for (User u : users) {%>
            <tr>
                <td><%= u.getUsername()%></td>
                <td><%= u.getName()%></td>
                <td>
                    <form>
                        <input type="hidden" name="username" value="<%=u.getUsername()%>"/>
                        <input type="submit" name="user-delete"
                               value="Remover"/>

                    </form>
                </td>
                <td>
                    <form>
                        <input type="hidden" name="username" value="<%=u.getUsername()%>"/>
                        <input type="password" name="newPassword"/>
                        <input type="submit" name="user-new-pass"
                               value="Alterar senha"/>
                    </form>
                </td>
            </tr>
            <%}%>
        </table>
        <%}%>
    </body>
</html>