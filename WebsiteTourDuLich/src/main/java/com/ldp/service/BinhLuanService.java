/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.service;

import com.ldp.pojos.BinhLuan;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface BinhLuanService {
    long slBinhLuan(int tourId);
    BinhLuan themBinhLuan(String noiDung, int tourId, int id);
    List<Object[]> layBinhLuansTour2(int tourId, int page);
}
