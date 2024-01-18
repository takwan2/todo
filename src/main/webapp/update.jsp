<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.dao.dto.TodoDTO, java.util.List, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Todo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<%
	TodoDTO todo = (TodoDTO)request.getAttribute("todo");
	List<String> errorMessages = (List) request.getAttribute("errorMessages");
%>
<body>
    <div class="container w-75">
    	<% if (errorMessages != null && !errorMessages.isEmpty()) { %>
            <div class="alert alert-danger" role="alert">
                <ul>
                    <% for (String errorMessage : errorMessages) { %>
                        <li><%= errorMessage %></li>
                    <% } %>
                </ul>
            </div>
        <% } %>
	    <div class="text-center">
		    <h2>Todo更新</h2>
		    <form action="update-servlet" method="post" class="ms-auto me-auto">
		        <div class="row g-2 align-items-center mb-2 justify-content-center">
		        	<div class="col-1">
		             	<label for="todo" class="form-label">Todo:</label>
		        	</div>
		        	<div class="col-3">
		            	<input type="text" class="form-control" name="todo" id="todo" value="<%=todo.getTodo()%>">
		        	</div>
		        </div>
		        <div class="row g-2 align-items-center mb-2 justify-content-center">
		        	<div class="col-1">
		            	<label for="timeLimit" class="form-label">期限:</label>
		        	</div>
		        	<div class="col-3">
		             	<input type="date" class="form-control" name="timeLimit" id="timeLimit" value="<%=todo.getTimeLimit()%>">
		        	</div>
		        </div>
		        <input type="hidden" name="id" value="<%=todo.getId() %>">
		        <button type="submit" class="btn btn-primary">Todoを更新する</button>
		    </form>
		    <form action="list-servlet" method="get" class="mt-2">
		        <input type="submit" value="キャンセル" class="btn btn-primary">
		    </form>
		</div>
    </div>
</body>
</html>