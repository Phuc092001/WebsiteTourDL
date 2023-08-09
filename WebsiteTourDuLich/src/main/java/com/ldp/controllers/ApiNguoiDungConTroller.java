/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.controllers;

import com.ldp.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
public class ApiNguoiDungConTroller {
    @Autowired
    private NguoiDungService nguoiDungService;
    
    @DeleteMapping("/api/nguoiDungs/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void xoaTour(@PathVariable(name = "id") int id) {
        this.nguoiDungService.xoaNguoiDung(id);
    }
}
