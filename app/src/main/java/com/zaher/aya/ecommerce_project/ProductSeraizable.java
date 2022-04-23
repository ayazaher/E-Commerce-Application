package com.zaher.aya.ecommerce_project;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductSeraizable implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Product> productArrayList;

    public ProductSeraizable(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

}
