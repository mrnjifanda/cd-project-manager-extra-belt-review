<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		</div>
	</body>
</html>