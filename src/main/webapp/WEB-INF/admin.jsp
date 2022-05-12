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

        <%--        <h1 class="center-Text">${requestScope.insufficient_funds}</h1>--%>

        <h3>Her kan du se alle ordere: </h3>

        <div style="padding:0px 0px 5px 5px">
            <label for="myInput">Søg: </label>
            <input type="text" id="myInput" onkeyup="searchByUsername()" placeholder="Søg efter kundenavn...">
        </div>
        <form method="post">
            <div class="table-responsive-xl">
                <table class="table-responsive-sm table" id="myTable">
                    <thead>
                    <tr>
                        <td>
                        <%-- til radio button --%>
                        </td>
                        <td>OrderId</td>
                        <td>Kunde navn</td>
                            <%--//TODO: Join med user tabel for at hente navn--%>
                        <td>ForespørgselsId</td>
                        <td>Indkøbs pris</td>
                        <td>Fortjeneste pris</td>
                        <td>Status</td>
                        <td>Bestillings tidspunkt</td>
                        <td>
                            <%-- til buttons --%>
                        </td>
                    </tr>
                    </thead>
                    <c:forEach var="userOrdersDTO" items="${requestScope.userOrdersDTOList}">
                        <tbody>
                        <tr>
                            <td>
                                <input class="form-check-input" type="radio" name="orderSelect" id="orderSelect${userOrdersDTO.order_id}"
                                       value="${userOrdersDTO.order_id}">
                            </td>
                            <td>${userOrdersDTO.order_id}</td>
                            <td>${userOrdersDTO.username}</td>
                            <td>${userOrdersDTO.inquiry_id}</td>
                            <td>${userOrdersDTO.cost_price} kr</td>
                            <td>${userOrdersDTO.final_price} kr</td>
                            <td>${userOrdersDTO.status_id}...</td>
                            <td>${userOrdersDTO.timestamp}</td>
                            <td>
                                <button>Se tegning</button>
                                <button>Se Stykliste</button>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
                    <%--//TODO: type="submit" send til næste side med valgt row data!--%>
                <button>Rediger</button>
                    <%--Send til en ny side hvor der redigeres--%>
                <button>Slet</button>
                <button>Godkend</button>
            </div>
        </form>

    </jsp:body>
</t:pagetemplate>