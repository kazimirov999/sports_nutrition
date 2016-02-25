<!--suppress XmlDuplicatedId -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 28.01.2016
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<form:form method="post" action="/edit/category" commandName="formCategoryBean" enctype="multipart/form-data">

    <table>
        <tr>
            <td><p class="error"><form:errors path="category.name" cssclass="error"></form:errors></p>
                <spring:message code="name.input"/><br>
                <form:input id="name" path="category.name"/></td>
        </tr>
        <tr>
            <td><p class="error"><form:errors path="category.description" cssclass="error"></form:errors></p>
                <spring:message code="category.description.input"/><br>
                <form:textarea id="description" path="category.description"/></td>
        </tr>
        <tr>
            <td><p class="error"><form:errors path="file" cssclass="error"></form:errors></p>
                <input id="file" type="file" name="file"
                       value="<spring:message code="image"/>" id="file"/></td>
        </tr>
        <tr>
            <td class="submit">
                <input id="submit" type="submit" value="<spring:message code="save"/>"/>
            </td>

            <%--#hidden input for save dettach state of category entities--%>
            <form:input type="hidden" cssClass="hidden_id_input" path="category.id" value="${formCategoryBean.category.id}"/>


        </tr>
    </table>
</form:form>
