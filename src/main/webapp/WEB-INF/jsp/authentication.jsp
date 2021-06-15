<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>MovieDB</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
    <div >
        <c:if test="${not empty usernameTaken}">
            <p class="text-warning">${usernameTaken} </p>
        </c:if>
        <c:if test="${param.error ne null}">
            <p class="text-warning">Wrong username and password </p>
        </c:if>
        <c:if test="${param.registered ne null}">
            <p class="text-info">User account registered successfully. You can now login with you credentials</p>
        </c:if>
    </div>
    <div class="row">
        <form method="post" id="auth-form" action="/login" commandName="${authRequest}">
            <div class="form-group">
                <label for="username">Name</label>
                <input type="text" name="username" value="${authRequest.username}" required class="form-control" required id="username"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" value="${authRequest.password}" required class="form-control" required id="password"/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-sm btn-primary w-100 my-2">Login</button>
            </div>
            <div class="form-group">
                <a type="submit" id="sign-up-btn" class="btn btn-sm btn-primary w-100 my-2">Sign Up</a>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    let authForm = document.getElementById("auth-form");
    let signUpBtn = document.getElementById("sign-up-btn");

    signUpBtn.addEventListener("click", e => {
        e.preventDefault();
        authForm.action ="/register";
        authForm.submit();
    } );
</script>

</body>
</html>