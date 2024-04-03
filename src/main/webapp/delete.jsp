<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 01/04/2024
  Time: 9:14 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Student</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Delete Student</h1>
    <p>Are you sure you want to delete this student?</p>
    <form action="${pageContext.request.contextPath}/delete-student?id=${student.id}" method="POST">
        <input type="hidden" name="id" value="${student.id}">
        <button type="submit" class="btn btn-danger">Delete</button>
        <a href="${pageContext.request.contextPath}/students" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
