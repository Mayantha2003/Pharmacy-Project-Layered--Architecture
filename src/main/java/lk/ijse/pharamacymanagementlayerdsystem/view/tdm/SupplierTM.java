package lk.ijse.pharamacymanagementlayerdsystem.view.tdm;

public class SupplierTM {
    private Long supplierId;
    private String supplierName;
    private String supplierAddress;
    private String supplierContactNumber;
    private String supplierEmail;

    public SupplierTM() {
    }

    public SupplierTM(String supplierName, String supplierAddress, String supplierContactNumber, String supplierEmail) {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierContactNumber = supplierContactNumber;
        this.supplierEmail = supplierEmail;
    }

    public SupplierTM(Long supplierId, String supplierName, String supplierAddress, String supplierContactNumber, String supplierEmail) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierContactNumber = supplierContactNumber;
        this.supplierEmail = supplierEmail;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierContactNumber() {
        return supplierContactNumber;
    }

    public void setSupplierContactNumber(String supplierContactNumber) {
        this.supplierContactNumber = supplierContactNumber;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    @Override
    public String toString() {
        return "SupplierTM{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", supplierAddress='" + supplierAddress + '\'' +
                ", supplierContactNumber='" + supplierContactNumber + '\'' +
                ", supplierEmail='" + supplierEmail + '\'' +
                '}';
    }
}
