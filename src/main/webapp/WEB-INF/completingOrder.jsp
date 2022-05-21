<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>


        <c:if test="${sessionScope.user != null }">

            <div class="loadingOrder">

            </div>
            <meta http-equiv = "refresh" content = "3; url = ${pageContext.request.contextPath}/profile.jsp" />

        </c:if>


    </jsp:body>

</t:pagetemplate>