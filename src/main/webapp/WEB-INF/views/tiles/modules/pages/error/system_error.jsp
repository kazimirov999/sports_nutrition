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

<spring:message code="page.system.error" var="systemError"/>
<spring:message code="backToMain.page" var="backToMain"/>


<html>
<head>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" type="text/css" href="/resources/styles/css/error.css"/>


  <title>System error</title>

</head>

<body>

<div class="wrapperError">
  <div class="TitleError">System Error</div>

  <p class="messageError">${systemError}</p>

  <p><a href="<c:url value='/' />">${backToMain}</a></p>
</div>

</body>

