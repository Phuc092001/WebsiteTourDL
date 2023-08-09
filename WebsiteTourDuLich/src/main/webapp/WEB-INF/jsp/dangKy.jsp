<%-- 
    Document   : dangKy
    Created on : Aug 9, 2023, 2:52:52 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<div id="background">
    <div class="dangNhap dangKy">
        <c:if test="${nguoiDung.id > 0}">
            <h1 class="text-center">Sửa thông tin</h1>
        </c:if>
        <c:if test="${nguoiDung.id <= 0}">
            <h1 class="text-center">Đăng ký</h1>
        </c:if>
        <c:if test="${err != null}">
            <div class="alert alert-danger">
                ${err}
            </div>
        </c:if>
        <form:form method="POST"
                   modelAttribute="nguoiDung"
                   enctype="multipart/form-data">
            <div class="form-group">
                <label class="text-white" for="ho">Họ và tên đệm</label>
                <form:input type="text" placeholder="Họ và tên đệm" 
                            cssClass="form-control" id="ho" path="ho"/>
            </div>
            <form:errors path="ho" class="err" element="div"/>
            <div class="form-group">
                <label class="text-white" for="ten">Tên</label>
                <form:input type="text" placeholder="Tên" 
                            cssClass="form-control"  id="ten" path="ten"/>

            </div>
            <form:errors path="ten" class="err" element="div"/>
            <div class="form-group">
                <label class="text-white" for="email">Email</label>
                <form:input type="Email" placeholder="name@gmail.com" 
                            cssClass="form-control" id="email" path="email"/>

            </div>
            <form:errors path="email" class="err" element="div"/>
            <div class="form-group">
                <label class="text-white" for="taiKhoan">Tài khoản</label>
                <form:input type="text" placeholder="Tên đăng nhập" 
                            cssClass="form-control" id="taiKhoan" path="taiKhoan"/>

            </div>
            <form:errors path="taiKhoan" class="err" element="div"/>
            <c:if test="${nguoiDung.id <= 0}">
                <div class="form-group">
                    <label class="text-white" for="matKhau">Mật khẩu</label>
                    <form:input type="password" placeholder="Mật khẩu" 
                                cssClass="form-control" id="matKhau" path="matKhau"/>
                </div>

                <form:errors path="matKhau" class="err" element="div"/>


                <div class="form-group">
                    <label class="text-white" for="xacThucMatKhau">Nhập lại mật khẩu</label>
                    <form:input type="password" id="xacThucMatKhau" path="xacThucMatKhau"
                                placeholder="Nhập lại mật khẩu" cssClass="form-control" />
                </div>

                <form:errors path="xacThucMatKhau" class="err" element="div"/>
            </c:if>

            <div class="form-group">
                <label class="text-white" for="sdt">Số điện thoại</label>
                <form:input type="text" placeholder="Số điện thoại" path="sdt" 
                            cssClass="form-control" id="sdt" />

            </div>
            <form:errors path="sdt" class="err" element="div"/>
            <div class="form-group">
                <label class="text-white" for="file">Ảnh đại diện</label>
                <form:input type="file" cssClass="form-control" id="file" path="file"/>
            </div>
            <c:if test="${nguoiDung.id <= 0}">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="form-group">
                        <label class="text-white" for="vaiTro">Vai trò</label>
                        <form:select class="form-control select" id="vaiTro" path="vaiTro">
                            <form:option value="ROLE_USER">Khách hàng</form:option>
                            <form:option value="ROLE_MANAGE">Nhân viên</form:option>
                            <form:option value="ROLO_ADMIN">admin</form:option>
                        </form:select>
                    </div>
                </sec:authorize>
            </c:if>
            <div class="form-group d-flex justify-content-center">
                <a class="btn btn-success text-white" href="<c:url value="/" />">Trang chủ</a>
                <form:hidden path="id" />
                    <c:if test="${nguoiDung.id > 0}">
                        <input type="submit" value="Sửa thông tin" class="btn btn-success text-white"/>
                    </c:if>
                    <c:if test="${nguoiDung.id <= 0}">    
                        <input type="submit" value="Đăng ký" class="btn btn-success text-white"/>
                    </c:if>
                    <a class="btn btn-success text-white" href="<c:url value="/dangNhap" />">Đăng nhập</a>
            </div>

        </form:form>

    </div>
</div>
