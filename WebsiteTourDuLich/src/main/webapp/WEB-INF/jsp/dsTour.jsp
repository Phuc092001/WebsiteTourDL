<%-- 
    Document   : dsTour
    Created on : Aug 9, 2023, 2:53:11 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section id="dsTour">
    <div class="container info-sec">
        <div>
            <h3 id="danh-sach-tour" data-aos="fade-right" data-aos-duration="1500">
                Danh sách các tour
            </h3>
            <form class="form-inline row container pr-xs-0" action="">
                <input class="form-control col-xs-1 col-md-4 mr-xs-2 mr-sm-1 mb-2" 
                       name="kw" type="search" placeholder="Bạn muốn đi đâu?" aria-label="Search">
                <input type="submit" class="btn btn-warning mb-2 font-weight-bold" value="Tìm kiếm"/>
            </form>
            <a class="btn btn-normal dropdown-toggle mb-4" href="#" id="dropdownGia"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Giá
            </a>
            <div id="gia" class="dropdown-menu text-center p-3" aria-labelledby="dropdownGia">
                <a class="btn btn-normal mb-2" href="<c:url value="/dsTour" />?tu=0&den=400000">Dưới 400,000</a>
                <a class="btn btn-normal mb-2" href="<c:url value="/dsTour" />?tu=400000&den=600000">Từ 400,000 - 600,000</a>
                <br/>
                <a class="btn btn-normal" href="<c:url value="/dsTour" />?tu=600000&den=1000000">Từ 600,000 - 1000,000</a>
                <a class="btn btn-normal" href="<c:url value="/dsTour" />?tu=1000000&den=99999999">Trên 1000,000</a>
            </div>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE')">
                <div class="d-flex justify-content-end my-3 them-tour">
                    <a class="btn btn-normal" href="<c:url value="/nhanVien/themSuaTour"/>">
                        <i class="fas fa-plus-circle"></i>
                        Thêm tour
                    </a>
                </div>
            </sec:authorize>
            <div class="d-flex justify-content-between">
                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE')">
                    <div class="so-luong-tour">
                        Tổng số tour: ${slTour}
                    </div>
                </sec:authorize>
                <ul class="pagination">
                    <c:forEach begin="1" end="${Math.ceil(slTour/9)}" var="i">
                        <li class="page-item">
                            <a class="page-link" 
                               href="<c:url value="/dsTour" />?page=${i}#danh-sach-tour">${i}
                            </a>
                        </li>
                    </c:forEach> 
                </ul>
            </div>
            <div id="dsTour">
                <div class="info-img">  
                    <div class="row">
                        <c:forEach var="pro" items="${tours}">   
                            <div class="col-xs-12 col-sm-6 col-md-4" id="pro${pro.tourId}">
                                <div class="box-tour">
                                    <a href="<c:url value="/chiTietTour/${pro.tourId}"/>">
                                        <div class="position-relative">
                                            <div id="image">
                                                <c:if test="${pro.anh!= null && pro.anh.startsWith('https') == true}">
                                                    <img class="img-fluid " src="<c:url value="${pro.anh}"/>"
                                                         alt="${pro.tenTour}"/>
                                                </c:if>
                                                <c:if test="${pro.anh == null || pro.anh.startsWith('https') == false}">
                                                    <img class="img-fluid " src="<c:url value="/images/macDinh.gif"/>"
                                                         alt="${pro.tenTour}"/>
                                                </c:if>
                                            </div>
                                            <div  class="vi-tri">
                                                <div class="px-3">
                                                    <span>
                                                        <i class="fas fa-map-marker-alt"></i>${pro.tenTour}
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    <div class="info-tour">
                                        <div>
                                            <h3>${pro.tenTour}</h3>
                                        </div>
                                        <div class="margin-5">
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <c:if test="${pro.soCho <= 0}">
                                                <a class="btn btn-info"
                                                   href="<c:url value="/chiTietTour/${pro.tourId}"/>">Xem tour</a>
                                            </c:if>
                                            <c:if test="${pro.soCho > 0}">
                                                <a href="javascript:;" class="btn btn-info" 
                                                   onclick="themVaoGio(${pro.tourId}, '${pro.tenTour}', ${pro.gia}, ${pro.soCho})"
                                                   data-toggle="modal" data-target="#myModal" >Đặt ngay</a>
                                            </c:if>
                                            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE')">
                                                <div class="btn-seting d-inline-block mt-1">
                                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                                        <a href="javascript:;" onclick="xoaTour(${pro.tourId})">
                                                            <i class="fas fa-trash-alt text-danger"></i>
                                                        </a>
                                                    </sec:authorize>
                                                    <a href="<c:url value="/nhanVien/themSuaTour/?tourId=${pro.tourId}" />">
                                                        <i class="fas fa-wrench text-warning"></i>
                                                    </a>
                                                </div>
                                            </sec:authorize>
                                        </div>
                                        <div class="gia d-flex justify-content-between">
                                            <c:if test="${pro.soCho > 0}">
                                                <span class="mt-auto ml-2">Còn: 
                                                    <span class="text-danger font-weight-bold">${pro.soCho}
                                                    </span>
                                                </span>
                                            </c:if>
                                            <c:if test="${pro.soCho <= 0}">
                                                <span class="mt-auto ml-2">Hết chỗ</span>
                                            </c:if>
                                            <span class="giaTien">${pro.gia}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div>
                <ul class="pagination d-flex justify-content-end">
                    <c:forEach begin="1" end="${Math.ceil(slTour/9)}" var="i">
                        <li class="page-item">
                            <a class="page-link" href="<c:url value="/dsTour" />?page=${i}#diaDiem">${i}</a>
                        </li>
                    </c:forEach> 
                </ul>
            </div>
        </div>
    </div>
    <!-- The Modal -->
    <div class="modal fade" id="myModal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    Đã thêm tour vào giỏ hàng
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <a href="<c:url value="/gioHang"/>" class="btn btn-success">Tới giỏ hàng</a>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                </div>

            </div>
        </div>
    </div>
</section>
<!-- comment -->
<section id="hotTours">
    <div class="container">
        <div>
            <h2 class="pb-4 font-weight-bold text-center">Các tour nổi bật</h2>
        </div>
        <div class="row info-img">
            <c:forEach var="pro" items="${hotTours}">   
                <div class="col-xs-12 col-sm-6 col-md-4" id="pro${pro[0]}">
                    <div class="box-tour">
                        <a href="<c:url value="/chiTietTour/${pro[0]}"/>">
                            <div class="position-relative">
                                <div id="image">
                                    <c:if test="${pro[3]!= null && pro[3].startsWith('https') == true}">
                                        <img class="img-fluid " src="<c:url value="${pro[3]}"/>"
                                             alt="${pro[1]}"/>
                                    </c:if>
                                    <c:if test="${pro[3] == null || pro[3].startsWith('https') == false}">
                                        <img class="img-fluid " src="<c:url value="/images/macDinh.gif"/>"
                                             alt="${pro[1]}"/>
                                    </c:if>
                                </div>
                                <span class="ribbon-content">
                                    <span>${pro[5]} chỗ đã được đặt</span>
                                </span>
                                <div  class="vi-tri">
                                    <div class="px-3">
                                        <span>
                                            <i class="fas fa-map-marker-alt"></i>${pro[1]}
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </a>
                        <div class="info-tour">
                            <div>
                                <h3>${pro[1]}</h3>
                            </div>
                            <div class="margin-5">
                            </div>
                            <div class="d-flex justify-content-between">
                                <c:if test="${pro[4] <= 0}">
                                    <a class="btn btn-info"
                                       href="<c:url value="/chiTietTour/${pro[0]}"/>">Xem tour</a>
                                </c:if>
                                <c:if test="${pro[4] > 0}">
                                    <a href="javascript:;" class="btn btn-info" 
                                       onclick="themVaoGio(${pro[0]}, '${pro[1]}', ${pro[2]}, ${pro[4]})"
                                       data-toggle="modal" data-target="#myModal" >Đặt ngay</a>
                                </c:if>
                                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE')">
                                    <div class="btn-seting d-inline-block mt-1">
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                            <a href="javascript:;" onclick="xoaTour(${pro[0]})">
                                                <i class="fas fa-trash-alt text-danger"></i>
                                            </a>
                                        </sec:authorize>
                                        <a href="<c:url value="/nhanVien/themSuaTour/?tourId=${pro[0]}" />">
                                            <i class="fas fa-wrench text-warning"></i>
                                        </a>
                                    </div>
                                </sec:authorize>
                            </div>
                            <div class="gia d-flex justify-content-between">
                                <c:if test="${pro[4] > 0}">
                                    <span class="mt-auto ml-2">Còn: 
                                        <span class="text-danger font-weight-bold">${pro[4]}
                                        </span>
                                    </span>
                                </c:if>
                                <c:if test="${pro[4] <= 0}">
                                    <span class="mt-auto ml-2">Hết chỗ</span>
                                </c:if>
                                <span class="giaTien">${pro[2]}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>



