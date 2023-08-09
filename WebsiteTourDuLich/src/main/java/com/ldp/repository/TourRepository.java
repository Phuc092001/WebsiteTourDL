/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.repository;

import com.ldp.pojos.Tour;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface TourRepository {
    List<Tour> getTours(String kw, int page, int giaTu, int den);
    List<Object[]> getHotTours(int num);
    Tour layTourId(int tourId);
    long slTour();
    boolean xoaTour(int tourId);
    boolean themHoacSua(Tour tour);
}
