package com.example.davinv.loustik.Culture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.davinv.loustik.DAO.DAOBase;

import java.util.ArrayList;

/**
 * Created by Valentin on 21/03/2016.
 */
public class QuestionDAO extends DAOBase {

    // Nom de la table
    public static final String TABLE_QUESTION_REPONSE = "QUESTION_REPONSE";

    // Table: QUESTION
    public static final String COL_ID = "id";
    public static final String COL_QUESTION = "question";
    public static final String COL_BONNE_REPONSE = "bonne_reponse";
    public static final String COL_MAUVAISE_REPONSE_1 = "mauvaise_reponse_1";
    public static final String COL_MAUVAISE_REPONSE_2 = "mauvaise_reponse_2";
    public static final String COL_THEME = "theme";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table Question
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_QUESTION_REPONSE + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_QUESTION + " TEXT NOT NULL, " +
                    COL_BONNE_REPONSE + " TEXT NOT NULL, " +
                    COL_MAUVAISE_REPONSE_1 + " TEXT NOT NULL, " +
                    COL_MAUVAISE_REPONSE_2 + " TEXT NOT NULL, " +
                    COL_THEME + " TEXT NOT NULL);";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table Question
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_QUESTION_REPONSE + ";";


    // Données pour la table
    private static final String[] DATA = new String[] {
            "'Quel est la couleur du cheval blanc d''Henri 4 ?', 'Blanc', 'Rouge', 'Jaune Fluo' , '"+JeuCultureActivity.THEME_GENERALE+"'",
            "'Dans la série \"Game of Thrones\", quels animaux accompagne Daenerys Targaryen ?', 'Des Dragons', 'Des loups', 'Des canards', '" +JeuCultureActivity.THEME_GENERALE+ "'",
            "'La France est dans la zone..?', 'Tempérée', 'Polaire', 'Tropicale', '" +JeuCultureActivity.THEME_GEOGRAPHIE+ "'",
            "'Qui découvrit les Indes en 1498 ?', 'Vasco de Gama', 'Magellan', 'Christophe Colomb', '" +JeuCultureActivity.THEME_HISOIRE+ "'",
            "'Combien de verres de 20 cl peut-on remplir avec 1 litre d''eau ?', '5', '4', '6', '" +JeuCultureActivity.THEME_GENERALE+ "'",
            "'Quel océan entoure l''île de Madagascar ?', 'Océan Indien', 'Océan Atlantique', 'Océan Pacifique', '" +JeuCultureActivity.THEME_GEOGRAPHIE+ "'",
            "'Lequel de ces pays n''appartient pas à l''Union européenne ?', 'Suisse', 'Portugal', 'Grèce', '" +JeuCultureActivity.THEME_GEOGRAPHIE+ "'",
            "'Quel composant de l''air l''être humain absorbe-t-il pour respirer ?', 'L''oxygène', 'L''azote', 'Le gaz carbonique', '" +JeuCultureActivity.THEME_SCIENCE+ "'",
            "'Comment s''appelle le chien maladroit qui est chargé de surveiller les Daltons ?', 'Rantanplan', 'Ranplantan', 'Annie C.', '" +JeuCultureActivity.THEME_GENERALE+ "'",
            "'Quel Roi de France a construit Versaille ?', 'Louis XIV (14) ', 'Louis XVI (16)', 'Napoléon', '" +JeuCultureActivity.THEME_HISOIRE+ "'",
            "'Quel est la capitale de l''Italie ?', 'Rome', 'Venise', 'Grenoble', '" +JeuCultureActivity.THEME_GEOGRAPHIE+ "'",};

    // retourne une liste de chaînes de caractères représentant les instructions SQL d'insertion de données dans la table
    public static String[] getInsertSQL() {
        String insertSQL = "INSERT INTO " + TABLE_QUESTION_REPONSE + "("
                + COL_QUESTION + ", "
                + COL_BONNE_REPONSE + ", "
                + COL_MAUVAISE_REPONSE_1 + ", "
                + COL_MAUVAISE_REPONSE_2 + ", "
                + COL_THEME + ") VALUES ";

        //
        String[] liste = new String[DATA.length];
        int i = 0;
        for (String questionReponse : DATA) {

            // Instruction SQL INSERT
            liste[i] = insertSQL + "(" + questionReponse + ")";
            i++;
        }

        //
        return liste;
    }

    public QuestionDAO(Context context) {
        super(context);

    }

    public long insert(Question question) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(COL_QUESTION, question.getQuestion());
        values.put(COL_BONNE_REPONSE, question.getReponseVrai());
        values.put(COL_MAUVAISE_REPONSE_1, question.getReponseFausse1());
        values.put(COL_MAUVAISE_REPONSE_2, question.getReponseFausse2());
        values.put(COL_THEME, question.getTheme());

        // Insertion de l'objet dans la BD via le ContentValues
        return getDB().insert(TABLE_QUESTION_REPONSE, null, values);
    }

    public int update(Question question) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(COL_QUESTION, question.getQuestion());
        values.put(COL_BONNE_REPONSE, question.getReponseVrai());
        values.put(COL_MAUVAISE_REPONSE_1, question.getReponseFausse1());
        values.put(COL_MAUVAISE_REPONSE_2, question.getReponseFausse2());
        values.put(COL_THEME, question.getTheme());

        // Insertion de l'objet dans la BD via le ContentValues et l'identifiant
        return getDB().update(TABLE_QUESTION_REPONSE, values, COL_ID + " = " + question.getId(), null);
    }

    public int removeByID(int id) {

        //Suppression d'une question de la BD à partir de l'ID
        return getDB().delete(TABLE_QUESTION_REPONSE, COL_ID + " = " + id, null);
    }

    public int remove(Question question) {

        return removeByID(question.getId());
    }

    public ArrayList<Question> selectAll() {

        //Récupère dans un Cursor les valeur correspondant à des enregistrements de question contenu dans la BD
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_QUESTION_REPONSE, null);

        return cursorToListQuestion(cursor);
    }

    public Question retrieveByID(int id) {

        //Récupère dans un Cursor les valeur correspondant à une question contenu dans la BD à l'aide de son id
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_QUESTION_REPONSE + " WHERE " + COL_ID + "=?", new String[]{Integer.toString(id)});

        return cursorToFirstQuestion(cursor);
    }

    public Question getQuestionRandom() {

        //Récupère dans un Cursor les valeur correspondant à une question au hasard
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_QUESTION_REPONSE + " ORDER BY RANDOM() LIMIT 1", null);

        return cursorToFirstQuestion(cursor);
    }

    //Cette méthode permet de convertir un cursor en une liste de questions
    private ArrayList<Question> cursorToListQuestion(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexQuestion = cursor.getColumnIndex(COL_QUESTION);
        int indexBonneReponse = cursor.getColumnIndex(COL_BONNE_REPONSE);
        int indexMauvaiseReponse1 = cursor.getColumnIndex(COL_MAUVAISE_REPONSE_1);
        int indexMauvaiseReponse2 = cursor.getColumnIndex(COL_MAUVAISE_REPONSE_2);


        // Declaration et initialisation d'une liste de question
        ArrayList<Question> liste = new ArrayList<>();

        while (cursor.moveToNext()) {

            // Création d'une question
            Question question = new Question();
            question.setId(cursor.getInt(indexId));
            question.setQuestion(cursor.getString(indexQuestion));
            question.setReponseVrai(cursor.getString(indexBonneReponse));
            question.setReponseFausse1(cursor.getString(indexMauvaiseReponse1));
            question.setReponseFausse2(cursor.getString(indexMauvaiseReponse2));

            // Ajout dans la liste
            liste.add(question);
        }

        // Fermeture du cursor
        cursor.close();

        //
        return liste;
    }

    //Cette méthode permet de convertir un cursor en une question
    private Question cursorToFirstQuestion(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexQuestion = cursor.getColumnIndex(COL_QUESTION);
        int indexBonneReponse = cursor.getColumnIndex(COL_BONNE_REPONSE);
        int indexMauvaiseReponse1 = cursor.getColumnIndex(COL_MAUVAISE_REPONSE_1);
        int indexMauvaiseReponse2 = cursor.getColumnIndex(COL_MAUVAISE_REPONSE_2);


        // Declaration d'une question
        Question question = null;

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            // Création d'une question
            question = new Question();
            question.setId(cursor.getInt(indexId));
            question.setQuestion(cursor.getString(indexQuestion));
            question.setReponseVrai(cursor.getString(indexBonneReponse));
            question.setReponseFausse1(cursor.getString(indexMauvaiseReponse1));
            question.setReponseFausse2(cursor.getString(indexMauvaiseReponse2));

        }

        // Fermeture du cursor
        cursor.close();

        //
        return question;
    }

    public ArrayList<Question> getQuestionParTheme(String Theme) {
        //Récupère dans un Cursor les valeur correspondant à des enregistrements de question contenu dans la BD
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_QUESTION_REPONSE + " WHERE " + COL_THEME + " = '"+Theme+"'", null);

        return cursorToListQuestion(cursor);
    }
}
