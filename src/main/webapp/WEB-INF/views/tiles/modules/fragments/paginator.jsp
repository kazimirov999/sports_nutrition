<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>

<div id="paginator">
    <div class="col-md-12 text-center">
        <div class="pagination pagination-centered">
            <ul class="pagination">
                <li>
                    <%--<li class="${pager.currentIndex == 1? 'disabled' : ''}">--%>
                    <c:if test="${pager.currentIndex == 1}"><span>First</span></c:if>
                    <c:if test="${pager.currentIndex != 1}">
                        <a href="<c:url value="${pager.baseUrl}1"/>">First</a>
                    </c:if>
                </li>
                <li>
                    <%--<li class="${pager.currentIndex != 1? '' : 'disabled'}">--%>
                    <c:if test="${pager.currentIndex == 1}"><span>«</span></c:if>
                    <c:if test="${pager.currentIndex != 1}">
                        <a title="Go to previous page"
                           href="<c:url value="${pager.baseUrl}${pager.currentIndex - 1}"/>">«</a>
                    </c:if>
                </li>

                <c:forEach var="item" begin="${pager.beginIndex}" end="${pager.endIndex}">
                <li class="${item == pager.currentIndex ? 'active' : ''}">
                    <c:if test="${pager.currentIndex == item}"><span>${item}</span></c:if>
                    <c:if test="${pager.currentIndex != item}">
                    <a href="<c:url value="${pager.baseUrl}${item}"/>">${item}</a>
                    </c:if>
                <li>
                    </c:forEach>

                <li>
                    <%--<li class="${pager.currentIndex != pager.totalPageCount? '' : 'disabled'}">--%>
                    <c:if test="${pager.currentIndex == pager.totalPageCount}"><span>»</span></c:if>
                    <c:if test="${pager.currentIndex != pager.totalPageCount}">
                        <a title="Go to next page"
                           href="<c:url value="${pager.baseUrl}${pager.currentIndex + 1}"/>">»</a>
                    </c:if>
                </li>
                <li>
                    <%--<li class="${pager.currentIndex == pager.totalPageCount? 'disabled' : ''}">--%>
                    <c:if test="${pager.currentIndex == pager.totalPageCount}"><span>Last</span></c:if>
                    <c:if test="${pager.currentIndex != pager.totalPageCount}">
                        <a href="<c:url value="${pager.baseUrl}${pager.totalPageCount}"/>">Last</a>
                    </c:if>
                </li>
            </ul>
        </div>
    </div>
</div>
