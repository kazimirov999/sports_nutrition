<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 26.01.2016
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<h3 class="desc_page"><spring:message code="category.add.page.description"/></h3>

<c:if test="${not empty serviceMessage}">
<div id="service_message">
    <p><spring:message code="${serviceMessage}"/></p>
</div>
</c:if>

<div id="category_form">
  <jsp:include page="../../forms/add_category_form.jsp"/>
</div>
</body>
</html>
