package com.example.quizzact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UtilisateurBDD {
    public class LivresBDD {
        private static final int VERSION_BDD = 1;
        private static final String NOM_BDD = "quizzAct.db";
        private static final String TABLE_UTILISATEUR = "UTILISATEUR";
        private static final String ID_UTILISATEUR = "idUser";
        private static final int NUM_ID_UTILISATEUR = 0;
        private static final String PSEUDO_UTILISATEUR = "pseudo";
        private static final int NUM_PSEUDO_UTILISATEUR = 1;
        private static final String MAIL_UTILISATEUR = "mail";
        private static final int NUM_MAIL_UTILISATEUR = 2;
        private static final String PASSWORD_UTILISATEUR = "pwd";
        private static final int NUM_PASSWORD_UTILISATEUR = 3;

        private SQLiteDatabase bdd;
        private BaseDeDonnees baseDeDonnees;
        public LivresBDD(Context context){
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
        public long insertUtilisateur(Utilisateur utilisateur){
            //Création d'un ContentValues (fonctionne comme une HashMap)
            ContentValues values = new ContentValues();
            //on lui ajoute une valeur associée à une clé
            values.put(PSEUDO_UTILISATEUR, utilisateur.getPseudo());
            values.put(MAIL_UTILISATEUR, utilisateur.getMail());
            values.put(PASSWORD_UTILISATEUR, utilisateur.getPassword());
            //on insère l'objet dans la BDD via le ContentValues
            return bdd.insert(TABLE_UTILISATEUR, null, values);
        }
        public int updateUtilisateur(int id, Utilisateur utilisateur){
            //La mise à jour d'un livre
            //on précise quel livre on doit mettre à jour grâce à l'ID
            ContentValues values = new ContentValues();
            values.put(PSEUDO_UTILISATEUR, utilisateur.getPseudo());
            values.put(MAIL_UTILISATEUR, utilisateur.getMail());
            values.put(PASSWORD_UTILISATEUR, utilisateur.getPassword());
            return bdd.update(TABLE_UTILISATEUR, values, ID_UTILISATEUR + " = " +id, null);
        }
        public int removeUtilisateurWithID(int id){
            //Suppression d'un livre de la BDD grâce à l'ID
            return bdd.delete(TABLE_UTILISATEUR, ID_UTILISATEUR + " = " +id, null);
        }
        public Utilisateur getUtilisateurWithPseudo(String pseudo){
            //On récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD
            Cursor c = bdd.query(TABLE_UTILISATEUR, new String[] {ID_UTILISATEUR,PSEUDO_UTILISATEUR, MAIL_UTILISATEUR,PASSWORD_UTILISATEUR},
                    PSEUDO_UTILISATEUR + " LIKE \"" + pseudo +"\"", null, null, null, null);
            return cursorToUtilisateur(c);
        }
        //Cette méthode permet de convertir un cursor en un livre
        private Utilisateur cursorToUtilisateur(Cursor c){
            //si aucun élément n'a été retourné dans la requête, on renvoie null
            if (c.getCount() == 0)
                return null;
            //Sinon on se place sur le premier élément
            c.moveToFirst();
            //On créé un livre
            Utilisateur utilisateur = new Utilisateur();
            //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
            utilisateur.setIdUser(c.getInt(NUM_ID_UTILISATEUR));
            utilisateur.setPseudo(c.getString(NUM_PSEUDO_UTILISATEUR));
            utilisateur.setMail(c.getString(NUM_MAIL_UTILISATEUR));
            utilisateur.setPassword(c.getString(NUM_PASSWORD_UTILISATEUR));
            //On ferme le cursor
            c.close();
            //On retourne le livre
            return utilisateur;
        }
    }
}
