<%-- 
    Document   : tinTuc
    Created on : Aug 9, 2023, 2:55:40 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="sl1" class="sl1" style="top: 230px; position: fixed; right: 50%; margin-right: 580px; bottom: 0px;">

    <a rel="nofollow" href="#" target="_blank">
        <img border="0" alt="sbobb" 
             src="https://cdn.dribbble.com/users/398490/screenshots/2189858/airplane-for-dribbble.gif" width="300" height="225">
    </a>
</div>  


<div id="sl2" style="top: 230px; position: fixed; left: 50%; margin-left: 580px; bottom: 0px;">
    <a rel="nofollow" href="#" target="_blank">
        <img border="0" alt="sbobb" 
             src="https://i.pinimg.com/originals/b4/4c/66/b44c665c88d2d14d1b4a6904bd133acb.gif" width="300" height="225">
    </a>
    <br>
    <a rel="nofollow" href="<c:url value="/dsTour"/>" target="_blank" >
        <img style="margin-top: 150px;" border="0" alt="sbobb"
             src="https://baoduyentourist.com/uploads/dat-tour.gif" height="100.2" width="331.8">
    </a>					
</div>
<h1 class="text-center font-mali">Tin tức du lịch</h1>
<section id="diemNoiBat">
    <div class="container">

        <h2>Địa điểm nổi bật</h2>
        <div class="row">
            <c:forEach var="tinTuc" items="${scraping}">
                <div class="col-4 my-3 text-black">
                    <a href="${tinTuc.link}">
                        <img class="logo img-fluid" src="${tinTuc.src}" />
                    </a>
                    <div>
                        <a href="${tinTuc.link}" class="d-title ">
                            <p class="font-weight-bold m-0 " >${tinTuc.title}</p>
                        </a>
                        <p class="d-content font-mali">${tinTuc.content}</p>
                        <span class="d-date">${tinTuc.date}</span>
                    </div>
                </div>  
            </c:forEach>
        </div>
    </div>
</section>
<section id="duLichVoiNguoiNoiTieng">
    <div class="container">
        <h2 class="text-white">Du lịch với người nổi tiếng</h2>
        <div class="row">
            <c:forEach var="tinTuc" items="${scrapingNguoiNoiTieng}">
                <div class="col-4 my-3 text-white">
                    <a href="${tinTuc.link}">
                        <img class="logo img-fluid" src="${tinTuc.src}" />
                    </a>
                    <div>
                        <a href="${tinTuc.link}" class="d-title text-white">
                            <p class="font-weight-bold m-0" >${tinTuc.title}</p>
                        </a>
                        <p class="d-content font-mali">${tinTuc.content}</p>
                        <span class="d-date">${tinTuc.date}</span>
                    </div>
                </div>  
            </c:forEach>
        </div>
    </div>
</section>
<section id="video">
    <div class="container">
        <h2 class="text-black">Video</h2>
        <div class="row">
            <c:forEach var="tinTuc" items="${scrapingVideo}">
                <div class="col-4 my-3 text-black">
                    <a href="${tinTuc.link}">
                        <div class="icon_video"></div>
                        <img class="logo img-fluid" src="${tinTuc.src}" />
                    </a>
                    <div>
                        <a href="${tinTuc.link}" class="d-title text-black">
                            <p class="font-weight-bold m-0" >${tinTuc.title}</p>
                        </a>
                        <c:if test="${tinTuc.date != null}">
                            <span class="d-date">${tinTuc.date}</span>
                        </c:if>
                        <c:if test="${tinTuc.date == null}">
                            <span class="d-date">--/--/--, --:--</span>
                        </c:if>
                    </div>
                </div>  
            </c:forEach>
        </div>
    </div>
</section>



