/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.controllers;

import com.ldp.pojos.NguoiDung;
import com.ldp.service.NguoiDungService;
import com.ldp.validator.NguoiDungValidator;
import com.ldp.validator.WebAppValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author ACER
 */
@Controller
public class NguoiDungController {

    @Autowired
    private WebAppValidator nguoiDungValidator;

    @Autowired
    private NguoiDungService userDetailsService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(nguoiDungValidator);
    }

    @GetMapping("/dangNhap")
    public String dangNhap() {
        return "dangNhap";
    }

    @GetMapping("/dangKy")
    public String dangKyView(Model model,
            @RequestParam(name = "id", defaultValue = "0") int id) {
        if (id > 0) {
            model.addAttribute("nguoiDung", this.userDetailsService.layNguoiDungId(id));
        } else {
            model.addAttribute("nguoiDung", new NguoiDung());
        }
        return "dangKy";
    }

    @PostMapping("/dangKy")
    public String dangKy(Model model,
            @ModelAttribute(value = "nguoiDung") @Valid NguoiDung nguoiDung,
            BindingResult result) {
        
        
        if (!result.hasErrors()) {
            String err = "";
            if (this.userDetailsService.themSuaNguoiDung(nguoiDung) == true) {
                return "redirect:/dangNhap";
            } else {
                err = "Chưa có ảnh đại diện";
                nguoiDung.setMatKhau("");
                nguoiDung.setXacThucMatKhau("");
                model.addAttribute("err", err);
            }
        } 
        
        return "dangKy";

    }

}

