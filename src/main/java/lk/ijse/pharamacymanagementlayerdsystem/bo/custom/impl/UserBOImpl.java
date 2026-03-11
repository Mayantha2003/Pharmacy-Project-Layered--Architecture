package lk.ijse.pharamacymanagementlayerdsystem.bo.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.UserBO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.DAOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.UserDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.UserDTO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.User;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    UserDAO userDAO =(UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public UserDTO login(String username, String password) throws SQLException, ClassNotFoundException {
        User user = userDAO.login(username, password);
        if (user != null) {
            return new UserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getFullName(),
                    user.getContact(),
                    user.getRole()
            );
        }
        return null;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(userDTO.getUserId(), userDTO.getUsername(),
                userDTO.getPassword(),userDTO.getFullName(), userDTO.getContact(), userDTO.getRole()
        ));
    }

    @Override
    public UserDTO searchUser(Long userId) throws SQLException, ClassNotFoundException {
        User user = userDAO.search(String.valueOf(userId));
            return new UserDTO(user.getUserId(), user.getUsername(), user.getPassword(), user.getFullName(),
                    user.getContact(), user.getRole()
            );
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(userDTO.getUserId(), userDTO.getUsername(), userDTO.getPassword(),
                userDTO.getFullName(), userDTO.getContact(), userDTO.getRole()
        ));
    }

    @Override
    public boolean deleteUser(Long userId) throws SQLException, ClassNotFoundException {
        return userDAO.delete(String.valueOf(userId));
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        ArrayList<User> users = userDAO.getAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            userDTOS.add(new UserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getFullName(),
                    user.getContact(),
                    user.getRole()
            ));
        }
        return userDTOS;
    }
}

