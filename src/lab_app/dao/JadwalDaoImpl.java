package lab_app.dao;

import javafx.collections.FXCollections;
import lab_app.entity.InventarisEntity;
import lab_app.entity.JadwalMaintenanceEntity;
import lab_app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class JadwalDaoImpl implements daointerfacehibernate<JadwalMaintenanceEntity> {
    @Override
    public int addData(JadwalMaintenanceEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int delData(JadwalMaintenanceEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(data);
        t.commit();
        s.flush();
        s.close();
        return 0;
    }

    @Override
    public int updateData(JadwalMaintenanceEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.update(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public List<JadwalMaintenanceEntity> getAll() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(JadwalMaintenanceEntity.class);
        query.from(JadwalMaintenanceEntity.class);
        List<JadwalMaintenanceEntity> cList = s.createQuery(query).getResultList();
        s.close();
        return FXCollections.observableArrayList(cList);
    }
}
