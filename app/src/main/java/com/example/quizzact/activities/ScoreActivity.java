package com.example.quizzact.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.MatrixCursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzact.R;
import com.example.quizzact.classes.Theme;
import com.example.quizzact.classesBDD.ScoreBDD;
import com.example.quizzact.classesBDD.ThemeBDD;

public class ScoreActivity extends AppCompatActivity {
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        final String [] col1 = {"col1:ligne1","col1:ligne2","col1:ligne3","col1:ligne4",    "col1:ligne5"};
        final String [] col2 = {"col2:ligne1","col2:ligne2","col2:ligne3","col2:ligne4","col2:ligne5"};

        TableLayout table = (TableLayout) findViewById(R.id.idTable);
        TableRow row; // ligne
        TextView tv1,tv2; //Case

        ScoreBDD scoreBDD= new ScoreBDD(this);
        scoreBDD.open();

        for(int i=1;i<=scoreBDD.countLignes();i++) {

            row = new TableRow(this);

            tv1 = new TextView(this);
            if(scoreBDD.getScoreWithID(i)!=null){
                tv1.setText(scoreBDD.getScoreWithID(i).getScore());
            }
            tv1.setGravity(Gravity.CENTER); // centrage dans la cellule
            tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );

            if((i==1)&&(scoreBDD.getScoreWithID(i)==null)){
                tv1.setText("Vous n'avez pas encore joué !");
            }

            row.addView(tv1);
           /* row.setBackgroundColor(R.color.Black);*/

            row.setPadding(0,40,0,40);
            table.addView(row);
        }

        scoreBDD.close();
    }



}