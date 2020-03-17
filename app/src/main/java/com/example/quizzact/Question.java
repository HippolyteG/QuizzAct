package com.example.quizzact;

public class Question {
    private int idQuest;
    private String libQuest;
    private int idTheme;

    public Question() {
    }

    public Question(String libQuest, int idRep) {
        this.libQuest = libQuest;
        this.idTheme = idRep;
    }

    public int getId() {
        return idQuest;
    }

    public void setId(int id) {
        this.idQuest = id;
    }

    public String getLibQuest() {
        return libQuest;
    }

    public void setLibQuest(String libQuest) {
        this.libQuest = libQuest;
    }

    public int getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(int idRep) {
        this.idTheme = idRep;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + idQuest +
                ", label='" + libQuest + '\'' +
                ", idRep=" + idTheme +
                '}';
    }
}
