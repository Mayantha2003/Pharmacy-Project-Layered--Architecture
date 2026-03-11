package lk.ijse.pharamacymanagementlayerdsystem.bo.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.CategoryBO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.DAOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.CategoryDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.CategoryDTO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryBOImpl implements CategoryBO {

    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CATEGORY);

    @Override
    public boolean saveCategory(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException {
        return categoryDAO.save(new Category(
                categoryDTO.getCategory_Id(),
                categoryDTO.getCategory_name(),
                categoryDTO.getCategory_type()
        ));
    }

    @Override
    public CategoryDTO searchCategory(Long category_Id) throws SQLException, ClassNotFoundException {
        Category category = categoryDAO.search(String.valueOf(category_Id));
            return new CategoryDTO(
                    category.getCategory_Id(),
                    category.getCategory_name(),
                    category.getCategory_type()
            );
    }

    @Override
    public boolean updateCategory(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException {
        return categoryDAO.update(new Category(
                categoryDTO.getCategory_Id(),
                categoryDTO.getCategory_name(),
                categoryDTO.getCategory_type()
        ));    }

    @Override
    public boolean deleteCategory(Long category_Id) throws SQLException, ClassNotFoundException {
        return categoryDAO.delete(String.valueOf(category_Id));    }

    @Override
    public ArrayList<CategoryDTO> getAllCategory() throws SQLException, ClassNotFoundException {
        ArrayList<Category> categories = categoryDAO.getAll();
        ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category : categories) {
            categoryDTOS.add(new CategoryDTO(
                    category.getCategory_Id(),
                    category.getCategory_name(),
                    category.getCategory_type()
            ));
        }
        return categoryDTOS;    }

    @Override
    public boolean isCategoryUsedInProducts(Long category_Id) throws SQLException, ClassNotFoundException {
        return categoryDAO.isCategoryUsedInProducts(category_Id);
    }

}
