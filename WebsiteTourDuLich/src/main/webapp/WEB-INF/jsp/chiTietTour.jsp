<%-- 
    Document   : chiTietTour
    Created on : Aug 9, 2023, 2:52:41 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="bg-tour" class="">
    <div class="container mr-auto ml-auto">
        <div class="chi-tiet-tour">
            <div class="row">
                <a href="<c:url value="/dsTour"/>">
                    <i class="fas fa-arrow-left mb-3 ml-4 text-black"></i>
                </a>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <c:if test="${tour.anh != null && tour.anh.startsWith('https') == true}">
                        <img src="${tour.anh}" alt="anh" style="width: 710px; object-fit: cover"/>
                    </c:if>
                    <c:if test="${tour.anh == null || tour.anh.startsWith('https') == false}">
                        <img class="img-fluid " src="<c:url value="/images/macDinh.gif"/>" alt="${tour.tenTour}"/>
                    </c:if>
                </div>
                <div class="col-md-4">
                    <h2>${tour.tenTour}</h2>
                    <h4 class="text-pink">Mô tả </h4>
                    <p class="mo-ta">${tour.moTa}</p>
                    <div class="margin-50"></div>
                    <div>
                        <i class="fas fa-calendar-alt text-pink h5"></i> 
                        Ngày bắt đầu: <span class="font-weight-bold ngay-thang-nam">${tour.ngayBD}</span> 
                        <br>
                        <i class="fas fa-calendar-check text-pink h5"></i> 
                        Ngày kết thúc: <span class="font-weight-bold ngay-thang-nam">${tour.ngayKT}</span>
                    </div>
                    <c:if test="${tour.soCho > 0}">
                        <div><i class="fas fa-chair text-pink h5"></i> 
                            Số chỗ còn nhận: <span class="text-danger font-weight-bold">${tour.soCho}</span></div>
                        </c:if>
                        <c:if test="${tour.soCho <= 0}">
                        <div><span class="text-danger font-weight-bold">Hết chỗ</span></div>
                    </c:if>
                    <p>Giá chỉ còn: <span class="text-success giaTien font-weight-bold"> ${tour.gia} </span></p>
                    <c:if test="${tour.soCho > 0}">
                        <a href="javascript:;" class="btn btn-info" 
                           onclick="themVaoGio(${tour.tourId}, '${tour.tenTour}', ${tour.gia}, ${tour.soCho})"
                           data-toggle="modal" data-target="#myModal" >Đặt tour</a>
                    </c:if>
                </div>
            </div>
            <c:if test="${nguoiDungDangNhap.id != null}">
                <form class="binh-luan">
                    <textarea id="binhLuanId" class="form-control col-md-5" placeholder="Nhập bình luận"></textarea>
                    <button type="submit" onclick="themBinhLuan(${tour.tourId}, ${nguoiDungDangNhap.id})"
                            class="btn btn-info">Đăng bình luận</button>
                </form>
            </c:if>
            <c:if test="${nguoiDungDangNhap.id == null}">
                <div class="col-md-8 mt-3 p-4 d-inline-flex justify-content-between border border-dark">
                    <div class="d-inline">
                        <h6>Đăng nhập</h6>
                        <p>Đăng nhập để tham gia bình luận nào.</p>
                        <br /><br />
                        <a href="<c:url value="/dangNhap" />" class="btn btn-normal">Đăng nhập</a>
                    </div>
                    <span class="border-left border-dark"></span>
                    <div class="d-inline">
                        <h6>Đăng ký ngay</h6>
                        <p class="mb-0">Bạn chưa có tài khoản?</p>
                        <p>Hãy nhanh tay tạo một tài khoản ngay nào!</p>
                        <br />
                        <a href="<c:url value="/dangKy" />" class="btn btn-normal">Đăng ký</a>
                    </div>
                </div>
            </c:if>
        </div>
        <div class="cac-binh-luan">
            <div class="bg-bl" id="binhLuanArea">
                <c:forEach var="b" items="${binhLuans}">
                    <div class="row" data-aos="fade-up" data-aos-duration="1000">   
                        <div class="col-md-2 d-flex justify-content-end">
                            <c:if test="${b[3] != null && b[3].startsWith('https') == true}">
                                <img class="img-fluid rounded-circle" src="${b[3]}"  alt="alt"/>
                            </c:if>
                            <c:if test="${b[3] == null || b[3].startsWith('https') == false}">
                                <div class="img-fluid rounded-circle d-flex flex-column text-center">
                                    <i class="fas fa-user-secret"></i>
                                    <span class="an-danh">Ẩn danh</span>
                                </div>
                            </c:if>
                        </div>
                        <div class="col-md-10">
                            <p>${b[1]}</p>
                            <i class="ngayBL">${b[2]}</i>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="d-flex justify-content-end" style="margin-top: -200px">
                <img src="<c:url value="../images/duLich3.png" />" />
            </div>
            <div class="d-flex justify-content-between page mr-sm-5 ml-sm-5">
                <ul class="pagination">
                    <c:forEach begin="1" end="${Math.ceil(slBinhLuan/5)}" var="i">  
                        <li class="page-item">
                            <a class="page-link" 
                               href="<c:url value="/chiTietTour/${tour.tourId}" />?page=${i}">${i}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
                <div> Tổng số bình luận ${slBinhLuan}</div> 
            </div>
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
                <a href="<c:url value="/gioHang" />" class="btn btn-success">Tới giỏ hàng</a>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
            </div>

        </div>
    </div>
</div>      
<script>
    $(document).ready(function () {
        $('[data-toggle="popover"]').popover();
    });
</script>
