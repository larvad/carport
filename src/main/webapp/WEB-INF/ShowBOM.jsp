<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>


        <div class="stykliste">
            <h1>Stykliste</h1>
            <form action="">
                <h3>Træ og Tagplader</h3>
                <div class="table-responsive-xl">
                    <table class="table-responsive-sm table" id="myTable">
                        <thead>
                        <tr>

                            <th>Type</th>
                            <th>Længde</th>
                            <th>Antal</th>
                            <th>Enhed</th>
                            <th>Beskrivelse</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="BOMtagogtræ" items="${requestScope.BOMtagogtræ}">
                            <tr>
                                <td>${BOMtagogtræ.type}</td>
                                <td>${BOMtagogtræ.length} cm</td>
                                <td>${BOMtagogtræ.quantity}</td>
                                <td>${BOMtagogtræ.unit}</td>
                                <td>${BOMtagogtræ.description}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <h3>Skruer og Beslag</h3>
                    <div class="table-responsive-xl">
                        <table class="table-responsive-sm table" id="myTable1">
                            <thead>
                            <tr>

                                <th>Type</th>
                                <th>Antal</th>
                                <th>Enhed</th>
                                <th>Beskrivelse</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="BOMskruerogbeslag" items="${requestScope.BOMSkruerOgBeslag}">
                                <tr>
                                    <td>${BOMskruerogbeslag.type}</td>
                                    <td>${BOMskruerogbeslag.quantity}</td>
                                    <td>${BOMskruerogbeslag.unit}</td>
                                    <td>${BOMskruerogbeslag.description}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <div>

                        </div>
                    </div>
                </div>
            </form>
        </div>


    </jsp:body>

</t:pagetemplate>