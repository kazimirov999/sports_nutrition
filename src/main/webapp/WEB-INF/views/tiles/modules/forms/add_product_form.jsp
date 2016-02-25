<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 28.01.2016
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<div class="product_form">
    <form:form method="post" action="/add/product" commandName="formProductBean" enctype="multipart/form-data">

        <table id="main_properties">
            <tr class="error_tr">
                <td colspan="2"><p class="error"><form:errors path="product.name" cssclass="error"></form:errors></p>
                </td>
                <td><p class="error"><form:errors path="product.stockAmount" cssclass="error"></form:errors></p></td>
            </tr>
            <tr>
                <td colspan="2">
                    <spring:message code="name.input"/><br>
                    <form:input id="name_input" path="product.name"/>
                </td>
                <td>
                    <spring:message code="product.stock_amount.input"/><br>
                    <form:input id="stock_amount_input" path="product.stockAmount"/>
                </td>
            </tr>
            <tr class="error_tr">
                <td><p class="error"><form:errors path="product.price" cssclass="error"></form:errors></p></td>
                <td><p class="error"><form:errors path="product.articleNumber" cssclass="error"></form:errors></p></td>
                <td><p class="error"><form:errors path="product.quantityInPackage" cssclass="error"></form:errors></p>
                </td>
            </tr>
            <tr>
                <td>
                    <spring:message code="product.price.input"/><br>
                    <form:input id="price_input" path="product.price"/>
                </td>
                <td>
                    <spring:message code="product.art#.input"/><br>
                    <form:input id="articleNumber_input" path="product.articleNumber"/>
                </td>
                <td>
                    <spring:message code="product.quantity_in_package.input"/><br>
                    <form:input id="product_in-package" path="product.quantityInPackage"/>
                </td>
        </table>

            <div id="composition">
                <p class="error"><form:errors path="product.composition" cssclass="error"></form:errors></p>
                <spring:message code="product.composition.input"/><br>
                <form:textarea class="composition_input" path="product.composition"/>
            </div>
            <div id="description">
                <p class="error"><form:errors path="product.description" cssclass="error"></form:errors></p>
                <spring:message code="product.description.input"/>
                <form:textarea class="description_input" path="product.description"/>
            </div>

        <div id="full_description">
            <p class="error"><form:errors path="product.fullDescription" cssclass="error"></form:errors></p>
            <spring:message code="product.full_description.input"/><br>
            <form:textarea class="full_description_input" path="product.fullDescription"/>
        </div>


        <table id="additional_properties">
            <tr>
                <td><spring:message code="product.category" text="Category"/></td>
                <td>
                    <form:select path="product.category" items="${categories}" itemValue="id"
                                 itemLabel="name"/></td>
                </td>
                <td><form:errors path="product.category" cssClass="error"/></td>
            </tr>
            <tr>
                <td><spring:message code="brand" text="Brand"/></td>
                <td>
                        <form:select class="product_brand" path="product.brand" items="${formPropertyContent.brandList}"
                                     itemValue="id" itemLabel="name"/>

                <td><form:errors path="product.brand" cssClass="error"/></td>
            </tr>
            <tr>
                <td><spring:message code="form.product" text="Form"/></td>
                <td>
                    <form:select  path="product.form">
                        <form:option value="" label="........."/>
                        <c:forEach var="formName" items="${formPropertyContent.form}">
                            <form:option value="${formName}"><spring:message code="${formName}.form"/></form:option>
                        </c:forEach>
                    </form:select></td>
                <td><form:errors path="product.form" cssClass="error"/></td>
            </tr>
            <tr>
                <td><spring:message code="gender" text="Gender"/></td>
                <td>
                    <form:select class="product_gender" path="product.gender">
                        <form:option value="" label="........."/>
                        <c:forEach var="genderName" items="${formPropertyContent.gender}">
                            <form:option value="${genderName}"><spring:message
                                    code="${genderName}.gender"/></form:option>
                        </c:forEach>
                    </form:select></td>
                <td><form:errors path="product.gender" cssClass="error"/></td>
            </tr>
            <tr>
                <td><spring:message code="discount" text="Discount"/></td>
                <td>
                    <form:select class="product_discount" path="product.discount"
                                 items="${formPropertyContent.discountList}"
                                 itemValue="id" itemLabel="size"/>
                </td>
                <td><form:errors path="product.discount" cssClass="error"/></td>
            </tr>
            <tr>
                <td><spring:message code="taste" text="Taste"/></td>
                <td>
                    <form:select class="product_taste" path="product.tasteList" items="${formPropertyContent.tasteList}"
                                 itemValue="id"
                                 itemLabel="name" multiple="true"/>
                </td>
                <td><form:errors path="product.tasteList" cssClass="error"/></td>
            </tr>
            <tr>
                <td><spring:message code="image" text="Image"/></td>
                <td>
                    <input class="file" type="file" path="file" name="file"/>
                </td>
                <td><p class="error"><form:errors path="file" cssclass="error"></form:errors></p></td>
            </tr>
        </table>


        <div id="submit">
            <p class="submit">
                <input class="submit" type="submit"
                       value="<spring:message code="add"/>"/></p>
        </div>
    </form:form>
</div>
