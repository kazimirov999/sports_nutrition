<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<form:form method="post" action="/add/category" commandName="formCategoryBean" enctype="multipart/form-data">

  <table>
    <tr>
      <td><p class="error"><form:errors path="category.name" cssclass="error"></form:errors></p>
        <spring:message code="name.input"/><br>
        <form:input id="name" path="category.name"/></td>
    </tr>
    <tr>
      <td><p class="error"><form:errors  path="category.description" cssclass="error"></form:errors></p>
        <spring:message code="category.description.input"/><br>
        <form:textarea id="description" path="category.description"/></td>
    </tr>
    <tr>
      <td><p class="error"><form:errors path="file" cssclass="error"></form:errors></p>
        <input id="file" type="file" path="file" name="file"
               value="<spring:message code="image"/>" id="file"/></td>
    </tr>
    <tr>
      <td class="submit">
        <input id="submit" type="submit" value="<spring:message code="add"/>"/>
      </td>
    </tr>
  </table>
</form:form>
