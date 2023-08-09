/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.configs;

import com.ldp.pojos.NguoiDung;
import com.ldp.service.NguoiDungService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.util.List;

/**
 *
 * @author ACER
 */
public class XuLyDangNhapThanhCong implements AuthenticationSuccessHandler{

   @Autowired
   private NguoiDungService userDetailsService;
   
   @Override
   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws  IOException, ServletException {
       NguoiDung n = this.userDetailsService.danhSachNguoiDung(a.getName()).get(0);
       request.getSession().setAttribute("nguoiDungDangNhap", n);
       
       response.sendRedirect("/WebsiteTourDuLich");
   }
    
}
