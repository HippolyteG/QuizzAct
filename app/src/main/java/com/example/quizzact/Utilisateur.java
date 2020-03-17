package com.example.quizzact;

public class Utilisateur {
    private int idUser;
    private String pseudo;
    private String mail;
    private String password;

    public Utilisateur(){

    }

    public Utilisateur(String pseudo,String mail,String password){
        this.pseudo=pseudo;
        this.mail=mail;
        this.password=password;
    }

    public int getIdUser(){return this.idUser;}

    public void setIdUser(int idUser){this.idUser=idUser;}

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUser=" + idUser +
                ", pseudo=" + pseudo +
                ", mail=" + mail +
                ", password=" + password +
                '}';
    }



}
