package lk.ijse.pharamacymanagementlayerdsystem.dto;

public class LowStockDTO {
    private String name;
    private int    totalQty;

    public LowStockDTO() {}

    public LowStockDTO(String name, int totalQty) {
        this.name     = name;
        this.totalQty = totalQty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    @Override
    public String toString() {
        return "LowStockDTO{" +
                "name='" + name + '\'' +
                ", totalQty=" + totalQty +
                '}';
    }
}
