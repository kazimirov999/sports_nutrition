<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<h3 class="desc_page"><spring:message code="category.edit.page.description"/></h3>

<p><spring:message code="category.edit.page.inform"/></p>

<c:if test="${not empty serviceMessage}">
    <div id="service_message">
        <p><spring:message code="${serviceMessage}"/></p>
    </div>
</c:if>

<c:if test="${not empty formCategoryBean}">
    <div id="category_form">
        <jsp:include page="../forms/edit_category_form.jsp"/>
    </div>
</c:if>
</body>
</html>
