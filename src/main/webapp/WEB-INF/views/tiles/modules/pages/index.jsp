<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:message code="index.page.title" var="title"/>

<%--<h3 class="indexTitle">${title}</h3>--%>

<div class="categoryContainer">
    <c:forEach items="${categories}" var="category">
        <div class="categoryBox">
            <div class="imageBox">
                <a href="<c:url value="/products/${category.id}" />">
                    <img src="/category/photo/${category.id}" alt="${category.name}" width="150" height="180"/>
                </a>
            </div>
            <div class="categoryNameBox">
                <a href="<c:url value="/products/${category.id}" />">${category.name}</a>
            </div>
        </div>
    </c:forEach>
</div>

<div class="slider_wrapper">
    <div class="slider-box">
        <div class="slider">
            <c:forEach begin="1" end="7" var="item">
                <img src="/resources/styles/images/slider/photo${item}.jpg" alt=""/>
            </c:forEach>
        </div>
    </div>
</div>