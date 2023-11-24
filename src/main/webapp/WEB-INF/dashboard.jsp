<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
		<title>Project Manager Dashboard</title>
	</head>
	<body>
		<div class="container mt-5">
			<div class="d-flex align-items-end justify-content-between">
				<h1>Welcome, ${ user.firstName }!</h1>
		
				<form action="/auth/logout" method="post"><input class="btn btn-link" type="submit" value="Logout" /></form>
			</div>
			
			<div class="d-flex align-items-end justify-content-between mt-5">
				<h4>All projects</h4>
				<a class="btn btn-primary" href="/projects/new">+ New Project</a>
			</div>
			
			<div class="mt-5">
				<table class="table text-center table-striped table-hover">
					<thead class="table-dark">
						<tr>
							<th>Project</th>
							<th>Team Lead</th>
							<th>Due Date</th>
							<th>Actions</th>
						</tr>	
					</thead>
					<tbody>
						<c:forEach var="project" items="${ projects }">
							<tr>
								<td>${ project.title }</td>
								<td>${ project.user.firstName }</td>
								<td>${ project.dueDate }</td>
								<td><a href="/projects/join/${ project.id }" class="btn btn-link">Join Team</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="mt-5">
				<h1>Your Project</h1>
				<table class="table text-center table-striped table-hover">
					<thead class="table-dark">
						<tr>
							<th>Project</th>
							<th>Lead</th>
							<th>Due Date</th>
							<th>Actions</th>
						</tr>	
					</thead>
					<tbody>
						<c:forEach var="yourProject" items="${ user.projects }">
							<tr>
								<td>${ yourProject.title }</td>
								<td>${ yourProject.user.firstName }</td>
								<td>${ yourProject.dueDate }</td>
								<td><a href="/projects/join/${ yourProject.id }" class="btn btn-link">Join Team</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>