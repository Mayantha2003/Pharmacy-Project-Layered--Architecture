package lk.ijse.pharamacymanagementlayerdsystem.bo.custom;

import lk.ijse.pharamacymanagementlayerdsystem.bo.SuperBO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public CustomerDTO searchCustomer(Long customerId) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(Long customerId) throws SQLException, ClassNotFoundException;

    public long getCustomerIdByName(String name) throws SQLException, ClassNotFoundException;
}
