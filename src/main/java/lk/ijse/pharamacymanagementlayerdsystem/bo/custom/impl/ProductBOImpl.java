package lk.ijse.pharamacymanagementlayerdsystem.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.ProductBO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.DAOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.CategoryDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.ProductDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.QueryDAO;

import lk.ijse.pharamacymanagementlayerdsystem.dto.ProductCategoryDTO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.ProductDTO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Product;
import lk.ijse.pharamacymanagementlayerdsystem.entity.ProductCategory;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO{

    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CATEGORY);

    @Override
    public boolean saveProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.save(new Product(dto.getProductId(), dto.getCode(), dto.getName(), dto.getPackSize(),
                dto.getCatId(), dto.getGenericName(), dto.getStrength(), dto.getDosageForm(), dto.getCompany()));
    }

    @Override
    public boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(dto.getProductId(), dto.getCode(), dto.getName(), dto.getPackSize(),
                dto.getCatId(), dto.getGenericName(), dto.getStrength(), dto.getDosageForm(), dto.getCompany()));
    }

    @Override
    public boolean deleteProduct(long productId) throws SQLException, ClassNotFoundException {
        return productDAO.delete(String.valueOf(productId));    }

    @Override
    public ProductDTO searchProduct(long productId) throws SQLException, ClassNotFoundException {
        Product p = productDAO.search(String.valueOf(productId));
        return new ProductDTO(p.getProductId(), p.getCode(), p.getName(), p.getPackSize(),
                p.getCatId(), p.getGenericName(), p.getStrength(), p.getDosageForm(), p.getCompany());
    }


    @Override
    public ArrayList<ProductCategoryDTO> getAllProductsWithCategory() throws SQLException, ClassNotFoundException {
        ArrayList<ProductCategory> entities = queryDAO.getAllProductsWithCategoryName();
        ArrayList<ProductCategoryDTO> dtos = new ArrayList<>();

        for (ProductCategory entity : entities) {
            dtos.add(new ProductCategoryDTO(
                    entity.getProductId(), entity.getCode(), entity.getName(),
                    entity.getPackSize(), entity.getCatId(), entity.getGenericName(),
                    entity.getStrength(), entity.getDosageForm(), entity.getCompany(),
                    entity.getCategoryName()
            ));
        }
        return dtos;
    }

    @Override
    public ProductCategoryDTO searchExactProduct(String searchText) throws SQLException, ClassNotFoundException {
        ProductCategory entity = queryDAO.searchExactProduct(searchText);
        if (entity == null) {
            return null;
        }
        return new ProductCategoryDTO(
                entity.getProductId(), entity.getCode(), entity.getName(),
                entity.getPackSize(), entity.getCatId(), entity.getGenericName(),
                entity.getStrength(), entity.getDosageForm(), entity.getCompany(),
                entity.getCategoryName()
        );
    }



    @Override
    public ArrayList<ProductDTO> getAllProducts() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = productDAO.getAll();
        ArrayList<ProductDTO> dtos = new ArrayList<>();
        for (Product p : products) {
            dtos.add(new ProductDTO(p.getProductId(), p.getCode(), p.getName(), p.getPackSize(),
                    p.getCatId(), p.getGenericName(), p.getStrength(), p.getDosageForm(), p.getCompany()));
        }
        return dtos;    }

    @Override
    public ObservableList<String> getAllProductsForCombo() throws SQLException, ClassNotFoundException {
        return productDAO.getAllProductsForCombo();
    }

    @Override
    public long getProductIdFromDisplay(String displayText) throws SQLException, ClassNotFoundException {
        return productDAO.getProductIdFromDisplay(displayText);
    }


    //For Category
    @Override
    public ObservableList<String> getAllCategoryForCombo() throws SQLException, ClassNotFoundException {
        return categoryDAO.getAllCategoriesForCombo();
    }

    @Override
    public String getCatTypeFromDisplay(String displayText) throws SQLException, ClassNotFoundException {
        return categoryDAO.getCatTypeFromDisplay(displayText);
    }

    @Override
    public long getCatIdFromDisplay(String displayText) throws SQLException, ClassNotFoundException {
        return categoryDAO.getCatIdFromDisplay(displayText);
    }
}