package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.CustomerDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rs = CRUDUtil.execute("SELECT * FROM Customers");
        ArrayList<Customer> customerList = new ArrayList<>();

        while (rs.next()) {
            Long cusId = rs.getLong("customer_id");
            String cusName = rs.getString("name");
            String cusAddress = rs.getString("address");
            String cusContact = rs.getString("contact");
            String cusEmail = rs.getString("email");

            Customer entity = new Customer(cusId,cusName,cusAddress,cusContact,cusEmail);
            customerList.add(entity);
        }
        return customerList;
    }

    @Override
    public boolean save(Customer customerDTO) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "INSERT INTO Customers (name, address, contact, email) VALUES (?, ?, ?, ?)",
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerContactNumber(),
                customerDTO.getCustomerEmail()
        );
    }

    @Override
    public boolean update(Customer customerDTO) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "UPDATE Customers SET name = ?, address = ?, contact = ?, email = ? WHERE customer_id = ?",
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerContactNumber(),
                customerDTO.getCustomerEmail(),
                customerDTO.getCustomerId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Customers WHERE customer_id = ?",id);
    }

    @Override
    public Customer search(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM Customers WHERE customer_id = ?", customerId);

        if(rs.next()){
            return new Customer(
                    rs.getLong("customer_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("contact"),
                    rs.getString("email")
            );
        }
        return null;
    }

    @Override
    public long getCustomerIdByName(String name) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT customer_id FROM Customers WHERE name = ?", name);

        if (rs.next()) {
            return rs.getLong("customer_id");
        }
        return 0;
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
            ResultSet rs = CRUDUtil.execute("SELECT COUNT(*) FROM Customers");
            return rs.next() ? rs.getInt(1) : 0;
    }
}
