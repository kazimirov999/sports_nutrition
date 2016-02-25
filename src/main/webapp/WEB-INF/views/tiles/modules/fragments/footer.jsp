<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 17.01.2016
  Time: 19:45
  To change this template use File | Settings | File Templates.
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
