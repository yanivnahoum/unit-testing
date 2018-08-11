package com.att.tlv.training.test.exercises.data;

public class Order {

    private long id;
    private long productId;
    private String productDescription;

    Order() {
        // Used by JPA
    }

    public Order(long productId, String productDescription) {
        this.productId = productId;
        this.productDescription = productDescription;
    }

    public long getId() {
        return id;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductDescription() {
        return productDescription;
    }
}
