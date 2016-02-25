<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 19.02.2016
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="page.not.fount" var="pageNotFound"/>
<spring:message code="backToMain.page" var="backToMain"/>


<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css" href="/resources/styles/css/error.css"/>


    <title>Page Not Fount 404</title>

</head>

<body>

<div class="wrapperError">
    <div class="TitleError">404 Page Not Fount</div>

    <p class="messageError">${pageNotFound}</p>

    <p><a href="<c:url value='/' />">${backToMain}</a></p>
</div>

</body>

