/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.controllers;

import com.ldp.pojos.BinhLuan;
import com.ldp.service.BinhLuanService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
public class ApiBinhLuanController {
    @Autowired
    private BinhLuanService binhLuanService;
    
    @PostMapping(path = "/api/themBinhLuan/{id}", produces = {
        MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BinhLuan> themBinhLuan(@RequestBody Map<String, String> params,
            @PathVariable(value = "id") int id) {
        try {
            String noiDung = params.get("noiDung");
            int tourId = Integer.parseInt(params.get("tourId"));
            
            BinhLuan b = this.binhLuanService.themBinhLuan(noiDung, tourId, id);
            
            return new ResponseEntity<>(b, HttpStatus.CREATED);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
}
