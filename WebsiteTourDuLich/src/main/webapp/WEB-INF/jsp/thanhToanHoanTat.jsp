<%-- 
    Document   : thanhToanHoanTat
    Created on : Aug 9, 2023, 2:54:40 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var time = 5; //How long (in seconds) to countdown
    var page = "<c:url value="/dsTour" />"; //The page to redirect to
    function countDown() {
        time--;
        gett("container").innerHTML = time;
        if (time == 1) {
            window.location = page;
        }
        if(time < 1){
            time++;
        }
    }
    function gett(id) {
        if (document.getElementById)
            return document.getElementById(id);
        if (document.all)
            return document.all.id;
        if (document.layers)
            return document.layers.id;
        if (window.opera)
            return window.opera.id;
    }
    function init() {
        if (gett('container')) {
            setInterval(countDown, 1000);
            gett("container").innerHTML = time;
        } else {
            setTimeout(init, 50);
        }
    }
    document.onload = init();
</script>
<div align="center">
    <h1>Thanh toán thành công</h1>
    <p>Cảm ơn bạn đã tin dùng. Chúc bạn có một chuyến đi vui vẻ.</p>
    <h2>Trở về trang danh sách tour sau <span id="container"></span> second(s)!</h2>
</div>

