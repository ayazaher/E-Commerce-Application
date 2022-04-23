package com.zaher.aya.ecommerce_project;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String productName;
    private Integer productPrice;
    private Integer productQty;

    public Product(int id, String productName, Integer productPrice, Integer productQty) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQty = productQty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

}
