/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.repository.impl;

import com.ldp.pojos.BinhLuan;
import com.ldp.pojos.NguoiDung;
import com.ldp.repository.BinhLuanRepository;
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
public class BinhLuanRepositoryImpl implements BinhLuanRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
  

    @Override
    public long slBinhLuan(int tourId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        Query query = session.createQuery("Select Count(*) From BinhLuan Where tour.id=:id");
        query.setParameter("id", tourId);
        
        return  Long.parseLong(query.getSingleResult().toString());
    }

    @Override
    public BinhLuan themBinhLuan(BinhLuan bl, int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(bl);
            
            return bl;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object[]> layBinhLuansTour2(int tourId, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

        Root rootB = query.from(BinhLuan.class);
        Root rootN = query.from(NguoiDung.class);

        
//        vi nhieu dieu kien ket nen dung List<Predicate>
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(rootB.get("nguoiDung"), rootN.get("id")));
        predicates.add(builder.equal(rootB.get("tour"), tourId));
        query.multiselect(rootB.get("id"), rootB.get("noiDung"),
                rootB.get("ngayBL"), rootN.get("anh"));
        query.where(predicates.toArray(new Predicate[] {}));
        query = query.orderBy(builder.desc(rootB.get("id")));//sap xep
        
        Query q = session.createQuery(query);
        int maxPage = 5;
        q.setMaxResults(maxPage);
        //page= 1 thì lấy 5 phần tử đầu 
        q.setFirstResult((page - 1 ) * maxPage);
        
        return q.getResultList();
        
    }
}
