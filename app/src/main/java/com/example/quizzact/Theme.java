package com.example.quizzact;

import androidx.annotation.NonNull;

public class Theme {
    private int idTheme;
    private String libTheme;

    public Theme(){

    }

    public Theme(String libTheme){
        this.libTheme=libTheme;
    }

    public int getIdTheme(){return this.idTheme;}

    public void setIdTheme(int idTheme){this.idTheme=idTheme;}

    public String getLibTheme(){
        return this.libTheme;
    }

    public void setLibTheme(String libTheme){
        this.libTheme=libTheme;
    }


    @Override
    public String toString() {
        return "Thème{" +
                "idTheme=" + idTheme +
                ", libTheme=" + libTheme +
                '}';
    }
}
