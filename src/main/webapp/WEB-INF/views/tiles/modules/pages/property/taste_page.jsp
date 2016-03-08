<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="property_page.jsp"/>


<c:if test="${not empty serviceMessage}">
    <div id="service_message">
        <p><spring:message code="${serviceMessage}"/></p>
    </div>
</c:if>

<c:if test="${not empty tasteToAddBean}">
    <div class="taste_form">
        <form:form method="post" action="/add/property/taste" commandName="tasteToAddBean">
            <ul>
                <li><p class="error"><form:errors path="name" cssclass="error"></form:errors></p></li>
                <li><spring:message code="name.input"/></li>
                <li><form:input id="name" path="name"/></li>
                <li class="submit">
                    <input id="submit" type="submit" value="<spring:message code="add"/>"/></li>
            </ul>
            </form:form>
    </div>
</c:if>
<c:if test="${empty tasteList && empty tasteToAddBean }">
    <p><spring:message code="taste.empty"/>
        <a href="<c:url value="/add/property/taste"/>"><spring:message code="add"/></a></p>
</c:if>

<c:if test="${not empty tasteToEditBean}">
    <div class="taste_form">
        <form:form method="post" action="/edit/property/taste" commandName="tasteToEditBean">
            <ul>
                <li><p class="error"><form:errors path="name" cssclass="error"></form:errors></p></li>
                <li><spring:message code="name.input"/></li>
                <li><form:input id="name" path="name"/></li>
                <li class="submit">
                    <input id="submit" type="submit" value="<spring:message code="save"/>"/></li>
                <form:input type="hidden" cssClass="hidden_id_input" path="id"/>
            </ul>
            </form:form>
    </div>
</c:if>

<c:if test="${not empty tasteList}">

    <div id="property_div_table">
        <table class="property_table">
            <c:forEach items="${tasteList}" var="taste">
                <tr>
                    <td><p>${taste.name}</p></td>
                    <td><a href="<c:url value="/delete/property/taste/${taste.id}" />"><spring:message
                            code="delete"/></a></td>
                    <td><a href="<c:url value="/edit/property/taste/${taste.id}" />"><spring:message code="edit"/></a></td>
                </tr>
            </c:forEach>
            <td colspan="3"><a href="<c:url value="/add/property/taste"/>"><spring:message code="add"/></a></td>
        </table>
    </div>
</c:if>
