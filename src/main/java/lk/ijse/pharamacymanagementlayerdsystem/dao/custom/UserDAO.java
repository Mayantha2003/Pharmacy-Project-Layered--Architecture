package lk.ijse.pharamacymanagementlayerdsystem.dao.custom;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CrudDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {

    public User login(String username, String password) throws SQLException, ClassNotFoundException ;

}
