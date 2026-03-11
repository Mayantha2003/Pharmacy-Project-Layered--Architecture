package lk.ijse.pharamacymanagementlayerdsystem.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{

    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException ;

    public boolean save(T customDTO) throws SQLException, ClassNotFoundException ;

    public boolean update(T customDTO) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public T search(String id) throws SQLException, ClassNotFoundException ;
}
