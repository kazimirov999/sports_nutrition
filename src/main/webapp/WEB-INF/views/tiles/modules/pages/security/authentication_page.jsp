<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h3 class="desc_page"><spring:message code="authentication.page.description"/></h3>


<div class="authenticationDiv">
  <jsp:include page="../../forms/authentication_form.jsp"/>
</div>