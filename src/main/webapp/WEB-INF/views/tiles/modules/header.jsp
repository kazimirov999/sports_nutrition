<%@ page import="net.sports_nutrition.domain.entities.Cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="cartSize" value='<%=(((request.getSession().getAttribute("cart")) != null)?((Cart)request.getSession().getAttribute("cart")).size():0)%>'/>


<spring:message code="authentication.button.header" var="authUserButton"/>
<spring:message code="register.button" var="registerUserButton"/>
<spring:message code="welcom.user" var="welcomUser"/>
<spring:message code="logout.user" var="logoutUser"/>
<c:set var="busketImgUrl" value="/resources/styles/images/busket.png"/>
<c:if test="${cartSize == '0'}">
    <c:set var="busketImgUrl" value="/resources/styles/images/busket_empty.png"/>
</c:if>

<div class="busket">
    <a href="<c:url value="/cart" />"><img width="70" height="70" src="<c:url value="${busketImgUrl}" />" alt="Busket"/></a>
    <p>Товаров: ${cartSize}</p>
</div>


<div class="authBlockHeader">

    <c:choose>
        <c:when test="${pageContext.request.userPrincipal.name != null}">
            <p>${pageContext.request.userPrincipal.name} |
                <a href="<c:url value='/logout' />">${logoutUser}</a></p>
        </c:when>
        <c:otherwise>
          <p>
              <a href="<c:url value="/login" />">${authUserButton}</a> |
              <a href="<c:url value="/register" />">${registerUserButton}</a>
          </p>
        </c:otherwise>
    </c:choose>
    </div>
