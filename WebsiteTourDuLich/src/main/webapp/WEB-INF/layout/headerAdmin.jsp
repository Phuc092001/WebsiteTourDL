<%-- 
    Document   : headerAdmin
    Created on : Aug 9, 2023, 3:10:28 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
    <div class="container justify-content-start">
        <a class="navbar-brand" href="<c:url value="/" />">
            <img class="logo" src="<c:url value="/images/logoText.png"/>"
                 alt="Logo" style="width:100px;">
        </a>
        <ul class="navbar-nav">
            <li class="nav-item">
                <div class="nav-link p-0">
                    <i class="fas fa-chevron-left" style="font-size: 20px;"></i> 
                    <a href="<c:url value="/" />">Trở về trang chủ</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
