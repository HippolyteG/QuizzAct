package com.example.quizzact.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quizzact.R;
import com.example.quizzact.classes.Question;
import com.example.quizzact.classesBDD.QuestionBDD;

public class QuizzActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

    }
}
