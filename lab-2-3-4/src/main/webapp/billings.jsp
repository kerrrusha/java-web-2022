<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Billing history</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body class="vh-100" style="background-color: #eee;">

<jsp:include page="partial-pages/header.jsp" />

<div class="container pb-5">
  <h1 class="display-4">Billing history</h1>
  <hr>
  <div class="d-flex flex-row justify-content-between align-items-center">
    <p>Sort content by:</p>
    <select class="form-select form-select-sm" id="sortType" onchange="updateSortingAndFillTable()" style="width: auto;">
      <option value="id-asc">Billing ID (asc)</option>
      <option value="id-desc">Billing ID (desc)</option>
      <option value="date-asc">Date (asc)</option>
      <option value="date-desc" selected>Date (desc)</option>
    </select>
  </div>
  <div class="my-3">
    <table class="table">
      <thead>
      <tr>
        <th scope="col">Billing ID</th>
        <th scope="col">Money amount</th>
        <th scope="col">From card number</th>
        <th scope="col">To card number</th>
        <th scope="col">Status</th>
        <th scope="col">Date</th>
      </tr>
      </thead>
      <tbody id="billingsTableBody">

      </tbody>
    </table>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/js/billings.js"></script>

</body>
</html>