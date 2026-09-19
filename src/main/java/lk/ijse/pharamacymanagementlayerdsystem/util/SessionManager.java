package lk.ijse.pharamacymanagementlayerdsystem.util;

import lk.ijse.pharamacymanagementlayerdsystem.dto.UserDTO;

public class SessionManager {

    public static UserDTO currentUser;

    public static void setUser(UserDTO user) {
        currentUser = user;
    }

    public static UserDTO getUser() {
        return currentUser;
    }



}
