<%-- 
    Document   : dangNhap
    Created on : Aug 9, 2023, 2:53:01 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:url value="/dangNhap" var="action"/>


<div id="background">
    <div class="dangNhap">
        <h1 class="text-center">Đăng nhập</h1>
        <form method="POST" action="${action}"> 
            <div class="form-group">
                <!--name phai giong trong SpringSecurityConfig--> 
                <input type="text" name="taiKhoan" placeholder="Tên đăng nhập" class="form-control"/>
                <!--name phai giong trong SpringSecurityConfig--> 
                <input type="password" name="matKhau" placeholder="Mật khẩu" class="form-control"/>
                <!--<a href="#" class="quen-mat-khau form-control">Quên mật khẩu?</a>-->
            </div>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger mt-3 mb-3">
                    Đã có lỗi xảy ra!
                </div>
            </c:if>
            <c:if test="${param.accessDenied != null}">
                <div class="alert alert-danger mt-3 mb-3">
                    Bạn không có quyền
                </div>
            </c:if>
            <div class="form-group d-flex justify-content-center">
                <a class="btn btn-success text-white" href="<c:url value="/" />">Trang chủ</a>
                <input class="btn btn-success text-white" type="submit" value="Đăng nhập"/>
                <a class="btn btn-success text-white" href="<c:url value="/dangKy" />">Đăng ký</a>
            </div>
        </form>
    </div>
</div>

