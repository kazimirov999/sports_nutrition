<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<spring:message code="order.amount" var="orderAmount"/>
<spring:message code="order.id" var="orderId"/>
<spring:message code="order.data" var="orderDate"/>
<spring:message code="customer" var="customer"/>
<spring:message code="phone" var="phone"/>
<spring:message code="order.send" var="orderButton"/>
<spring:message code="user.email" var="email"/>
<spring:message code="customer.address" var="address"/>
<spring:message code="customer.address" var="address"/>
<spring:message code="customer.user" var="customerIsUser"/>
<spring:message code="yes" var="yes"/>
<spring:message code="no" var="no"/>
<spring:message code="taste" var="taste"/>
<spring:message code="price" var="price"/>
<spring:message code="discount" var="discount"/>
<spring:message code="quantity" var="quantity"/>
<spring:message code="sum" var="sum"/>
<spring:message code="order.delete" var="deleteOrderButton"/>
<spring:message code="order.send" var="orderButton"/>

<c:if test="${holderCarts.pageList.size() == '0'}">
    <div id="service_message">
        <p><spring:message code="cart.empty"/></p>
    </div>
</c:if>

<c:if test="${not empty serviceMessage}">
    <div id="service_message">
        <p><spring:message code="${serviceMessage}"/></p>
    </div>
</c:if>

<c:if test="${holderCarts.pageList.size() != '0'}">

    <div class="orders">

        <p><span class="bold">${orderAmount}:</span> ${totalProducts}<br>
            <span class="bold">${sum}:</span> ${totalPrice}</p>

        <c:forEach items="${holderCarts.pageList}" var="cart">

            <ul>
                <li><span class="bold">${orderId}</span>${cart.orderId}</li>
                <li><span class="bold">${sum}:</span>${cart.grandTotalPrice}</li>
                <li><span class="bold">${orderDate}</span> ${cart.date.toString("dd-MM-yyyy HH : mm")}</li>
                <li><span class="bold">${customer}:</span> ${cart.customer.firstName} ${cart.customer.lastName}</li>
                <li><span class="bold">${phone}:</span> ${cart.customer.phoneNumber}</li>
                <li><span class="bold">${email}:</span> ${cart.customer.email}</li>
                <li><span class="bold">${address}:</span>${cart.customer.country.name}, ${cart.customer.address}</li>
                <li>
                    <span class="bold">${customerIsUser}</span>
                    <c:if test="${cart.customer.isUser == 'false'}"> ${no}</c:if>
                    <c:if test="${cart.customer.isUser == 'true'}"> ${yes}</c:if>
                </li>
            </ul>

            <div class="orderTable">
                <table>
                    <tr>
                        <td></td>
                        <td>${taste}:</td>
                        <td>${price}:</td>
                        <td>${discount}:</td>
                        <td>${quantity}:</td>
                        <td>${sum}:</td>
                    </tr>
                    <c:forEach var="item" items="${cart.cartItems}">
                        <tr>
                            <td>${item.product.name}</td>
                            <td>${item.taste.name}</td>
                            <td>${item.product.price}</td>
                            <td>${item.product.discount.size} %</td>
                            <td>${item.quantity}</td>
                            <td>${item.totalPrice}</td>
                        </tr>
                    </c:forEach>

                    <tr>
                        <td colspan="6" class="center">
                            <a class="bold"
                               href="<c:url value="/place_delete/order/${cart.id}"/>">${deleteOrderButton}</a>/
                            <a class="bold" href="<c:url value="/place_send/order/${cart.id}"/>">${orderButton}</a>
                        </td>
                    </tr>
                </table>
            </div>
        </c:forEach>
    </div>
    <c:if test="${pager.totalPageCount > 1}">
        <jsp:include page="../fragments/paginator.jsp"/>
    </c:if>
</c:if>
