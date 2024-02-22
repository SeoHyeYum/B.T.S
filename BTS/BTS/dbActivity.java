package com.example.java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class dbActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        //*db이름과 버전 명시
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Place_DB.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = (TextView) findViewById(R.id.result);

        final EditText etTheme1 = (EditText) findViewById(R.id.theme1);
        final EditText etTheme2 = (EditText) findViewById(R.id.theme2);

        // DB에 데이터 추가
        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String S_Theme1 = etTheme1.getText().toString();
                String S_Theme2 = etTheme2.getText().toString();

                dbHelper.insert(S_Theme1, S_Theme2);
                result.setText(dbHelper.getResult());
            }
        });

        //DB에 있는 데이터 수정
        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                String S_Theme1 = etTheme1.getText().toString();
                String S_Theme2 = etTheme2.getText().toString();

                dbHelper.update(S_Theme1, S_Theme2);
                result.setText(dbHelper.getResult());
            }
        });

        // DB에 있는 데이터 삭제
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String S_Theme1 = etTheme1.getText().toString();
                String S_Theme2 = etTheme2.getText().toString();
                dbHelper.delete(S_Theme1, S_Theme2);
                result.setText(dbHelper.getResult());
            }
        });

        // DB에 있는 데이터 조회
        Button select = (Button) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(dbHelper.getResults());
            }
        });


    }
}