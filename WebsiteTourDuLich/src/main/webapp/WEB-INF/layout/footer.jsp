<%-- 
    Document   : footer
    Created on : Aug 9, 2023, 3:09:57 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="footer text-center" id="lienHe">
    <div class="container text-black">
        <div class="row pt-5 pb-5">
            <!--cot1-->
            <div class="col-1">
                <a href="<c:url value="/" />">
                    <img class="" src="<c:url value="/images/logo.png"/>"
                         alt="Logo" style="width:50px; object-fit: cover">
                </a>
            </div>
            <!--cot2-->
            <div class="col-6 text-left">
                <div class="">
                    <h3>Công ty TNHH Du Lich Việt Nam</h3>
                    <span class="font-weight-bold text-dange font-mali">Trụ sở chính: </span>
                    <span class="font-mali">Tầng 9 và Tầng 10, 31 Lê Duẩn, 
                        P. Bến Nghé, Quận 1, TP. Hồ Chí Minh, Việt Nam</span>
                    <br />
                    <span class="font-weight-bold text-danger">Trung tâm điều hành: </span>
                    <span class="font-mali">144 Nguyễn Đình Chiểu, P. 6, Quận 3, TP. Hồ Chí Minh, Việt Nam</span>
                    <br />
                    <span class="font-weight-bold text-danger">Email: </span>
                    <span class="font-mali">dulich@gmail.com</span>
                    <br />
                    <span class="font-weight-bold text-danger">Số điện thoại </span>
                    <span class="text-success h5">(+84) 977722972</span>

                </div>
                <div>
                    <br />
                    <span class="font-weight-bold text-danger mr-4">Liên hệ với chúng tôi ngay </span>
                    <a class="btn btn-outline-dark btn-social mx-1" 
                       href="https://www.facebook.com/dqlinh0802/"
                       data-aos="fade-up" data-aos-duration="1000">
                        <i class="fab fa-fw fa-facebook-f"></i>
                    </a>
                    <a class="btn btn-outline-dark btn-social mx-1" 
                       href="https://www.instagram.com/d__q__l/"
                       data-aos="fade-up" data-aos-duration="1500">
                        <i class="fab fa-fw fa-instagram"></i>
                    </a>
                    <a class="btn btn-outline-dark btn-social mx-1" 
                       href="#"
                       data-aos="fade-up" data-aos-duration="2000">
                        <i class="fab fa-fw fa-twitter"></i>
                    </a>
                </div>
                <div>
                    <br />
                    <p class="font-weight-bold text-black">Giấy chứng nhận đăng ký doanh nghiệp số: 
                        <span class="ml-1 ma-so">0310119475</span>
                    </p>
                    <p > do 
                        <span class="font-weight-bold text-black">
                            Sở Kế hoạch và Đầu tư thành phố Hồ Chí Minh cấp ngày 12/11/2010
                        </span>
                    </p> 
                    <p class="font-weight-bold text-black">Mã số thuế: 
                        <span class="ml-1 ma-so">0323444575</span>
                    </p>
                </div>
            </div>
            <!-- cot3-->
            <div class="col-5">
                <img src="<c:url value="/images/duLich5.png"/>" alt="alt"  style="width:400px;"/>
            </div>
            <button  id="myBtn" onclick="topFunction()">
                <i class="fas fa-arrow-circle-up"></i>
            </button>
        </div>
    </div>
    <div class="copyright py-4 text-center text-black">
        <div class="container"><span>Bản quyền &copy; 2021 DuLich.com</span></div>
    </div>
</div>

<script>
    var mybutton = document.getElementById("myBtn");

    window.onscroll = function () {
        scrollFunction();
    };
    
    function scrollFunction() {
        if (document.body.scrollTop > 700 || document.documentElement.scrollTop > 700) {
            mybutton.style.display = "block";
        } else {
            mybutton.style.display = "none";
        }
    }

    function topFunction() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    }
</script>
