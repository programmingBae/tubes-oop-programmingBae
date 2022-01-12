package lab_app.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lab_app.entity.AnggotaStaffEntity;
import lab_app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class AnggotaStaffDaoImpl implements daointerfacehibernate<AnggotaStaffEntity> {
    @Override
    public int addData(AnggotaStaffEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int delData(AnggotaStaffEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(data);
        t.commit();
        s.flush();
        s.close();
        return 0;
    }

    @Override
    public int updateData(AnggotaStaffEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.update(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public ObservableList<AnggotaStaffEntity> getAll() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(AnggotaStaffEntity.class);
        query.from(AnggotaStaffEntity.class);
        List<AnggotaStaffEntity> cList = s.createQuery(query).getResultList();
        s.close();
        return FXCollections.observableArrayList(cList);
    }

    public ObservableList<AnggotaStaffEntity> getAllKoor(){
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(AnggotaStaffEntity.class);
        Root<AnggotaStaffEntity> root =     query.from(AnggotaStaffEntity.class);
        Predicate p  = builder.equal(root.get("jabatan"), "Koordinator");
        List<AnggotaStaffEntity> cList = s.createQuery(query.where(p)).getResultList();
        s.close();
        return FXCollections.observableArrayList(cList);

    }
}
