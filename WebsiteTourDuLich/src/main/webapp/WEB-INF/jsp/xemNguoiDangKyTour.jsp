<%-- 
    Document   : xemNguoiDangKyTour
    Created on : Aug 9, 2023, 2:56:00 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div id="bg-quan-ly" class="">
    <div class="container mr-auto ml-auto">
        <h1 class="text-center" data-aos="fade-down" data-aos-duration="1500">
            Quản lý đặt tour
        </h1>
        <table class="table bg-light">
            <tr class="text-white h5 bg-tb">
                <th class="text-center">Mã hóa đơn</th>
                <th class="text-center">Người đặt</th>
                <th class="text-center">Số điện thoại</th>
                <th class="text-center">Ngày đặt</th>
                <th class="text-center">Tổng tiền</th>
                <th class="text-center"></th>
            </tr>
            <c:forEach var="nd" items="${nguoiDatTour}">
                <tr class="">
                    <td class="text-center">${nd[0]}</td>
                    <td class="text-center">${nd[1]} ${nd[2]}</td>
                    <td class="text-center">${nd[3]}</td>
                    <td class="text-center">${nd[4]}</td>
                    <td class="text-center giaTien">${nd[5]}</td>
                    <td class="text-center">
                        <a href="<c:url value="/nhanVien/xemNguoiDangKyTour/${nd[0]}"/>">
                            <input type="button" value="Chi tiết" class="btn btn-success"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="d-flex justify-content-between page mr-sm-5 ml-sm-5"> 
            <ul class="pagination">
                <c:forEach begin="1" end="${Math.ceil(slTourDuocDat/10)}" var="i">
                    <li class="page-item">
                        <a class="page-link" href="<c:url value="/nhanVien/xemNguoiDangKyTour" />?page=${i}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>