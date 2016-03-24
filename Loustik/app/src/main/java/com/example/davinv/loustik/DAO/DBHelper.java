package com.example.davinv.loustik.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.davinv.loustik.Culture.QuestionDAO;
import com.example.davinv.loustik.Login.UserDAO;

/**
 * Created by Valentin on 21/03/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    // VERSION de la bdd, permet les mises à jour des tables et champs au lancement de l'application
    private static final int VERSION = 4;

    // NOM de la base
    private static final String DATABASE_NAME = "database_loustik";

    // TAG pour le log
    private static final String TAG = "DBHelper";

    // Constructeur
    public DBHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Créer la table question
        db.execSQL(QuestionDAO.CREATE_TABLE);
        db.execSQL(UserDAO.CREATE_TABLE);

        // Insérer les données
        for (String insert : QuestionDAO.getInsertSQL()) {
            db.execSQL(insert);
        }
        for (String insert : UserDAO.getInsertSQL()) {
            db.execSQL(insert);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Log
        Log.w(TAG, "UPGRADING DATABASE FROM VERSION " + oldVersion
                + " TO "
                + newVersion + ", WHICH WILL DESTROY ALL OLD DATA !");

        // DROP
        db.execSQL(QuestionDAO.DROP_TABLE);
        db.execSQL(UserDAO.DROP_TABLE);

        // Relancer la création
        onCreate(db);
    }
}
