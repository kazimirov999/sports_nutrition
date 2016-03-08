<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="property_navigation">
<ul>
  <li><a href="<c:url value="/property/taste" />"><spring:message code="taste"/></a></li>
  <li><a href="<c:url value="/property/brand" />"><spring:message code="brand"/></a></li>
  <li><a href="<c:url value="/property/discount" />"><spring:message code="discount"/></a></li>


</ul>
  </div>