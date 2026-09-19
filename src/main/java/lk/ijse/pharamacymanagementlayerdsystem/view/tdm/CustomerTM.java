package lk.ijse.pharamacymanagementlayerdsystem.view.tdm;

public class CustomerTM {
    private Long customerId;
    private String customerName;
    private String customerAddress;
    private String customerContactNumber;
    private String customerEmail;

    public CustomerTM() {
    }

    public CustomerTM(String customerName, String customerAddress, String customerContactNumber, String customerEmail) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContactNumber = customerContactNumber;
        this.customerEmail = customerEmail;
    }

    public CustomerTM(Long customerId, String customerName, String customerAddress, String customerContactNumber, String customerEmail) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContactNumber = customerContactNumber;
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerContactNumber() {
        return customerContactNumber;
    }

    public void setCustomerContactNumber(String customerContactNumber) {
        this.customerContactNumber = customerContactNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerContactNumber='" + customerContactNumber + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }
}
