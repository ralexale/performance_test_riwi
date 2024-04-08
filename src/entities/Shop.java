package entities;

import java.sql.Timestamp;

public class Shop {
    private Integer id;
    private Integer clientId;
    private Integer productId;
    private Timestamp dateShop;
    private Integer quantity;
    private Client client;
    private Product product;

    private Store store;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Shop() {
    }

    public Shop(Integer id, Integer clientId, Integer productId, Timestamp dateShop, Integer quantity, Client client, Product product, Store store) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.dateShop = dateShop;
        this.quantity = quantity;
        this.client = client;
        this.product = product;
        this.store = store;
    }

    public Shop(int id, int clientId, int productId, int quantity, Timestamp dateShop) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.dateShop = dateShop;
    }

    public Shop(int id, int clientId, int productId, int quantity, Timestamp dateShop, Product product, Client client) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.dateShop = dateShop;
        this.product = product;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Timestamp getDateShop() {
        return dateShop;
    }

    public void setDateShop(Timestamp dateShop) {
        this.dateShop = dateShop;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", productId=" + productId +
                ", dateShop=" + dateShop +
                ", quantity=" + quantity;
    }
}
