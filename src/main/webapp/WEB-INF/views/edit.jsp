<%--suppress JSUnresolvedLibraryURL --%>
<%--@elvariable id="login" type="java.lang.String"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="URL" value="${pageContext.servletContext.contextPath}"/>
<c:set var="nameVal" value=""/>
<c:set var="descriptionVal" value=""/>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <c:if test="${not empty oldPost}">
        <c:set var="title" value="Edit Post"/>
    </c:if>
    <c:if test="${empty oldPost}">
        <c:set var="title" value="New Post"/>
    </c:if>
    <c:if test="${not empty comment}">
        <c:set var="title" value="New Comment"/>
    </c:if>
    <title>${title}</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${URL}/">Форум job4j</a>
    <button class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-link" href="${URL}/create">Новая тема</a>
            <a class="nav-link" href="${URL}/logout">Выйти</a>
        </div>
    </div>
</nav>
<div class="container mt-3">
    <%--@elvariable id="errorMessage" type="java.lang.String"--%>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>
    <form name="topic" action="<c:url value="${action}"/>" method="POST">
        <input type="hidden" name="author" value="${login}">
        <c:if test="${not empty oldPost}">
            <c:set var="nameVal" value="${oldPost.name}"/>
            <c:set var="descriptionVal" value="${oldPost.description}"/>
            <input type="hidden" name="oldPostId" value="${oldPost.id}">
            <input type="hidden" name="author" value="${oldPost.author.login}">
        </c:if>
        <c:if test="${not empty oldPost}">
            <input type="hidden" name="name" value="${nameVal}">
            <input type="hidden" name="author" value="${oldPost.author.login}">
        </c:if>
        <c:if test="${not empty comment}">
            <input type="hidden" name="comment" value="${comment}">
            <input type="hidden" name="name" value="${nameVal}">

        </c:if>
        <c:if test="${empty oldPost && empty comment}">
            <div class="form-group">
                <label for="name">Тема</label>
                <input type="text" class="form-control" name="name" id="name" placeholder="Тема" value="${nameVal}">
            </div>
        </c:if>
        <div class="form-group">
            <label for="description">Сообщение</label>
            <textarea class="form-control" name="description" id="description" rows="3" placeholder="Сообщение">${descriptionVal}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">Создать</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>