package com.example.quizzact.activities;

import com.example.quizzact.R;
import com.example.quizzact.audio.MusicService;
import com.example.quizzact.classes.Bonne_Reponse;
import com.example.quizzact.classes.Question;
import com.example.quizzact.classes.Reponse;
import com.example.quizzact.classes.Theme;
import com.example.quizzact.classesBDD.Bonne_ReponseBDD;
import com.example.quizzact.classesBDD.QuestionBDD;
import com.example.quizzact.classesBDD.ReponseBDD;
import com.example.quizzact.classesBDD.ScoreBDD;
import com.example.quizzact.classesBDD.ThemeBDD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    Button buttonPlay;
    Button buttonScore;
    Button buttonSettings;
    Button buttonSon;
    MediaPlayer sounds;
    HomeWatcher homeWatcher;

    static String test="ON";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.buttonPlay = findViewById(R.id.buttonPlay);
        this.buttonScore = findViewById(R.id.buttonScore);
        this.buttonSettings = findViewById(R.id.buttonSettings);
        this.buttonSon = findViewById(R.id.buttonMusic);
        this.sounds =MediaPlayer.create(this,R.raw.sound);

        Intent intent2 = getIntent();
        if(intent2.getStringExtra("buttonSounds")!=null){
            test=intent2.getStringExtra("buttonSounds");
        }




        //BASE DE DONNES
        QuestionBDD questionBDD = new QuestionBDD(this);
        ReponseBDD reponseBDD = new ReponseBDD(this);
        Bonne_ReponseBDD bonne_reponseBDD = new Bonne_ReponseBDD(this);
        ThemeBDD themeBDD = new ThemeBDD(this);

        questionBDD.open();
        reponseBDD.open();
        bonne_reponseBDD.open();
        themeBDD.open();

        //THEMES
        themeBDD.insertTheme(new Theme("Capitales"));
        themeBDD.insertTheme(new Theme("Science"));
        themeBDD.insertTheme(new Theme("Musique"));
        themeBDD.insertTheme(new Theme("Sport"));


        //QUESTIONS
        questionBDD.insertQuestion(new Question("Quelle est la capitale de la France ?",1));
        questionBDD.insertQuestion(new Question("Quelle est la capitale de la Belgique ?",1));
        questionBDD.insertQuestion(new Question("Quelle est la capitale de la Suisse ?",1));
        questionBDD.insertQuestion(new Question("Quelle est la capitale de la Finlande ?",1));
        questionBDD.insertQuestion(new Question("Quelle est la capitale du Canada ?",1));
        questionBDD.insertQuestion(new Question("Quelle est la capitale de la Norvège ?",1));
        questionBDD.insertQuestion(new Question("Quelle est la capitale des Etats-Unis ?",1));
        questionBDD.insertQuestion(new Question("Quelle est la capitale de la Chine ?",1));
        questionBDD.insertQuestion(new Question("Quelle est la capitale du Japon ?",1));
        questionBDD.insertQuestion(new Question("Quelle est la capitale de la Russie ?",1));
        questionBDD.insertQuestion(new Question("Quel âge a la planète Terre ?",2));
        questionBDD.insertQuestion(new Question("Qu'est ce qu'une molécule ?",2));
        questionBDD.insertQuestion(new Question("Le volt est l'unité de ?",2));
        questionBDD.insertQuestion(new Question("Le BCG est un vaccin contre ?",2));
        questionBDD.insertQuestion(new Question("A la température de 15°C, a quelle vitesse atteint-on le mur du son ?",2));
        questionBDD.insertQuestion(new Question("A quoi sert le fluor ?",2));
        questionBDD.insertQuestion(new Question("Quel groupe sanguin est donneur universel ?",2));
        questionBDD.insertQuestion(new Question("Avec quoi joue-t-on au bowling ?",4));
        questionBDD.insertQuestion(new Question("À quelle hauteur se trouve le filet de volley pour une compétition masculine ?",4));
        questionBDD.insertQuestion(new Question("Quel sport est pratiqué lors de Roland Garros ?",4));
        questionBDD.insertQuestion(new Question(" Quel sport a été inventé par un pasteur canadien installé dans le Massachusetts ?",4));
        questionBDD.insertQuestion(new Question(" Quel pays a remporté la Coupe du monde de Football en 1998 ?",4));
        questionBDD.insertQuestion(new Question(" Quel est le nom du vainqueur du tournoi de tennis de Wimbledon en 2005 ?",4));
        questionBDD.insertQuestion(new Question("Combien de fois, Alain Prost a-t-il été champion du monde de Formule 1 ?",4));
        questionBDD.insertQuestion(new Question("Edith Piaf a chanté la chanson ''À quoi ça sert l'amour'' avec ?",3));
        questionBDD.insertQuestion(new Question("Qui a gagné le Grand Prix Eurovision en 1965 avec ''Poupée de cire'' ?",3));
        questionBDD.insertQuestion(new Question("Quel était le surnom de la chanteuse Barbara ?",3));
        questionBDD.insertQuestion(new Question("Comment a-t-on appelé la jeune scène française des années 60 ?",3));
        questionBDD.insertQuestion(new Question("Lequel de ces titres n'appartient pas au répertoire de Charles Aznavour ?",3));
        questionBDD.insertQuestion(new Question("''Le Sud'' est une de ses plus belles mélodies. De quel interprète il s'agit ?",3));
        questionBDD.insertQuestion(new Question("Parmi ces trois chanteurs, lequel n'a pas participé à la comédie musicale ''Notre-Dame de Paris'' ?",3));


        //REPONSES
        reponseBDD.insertReponse(new Reponse(1,"Paris"));
        reponseBDD.insertReponse(new Reponse(1,"Lyon"));
        reponseBDD.insertReponse(new Reponse(1,"Bordeaux"));
        reponseBDD.insertReponse(new Reponse(1,"Strasbourg"));
        reponseBDD.insertReponse(new Reponse(2,"Bruxelles"));
        reponseBDD.insertReponse(new Reponse(2,"Liège"));
        reponseBDD.insertReponse(new Reponse(2,"Anvers"));
        reponseBDD.insertReponse(new Reponse(2,"Gand"));
        reponseBDD.insertReponse(new Reponse(3,"Berne"));
        reponseBDD.insertReponse(new Reponse(3,"Genève"));//10
        reponseBDD.insertReponse(new Reponse(3,"Zurich"));
        reponseBDD.insertReponse(new Reponse(3,"Lucerne"));
        reponseBDD.insertReponse(new Reponse(4,"Helsinski"));
        reponseBDD.insertReponse(new Reponse(4,"Turku"));
        reponseBDD.insertReponse(new Reponse(4,"Oulu"));
        reponseBDD.insertReponse(new Reponse(4,"Tampere"));
        reponseBDD.insertReponse(new Reponse(5,"Ottawa"));
        reponseBDD.insertReponse(new Reponse(5,"Vancouver"));
        reponseBDD.insertReponse(new Reponse(5,"Calgary"));
        reponseBDD.insertReponse(new Reponse(5,"Montréal"));//20
        reponseBDD.insertReponse(new Reponse(6,"Oslo"));
        reponseBDD.insertReponse(new Reponse(6,"Bergen"));
        reponseBDD.insertReponse(new Reponse(6,"Haugesund"));
        reponseBDD.insertReponse(new Reponse(6,"Drammen"));
        reponseBDD.insertReponse(new Reponse(7,"Washington"));
        reponseBDD.insertReponse(new Reponse(7,"San Francisco"));
        reponseBDD.insertReponse(new Reponse(7,"Miami"));
        reponseBDD.insertReponse(new Reponse(7,"New York"));
        reponseBDD.insertReponse(new Reponse(8,"Pékin"));
        reponseBDD.insertReponse(new Reponse(8,"Hong-Kong"));
        reponseBDD.insertReponse(new Reponse(8,"Shanghai"));
        reponseBDD.insertReponse(new Reponse(8,"Wuhan"));
        reponseBDD.insertReponse(new Reponse(9,"Tokyo"));
        reponseBDD.insertReponse(new Reponse(9,"Hiroshima"));
        reponseBDD.insertReponse(new Reponse(9,"Yokohama"));
        reponseBDD.insertReponse(new Reponse(9,"Chiba"));
        reponseBDD.insertReponse(new Reponse(10,"Moscou"));
        reponseBDD.insertReponse(new Reponse(10,"Saint-Pétersbourg"));
        reponseBDD.insertReponse(new Reponse(10,"Perm"));
        reponseBDD.insertReponse(new Reponse(10,"Oufa"));
        reponseBDD.insertReponse(new Reponse(11,"5,57 milliars d'années"));
        reponseBDD.insertReponse(new Reponse(11,"4,57 milliards d'années"));
        reponseBDD.insertReponse(new Reponse(11,"3,87 milliards d'années"));
        reponseBDD.insertReponse(new Reponse(11,"4,87 milliards d'années"));
        reponseBDD.insertReponse(new Reponse(12,"Un assemblage d'atomes"));
        reponseBDD.insertReponse(new Reponse(12,"Un assemblage de cellule"));
        reponseBDD.insertReponse(new Reponse(12,"Le plus petit des éléments"));
        reponseBDD.insertReponse(new Reponse(12,"Un noyau d'atome"));
        reponseBDD.insertReponse(new Reponse(13,"Tension électrique"));
        reponseBDD.insertReponse(new Reponse(13,"Courant électrique"));
        reponseBDD.insertReponse(new Reponse(13,"Résistance électrique"));
        reponseBDD.insertReponse(new Reponse(13,"Capacité de batterie"));
        reponseBDD.insertReponse(new Reponse(14,"La tuberculose"));
        reponseBDD.insertReponse(new Reponse(14,"Le tétanos"));
        reponseBDD.insertReponse(new Reponse(14,"La grippe"));
        reponseBDD.insertReponse(new Reponse(14,"Le COVID-19"));
        reponseBDD.insertReponse(new Reponse(15,"1224 Km/h"));
        reponseBDD.insertReponse(new Reponse(15,"924 Km/h"));
        reponseBDD.insertReponse(new Reponse(15,"1024 Km/h"));
        reponseBDD.insertReponse(new Reponse(15,"1124 Km/h"));
        reponseBDD.insertReponse(new Reponse(16,"O-"));
        reponseBDD.insertReponse(new Reponse(16,"A-"));
        reponseBDD.insertReponse(new Reponse(16,"A+"));
        reponseBDD.insertReponse(new Reponse(16,"AB+"));
        reponseBDD.insertReponse(new Reponse(17,"A lutter contre les caries dentaires"));
        reponseBDD.insertReponse(new Reponse(17,"A nettoyer la bouche"));
        reponseBDD.insertReponse(new Reponse(17,"A ficer le calcium des os et des dents"));
        reponseBDD.insertReponse(new Reponse(17,"A conserver l'émaille des dents"));
        reponseBDD.insertReponse(new Reponse(18,"Une boule"));
        reponseBDD.insertReponse(new Reponse(18,"Une balle"));
        reponseBDD.insertReponse(new Reponse(18,"Un dé"));
        reponseBDD.insertReponse(new Reponse(18,"Un ballon"));
        reponseBDD.insertReponse(new Reponse(19,"2,43 mètres"));
        reponseBDD.insertReponse(new Reponse(19,"2,24 mètres"));
        reponseBDD.insertReponse(new Reponse(19,"1,93 mètres"));
        reponseBDD.insertReponse(new Reponse(19,"2,78 mètres"));
        reponseBDD.insertReponse(new Reponse(20,"Tennis"));
        reponseBDD.insertReponse(new Reponse(20,"Voile"));
        reponseBDD.insertReponse(new Reponse(20,"Judo"));
        reponseBDD.insertReponse(new Reponse(20,"Natation"));
        reponseBDD.insertReponse(new Reponse(21,"Le basket"));
        reponseBDD.insertReponse(new Reponse(21,"Le hockey"));
        reponseBDD.insertReponse(new Reponse(21,"Le curling"));
        reponseBDD.insertReponse(new Reponse(21,"Le saut à ski"));
        reponseBDD.insertReponse(new Reponse(22,"La France"));
        reponseBDD.insertReponse(new Reponse(22,"Le Brésil"));
        reponseBDD.insertReponse(new Reponse(22,"L'Italie"));
        reponseBDD.insertReponse(new Reponse(22,"L'Allemagne"));
        reponseBDD.insertReponse(new Reponse(23,"Roger Federer"));
        reponseBDD.insertReponse(new Reponse(23,"Albert Costa"));
        reponseBDD.insertReponse(new Reponse(23,"Yannick Noah"));
        reponseBDD.insertReponse(new Reponse(23,"Andy Roddick"));
        reponseBDD.insertReponse(new Reponse(24,"4"));
        reponseBDD.insertReponse(new Reponse(24,"8"));
        reponseBDD.insertReponse(new Reponse(24,"2"));
        reponseBDD.insertReponse(new Reponse(24,"5"));
        reponseBDD.insertReponse(new Reponse(25,"Théo Sarapo"));
        reponseBDD.insertReponse(new Reponse(25,"Yves Montand"));
        reponseBDD.insertReponse(new Reponse(25,"Georges Moustaki"));
        reponseBDD.insertReponse(new Reponse(25,"Daniel Lavoie"));
        reponseBDD.insertReponse(new Reponse(26,"France Gall"));
        reponseBDD.insertReponse(new Reponse(26,"Françoise Hardy"));
        reponseBDD.insertReponse(new Reponse(26,"Sylvie Vartan"));
        reponseBDD.insertReponse(new Reponse(26,"Angèle"));
        reponseBDD.insertReponse(new Reponse(27,"La Dame en noir"));
        reponseBDD.insertReponse(new Reponse(27,"La Belle Dame"));
        reponseBDD.insertReponse(new Reponse(27,"La Grande Dame"));
        reponseBDD.insertReponse(new Reponse(27,"La Dame du Lac"));
        reponseBDD.insertReponse(new Reponse(28,"Les yé-yé"));
        reponseBDD.insertReponse(new Reponse(28,"Les yo-yo"));
        reponseBDD.insertReponse(new Reponse(28,"Les ya-ya"));
        reponseBDD.insertReponse(new Reponse(28,"Les yu-yu"));
        reponseBDD.insertReponse(new Reponse(29,"Nantes"));
        reponseBDD.insertReponse(new Reponse(29,"Et pourtant"));
        reponseBDD.insertReponse(new Reponse(29,"Hier Encore"));
        reponseBDD.insertReponse(new Reponse(29,"La Bohème"));
        reponseBDD.insertReponse(new Reponse(30,"Nino Ferrer"));
        reponseBDD.insertReponse(new Reponse(30,"Claude Nougaro"));
        reponseBDD.insertReponse(new Reponse(30,"Léo Ferré"));
        reponseBDD.insertReponse(new Reponse(30,"Carla Bruni"));
        reponseBDD.insertReponse(new Reponse(31,"Patrick Bruel"));
        reponseBDD.insertReponse(new Reponse(31,"Daniel Lavoie"));
        reponseBDD.insertReponse(new Reponse(31,"Patrick Fiori"));
        reponseBDD.insertReponse(new Reponse(31,"Garou"));



        //BONNES REPONSES
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(1,1));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(5,2));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(9,3));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(13,4));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(17,5));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(21,6));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(25,7));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(29,8));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(33,9));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(37,10));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(41,11));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(45,12));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(49,13));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(53,14));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(57,15));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(61,16));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(65,17));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(69,18));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(73,19));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(77,20));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(81,21));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(85,22));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(89,23));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(93,24));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(97,25));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(101,26));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(105,27));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(109,28));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(113,29));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(117,30));
        bonne_reponseBDD.insertBonneReponse(new Bonne_Reponse(121,31));



        //BIND Music Service
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        Intent intent = getIntent();

        //IMPORTANT -> Musique se lance dés le début
        if(intent.getAction()=="android.intent.action.MAIN"){
            startService(music);
        }else if(intent.getStringExtra("buttonMusic")=="ON"){
            startService(music);
        }


        //Start HomeWatcher
        homeWatcher = new HomeWatcher(this);
        homeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });
        homeWatcher.startWatch();




        this.buttonPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(test.equals("ON"))
                sounds.start();

                Intent intent = new Intent(MainActivity.this, QuizzActivity.class);
                if(getIntent().getStringExtra("buttonSounds")!=null){
                    intent.putExtra("buttonSounds",getIntent().getStringExtra("buttonSounds"));
                }
                if(getIntent().getStringExtra("buttonMusic")!=null){
                    intent.putExtra("buttonMusic",getIntent().getStringExtra("buttonMusic"));
                }
                startActivity(intent);
            }
        });

        this.buttonSettings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, ParamsActivity.class);
                Intent intent2 = getIntent();
                if(intent2.getStringExtra("buttonSounds")!=null){

                    if(test.equals("ON")){
                        sounds.start();
                    }
                    intent.putExtra("buttonSounds",intent2.getStringExtra("buttonSounds"));
                }else{
                    sounds.start();
                }

                if(intent2.getStringExtra("buttonMusic")!=null){
                    intent.putExtra("buttonMusic",intent2.getStringExtra("buttonMusic"));
                }

                startActivity(intent);
            }
        });

        this.buttonScore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println(test);
                if(test.equals("ON"))
                sounds.start();

                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                if(getIntent().getStringExtra("buttonSounds")!=null){
                    intent.putExtra("buttonSounds",getIntent().getStringExtra("buttonSounds"));
                }
                if(getIntent().getStringExtra("buttonMusic")!=null){
                    intent.putExtra("buttonMusic",getIntent().getStringExtra("buttonMusic"));
                }
                startActivity(intent);
            }
        });


    }





    private boolean mIsBound = false;
    private MusicService mServ;

    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon,Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();

        if (mServ != null) {
            if(intent.getStringExtra("buttonMusic")!=null){
                if(intent.getStringExtra("buttonMusic").equals("ON"))
                    mServ.resumeMusic();
            }else{
                mServ.resumeMusic();
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        //Detect idle screen
        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //UNBIND music service
        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);

    }

}


