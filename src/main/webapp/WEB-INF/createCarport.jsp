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

        <div class="container">
            <div class="row">
                <div class="col-sm">
                    <div class="dropdown">
                        Træ
                        <select name="træLængde" class="form-select" aria-label="Default select example">
                            <option selected>Længde</option>
                            <option>value</option>
                            <option>value</option>
                            <option>value</option>
                        </select>
                        </select>
                    </div>
                </div>
                <div class="col-sm">
                    <div class="dropdown">
                        Træ
                        <select name="træHøjde" class="form-select " aria-label="Default select example">
                            <option selected>Højde</option>
                            <option>value</option>
                            <option>value</option>
                            <option>value</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm">
                    <div class="dropdown">
                        Tag - uden rejsning
                        <select name="tagUdenRejsning" class="form-select " aria-label="Default select example">
                            <option selected>Type</option>
                            <option>value</option>
                            <option>value</option>
                            <option>value</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm">
                    <div class="dropdown">
                        Tag - med Rejsning
                        <select name="tagMedRejsning" class="form-select " aria-label="Default select example">
                            <option selected>Type</option>
                            <option>value</option>
                            <option>value</option>
                            <option>value</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm">
                    <div class="dropdown">
                        Beklædning
                        <select name="beklædning" class="form-select " aria-label="Default select example">
                            <option selected>Træ sort</option>
                            <option>value</option>
                            <option>value</option>
                            <option>value</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm">
                    <br>
                    <button type="submit"  class="btn btn-light" value="submit"  >Forespørgsel</button>
                </div>
            </div>
        </div>

    </jsp:body>
</t:pagetemplate>