package lk.ijse.pharamacymanagementlayerdsystem.dao.custom;

import lk.ijse.pharamacymanagementlayerdsystem.dao.SuperDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Batch;
import lk.ijse.pharamacymanagementlayerdsystem.entity.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface QueryDAO extends SuperDAO {

    ArrayList<ProductCategory> getAllProductsWithCategoryName() throws SQLException, ClassNotFoundException;

    public ProductCategory searchExactProduct(String searchText) throws SQLException, ClassNotFoundException;


    //For Batches Join Queries
    public List<String> searchAvailableBatches(String product) throws SQLException, ClassNotFoundException;

    public Batch getBatchByDisplay(String display) throws SQLException, ClassNotFoundException;

    public List<String> getProductSuggestionsStartingWith(String letter) throws SQLException, ClassNotFoundException;



    //For DashBoard Join Queries
    public double getTotalProfit(String filter) throws SQLException, ClassNotFoundException;

    public int getLowStockCount() throws SQLException, ClassNotFoundException;

    public ResultSet getExpiredMedicines() throws SQLException, ClassNotFoundException;

    public ResultSet getLowStockMedicines() throws SQLException, ClassNotFoundException;

}
