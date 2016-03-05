<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 12.02.2016
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="authentication.button" var="authButton"/>
<spring:message code="register.button" var="registerButton"/>

<c:url value="/j_spring_security_check" var="loginUrl"/>

<form class="form-container " method="post" action="${loginUrl}">

    <div class="form-title"><h2><spring:message code="authentication.user"/></h2></div>

    <c:if test="${not empty errorAuth}">
        <div id="service_message">
            <p class="error"><spring:message code="${errorAuth}"/></p>
        </div>
    </c:if>

    <div class="form-title">
        <spring:message code="login.email"/>
    </div>
    <input class="form-field" type='text' name='username'/>

    <div class="form-title">
        <spring:message code="password.user"/>
    </div>
    <input class="form-field" type='password' name='password'>

    <div class="submit-container submit">
        <input class="submit-button" name="submit" type="submit"
               value="${authButton}">

        <input class="submit-button" type=button
               onClick="location.href='/register'"
               value='${registerButton}'>
    </div>
</form>



