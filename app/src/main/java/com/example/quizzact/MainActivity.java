package com.example.quizzact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PowerManager;
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
    Button buttonSon;

    MediaPlayer mediaPlayer;
    HomeWatcher homeWatcher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.buttonPlay = findViewById(R.id.buttonPlay);
        this.buttonScore = findViewById(R.id.buttonScore);
        this.buttonSettings = findViewById(R.id.buttonSettings);
        this.buttonSon = findViewById(R.id.buttonSon);

        /*this.mediaPlayer = MediaPlayer.create(this,R.raw.musique);
        mediaPlayer.start();*/

        //BIND Music Service
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        Intent intent = getIntent();
        System.out.println(intent);
        System.out.println("SALUT");
        if(intent.getAction()=="android.intent.action.MAIN"){
            startService(music);
        }else if(intent.getStringExtra("buttonSon")=="ON"){
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


        /*ThemeBDD themeBdd= new ThemeBDD(this);
        themeBdd.open();
        Theme theme = themeBdd.getThemeWithID(2);
        String test = theme.getLibTheme();
        Toast.makeText(this, test, Toast.LENGTH_LONG).show();*/

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


    /*@Override
    protected void onRestart() {
        super.onRestart();
        this.mediaPlayer.start();
    }*/


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
            if(intent.getStringExtra("buttonSon")=="ON")
            mServ.resumeMusic();
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


