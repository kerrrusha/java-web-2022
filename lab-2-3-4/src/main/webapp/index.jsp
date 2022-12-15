<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Payment System</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <style>
        .payment-card {
            background-color: aquamarine; width: 220px; height: 100px;
        }
        .oncard {
            background-color: #eeeeee;
        }
    </style>
</head>
<body class="vh-100" style="background-color: #eee;">

<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <a href="${pageContext.request.contextPath}/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
            <h5>${sessionScope.user.getFullName()}</h5>
        </a>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="${pageContext.request.contextPath}/" class="nav-link px-2 link-dark">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/about" class="nav-link px-2 link-dark">About</a></li>
        </ul>

        <div class="col-md-3 text-end">
            <a href="${pageContext.request.contextPath}/auth/signout" type="button" class="btn btn-outline-primary me-2">Signout</a>
        </div>
    </header>
</div>

<div class="container">
    <h1 class="display-4">Welcome to your Payment System account!</h1>
    <h3 class="mb-3">Here you can manage your money cards and billings.</h3>
    <hr>
    <div class="my-3">
        <p>Your payment cards</p>
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0 d-flex flex-column">
            <li class="border d-flex flex-row justify-content-around align-items-center p-3" style="align-content: normal;">
                <div class="d-flex flex-column align-items-center justify-content-start">
                    <h4>Universal card</h4>
                    <p class="display-6">5.50 $</p>
                    <p class="text-muted">Created at: 16.12.2022</p>
                </div>
                <div class="rounded border payment-card d-flex flex-column">
                    <div class="d-flex flex-row justify-content-between">
                        <p class="oncard secret">123</p>
                        <p class="oncard expiration-date">12.2024</p>
                    </div>
                    <p class="oncard number text-center">1234 5678 9101 1121</p>
                </div>
                <div class="d-flex flex-column align-items-center justify-content-start">
                    <a href="">Create billing</a>
                    <a href="">Block this card</a>
                </div>
            </li>
        </ul>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
</html>