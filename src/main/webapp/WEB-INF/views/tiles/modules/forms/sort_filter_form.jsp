<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 05.02.2016
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<spring:message code="product.isExistInStorage" var="productIsExist"/>
<spring:message code="sort.filter.name_asc" var="nameASC"/>
<spring:message code="sort.filter.name_desc" var="nameDESC"/>
<spring:message code="sort.filter.price_asc" var="priceASC"/>
<spring:message code="sort.filter.price_desc" var="priceDESC"/>
<spring:message code="sort.label" var="sortLabel"/>

<div id="sort_filter_form">
    <form:form modelAttribute="formSortedBean" method="GET" action="/sort/${categoryId}">

        <span class="sort">${sortLabel}</span><br>
        <form:select class="sort_select_filter" path="sortType">
            <form:option value="NAME_ASC">${nameASC}</form:option>
            <form:option value="NAME_DESC">${nameDESC}</form:option>
            <form:option value="PRICE_ASC">${priceASC}</form:option>
            <form:option value="PRICE_DESC">${priceDESC}</form:option>
        </form:select>

        <div class="submit">
            <input class="submit" type="submit" value="Ok"/>
        </div>

        <div class="checkBoxIsExistProduct">
            <form:checkbox class="checkBox" path="productAvailability" value="true" label="${productIsExist}"/>
        </div>
    </form:form>
</div>