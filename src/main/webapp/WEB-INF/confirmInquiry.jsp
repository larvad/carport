<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1 class="carportTitle">Tak for din bestilling!</h1>
        <div class="card confirm details" style="width: 20rem;">
            <img src="${pageContext.request.contextPath}/images/logo.svg" width="100px;" style="margin-top:15px;" class="rounded mx-auto d-block"
                 class="img-fluid" class="card-img-top" alt="...">
            <div class="card-body">
                <h6 class="card-title">Carport Detaljer </h6>
                <p class="card-text"></p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Forespørgsel nr: ${requestScope.inquiry.inquiryId}</li>
                <li class="list-group-item">Carport: ${(requestScope.inquiry.carpWidth)/1000}m
                    X ${(requestScope.inquiry.carpLength)/1000}m
                </li>
                <li class="list-group-item">Tagtype: ${requestScope.inquiry.roofType}</li>
                <c:if test="${requestScope.inquiry.shedWidth !=0}">
                    <li class="list-group-item">Redskabsrum: ${(requestScope.inquiry.shedWidth)/1000}m
                        X ${(requestScope.inquiry.shedLength)/1000}m
                    </li>
                </c:if>
                <c:if test="${requestScope.inquiry.shedWidth == 0}">
                    <li class="list-group-item">Redskabsrum: ikke valgt</li>
                </c:if>
            </ul>
            <div class="card-footer" style="background-color: #003d768f">
                Johannes Fog A/S - Firskovvej 20 <br> 2800 Lyngby
            </div>
        </div>


    </jsp:body>
</t:pagetemplate>