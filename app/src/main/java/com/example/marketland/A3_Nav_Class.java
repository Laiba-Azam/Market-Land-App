package com.example.marketland;

public class A3_Nav_Class {
    Integer productId;
    String productName;
    public A3_Nav_Class(Integer productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
}
