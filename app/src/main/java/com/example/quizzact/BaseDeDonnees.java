package com.example.quizzact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper {

    /////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    //THEME
    private static final String TABLE_THEME = "THEME";
    private static final String ID_THEME= "idTheme";
    private static final String LIB_THEME = "libTheme";

    //QUESTION
    private static final String TABLE_QUESTION = "QUESTION";
    private static final String ID_QUESTION = "idQuest";
    private static final String LIB_QUESTION = "libQuest";

    //REPONSE
    private static final String TABLE_REPONSE = "REPONSE";
    private static final String ID_REPONSE = "idRep";
    private static final String LIB_REPONSE = "libRep";

    //BONNE REPONSE
    private static final String TABLE_BONNE_REPONSE = "BONNE_REPONSE";

    //UTILISATEUR
    private static final String TABLE_UTILISATEUR = "UTILISATEUR";
    private static final String ID_UTILISATEUR = "idUser";
    private static final String PSEUDO_UTILISATEUR = "pseudo";
    private static final String MAIL_UTILISATEUR = "mail";
    private static final String PASSWORD_UTILISATEUR = "pwd";

    //SCORE
    private static final String TABLE_SCORE = "SCORE";
    private static final String ID_SCORE = "idScore";
    private static final String SCORE_SCORE = "score";
    private static final String NBERR_SCORE = "nbErr";


    /////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    private static final String CREATE_TABLE_THEME = "create table " + TABLE_THEME + " ("
            + ID_THEME + " integer primary key autoincrement, "
            + LIB_THEME + " text not null);";



    private static final String CREATE_TABLE_QUESTION = "create table " + TABLE_QUESTION + " ("
            + ID_QUESTION +" integer primary key autoincrement,"

            + LIB_QUESTION+ " text not null, "
            + ID_THEME +" integer not null references "+ TABLE_THEME +"("+ID_THEME+"));";



    private static final String CREATE_TABLE_REPONSE = "create table " + TABLE_REPONSE + " ("
            + ID_REPONSE +" integer primary key autoincrement,"
            + ID_QUESTION+" integer not null references "+TABLE_QUESTION+"("+ID_QUESTION+"),"
            + LIB_REPONSE+ " text not null);";



    private static final String CREATE_TABLE_BONNE_REPONSE = "create table " + TABLE_BONNE_REPONSE + " ("
            + ID_REPONSE +" integer not null references "+TABLE_REPONSE+"("+ID_REPONSE+"),"
            + ID_QUESTION+" integer not null references "+TABLE_QUESTION+"("+ID_QUESTION+"),"
            + "primary key ("+ID_QUESTION+","+ID_REPONSE+"));";



    private static final String CREATE_TABLE_UTILISATEUR = "create table " + TABLE_UTILISATEUR + " ("
            + ID_UTILISATEUR +" integer primary key autoincrement,"
            + PSEUDO_UTILISATEUR+" text not null ,"
            + MAIL_UTILISATEUR+ "text not null ,"
            + PASSWORD_UTILISATEUR + " text not null);";


    private static final String CREATE_TABLE_SCORE = "create table " + TABLE_SCORE + " ("
            + ID_SCORE +" integer primary key autoincrement,"
            + ID_UTILISATEUR+" integer not null references "+TABLE_UTILISATEUR+"("+ID_UTILISATEUR+"),"
            + ID_QUESTION+" integer not null references "+TABLE_QUESTION+"("+ID_QUESTION+"),"
            + SCORE_SCORE+" integer not null ,"
            + NBERR_SCORE+" integer not null);";


    /////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////


    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_THEME);
        db.execSQL(CREATE_TABLE_QUESTION);
        db.execSQL(CREATE_TABLE_REPONSE);
        db.execSQL(CREATE_TABLE_BONNE_REPONSE);
        db.execSQL(CREATE_TABLE_UTILISATEUR);
        db.execSQL(CREATE_TABLE_SCORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_THEME + ";");
        db.execSQL("DROP TABLE " + TABLE_QUESTION + ";");
        db.execSQL("DROP TABLE " + TABLE_REPONSE + ";");
        db.execSQL("DROP TABLE " + TABLE_BONNE_REPONSE + ";");
        db.execSQL("DROP TABLE " + TABLE_UTILISATEUR + ";");
        db.execSQL("DROP TABLE " + TABLE_SCORE + ";");
        onCreate(db);
    }
}