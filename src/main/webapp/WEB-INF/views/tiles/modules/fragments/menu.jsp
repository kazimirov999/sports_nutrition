<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:message code="manage.users" var="showUser"/>
<spring:message code="delete.short" var="buttonDelete"/>
<spring:message code="edit.short" var="buttonEdit"/>
<spring:message code="menu" var="menu"/>
<spring:message code="category" var="category"/>
<spring:message code="welcom.user" var="welcomUser"/>
<spring:message code="logout.user" var="logoutUser"/>
<spring:message code="authentication.user" var="authUser"/>
<spring:message code="admin.bar" var="adminBar"/>
<spring:message code="add.category.name.url" var="addCategory"/>
<spring:message code="add.product.name.url" var="addProduct"/>
<spring:message code="property.name.url" var="propertyBar"/>
<spring:message code="orders" var="orders"/>
<spring:message code="index.page" var="IndexPage"/>

<c:if test="${not empty errorAuth || pageContext.request.userPrincipal.name != null}">
    <c:set var="active" value="active"/>
    <c:set var="visibleBlock" value="visibleBlock"/>
</c:if>


<div id='cssmenu'>
    <ul>
        <li class='active'><a href='<c:url value="/"/>'><span>${IndexPage}</span></a></li>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li class='has-sub active visibleBlock'><a href='#'><span>${adminBar}</span></a>
                <ul class="visibleBlock">
                    <li><a href="<c:url value="/all/orders/page/1" />">${orders}</a></li>
                    <li><a href="<c:url value="/add/category" />">${addCategory}</a></li>
                    <li><a href="<c:url value="/add/product" />">${addProduct}</a></li>
                    <li><a href="<c:url value="/manage/property/page" />">${propertyBar}</a></li>
                    <li><a href="<c:url value="/show/users" />">${showUser}</a></li>

                </ul>
            </li>
        </sec:authorize>

        <li class='has-sub active visibleBlock' style="display: block"><a href='#'><span>${category}</span></a>
            <ul class="visibleBlock">
                <c:if test="${not empty categories}">
                    <c:forEach items="${categories}" var="category">
                        <li>
                            <a href="<c:url value="/products/${category.id}" />">${category.name}</a>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <div class="buttonDivAdmin">
                                    <input class="adminButton" type=button
                                           onClick="location.href='/delete/category/${category.id}'"
                                           value='${buttonDelete}'>
                                    <input class="adminButton" type=button
                                           onClick="location.href='/edit/category/${category.id}'"
                                           value='${buttonEdit}'>
                                </div>
                            </sec:authorize>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </li>

        <li class='has-sub ${active}'>
            <a href='#'><span>${authUser}</span></a>
            <ul class="${visibleBlock}">
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <div class="littleAuthenticationDiv">
                        <jsp:include page="../forms/authentication_form.jsp"/>
                    </div>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <div class="welcomUserMenu">
                        <p>${welcomUser}</p>

                        <p>${pageContext.request.userPrincipal.name}</p>
                        <input class="adminButton" type=button
                               onClick="location.href='/logout'"
                               value='${logoutUser}'>
                    </div>
                </c:if>
            </ul>
        </li>
    </ul>
</div>

