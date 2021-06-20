/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Thanh
 * @param <T>
 */
public interface Inf_DAO<T> {

    public void insert(T t);

    public void update(T t);

    public void delete(Integer ID);

    public List<T> select();
    
    public T findID(Integer ID);
    
    public List<T> selectByKeyword(String Key);
    
    public List<T> select(String sql, Object... args);

    public T readFromResultSet(ResultSet rs);

}
