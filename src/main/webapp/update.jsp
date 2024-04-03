<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 01/04/2024
  Time: 9:14 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Student</title>
  <!-- Bootstrap CSS -->
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <h1>Edit Student</h1>
  <form action="${pageContext.request.contextPath}/update-student?id=${student.id}" method="POST">
    <div class="form-group">
      <label for="nameStudent">Name</label>
      <input type="text" class="form-control" id="nameStudent" name="nameStudent" value="${student.nameStudent}">
    </div>
    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" class="form-control" id="email" name="email" value="${student.email}">
    </div>
    <div class="form-group">
      <label for="dob">Date of Birth</label>
      <input type="date" class="form-control" id="dob" name="dob" value="${student.dob}">
    </div>
    <div class="form-group">
      <label for="address">Address</label>
      <input type="text" class="form-control" id="address" name="address" value="${student.address}">
    </div>
    <div class="form-group">
      <label for="phoneNumber">Phone Number</label>
      <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${student.phoneNumber}">
    </div>
    <div class="form-group">
      <label for="classID">Classroom</label>
      <select class="form-control" id="classID" name="classID">
        <c:forEach var="classroom" items="${classrooms}">
          <option value="${classroom.classID}" ${classroom.classID == student.classID ? 'selected' : ''}>${classroom.nameClass}</option>
        </c:forEach>
      </select>
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
    <a href="${pageContext.request.contextPath}/search-student?search=${student.nameStudent}" class="btn btn-danger">Cane</a>
  </form>
</div>
</body>
</html>

