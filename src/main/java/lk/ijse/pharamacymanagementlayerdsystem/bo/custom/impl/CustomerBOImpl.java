package lk.ijse.pharamacymanagementlayerdsystem.bo.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.CustomerBO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.DAOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.CustomerDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.CustomerDTO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        for(Customer customer : customers){
            customerDTOS.add(new CustomerDTO(customer.getCustomerId(),customer.getCustomerName(),
                    customer.getCustomerAddress(),customer.getCustomerContactNumber(),customer.getCustomerEmail()));
        }
        return customerDTOS;
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getCustomerId(),customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),customerDTO.getCustomerContactNumber(),customerDTO.getCustomerEmail()));
    }

    @Override
    public CustomerDTO searchCustomer(Long customerId) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(String.valueOf(customerId));
        return new CustomerDTO(customer.getCustomerId(),customer.getCustomerName(),customer.getCustomerAddress()
                ,customer.getCustomerContactNumber(),customer.getCustomerEmail());
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCustomerId(),customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),customerDTO.getCustomerContactNumber(),customerDTO.getCustomerEmail()));
    }

    @Override
    public boolean deleteCustomer(Long customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(String.valueOf(customerId));
    }

    @Override
    public long getCustomerIdByName(String name) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerIdByName(name);
    }
}
