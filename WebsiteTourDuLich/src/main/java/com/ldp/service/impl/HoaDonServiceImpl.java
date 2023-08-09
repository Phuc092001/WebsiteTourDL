/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.service.impl;

import com.ldp.pojos.GioHang;
import com.ldp.repository.HoaDonRepository;
import com.ldp.service.HoaDonService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;
    
    @Override
    public boolean themHoaDon(Map<Integer, GioHang> gioHang, int id, String tinhTrang) {
        if(gioHang != null)
            return this.hoaDonRepository.themHoaDon(gioHang, id, tinhTrang);
        return false;
    }
}
