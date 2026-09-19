package lk.ijse.pharamacymanagementlayerdsystem.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.pharamacymanagementlayerdsystem.bo.SuperBO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.ProductCategoryDTO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {

    public boolean saveProduct(ProductDTO dto) throws SQLException ,ClassNotFoundException;

    public boolean updateProduct(ProductDTO dto) throws SQLException ,ClassNotFoundException;

    public boolean deleteProduct(long productId) throws SQLException,ClassNotFoundException ;

    public ProductDTO searchProduct(long productId) throws SQLException,ClassNotFoundException;


    ArrayList<ProductCategoryDTO> getAllProductsWithCategory() throws SQLException, ClassNotFoundException;

    public ProductCategoryDTO searchExactProduct(String searchText) throws SQLException ,ClassNotFoundException;


    public ArrayList<ProductDTO> getAllProducts() throws SQLException,ClassNotFoundException ;

    public ObservableList<String> getAllProductsForCombo() throws SQLException ,ClassNotFoundException;

    public long getProductIdFromDisplay(String displayText) throws SQLException, ClassNotFoundException;


    public ObservableList<String> getAllCategoryForCombo() throws SQLException, ClassNotFoundException;

    public String getCatTypeFromDisplay(String displayText) throws SQLException, ClassNotFoundException;

    public long getCatIdFromDisplay(String displayText) throws SQLException, ClassNotFoundException;

}
