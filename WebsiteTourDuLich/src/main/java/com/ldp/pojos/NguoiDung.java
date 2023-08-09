/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "nguoidung")
public class NguoiDung implements Serializable {

    

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String MANAGE = "ROLE_MANAGE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "{nguoidung.errNull}")
    private String ho;
    @NotNull(message = "{nguoidung.errNull}")
    private String ten;
    private String email;
    @NotNull(message = "{nguoidung.errNull}")
    @Size(max=13, message = "{nguoidung.errLen}")
    private String sdt;
    @NotNull(message = "{nguoidung.errNull}")
    private String taiKhoan;
    @NotNull(message = "{nguoidung.errNull}")
    private String matKhau;
    private String anh;
    private String diaChi;
    private String vaiTro;

    @Transient
    private MultipartFile file;

    //truong bth dung tren day th, k luu xuong data
    @Transient
    private String xacThucMatKhau;

    //HoaDon
    @OneToMany(mappedBy = "nguoiDung")//gắn với thuộc tính trong class bên kết nối
    @JsonIgnore// k lay khi truyenlen Json
    private List<HoaDon> hoaDons;
    
    //BinhLuan
    @OneToMany(mappedBy = "nguoiDung")//gắn với thuộc tính trong class bên kết nối
    @JsonIgnore// k lay khi truyenlen Json
    private List<BinhLuan> binhLuans;

    /**
     * @return the binhLuans
     */
    public List<BinhLuan> getBinhLuans() {
        return binhLuans;
    }

    /**
     * @param binhLuans the binhLuans to set
     */
    public void setBinhLuans(List<BinhLuan> binhLuans) {
        this.binhLuans = binhLuans;
    }

    /**
     * @return the xacThucMatKhau
     */
    public String getXacThucMatKhau() {
        return xacThucMatKhau;
    }
/**
     * @return the hoaDons
     */
    public List<HoaDon> getHoaDons() {
        return hoaDons;
    }

    /**
     * @param hoaDons the hoaDons to set
     */
    public void setHoaDons(List<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }
    /**
     * @param xacThucMatKhau the xacThucMatKhau to set
     */
    public void setXacThucMatKhau(String xacThucMatKhau) {
        this.xacThucMatKhau = xacThucMatKhau;
    }

    /**
     * @return the anh
     */
    public String getAnh() {
        return anh;
    }

    /**
     * @param anh the anh to set
     */
    public void setAnh(String anh) {
        this.anh = anh;
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
     * @return the ho
     */
    public String getHo() {
        return ho;
    }

    /**
     * @param ho the ho to set
     */
    public void setHo(String ho) {
        this.ho = ho;
    }

    /**
     * @return the ten
     */
    public String getTen() {
        return ten;
    }

    /**
     * @param ten the ten to set
     */
    public void setTen(String ten) {
        this.ten = ten;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    /**
     * @return the taiKhoan
     */
    public String getTaiKhoan() {
        return taiKhoan;
    }

    /**
     * @param taiKhoan the taiKhoan to set
     */
    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the vaiTro
     */
    public String getVaiTro() {
        return vaiTro;
    }

    /**
     * @param vaiTro the vaiTro to set
     */
    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
