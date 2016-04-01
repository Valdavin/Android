package com.example.davinv.loustik.User;

import java.io.Serializable;

/**
 * Created by davinv on 22/03/16.
 */
public class User implements Serializable {




    private int id;
    private String nom;
    private String prenom;
    private String avatar;
    private int score;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String nom, String prenom, String avatar) {
        this.nom = nom;
        this.prenom = prenom;
        this.avatar = avatar;
        score = 0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Ajoute au <b>s</b> au score de l'user
     * @param s
     */
    public void addScore(int s) {
        this.score = this.score + s;
    }

    public String getAvatar() {
        return avatar;
    }





    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }




}
