<%-- 
    Document   : themSuaTour
    Created on : Aug 9, 2023, 2:54:55 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!--modelAttribute="tour" la duoi tourController-->
<!--enctype=" la cua thang multipart-->
<!--path="file" trong Tour.clas-->

<div id="bg-tour">
    <c:if test="${tour.tourId > 0}">
        <h1 class="text-center" data-aos="fade-down" data-aos-duration="1500">
            Sửa tour
        </h1>
    </c:if>
    <c:if test="${tour.tourId <= 0}">
        <h1 class="text-center" data-aos="fade-down" data-aos-duration="1500">
            Thêm tour
        </h1>
    </c:if>

    <div >
        <c:if test="${errMsg != null}">
            <div class="alert alert-danger">${errMsg}</div>
        </c:if>
        <div class="container info-tour">
            <div class="row">
                <div id="form-tour" class="col-md-6">
                    <div  class="form-tour">
                        <form:form method="POST" 
                                   modelAttribute="tour" 
                                   enctype="multipart/form-data">
                            <form:errors path="*" cssClass="alert alert-danger text-center" element="div"/>
                            <div class="form-group">
                                <label class="text-white" for="tenTour">Tên tour</label>
                                <form:input type="text" cssClass="form-control" id="tenTour" path="tenTour"/>
                            </div>
                            <div class="form-group">
                                <label class="text-white" for="gia">Giá</label>
                                <form:input type="text" cssClass="form-control" id="gia" path="gia"/>
                            </div>
                            <div class="form-group">
                                <label class="text-white" for="ngayBD">Ngày bắt đầu</label>
                                <form:input type="date" cssClass="form-control" id="ngayBD" path="ngayBD"/>
                            </div>
                            <div class="form-group">
                                <label class="text-white" for="ngayKT">Ngày kết thúc</label>
                                <form:input type="date" cssClass="form-control" id="ngayKT" path="ngayKT"/>
                            </div>
                            <div class="form-group">
                                <label class="text-white" for="moTa">Mô tả</label>
                                <form:textarea path="moTa" id="moTa" rows="5" cols="48" />
                            </div>
                            <div class="form-group">
                                <label class="text-white" for="soCho">Số chỗ</label>
                                <form:input type="number" min="0" path="soCho" id="soCho" class="so-cho text-center"/>
                            </div>
                            <c:if test="${tour.tourId > 0}">
                                <div class="form-group"  id="hidden"  style="display:none;">
                                    <label class="text-white" for="file">File ảnh</label>
                                    <form:input type="file" cssClass="form-control" id="file" path="file"/>
                                </div>
                            </c:if>
                            <c:if test="${tour.tourId <= 0}">
                                <div class="form-group">
                                    <label class="text-white" for="file">File ảnh</label>
                                    <form:input type="file" cssClass="form-control" id="file" path="file"/>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <form:hidden path="tourId" />
                                <c:if test="${tour.tourId > 0}">
                                    <input type="submit" value="Sửa tour" class="btn btn-success"/>
                                </c:if>
                                <c:if test="${tour.tourId <= 0}">
                                    <input type="submit" value="Thêm tour" class="btn btn-success"/>
                                </c:if>
                            </div>
                        </form:form>
                    </div>
                </div>
                <c:if test="${tour.tourId > 0}">
                    <div class="col-md-6">
                        <label class="text-white" for="file">Hình ảnh</label>
                        <c:if test="${tour.anh != null && tour.anh.startsWith('https') == true}">
                            <img class="img-fluid" src="${tour.anh}" alt="${tour.tenTour}"/>
                        </c:if>
                        <c:if test="${tour.anh == null || tour.anh.startsWith('https') == false}">
                            <img class="img-fluid" src="<c:url value="/images/macDinh.gif"/>" alt="${tour.tenTour}"/>
                        </c:if>
                        <button class="btn btn-success mt-2" onclick="showDiv()">Chỉnh sửa ảnh</button>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<script>
    function showDiv() {
        document.getElementById('hidden').style.display = "inline-block";
    }
</script>
