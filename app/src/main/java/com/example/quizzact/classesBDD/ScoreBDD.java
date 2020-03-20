package com.example.quizzact.classesBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizzact.BaseDeDonnees;
import com.example.quizzact.classes.Score;

public class ScoreBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "quizzAct.db";
    private static final String TABLE_SCORE = "SCORE";

    private static final String ID_SCORE = "idScore";
    private static final int NUM_IDSCORE = 0;

    private static final String ID_UTILISATEUR = "idUser";
    private static final int NUM_IDUSER = 1;

    private static final String ID_QUESTION = "idQuest";
    private static final int NUM_IDQUEST = 2;

    private static final String SCORE_SCORE = "score";
    private static final int NUM_SCORE = 3;

    private static final String NBERR_SCORE = "nbErr";
    private static final int NUM_NBERR = 4;


    private SQLiteDatabase bdd;
    private BaseDeDonnees baseDeDonnees;
    public ScoreBDD(Context context){
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
    public long insertScore(Score score){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé
        values.put(ID_UTILISATEUR, score.getIdUser());
        values.put(ID_QUESTION, score.getIdQuest());
        values.put(SCORE_SCORE, score.getScore());
        values.put(NBERR_SCORE, score.getNbErr());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_SCORE, null, values);
    }
    public int updateScore(int id, Score score){
        //La mise à jour d'un livre
        //on précise quel livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(ID_UTILISATEUR, score.getIdUser());
        values.put(ID_QUESTION, score.getIdQuest());
        values.put(SCORE_SCORE, score.getScore());
        values.put(NBERR_SCORE, score.getNbErr());
        return bdd.update(TABLE_SCORE, values, ID_SCORE + " = " +id, null);
    }
    public int removeScoreWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_SCORE, ID_SCORE + " = " +id, null);
    }


    public Score getScoreWithScore(int score){
        //On récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD
        Cursor c = bdd.query(TABLE_SCORE, new String[] {ID_SCORE, ID_UTILISATEUR, ID_QUESTION,SCORE_SCORE,NBERR_SCORE},
                SCORE_SCORE + " LIKE \"" + score +"\"", null, null, null, null);
        return cursorToScore(c);
    }

    public Score getScoreWithID(int id){
        //On récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD
        Cursor c = bdd.query(TABLE_SCORE, new String[] {ID_SCORE, ID_UTILISATEUR, ID_QUESTION,SCORE_SCORE,NBERR_SCORE},
                ID_SCORE + " LIKE \"" + id +"\"", null, null, null, null);
        return cursorToScore(c);
    }

    public int countLignes(){
        int nbLignes=0;
        nbLignes=(int) DatabaseUtils.queryNumEntries(bdd,TABLE_SCORE);
        return nbLignes;
    }



    //Cette méthode permet de convertir un cursor en un livre
    private Score cursorToScore(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Score score = new Score();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        score.setIdScore(c.getInt(NUM_IDSCORE));
        score.setIdUser(c.getInt(NUM_IDUSER));
        score.setIdQuest(c.getInt(NUM_IDQUEST));
        score.setScore(c.getInt(NUM_IDQUEST));
        score.setNbErr(c.getInt(NUM_IDQUEST));
        //On ferme le cursor
        c.close();
        //On retourne le livre
        return score;
    }
}
