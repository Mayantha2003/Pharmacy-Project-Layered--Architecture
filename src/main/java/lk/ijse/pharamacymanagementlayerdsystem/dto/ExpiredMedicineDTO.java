package lk.ijse.pharamacymanagementlayerdsystem.dto;

public class ExpiredMedicineDTO {
    private String name;
    private String batchNumber;
    private String expiryDate;
    private int    qtyRemaining;

    public ExpiredMedicineDTO() {}

    public ExpiredMedicineDTO(String name, String batchNumber,
                              String expiryDate, int qtyRemaining) {
        this.name         = name;
        this.batchNumber  = batchNumber;
        this.expiryDate   = expiryDate;
        this.qtyRemaining = qtyRemaining;
    }

    public ExpiredMedicineDTO(String name, String expiryDate, int qty) {
        this.name         = name;
        this.expiryDate   = expiryDate;
        this.qtyRemaining = qty;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQtyRemaining() {
        return qtyRemaining;
    }

    public void setQtyRemaining(int qtyRemaining) {
        this.qtyRemaining = qtyRemaining;
    }

    @Override
    public String toString() {
        return "ExpiredMedicineDTO{" +
                "name='" + name + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", qtyRemaining=" + qtyRemaining +
                '}';
    }
}
