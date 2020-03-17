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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.buttonPlay = (Button) findViewById(R.id.buttonPlay);


        ///////        THEME

        ThemeBDD themeBDD = new ThemeBDD(this);

        Theme animaux = new Theme("animaux");


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

        //questBdd.open();
        //questBdd.close();








        this.buttonPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuizzActivity.class);
                startActivity(intent);
            }
        });

    }
}
