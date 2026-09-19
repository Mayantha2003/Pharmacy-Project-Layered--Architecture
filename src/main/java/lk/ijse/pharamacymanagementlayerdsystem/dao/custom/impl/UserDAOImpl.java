package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.UserDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM Users ORDER BY user_id ASC");
        ArrayList<User> userList = new ArrayList<>();

        while(rs.next()){
            Long userId = rs.getLong("user_id");
            String userName = rs.getString("username");
            String password = rs.getString("password");
            String fullName = rs.getString("full_name");
            String contact = rs.getString("contact");
            String role = rs.getString("role");

            User entity = new User(userId,userName,password,fullName,contact,role);
            userList.add(entity);
        }
        return userList;
    }

    @Override
    public boolean save(User userDTO) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "INSERT INTO Users (username, password, full_name, contact,role ) VALUES (?, ?, ?, ?, ?)",
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getFullName(),
                userDTO.getContact(),
                userDTO.getRole()
        );
    }

    @Override
    public boolean update(User userDTO) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "UPDATE Users SET username = ?, password = ?, full_name = ? , contact = ?, role = ? WHERE user_id = ?",
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getFullName(),
                userDTO.getContact(),
                userDTO.getRole(),
                userDTO.getUserId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Users WHERE user_id = ?", id);

    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM Users WHERE user_id = ?",id );
        if (rs.next()) {
            return new User(
                    rs.getLong("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("full_name"),
                    rs.getString("contact"),
                    rs.getString("role")
            );
        }
        return null;
    }

    @Override
    public User login(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        ResultSet rs = CRUDUtil.execute(sql, username, password);

        if (rs.next()) {
            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setFullName(rs.getString("full_name"));
            return user;
        }
        return null;
    }
}
