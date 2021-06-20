package DAO;

import java.sql.ResultSet;
import java.util.List;

public interface Info_DAO<T> {
    public List<T> select(String sql, Object...args);
    public T readFromRS(ResultSet rs);
}
