/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.repository;

import com.ldp.pojos.GioHang;
import java.util.Map;

/**
 *
 * @author ACER
 */
public interface HoaDonRepository {
    boolean themHoaDon(Map<Integer, GioHang> gioHang, int id, String tinhTrang);
}
