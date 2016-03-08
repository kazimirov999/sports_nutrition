<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<h3 class="desc_page"><spring:message code="category.page.describtion"/></h3>--%>

<c:if test="${not empty serviceMessage}">
    <div id="service_message">
        <p><spring:message code="${serviceMessage}"/></p>
    </div>
</c:if>
<c:if test="${not empty category}">

    <div id="failure_message">
        <p><spring:message code="${failureMessage}"/></p>
        <p><a href="<c:url value="/delete/all/products/${category.id}" />">
            <spring:message code="category.delete.all.product"/> - "${category.name}"
        </a></p>
    </div>
</c:if>
