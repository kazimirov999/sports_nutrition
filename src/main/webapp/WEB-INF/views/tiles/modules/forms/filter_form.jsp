<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="text-align" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="name.form.main.filter" var="filterBar"/>
<spring:message code="price.product" var="priceTitle"/>
<spring:message code="gender" var="genderTitle"/>
<spring:message code="form.product" var="formProductTitle"/>
<spring:message code="brand" var="brandTitle"/>
<spring:message code="taste" var="tasteTitle"/>
<spring:message code="discount" var="discountTitle"/>
<spring:message code="search.button" var="buttonSearch"/>

<div class="filterFormDiv">

    <form:form commandName="formFilterBean" method="POST" action="">

        <div class="form-title"><h2>${filterBar}</h2></div>
        <div class="price">
            <p class="priceTitle">${priceTitle}</p>
            <form:errors path="lowPrice" class="error"></form:errors>
            <form:errors path="highPrice" class="error"></form:errors>
            <input type="text" name="lowPrice" value="${formFilterBean.lowPrice}"/>
            <span> - </span>
            <input type="text" name="highPrice" value="${formFilterBean.highPrice}"/>

            <input class="okButton" type="submit" value="ok"/>
        </div>

        <c:if test="${not empty formPropertyContent.genderMap}">
            <ul><p class="ulTitle">${genderTitle}<p>
                <c:forEach var="entry" items="${formPropertyContent.genderMap}">
                <li>
                    <spring:message code="${entry.key}.gender" var="genderName"/>
                    <c:set var="count" value=""/>
                    <c:if test="${(empty formFilterBean.genderList) && entry.value != '0'}">
                        <c:set var="count" value="(${entry.value})"/>
                    </c:if>
                    <form:checkbox class="checkBox" path="genderList" value="${entry.key}"
                                   label="${genderName} ${count}"/>
                </li>
                </c:forEach>
            </ul>
        </c:if>

        <c:if test="${not empty formPropertyContent.formMap}">
            <ul><p class="ulTitle">${formProductTitle}</p>
                <c:forEach var="entry" items="${formPropertyContent.formMap}">
                    <li>
                        <spring:message code="${entry.key}.form" var="formName"/>
                        <c:set var="count" value=""/>
                        <c:if test="${(empty formFilterBean.formList) && entry.value != '0'}">
                            <c:set var="count" value="(${entry.value})"/>
                        </c:if>
                        <form:checkbox class="checkBox" path="formList" value="${entry.key}"
                                       label="${formName} ${count}"/>
                    </li>
                </c:forEach>
            </ul>
        </c:if>

        <c:if test="${not empty formPropertyContent.brandMap}">
            <ul><p class="ulTitle">${brandTitle}</p>
                <c:forEach var="entry" items="${formPropertyContent.brandMap}">
                    <li>
                        <c:set var="count" value=""/>
                        <c:if test="${(empty formFilterBean.brandIdList) && entry.value != '0'}">
                            <c:set var="count" value="(${entry.value})"/>
                        </c:if>
                        <form:checkbox class="checkBox" path="brandIdList" value="${entry.key.id}"
                                       label="${entry.key.name} ${count}"/>
                    </li>
                </c:forEach>
            </ul>
        </c:if>

        <c:if test="${not empty formPropertyContent.tasteMap}">
            <ul><p class="ulTitle">${tasteTitle}</p>
                <c:forEach var="entry" items="${formPropertyContent.tasteMap}">
                    <li>
                        <c:set var="count" value=""/>
                        <c:if test="${(empty formFilterBean.tasteIdList) && entry.value != '0'}">
                            <c:set var="count" value="(${entry.value})"/>
                        </c:if>
                        <form:checkbox class="checkBox" path="TasteIdList" value="${entry.key.id}"
                                       label="${entry.key.name} ${count}"/>
                    </li>
                </c:forEach>
            </ul>
        </c:if>

        <c:if test="${not empty formPropertyContent.discountMap}">
            <ul><p class="ulTitle">${discountTitle}</p>
                <c:forEach var="entry" items="${formPropertyContent.discountMap}">
                    <li>
                        <c:set var="count" value=""/>
                        <c:if test="${(empty formFilterBean.discountIdList) && entry.value != '0'}">
                            <c:set var="count" value="(${entry.value})"/>
                        </c:if>
                        <form:checkbox class="checkBox" path="discountIdList" value="${entry.key.id}"
                                       label="${entry.key.getAroundSize()}% ${count}"/>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <div class="submit">
            <input type="submit" value="${buttonSearch}"/>
        </div>
    </form:form>
</div>