package com.example.valentin.endyearproject.product;

/**
 * Created by Valentin on 19/03/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.valentin.endyearproject.DAO.DAOBase;

import java.util.ArrayList;

public class ProductDAO extends DAOBase {

    // Name of table
    public static final String DBNAME = "PRODUCT";

    // Table: PRODUCT
    public static final String COL_ID = "id";
    public static final String COL_PRODUCTNAME = "product_name";
    public static final String COL_BARCODE = "barcode";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table Product
    public static final String CREATE_TABLE =
            "CREATE TABLE " + DBNAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_PRODUCTNAME + " TEXT NOT NULL, " +
                    COL_BARCODE + " TEXT NOT NULL);";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table Product
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + DBNAME + ";";


    // Données pour la table
    private static final String[] DATA = new String[] {
            "'Water', '042421337'"};

    // retourne une liste de chaînes de caractères représentant les instructions SQL d'insertion de données dans la table
    public static String[] getInsertSQL() {
        String insertSQL = "INSERT INTO " + DBNAME + "("
                + COL_BARCODE + ", "
                + COL_PRODUCTNAME + ") VALUES ";

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

    public ProductDAO(Context context) {
        super(context);

    }

    public long insert(Product product) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(COL_BARCODE, product.getBarcode());
        values.put(COL_PRODUCTNAME, product.getProductName());

        // Insertion de l'objet dans la BD via le ContentValues
        return getDB().insert(DBNAME, null, values);
    }

    public int update(Product product) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(COL_BARCODE, product.getBarcode());
        values.put(COL_PRODUCTNAME, product.getProductName());

        // Insertion de l'objet dans la BD via le ContentValues et l'identifiant
        return getDB().update(DBNAME, values, COL_ID + " = " + product.getId(), null);
    }

    public int removeByID(int id) {

        //Suppression d'une product de la BD à partir de l'ID
        return getDB().delete(DBNAME, COL_ID + " = " + id, null);
    }

    public int remove(Product product) {

        return removeByID(product.getId());
    }

    public ArrayList<Product> selectAll() {

        //Récupère dans un Cursor les valeur correspondant à des enregistrements de product contenu dans la BD
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + DBNAME, null);

        return cursorToListProduct(cursor);
    }

    public Product retrieveByID(int id) {

        //Récupère dans un Cursor les valeur correspondant à une product contenu dans la BD à l'aide de son id
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + DBNAME + " WHERE " + COL_ID + "=?", new String[]{Integer.toString(id)});

        return cursorToFirstProduct(cursor);
    }

    public Product getProductRandom() {

        //Récupère dans un Cursor les valeur correspondant à une product au hasard
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + DBNAME + " ORDER BY RANDOM() LIMIT 1", null);

        return cursorToFirstProduct(cursor);
    }

    //Cette méthode permet de convertir un cursor en une liste de products
    private ArrayList<Product> cursorToListProduct(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexProductName = cursor.getColumnIndex(COL_PRODUCTNAME);
        int indexBarcode = cursor.getColumnIndex(COL_BARCODE);


        // Declaration et initialisation d'une liste de product
        ArrayList<Product> liste = new ArrayList<>();

        while (cursor.moveToNext()) {

            // Création d'une product
            Product product = new Product();
            product.setId(cursor.getInt(indexId));
            product.setProductName(cursor.getString(indexProductName));
            product.setBarcode(cursor.getString(indexBarcode));

            // Ajout dans la liste
            liste.add(product);
        }

        // Fermeture du cursor
        cursor.close();

        //
        return liste;
    }

    //Cette méthode permet de convertir un cursor en une product
    private Product cursorToFirstProduct(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexProductName = cursor.getColumnIndex(COL_PRODUCTNAME);
        int indexBarcode = cursor.getColumnIndex(COL_BARCODE);


        // Declaration d'une product
        Product product = null;

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            // Création d'une product
            product.setId(cursor.getInt(indexId));
            product.setProductName(cursor.getString(indexProductName));
            product.setBarcode(cursor.getString(indexBarcode));

        }

        // Fermeture du cursor
        cursor.close();

        //
        return product;
    }


}

