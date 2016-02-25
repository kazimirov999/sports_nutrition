<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 03.02.2016
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="property_navigation">
<ul>
  <li><a href="<c:url value="/property/taste" />"><spring:message code="taste"/></a></li>
  <li><a href="<c:url value="/property/brand" />"><spring:message code="brand"/></a></li>
  <li><a href="<c:url value="/property/discount" />"><spring:message code="discount"/></a></li>


</ul>
  </div>