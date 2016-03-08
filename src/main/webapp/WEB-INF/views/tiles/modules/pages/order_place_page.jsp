<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${cart.size() != '0'}">
    <jsp:include page="../forms/order_form.jsp"/>
</c:if>