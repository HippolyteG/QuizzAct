package com.example.quizzact.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzact.R;
import com.example.quizzact.classes.Question;
import com.example.quizzact.classes.Reponse;
import com.example.quizzact.classesBDD.QuestionBDD;
import com.example.quizzact.classesBDD.ReponseBDD;

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
        reponseBDD.open();
        questionBDD.open();
        Question question;
        int[] tableau = new int [5];
        Reponse[] rep = new Reponse[4];

        for(int i=0;i<5;i++){

            int nombreAleatoire = 1 + (int)(Math.random() * ((questionBDD.countLignes() - 1) + 1));
            tableau[i] = nombreAleatoire;


            System.out.println("LE NOMBRE ALEATOIRE EST "+nombreAleatoire);
            question=questionBDD.getQuestionAvecID(nombreAleatoire);


            if(question!=null){
                tvQuestion.setText(question.getLibQuest());

                rep = reponseBDD.getReponsesLieAQuestion(questionBDD.getQuestionAvecID(nombreAleatoire).getId());

                reponse1.setText(rep[0].getLibRep());
                reponse2.setText(rep[1].getLibRep());
                reponse3.setText(rep[2].getLibRep());
                reponse4.setText(rep[3].getLibRep());




                /*Reponse rep1 = reponseBDD.getReponseAvecID(questionBDD.getQuestionAvecID(nombreAleatoire).getId());
                reponse1.setText(rep1.getLibRep());

                Reponse rep2 = reponseBDD.getReponseAvecID(questionBDD.getQuestionAvecID(nombreAleatoire).getId());
                reponse2.setText(rep2.getLibRep());

                Reponse rep3 = reponseBDD.getReponseAvecID(questionBDD.getQuestionAvecID(nombreAleatoire).getId());
                reponse3.setText(rep3.getLibRep());

                Reponse rep4 = reponseBDD.getReponseAvecID(questionBDD.getQuestionAvecID(nombreAleatoire).getId());
                reponse4.setText(rep4.getLibRep());*/
            }









        }


    }
}
