package lk.ijse.pharamacymanagementlayerdsystem.view.tdm;

public class CategoryTM {
    private Long category_Id;
    private String category_name;
    private String category_type;

    public CategoryTM() {
    }

    public CategoryTM(String category_name, String category_type) {
        this.category_name = category_name;
        this.category_type = category_type;
    }

    public CategoryTM(Long category_Id, String category_name, String category_type) {
        this.category_Id = category_Id;
        this.category_name = category_name;
        this.category_type = category_type;
    }

    public Long getCategory_Id() {
        return category_Id;
    }

    public void setCategory_Id(Long category_Id) {
        this.category_Id = category_Id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

    @Override
    public String toString() {
        return "CategoryTM{" +
                "category_Id=" + category_Id +
                ", category_name='" + category_name + '\'' +
                ", category_type='" + category_type + '\'' +
                '}';
    }
}
