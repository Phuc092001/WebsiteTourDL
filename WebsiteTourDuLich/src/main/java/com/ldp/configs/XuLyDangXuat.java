/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.configs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 *
 * @author ACER
 */
public class XuLyDangXuat implements LogoutSuccessHandler {

   @Override
   public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws  IOException, ServletException {
       request.getSession().removeAttribute("nguoiDungDangNhap");
        
        response.sendRedirect("/WebsiteTourDuLich/dangNhap");
   }
    
}
