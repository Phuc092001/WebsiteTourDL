/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.repository.impl;

import com.ldp.pojos.NguoiDung;
import com.ldp.pojos.Tour;
import com.ldp.repository.NguoiDungRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ACER
 */
@Repository
@Transactional
public class NguoiDungRepositoryImpl implements NguoiDungRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    

    
    @Override
    public boolean themSuaNguoiDung(NguoiDung nguoiDung) {
        Session session = sessionFactory.getObject().getCurrentSession();
        
        try{
            if(nguoiDung.getId() > 0)
                session.update(nguoiDung);
            else
                session.save(nguoiDung);
            
            return true;
        } catch (Exception ex){
            System.err.println("Error " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public List<NguoiDung> danhSachNguoiDung(String taiKhoan) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NguoiDung> query = builder.createQuery(NguoiDung.class);
        Root root = query.from(NguoiDung.class);
        query = query.select(root);
        
        if(!taiKhoan.isEmpty()){
            Predicate p = builder.equal(root.get("taiKhoan").as(String.class), taiKhoan.trim());
            
            query = query.where(p);
        }
        
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public NguoiDung layNguoiDungId(int nguoiDungId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(NguoiDung.class, nguoiDungId);
    }

    @Override
    public List<NguoiDung> dsNguoiDung(String taiKhoan, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();  
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NguoiDung> query = builder.createQuery(NguoiDung.class);
        Root root = query.from(NguoiDung.class);
        query = query.select(root);
        
        
        //%% -> chuyen thanh 1 dau khi xuong duoi
        if(taiKhoan != null){
            Predicate p = builder.like(root.get("taiKhoan").as(String.class),
                    String.format("%%%s%%", taiKhoan));
            query = query.where(p);
        }
        
        Query q = session.createQuery(query); 
        int maxPage = 6;
        q.setMaxResults(maxPage);
        //page= 1 thì lấy 6 phần tử đầu 
        q.setFirstResult((page - 1 ) * maxPage);
        
        return q.getResultList();
    }

    @Override
    public long slNguoiDung() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("Select Count(*) From NguoiDung");
        return Long.parseLong(q.getSingleResult().toString());        
    }

    @Override
    public boolean xoaNguoiDung(int id) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            NguoiDung t = session.get(NguoiDung.class, id);
            session.delete(t);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public List<NguoiDung> layNguoiDungTaiKhoan(String taiKhoan) {
        Session session = this.sessionFactory.getObject().getCurrentSession();  
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NguoiDung> query = builder.createQuery(NguoiDung.class);
        Root root = query.from(NguoiDung.class);
        query = query.select(root);
        
        
        //%% -> chuyen thanh 1 dau khi xuong duoi
        if(taiKhoan != null){
            Predicate p = builder.like(root.get("taiKhoan").as(String.class),
                    String.format("%%%s%%", taiKhoan));
            query = query.where(p);
        }
        
        Query q = session.createQuery(query); 
        
        return q.getResultList();
    }

}
