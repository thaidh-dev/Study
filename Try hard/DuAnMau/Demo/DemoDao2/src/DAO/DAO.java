package DAO;

import java.sql.*;
import java.util.List;

public interface DAO<T> {
    public abstract List<T> fillToTable();
    public abstract void insert(T t);
    public abstract void update(T t);
    public abstract void delete();
    public abstract ResultSet find(String sql, Object...args);
    public abstract T showDetail();
    public abstract void clear();
}
