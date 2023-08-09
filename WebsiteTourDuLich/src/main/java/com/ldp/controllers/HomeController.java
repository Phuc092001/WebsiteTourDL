/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.controllers;

import com.ldp.pojos.GioHang;
import com.ldp.service.NguoiDungService;
import com.ldp.service.TourService;
import com.ldp.utils.Utils;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author ACER
 */
@Controller
@ControllerAdvice
public class HomeController {

    private final int MAX_HOT_TOUR = 6;
    private final String PAGE_BAT_DAU = "1";
    private final String PAGE_KET_THUC = "-1";

    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private TourService tourService;

    @RequestMapping("/")
    public String index(Model model) {

        return "index";
    }

    @RequestMapping("/dsTour")
    public String dsTour(Model model,
            @RequestParam(required = false) Map<String, String> params) {

        //co thì lấy k có thì lấy 1
        int page = Integer.parseInt(params.getOrDefault("page", PAGE_BAT_DAU));
        int giaTu = Integer.parseInt(params.getOrDefault("tu", PAGE_KET_THUC));
        int den = Integer.parseInt(params.getOrDefault("den", PAGE_KET_THUC));
        model.addAttribute("tours", this.tourService.getTours(params.get("kw"), page, giaTu, den));
        model.addAttribute("slTour", this.tourService.slTour());

        model.addAttribute("hotTours", this.tourService.getHotTours(MAX_HOT_TOUR));
        
        return "dsTour";
    }

    //dung chung
    //phai ep kieu 
    @ModelAttribute
    public void dungChung(Model model, HttpSession session) {
        model.addAttribute("demSLTour", Utils.demSLTour((Map<Integer, GioHang>) session.getAttribute("gioHang")));
        model.addAttribute("nguoiDungDangNhap", session.getAttribute("nguoiDungDangNhap"));
        model.addAttribute("scraping", Utils.Scraping("https://dulichvietnam.com.vn/cam-nang-trong-nuoc.html"));
        model.addAttribute("scrapingNguoiNoiTieng", Utils.Scraping("https://dulichvietnam.com.vn/sao-va-du-lich.html"));
        model.addAttribute("scrapingVideo", Utils.ScrapingVideo("https://dulichvietnam.com.vn/videos.html"));
    }

}
