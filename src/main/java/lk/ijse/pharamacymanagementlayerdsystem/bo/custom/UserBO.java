package lk.ijse.pharamacymanagementlayerdsystem.bo.custom;

import lk.ijse.pharamacymanagementlayerdsystem.bo.SuperBO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public UserDTO login(String username, String password) throws SQLException, ClassNotFoundException;

    public boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    public UserDTO searchUser(Long userId) throws SQLException, ClassNotFoundException;

    public boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteUser(Long userId) throws SQLException, ClassNotFoundException;

    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;
}
