package com.example.davinv.loustik.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.example.davinv.loustik.DAO.DAOBase;

import java.util.ArrayList;

/**
 * Created by davinv on 22/03/16.
 */
public class UserDAO extends DAOBase {
    // Nom de la table
    public static final String TABLE_USER = "USER";

    // Table: USER
    public static final String COL_ID = "id";
    public static final String COL_NOM = "nom";
    public static final String COL_PRENOM = "prenom";
    public static final String COL_AVATAR = "avatar";
    public static final String COL_SCORE = "score";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table User
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_USER + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NOM + " TEXT NOT NULL, " +
                    COL_PRENOM + " TEXT NOT NULL, " +
                    COL_AVATAR + " TEXT NOT NULL, " +
                    COL_SCORE + " TEXT NOT NULL);";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table User
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER + ";";


    // Données pour la table
    private static final String[] DATA = new String[] {
            "'Ad', 'Hoc', 'swag', '0'"};

    // retourne une liste de chaînes de caractères représentant les instructions SQL d'insertion de données dans la table
    public static String[] getInsertSQL() {
        String insertSQL = "INSERT INTO " + TABLE_USER + "("
                + COL_NOM + ", "
                + COL_PRENOM + ", "
                + COL_AVATAR + ", "
                + COL_SCORE + ") VALUES ";

        //
        String[] liste = new String[DATA.length];
        int i = 0;
        for (String user : DATA) {

            // Instruction SQL INSERT
            liste[i] = insertSQL + "(" + user + ")";
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
        values.put(COL_NOM, user.getNom());
        values.put(COL_PRENOM, user.getPrenom());
        values.put(COL_AVATAR, user.getAvatar());
        values.put(COL_SCORE, user.getScore());

        // Insertion de l'objet dans la BD via le ContentValues
        return getDB().insert(TABLE_USER, null, values);
    }

    public int update(User user) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(COL_NOM, user.getNom());
        values.put(COL_PRENOM, user.getPrenom());
        values.put(COL_AVATAR, user.getAvatar());
        values.put(COL_SCORE, user.getScore());

        // Insertion de l'objet dans la BD via le ContentValues et l'identifiant
        return getDB().update(TABLE_USER, values, COL_ID + " = " + user.getId(), null);
    }

    public int removeByID(int id) {

        //Suppression d'un User de la BD à partir de l'ID
        return getDB().delete(TABLE_USER, COL_ID + " = " + id, null);
    }

    public int remove(User user) {

        return removeByID(user.getId());
    }

    public ArrayList<User> selectAll() {

        //Récupère dans un Cursor les valeur correspondant à des enregistrements de User contenu dans la BD
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_USER, null);

        return cursorToListUser(cursor);
    }

    public User retrieveByID(int id) {

        //Récupère dans un Cursor les valeur correspondant à une User contenu dans la BD à l'aide de son id
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COL_ID + "=?", new String[]{Integer.toString(id)});

        return cursorToFirstUser(cursor);
    }

    //Cette méthode permet de convertir un cursor en une liste de user
    private ArrayList<User> cursorToListUser(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexNom = cursor.getColumnIndex(COL_NOM);
        int indexPrenom = cursor.getColumnIndex(COL_PRENOM);
        int indexAvatar = cursor.getColumnIndex(COL_AVATAR);
        int indexScore = cursor.getColumnIndex(COL_SCORE);


        // Declaration et initialisation d'une liste de User
        ArrayList<User> liste = new ArrayList<>();

        while (cursor.moveToNext()) {

            // Création d'un User
            User user = new User();
            user.setId(cursor.getInt(indexId));
            user.setNom(cursor.getString(indexNom));
            user.setPrenom(cursor.getString(indexPrenom));
            user.setAvatar(cursor.getString(indexAvatar));
            user.setScore(cursor.getInt(indexScore));

            // Ajout dans la liste
            liste.add(user);
        }

        // Fermeture du cursor
        cursor.close();

        //
        return liste;
    }

    //Cette méthode permet de convertir un cursor en un User
    private User cursorToFirstUser(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexNom = cursor.getColumnIndex(COL_NOM);
        int indexPrenom = cursor.getColumnIndex(COL_PRENOM);
        int indexAvatar = cursor.getColumnIndex(COL_AVATAR);
        int indexScore = cursor.getColumnIndex(COL_SCORE);


        // Declaration d'une user
        User user = null;

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            // Création d'une user
            user = new User();
            user.setId(cursor.getInt(indexId));
            user.setNom(cursor.getString(indexNom));
            user.setPrenom(cursor.getString(indexPrenom));
            user.setAvatar(cursor.getString(indexAvatar));
            user.setScore(cursor.getInt(indexScore));

        }

        // Fermeture du cursor
        cursor.close();

        //
        return user;
    }


}
