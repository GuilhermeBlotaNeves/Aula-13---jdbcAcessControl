<%-- 
    Document   : my-tasks
    Created on : 30 de mai. de 2022, 15:38:35
    Author     : Fatec
--%>

<%@page import="model.Task"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User sessionUser = (User) session.getAttribute("user");
    Exception tasksException = null;
    ArrayList<Task> tasks = new ArrayList<>();
    try{
        tasks = Task.getUserTasks(sessionUser.getUsername());
        if(request.getParameter("task-add")!=null){
            String username = sessionUser.getUsername();
            String title = request.getParameter("title");
            Task task = new Task(0, username, title);
            Task.addTask(task);
            response.sendRedirect(request.getRequestURI());
        }else if(request.getParameter("task-delete")!=null){
            int id = Integer.parseInt(request.getParameter("id"));
            Task.deleteTask(id);
            response.sendRedirect(request.getRequestURI());
        }
    }catch(Exception ex){
        tasksException = ex;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MINHAS TAREFAS - MyTasks</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
        <h2>Início</h2>
        <h3>Lista das Minhas Tarefas</h3>
        <%if(tasksException != null){%>
        <div style="color: red">
            <%= tasksException.getMessage() %>
        </div><hr/>
        <%}%>
        <%if(sessionUser==null){%>
            Você deve fazer login para visualizar este conteúdo.
        <%}else{%>
            <form>
                <input type="text" name="title"/>
                <input type="submit" name="task-add" value="+"/>
            </form><hr/>
            <table border="1">
                <tr><th>Title</th><th>Remover</th></tr>
                <%for(Task t: tasks){%>
                <tr>
                    <td><%= t.getTitle() %></td>
                    <td>
                        <form>
                            <input type="hidden" name="id" 
                                   value="<%= t.getId() %>"/>
                            <input type="submit" name="task-delete" 
                                   value="-"/>
                        </form>
                    </td>
                </tr>
                <%}%>
            </table>
        <%}%>
    </body>
</html>