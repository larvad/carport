<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <div class="boxBody">
            <div class="boxRight">
                <h1>Opret ny bruger</h1>
                <form action="fc/createUser" method="post">
                    <input type="hidden" name="command" value="createUser"/>
                    <input type="hidden" name="loginType" value="1">
                    <div class="mb-3 mb-3-user">
                        <label for="fullname" class="form-label">Fulde navn</label>
                        <input type="text" name="fullname" class="form-control" id="fullname" placeholder="Fulde navn">
                    </div>
                    <div class="mb-3 mb-3-user mb-3-user-right">
                        <label for="email" class="form-label">Email address</label>
                        <input type="email" name="email" class="form-control" id="email" placeholder="email@example.com">
                    </div>
                    <div class="mb-3 mb-3-user">
                        <label for="adress" class="form-label">Adresse</label>
                        <input type="text" name="adress" class="form-control" id="adress" placeholder="Johannes Fog Vej 21">
                    </div>
                    <div class="mb-3 mb-3-user mb-3-user-right">
                        <label for="phoneno" class="form-label">Telefon nr</label>
                        <input type="number" name="phoneno" class="form-control" id="phoneno" placeholder="12345678">
                    </div>
                    <div class="mb-3 mb-3-user">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" name="password" class="form-control" id="password" placeholder="Password">
                    </div>
                    <div class="mb-3 mb-3-user mb-3-user-right">
                        <label for="rppassword" class="form-label">Gentag password</label>
                        <input type="password" name="rppassword" class="form-control" id="rppassword" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary btn-layer2">Sign up</button>
                </form>
            </div>
            <div class="boxLeft">
                <h1>Login</h1>
                <form action="fc/createUser" method="post">
                    <input type="hidden" name="command" value="createUser"/>
                    <input type="hidden" name="loginType" value="2">
                    <div class="mb-3 mb-3-login">
                        <label for="email2" class="form-label">Email address</label>
                        <input type="email" name="email2" class="form-control" id="email2" placeholder="email@example.com">
                    </div>
                    <div class="mb-3 mb-3-login">
                        <label for="password2" class="form-label">Password</label>
                        <input type="password" name="password2" class="form-control" id="password2" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary btn-layer4">Sign in</button>
                </form>
            </div>
        </div>






<%--        <p><a href="index.jsp">Til forsiden</a></p>--%>

<%--        <section>--%>
<%--            <div class="container">--%>
<%--                <div class="row d-flex justify-content-center align-items-center h-100">--%>
<%--                    <div class="col-lg-12 col-xl-11">--%>
<%--                        <div class="card text-black" style="border-radius: 25px;">--%>
<%--                            <div class="card-body p-md-5">--%>
<%--                                <div class="row justify-content-center">--%>
<%--                                    <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">--%>

<%--                                        <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Opret bruger</p>--%>

<%--                                        <form class="mx-1 mx-md-4" action="fc/createUser" method="post">--%>
<%--                                            <input type="hidden" name="command" value="createUser"/>--%>
<%--                                            <div class="row">--%>
<%--                                                <div class="col">--%>
<%--                                                    <div class="d-flex flex-row align-items-center mb-4">--%>
<%--                                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>--%>
<%--                                                        <div class="form-outline flex-fill mb-0">--%>
<%--                                                            <input type="text" id="fullname" class="form-control"--%>
<%--                                                                   name="fullname"/>--%>
<%--                                                            <label class="form-label" for="fullname">Fulde navn</label>--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>

<%--                                            <div class="d-flex flex-row align-items-center mb-4">--%>
<%--                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>--%>
<%--                                                <div class="form-outline flex-fill mb-0">--%>
<%--                                                    <input type="number" id="phoneno" class="form-control"--%>
<%--                                                           name="phoneno"/>--%>
<%--                                                    <label class="form-label" for="phoneno">Telefonnummer</label>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>

<%--                                            <div class="d-flex flex-row align-items-center mb-4">--%>
<%--                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>--%>
<%--                                                <div class="form-outline flex-fill mb-0">--%>
<%--                                                    <input type="text" id="adress" class="form-control"--%>
<%--                                                           name="adress"/>--%>
<%--                                                    <label class="form-label" for="adress">Adresse</label>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>

<%--                                            <div class="d-flex flex-row align-items-center mb-4">--%>
<%--                                                <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>--%>
<%--                                                <div class="form-outline flex-fill mb-0">--%>
<%--                                                    <input type="email" id="email" class="form-control"--%>
<%--                                                           name="email"/>--%>
<%--                                                    <label class="form-label" for="email">Email</label>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>

<%--                                            <div class="d-flex flex-row align-items-center mb-4">--%>
<%--                                                <i class="fas fa-lock fa-lg me-3 fa-fw"></i>--%>
<%--                                                <div class="form-outline flex-fill mb-0">--%>
<%--                                                    <input type="password" id="password" class="form-control"--%>
<%--                                                           name="password"/>--%>
<%--                                                    <label class="form-label" for="password">Password</label>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>

<%--                                            <div class="d-flex flex-row align-items-center mb-4">--%>
<%--                                                <i class="fas fa-key fa-lg me-3 fa-fw"></i>--%>
<%--                                                <div class="form-outline flex-fill mb-0">--%>
<%--                                                    <input type="password" id="rppassword" class="form-control"--%>
<%--                                                           name="rppassword"/>--%>
<%--                                                    <label class="form-label" for="rppassword">Gentag--%>
<%--                                                        password</label>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>
<%--                                            <div class="container signin">--%>
<%--                                                <p>Har du allerede en konto? <a href="login.jsp">Log ind</a></p>--%>
<%--                                            </div>--%>

<%--                                            <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">--%>
<%--                                                <button type="submit" class="btn btn-primary btn-lg">Opret konto--%>
<%--                                                </button>--%>
<%--                                            </div>--%>

<%--                                        </form>--%>

<%--                                    </div>--%>
<%--                                    <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">--%>
<%--                                        <img src="${pageContext.request.contextPath}/images/signupcup.png"--%>
<%--                                             class="img-fluid" style="border-radius: 10px">--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </section>--%>

    </jsp:body>
</t:pagetemplate>