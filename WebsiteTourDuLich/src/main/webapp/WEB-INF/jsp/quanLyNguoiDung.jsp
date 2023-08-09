<%-- 
    Document   : quanLyNguoiDung
    Created on : Aug 9, 2023, 2:54:03 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="bg-quan-ly" class="">
    <div class="container">
        <h1 class="text-center text-black" data-aos="fade-down" data-aos-duration="1500">
            Quản lý người dùng
        </h1>
        <div class="mb-3 d-flex justify-content-between">
            <a><a href="<c:url value="/dangKy" />"  class="btn btn-info d-inline">Thêm người dùng</a>
            <form class="form-inline d-inline-block ml-auto" action="">
                <input class="form-control mr-sm-2" name="taiKhoan" type="search" placeholder="Nhập tên tài khoản..." aria-label="Search">
                <input type="submit" class="btn btn-info" value="Tìm kiếm" />
            </form>
        </div>
        <table class="table bg-light">
            <tr class="text-center text-white h5 bg-tb">
                <th>Người dùng</th>
                <th>Tài khoản người dùng</th>
                <th>Số điện thoại</th>
                <th>Vai trò</th>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE')">
                    <th></th>
                    </sec:authorize>
            </tr>
            <c:forEach var="nguoiDung" items="${dsNguoiDung}">
                <tr class="text-center" id="n${nguoiDung.id}">
                    <c:if test="${nguoiDung.anh != null && nguoiDung.anh.startsWith('https') == true}">
                        <td>
                            <img class="img-fluid rounded-circle " src="<c:url value="${nguoiDung.anh}"/>" alt="${nguoiDung.taiKhoan}"/>
                            <div class="ten-nguoi-dung">
                                ${nguoiDung.ho} ${nguoiDung.ten}
                            </div>
                        </td>
                    </c:if>
                    <c:if test="${nguoiDung.anh == null || nguoiDung.anh.startsWith('https') == false}">
                        <td>
                            <img class="img-fluid rounded-circle" src="<c:url value="/images/21.jpg"/>" alt="${nguoiDung.taiKhoan}"/>
                            <div class="ten-nguoi-dung">
                                ${nguoiDung.ho} ${nguoiDung.ten}
                            </div>
                        </td>
                    </c:if>
                    <td>${nguoiDung.taiKhoan}</td>
                    <td>${nguoiDung.sdt}</td>
                    <c:if test="${nguoiDung.vaiTro != null && nguoiDung.vaiTro.startsWith('ROLE_A') == true}">
                        <td>Admin</td>
                    </c:if>
                    <c:if test="${nguoiDung.vaiTro != null && nguoiDung.vaiTro.startsWith('ROLE_U') == true}">
                        <td>Người dùng</td>
                    </c:if>
                    <c:if test="${nguoiDung.vaiTro != null && nguoiDung.vaiTro.startsWith('ROLE_M') == true}">
                        <td>Nhân viên</td>
                    </c:if>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <a href="javascript:;" onclick="xoaNguoiDung(${nguoiDung.id})">
                                <input type="button" value="Xóa" class="btn btn-danger"/>
                            </a>
                            <a href="<c:url value="/khongTimThay" />">
                                <input type="button" value="Sửa" class="btn btn-success"/>
                            </a>
                        </td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_MANAGE')">
                        <c:if test="${nguoiDung.vaiTro != null && nguoiDung.vaiTro.startsWith('ROLE_U') == true}">
                            <td>
                                <a  href="javascript:;" onclick="xoaNguoiDung(${nguoiDung.id})">
                                    <input type="button" value="Xóa" class="btn btn-danger"/>
                                </a>
                                <a href="<c:url value="/khongTimThay" />">
                                    <input type="button" value="Sửa" class="btn btn-success"/>
                                </a>
                            </td>
                        </c:if>
                        <c:if test="${nguoiDung.vaiTro != null && nguoiDung.vaiTro.startsWith('ROLE_U') != true}">
                            <td>
                                <div class="alert alert-danger mb-0">Bạn không có quyền</div>
                            </td>
                        </c:if>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        <div class="d-flex justify-content-between page mr-sm-5 ml-sm-5"> 
            <ul class="pagination">
                <c:forEach begin="1" end="${Math.ceil(slNguoiDung/6)}" var="i">
                    <li class="page-item">
                        <a class="page-link" href="<c:url value="/nhanVien/nguoiDungs" />?page=${i}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
            <div> Tổng số lượng người dùng: ${slNguoiDung}</div>
        </div>
    </div>
</div>


