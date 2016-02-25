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

<c:if test="${not empty brandToAddBean}">
    <div class="brand_form">
        <form:form method="post" action="/add/property/brand" commandName="brandToAddBean">
            <table class="brand_form_table">
                <tr>
                    <td>
                        <p class="error"><form:errors path="name" cssclass="error"></form:errors></p></td>
                    <td><spring:message code="name.input"/></td>
                    <td><form:input id="name" path="name"/></td>

                </tr>
                <tr>
                    <td><p class="error"><form:errors path="description" cssclass="error"></form:errors></p></td>
                    <td><spring:message code="description"/></td>
                    <td><form:input class="description" path="description"/></td>
                </tr>
                <tr>
                    <td><p class="error"><form:errors path="country" cssClass="error"/></p></td>
                    <td><spring:message code="country" text="Country"/></td>
                    <td><form:select class="brand" path="country" items="${countryList}"
                                     itemValue="id"
                                     itemLabel="name"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    </td>
                    <td class="submit">
                        <input id="submit" type="submit" value="<spring:message code="add"/>"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</c:if>

<c:if test="${empty brandList && empty brandToAddBean }">
    <p><spring:message code="brand.empty"/>
        <a href="<c:url value="/add/property/brand"/>"><spring:message code="add"/></a></p>
</c:if>

<c:if test="${not empty brandToEditBean}">
    <div class="brand_form">
        <form:form method="post" action="/edit/property/brand" commandName="brandToEditBean">
            <table class="brand_form_table">
                <tr>
                    <td><p class="error"><form:errors path="name" cssclass="error"></form:errors></p></td>
                    <td><spring:message code="name.input"/></td>
                    <td><form:input id="name" path="name"/></td>

                </tr>
                <tr>
                    <td><p class="error"><form:errors path="description" cssclass="error"></form:errors></p></td>
                    <td><spring:message code="description"/></td>
                    <td><form:input class="description" path="description"/></td>
                </tr>
                <tr>
                    <td><p class="error"><form:errors path="country" cssClass="error"/></p></td>
                    <td><spring:message code="country" text="Country"/></td>
                    <td><form:select class="brand" path="country" items="${countryList}"
                                     itemValue="id"
                                     itemLabel="name"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    </td>
                    <td class="submit">
                        <input id="submit" type="submit" value="<spring:message code="save"/>"/></td>
                </tr>
                <form:input type="hidden" cssClass="hidden_id_input" path="id"/>
            </table>
        </form:form>
    </div>
</c:if>

<c:if test="${not empty brandList}">

    <div id="property_div_table">
        <table class="property_table">
            <c:forEach items="${brandList}" var="brand">
                <tr>
                    <td><p>${brand.name}</p></td>
                    <td><a href="<c:url value="/delete/property/brand/${brand.id}" />"><spring:message
                            code="delete"/></a></td>
                    <td><a href="<c:url value="/edit/property/brand/${brand.id}" />"><spring:message code="edit"/></a>
                    </td>
                </tr>
            </c:forEach>
            <td colspan="3"><a href="<c:url value="/add/property/brand"/>"><spring:message code="add"/></a></td>
        </table>
    </div>
</c:if>
