package com.example.quizzact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizzact.classes.Question;
import com.example.quizzact.classes.Reponse;
import com.example.quizzact.classes.Theme;
import com.example.quizzact.classesBDD.QuestionBDD;
import com.example.quizzact.classesBDD.ReponseBDD;
import com.example.quizzact.classesBDD.ThemeBDD;


public class MainActivity extends AppCompatActivity {

    Button buttonPlay;
    Button buttonScore;
    Button buttonSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.buttonPlay = (Button) findViewById(R.id.buttonPlay);
        this.buttonScore = (Button) findViewById(R.id.buttonScore);
        this.buttonSettings = (Button) findViewById(R.id.buttonSettings);


        ///////        THEME

        ThemeBDD themeBDD = new ThemeBDD(this);

        Theme themeAnimaux = new Theme("Animaux");

        themeBDD.open();
        themeBDD.insertTheme(themeAnimaux);
        themeBDD.close();


        //////         REPONSE

        ReponseBDD repBdd = new ReponseBDD(this);

        Reponse rep1 = new Reponse (1,"1");
        Reponse rep2 = new Reponse(1,"2");
        Reponse rep3 = new Reponse (1,"3");
        Reponse rep4 = new Reponse (1,"4");

        repBdd.open();
        repBdd.insertReponse(rep1);
        repBdd.insertReponse(rep2);
        repBdd.insertReponse(rep3);
        repBdd.insertReponse(rep4);
        repBdd.close();


        //////         QUESTION
        QuestionBDD questBdd = new QuestionBDD(this);

        Question quest = new Question("Combien de dents ont les chiens",1);

        questBdd.open();
        questBdd.insertQuestion(quest);
        questBdd.close();








        this.buttonPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuizzActivity.class);
                startActivity(intent);
            }
        });

        this.buttonSettings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ParamsActivity.class);
                startActivity(intent);
            }
        });

        this.buttonScore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ScoreActivity.class);
                startActivity(intent);
            }
        });
    }
}


