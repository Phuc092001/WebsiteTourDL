/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.repository;

import java.util.List;

/**
 *
 * @author ACER
 */
public interface NguoiDatTourRepository {
    List<Object[]> dsTourDuocDat(String tenTour, int page);
    long slTourDuocDat();
    List<Object[]> dsChiTietDat(int id);
}
