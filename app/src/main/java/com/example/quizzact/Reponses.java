package com.example.quizzact;

public class Reponses {
    private int idRep;
    private int idQuest;
    private String label;

    public Reponses() {
    }

    public Reponses(int idQuest, String label) {
        this.idQuest = idQuest;
        this.label = label;
    }

    public int getIdRep() {
        return idRep;
    }

    public void setIdRep(int idRep) {
        this.idRep = idRep;
    }

    public int getIdQuest() {
        return idQuest;
    }

    public void setIdQuest(int idQuest) {
        this.idQuest = idQuest;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Reponses{" +
                "idRep=" + idRep +
                ", idQuest=" + idQuest +
                ", label='" + label + '\'' +
                '}';
    }
}
