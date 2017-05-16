package com.example.valentin.endyearproject.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.valentin.endyearproject.ListProducts.ListProductsDAO;
import com.example.valentin.endyearproject.user.UserDAO;
import com.example.valentin.endyearproject.product.ProductDAO;

/**
 * Created by Valentin on 19/03/2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    // VERSION de la bdd, permet les mises à jour des tables et champs au lancement de l'application
    private static final int VERSION = 2;

    // NOM de la base
    private static final String DATABASE_NAME = "database_endyearproject";

    // TAG pour le log
    private static final String TAG = "DBHelper";

    // Constructeur
    public DBHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create Table
        db.execSQL(ProductDAO.CREATE_TABLE);
        db.execSQL(UserDAO.CREATE_TABLE);
        db.execSQL(ListProductsDAO.CREATE_TABLE);

        // Insérer les données

        for (String insert : ProductDAO.getInsertSQL()) {
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
        db.execSQL(ListProductsDAO.DROP_TABLE);
        db.execSQL(ProductDAO.DROP_TABLE);
        db.execSQL(UserDAO.DROP_TABLE);


        // Relancer la création
        onCreate(db);
    }
}
