<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3 class="desc_page"><spring:message code="product.add.page.description"/></h3>

<c:if test="${not empty serviceMessage}">
  <div id="service_message">
    <p><spring:message code="${serviceMessage}"/></p>
  </div>
</c:if>

<div class="product_form_wrapper">
  <jsp:include page="../forms/add_product_form.jsp"/>
</div>
