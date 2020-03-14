package com.example.quizzact;

public class Score {
    private int idScore;
    private int idUser;
    private int idQuest;
    private int score;
    private int nbErr;

    public Score() {
    }

    public Score(int idUser, int idQuest, int score, int nbErr) {
        this.idUser = idUser;
        this.idQuest = idQuest;
        this.score = score;
        this.nbErr = nbErr;
    }

    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdQuest() {
        return idQuest;
    }

    public void setIdQuest(int idQuest) {
        this.idQuest = idQuest;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNbErr() {
        return nbErr;
    }

    public void setNbErr(int nbErr) {
        this.nbErr = nbErr;
    }

    @Override
    public String toString() {
        return "Score{" +
                "idScore=" + idScore +
                ", idUser=" + idUser +
                ", idQuest=" + idQuest +
                ", score=" + score +
                ", nbErr=" + nbErr +
                '}';
    }
}
