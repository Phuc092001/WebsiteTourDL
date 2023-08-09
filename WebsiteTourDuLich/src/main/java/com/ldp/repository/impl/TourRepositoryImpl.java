/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.repository.impl;

import com.ldp.pojos.ChiTietHoaDon;
import com.ldp.pojos.Tour;
import com.ldp.repository.TourRepository;
import java.util.ArrayList;
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
public class TourRepositoryImpl implements TourRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Tour> getTours(String kw, int page, int giaTu, int den) {
        Session session = this.sessionFactory.getObject().getCurrentSession();  
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tour> query = builder.createQuery(Tour.class);
        
        Root root = query.from(Tour.class);
        query = query.select(root);
        List<Predicate> predicates = new ArrayList<>();
        
        //%% -> chuyen thanh 1 dau khi xuong duoi
        if(kw != null){
            predicates.add(builder.like(root.get("tenTour").as(String.class),
                    String.format("%%%s%%", kw)));
            
        }
        if(giaTu != -1){
            predicates.add(builder.lessThanOrEqualTo(root.get("gia"), den));
            predicates.add(builder.greaterThanOrEqualTo(root.get("gia"), giaTu));
            query = query.orderBy(builder.asc(root.get("gia")));
        }
        query = query.where(predicates.toArray(new Predicate[]{}));
        Query q = session.createQuery(query); 
        int maxPage = 9;
        q.setMaxResults(maxPage);
        //page= 1 thì lấy 18 phần tử đầu 
        q.setFirstResult((page - 1 ) * maxPage);
        
        return q.getResultList();
    }
    

    

    @Override
    public boolean themHoacSua(Tour tour) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        try{
            if(tour.getTourId() > 0)
                session.update(tour);
            else
                session.save(tour);
            return true;
        } catch (Exception ex){
            System.err.println("Lỗi xảy ra khi thêm hoặc sửa" + ex.getMessage());
            //in ra cac buoc toi dau bi loi
            ex.printStackTrace();
        }
        return false;
        
    }

    @Override
    public Tour layTourId(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Tour.class, id);
    }

    @Override
    public boolean xoaTour(int tourId) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            Tour t = session.get(Tour.class, tourId);
            session.delete(t);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    @Override
    public long slTour() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("Select Count(*) From Tour");
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public List<Object[]> getHotTours(int num) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root rootT = query.from(Tour.class);
        Root rootC = query.from(ChiTietHoaDon.class);
        
        query = query.where(builder.equal(rootC.get("tour"), rootT.get("tourId")));
        query.multiselect(rootT.get("tourId"), rootT.get("tenTour")
                , rootC.get("gia"), rootT.get("anh"), rootT.get("soCho")
                , builder.count(rootT.get("tourId")));
        
        
        query = query.groupBy(rootT.get("tourId"));
        query = query.orderBy(builder.desc(builder.count(rootT.get("tourId"))));
        
        Query q = session.createQuery(query);
        q.setMaxResults(num);
        
        
        return q.getResultList();
    }

}
