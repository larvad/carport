<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Order oversigt
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <%--        <h1 class="center-Text">${requestScope.insufficient_funds}</h1>--%>

        <h3>Her kan du se alle ordere: </h3>
        <br>

<%--        <div style="padding:0px 0px 5px 5px">--%>
<%--            <label for="myInput">Søg: </label>--%>
<%--            <input type="text" id="myInput" onkeyup="searchByUsername()" placeholder="Søg efter kundenavn...">--%>
<%--        </div>--%>
        <form method="post">
            <div class="table-responsive-xl">
                <table class="table-responsive-sm table" id="myTable">
                    <thead>
                    <tr>
                        <th>
                                <%-- til radio button --%>
                        </th>
                        <th>OrderId</th>
                        <th>Kunde navn</th>
                        <th>Tlf nummer</th>
                        <th>ForespørgselsId</th>
                        <th>Indkøbs pris</th>
                        <th>Fortjeneste pris</th>
                        <th>Status</th>
                        <th>Bestillings tidspunkt</th>
                        <th>
                                <%-- til buttons --%>
                        </th>
                    </tr>
                    </thead>
                    <c:forEach var="userOrdersDTO" items="${requestScope.userOrdersDTOList}">
                        <tbody>
                        <tr>
                            <td>
                                <button  type="submit" name="edit" value="${userOrdersDTO.order_id}" formaction="fc/adminEdit?command=adminEdit" style="border: 0px;background-color: white;">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-wrench-adjustable" viewBox="0 0 16 16">
                                        <path d="M16 4.5a4.492 4.492 0 0 1-1.703 3.526L13 5l2.959-1.11c.027.2.041.403.041.61Z"/>
                                        <path d="M11.5 9c.653 0 1.273-.139 1.833-.39L12 5.5 11 3l3.826-1.53A4.5 4.5 0 0 0 7.29 6.092l-6.116 5.096a2.583 2.583 0 1 0 3.638 3.638L9.908 8.71A4.49 4.49 0 0 0 11.5 9Zm-1.292-4.361-.596.893.809-.27a.25.25 0 0 1 .287.377l-.596.893.809-.27.158.475-1.5.5a.25.25 0 0 1-.287-.376l.596-.893-.809.27a.25.25 0 0 1-.287-.377l.596-.893-.809.27-.158-.475 1.5-.5a.25.25 0 0 1 .287.376ZM3 14a1 1 0 1 1 0-2 1 1 0 0 1 0 2Z"/>
                                    </svg>
                                </button> <%--Send til en ny side hvor der redigeres--%>
                                <button type="submit"  name="approve" value="${userOrdersDTO.order_id}" formaction="fc/adminApprove?command=adminApprove" style="border: 0px;background-color: white;" <c:if test="${userOrdersDTO.status_id >= 2}">disabled</c:if>>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-file-earmark-check" viewBox="0 0 16 16">
                                        <path d="M10.854 7.854a.5.5 0 0 0-.708-.708L7.5 9.793 6.354 8.646a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z"></path>
                                        <path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5v2z"></path>
                                    </svg>
                                </button>
                                <button type="submit" name="delete" value="${userOrdersDTO.order_id}" formaction="fc/adminDelete?command=adminDelete" style="border: 0px;background-color: white;">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
                                        <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                                    </svg>
                                </button>
                            </td>
                            <td>${userOrdersDTO.order_id}</td>
                            <td>${userOrdersDTO.username}</td>
                            <td>${userOrdersDTO.phone_no}</td>
                            <td>${userOrdersDTO.inquiry_id}</td>
<%--                            <td>${userOrdersDTO.cost_price.toString().substring(0, userOrdersDTO.cost_price.toString().indexOf('.')+3).replace('.', ',')} kr</td>--%>
<%--                            <td>${userOrdersDTO.final_price.toString().substring(0, userOrdersDTO.final_price.toString().indexOf('.')+3).replace('.', ',')} kr</td>--%>
                            <td>${userOrdersDTO.cost_price} kr</td>
                            <td>${userOrdersDTO.final_price} kr</td>
                            <td>
                            <c:if test="${userOrdersDTO.status_id == 1}">Venter</c:if>
                            <c:if test="${userOrdersDTO.status_id == 2}">Færdig</c:if>
                            <c:if test="${userOrdersDTO.status_id == 3}">Betalt</c:if>
                            </td>
                            <td>${userOrdersDTO.timestamp}</td>
                            <td>
                                <button type="submit" name="drawing" value="${userOrdersDTO.order_id}" formaction="fc/showSVG?command=showSVG" style="border: 0px;background-color: white;">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-card-image" viewBox="0 0 16 16">
                                    <path d="M6.002 5.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
                                    <path d="M1.5 2A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13zm13 1a.5.5 0 0 1 .5.5v6l-3.775-1.947a.5.5 0 0 0-.577.093l-3.71 3.71-2.66-1.772a.5.5 0 0 0-.63.062L1.002 12v.54A.505.505 0 0 1 1 12.5v-9a.5.5 0 0 1 .5-.5h13z"/>
                                </svg>
                                </button>
                                <button name="BOM" formaction="fc/createBOM?command=createBOM" value="${userOrdersDTO.order_id}" style="border: 0px;background-color: white;">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-card-list" viewBox="0 0 16 16">
                                    <path d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
                                    <path d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8zm0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm-1-5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zM4 8a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm0 2.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0z"/>
                                </svg>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
                    <%--//TODO: type="submit" send til næste side med valgt row data!--%>
                <div>

                </div>
            </div>

        </form>

    </jsp:body>
</t:pagetemplate>