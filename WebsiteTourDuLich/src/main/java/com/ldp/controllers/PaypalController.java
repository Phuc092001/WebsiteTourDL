/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.controllers;

import com.ldp.pojos.GioHang;
import com.ldp.service.HoaDonService;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ACER
 */
@Controller
public class PaypalController {
    @Autowired
    private HoaDonService hoaDonService;
    
    @GetMapping("/paypal")
    public String paypal(Model model,
            @RequestParam(required = false) Map<String, String> params) {
        int tongTien = Integer.parseInt(params.getOrDefault("tongTien", "1"));
        model.addAttribute("tongTien", tongTien);
        
        return "paypal";
    }
    
    @GetMapping("/paypal/thanhToanHoanTat")
    public String thanhToanHoanTat() {
        return "thanhToanHoanTat";
    }
    @PostMapping("/api/thanhToanOnline/{id}")
    public HttpStatus thanhToanOnline(HttpSession session,
            @PathVariable(value = "id") int id) {
        String tinhTrang = "Thanh Toán Online";
        if(this.hoaDonService.themHoaDon((Map<Integer, GioHang>) session.getAttribute("gioHang"), id, tinhTrang) == true) {
            session.removeAttribute("gioHang");
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
