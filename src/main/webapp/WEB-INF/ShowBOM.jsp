<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>


        <form action="">
            <h1>Træ og Tagplader</h1>
            <div class="table-responsive-xl">
                <table class="table-responsive-sm table" id="myTable">
                    <thead>
                    <tr>

                        <th>Type</th>
                        <th>Længde</th>
                        <th>antal</th>
                        <th>Endhed</th>
                        <th>Beskrivelse</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="BOMtagogtræ" items="${requestScope.BOMtagogtræ}">
                        <tr>
                            <td>${BOMtagogtræ.type}</td>
                            <td>${BOMtagogtræ.length} cm</td>
                            <td>${BOMtagogtræ.unit}</td>
                            <td>${BOMtagogtræ.quantity}</td>
                            <td>${BOMtagogtræ.description}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <h1>Skruer og Beslag</h1>
                <div class="table-responsive-xl">
                    <table class="table-responsive-sm table" id="myTable1">
                        <thead>
                        <tr>

                            <th>Type</th>
                            <th>Længde</th>
                            <th>antal</th>
                            <th>Endhed</th>
                            <th>Beskrivelse</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="BOMskruerogbeslag" items="${requestScope.BOMSkruerOgBeslag}">
                            <tr>
                                <td>${BOMskruerogbeslag.type}</td>
                                <td>${BOMskruerogbeslag.length} cm</td>
                                <td>${BOMskruerogbeslag.unit}</td>
                                <td>${BOMskruerogbeslag.quantity}</td>
                                <td>${BOMskruerogbeslag.description}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                <div>

                </div>
            </div>
        </form>


    </jsp:body>

</t:pagetemplate>