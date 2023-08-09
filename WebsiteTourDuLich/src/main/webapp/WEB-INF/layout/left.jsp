<%-- 
    Document   : left
    Created on : Aug 9, 2023, 3:10:38 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- A vertical navbar -->
<nav class="navbar">
    <!-- Links -->
    <div>
        <h3 class="text-white de-muc">Thống kê</h3>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link p-0 pt-3" href="<c:url value="/admin/thongKeDoanhThuTour"/>">Doanh thu theo tour</a>
            </li>
            <li class="nav-item">
                <a class="nav-link p-0 pt-3" href="<c:url value="/admin/thongKeSoLuongTour"/>">Số lượng tour</a>
            </li>
        </ul>
    </div>
    <div class="mt-5">
        <h3 class="text-white de-muc">Quản lý</h3>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link p-0 pt-3" href="<c:url value="/nhanVien/themSuaTour"/>">Thêm tour</a>
            </li>
            <li class="nav-item">
                <a class="nav-link p-0 pt-3" href="<c:url value="/nhanVien/xemNguoiDangKyTour"/>">Đặt tour</a>
            </li>
            <li class="nav-item">
                <a class="nav-link p-0 pt-3" href="<c:url value="/nhanVien/nguoiDungs"/>">Người dùng</a>
            </li>
        </ul>
    </div>
</nav>
