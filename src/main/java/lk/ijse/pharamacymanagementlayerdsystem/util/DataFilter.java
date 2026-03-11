package lk.ijse.pharamacymanagementlayerdsystem.util;

public class DataFilter {

    public static String getDateFilter(String filter) {
        switch (filter) {
            case "Today":
                return "CURDATE()";
            case "Weekly":
                return "DATE_SUB(CURDATE(), INTERVAL 7 DAY)";
            case "Monthly":
                return "DATE_SUB(CURDATE(), INTERVAL 30 DAY)";
            default:
                return "CURDATE()";
        }
    }

}
