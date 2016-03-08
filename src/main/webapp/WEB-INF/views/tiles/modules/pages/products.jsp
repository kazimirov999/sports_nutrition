<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:message code="currency" var="currency"/>
<spring:message code="price" var="price"/>
<spring:message code="taste" var="taste"/>
<spring:message code="free.drive" var="freeDrive"/>
<spring:message code="buy" var="buy"/>
<spring:message code="edit" var="edit"/>
<spring:message code="delete" var="delete"/>
<spring:message code="products.amount" var="totalProducts"/>

<spring:message code="freeDrivePrice" var="freeDrivePrice"/>
<div class="topNavigationProduct">
    <div class="resultSelect">
        <span class="category">${categories.get(categoryId-1).getName().toUpperCase()}, </span>
        <span class="totalProduct">${totalProducts} <span class="amount">${products.getNrOfElements()}</span></span>
    </div>
    <jsp:include page="../forms/sort_filter_form.jsp"/>
</div>

<div class="productsContainer">
    <c:forEach items="${products.pageList}" var="product">

        <div class="productBox">
            <c:if test="${product.discount != null && product.discount.size != '0.00'}">
                <div class="discountLabel">
                    <span class="discLabel">- ${product.discount.getAroundSize()}%</span>
                </div>
            </c:if>
            <div class="imageBox">
                <a href="<c:url value="/show/product/${product.id}" />">
                    <img src="/product/photo/${product.id}" alt="${product.name}" width="194" height="250"/>
                </a>
            </div>

            <div class="manufactured">
                    ${product.brand.name}
            </div>

            <div class="productNameBox">
                <a href="<c:url value="/show/product/${product.id}" />">${product.name}</a>
            </div>

            <div class="priceBox">
                    ${price}: <span class="realPrice">${product.getRealPrice()}<span
                    class="currency"> ${currency}</span></span>
                <c:if test="${product.discount != null && product.discount.size != '0.00'}">
                    <div class="priceWithoutDiscountWrapper">
                        <span class="priceWithoutDiscount">${product.price}</span><span
                            class="currency"> ${currency}.</span>
                    </div>
                </c:if>
            </div>

            <div class="buyBox">
                <form:form method="GET" action="/buy" commandName="formBuyBean">

                    <div class="tasteBox">${taste}:
                        <form:select class="taste" path="taste" items="${product.tasteList}"
                                     itemValue="id"
                                     itemLabel="name"/>
                        <form:input type="hidden" path="product" value="${product.id}"/>
                    </div>
                    <div class="driveBox">
                        <input class="buttonBuy" type="submit" value="${buy}"/>
                        <c:if test="${product.getRealPrice() > freeDrivePrice}">
                            <div class="wrraperDriveImg" title="${freeDrive}">
                                <div class="driveImg"></div>
                            </div>
                        </c:if>
                    </div>
                </form:form>
            </div>

            <div class="productAdminBox">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a href="<c:url value="/edit/product/${product.id}" />">${edit}</a> |
                    <a href="<c:url value="/delete/product/${product.id}" />">${delete}</a>
                </sec:authorize>
            </div>
        </div>
    </c:forEach>
    <div class="clear"></div>
    <c:if test="${pager.totalPageCount > 1}">
        <jsp:include page="../fragments/paginator.jsp"/>
    </c:if>
</div>

