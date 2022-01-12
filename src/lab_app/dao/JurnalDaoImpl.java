package lab_app.dao;

import javafx.collections.FXCollections;
import lab_app.entity.AnggotaStaffEntity;
import lab_app.entity.JurnalEntity;
import lab_app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class JurnalDaoImpl implements daointerfacehibernate<JurnalEntity>{
    @Override
    public int addData(JurnalEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int delData(JurnalEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(data);
        t.commit();
        s.flush();
        s.close();
        return 0;
    }

    @Override
    public int updateData(JurnalEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.update(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public List<JurnalEntity> getAll() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(JurnalEntity.class);
        query.from(JurnalEntity.class);
        List<JurnalEntity> cList = s.createQuery(query).getResultList();
        s.close();
        return FXCollections.observableArrayList(cList);
    }
}
