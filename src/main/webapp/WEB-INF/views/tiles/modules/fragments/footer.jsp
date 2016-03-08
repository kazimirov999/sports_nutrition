<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<spring:message code="footer.copywrite" var="copy"/>
<spring:message code="footer.text" var="text"/>
<spring:message code="footer.siteName" var="siteName"/>

<div class="footerWrapper">
    <div class="footerContent">
        <span class="copy">${copy}</span>
        <span class="text">${text}</span>
        <span class="siteName">${siteName}</span>
    </div>
</div>
