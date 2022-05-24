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
            <div class="carportDesigner">
                <p class="carportTitle">Carport Designer</p>
                <h1 style="color: red; text-align: center">${requestScope.shed40}</h1>
                <br><br>
                <form action="fc/sendInquiry" method="post">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm">
                                <div class="dropdown">
                                    <h1 bold style="font-size: x-large">Carport Bredde</h1>
                                    <select required="required" name="carpWidth" class="form-select" aria-label="Default select example">
                                        <option value="">Vælg bredde</option>
                                        <c:forEach var="i" begin="240" end="600" step="30">
                                            <option value="<c:out value="${i * 10}"></c:out>"><c:out value="${i}"></c:out> cm
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm">
                                <div class="dropdown">
                                    <h1 bold style="font-size: x-large">Carport Længde</h1>
                                    <select required="required" name="carpLength" class="form-select " aria-label="Default select example">
                                        <option value="" selected>Vælg længde</option>
                                        <c:forEach var="i" begin="240" end="780" step="30">
                                            <option value="<c:out value="${i * 10}"></c:out>"><c:out value="${i}"></c:out> cm
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br><br>
                    <div class="radioButtons">
                        <p class="carportTitle carportSubTitle">Valg af tag</p>
                        <div class="form-check form-check-inline carportTagVenstre">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2"
                                   value="option2"
                                   onchange="disableFunc2()">
                            <label class="form-check-label radioText" for="inlineRadio2">Fladt</label>
                        </div>
                        <div class="form-check form-check-inline carportTagHøjre">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1"
                                   value="option1"
                                   checked="checked" onchange="disableFunc()">
                            <label class="form-check-label radioText" for="inlineRadio1">Med rejsning</label>
                        </div>

                    </div>
                    <br><br>
                    <c:if test="inLinedRadio.equals"></c:if>
                    <div class="container">
                        <div class="row">
                            <div class="col-sm">
                                <div class="dropdown">
                                    <h1 bold style="font-size: x-large">fladt type</h1>
                                    <select required="required" name="roofType" class="form-select " id="number1"
                                            aria-label="Default select example" disabled="true">
                                        <option selected value="">Her kan du vælge Fladt Tag type</option>
                                        <c:forEach var="type" items="${applicationScope.flatRoofMaterialsList}">
                                            <option value="${type.type}">${type.type}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm">
                                <div class="dropdown">
                                    <h1 bold style="font-size: x-large">med rejsning type</h1>
                                    <select required="required" name="roofType" class="form-select " id="number2"
                                            aria-label="Default select example">
                                        <option value="">Her kan du vælge Rejst Tag type</option>
                                        <c:forEach var="raisedtype" items="${applicationScope.raisedRoofMaterialsList}">
                                            <option value="${raisedtype.type}">${raisedtype.type}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm">
                                <div class="dropdown">
                                    <h1 bold style="font-size: x-large">Taghældning </h1>
                                    <select required="required" name="roofSloop" class="form-select " id="number3"
                                            aria-label="Default select example">
                                        <option selected value="">Vælg hældning på taget</option>
                                        <c:forEach var="i" begin="15" end="45" step="5">
                                            <option value="<c:out value="${i}"></c:out>"><c:out value="${i}"></c:out> Grader
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <br><br><br><br><br>
                            <p class="carportTitle carportSubTitle skurTitle">Tilvalg </p>
                            <div class="form-check checkBox">
                                <input class="form-check-input" name="checkboxShed" type="checkbox"  id="flexCheckDefault"
                                       onchange="visSkur()">
                                <label class="form-check-label radioText" for="flexCheckDefault">
                                    skur
                                </label>
                            </div>
                            <br><br>
                            <div class="container">
                                <div id="skur" style="display: none">
                                    <div class="row">
                                        <div class="col-sm">
                                            <div class="dropdown">
                                                <h1 bold style="font-size: x-large">Skur Bredde</h1>
                                                <select required="required" id="shedWidth" name="shedWidth" class="form-select "
                                                        aria-label="Default select example" disabled="true">
                                                    <option value="" selected>Vælg Skur Bredde</option>
                                                    <option value="1">Fuld Bredde</option>
                                                    <option value="2">Halv Bredde</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-sm">
                                            <div class="dropdown">
                                                <h1 bold style="font-size: x-large">Skur Længde</h1>
                                                <select required="required" id="shedLength" name="shedLength" class="form-select "
                                                        aria-label="Default select example" disabled="true">
                                                    <option value="" selected>Vælg Skur Længde</option>
                                                    <c:forEach var="i" begin="150" end="690" step="30">
                                                        <option value="<c:out value="${i * 10}"></c:out>"><c:out
                                                                value="${i}"></c:out>
                                                            cm
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
                        <div class="container">
                            <input type="hidden" name="command" value="sendInquiry"/>
                            <button type="submit" class="btn btn-layer3 designButton" formaction="fc/sendInquiry" formmethod="post"
                                    value="submit">Send carport
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <meta http-equiv = "refresh" content = "0; url = ${pageContext.request.contextPath}/createUser.jsp" />
        </c:if>

    </jsp:body>
</t:pagetemplate>