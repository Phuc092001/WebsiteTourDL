/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.service.impl;

import com.ldp.pojos.Tour;
import com.ldp.repository.ThongKeRepository;
import com.ldp.service.ThongKeService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class ThongKeServiceImpl implements ThongKeService{

    @Autowired
    private ThongKeRepository thongKeRepository;
    
    
    @Override
    public List<Object[]> doanhThuTheoTour(Date ngayBD) {
        return this.thongKeRepository.doanhThuTheoTour(ngayBD);
    }

    @Override
    public List<Tour> soLuongTour() {
        return this.thongKeRepository.soLuongTour();
    }
    
}
