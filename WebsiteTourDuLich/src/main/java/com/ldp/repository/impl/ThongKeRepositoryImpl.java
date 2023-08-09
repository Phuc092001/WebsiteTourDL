/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.repository.impl;

import com.ldp.pojos.ChiTietHoaDon;
import com.ldp.pojos.HoaDon;
import com.ldp.pojos.Tour;
import com.ldp.repository.ThongKeRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class ThongKeRepositoryImpl implements ThongKeRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Object[]> doanhThuTheoTour(Date ngayBD) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

        Root rootT = query.from(Tour.class);
        Root rootH = query.from(HoaDon.class);
        Root rootC = query.from(ChiTietHoaDon.class);

//        vi nhieu dieu kien ket nen dung List<Predicate>
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(rootC.get("tour"), rootT.get("tourId")));
        predicates.add(builder.equal(rootC.get("hoaDon"), rootH.get("id")));

//        dung prod để sum
        query.multiselect(rootT.get("tourId"), rootT.get("tenTour"),
                builder.sum(builder.prod(rootC.get("soLuong"), rootC.get("gia"))));

        if (ngayBD != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(ngayBD);
            int month = cal.get(Calendar.MONTH); //cái này tháng bắt đầu từ 0
            int year = cal.get(Calendar.YEAR);
            predicates.add(builder.equal(builder.function("YEAR", Integer.class, rootH.get("ngayMua")), year));
            predicates.add(builder.equal(builder.function("MONTH", Integer.class, rootH.get("ngayMua")), month+1));
        }

//        ket xong thi group lai theo tung thang
        query = query.where(predicates.toArray(new Predicate[]{}));
        query = query.groupBy(rootT.get("tourId"));
        query = query.orderBy(builder.asc(rootT.get("tourId")));

        Query q = session.createQuery(query);

        return q.getResultList();
    }

    @Override
    public List<Tour> soLuongTour() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tour> query = builder.createQuery(Tour.class);

        Root rootT = query.from(Tour.class);
        query = query.select(rootT);
        query = query.orderBy(builder.desc(rootT.get("soCho")));
        Query q = session.createQuery(query); 
        int maxPage = 10;
        q.setMaxResults(maxPage);
        q.setFirstResult(maxPage);

        return q.getResultList();
    }
}
