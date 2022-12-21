<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <a href="${pageContext.request.contextPath}/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
            <h5>${sessionScope.user.getFullName()}</h5>
        </a>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="${pageContext.request.contextPath}/" class="nav-link px-2 link-dark">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/cabinet" class="nav-link px-2 link-dark">Cabinet</a></li>
            <li><a href="${pageContext.request.contextPath}/about" class="nav-link px-2 link-dark">About</a></li>
        </ul>

        <div class="col-md-3 text-end">
            <a href="${pageContext.request.contextPath}/auth/signout" type="button" class="btn btn-outline-primary me-2">Signout</a>
        </div>
    </header>
</div>