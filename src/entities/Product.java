package entities;

public class Product {
    private int id;
    private String name;

    private Double price;

    private Integer storeId;

    private Integer stock;

    private Store store;


    public Product() {
    }

    public Product(int id, String name, Double price, Integer storeId, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.storeId = storeId;
        this.stock = stock;
    }

    public Product(int id, String name, Double price, Integer storeId, Integer stock, Store store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.storeId = storeId;
        this.stock = stock;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", idStore=" + storeId +
                ", stock=" + stock;
    }
}
