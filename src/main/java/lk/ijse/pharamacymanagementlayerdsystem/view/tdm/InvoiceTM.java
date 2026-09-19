package lk.ijse.pharamacymanagementlayerdsystem.view.tdm;

import java.time.LocalDate;

public class InvoiceTM {
    private long batchId;
    private String productName;
    private String batchNumber;
    private LocalDate expiryDate;
    private int quantity;
    private double sellingPrice;
    private double lineTotal;

    public InvoiceTM() {
    }

    public InvoiceTM(String productName,String batchNumber, LocalDate expiryDate, int quantity, double sellingPrice,
                     double lineTotal) {
        this.productName = productName;
        this.batchNumber = batchNumber;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.lineTotal = lineTotal;
    }

    public InvoiceTM(long batchId,String productName, String batchNumber, LocalDate expiryDate, int quantity, double sellingPrice,
                     double lineTotal) {
        this.batchId = batchId;
        this.productName = productName;
        this.batchNumber = batchNumber;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.lineTotal = lineTotal;
    }

    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public void calculateLineTotal() {
        this.lineTotal = this.quantity * this.sellingPrice;
    }

    @Override
    public String toString() {
        return "InvoiceTM{" +
                "batchId=" + batchId +
                ", productName='" + productName + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", expiryDate=" + expiryDate +
                ", quantity=" + quantity +
                ", sellingPrice=" + sellingPrice +
                ", lineTotal=" + lineTotal +
                '}';
    }
}
