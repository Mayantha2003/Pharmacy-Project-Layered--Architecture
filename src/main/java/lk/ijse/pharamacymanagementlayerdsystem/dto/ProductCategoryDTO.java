package lk.ijse.pharamacymanagementlayerdsystem.dto;

public class ProductCategoryDTO {
    private Long productId;
    private String code;
    private String name;
    private String packSize;
    private Long catId;
    private String genericName;
    private String strength;
    private String dosageForm;
    private String company;
    private String categoryName;

    public ProductCategoryDTO() {
    }

    public ProductCategoryDTO(String code, String name, String packSize, Long catId, String genericName,
                              String strength, String dosageForm, String company, String categoryName) {
        this.code = code;
        this.name = name;
        this.packSize = packSize;
        this.catId = catId;
        this.genericName = genericName;
        this.strength = strength;
        this.dosageForm = dosageForm;
        this.company = company;
        this.categoryName = categoryName;
    }

    public ProductCategoryDTO(Long productId, String code, String name, String packSize, Long catId, String strength,
                              String genericName, String dosageForm, String company, String categoryName) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.packSize = packSize;
        this.catId = catId;
        this.strength = strength;
        this.genericName = genericName;
        this.dosageForm = dosageForm;
        this.company = company;
        this.categoryName = categoryName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "ProductCategoryDTO{" +
                "productId=" + productId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", packSize='" + packSize + '\'' +
                ", catId=" + catId +
                ", genericName='" + genericName + '\'' +
                ", strength='" + strength + '\'' +
                ", dosageForm='" + dosageForm + '\'' +
                ", company='" + company + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
