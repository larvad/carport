<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/carport.js"></script>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light navbar-custom">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
                <img src="${pageContext.request.contextPath}/images/logo.svg" width="125px;" class="img-fluid"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mx-auto mb-2 mb-lg-0">
                </ul>
                <div class="buttonsRight">
                    <c:if test="${sessionScope.user.roleId == 2 }">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/admin?command=admin">Big Money</a>
                    </c:if>
                    <c:if test="${sessionScope.user == null }">
                    <div class="dropdown dropdown-color">
                        <button class="btn btn-secondary dropdown-toggle btn-layer1" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            Login
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <form action="fc/login" method="post">
                                <input type="hidden" name="command" value="login"/>
                                <div class="mb-3 mb-3-header">
                                    <label for="email" class="form-label">Email address</label>
                                    <input type="email" name="email" class="form-control" id="email" placeholder="email@example.com">
                                </div>
                                <div class="mb-3 mb-3-header">
                                    <label for="password" class="form-label">Password</label>
                                    <input type="password" name="password" class="form-control" id="password" placeholder="Password">
                                </div>
                                <button type="submit" class="btn btn-primary btn-layer2">Sign in</button>
                            </form>
                        </ul>
                    </div>
                    <a class="btn btn-layer2" href="${pageContext.request.contextPath}/createUser.jsp">Opret bruger</a>
                    </c:if>
                    <c:if test="${sessionScope.user != null }">
                        <a class="profilKnap" href="${pageContext.request.contextPath}/fc/profile?command=profile">Min profil</a>
                    <a class="btn btn-layer1" href="${pageContext.request.contextPath}/fc/logout?command=logout">Log out</a>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>
</header>

<div id="body" class="container mt-4" style="min-height: 400px;">
    <h1><jsp:invoke fragment="header"/></h1>
    <jsp:doBody/>
</div>

<!-- Footer -->

<div class="footerClass">
    <section class="">
        <footer class="text-center text-white" style="background-color: #003d76">
            <div class="container p-4 pb-0">
                <section class="verticalSpans">
                    <div>
                        <p>
                            Nørgaardsvej 30<br/>
                            2800 Lyngby
                        </p>
                        <p>
                            <jsp:invoke fragment="footer"/>
                        </p>
                        <p>
                            Datamatikeruddannelsen<br/>
                            2. semester forår 2022
                        </p>
                    </div>
                </section>
            </div>
            <div class="text-center p-3" style="background-color: rgba(0,0,0,0.2)">
                &copy; 2022 Cphbusiness
            </div>
        </footer>
    </section>
</div>


<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>