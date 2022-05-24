<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>
        <c:if test="${sessionScope.user != null }">
            <br><br>
            <form action="fc/sendInquiry" method="post">
                <div class="container">
                    <div class="row">
                        <div class="col-sm">
                            <div class="dropdown">
                                <h1 bold style="font-size: x-large">Carport bredde</h1>
                                <select required="required" name="carpWidth" class="form-select"
                                        aria-label="Default select example">
                                    <option value="">Vælg bredde</option>
                                    <c:forEach var="i" begin="240" end="600" step="30">
                                        <option value="<c:out value="${i * 10}"></c:out>" <c:if
                                                test="${i == sessionScope.inquiry.carpWidth/10}"> selected="selected"</c:if>>
                                            <c:out value="${i}"></c:out> cm
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm">
                            <div class="dropdown">
                                <h1 bold style="font-size: x-large">Carport længde</h1>
                                <select required="required" name="carpLength" class="form-select "
                                        aria-label="Default select example">
                                    <option value="">Vælg længde</option>
                                    <c:forEach var="i" begin="240" end="780" step="30">
                                        <option value="<c:out value="${i * 10}"></c:out>" <c:if
                                                test="${i == sessionScope.inquiry.carpLength/10}"> selected="selected"</c:if>>
                                            <c:out value="${i}"></c:out> cm
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <br><br>
                <div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1"
                               value="option1"
                               <c:if test="${sessionScope.inquiry.roofSlope > 0}">checked="checked"</c:if>
                               onchange="disableFunc()">
                        <label class="form-check-label" for="inlineRadio1">Tag - Med rejsning</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2"
                               value="option2"
                               <c:if test="${sessionScope.inquiry.roofSlope == 0}">checked="checked"</c:if>
                               onchange="disableFunc2()">
                        <label class="form-check-label" for="inlineRadio2">Tag - uden Rejsning</label>
                    </div>
                </div>

                <br><br>
                <div class="container">
                    <div class="row">
                        <div class="col-sm">
                            <div class="dropdown">
                                <h1 bold style="font-size: x-large">Tag - uden Rejsning</h1>
                                <select required="required" name="roofType" class="form-select " id="number1"
                                        aria-label="Default select example"
                                        <c:if test="${sessionScope.inquiry.roofSlope != 0}">disabled="true"</c:if>>
                                    <option selected value="">Her kan du vælge Fladt Tag type</option>
                                    <c:forEach var="flattype" items="${applicationScope.flatRoofMaterialsList}">
                                        <option value="${flattype.type}" <c:if test="${flattype.type == sessionScope.inquiry.roofType}">selected="selected"</c:if>>
                                                ${flattype.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm">
                            <div class="dropdown">
                                <h1 bold style="font-size: x-large">Tag - med Rejsning</h1>
                                <select required="required" name="roofType" class="form-select " id="number2"
                                        aria-label="Default select example"
                                        <c:if test="${sessionScope.inquiry.roofSlope == 0}">disabled="true"</c:if>>
                                    <option value="">Her kan du vælge Rejst Tag type</option>
                                    <c:forEach var="raisedtype" items="${applicationScope.raisedRoofMaterialsList}">
                                        <option value="${raisedtype.type}" <c:if test="${raisedtype.type == sessionScope.inquiry.roofType }">selected="selected"</c:if>>${raisedtype.type}</option>
                                    </c:forEach>

                                </select>
                            </div>
                        </div>
                        <div class="col-sm">
                            <div class="dropdown">
                                <h1 bold style="font-size: x-large">Taghældning </h1>
                                <select required="required" name="roofSloop" class="form-select " id="number3"
                                        aria-label="Default select example"
                                        <c:if test="${sessionScope.inquiry.roofSlope == 0}">disabled="true"</c:if>>
                                    <option selected value="">Vælg hældning på taget</option>
                                    <c:forEach var="i" begin="15" end="45" step="5">
                                        <option value="<c:out value="${i}"></c:out>" <c:if test="${i == sessionScope.inquiry.roofSlope}">selected="selected"</c:if>><c:out value="${i}"></c:out> Grader
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br><br><br><br><br>
                        <div class="form-check">
                            <input class="form-check-input" name="checkboxShed" type="checkbox" id="flexCheckDefault"
                                   <c:if test="${sessionScope.inquiry.shedLength > 0 && sessionScope.inquiry.shedWidth > 0}">Checked</c:if>
                                   onchange="visSkur()">
                            <label class="form-check-label" for="flexCheckDefault">
                                Skur
                            </label>
                        </div>
                        <br><br>
                        <div class="container">
                            <div id="skur" style="display: <c:if test="${sessionScope.inquiry.shedLength == 0}">none</c:if> <c:if test="${sessionScope.inquiry.shedLength != 0}">block</c:if>;">
                                <div class="row">
                                    <div class="col-sm">
                                        <div class="dropdown">
                                            <h1 bold style="font-size: x-large">Skur Bredde</h1>
                                            <select required="required" id="shedWidth" name="shedWidth"
                                                    class="form-select "
                                                    aria-label="Default select example" <c:if test="${sessionScope.inquiry.shedWidth == 0}">disabled="true"</c:if>>
                                                <option value="" selected>Vælg Skur Bredde</option>
                                                <c:forEach var="i" begin="210" end="720" step="30">
                                                    <option value="<c:out value="${i * 10}"></c:out>" <c:if test="${i == sessionScope.inquiry.shedWidth/10}">selected="selected"</c:if>>
                                                        <c:out value="${i}"></c:out> cm
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm">
                                        <div class="dropdown">
                                            <h1 bold style="font-size: x-large">Skur Længde</h1>
                                            <select required="required" id="shedLength" name="shedLength"
                                                    class="form-select "
                                                    aria-label="Default select example" <c:if test="${sessionScope.inquiry.shedLength == 0}">disabled="true"</c:if>>
                                                <option value="" selected>Vælg Skur Længde</option>
                                                <c:forEach var="i" begin="150" end="690" step="30">
                                                    <option value="<c:out value="${i * 10}"></c:out>" <c:if test="${i == sessionScope.inquiry.shedLength/10}">selected="selected"</c:if>>
                                                        <c:out value="${i}"></c:out> cm
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br><br>
                    <div>
                        <label for="costPrice">Cost price:</label>
                        <input type="number" id="costPrice" name="costPrice" value="${sessionScope.order.costPrice}" disabled="true">
                        <label for="finalPrice">Final price:</label>
                        <input type="number" min="0" step=".01" id="finalPrice" name="finalPrice" value="${sessionScope.order.finalPrice}">
                    </div>
                    <br><br>
                    <div class="container">
                        <input type="hidden" name="command" value="adminEdit2"/>
                        <button type="submit" name="update" class="btn btn-primary" formaction="fc/adminEdit2" formmethod="post"
                                value="opdater">Opdater
                        </button>
                    </div>
                </div>
            </form>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <meta http-equiv="refresh" content="0; url = ${pageContext.request.contextPath}/createUser.jsp"/>
        </c:if>

    </jsp:body>
</t:pagetemplate>