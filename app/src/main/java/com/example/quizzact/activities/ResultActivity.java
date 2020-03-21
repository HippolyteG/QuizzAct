package com.example.quizzact.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzact.R;

public class ResultActivity extends AppCompatActivity /*implements Parcelable */{


    int score;
    TextView tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvScore=findViewById(R.id.tvScore);

        score=getIntent().getIntExtra("score",0);
        tvScore.setText((String.valueOf(score*20))+"%");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        }, 1500L);
    }



}
