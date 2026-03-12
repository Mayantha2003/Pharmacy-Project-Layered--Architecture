package lk.ijse.pharamacymanagementlayerdsystem.dao;

import com.mysql.cj.Query;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.CategoryDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory instance;
    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return instance == null ? instance =  new DAOFactory():instance;
    }
    public enum DAOTypes{
        CUSTOMER,
        PRODUCT,
        USER,
        SUPPLIER,
        CATEGORY,
        QUERY,
        GRN,
        GRNDETAILS,
        BATCH,
        INVOICE,
        INVOICEDETAIL,
        INVOICEPAYMENT,


    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        return switch (daoTypes) {
            case CUSTOMER -> new CustomerDAOImpl();
            case PRODUCT -> new ProductDAOImpl();
            case USER -> new UserDAOImpl();
            case SUPPLIER -> new SupplierDAOImpl();
            case CATEGORY -> new CategoryDAOImpl();
            case QUERY -> new QueryDAOImpl();
            case GRN -> new GRNDAOImpl();
            case GRNDETAILS -> new GRNDetailDAOImpl();
            case BATCH -> new BatchDAOImpl();
            case INVOICE -> new InvoiceDAOImpl();
            case INVOICEDETAIL -> new InvoiceDetailDAOImpl();
            case INVOICEPAYMENT -> new InvoicePaymentDAOImpl();
            default -> null;
        };
    }
}
