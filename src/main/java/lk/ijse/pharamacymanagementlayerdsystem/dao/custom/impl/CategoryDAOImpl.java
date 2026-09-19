package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.CategoryDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public ArrayList<Category> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rs = CRUDUtil.execute("SELECT * FROM Categories ORDER BY cat_id ASC");
        ArrayList<Category> CategoryList = new ArrayList<>();

        while (rs.next()) {
            Long catId = rs.getLong("cat_id");
            String catName = rs.getString("name");
            String catType = rs.getString("type");

            Category entity= new Category(catId, catName, catType);
            CategoryList.add(entity);
        }

        return CategoryList;
    }

    @Override
    public boolean save(Category categoryDTO) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "INSERT INTO Categories (name, type) VALUES (?, ?)",
                categoryDTO.getCategory_name(),
                categoryDTO.getCategory_type()
        );
    }

    @Override
    public boolean update(Category categoryDTO) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "UPDATE Categories SET name = ?, type = ? WHERE cat_id = ?",
                categoryDTO.getCategory_name(),
                categoryDTO.getCategory_type(),
                categoryDTO.getCategory_Id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Categories WHERE cat_id = ?", id);
    }

    @Override
    public Category search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute(
                "SELECT * FROM Categories WHERE cat_id = ?", id);

        if (rs.next()) {
            return new Category(
                    rs.getLong("cat_id"),
                    rs.getString("name"),
                    rs.getString("type")
            );
        }
        return null;
    }

    @Override
    public boolean isCategoryUsedInProducts(Long category_Id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT 1 FROM Products WHERE cat_id = ? LIMIT 1";

        ResultSet rs = CRUDUtil.execute(sql, category_Id);

        return rs.next();
    }

    @Override
    public String getCategoryDisplayName(long catId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT name, type FROM Categories WHERE cat_id = ?";
        ResultSet rs = CRUDUtil.execute(sql, catId);

        if (rs.next()) {
            return rs.getString("name") + " (" + rs.getString("type") + ")";
        }
        return "Unknown Category";
    }

    @Override
    public ObservableList<String> getAllCategoriesForCombo() throws SQLException, ClassNotFoundException {
        String sql = "SELECT name, type FROM Categories ORDER BY type DESC, name ASC";
        ResultSet rs = CRUDUtil.execute(sql);

        ObservableList<String> list = FXCollections.observableArrayList();
        while (rs.next()) {
            String display = rs.getString("name") + " (" + rs.getString("type") + ")";
            list.add(display);
        }
        return list;
    }

    @Override
    public String getCatTypeFromDisplay(String displayText) throws SQLException, ClassNotFoundException {

        if (displayText == null || displayText.isEmpty()) {
            return "";
        }
        String categoryName = displayText.split(" \\(")[0].trim();

        String sql = "SELECT type FROM Categories WHERE name = ?";
        ResultSet rs = CRUDUtil.execute(sql, categoryName);

        if (rs.next()) {
            return rs.getString("type");
        }
        return "";
    }

    @Override
    public long getCatIdFromDisplay(String displayText) throws SQLException, ClassNotFoundException {
        if (displayText == null || displayText.isEmpty()) {
            return -1;
        }

        String categoryName = displayText.split(" \\(")[0].trim();

        String sql = "SELECT cat_id FROM Categories WHERE name = ?";
        ResultSet rs = CRUDUtil.execute(sql, categoryName);

        if (rs.next()) {
            return rs.getLong("cat_id");
        }
        return -1;
    }
}
