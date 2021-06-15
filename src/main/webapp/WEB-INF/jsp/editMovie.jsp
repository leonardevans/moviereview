<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>MovieDB - Add Movie</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">MovieDB</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Genre
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:forEach items="${genres}" var="genre">
                            <li><a class="dropdown-item" href="/genre/${genre.id}">${genre.name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Rating
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown1">
                        <li><a class="dropdown-item" href="/rating/1">1+</a></li>
                        <li><a class="dropdown-item" href="/rating/2">2+</a></li>
                        <li><a class="dropdown-item" href="/rating/3">3+</a></li>
                        <li><a class="dropdown-item" href="/rating/4">4+</a></li>
                        <li><a class="dropdown-item" href="/rating/5">5+</a></li>
                        <li><a class="dropdown-item" href="/rating/6">6+</a></li>
                        <li><a class="dropdown-item" href="/rating/7">7+</a></li>
                        <li><a class="dropdown-item" href="/rating/8">8+</a></li>
                        <li><a class="dropdown-item" href="/rating/9">9+</a></li>
                        <li><a class="dropdown-item" href="/rating/10">10</a></li>
                    </ul>
                </li>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                    <li class="nav-item dropdown">
                        <a class="nav-link" href="/admin/add-movie">
                            Add Movie
                        </a>
                    </li>
                </sec:authorize>

                <li class="nav-item dropdown">
                    <sec:authorize access="isAuthenticated()">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Welcome, <sec:authentication property="principal.username" />
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown2">
                            <li><a class="dropdown-item" href="/logout">Logout</a></li>
                        </ul>
                    </sec:authorize>

                    <sec:authorize access="!isAuthenticated()">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            More
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown2">
                            <li><a class="dropdown-item" href="/login">Login</a></li>
                        </ul>
                    </sec:authorize>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div>
        <c:if test="${param.updated ne null}">
            <p class="text-info">Movie updated successfully</p>
        </c:if>
    </div>
    <div class="row">
        <form action="/admin/update-movie" method="post" enctype="multipart/form-data" commandName="${movieRequest}">
            <input type="hidden" name="id" value="${movieRequest.id}" required class="form-control" required id="id"/>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" name="name" value="${movieRequest.name}" required class="form-control" required id="name"/>
            </div>

            <div class="form-group">
                <label for="genre">Genre</label>
                <select name="genre" id="genre" required class="form-select form-select-sm">
                    <c:forEach items="${genres}" var="genre">
                        <option value="${genre.id}" ${movieRequest.genre.id == genre.id ? 'selected' : ''}>${genre.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="releaseYear">Release Year</label>
                <input type="number" name="releaseYear" value="${movieRequest.releaseYear}" required class="form-control" required id="releaseYear"/>
            </div>

            <div class="form-group">
                <label for="rating">Rating</label>
                <input type="number" name="rating" value="${movieRequest.rating}" required class="form-control" required id="rating"/>
            </div>

            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" required class="form-control" id="description" name="description" rows="3">
                    ${movieRequest.description}
                </textarea>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-sm btn-primary">Update Movie</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>