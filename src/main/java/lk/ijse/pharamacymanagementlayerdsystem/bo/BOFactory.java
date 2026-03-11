package lk.ijse.pharamacymanagementlayerdsystem.bo;

import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.CategoryBO;
import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.ProductBO;
import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory instance;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return instance == null ? instance = new BOFactory() : instance;
    }
    public enum BOTypes{
        CUSTOMER,
        PRODUCT,
        USER,
        SUPPLIER,
        CATEGORY,
        GRN,
        BATCH,
        INVOICE,
        DASHBOARD

    }
    public SuperBO getBO(BOTypes boTypes){
        return switch (boTypes) {
            case CUSTOMER -> new CustomerBOImpl();
            case PRODUCT -> new ProductBOImpl();
            case USER -> new UserBOImpl();
            case SUPPLIER -> new SupplierBOImpl();
            case CATEGORY -> new CategoryBOImpl();
            case GRN -> new GRNBOImpl();
            case BATCH -> new BatchBOImpl();
            case INVOICE -> new InvoiceBOImpl();
            case DASHBOARD -> new DashboardBOImpl();
            default -> null;
        };
    }
}
