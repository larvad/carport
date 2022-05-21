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
        <section class="vh-100">
            <div class="container py-5 h-100 box1">
                <div class="row d-flex justify-content-center align-items-center">
                    <h1>Min bestilling</h1>

                    <div class="col-12">
                        <div class="card card-stepper text-black" style="border-radius: 16px;">

                            <div class="card-body">

                                <div class="d-flex justify-content-between align-items-center mb-5">
                                    <div>
                                        <h5 class="mb-0">INVOICE <span class="textOrder">REQUEST #${sessionScope.requestID}</span></h5>
                                    </div>
                                </div>
                                <div class="statusContent">

                                    <ul id="progressbar-2" class="d-flex justify-content-between mx-0 mt-0 mb-5 ">
                                        <li class="step0<c:if test="${sessionScope.statusDTO.statusID >= 1}"> active </c:if> text-center" id="step1"></li>
                                        <li class="step0 <c:if test="${sessionScope.statusDTO.statusID >= 2}"> active </c:if> text-center" id="step2"></li>
                                        <li class="step0 <c:if test="${sessionScope.statusDTO.statusID == 3}"> active </c:if> text-muted text-end" id="step4"></li>
                                    </ul>
                                    <div class="d-flex text">
                                        <div class="d-lg-flex align-items-center">
                                            <i class="fas fa-clipboard-list fa-3x me-lg-4 mb-3 mb-lg-0"></i>
                                            <div>
                                                <p class="fw-bold mb-1">Forespørgsel</p>
                                                <p class="fw-bold mb-0">Sendt</p>
                                            </div>
                                        </div>
                                        <div class="d-lg-flex align-items-center move move2">
                                            <c:if test="${sessionScope.statusDTO.statusID == 1}">
                                                <div class="status-header">
                                                    <div class="loader"></div>
                                                </div>
                                                <div>
                                                    <p class="fw-bold mb-1">Afventer</p>
                                                    <p class="fw-bold mb-0">Bekræftigelse</p>
                                                </div>
                                            </c:if>
                                            <c:if test="${sessionScope.statusDTO.statusID >= 2}">
                                                <div class="status-header">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-check-circle-fill" viewBox="0 0 16 16" style="color: green">
                                                        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                    </svg>
                                                </div>
                                                <div>
                                                    <p class="fw-bold mb-1">Order</p>
                                                    <p class="fw-bold mb-0">Bekræftet</p>
                                                </div>
                                            </c:if>
                                        </div>
                                        <div class="d-lg-flex align-items-center move">
                                            <c:if test="${sessionScope.statusDTO.statusID == 2}">
                                                <div class="status-header">
                                                    <div class="loader"></div>
                                                </div>
                                            </c:if>
                                                <c:if test="${sessionScope.statusDTO.statusID <= 2}">
                                                <div>
                                                    <p class="fw-bold mb-1">Afventer</p>
                                                    <p class="fw-bold mb-0">Betaling</p>
                                                </div>
                                                </c:if>
                                            <c:if test="${sessionScope.statusDTO.statusID == 3}">
                                                <div class="status-header">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-check-circle-fill" viewBox="0 0 16 16" style="color: green">
                                                        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                    </svg>
                                                </div>
                                                <div>
                                                    <p class="fw-bold mb-1">Order</p>
                                                    <p class="fw-bold mb-0">Betalt</p>
                                                </div>
                                            </c:if>
                                            <div class="d-lg-flex align-items-center">
                                                <c:if test="${sessionScope.statusDTO.statusID >= 2}">
                                                    <div class="adminBox">
                                                        <img src="../images/jensjensen.jpg">
                                                        <div class="p2">
                                                            <p class="mb-0"><span class="bold" style="text-transform: uppercase; font-size: 14px;">Byggevejleder:</span></p>
                                                            <p class="mb-0"><span class="bold">Navn:</span> Jens Jensen</p>
                                                            <p class="mb-0"><span class="bold">Mail:</span> jens@johannesfog.dk</p>
                                                            <p class="mb-0"><span class="bold">Tlf:</span> 12345678</p>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${sessionScope.statusDTO.statusID < 2}">
                                                    <div class="adminBox adminBox2">
                                                        <img src="../images/afventerPb.jpg">
                                                        <div class="p2">
                                                            <p class="mb-0">Byggevejleder: Afventer</p>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${sessionScope.statusDTO.statusID >= 2}">
                                    <div class="listAndPay">
                                        <div class="leftSide">
                                            <c:if test="${sessionScope.statusDTO.statusID == 2}">
                                                <h2>Pris-total</h2>
                                                <h3>${sessionScope.finalPrice} DKK</h3>
                                            </c:if>
                                            <c:if test="${sessionScope.statusDTO.statusID == 3}">
                                                <h2>Order #${sessionScope.statusDTO.orderID}</h2>
                                                <h3>Betaling gennemført</h3>
                                            </c:if>
                                        </div>
                                        <div class="rightSide">
                                            <form action="fc/showSVG?command=showSVG" method="post">
                                                <input type="hidden" name="drawing" value="${sessionScope.statusDTO.orderID}"/>
                                                <input type="submit" class="btn btn-layer1 btnList" value="SE TEGNING"/>
                                            </form>
                                            <c:if test="${sessionScope.statusDTO.statusID == 2}">
                                                <form action="fc/profileUpdate?command=profileUpdate" method="post">
                                                    <input type="submit" class="btn btn-layer1 btnList btn-layer5" value="Betal"/>
                                                </form>
                                            </c:if>
                                            <c:if test="${sessionScope.statusDTO.statusID == 3}">
                                            <form action="fc/createBOM?command=createBOM" method="post">
                                                <input type="hidden" name="BOM" value="${sessionScope.statusDTO.orderID}">
                                                <input type="submit" class="btn btn-layer1 btnList btn-layer5 btn-layer6" value="Se stykliste"/>
                                            </form>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        </c:if>


    </jsp:body>

</t:pagetemplate>