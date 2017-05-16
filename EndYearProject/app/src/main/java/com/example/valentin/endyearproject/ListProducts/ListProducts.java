package com.example.valentin.endyearproject.ListProducts;

/**
 * Created by Valentin on 16/05/2017.
 */

public class ListProducts {
    private int id;
    private int idUser;

    public ListProducts() {
    }

    public ListProducts(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
