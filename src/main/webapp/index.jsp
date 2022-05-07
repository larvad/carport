<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Welcome to the frontpage
    </jsp:attribute>

    <jsp:attribute name="footer">
        Welcome to the frontpage
    </jsp:attribute>

    <jsp:body>

        <p>Startcode for 2nd semester </p>

        <c:if test="${sessionScope.user != null}">
            <p>Du, ${sessionScope.user.username}, er logget ind nu.</p>

            <form action="fc/" method="post">
                <input type="hidden" name="command" value="CreateCarport"/>
                <input type="submit"  value="Her kan du skabe dine egen carport"/>
            </form>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>You are not logged in yet. You can do it here: <a
                    href="login.jsp">Login</a></p>
            
            <p>Du kan oprette en bruger her: <a href="createUser.jsp">Opret bruger</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>