<%@ include file="index.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Create New Student</title>
  <!-- Bootstrap CSS -->
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <h1>Create New Student</h1>
  <form action="${pageContext.request.contextPath}/add-student" method="POST">
    <div class="form-group">
      <label for="nameStudent">Name</label>
      <input type="text" class="form-control" id="nameStudent" name="nameStudent" required>
    </div>
    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" class="form-control" id="email" name="email" required>
    </div>
    <div class="form-group">
      <label for="dob">Date of Birth</label>
      <input type="date" class="form-control" id="dob" name="dob" required>
    </div>
    <div class="form-group">
      <label for="address">Address</label>
      <input type="text" class="form-control" id="address" name="address" required>
    </div>
    <div class="form-group">
      <label for="phoneNumber">Phone Number</label>
      <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
    </div>
    <div class="form-group">
      <label for="classID">Classroom</label>
      <select class="form-control" id="classID" name="classID" required>
        <option value="">Select Classroom</option>
        <%-- Loop through classrooms and populate dropdown options --%>
        <c:forEach var="classroom" items="${classrooms}">
          <option value="${classroom.classID}">${classroom.nameClass}</option>
        </c:forEach>
      </select>
    </div>
    <button type="submit" class="btn btn-primary">Create</button>
    <a href="${pageContext.request.contextPath}/students" class="btn btn-danger">Cane</a>
  </form>
</div>
</body>
</html>

