/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.controllers;

import com.ldp.pojos.GioHang;
import com.ldp.pojos.Tour;
import com.ldp.service.HoaDonService;
import com.ldp.service.TourService;
import com.ldp.utils.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
public class ApiGioHangController {
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private TourService tourService;

    @GetMapping("/api/tours")
    public ResponseEntity<List<Tour>> dsTours() {
        List<Tour> tours = this.tourService.getTours("", 1, -1, -1);

        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    //phien lam viec, no khac truy van hibernate
    @PostMapping("/api/gioHang")
    public int themVaoGio(@RequestBody GioHang params,
            HttpSession session) {
        Map<Integer, GioHang> gioHang = (Map<Integer, GioHang>) session.getAttribute("gioHang");
        //neu chua co gio hang thi tao mới
        int tourId = params.getTourId();
        Tour tour = this.tourService.layTourId(tourId);
        if (gioHang == null) {
            gioHang = new HashMap<>();
        }

        
        
        if (gioHang.containsKey(tourId) == true) {
            //neu co tour trong gio thi
            GioHang g = gioHang.get(tourId);
            g.setSoLuong(g.getSoLuong() + 1);
            
            g.setSoCho(tour.getSoCho());
        } else {
            //neu khong co tour trong gio
            gioHang.put(tourId, params);
            
        }

        session.setAttribute("gioHang", gioHang);
        return Utils.demSLTour(gioHang);

    }

    @PutMapping("/api/gioHang")
    public ResponseEntity<Map<String, String>> capNhapGioHang(@RequestBody GioHang params, HttpSession session) {
        Map<Integer, GioHang> gioHang = (Map<Integer, GioHang>) session.getAttribute("gioHang");
        if (gioHang == null) {
            gioHang = new HashMap<>();
        }

        int tourId = params.getTourId();

        if (gioHang.containsKey(tourId) == true) {
            //neu co tour trong gio thi
            GioHang g = gioHang.get(tourId);
            g.setSoLuong(params.getSoLuong());
        }
        session.setAttribute("gioHang", gioHang);
        
        
        return new ResponseEntity<>(Utils.tinhTien(gioHang), HttpStatus.OK);
    }
    
    @DeleteMapping("/api/gioHang/{tourId}")
    public ResponseEntity<Map<String, String>> xoaTourTrongGio(HttpSession session, 
            @PathVariable(value = "tourId") int tourId){
        Map<Integer, GioHang> gioHang = (Map<Integer, GioHang>) session.getAttribute("gioHang");
        if (gioHang != null && gioHang.containsKey(tourId)) {
            gioHang.remove(tourId);
            
            session.setAttribute("gioHang", gioHang);
        }
        
        return new ResponseEntity<>(Utils.tinhTien(gioHang), HttpStatus.OK);
    }
    
    
    @PostMapping("/api/thanhToan/{id}")
    public HttpStatus thanhToan(HttpSession session,
            @PathVariable(value = "id") int id){
        String tinhTrang = "Thanh toán trực tiếp";
        if(this.hoaDonService.themHoaDon((Map<Integer, GioHang>) session.getAttribute("gioHang"), id, tinhTrang) == true) {
            session.removeAttribute("gioHang");
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
        
    }
}
