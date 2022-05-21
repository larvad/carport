<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:payment>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>


        <c:if test="${sessionScope.user != null }">


            <div class="loadingPayment">
                <img src="${pageContext.request.contextPath}/images/logo.svg" width="100px;" class="img-fluid"/>
                    <div class="loadingBOx">
                        <div class="loader2"></div>
                        <h2>Processing payment</h2>
                </div>
            </div>


            <meta http-equiv = "refresh" content = "2; url = ${pageContext.request.contextPath}/fc/profile?command=profile">

        </c:if>


    </jsp:body>

</t:payment>