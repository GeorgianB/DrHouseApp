package ro.baratheon.doctorhouse.entity;

public class Product {
    private Integer id;
    private String name;
    private String description;
    private Category category;
    private Double price;
    private String image;
    private String modelObject;
    private String modelTexture;
    private Boolean isArCompatible;
    private Boolean selected = false;

    public Product(Integer id, String name, Double price, String description, Category category, String image, String modelObject, String modelTexture, Boolean isArCompatible) {
        this.id = id;
        this.name = name;
        this.description = description;

        this.category = category;
        this.price = price;
        this.image = image;
        this.modelObject = modelObject;
        this.modelTexture = modelTexture;
        this.isArCompatible = isArCompatible;
    }

    public String getModelObject() {
        return modelObject;
    }

    public String getModelTexture() {
        return modelTexture;
    }

    public Boolean getArCompatible() {
        return isArCompatible;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
