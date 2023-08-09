/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.pojos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "binhluan")
public class BinhLuan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")//****
    private int id;
    private String noiDung;
    private Date ngayBL;

    
    @ManyToOne(fetch = FetchType.LAZY)//mac dinh la eager join vao lay het
    //lazy khi nao goi thi moi join 
    @JoinColumn(name = "idTour")
    private Tour tour;
    
    
    @ManyToOne(fetch = FetchType.EAGER)//mac dinh la eager join vao lay het
    //lazy khi nao goi thi moi join 
    @JoinColumn(name = "idNguoiDung")
    private NguoiDung nguoiDung;
    
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the noiDung
     */
    public String getNoiDung() {
        return noiDung;
    }

    /**
     * @param noiDung the noiDung to set
     */
    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    /**
     * @return the ngayBL
     */
    public Date getNgayBL() {
        return ngayBL;
    }

    /**
     * @param ngayBL the ngayBL to set
     */
    public void setNgayBL(Date ngayBL) {
        this.ngayBL = ngayBL;
    }

    /**
     * @return the tour
     */
    public Tour getTour() {
        return tour;
    }

    /**
     * @param tour the tour to set
     */
    public void setTour(Tour tour) {
        this.tour = tour;
    }

    /**
     * @return the nguoiDung
     */
    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    /**
     * @param nguoiDung the nguoiDung to set
     */
    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
}
