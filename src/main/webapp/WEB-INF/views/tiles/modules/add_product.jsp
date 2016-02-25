<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 30.01.2016
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="desc_page"><spring:message code="product.add.page.description"/></h3>

<c:if test="${not empty serviceMessage}">
  <div id="service_message">
    <p><spring:message code="${serviceMessage}"/></p>
  </div>
</c:if>

<div class="product_form_wrapper">
  <jsp:include page="forms/add_product_form.jsp"/>
</div>
