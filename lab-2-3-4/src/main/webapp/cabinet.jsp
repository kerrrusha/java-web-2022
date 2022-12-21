<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Personal cabinet - ${sessionScope.user.getFullName()}</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body class="vh-100" style="background-color: #eee;">

<jsp:include page="partial-pages/header.jsp" />

<div class="container">
  <h1 class="display-4">Personal cabinet</h1>
  <h3 class="mb-3">Here you can check your payment profile information</h3>
  <hr>
  <div class="my-3">
    <h5>First name: <span class="display-5">${sessionScope.user.getFirstName()}</span></h5>
    <h5>Last name: <span class="display-5">${sessionScope.user.getLastName()}</span></h5>
    <h5>Phone: <span class="display-5">${sessionScope.user.getPhone()}</span></h5>
    <h5>Role: <span class="display-5">
      ${requestScope.roleService.getRoleNameById(sessionScope.user.getRoleId())}
    </span></h5>
    <h5>Registered: <span class="display-5">${sessionScope.user.getCreatedTime()}</span></h5>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
</html>