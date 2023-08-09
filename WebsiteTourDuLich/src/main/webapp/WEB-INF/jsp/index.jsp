<%-- 
    Document   : index
    Created on : Aug 9, 2023, 2:53:33 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div id="jumbotron" class="carousel slide" data-ride="carousel">

    <!-- Indicators -->
    <ul class="carousel-indicators">
        <li data-target="#jumbotron" data-slide-to="0" class="active"></li>
        <li data-target="#jumbotron" data-slide-to="1"></li>
        <li data-target="#jumbotron" data-slide-to="2"></li>
    </ul>

    <!-- The slideshow -->
    <div class="carousel-inner ">
        <div class="carousel-item active">
            <img src="<c:url value="./images/slider1.jpg" />" alt="daLat" style="height: 700px; width: 100%; object-fit: cover">
            <div class="carousel-caption">
                <h3>Đà Lạt</h3>
                <p>Thàng phố mộng mơ</p>
            </div>
        </div>
        <div class="carousel-item">
            <img src="<c:url value="./images/slider2.jpg" />" alt="daNang" style="height: 700px; width: 100%; object-fit: cover">
            <div class="carousel-caption">
                <h3>Đà Nẵng</h3>
                <p>Thành phố của những cây cầu!</p>
            </div>
        </div>
        <div class="carousel-item">
            <img src="<c:url value="./images/slider3.jpg" />" alt="vungTau" style="height: 700px; width: 100%; object-fit: cover">
            <div class="carousel-caption">
                <h3>Vũng Tàu</h3>
                <p>Thành phố biển!</p>
            </div>
        </div>
    </div>
    <!-- Left and right controls -->
    <a class="carousel-control-prev" href="#jumbotron" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </a>
    <a class="carousel-control-next" href="#jumbotron" data-slide="next">
        <span class="carousel-control-next-icon"></span>
    </a>


</div>
<!--banner-->

<div class="bg">
    <div class="bg-location">
        <div class="info-location" >
            <p class="" data-aos="fade-left" data-aos-duration="1500">
                <span class="text-danger font-weight-bold">" </span>
                Du lịch mang đến những trải nghiệm thực tế về cuộc sống,
                về con người, về văn hóa, về ẩm thực… Bất cứ điều gì bạn quan tâm,
                bạn yêu thích, bạn sẽ dễ dàng tìm thấy trong một chuyến đi.
                Một vùng đất dù xa lạ hay quen thuộc đều sẽ làm tươi mới tâm hồn bạn để bạn thấy
                cuộc sống vốn dĩ tươi đẹp cho dù bất cứ điều gì xảy ra.
                <span class="text-danger font-weight-bold"> "</span>
            </p>
            <img  src="<c:url value="./images/duLich1.png" />" 
                  style="position: absolute; right: 0px; bottom: 0px; transform: rotateY(180deg);">
        </div>
    </div>
</div>
<video class="video-fluid z-depth-1" data-aos="fade-up" data-aos-duration="1500"
       loop controls muted style="width: 100%; height: 720px;">
    <source src="https://res.cloudinary.com/xxxs1mplexxx/video/upload/v1633823602/y2meta.com_-_XIN_CHA%CC%80O_VIE%CC%A3%CC%82T_NAM___DU_L%E1%BB%8ACH_XUYE%CC%82N_VIE%CC%A3%CC%82T___PHI%C3%8AN_B%E1%BA%A2N_FLYCAM___HELLO_VIET_NAM_nejjkz.mp4" type="video/mp4" />
</video>
<div class="bg-2" >
    <div class="info-location-2" style="position: absolute;">
        <p class="" data-aos="fade-right" data-aos-duration="1500">
            <span class="text-danger font-weight-bold">" </span>Là một con dân nước Việt Nam nhưng mấy ai 
            đã đặt chân hết các vùng miền đất Việt tươi đẹp.
            Nếu có cơ hội bạn hãy đến top các địa điểm du lịch 
            nổi tiếng đẹp nhất ở Việt Nam bạn sẽ biết ở Việt 
            Nam có những thiên đường thật sự, truyền thống,
            văn hóa dân tộc được hòa quyện hoàn hảo với thiên
            nhiên đất trời<span class="text-danger font-weight-bold"> "</span>
        </p>
        <img src="<c:url value="./images/duLich2.png" />" style="position: relative; right: 200px; bottom: 131px">
    </div>
</div>


<div class="container box-images">
    <h3 class="" data-aos="fade-right" data-aos-duration="2000">Các điểm đến nổi bật</h3>
    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-8" data-aos="fade-up-right" data-aos-duration="1800">
            <a href="<c:url value="/dsTour?kw=Cao+bằng"/>">
                <img class="img-fluid" src="https://res.cloudinary.com/xxxs1mplexxx/image/upload/v1633809824/fbvtp8bs3ud4mbjsjfiv.jpg"/>
                <h4 class="text-white">Cao Bằng</h4>
            </a>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-4">
            <div class="mb-4"  data-aos="fade-left" data-aos-duration="1000">
                <a href="<c:url value="/dsTour?kw=Hạ+long"/>">
                    <img class="img-fluid mb-3" src="https://res.cloudinary.com/xxxs1mplexxx/image/upload/v1633773323/uvwjiqs1lumvkqs2r5in.gif"/>
                    <h4 class="text-white">Hạ Long</h4>
                </a>
            </div>
            <div data-aos="fade-up" data-aos-duration="1500">
                <img class="img-fluid" src="https://res.cloudinary.com/xxxs1mplexxx/image/upload/v1633801696/bl4mgzzjgbhlwb5s0rv4.gif"/>
                <h4 class="text-white">Bình Thuận</h4>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-4" data-aos="fade-right" data-aos-duration="1700">
            <div class="mb-4">
                <a href="<c:url value="/dsTour?kw=Phú+Yên"/>">
                    <img class="img-fluid mb-3" src="https://res.cloudinary.com/xxxs1mplexxx/image/upload/v1633417793/rvogbphlppcy8cbnn5qn.gif"/>
                    <h4 class="text-white">Phú Yên</h4>
                </a>
            </div>
            <div data-aos="fade-up" data-aos-duration="1500">
                <a href="<c:url value="/dsTour?kw=Phú+quốc"/>">
                    <img class="img-fluid" src="https://res.cloudinary.com/xxxs1mplexxx/image/upload/v1633772867/ccgnvmpm8gszgwdgxzxo.gif"/>
                    <h4 class="text-white">Phú Quốc</h4>
                </a>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-8" data-aos="fade-up-left" data-aos-duration="2000">
            <a href="<c:url value="/dsTour?kw=Cao+bằng"/>">
                <img class="img-fluid" src="https://res.cloudinary.com/xxxs1mplexxx/image/upload/v1633376391/b5zfn0bh9klcjblzg6sq.jpg"/>
                <h4 class="text-white">Thác Bản Giốc</h4>
            </a>
        </div>
    </div>
</div>
<div class="bg-diem-den d-flex justify-content-center align-items-center" 
     data-aos="fade-up" data-aos-duration="2000">
    <div class="ket-thuc text-center px-3">
        <p class="my-auto Oswald" data-aos="fade-up" data-aos-duration="2000">
            Chúng ta chỉ có duy nhất một lần làm người trẻ.
            Nếu không tận dụng nó, chúng ta sẽ không bao giờ có cơ hội
            để đi du lịch như lúc này.</p>
    </div>
    <div class="bg-ket-thuc bg-ket-thuc-1" data-aos="fade-down-right" data-aos-duration="1500">
    </div>
    <div class="bg-ket-thuc bg-ket-thuc-2" data-aos="fade-up-left" data-aos-duration="2000">
    </div>  
</div>
<div class="bg-ket-thuc bg-ket-thuc-3" data-aos="zoom-in-up" data-aos-duration="1500">
</div>
