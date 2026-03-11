package lk.ijse.pharamacymanagementlayerdsystem.bo.custom;

import lk.ijse.pharamacymanagementlayerdsystem.bo.SuperBO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.CategoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CategoryBO extends SuperBO {

    public boolean saveCategory(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException;

    public CategoryDTO searchCategory(Long category_Id) throws SQLException, ClassNotFoundException;

    public boolean updateCategory(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteCategory(Long category_Id) throws SQLException, ClassNotFoundException;

    public ArrayList<CategoryDTO> getAllCategory() throws SQLException, ClassNotFoundException;

    public boolean isCategoryUsedInProducts(Long category_Id) throws SQLException, ClassNotFoundException;

}
