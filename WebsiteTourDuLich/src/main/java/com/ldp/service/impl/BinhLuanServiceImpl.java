/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.service.impl;

import com.ldp.pojos.BinhLuan;
import com.ldp.pojos.NguoiDung;
import com.ldp.pojos.Tour;
import com.ldp.repository.BinhLuanRepository;
import com.ldp.repository.NguoiDungRepository;
import com.ldp.repository.TourRepository;
import com.ldp.service.BinhLuanService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class BinhLuanServiceImpl implements BinhLuanService {
    @Autowired
    private BinhLuanRepository binhLuanRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    
  
    @Override
    public long slBinhLuan(int tourId) {
        return this.binhLuanRepository.slBinhLuan(tourId);
    }

    @Override
    public BinhLuan themBinhLuan(String noiDung, int tourId, int id) {
        Tour t = this.tourRepository.layTourId(tourId);
        NguoiDung n = this.nguoiDungRepository.layNguoiDungId(id);
    
        BinhLuan b = new BinhLuan();
        if(noiDung == "" || noiDung == null)
            b.setNoiDung(".");
        else
            b.setNoiDung(noiDung);
        b.setNguoiDung(n);
        b.setTour(t);
        
        b.setNgayBL(new Date());
            
        return this.binhLuanRepository.themBinhLuan(b, id);
    }

    @Override
    public List<Object[]> layBinhLuansTour2(int tourId, int page) {
        return this.binhLuanRepository.layBinhLuansTour2(tourId, page);
    }
}
