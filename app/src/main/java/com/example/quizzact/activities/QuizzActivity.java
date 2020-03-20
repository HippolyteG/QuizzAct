package com.example.quizzact.activities;

import android.app.Activity;
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
        Bonne_ReponseBDD bonne_reponseBDD = new Bonne_ReponseBDD(this);

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


            System.out.println("LE NOMBRE ALEATOIRE EST "+nombreAleatoire);
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
        reponse1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

    }
}
