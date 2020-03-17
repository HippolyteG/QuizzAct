package com.example.quizzact.classesBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizzact.BaseDeDonnees;
import com.example.quizzact.classes.Question;

public class QuestionBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "quizzAct.db";

    private static final String TABLE_QUESTION = "QUESTION";
    private static final String COL_ID_QUEST = "idQuest";
    private static final int NUM_COL_ID_QUEST = 0;
    private static final String COL_LIB_QUEST = "libQuest";
    private static final int NUM_COL_LIB_QUEST = 1;
    private static final String COL_ID_THEME = "idTheme";
    private static final int NUM_COL_ID_THEME = 2;
    private SQLiteDatabase bdd;
    private BaseDeDonnees baseDeDonnees;

    public QuestionBDD(Context context){
        //On crée la BDD et sa table
        baseDeDonnees = new BaseDeDonnees(context, NOM_BDD, null, VERSION_BDD);
    }
    public void open(){
        //on ouvre la BDD en écriture
        bdd = baseDeDonnees.getWritableDatabase();
    }
    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }
    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertQuestion(Question question){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé
        values.put(COL_LIB_QUEST, question.getLibQuest());
        values.put(COL_ID_THEME, question.getIdTheme());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_QUESTION, null, values);
    }
    public int updateQuestion(int id, Question question){
        //La mise à jour d'une question
        //on précise quelle question on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_LIB_QUEST, question.getLibQuest());
        values.put(COL_ID_THEME, question.getIdTheme());
        return bdd.update(TABLE_QUESTION, values, COL_ID_QUEST + " = " +id, null);
    }

    public int removeQuestionAvecID(int id){
        //Suppression d'une question de la BDD grâce à l'ID
        return bdd.delete(TABLE_QUESTION, COL_ID_QUEST + " = " +id, null);
    }
    public Question getQuestionAvecLib(String libQuest){
        //On récupère dans un Cursor les valeurs correspondant à une question contenu dans la BDD
        //(ici on sélectionne la question grâce à son titre)
        Cursor c = bdd.query(TABLE_QUESTION, new String[] {COL_ID_QUEST, COL_LIB_QUEST, COL_ID_THEME},
                COL_LIB_QUEST + " LIKE \"" + libQuest +"\"", null, null, null, null);
        return cursorToQuestion(c);
    }
    //Cette méthode permet de convertir un cursor en une question
    private Question cursorToQuestion(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Question question = new Question();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        question.setId(c.getInt(NUM_COL_ID_QUEST));
        question.setLibQuest(c.getString(NUM_COL_LIB_QUEST));
        question.setIdTheme(c.getInt(NUM_COL_ID_THEME));
        //On ferme le cursor
        c.close();
        //On retourne la question
        return question;
    }
}
