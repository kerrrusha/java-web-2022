<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Money cards info</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body class="vh-100" style="background-color: #eee;">

<jsp:include page="partial-pages/header.jsp" />

<div class="container">
    <h1 class="display-4">Money accounts info</h1>
    <hr>
    <div class="d-flex flex-row justify-content-between align-items-center">
        <p>Sort content by:</p>
        <select class="form-select form-select-sm" id="sortType" onchange="updateSorting()" style="width: auto;">
            <option value="id-asc" selected>Money account ID (asc)</option>
            <option value="id-desc">Money account ID (desc)</option>
            <option value="name-asc">Money account name (asc)</option>
            <option value="name-desc">Money account name (desc)</option>
            <option value="balance-asc">Balance (asc)</option>
            <option value="balance-desc">Balance (desc)</option>
        </select>
    </div>
    <div class="my-3">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Money account ID</th>
                <th scope="col">Money account name</th>
                <th scope="col">Card number</th>
                <th scope="col">Balance</th>
                <th scope="col">Expiration date</th>
            </tr>
            </thead>
            <tbody id="moneyCardsTableBody">

            </tbody>
        </table>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/js/moneycards.js"></script>

</body>
</html>