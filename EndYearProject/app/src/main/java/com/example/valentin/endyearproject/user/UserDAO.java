package com.example.valentin.endyearproject.user;

/**
 * Created by Valentin on 19/03/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.valentin.endyearproject.DAO.DAOBase;

import java.util.ArrayList;

public class UserDAO extends DAOBase {

    // Name of table
    public static final String DBNAME = "USER";

    // Table: PRODUCT
    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "username";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table User
    public static final String CREATE_TABLE =
            "CREATE TABLE " + DBNAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_USERNAME + " TEXT NOT NULL);";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table User
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + DBNAME + ";";


    // Données pour la table
    private static final String[] DATA = new String[] {
            "'User1'"};

    // retourne une liste de chaînes de caractères représentant les instructions SQL d'insertion de données dans la table
    public static String[] getInsertSQL() {
        String insertSQL = "INSERT INTO " + DBNAME + "("
                + COL_USERNAME + ") VALUES ";

        //
        String[] liste = new String[DATA.length];
        int i = 0;
        for (String productReponse : DATA) {

            // Instruction SQL INSERT
            liste[i] = insertSQL + "(" + productReponse + ")";
            i++;
        }

        //
        return liste;
    }

    public UserDAO(Context context) {
        super(context);

    }

    public long insert(User user) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(COL_USERNAME, user.getUserName());

        // Insertion de l'objet dans la BD via le ContentValues
        return getDB().insert(DBNAME, null, values);
    }

    public int update(User user) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(COL_USERNAME, user.getUserName());

        // Insertion de l'objet dans la BD via le ContentValues et l'identifiant
        return getDB().update(DBNAME, values, COL_ID + " = " + user.getId(), null);
    }

    public int removeByID(int id) {

        //Suppression d'une product de la BD à partir de l'ID
        return getDB().delete(DBNAME, COL_ID + " = " + id, null);
    }

    public int remove(User user) {

        return removeByID(user.getId());
    }

    public ArrayList<User> selectAll() {

        //Récupère dans un Cursor les valeur correspondant à des enregistrements de product contenu dans la BD
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + DBNAME, null);

        return cursorToListProduct(cursor);
    }

    public User retrieveByID(int id) {

        //Récupère dans un Cursor les valeur correspondant à une product contenu dans la BD à l'aide de son id
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + DBNAME + " WHERE " + COL_ID + "=?", new String[]{Integer.toString(id)});

        return cursorToFirstProduct(cursor);
    }

    public User getProductRandom() {

        //Récupère dans un Cursor les valeur correspondant à une product au hasard
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + DBNAME + " ORDER BY RANDOM() LIMIT 1", null);

        return cursorToFirstProduct(cursor);
    }

    //Cette méthode permet de convertir un cursor en une liste de products
    private ArrayList<User> cursorToListProduct(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexUserName = cursor.getColumnIndex(COL_USERNAME);


        // Declaration et initialisation d'une liste de product
        ArrayList<User> liste = new ArrayList<>();

        while (cursor.moveToNext()) {

            // Création d'une user
            User user = new User();
            user.setId(cursor.getInt(indexId));
            user.setUserName(cursor.getString(indexUserName));

            // Ajout dans la liste
            liste.add(user);
        }

        // Fermeture du cursor
        cursor.close();

        //
        return liste;
    }

    //Cette méthode permet de convertir un cursor en une product
    private User cursorToFirstProduct(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexUserName = cursor.getColumnIndex(COL_USERNAME);


        // Declaration d'une user
        User user = null;

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            // Création d'une user
            user.setId(cursor.getInt(indexId));
            user.setUserName(cursor.getString(indexUserName));

        }

        // Fermeture du cursor
        cursor.close();

        //
        return user;
    }


}

