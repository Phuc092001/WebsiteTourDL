/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ldp.pojos.Tour;
import com.ldp.repository.TourRepository;
import com.ldp.service.TourService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Tour> getTours(String kw, int page, int giaTu, int den) {

        return this.tourRepository.getTours(kw, page, giaTu, den);
    }

    @Override
    public long slTour() {
        return this.tourRepository.slTour();
    }


    @Override
    public boolean themHoacSua(Tour tour) {
        try {
            if (tour.getTourId() > 0) {   //sua tour
                if (tour.getFile().getBytes().length == 0) {
                    Tour a = this.tourRepository.layTourId(tour.getTourId());
                    tour.setAnh(a.getAnh());
                }else{
                    Map m = this.cloudinary.uploader().upload(tour.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));

                    tour.setAnh((String) m.get("secure_url"));
                }
            } else {    //them
                if (tour.getFile().getBytes().length != 0) {
                    Map m = this.cloudinary.uploader().upload(tour.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));

                    tour.setAnh((String) m.get("secure_url"));
                } else {
                    tour.setAnh("");
                }
            }
            return this.tourRepository.themHoacSua(tour);
        } catch (IOException ex) {
            System.err.println("Đã bị lỗi!!!");
        }
        return false;
    }

    @Override
    public Tour layTourId(int tourId) {
        return this.tourRepository.layTourId(tourId);
    }

    @Override
    public boolean xoaTour(int tourId) {
        return this.tourRepository.xoaTour(tourId);
    }

    @Override
    public List<Object[]> getHotTours(int num) {
       return this.tourRepository.getHotTours(num);
    }


}

