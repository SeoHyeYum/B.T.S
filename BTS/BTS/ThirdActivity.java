package com.example.java;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ThirdActivity extends AppCompatActivity {

    private Button btn_select;
    private String name[]=new String[30];   //선택된 checkBox의 이름을 배열에 저장한다.
    private int number=0;   //선택된 checkBox의 개수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Place_DB.db", null, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        // DB에 데이터 추가, 다음창으로 이동
        btn_select = (Button) findViewById(R.id.btn_next);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, WeatherActivity.class);
                String S_Theme1 = name[0];
                String S_Theme2 = name[1];

                if(number != 2) {   //테마를 선택하지 않으면 띄우는 메세지창
                    AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(ThirdActivity.this);
                    // alert의 title과 Messege 세팅
                    myAlertBuilder.setTitle("경고");
                    myAlertBuilder.setMessage("테마는 2개를 선택하여 주십시오. ");
                    // OK 버튼 추가
                    myAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // OK 버튼을 눌렸을 경우
                            Toast.makeText(getApplicationContext(), "확인",
                                    Toast.LENGTH_SHORT).show();
                            number = 0; //checkBox 개수 초기화
                        }
                    });
                    // Alert를 생성해주고 보여주는 메소드(show를 선언해야 Alert가 생성됨)
                    myAlertBuilder.show();
                }
                else {
                    dbHelper.insert(S_Theme1, S_Theme2); //선택한 테마 db에 삽입
                    startActivity(intent); //액티비티 이동
                }
            }
        });



    }
    public void onCheckboxClicked(View v) {
        boolean checked=((CheckBox)v).isChecked();
        switch(v.getId()){
            case R.id.checkBox:
                if(checked){
                    name[number]="온천";
                    number++;
                }
                break;
            case R.id.checkBox2:
                if(checked){
                    name[number]="힐링";
                    number++;
                }
                break;
            case R.id.checkBox3:
                if(checked){
                    name[number]="바다";
                    number++;
                }
                break;
            case R.id.checkBox4:
                if(checked){
                    name[number]="절";
                    number++;
                }
                break;
            case R.id.checkBox5:
                if(checked){
                    name[number]="전망대";
                    number++;
                }
                break;
            case R.id.checkBox6:
                if(checked){
                    name[number]="역사";
                    number++;
                }
                break;

            case R.id.checkBox7:
                if(checked){
                    name[number]="산";
                    number++;
                }
                break;

            case R.id.checkBox8:
                if(checked){
                    name[number]="포토존";
                    number++;
                }
                break;

            case R.id.checkBox9:
                if(checked){
                    name[number]="도보코스";
                    number++;
                }
                break;

            case R.id.checkBox10:
                if(checked){
                    name[number]="식물원";
                    number++;
                }
                break;

            case R.id.checkBox11:
                if(checked){
                    name[number]="관람";
                    number++;
                }
                break;

            case R.id.checkBox12:
                if(checked){
                    name[number]="공원";
                    number++;
                }
                break;

            case R.id.checkBox13:
                if(checked){
                    name[number]="자연";
                    number++;
                }
                break;

            case R.id.checkBox14:
                if(checked){
                    name[number]="문화재";
                    number++;
                }
                break;

            case R.id.checkBox15:
                if(checked){
                    name[number]="기념물";
                    number++;
                }
                break;

            case R.id.checkBox16:
                if(checked){
                    name[number]="맛집투어";
                    number++;
                }
                break;

            case R.id.checkBox17:
                if(checked){
                    name[number]="체험";
                    number++;
                }
                break;

            case R.id.checkBox18:
                if(checked){
                    name[number]="경치";
                    number++;
                }
                break;

            case R.id.checkBox19:
                if(checked){
                    name[number]="액티비티";
                    number++;
                }
                break;

        }

    }
}