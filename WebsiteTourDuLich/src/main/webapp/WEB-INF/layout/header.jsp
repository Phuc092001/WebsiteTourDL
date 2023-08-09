<%-- 
    Document   : header
    Created on : Aug 9, 2023, 3:10:18 PM
    Author     : ACER
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>



<nav class="navbar navbar-expand-lg navbar-dark bg-nav">
    <div class="container header">
        <a class="navbar-brand" href="<c:url value="/" />">
            <img class="logo" src="<c:url value="/images/logoText.png"/>"
                 alt="Logo" style="width:100px;" />
        </a>
        <div class="navbar-toggler title">Du Lich</div>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/"/>">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/dsTour"/>">Tour</a>
                </li>
                <sec:authorize access="hasAnyRole('ROLE_MANAGE', 'ROLE_ADMIN')">
                    <div class="nav-item dropdown d-flex">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink2"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Quản lý
                        </a>
                        <div class="dropdown-menu text-center quan-ly" aria-labelledby="navbarDropdownMenuLink2">
                            <div>
                                <a class="btn btn-outline-dark mr-auto ml-auto mb-2" href="<c:url value="/nhanVien/themSuaTour"/>">Thêm tour</a>
                            </div> 
                            <div>
                                <a class="btn btn-outline-dark mr-auto ml-auto mb-2"  href="<c:url value="/nhanVien/xemNguoiDangKyTour"/>">Đặt tour</a>
                            </div>
                            <div>
                                <a class="btn btn-outline-dark mr-auto ml-auto" href="<c:url value="/nhanVien/nguoiDungs"/>">Người dùng</a>
                            </div>
                        </div>
                    </div>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="nav-item">
                        <a  class="nav-link" href="<c:url value="/admin/thongKeDoanhThuTour"/>">Thống kê</a>
                    </li>               
                </sec:authorize>
                <sec:authorize access="!hasAnyRole('ROLE_MANAGE', 'ROLE_ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="#lienHe">Liên hệ</a>
                    </li>
                </sec:authorize>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/tinTuc"/>">Tin tức</a>
                </li>
                <sec:authorize access="!hasAnyRole('ROLE_MANAGE', 'ROLE_ADMIN')">
                    <div style="margin-right: 120px"></div>
                </sec:authorize>
            </ul>
            <div class="nav-item dropdown d-flex">
                <div class="item-gio-hang">
                    <a class="gioHang" href="<c:url value="/gioHang" />">
                        <i class="fas fa-cart-arrow-down"></i>
                        <span class="badge badge-danger" id="slTour">${demSLTour}</span>
                    </a>
                </div>
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Tài khoản
                    </a>
                    <div class="dropdown-menu text-center tai-khoan" aria-labelledby="navbarDropdownMenuLink">
                        <a class="btn btn-info mr-auto ml-auto" type="button" href="<c:url value="/dangNhap" />">Đăng nhập</a>
                        <div>
                            Chưa có tài khoản? <a href="<c:url value="/dangKy" />">Đăng kí</a> ngay
                        </div>
                    </div>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <div class="ml-3 d-flex align-items-center">
                        <div class="nav-item dropdown d-flex">
                            <c:if test="${nguoiDungDangNhap.anh != null && nguoiDungDangNhap.anh.startsWith('https') == true}">
                                <a class="nav-link" 
                                   href="#" id="navbarDropdownMenuLink2"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <img class="btn btn-outline-dark dropdown-toggle" src="<c:url value="${nguoiDungDangNhap.anh}"/>"
                                         alt="${pageContext.request.userPrincipal.name}"/>
                                </a>
                            </c:if>
                            <c:if test="${nguoiDungDangNhap.anh == null || nguoiDungDangNhap.anh.startsWith('https') != true}">
                                <div class="nguoi-dung">
                                    <a class="nav-link" 
                                       href="#" id="navbarDropdownMenuLink2"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-user"></i>
                                    </a
                                </div>
                            </c:if>
                            <div class="dropdown-menu quan-ly text-center" aria-labelledby="navbarDropdownMenuLink2">
                                <div>
                                    <span class="font-weight-bold">${pageContext.request.userPrincipal.name}</span>
                                    <c:if test="${nguoiDungDangNhap.vaiTro != null && nguoiDungDangNhap.vaiTro.startsWith('ROLE_A') == true}">
                                        <span>(Quản lý)</span>
                                    </c:if>
                                    <c:if test="${nguoiDungDangNhap.vaiTro != null && nguoiDungDangNhap.vaiTro.startsWith('ROLE_M') == true}">
                                        <span>(Nhân viên)</span>
                                    </c:if>
                                    <c:if test="${nguoiDungDangNhap.vaiTro != null && nguoiDungDangNhap.vaiTro.startsWith('ROLE_U') == true}">
                                        <span>(Khách hàng)</span>
                                    </c:if>
                                </div>
                                <div >
                                    <a class="btn btn-danger" href="<c:url value="/logout" />">Đăng xuất</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</nav>