/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.repository.impl;

import com.ldp.pojos.ChiTietHoaDon;
import com.ldp.pojos.HoaDon;
import com.ldp.pojos.NguoiDung;
import com.ldp.pojos.Tour;
import com.ldp.repository.NguoiDatTourRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class NguoiDatTourRepositoryImpl implements NguoiDatTourRepository{
     @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Object[]> dsTourDuocDat(String tenTour, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
         
        Root rootT = query.from(Tour.class);
        Root rootH = query.from(HoaDon.class);
        Root rootC = query.from(ChiTietHoaDon.class);
        Root rootN = query.from(NguoiDung.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(rootC.get("tour"), rootT.get("tourId")));
        predicates.add(builder.equal(rootC.get("hoaDon"), rootH.get("id")));
        predicates.add(builder.equal(rootH.get("nguoiDung"), rootN.get("id")));
        
        
        if (tenTour != null && tenTour.trim().isEmpty()) {
            predicates.add(builder.like(rootT.get("tenTour"), String.format("%%%s%%", tenTour)));
        }
        

        query.multiselect(rootH.get("id"), rootN.get("ho"), rootN.get("ten"),
                rootN.get("sdt"), rootH.get("ngayMua"), rootH.get("tongTien"));
        
        query.where(predicates.toArray(new Predicate[] {}));
        query.groupBy(rootH.get("id"));
        query.orderBy(builder.desc(rootH.get("id")));
        Query q = session.createQuery(query);
        int maxPage = 10;
        q.setMaxResults(maxPage);
        //page= 1 thì lấy 10 phần tử đầu 
        q.setFirstResult((page - 1 ) * maxPage);
        return q.getResultList();
    }

    @Override
    public long slTourDuocDat() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("Select Count(*) From HoaDon");
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public List<Object[]> dsChiTietDat(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
         
        Root rootT = query.from(Tour.class);
        Root rootH = query.from(HoaDon.class);
        Root rootC = query.from(ChiTietHoaDon.class);
//        Root rootN = query.from(NguoiDung.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(rootC.get("tour"), rootT.get("tourId")));
        predicates.add(builder.equal(rootC.get("hoaDon"), rootH.get("id")));
        predicates.add(builder.equal(rootC.get("hoaDon"), id));
        


        query.multiselect(rootT.get("tourId"), rootT.get("tenTour"),
                rootC.get("soLuong"), rootC.get("gia"), rootH.get("tongTien"));
        
        query.where(predicates.toArray(new Predicate[] {}));
        query.orderBy(builder.asc(rootH.get("id")));
        
        Query q = session.createQuery(query);

        
        return q.getResultList();
    }
}
