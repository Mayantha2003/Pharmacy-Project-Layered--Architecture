package lk.ijse.pharamacymanagementlayerdsystem.dao.custom;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CrudDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Grn;

import java.sql.SQLException;

public interface GRNDAO extends CrudDAO<Grn> {

    public String generateNextGrnNumber() throws SQLException, ClassNotFoundException;

    long getLastId() throws SQLException, ClassNotFoundException;

}
