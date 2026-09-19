package lk.ijse.pharamacymanagementlayerdsystem.bo.custom;


import lk.ijse.pharamacymanagementlayerdsystem.bo.SuperBO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.GrnDTO;

import java.sql.SQLException;

public interface GRNBO extends SuperBO {

    public boolean saveGRNWithTransaction(GrnDTO grnDTO) throws SQLException, ClassNotFoundException;

     public String generateNextGrnNumber() throws SQLException, ClassNotFoundException ;

}
