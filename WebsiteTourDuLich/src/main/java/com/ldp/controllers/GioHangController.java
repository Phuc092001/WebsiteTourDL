/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.controllers;

import com.ldp.pojos.GioHang;
import com.ldp.utils.Utils;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ACER
 */
@Controller
public class GioHangController {
    @GetMapping("/gioHang")
    public String gioHang(Model model, HttpSession session){
        
        // kiem tra có giỏ hàng chưa
        Map<Integer, GioHang> gioHang = (Map<Integer, GioHang>) session.getAttribute("gioHang");
        if(gioHang != null)// có giỏ rồi
            model.addAttribute("gioHangs", gioHang.values());// ds các item bỏ vô giỏ
        else
            model.addAttribute("gioHangs", null);
        
        model.addAttribute("tinhTien", Utils.tinhTien(gioHang));
        
        return "gioHang";
    }
}
