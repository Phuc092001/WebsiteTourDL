/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ldp.pojos.NguoiDung;
import com.ldp.repository.NguoiDungRepository;
import com.ldp.service.NguoiDungService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service("userDetailsService")
public class NguoiDungServiceImpl implements NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;

    //xu ly băm password
    @Override
    public boolean themSuaNguoiDung(NguoiDung nguoiDung) {

        try {
            if (nguoiDung.getId() > 0) {
                //sua thong tin
                if (nguoiDung.getFile().getBytes().length == 0) {
                    NguoiDung a = this.nguoiDungRepository.layNguoiDungId(nguoiDung.getId());
                    nguoiDung.setAnh(a.getAnh());
                } else {
                    Map m = this.cloudinary.uploader().upload(nguoiDung.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                    nguoiDung.setAnh((String) m.get("secure_url"));
                }
            } else { //them nguoi dung
                if (nguoiDung.getFile().getBytes().length != 0) {
                    String pass = nguoiDung.getMatKhau();
                    nguoiDung.setMatKhau(this.passwordEncoder.encode(pass));

                    //neu nguoi binh thuong dk thi mac d
                    if (nguoiDung.getVaiTro() == null) {
                        nguoiDung.setVaiTro(NguoiDung.USER);
                    }

                    if (nguoiDung.getFile().getBytes().length != 0) {
                        Map m = this.cloudinary.uploader().upload(nguoiDung.getFile().getBytes(),
                                ObjectUtils.asMap("resource_type", "auto"));

                        nguoiDung.setAnh((String) m.get("secure_url"));
                        nguoiDung.setVaiTro(nguoiDung.getVaiTro());
                    } else {
                        return false;
                    }
                } else {
                    nguoiDung.setAnh("");
                }
            }
            return this.nguoiDungRepository.themSuaNguoiDung(nguoiDung);
        } catch (IOException ex) {
            System.err.println("Đã xảy ra lỗi!!!");
        }
        return false;
    }

    @Override
    public List<NguoiDung> danhSachNguoiDung(String ten) {
        return this.nguoiDungRepository.danhSachNguoiDung(ten);
    }

    //tra ra userDetailsService
    @Override
    public UserDetails loadUserByUsername(String ten) throws UsernameNotFoundException {
        List<NguoiDung> ds = this.danhSachNguoiDung(ten);
        if (ds.isEmpty()) {
            throw new UsernameNotFoundException("Không tồn tại");
        }

        //chi co 1 thang user thoi vi ss equal ma'
        NguoiDung nguoiDung = ds.get(0);

        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(nguoiDung.getVaiTro()));

        return new org.springframework.security.core.userdetails.User(
                nguoiDung.getTaiKhoan(), nguoiDung.getMatKhau(), auth);
    }

    @Override
    public NguoiDung layNguoiDungId(int nguoiDungId) {
        return this.nguoiDungRepository.layNguoiDungId(nguoiDungId);
    }

    @Override
    public List<NguoiDung> dsNguoiDung(String taiKhoan, int page) {
        return this.nguoiDungRepository.dsNguoiDung(taiKhoan, page);
    }

    @Override
    public long slNguoiDung() {
        return this.nguoiDungRepository.slNguoiDung();
    }

    @Override
    public boolean xoaNguoiDung(int id) {
        return this.nguoiDungRepository.xoaNguoiDung(id);
    }

    @Override
    public List<NguoiDung> layNguoiDungTaiKhoan(String taiKhoan) {
        return this.nguoiDungRepository.layNguoiDungTaiKhoan(taiKhoan);
    }

}
