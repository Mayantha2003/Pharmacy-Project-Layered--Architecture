module lk.ijse.pharamacymanagementlayerdsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires mysql.connector.j;
    requires net.sf.jasperreports.core;
    requires javafx.graphics;
    requires java.sql.rowset;


    opens lk.ijse.pharamacymanagementlayerdsystem to javafx.fxml;
    opens lk.ijse.pharamacymanagementlayerdsystem.controller to javafx.fxml;
    opens lk.ijse.pharamacymanagementlayerdsystem.view.tdm to javafx.base;

    opens lk.ijse.pharamacymanagementlayerdsystem.dto to javafx.base;

    exports lk.ijse.pharamacymanagementlayerdsystem;
    exports lk.ijse.pharamacymanagementlayerdsystem.controller;
    exports lk.ijse.pharamacymanagementlayerdsystem.dto;
}