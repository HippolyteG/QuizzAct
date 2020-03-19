package com.example.quizzact;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

public class ParamsActivity extends AppCompatActivity /*implements Parcelable */{

    Button buttonSon;
    MediaPlayer mediaplayer;
    HomeWatcher homeWatcher;
    private boolean mIsBound = false;
    private MusicService mServ;
    public static final String KEY_BUNDLE_BUTTON_SON = "buttonSon";
    public String test;

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
        doBindService();
        final Intent music = new Intent();
        music.setClass(this,MusicService.class);


        if (savedInstanceState != null) {
            buttonSon.setText(savedInstanceState.getString(test));
        }


        Intent intent=getIntent();
       if(intent.getStringExtra("buttonSon")!=null){

            buttonSon.setText(intent.getStringExtra("buttonSon"));
            System.out.println(""+intent.getStringExtra("buttonSon"));
        }




        this.buttonSon.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(String.valueOf(buttonSon.getText()).equals("ON")){
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
        Bundle bundle = intent.getExtras();
        onSaveInstanceState(bundle);
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
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJj" +
                "JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ" +
                "JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ "+String.valueOf(buttonSon.getText()));
        savedInstanceState.putString(KEY_BUNDLE_BUTTON_SON, String.valueOf(buttonSon.getText()));

        onRestoreInstanceState(savedInstanceState);


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        buttonSon.setText(savedInstanceState.getString(KEY_BUNDLE_BUTTON_SON));


    }
    @Override
    protected void onResume() {
        super.onResume();

        if ((mServ != null)&&(buttonSon.getText()=="ON")) {
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
