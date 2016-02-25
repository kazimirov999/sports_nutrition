<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 08.02.2016
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<spring:message code="currency" var="currency"/>
<spring:message code="total.products" var="productsAmount"/>
<spring:message code="total.price" var="totalPrice"/>
<spring:message code="taste" var="taste"/>
<spring:message code="discount" var="discount"/>
<spring:message code="price" var="price"/>
<spring:message code="quantity" var="quantity"/>
<spring:message code="cart.clean.cart" var="cleanCart"/>
<spring:message code="cart.place" var="cartPlace"/>
<spring:message code="sum" var="sum"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="generalTableStyle cartTable">
    <table>
        <tr>
            <td colspan="3">Корзина покупок</td>
            <td colspan="3"> ${productsAmount} ${cart.size()}</td>
            <td colspan="3">${totalPrice} ${cart.grandTotalPrice} ${currency}</td>
        </tr>
        <tr>
            <td colspan="3"></td>
            <td>${taste}</td>
            <td>${price} (${currency})</td>
            <td>${discount}</td>
            <td class="quantity">${quantity}</td>
            <td>${sum}: (${currency})</td>
            <td></td>
        </tr>
        <c:forEach var="item" items="${cart.cartItems}">
            <tr>
                <td>
                    <div class="img">
                        <a href="<c:url value="/show/product/${item.product.id}" />">
                        <img src="/product/photo/${item.product.id}" alt="${item.product.name}" width="80" height="105"/></a>
                    </div>
                </td>
                <td colspan="2">
                    <div class="nameWrapper"> ${item.product.name}</div>
                </td>
                <td>${item.taste.name}</td>
                <td>${item.product.price}</td>
                <td>${item.product.discount.size} %</td>
                <td>
                    <form:form method="GET" action="/manage/cart" commandName="formBuyBean">
                    <form:input type="hidden" path="product" value="${item.product.id}"/>
                    <form:input type="hidden" path="taste" value="${item.taste.id}"/>
                    <input class="resize" type="submit" name="decrease" value="-"/> < ${item.quantity} >
                    <input class="resize" type="submit" name="increase" value="+"/>
                </td>
                <td>${item.totalPrice}</td>
                <td>
                    <input class="deleteButton" type="submit" name="delete" value="X"/>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="9">
                <a href="<c:url value="/clean/cart"/>">${cleanCart}</a> /
                <a href="<c:url value="/order"/>">${cartPlace}</a>
        </tr>
    </table>
</div>
