<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 08.02.2016
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="order">

    <form:form class="form-container order_form" method="post" action="/save/order" commandName="formCustomerBean">

        <div class="form-title"><h2><spring:message code="cart.place"/></h2></div>

        <div class="form-title">
            <spring:message code="first.name"/>
            <form:errors path="firstName" class="error"></form:errors>
        </div>
        <form:input class="form-field name" path="firstName"/>

        <div class="form-title">
            <spring:message code="last.name"/>
            <form:errors path="lastName" class="error"></form:errors>
        </div>
        <form:input class="form-field" path="lastName"/>

        <div class="form-title">
            <spring:message code="email"/>
            <form:errors path="email" class="error"></form:errors>
        </div>
        <form:input class="form-field" path="email"/>

        <div class="form-title">
            <spring:message code="phone.number"/>
            <form:errors path="phoneNumber" class="error"></form:errors>
        </div>
        <form:input class="form-field" path="phoneNumber"/>

        <div class="form-title">
            <spring:message code="country"/></div>
        <form:select class="form-field address" path="country" items="${countryList}"
                     itemValue="id"
                     itemLabel="name"/>

        <div class="form-title address">
            <spring:message code="address"/>
            <form:errors path="address" class="error"></form:errors>
        </div>
        <form:input class="form-field" path="address"/>

        <div class="submit-container submit">
            <input class="submit-button" type="submit" value="<spring:message code="send"/>"/>
        </div>
    </form:form>
</div>
