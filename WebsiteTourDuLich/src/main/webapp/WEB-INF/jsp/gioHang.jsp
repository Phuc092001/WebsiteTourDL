<%-- 
    Document   : gioHang
    Created on : Aug 9, 2023, 2:53:23 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<div id="bg-tour" class="text-black">
    <div class="container mr-auto ml-auto">
        <div class="d-flex justify-content-between">
            <a href="<c:url value="/dsTour"/>">
                <i class="fas fa-arrow-left ml-4 text-black"></i>
            </a>
            <h1 class="d-inline-block">Đăng ký đặt tour</h1>
            <div></div>
        </div>
        <table class="table text-black">
            <tr class="text-center text-black h4 bg-tb">
                <th>Mã tour</th>
                <th>Tên tour</th>
                <th>Số lượng đặt/Số lượng còn</th>
                <th>Đơn giá</th>
                <th></th>
            </tr>
            <c:forEach var="c" items="${gioHangs}">
                <tr class="text-center">
                    <td>${c.tourId}</td>
                    <td>${c.tenTour}</td>
                    <td class="">
                        <div class="form-group">
                            <input id="soLuong" type="number" min="0" max="${c.soCho}" value="${c.soLuong}" class="so-luong text-center" 
                                   onblur="capNhatSLTour(this, ${c.tourId}, ${c.soCho})"/>
                            /${c.soCho}
                        </div>
                    </td>
                    <td class="giaTien">${c.gia}</td>
                    <td>
                        <input type="button" value="Xóa" onclick="xoaTourTrongGio(${c.tourId})" class="btn btn-danger"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h4 class="alert alert-warning text-right">
            Tổng tiền: <span id="tongTien" class="giaTien">${tinhTien.tongTien}</span>
        </h4>
        <div class="d-flex justify-content-center">
            <c:if test="${nguoiDungDangNhap.id != null}">
                <input type="button" 
                       onclick="thanhToan(${nguoiDungDangNhap.id}, ${demSLTour}, false, ${tinhTien.tongTien})" 
                       value="Đặt ngay" class="btn btn-info thanh-toan"
                       data-toggle="modal" data-target="#myModal"/>
                <a class="btn btn-info thanh-toan" 
                   onclick="thanhToan(${nguoiDungDangNhap.id}, ${demSLTour}, true, ${tinhTien.tongTien})"
                   href="<c:url value="/paypal?tongTien=${tinhTien.tongTien}" />">Thanh toán online</a>
            </c:if>
            <c:if test="${nguoiDungDangNhap.id == null}">
                <div class="col-md-8 p-4 d-inline-flex justify-content-between border border-white">
                    <div class="d-inline">
                        <h6>Đăng nhập</h6>
                        <p>Đăng nhập để liên hệ đặng ngay nào.</p>
                        <br /><br />
                        <a href="<c:url value="/dangNhap" />" class="btn btn-normal border-black text-black">Đăng nhập</a>
                    </div>
                    <span class="border-left border-black"></span>
                    <div class="d-inline">
                        <h6>Đăng ký ngay</h6>
                        <p class="mb-0">Bạn chưa có tài khoản?</p>
                        <p>Hãy nhanh tay tạo một tài khoản ngay nào!</p>
                        <br />
                        <a href="<c:url value="/dangKy" />" class="btn btn-normal border-black text-black">Đăng ký</a>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('[data-toggle="popover"]').popover();
    });
</script>