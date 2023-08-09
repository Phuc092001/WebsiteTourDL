/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.repository.impl;

import com.ldp.pojos.ChiTietHoaDon;
import com.ldp.pojos.GioHang;
import com.ldp.pojos.HoaDon;
import com.ldp.pojos.NguoiDung;
import com.ldp.pojos.Tour;
import com.ldp.repository.HoaDonRepository;
import com.ldp.repository.NguoiDungRepository;
import com.ldp.repository.TourRepository;
import com.ldp.utils.Utils;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ACER
 */
@Repository
public class HoaDonRepositoryImpl implements HoaDonRepository {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private TourRepository tourRepository;

    @Override
    //do cap nhat nhieu giao tac
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean themHoaDon(Map<Integer, GioHang> gioHang, int id, String tinhTrang) {
        try {
            Session session = sessionFactory.getObject().getCurrentSession();

            
            
            HoaDon hoaDon = new HoaDon();
            hoaDon.setNguoiDung(this.nguoiDungRepository.layNguoiDungId(id));

            hoaDon.setNgayMua(new Date());
            hoaDon.setTinhTrang(tinhTrang);

            Map<String, String> tinhTien = Utils.tinhTien(gioHang);
            hoaDon.setTongTien(BigDecimal.valueOf(Long.parseLong(tinhTien.get("tongTien"))));

            session.save(hoaDon);

            
            for (GioHang g: gioHang.values()) {
                 ChiTietHoaDon c = new ChiTietHoaDon();
                c.setGia(g.getGia());
                c.setSoLuong(g.getSoLuong());
                c.setHoaDon(hoaDon);
                c.setTour(this.tourRepository.layTourId(g.getTourId()));

                Tour tour = this.tourRepository.layTourId(g.getTourId());
                tour.setSoCho(tour.getSoCho() - g.getSoLuong());
                session.save(tour); 
                
                session.save(c);
            }
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
