package com.example.quizzact;

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
import android.view.View;
import android.widget.Button;

import androidx.annotation.ColorInt;
import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

public class ParamsActivity extends AppCompatActivity /*implements Parcelable */{

    Button buttonSon;
    MediaPlayer mediaplayer;
    HomeWatcher homeWatcher;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params );
        this.buttonSon = (Button) findViewById(R.id.buttonSon);

        if(mServ.isPlaying()){
            System.out.println("SALUT");
            buttonSon.setText("ON");
        }else{
            System.out.println("TAMERE");
            buttonSon.setText("OFF");
        }
        /*this.buttonSon.setText("ON");*/


        /*this.mediaplayer = getIntent().getParcelableExtra("salut");
        mediaplayer.start();
*/
        doBindService();
        final Intent music = new Intent();
        music.setClass(this,MusicService.class);
        startService(music);







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


        this.buttonSon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(buttonSon.getText()=="ON"){
                    buttonSon.setText("OFF");
                    mServ.pauseMusic();
                }else {
                    buttonSon.setText("ON");
                    mServ.resumeMusic();
                }

            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ParamsActivity.this,MainActivity.class);
        intent.putExtra("buttonSon",buttonSon.getText());
        startActivity(intent);
        super.onBackPressed();

    }




    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
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

        if (mServ != null) {
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
