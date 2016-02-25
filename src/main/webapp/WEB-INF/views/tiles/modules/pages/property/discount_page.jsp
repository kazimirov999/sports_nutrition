<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 03.02.2016
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="property_page.jsp"/>


<c:if test="${not empty serviceMessage}">
    <div id="service_message">
        <p><spring:message code="${serviceMessage}"/></p>
    </div>
</c:if>


<c:if test="${not empty discountToAddBean}">
    <div class="discount_form">
        <form:form method="post" action="/add/property/discount" commandName="discountToAddBean">
        <table class="discount_form_table">
            <tr>
                <td><spring:message code="name.input"/></td>
                <td><form:input id="name" path="name"/></td>
                <td><p class="error"><form:errors path="name" cssclass="error"></form:errors></p></td>
            </tr>
            <tr>
                <td><spring:message code="discount.size"/></td>
                <td><form:input class="discount" path="size"/></td>
                <td><p class="error"><form:errors path="size" cssclass="error"></form:errors></p></td>
            </tr>
            <tr>
                <td><spring:message code="discount.expiration_date"/><br>
                    <spring:message code="format.date"/></td>
                <td><form:input class="expiration_date" path="expirationDate"/></td>
                <td><p class="error"><form:errors path="expirationDate" cssclass="error"></form:errors></p></td>
            </tr>
            <tr>
                <td></td>
                <td class="submit"><input id="submit" type="submit" value="<spring:message code="add"/>"/></td>
                <td></td>
            </tr>
            </table>
                <form:input type="hidden" cssClass="hidden_id_input" path="id"/>
            </form:form>
    </div>
</c:if>

<c:if test="${empty discountList && empty discountToAddBean }">
    <p><spring:message code="discount.empty"/>
        <a href="<c:url value="/add/property/discount"/>"><spring:message code="add"/></a></p>
</c:if>

<c:if test="${not empty discountToEditBean}">
    <div class="discount_form">
        <form:form method="post" action="/edit/property/discount" commandName="discountToEditBean">
        <table class="discount_form_table">

            <tr>
                <td><spring:message code="name.input"/></td>
                <td><form:input id="name" path="name"/></td>
                <td><p class="error"><form:errors path="name" cssclass="error"></form:errors></p></td>
            </tr>
            <tr>
                <td><spring:message code="discount.size"/></td>
                <td><form:input class="discount" path="size"/></td>
                <td><p class="error"><form:errors path="size" cssclass="error"></form:errors></p></td>
            </tr>
            <tr>
            <tr>
                <td><spring:message code="discount.expiration_date"/><br>
                    <spring:message code="format.date"/></td>
                <td><form:input class="description" path="expirationDate"/></td>
                <td><p class="error"><form:errors path="expirationDate" cssclass="error"></form:errors></p></td>
            </tr>
            <tr>
                <td></td>
                <td class="submit"><input id="submit" type="submit" value="<spring:message code="save"/>"/></td>
                <td></td>
            </tr>
                <form:input type="hidden" cssClass="hidden_id_input" path="id"/>
            </form:form>
    </div>
</c:if>

<c:if test="${not empty discountList}">

    <div id="property_div_table">
        <table class="property_table">
            <tr>
                <td><spring:message code="discount.name"/></td>
                <td><spring:message code="discount.size"/>(%)</td>
                <td><spring:message code="discount.expiration_date"/></td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${discountList}" var="discount">

                <tr>
                    <td><p>${discount.name}</p></td>
                    <td><p>${discount.size}</p></td>
                    <td><p>${discount.expirationDate.toString("yyyy-MM-dd")}</p></td>
                    <td><a href="<c:url value="/delete/property/discount/${discount.id}" />"><spring:message
                            code="delete"/></a></td>
                    <td><a href="<c:url value="/edit/property/discount/${discount.id}" />"><spring:message
                            code="edit"/></a></td>
                </tr>
            </c:forEach>
            <td colspan="3"><a href="<c:url value="/add/property/discount"/>"><spring:message code="add"/></a></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </table>
    </div>
</c:if>
