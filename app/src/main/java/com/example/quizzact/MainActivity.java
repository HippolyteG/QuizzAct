package com.example.quizzact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
}


