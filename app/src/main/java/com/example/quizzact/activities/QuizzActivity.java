package com.example.quizzact.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzact.R;
import com.example.quizzact.classes.Question;
import com.example.quizzact.classes.Reponse;
import com.example.quizzact.classesBDD.Bonne_ReponseBDD;
import com.example.quizzact.classesBDD.QuestionBDD;
import com.example.quizzact.classesBDD.ReponseBDD;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizzActivity extends Activity {

    TextView tvNumQuestion;
    TextView tvQuestion;
    Button reponse1;
    Button reponse2;
    Button reponse3;
    Button reponse4;
    int numQuestion;
    MediaPlayer sounds;

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

        sounds=MediaPlayer.create(this,R.raw.sound);

        QuestionBDD questionBDD = new QuestionBDD(this);
        ReponseBDD reponseBDD = new ReponseBDD(this);
        reponseBDD.open();
        questionBDD.open();
        Question question;
        Reponse[] rep;
        ArrayList<Integer> listeQuestions = new ArrayList<>();

            Intent intentTest = getIntent();
            if(intentTest.getIntegerArrayListExtra("listeQuestions")!=null){
                listeQuestions=intentTest.getIntegerArrayListExtra("listeQuestions");
            }
            if(intentTest.getIntExtra("numQuestion",numQuestion)!=0){
                numQuestion=intentTest.getIntExtra("numQuestion",numQuestion);
            }

           if(numQuestion!=5){

                tvNumQuestion.setText("Question "+(numQuestion+1)+"/5");
                System.out.println("Le numéro de la question est "+numQuestion);
                int nombreAleatoire = 1 + (int)(Math.random() * ((questionBDD.countLignes() - 1) + 1));
                while(listeQuestions.contains(nombreAleatoire)){
                    nombreAleatoire = 1 + (int)(Math.random() * ((questionBDD.countLignes() - 1) + 1));
                }
                listeQuestions.add(nombreAleatoire);



                question=questionBDD.getQuestionAvecID(nombreAleatoire);

                if(question!=null) {
                    tvQuestion.setText(question.getLibQuest());

                    rep = reponseBDD.getReponsesLieAQuestion(questionBDD.getQuestionAvecID(nombreAleatoire).getId());

                    Collections.shuffle(Arrays.asList(rep));


                    reponse1.setText(rep[0].getLibRep());
                    reponse2.setText(rep[1].getLibRep());
                    reponse3.setText(rep[2].getLibRep());
                    reponse4.setText(rep[3].getLibRep());

                }
            }else{
               Intent intent = new Intent(QuizzActivity.this,MainActivity.class);
               startActivity(intent);
           }





                final Reponse rep1 = reponseBDD.getReponseAvecLibRep(reponse1.getText().toString());
                final Reponse rep2 = reponseBDD.getReponseAvecLibRep(reponse2.getText().toString());
                final Reponse rep3 = reponseBDD.getReponseAvecLibRep(reponse3.getText().toString());
                final Reponse rep4 = reponseBDD.getReponseAvecLibRep(reponse4.getText().toString());

                final ArrayList<Integer> finalListeQuestions = listeQuestions;






                reponse1.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v){
                        numQuestion+=1;
                        sounds.start();
                        Intent intent = new Intent(QuizzActivity.this,QuizzActivity.class);
                        intent.putExtra("listeQuestions", finalListeQuestions);
                        intent.putExtra("numQuestion",numQuestion);

                        Bonne_ReponseBDD bonne_reponseBDD = new Bonne_ReponseBDD(QuizzActivity.this);
                        bonne_reponseBDD.open();
                        QuestionBDD qbdd= new QuestionBDD(QuizzActivity.this);
                        qbdd.open();
                        Question q = qbdd.getQuestionAvecLib(tvQuestion.getText().toString());

                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep1.getIdRep()){
                            reponse1.setBackgroundColor(Color.GREEN);
                        }
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep2.getIdRep()){
                            reponse1.setBackgroundColor(Color.GRAY);
                            reponse2.setBackgroundColor(Color.GREEN);
                        }
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep3.getIdRep()){
                            reponse1.setBackgroundColor(Color.GRAY);
                            reponse3.setBackgroundColor(Color.GREEN);
                        }
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep4.getIdRep()){
                            reponse1.setBackgroundColor(Color.GRAY);
                            reponse4.setBackgroundColor(Color.GREEN);
                        }
                        finish();
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                });
                reponse2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        numQuestion+=1;
                        sounds.start();
                        Intent intent = new Intent(QuizzActivity.this,QuizzActivity.class);
                        intent.putExtra("listeQuestions", finalListeQuestions);
                        intent.putExtra("numQuestion",numQuestion);
                        Bonne_ReponseBDD bonne_reponseBDD = new Bonne_ReponseBDD(QuizzActivity.this);
                        bonne_reponseBDD.open();
                        QuestionBDD qbdd = new QuestionBDD(QuizzActivity.this);
                        qbdd.open();
                        Question q = qbdd.getQuestionAvecLib(tvQuestion.getText().toString());

                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep1.getIdRep()){
                            reponse1.setBackgroundColor(Color.GREEN);
                            reponse2.setBackgroundColor(Color.GRAY);
                        }
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep2.getIdRep()){
                            reponse2.setBackgroundColor(Color.GREEN);
                        }
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep3.getIdRep()){
                            reponse2.setBackgroundColor(Color.GRAY);
                            reponse3.setBackgroundColor(Color.GREEN);
                        }
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep4.getIdRep()){
                            reponse2.setBackgroundColor(Color.GRAY);
                            reponse4.setBackgroundColor(Color.GREEN);
                        }
                        finish();
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }

                });
                reponse3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        numQuestion+=1;
                        sounds.start();
                        Intent intent = new Intent(QuizzActivity.this,QuizzActivity.class);
                        intent.putExtra("listeQuestions", finalListeQuestions);
                        intent.putExtra("numQuestion",numQuestion);
                        Bonne_ReponseBDD bonne_reponseBDD = new Bonne_ReponseBDD(QuizzActivity.this);
                        bonne_reponseBDD.open();
                        QuestionBDD qbdd= new QuestionBDD(QuizzActivity.this);
                        qbdd.open();
                        Question q = qbdd.getQuestionAvecLib(tvQuestion.getText().toString());
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep1.getIdRep()){
                            reponse1.setBackgroundColor(Color.GREEN);
                            reponse3.setBackgroundColor(Color.GRAY);
                        }
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep2.getIdRep()){
                            reponse2.setBackgroundColor(Color.GREEN);
                            reponse3.setBackgroundColor(Color.GRAY);
                        }
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep3.getIdRep()){
                            reponse3.setBackgroundColor(Color.GREEN);
                        }
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep4.getIdRep()){
                            reponse3.setBackgroundColor(Color.GRAY);
                            reponse4.setBackgroundColor(Color.GREEN);
                        }
                        finish();
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                });

                reponse4.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        numQuestion+=1;
                        sounds.start();
                        Intent intent = new Intent(QuizzActivity.this,QuizzActivity.class);
                        intent.putExtra("listeQuestions", finalListeQuestions);
                        intent.putExtra("numQuestion",numQuestion);

                        Bonne_ReponseBDD bonne_reponseBDD = new Bonne_ReponseBDD(QuizzActivity.this);
                        bonne_reponseBDD.open();
                        QuestionBDD qbdd= new QuestionBDD(QuizzActivity.this);
                        qbdd.open();
                        Question q = qbdd.getQuestionAvecLib(tvQuestion.getText().toString());
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep1.getIdRep()){
                            reponse1.setBackgroundColor(Color.GREEN);
                            reponse4.setBackgroundColor(Color.GRAY);
                        }
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep2.getIdRep()){
                            reponse2.setBackgroundColor(Color.GREEN);
                            reponse4.setBackgroundColor(Color.GRAY);
                        }
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep3.getIdRep()){
                            reponse3.setBackgroundColor(Color.GREEN);
                            reponse4.setBackgroundColor(Color.GRAY);
                        }
                        if(bonne_reponseBDD.getBonneReponseAvecIDQuestion(q.getId()).getIdRep()==rep4.getIdRep()){
                            reponse4.setBackgroundColor(Color.GREEN);
                        }

                        finish();
                        startActivity(intent);
                        overridePendingTransition(0, 0);

                    }
                });


        }

}
