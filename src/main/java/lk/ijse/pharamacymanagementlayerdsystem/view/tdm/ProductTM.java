package lk.ijse.pharamacymanagementlayerdsystem.view.tdm;

public class ProductTM {
    private long productId;
    private String code;
    private String name;
    private String packSize;
    private long catId;

    public ProductTM() {
    }

    public ProductTM(String code, String name, String packSize, long catId) {
        this.code = code;
        this.name = name;
        this.packSize = packSize;
        this.catId = catId;
    }

    public ProductTM(long productId, String code, String name, String packSize, long catId) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.packSize = packSize;
        this.catId = catId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCatId() {
        return catId;
    }

    public void setCatId(long catId) {
        this.catId = catId;
    }

    @Override
    public String toString() {
        return "ProductTM{" +
                "productId=" + productId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", packSize='" + packSize + '\'' +
                ", catId=" + catId +
                '}';
    }
}
