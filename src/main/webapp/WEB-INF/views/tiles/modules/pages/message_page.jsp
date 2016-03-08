<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<c:if test="${not empty serviceMessage}">
  <div id="service_message">
    <p><spring:message code="${serviceMessage}"/></p>
  </div>
</c:if>