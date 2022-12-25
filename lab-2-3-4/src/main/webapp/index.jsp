<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Payment System</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <style>
        .oncard {
            background-color: #eeeeee;
        }
    </style>
</head>
<body class="vh-100" style="background-color: #eee;">

<jsp:include page="partial-pages/header.jsp" />

<div class="container pb-5">
    <h1 class="display-4">Welcome to your Payment System account!</h1>
    <h3 class="mb-3">Here you can manage your money cards and billings.</h3>
    <hr>
    <div class="my-3">
        <div class="d-flex flex-row justify-content-between align-items-center">
            <p class="display-5">Your payment cards:</p>
            <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/open-new-money-card">Open new money card</a>
        </div>
        <div id="create-billing-base-url" style="display: none;">${pageContext.request.contextPath}/create-billing?fromMoneyAccountId=</div>
        <ul id="card-list" class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0 d-flex flex-column">

        </ul>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/js/index.js"></script>

</body>
</html>