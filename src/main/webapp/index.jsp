<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>


<%--             <p>Du, ${sessionScope.user.username}, er logget ind nu.</p>--%>

<%--            <video id="background-video" autoplay loop muted poster="https://assets.codepen.io/6093409/river.jpg">--%>
<%--                <source src="images/vid.mp4" type="video/mp4">--%>
<%--            </video>--%>
            <div id="outer">
                <h1>Byg din egen carport</h1>
                <form action="fc/createCarport" method="post">
                    <input type="hidden" name="command" value="createCarport"/>
                    <input type="submit" class="btn btn-layer3" value="send forespørgsel"/>
                </form>
                <div id="home-top-video">
                    <video autoplay loop muted width="100%">
                        <source src="images/vid.mp4" type="video/mp4" /> Your browser does not support the video tag. We suggest you upgrade your browser.
                    </video>
                </div>
            </div>


    </jsp:body>

</t:pagetemplate>