<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             <h5>Tak for din skræddesyede carport forespørgsel hos FOG</h5>
        <h5>En af vores medarbejdere vil kontakte Dig snart</h5><br>
        <h5>Her kan du se carportens detaljer</h5>

    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div class="card confirm" style="width: 20rem;">
            <img src="${pageContext.request.contextPath}/images/logo.svg" width="100px;" class="rounded mx-auto d-block"
                 class="img-fluid" class="card-img-top" alt="...">
            <div class="card-body">
                <h6 class="card-title">Skræddesy din egen carport </h6>
                <p class="card-text"></p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Forespørgsel nr: ${requestScope.inquiry.inquiryId}</li>
                <li class="list-group-item">Carport: ${(requestScope.inquiry.carpWidth)/1000}m
                    X ${(requestScope.inquiry.carpLength)/1000}m
                </li>
                <li class="list-group-item">Tagtype: ${requestScope.inquiry.roofType}</li>
                <c:if test="${sessionScope.inquiry.shedWidth !=0}">
                    <li class="list-group-item">Redskabsrum: ${(requestScope.inquiry.shedWidth)/1000}m
                        X ${(requestScope.inquiry.shedLength)/1000}m
                    </li>
                </c:if>
                <c:if test="${sessionScope.inquiry.shedWidth == 0}">
                    <li class="list-group-item">Redskabsrum: ikke valgt</li>
                </c:if>
            </ul>
            <div class="card-footer" style="background-color: #003d768f">
                Johannes Fog A/S - Firskovvej 20 <br> 2800 Lyngby
            </div>
        </div>


    </jsp:body>
</t:pagetemplate>