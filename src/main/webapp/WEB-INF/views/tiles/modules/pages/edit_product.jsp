<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 02.02.2016
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="desc_page"><spring:message code="product.edit.page.description"/></h3>

<c:if test="${not empty serviceMessage}">
  <div id="service_message">
    <p><spring:message code="${serviceMessage}"/></p>
  </div>
</c:if>

<div class="product_form_wrapper">
  <jsp:include page="../forms/edit_product_form.jsp"/>
</div>
