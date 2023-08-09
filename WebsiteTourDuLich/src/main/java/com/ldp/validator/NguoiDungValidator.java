/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.validator;

import com.ldp.pojos.NguoiDung;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author ACER
 */
@Component
public class NguoiDungValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return NguoiDung.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NguoiDung n = (NguoiDung) o;
        //rejectValue name nó biet truong nào bị lỗi
        if (n.getTaiKhoan().equals("")) {
            errors.rejectValue("taiKhoan", "nguoidung.errNull");
        }
        if (n.getEmail().equals("")) {
            errors.rejectValue("email", "nguoidung.errNull");
        }
        if (n.getHo().equals("")) {
            errors.rejectValue("ho", "nguoidung.errNull");
        }
        if (n.getTen().equals("")) {
            errors.rejectValue("ten", "nguoidung.errNull");
        }
        if (n.getSdt().equals("")) {
            errors.rejectValue("sdt", "nguoidung.errNull");
        }
        if (n.getMatKhau().equals("")) {
            n.setMatKhau("");
            errors.rejectValue("matKhau", "nguoidung.errNull");
        }
        if (n.getXacThucMatKhau().equals("")) {
            n.setXacThucMatKhau("");
            errors.rejectValue("xacThucMatKhau", "nguoidung.errNull");
        }
        if (!n.getMatKhau().equals(n.getXacThucMatKhau())) {
            errors.rejectValue("xacThucMatKhau", "nguoidung.errMatKhau");
        }
    }

}

