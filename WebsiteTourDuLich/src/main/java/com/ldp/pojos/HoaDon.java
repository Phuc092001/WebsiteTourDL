/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "hoadon")
public class HoaDon implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")//****
    private int id;
    private BigDecimal tongTien;
    private Date ngayMua;
    private String tinhTrang;

    
    //NguoiDung
    @ManyToOne//mac dinh la eager join vao lay het
    //lazy khi nao goi thi moi join 
    @JoinColumn(name = "idNguoiDung")
    private NguoiDung nguoiDung;
    
    
    
    //ChiTietHoaDon
    @OneToMany(mappedBy = "hoaDon")//gắn với thuộc tính trong class bên kết nối
    @JsonIgnore// k lay khi truyenlen Json
    private List<ChiTietHoaDon> chiTietHoaDons;
    
    
    
    
    
    /**
     * @return the chiTietHoaDons
     */
    public List<ChiTietHoaDon> getChiTietHoaDons() {
        return chiTietHoaDons;
    }

    /**
     * @param chiTietHoaDons the chiTietHoaDons to set
     */
    public void setChiTietHoaDons(List<ChiTietHoaDon> chiTietHoaDons) {
        this.chiTietHoaDons = chiTietHoaDons;
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
     * @return the tongTien
     */
    public BigDecimal getTongTien() {
        return tongTien;
    }

    /**
     * @param tongTien the tongTien to set
     */
    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    /**
     * @return the ngayMua
     */
    public Date getNgayMua() {
        return ngayMua;
    }

    /**
     * @param ngayMua the ngayMua to set
     */
    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public void setNgayMua(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        /**
     * @return the tinhTrang
     */
    public String getTinhTrang() {
        return tinhTrang;
    }

    /**
     * @param tinhTrang the tinhTrang to set
     */
    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }


    
    
}

