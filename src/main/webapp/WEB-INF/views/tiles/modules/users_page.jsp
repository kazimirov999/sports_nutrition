<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 18.02.2016
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${not empty serviceMessage}">
    <div id="service_message">
        <p><spring:message code="${serviceMessage}"/></p>
    </div>
</c:if>

<c:if test="${not empty userList}">
    <div class="usersBox">
        <div class="usersTable">
            <table>
                <tr>
                    <td>Имя</td>
                    <td>Фамилия</td>
                    <td>Email</td>
                    <td>Номер телефона</td>
                    <td>Адрес</td>
                    <td>Активирован</td>
                    <td>Role</td>
                    <td colspan="3"></td>
                </tr>
                <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.loginEmail}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.address}</td>
                    <td>${user.enabled}</td>
                    <td>${user.role}</td>
                    <c:choose>
                        <c:when test="${user.enabled == 'true'}">
                            <td><a href="<c:url value='/deactivate/user/${user.id}' />">Деактивировать</a></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="<c:url value='/activate/user/${user.id}' />">Активировать</a></td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <c:if test="${user.role != 'ROLE_ADMIN'}">
                            <a href="<c:url value='/do_admin/user/${user.id}' />">Сделать админом</a>
                        </c:if>
                    </td>
                    <td><a href="<c:url value='/delete/user/${user.id}' />">Удалить</a></td>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </div>
</c:if>
