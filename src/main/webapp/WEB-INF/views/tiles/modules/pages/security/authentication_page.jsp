<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 12.02.2016
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h3 class="desc_page"><spring:message code="authentication.page.description"/></h3>


<div class="authenticationDiv">
  <jsp:include page="../../forms/authentication_form.jsp"/>
</div>