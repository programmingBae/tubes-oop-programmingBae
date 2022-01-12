package lab_app.dao;

import javafx.collections.FXCollections;
import lab_app.entity.AnggotaStaffEntity;
import lab_app.entity.LaboratoriumEntity;
import lab_app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class LabDaoImpl implements daointerfacehibernate<LaboratoriumEntity>{
    @Override
    public int addData(LaboratoriumEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int delData(LaboratoriumEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(data);
        t.commit();
        s.flush();
        s.close();
        return 0;
    }

    @Override
    public int updateData(LaboratoriumEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.update(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public List<LaboratoriumEntity> getAll() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(LaboratoriumEntity.class);
        query.from(LaboratoriumEntity.class);
        List<LaboratoriumEntity> cList = s.createQuery(query).getResultList();
        s.close();
        return FXCollections.observableArrayList(cList);
    }


}
