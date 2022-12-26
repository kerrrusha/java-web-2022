<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Admin panel</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body class="vh-100" style="background-color: #eee;">

<jsp:include page="../partial-pages/header.jsp" />

<div class="container d-flex flex-column align-items-center justify-content-center">
  <h1 class="display-4">Welcome to admin panel!</h1>
  <hr>
  <div class="w-25">
    <div class="my-5">
      <div class="form-group mb-2">
        <label for="money-account-id">Client's money account</label>
        <input type="text" oninput="updateMoneyAccountButtonStatus()" class="form-control" id="money-account-id" placeholder="Money account ID">
      </div>
      <div class="d-flex flex-row justify-content-between">
        <button id="money-account-block-button"
                onclick="sendBlockMoneyAccountRequest('${pageContext.request.contextPath}/admin/block-money-account')"
                class="btn btn-primary">Block</button>
        <button id="money-account-unblock-button"
                onclick="sendUnblockMoneyAccountRequest('${pageContext.request.contextPath}/admin/unblock-money-account')"
                class="btn btn-secondary">Unblock</button>
      </div>
      <div id="money-account-result"></div>
    </div>
    <div class="my-5">
      <div class="form-group mb-2">
        <label for="client-id">Whole client's account</label>
        <input type="text" oninput="updateClientButtonStatus()" class="form-control" id="client-id" placeholder="Client ID">
      </div>
      <div class="d-flex flex-row justify-content-between">
        <button id="client-block-button"
                onclick="sendBlockClientRequest('${pageContext.request.contextPath}/admin/block-client')"
                class="btn btn-primary">Block</button>
        <button id="client-unblock-button"
                onclick="sendUnblockClientRequest('${pageContext.request.contextPath}/admin/unblock-client')"
                class="btn btn-secondary">Unblock</button>
      </div>
      <div id="client-result"></div>
    </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/js/admin-panel.js"></script>

</body>
</html>