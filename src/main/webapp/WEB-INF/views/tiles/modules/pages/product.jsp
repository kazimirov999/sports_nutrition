<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 29.01.2016
  Time: 18:33
  To change this template use File | Settings | File Templates.
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
<spring:message code="select.taste.label" var="tasteLabel"/>
<spring:message code="edit" var="edit"/>
<spring:message code="delete" var="delete"/>
<spring:message code="product.notExistInStorage" var="unavaible"/>
<spring:message code="product.isExistInStorage" var="avaible"/>
<spring:message code="freeDrivePrice" var="freeDrivePrice"/>
<spring:message code="free.drive.label" var="${freeDrive}" />
<spring:message code="${product.form}.form" var="formName"/>
<spring:message code="${product.gender}.gender" var="genderName"/>

<c:if test="${product != null}">
    <div class="productShowContentBox">
        <div class="productImageBox">
            <c:if test="${product.discount != null && product.discount.size != '0.00'}">
                <div class="discountLabel">
                    <span class="discLabel">- ${product.discount.getAroundSize()}%</span>
                </div>
            </c:if>
            <img src="/product/photo/${product.id}" alt="${product.name}" width="280" height="360"/>
        </div>
        <div class="productMainContentBox">
            <div class="productNameBox">
                    ${product.name.toUpperCase()}<br>
                <span class="brandName"> ОТ ${product.brand.name.toUpperCase()}</span>
            </div>
            <div class="bonusBox">
                <c:if test="${product.getRealPrice() > freeDrivePrice}">
                    <div class="wrraperDriveImg" title="${freeDrive}">
                        <div class="driveImg"></div>
                        ${freeDrive}
                    </div>
                </c:if>
            </div>
            <div class="productAdminBox">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a href="<c:url value="/edit/product/${product.id}" />">${edit}</a> |
                    <a href="<c:url value="/delete/product/${product.id}" />">${delete}</a>
                </sec:authorize>
            </div>
            <div class="wrapperMainBox">
                <div class="productBuyBox">
                    <form:form method="GET" action="/buy" commandName="formBuyBean">

                        <div class="productTasteBox"><span class="tasteSpan">${tasteLabel}</span>
                            <form:select class="taste" path="taste" items="${product.tasteList}"
                                         itemValue="id"
                                         itemLabel="name"/>
                            <form:input type="hidden" path="product" value="${product.id}"/>
                        </div>

                        <input class="buttonBuy" type="submit" value="${buy}"/>
                    </form:form>
                </div>
                <div class="productPriceBox">
                    <div class="realPrice">${product.getRealPrice()}
                        <span class="currency"> ${currency}.</span>
                    </div>
                    <c:if test="${product.discount != null && product.discount.size != '0.00'}">
                        <div class="priceWithoutDiscountWrapper">
                            <span class="priceWithoutDiscount">${product.price}</span>
                            <span class="currency"> ${currency}.</span>
                        </div>
                    </c:if>
                </div>
                <div class="avaibleProductBox">
                    <c:choose>
                        <c:when test="${product.stockAmount > '0'}">
                            <div class="avaible">есть в наличии</div>
                        </c:when>
                        <c:otherwise>
                            <div class="unavaible">нет в наличии</div>
                        </c:otherwise>
                    </c:choose>

                </div>

                <div class="wrapperShotDescComposition">
                    <div class="productShortDesc">${product.description}</div>
                    <div class="productPropertiesBox">
                        <table>
                            <tr>
                                <td> Производитель:</td>
                                <td>${product.brand.name}</td>
                            </tr>
                            <tr>
                                <td> Артикул:</td>
                                <td>${product.articleNumber}</td>
                            <tr>
                                <td> Категория:</td>
                                <td> ${product.category.name}</td>
                            <tr>
                                <td> Фасовка:</td>
                                <td>${product.quantityInPackage}</td>
                            <tr>
                                <td> Форма выпуска:</td>
                                <td> ${formName}</td>
                            <tr>
                                <td> Пол:</td>
                                <td> ${genderName}</td>
                        </table>
                    </div>

                </div>
            </div>
            <div class="fullDescription">
                    ${product.fullDescription}
            </div>

        </div>
    </div>


</c:if>
