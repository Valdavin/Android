package com.example.valentin.endyearproject.product;

import java.io.Serializable;

/**
 * Created by Valentin on 19/03/2017.
 */

public class Product implements Serializable {

    private int id;
    private String productName;
    private String barcode;
    private float price;
    private int idListProducts;

    public Product(String productName, String barcode, float price, int idListProducts) {
        this.productName = productName;
        this.barcode = barcode;
        this.price = price;
        this.idListProducts = idListProducts;
    }

    public Product(String productName, String barcode) {
        this.productName = productName;
        this.barcode = barcode;
    }

    public Product() {
        this.productName = "ValDef";
        this.barcode = "010101";
        this.price = 0f;
        this.idListProducts = 0;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIdListProducts() {
        return idListProducts;
    }

    public void setIdListProducts(int idListProducts) {
        this.idListProducts = idListProducts;
    }
}
