<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Open new money card</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body class="vh-100" style="background-color: #eee;">

<jsp:include page="partial-pages/header.jsp" />

<div class="container">
  <p>You have already opened ${requestScope.moneyCardService.getUserActiveCardsAmount()} of ${requestScope.moneyCardService.getMaxActiveCardsAllowedAmount()} cards.</p>
  <hr>

  <div class="form-outline flex-fill">
    <input type="text" id="moneyAccountName" class="form-control" value="Standard" oninput="updateButtonStatus()"/>
    <label class="form-label" for="moneyAccountName">New Money Account name</label>
  </div>

  <button class="btn btn-outline-success mt-3" id="postButton"
          onclick="sendOpenNewCardRequest(window.location.href)">
    Open new money card
  </button>

  <div id="result" class="mt-3">

  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/js/open-new-money-card.js"></script>

</body>
</html>