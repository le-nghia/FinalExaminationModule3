<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 01/04/2024
  Time: 10:04 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results for ${param.name}</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Search Results</h1>
    <h2>Results for: <c:out value="${param.search}"/></h2>
    <a href="${pageContext.request.contextPath}/students" class="btn btn-primary">Home</a>
    <!-- Sử dụng thẻ c:out để tránh XSS -->
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Date of Birth</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Classroom</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.nameStudent}</td>
                <td>${student.email}</td>
                <td>${student.dob}</td>
                <td>${student.address}</td>
                <td>${student.phoneNumber}</td>
                <td>${student.classID}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/update-student?id=${student.id}" class="btn btn-primary">Edit</a>
                    <a href="${pageContext.request.contextPath}/delete-student?id=${student.id}" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
