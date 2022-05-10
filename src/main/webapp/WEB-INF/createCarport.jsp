<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Skræddersy din egen carport
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>
        <br><br>
        <div class="container">
            <div class="row">
                <div class="col-sm">
                    <div class="dropdown">
                        <h1 bold style="font-size: x-large">Carport længde</h1>
                        <select name="carportLængde" class="form-select" aria-label="Default select example">
                            <option value="">Vælg carport længde</option>
                            <c:forEach var="i" begin="240" end="600" step="30">
                                <option value="<c:out value="${i * 10}"></c:out>"><c:out value="${i}"></c:out> cm</option>
                            </c:forEach>
                        </select>
                        </select>
                    </div>
                </div>
                <div class="col-sm">
                    <div class="dropdown">
                        <h1 bold style="font-size: x-large">Carport bredde</h1>
                        <select name="carportBredde" class="form-select " aria-label="Default select example">
                            <option selected>Vælg højde</option>
                            <c:forEach var="i" begin="240" end="780" step="30">
                                <option value="<c:out value="${i * 10}"></c:out>"><c:out value="${i}"></c:out> cm</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <br><br>
        <div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" checked="checked">
            <label class="form-check-label" for="inlineRadio1">Tag - Med rejsning</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">
            <label class="form-check-label" for="inlineRadio2">Tag - uden Rejsning</label>
        </div>
        </div>
        <br><br>
        <c:if test="inLinedRadio.equals"></c:if>
        <div class="container">
            <div class="row">
                <div class="col-sm">
                    <div class="dropdown">
                        <h1 bold style="font-size: x-large">Tag - uden Rejsning</h1>
                        <select name="tagUdenRejsning" class="form-select " aria-label="Default select example">
                            <option selected value="">Vælg fladt tag type </option>
                            <option value="1">Plasttrapezplader </option>
                            <option value="1">Stål</option>
                            <option value="1">Tagpap </option>
                        </select>
                    </div>
                </div>
                <div class="col-sm">
                    <div class="dropdown">
                        <h1 bold style="font-size: x-large">Tag - med Rejsning</h1>

                        <select name="tagMedRejsning" class="form-select " aria-label="Default select example">
                            <option selected>Vælg Tag Type</option>
                            <option value="">Betontagsten - Rød</option>
                            <option value="">Betontagsten - Teglrød</option>
                            <option value="">Betontagsten - Brun</option>
                            <option value="">Betontagsten - Sort</option>
                            <option value="">Eternittag B6 - Grå</option>
                            <option value="">Eternittag B6 - Sort</option>
                            <option value="">Eternittag B6 - Mokka(brun)</option>
                            <option value="">Eternittag B6 - Rødbrun</option>
                            <option value="">Eternittag B6 - Teglrød</option>
                            <option value="">Eternittag B7 - Grå</option>
                            <option value="">Eternittag B7 - Sort</option>
                            <option value="">Eternittag B7 - Mokka(brun)</option>
                            <option value="">Eternittag B7 - Rødbrun</option>
                            <option value="">Eternittag B7 - Teglrød</option>
                            <option value="">Eternittag B7 - Rødflammet</option>
                        </select>
                    </div>
                </div>
                <br><br><br><br><br>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">
                        Skur
                    </label>
                </div>
                <br><br>
                <div class="col-sm">
                    <div class="dropdown">
                        <h1 bold style="font-size: x-large">Skur Længde</h1>
                        <select name="skruLængde" class="form-select " aria-label="Default select example">
                            <option selected>Vælg Skur Længde</option>
                            <c:forEach var="i" begin="150" end="690" step="30">
                                <option value="<c:out value="${i * 10}"></c:out>"><c:out value="${i}"></c:out> cm</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-sm">
                    <div class="dropdown">
                        <h1 bold style="font-size: x-large">Skur Bredde</h1>
                        <select name="skurBredde" class="form-select " aria-label="Default select example">
                            <option selected>Vælg Skur Bredde</option>
                            <c:forEach var="i" begin="210" end="720" step="30">
                                <option value="<c:out value="${i * 10}"></c:out>"><c:out value="${i}"></c:out> cm</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <br><br>
            <div class="col-sm">
                <button type="submit" class="btn btn-primary" value="submit">Forespørgsel</button>
            </div>
        </div>

    </jsp:body>
</t:pagetemplate>