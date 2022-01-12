package lab_app.dao;

import javafx.collections.FXCollections;
import lab_app.entity.InventarisEntity;
import lab_app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class InventarisDaoImpl implements daointerfacehibernate<InventarisEntity>{
    @Override
    public int addData(InventarisEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int delData(InventarisEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(data);
        t.commit();
        s.flush();
        s.close();
        return 0;
    }

    @Override
    public int updateData(InventarisEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.update(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public List<InventarisEntity> getAll() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(InventarisEntity.class);
        query.from(InventarisEntity.class);
        List<InventarisEntity> cList = s.createQuery(query).getResultList();
        s.close();
        return FXCollections.observableArrayList(cList);
    }
}
