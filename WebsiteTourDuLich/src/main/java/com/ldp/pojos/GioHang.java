/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.pojos;

import java.math.BigDecimal;

/**
 *
 * @author ACER
 */
public class GioHang {
    private int tourId;
    private String tenTour;
    private BigDecimal gia;
    private int soLuong;
    private int soCho;

    
    
    /**
     * @return the soCho
     */
    public int getSoCho() {
        return soCho;
    }

    /**
     * @param soCho the soCho to set
     */
    public void setSoCho(int soCho) {
        this.soCho = soCho;
    }
    /**
     * @return the tourId
     */
    public int getTourId() {
        return tourId;
    }

    /**
     * @param tourId the tourId to set
     */
    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    /**
     * @return the tenTour
     */
    public String getTenTour() {
        return tenTour;
    }

    /**
     * @param tenTour the tenTour to set
     */
    public void setTenTour(String tenTour) {
        this.tenTour = tenTour;
    }

    /**
     * @return the gia
     */
    public BigDecimal getGia() {
        return gia;
    }

    /**
     * @param gia the gia to set
     */
    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
