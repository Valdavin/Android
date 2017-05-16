package com.example.valentin.endyearproject.ListProducts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.valentin.endyearproject.DAO.DAOBase;

import java.util.ArrayList;

/**
 * Created by Valentin on 19/03/2017.
 */

public class ListProductsDAO extends DAOBase {

    // Name of table
    public static final String DBNAME = "LISTPRODUCTS";

    // Table: PRODUCT
    public static final String COL_ID = "id";
    public static final String COL_USERID = "user_id";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table User
    public static final String CREATE_TABLE =
            "CREATE TABLE " + DBNAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_USERID + " INTEGER" +");";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table User
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + DBNAME + ";";


    // Données pour la table
    private static final String[] DATA = new String[] {
          };

    // retourne une liste de chaînes de caractères représentant les instructions SQL d'insertion de données dans la table
    public static String[] getInsertSQL() {
        String insertSQL = "INSERT INTO " + DBNAME + "("
                + COL_USERID + ") VALUES ";

        //
        String[] liste = new String[DATA.length];
        int i = 0;
        for (String listProductsReponse : DATA) {

            // Instruction SQL INSERT
            liste[i] = insertSQL + "(" + listProductsReponse + ")";
            i++;
        }

        //
        return liste;
    }

    public ListProductsDAO(Context context) {
        super(context);

    }

    public long insert(ListProducts listProducts) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(COL_USERID, listProducts.getIdUser());


        // Insertion de l'objet dans la BD via le ContentValues
        return getDB().insert(DBNAME, null, values);
    }

    public int update(ListProducts listProducts) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(COL_USERID, listProducts.getIdUser());

        // Insertion de l'objet dans la BD via le ContentValues et l'identifiant
        return getDB().update(DBNAME, values, COL_ID + " = " + listProducts.getId(), null);
    }

    public int removeByID(int id) {

        //Suppression d'une listProducts de la BD à partir de l'ID
        return getDB().delete(DBNAME, COL_ID + " = " + id, null);
    }

    public int remove(ListProducts listProducts) {

        return removeByID(listProducts.getId());
    }

    public ArrayList<ListProducts> selectAll() {

        //Récupère dans un Cursor les valeur correspondant à des enregistrements de listProducts contenu dans la BD
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + DBNAME, null);

        return cursorToListProducts(cursor);
    }

    public ArrayList<ListProducts> selectByUserId(int userid) {

        //Récupère dans un Cursor les valeur correspondant à des enregistrements de listProducts contenu dans la BD
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + DBNAME + " WHERE " + COL_USERID + "=?", new String[]{Integer.toString(userid)});

        return cursorToListProducts(cursor);
    }

    public ListProducts retrieveByID(int id) {

        //Récupère dans un Cursor les valeur correspondant à une listProducts contenu dans la BD à l'aide de son id
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + DBNAME + " WHERE " + COL_ID + "=?", new String[]{Integer.toString(id)});

        return cursorToFirstListProducts(cursor);
    }

    public ListProducts getListProductsRandom() {

        //Récupère dans un Cursor les valeur correspondant à une listProducts au hasard
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + DBNAME + " ORDER BY RANDOM() LIMIT 1", null);

        return cursorToFirstListProducts(cursor);
    }

    //Cette méthode permet de convertir un cursor en une liste de listProducts
    private ArrayList<ListProducts> cursorToListProducts(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexIdUser = cursor.getColumnIndex(COL_USERID);


        // Declaration et initialisation d'une liste de listProducts
        ArrayList<ListProducts> liste = new ArrayList<>();

        while (cursor.moveToNext()) {

            // Création d'une listProducts
            ListProducts listProducts = new ListProducts();
            listProducts.setId(cursor.getInt(indexId));
            listProducts.setIdUser(cursor.getInt(indexIdUser));

            // Ajout dans la liste
            liste.add(listProducts);
        }

        // Fermeture du cursor
        cursor.close();

        //
        return liste;
    }

    //Cette méthode permet de convertir un cursor en une listProducts
    private ListProducts cursorToFirstListProducts(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexIdUser = cursor.getColumnIndex(COL_USERID);


        // Declaration d'une listProducts
        ListProducts listProducts = null;

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            // Création d'une listProducts
            listProducts.setId(cursor.getInt(indexId));
            listProducts.setIdUser(cursor.getInt(indexIdUser));

        }

        // Fermeture du cursor
        cursor.close();

        //
        return listProducts;
    }


}

