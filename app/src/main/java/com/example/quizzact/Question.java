package com.example.quizzact;

public class Question {
    private int id;
    private String label;
    private int idTheme;

    public Question() {
    }

    public Question(String label, int idRep) {
        this.label = label;
        this.idTheme = idRep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getIdRep() {
        return idTheme;
    }

    public void setIdRep(int idRep) {
        this.idTheme = idRep;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", idRep=" + idTheme +
                '}';
    }
}
