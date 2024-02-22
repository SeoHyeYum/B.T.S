package com.example.java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Result extends AppCompatActivity {
    int count;
    private Button btn_end;
    Random rnd = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        btn_end = findViewById(R.id.btn_end);
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Place_DB.db", null, 1);
        final TextView theme = (TextView) findViewById(R.id.s_theme);
        final TextView theme2 = (TextView) findViewById(R.id.s_theme2);
        theme.setText(dbHelper.userResult());
        theme2.setText(dbHelper.userResult2());
        Intent i = getIntent();

        TextView t = findViewById(R.id.textView4);

        String text = i.getStringExtra("a");
        String place[] = text.split(",");

        String x = "";

        int r1 =rnd.nextInt(place.length);
        int r2 =rnd.nextInt(place.length);
        int r3 =rnd.nextInt(place.length);
        int r4 =rnd.nextInt(place.length);

        String []rand = new String[4];

        rand[0] = place[r1];
        rand[1] = place[r2];
        rand[2] = place[r3];
        rand[3] = place[r4];

        for(int n = 0; n<4; n++)
            x += rand[n] + "-";

        t.setText(x);

        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String S_Theme1 = theme.getText().toString();
                String S_Theme2 = theme2.getText().toString();
                dbHelper.delete(S_Theme1, S_Theme2);    //user_table 삭제

                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
}