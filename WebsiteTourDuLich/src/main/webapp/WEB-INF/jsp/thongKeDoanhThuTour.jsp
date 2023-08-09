<%-- 
    Document   : thongKeDoanhThuTour
    Created on : Aug 9, 2023, 2:55:07 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<h1 class="text-center mt-3 text-success" data-aos="fade-down" data-aos-duration="1500">
    Thống kê doanh thu theo tour
</h1>
<div class="d-flex justify-content-center form">
    <form action="" class="text-center">
        <div class="d-flex align-items-center mt-2 mb-2">
            <input type="month" name="ngayBD" class="form-control" />
            <!--            <input type="date" name="ngayBD" class="form-control"/>
                        <span class="mx-2">-</span>
                        <input type="date" name="ngayKT" class="form-control"/>-->
        </div>
        <input type="submit" value="Thống kê" class="btn btn-info"/>
    </form>
</div>
<div style="margin-left: auto; margin-right: auto">
    <canvas id="myChart"></canvas>
</div>
<spring:url value="/report/?type=pdf" var="pdfURL" />
<a class="btn btn-info" href="${pdfURL }">Xuất file PDF</a> 
<table class="table">
    <tr class="text-white h5 bg-tb">
        <th class="text-center">Mã tour</th>
        <th class="text-center">Tên tour</th>
        <th class="text-center">Doanh thu</th>
    </tr>
    <c:forEach var="pro" items="${thongKeTour}">
        <tr>
            <td class="text-center">${pro[0]}</td>
            <td class="text-center">${pro[1]}</td>
            <td class="giaTien text-center">${pro[2]}</td>
        </tr>
    </c:forEach>
</table>
<!--tra ve pro[0] vi ben kia tra ve kieu Object-->

<script>
    let tourLaels = [], tourInfo = [];
    <c:forEach var="pro" items="${thongKeTour}">
    tourLaels.push('${pro[1]}')
    tourInfo.push(${pro[2]})
    </c:forEach>

    window.onload = function () {
        bieuDoTour("myChart", tourLaels, tourInfo)
        let gia = document.getElementsByClassName("giaTien")

        //dau phay trong tien
        var formatter = new Intl.NumberFormat('vi', {
            style: 'currency',
            currency: 'VND',
        });
        for (let i = 0; i < gia.length; i++)
        {

            gia[i].innerText = formatter.format(gia[i].innerText);
        }
    }

    AOS.init();
</script>