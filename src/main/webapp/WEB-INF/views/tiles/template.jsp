<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 22.01.2016
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:importAttribute name="titleName"/>
<tiles:importAttribute name="metaKeysGen"/>
<spring:message text="Sports nutrition" code="meta.desc.suf" var="metaDescSuf"/>
<spring:message text="Sports nutrition" code="meta.key.suf" var="metaKeysSuf"/>

<!DOCTYPE >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="keywords" content="${metaKeysGen} ${metaKeysSuf}" >
    <meta name="description" content="${metaKeysGen} ${metaDescSuf}" >

    <link rel="stylesheet" type="text/css" href="/resources/styles/css/menu.css">
    <link rel="stylesheet" type="text/css" href="/resources/styles/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/css/forms.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/css/paginator.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/css/product.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/css/product.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/css/slider.css"/>

    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
    <script src="/resources/styles/scripts/slider.js"></script>
    <script src="/resources/styles/scripts/menu.js"></script>


    <c:choose>
        <c:when test="${not empty titleName}">
            <title><spring:message text="Sports nutrition" code="${titleName}"/></title>
        </c:when>
        <c:otherwise>
            <title><tiles:insertAttribute name="title" ignore="true"/></title>
        </c:otherwise>
    </c:choose>


</head>
<body>

<div class="wrapper">

    <div class="header">
        <tiles:insertAttribute name="header"/>
    </div>
    <!-- .header-->

    <div class="middle">

        <div class="container">
            <div class="content">
                <tiles:insertAttribute name="body"/>
            </div>
            <!-- .content-->
        </div>
        <!-- .containers-->

        <div class="left-sidebar">
            <tiles:insertAttribute name="menu"/>
            <tiles:insertAttribute name="filter_form"/>
        </div>
        <!-- .left-sidebar -->
    </div>
    <!-- .middle-->

    <div class="clear"></div>

    <div class="footer">
        <tiles:insertAttribute name="footer"/>
    </div>
    <!-- .footer -->

</div>

</body>
</html>
