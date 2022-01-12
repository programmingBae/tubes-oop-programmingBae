package lab_app.dao;

import java.util.List;

public interface daointerfacehibernate<T>{

    public int addData(T data);
    public int delData(T data);
    public int updateData(T data);
    List<T> getAll();
}
