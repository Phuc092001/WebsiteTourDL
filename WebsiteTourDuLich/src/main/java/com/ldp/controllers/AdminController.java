/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.controllers;

import com.ldp.service.ThongKeService;
import com.ldp.utils.PdfDoanhThu;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ACER
 */
@Controller
public class AdminController {

    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/admin/thongKeDoanhThuTour")
    public String thongKeDoanhThuTour(Model model,
            @RequestParam(required = false) Map<String, String> params) {

        Date ngayBD = null;
        try {
            String BD = params.getOrDefault("ngayBD", null);
            if (BD != null) {
                Date date1=new SimpleDateFormat("yyyy-MM").parse(BD);
                ngayBD = date1;
            }

//            String KT = params.getOrDefault("ngayKT", null);
//            if (KT != null) {
//                ngayKT = f.parse(KT);
//            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        
        model.addAttribute("thongKeTour", this.thongKeService.doanhThuTheoTour(ngayBD));

        return "thongKeDoanhThuTour";
    }
    
    @GetMapping("/admin/thongKeSoLuongTour")
    public String thongSoLuongTour(Model model) {

        model.addAttribute("thongKeSoLuongTour", this.thongKeService.soLuongTour());

        return "thongKeSoLuongTour";
    }
    
    @RequestMapping(value = "/report", method=RequestMethod.GET)
    public ModelAndView userListReport(HttpServletRequest req, HttpServletResponse res) {

        String typeReport = req.getParameter("type");


        if(typeReport != null && typeReport.equals("pdf")){
            return new ModelAndView(new PdfDoanhThu(), "list", thongKeService.doanhThuTheoTour(null));
        }

        return new ModelAndView("", "list", thongKeService.doanhThuTheoTour(null));
    }
}
