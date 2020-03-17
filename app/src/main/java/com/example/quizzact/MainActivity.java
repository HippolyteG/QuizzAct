package com.example.quizzact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

        ThemeBDD themeBDD1 = new ThemeBDD(this);
        Theme theme = new Theme("Capitale");
        //Création d'une instance de la classe LivresBDD
        QuestionBDD questionBDD = new QuestionBDD(this);
        //Création d'un question
        Question question = new Question("Quelle est la capitale de la france ?", 0);
        //On ouvre la base de données pour écrire dedans
        themeBDD1.open();
        questionBDD.open();
        Question myOldQuest = questionBDD.getQuestionAvecLib("Old Question");
        //Si aucun question n'est retourné, c'est le cas à la première exécution de l'application
        if(myOldQuest == null){
            //On affiche un message indiquant que le question n'existe pas dans la BDD
            Toast.makeText(this, "l'ancien question n'existe pas", Toast.LENGTH_LONG).show();
        }
        //Si le question existe, c'est le cas à partir de la deuxième exécuton de l'application
        else{
            //on affiche un message indiquant que le question existe dans la BDD
            Toast.makeText(this, "l'ancien question existe", Toast.LENGTH_LONG).show();
        }
        //On insère le question que l'on vient de créer
        questionBDD.insertQuestion(question);
        //Pour vérifier que l'on a bien créé notre question dans la BDD
        //on extrait le question de la BDD grâce au titre du question que l'on a créé précédemment
        Question questionFromBdd = questionBDD.getQuestionAvecLib(question.getLibQuest());
        //Si un question est retourné (donc si le question à bien été ajouté à la BDD)
        if(questionFromBdd != null){
            //On affiche les infos du question dans un Toast
            Toast.makeText(this, questionFromBdd.toString(), Toast.LENGTH_LONG).show();
            //On modifie le titre du question
            questionFromBdd.setLibQuest("J'ai modifié le titre du question");
            //Puis on met à jour la BDD
            questionBDD.updateQuestion(questionFromBdd.getId(), questionFromBdd);
        }
        //On extrait le question de la BDD grâce au nouveau titre
        questionFromBdd = questionBDD.getQuestionAvecLib("J'ai modifié le titre du question");
        //S'il existe un question possédant ce titre dans la BDD
        if(questionFromBdd != null){
            //On affiche les nouvelles informations du question pour vérifier que le titre du
            //question a bien été mis à jour
            Toast.makeText(this, questionFromBdd.toString(), Toast.LENGTH_LONG).show();
            //on supprime le question de la BDD grâce à son ID
            questionBDD.removeQuestionAvecID(questionFromBdd.getId());
        }
        //On essaye d'extraire de nouveau le question de la BDD toujours grâce à son nouveau
        //titre
        questionFromBdd = questionBDD.getQuestionAvecLib("J'ai modifié le titre du question");
        //Si aucun question n'est retourné
        if(questionFromBdd == null){
            //On affiche un message indiquant que le question n'existe pas dans la BDD
            Toast.makeText(this, "Ce question n'existe pas dans la BDD",
                    Toast.LENGTH_LONG).show();
        }
        //Si le question existe (mais normalement il ne devrait pas)
        else{
            //on affiche un message indiquant que le question existe dans la BDD
            Toast.makeText(this, "Ce question existe dans la BDD", Toast.LENGTH_LONG).show();
        }
        //on crée un question que l'on voudra retrouver à la prochaine exécution de l'application
        Question question2 = new Question("Quelle est la capitale de la Berlgique", 0);
        questionBDD.insertQuestion(question2);
        themeBDD.close();
        questionBDD.close();

       /* LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));
        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_launcher_background);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Notre toast personnalisé avec une image dedans !");
        Toast toast = new Toast(getApplicationContext());
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();*/




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


