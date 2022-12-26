<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Replenishment</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body class="vh-100" style="background-color: #eee;">

<jsp:include page="partial-pages/header.jsp" />

<div class="container d-flex flex-column align-items-center justify-content-center">
    <p>You are creating replenishment to <span class="h5">${requestScope.moneyAccount.getName()} card</span> (${requestScope.moneyCard.getPrettyBalanceString()})</p>
    <hr>

    <div class="rounded border border-dark d-flex flex-column" style="background-color: slategray; width: 220px; height: 125px;">
        <div class="d-flex flex-row justify-content-around mt-1">
            <p class="oncard">Sender's card number:</p>
        </div>
        <input class="text-center" maxlength="16" oninput="updateButtonStatus()" type="text" id="sender-card-number"/>
    </div>

    <div class="form-outline flex-fill mt-2">
        <input class="form-control text-center" maxlength="4" oninput="updateButtonStatus()" type="text" id="sender-card-secret"/>
        <label class="form-label" for="sender-card-secret">Sender's card secret:</label>
    </div>

    <div class="form-outline flex-fill mt-2">
        <input type="number" id="money-amount" min="0" max="999999" value="0.00" step="0.1" class="form-control" />
        <label class="form-label" for="money-amount">Money amount ($)</label>
    </div>

    <button class="btn btn-outline-success mt-3" id="postButton"
            onclick="sendCreateReplenishmentRequest(window.location.href)">
        Send billing
    </button>

    <div id="result" class="mt-3">

    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/js/create-replenishment.js"></script>

</body>
</html>