package com.example.quizzact.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzact.R;
import com.example.quizzact.classes.Bonne_Reponse;
import com.example.quizzact.classes.Question;
import com.example.quizzact.classes.Reponse;
import com.example.quizzact.classesBDD.Bonne_ReponseBDD;
import com.example.quizzact.classesBDD.QuestionBDD;
import com.example.quizzact.classesBDD.ReponseBDD;

import java.util.Arrays;
import java.util.Collections;

public class QuizzActivity extends Activity {

    TextView tvNumQuestion;
    TextView tvQuestion;
    Button reponse1;
    Button reponse2;
    Button reponse3;
    Button reponse4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        tvNumQuestion = findViewById(R.id.numQuestion);
        tvQuestion = findViewById(R.id.question);
        reponse1 = findViewById(R.id.reponse1);
        reponse2 = findViewById(R.id.reponse2);
        reponse3 = findViewById(R.id.reponse3);
        reponse4 = findViewById(R.id.reponse4);

        QuestionBDD questionBDD = new QuestionBDD(this);
        ReponseBDD reponseBDD = new ReponseBDD(this);
        Bonne_ReponseBDD bonne_reponseBDD = new Bonne_ReponseBDD(QuizzActivity.this);

        bonne_reponseBDD.open();
        reponseBDD.open();
        questionBDD.open();


        Question question;
        Reponse[] rep = new Reponse[4];
        Bonne_Reponse bonne_reponse = new Bonne_Reponse();
        int[] tableau = new int [5];

        /*int random = (int)(Math.random() * ((4-1) + 1)) +1;*/

        for(int i=0;i<5;i++){

            int nombreAleatoire = 1 + (int)(Math.random() * ((questionBDD.countLignes() - 1) + 1));
            tableau[i] = nombreAleatoire;



            question=questionBDD.getQuestionAvecID(nombreAleatoire);
            bonne_reponse=bonne_reponseBDD.getBonneReponseAvecIDQuestion(nombreAleatoire);

            if(question!=null){
                tvQuestion.setText(question.getLibQuest());

                rep = reponseBDD.getReponsesLieAQuestion(questionBDD.getQuestionAvecID(nombreAleatoire).getId());
                //melange le tableau pour faire changer de place les réponses.
                Collections.shuffle(Arrays.asList(rep));
                reponse1.setText(rep[0].getLibRep());
                reponse2.setText(rep[1].getLibRep());
                reponse3.setText(rep[2].getLibRep());
                reponse4.setText(rep[3].getLibRep());

            }

        }
        final Reponse rep1 = reponseBDD.getReponseAvecLibRep(reponse1.getText().toString());
        final Reponse rep2 = reponseBDD.getReponseAvecLibRep(reponse2.getText().toString());
        final Reponse rep3 = reponseBDD.getReponseAvecLibRep(reponse3.getText().toString());
        final Reponse rep4 = reponseBDD.getReponseAvecLibRep(reponse4.getText().toString());

        reponse1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Bonne_ReponseBDD bonne_reponseBDD = new Bonne_ReponseBDD(QuizzActivity.this);
                bonne_reponseBDD.open();
                QuestionBDD qbdd= new QuestionBDD(QuizzActivity.this);
                qbdd.open();
                Question q = qbdd.getQuestionAvecLib(tvQuestion.getText().toString());
                if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep1.getIdRep()){
                    reponse1.setBackgroundColor(Color.GREEN);
                    reponse2.setBackgroundColor(Color.RED);
                    reponse3.setBackgroundColor(Color.RED);
                    reponse4.setBackgroundColor(Color.RED);
                }

            }
        });
        reponse2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bonne_ReponseBDD bonne_reponseBDD = new Bonne_ReponseBDD(QuizzActivity.this);
                bonne_reponseBDD.open();
                QuestionBDD qbdd = new QuestionBDD(QuizzActivity.this);
                qbdd.open();
                Question q = qbdd.getQuestionAvecLib(tvQuestion.getText().toString());

                if (bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep() == rep2.getIdRep()) {
                    reponse1.setBackgroundColor(Color.RED);
                    reponse2.setBackgroundColor(Color.GREEN);
                    reponse3.setBackgroundColor(Color.RED);
                    reponse4.setBackgroundColor(Color.RED);
                }
            }

        });
        reponse3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bonne_ReponseBDD bonne_reponseBDD = new Bonne_ReponseBDD(QuizzActivity.this);
                bonne_reponseBDD.open();
                QuestionBDD qbdd= new QuestionBDD(QuizzActivity.this);
                qbdd.open();
                Question q = qbdd.getQuestionAvecLib(tvQuestion.getText().toString());
                if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep3.getIdRep()){
                    reponse1.setBackgroundColor(Color.RED);
                    reponse2.setBackgroundColor(Color.RED);
                    reponse3.setBackgroundColor(Color.GREEN);
                    reponse4.setBackgroundColor(Color.RED);
                }

            }
        });
        reponse4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bonne_ReponseBDD bonne_reponseBDD = new Bonne_ReponseBDD(QuizzActivity.this);
                bonne_reponseBDD.open();
                QuestionBDD qbdd= new QuestionBDD(QuizzActivity.this);
                qbdd.open();
                Question q = qbdd.getQuestionAvecLib(tvQuestion.getText().toString());
                if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep4.getIdRep()){
                    reponse1.setBackgroundColor(Color.RED);
                    reponse2.setBackgroundColor(Color.RED);
                    reponse3.setBackgroundColor(Color.RED);
                    reponse4.setBackgroundColor(Color.GREEN);
                }

            }
        });

    }
}
