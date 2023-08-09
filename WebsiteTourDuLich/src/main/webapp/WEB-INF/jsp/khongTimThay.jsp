<%-- 
    Document   : khongTimThay
    Created on : Aug 9, 2023, 2:53:48 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="khong-tim-thay">

    <div class="khong-tim-thay">
        <div class="khong-tim-thay-404">
            <h1>Payment Error</h1>
        </div>
        <p class="text-uppercase">${errorMessage}</p>
        <a href="<c:url value="/"/>">Trở về trang chủ</a>
    </div>
</div>
