package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.ProductDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Products ORDER BY product_id ASC";
        ResultSet rs = CRUDUtil.execute(sql);
        ArrayList<Product> products = new ArrayList<>();

        while (rs.next()) {
            products.add(new Product(
                    rs.getLong("product_id"),
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("pack_size"),
                    rs.getLong("cat_id"),
                    rs.getString("generic_name"),
                    rs.getString("strength"),
                    rs.getString("dosage_form"),
                    rs.getString("company")
            ));
        }
        return products;
    }

    @Override
    public boolean save(Product productDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Products (code, name, pack_size, cat_id, generic_name, strength," +
                " dosage_form, company) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        return CRUDUtil.execute(sql,
                productDTO.getCode(),
                productDTO.getName(),
                productDTO.getPackSize(),
                productDTO.getCatId(),
                productDTO.getGenericName(),
                productDTO.getStrength(),
                productDTO.getDosageForm(),
                productDTO.getCompany());
    }

    @Override
    public boolean update(Product productDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Products SET code = ?, name = ?, pack_size = ?, cat_id = ?, "
                + "generic_name = ?, strength = ?, dosage_form = ?, company = ? WHERE product_id = ?";

        return CRUDUtil.<Boolean>execute(sql,
                productDTO.getCode(),
                productDTO.getName(),
                productDTO.getPackSize(),
                productDTO.getCatId(),
                productDTO.getGenericName(),
                productDTO.getStrength(),
                productDTO.getDosageForm(),
                productDTO.getCompany(),
                productDTO.getProductId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Products WHERE product_id = ?";
        return CRUDUtil.<Boolean>execute(sql, id);
    }

    @Override
    public Product search(String productId ) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Products WHERE product_id = ?";
        ResultSet rs = CRUDUtil.execute(sql, productId);

        if (rs.next()) {
            return new Product(
                    rs.getLong("product_id"),
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("pack_size"),
                    rs.getLong("cat_id"),
                    rs.getString("generic_name"),
                    rs.getString("strength"),
                    rs.getString("dosage_form"),
                    rs.getString("company")
            );
        }
        return null;
    }

    // GRN form Product dropdown loading
    @Override
    public ObservableList<String> getAllProductsForCombo() throws SQLException, ClassNotFoundException {
        String sql = "SELECT product_id, code, name FROM Products ORDER BY name ASC";

        ResultSet rs = CRUDUtil.execute(sql);

        ObservableList<String> list = FXCollections.observableArrayList();
        while (rs.next()) {
            String display = rs.getString("name") + " (" + rs.getString("code") + ")";
            list.add(display);
        }
        return list;
    }

    @Override
    public long getProductIdFromDisplay(String displayText) throws SQLException, ClassNotFoundException {
        if (displayText == null || displayText.isEmpty()) {
            return -1;
        }
        String code = displayText.substring(displayText.lastIndexOf("(") + 1, displayText.lastIndexOf(")"));

        String sql = "SELECT product_id FROM Products WHERE code = ?";
        ResultSet rs = CRUDUtil.execute(sql, code);

        if (rs.next()) {
            return rs.getLong("product_id");
        }
        return -1;
    }





}
