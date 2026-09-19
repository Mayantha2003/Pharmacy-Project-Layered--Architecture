package lk.ijse.pharamacymanagementlayerdsystem.dao.custom;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.CrudDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {

    public long getCustomerIdByName(String name) throws SQLException, ClassNotFoundException;

    public int getCustomerCount() throws SQLException, ClassNotFoundException ;

}
