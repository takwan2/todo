<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.ArrayList, model.dao.dto.TodoDTO"%>
 <%
    List<TodoDTO> todoList = (List) request.getAttribute("todoList");
 	List<String> errorMessages = (List) request.getAttribute("errorMessages");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo List</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
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
		<h1 class="text-center">Todoリスト</h1>
		<table class="table mb-5 text-center align-middle">
			<thead>
			    <tr>
			      <th scope="col">Todo</th>
			      <th scope="col">期限</th>
			      <th></th>
			    </tr>
			</thead>
	    	<tbody>
		    	<% for(TodoDTO todo: todoList){  %>
			    	<tr>
				      <div class="row">
				        <th class="col-4"><%=todo.getTodo()%></th>
				        <td class="col-4"><%=todo.getTimeLimit()%></td>
				        <td class="col-4">				        	
					        <a href="update-servlet?id=<%= todo.getId() %>" class="btn btn-primary">更新</a>
					        <a href="delete-servlet?id=<%= todo.getId() %>" class="btn btn-primary">削除</a>
				        </td>
				      </div>
			    	</tr>
			    <% } %>
	    	</tbody>
	    </table>
	    <div class="text-center">
		    <h2>Todo登録</h2>
		    <form action="insert-servlet" method="post" class="ms-auto me-auto">
		        <div class="row g-2 align-items-center mb-2 justify-content-center">
		        	<div class="col-1">
		             	<label for="todo" class="form-label">Todo:</label>
		        	</div>
		        	<div class="col-3">
		            	<input type="text" class="form-control" name="todo" id="todo">
		        	</div>
		        </div>
		        <div class="row g-2 align-items-center mb-2 justify-content-center">
		        	<div class="col-1">
		            	<label for="timeLimit" class="form-label">期限:</label>
		        	</div>
		        	<div class="col-3">
		             	<input type="date" class="form-control" name="timeLimit" id="timeLimit">
		        	</div>
		        </div>
		        <button type="submit" class="btn btn-primary">Todoを登録する</button>
		    </form>
	    </div>
	</div>
	    
</body>
</html>