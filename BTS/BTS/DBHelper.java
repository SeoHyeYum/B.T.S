package com.example.java;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //*num,select_theme1,select_theme2
    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 user_table, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        select_theme1 문자열 컬럼, select_theme2 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE user_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, select_theme1 TEXT, select_theme2 TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insert(String select_theme1, String select_theme2) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO user_table VALUES(null,  '" + select_theme1 + "', '" + select_theme2 + "');");
        db.close();
    }

    public void update(String select_theme1, String select_theme2) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE user_table SET gu=" + select_theme1 + " WHERE select_theme2='" + select_theme2 + "';");
        db.execSQL("UPDATE user_table SET theme1=" + select_theme2 + " WHERE select_theme1='" + select_theme1 + "';");
        db.close();
    }

    public void delete(String select_theme1, String select_theme2) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM user_table WHERE select_theme1='" + select_theme1 + "';");
        db.execSQL("DELETE FROM user_table WHERE select_theme2='" + select_theme2 + "';");

        db.close();
    }

    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 user_table테이블에 있는 모든 데이터 출력
        //user_table은 사용자가 선택한 테마가 담겨있는 테이블
        Cursor cursor = db.rawQuery("SELECT * FROM user_table", null);
        //넘버:장소|구|테마1|테마2|door|with
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getString(2)
                    + " \n ";
        }
        return result;
    }


    public String getResults() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";


        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        //bts_table은 만들어져있는 테이블을 가지고 온것
        //자동으로 값이 증가하는 _id 정수형 기본키 컬럼과 place, theme1, theme2, door 등의 문자열로 구성되어있음
        Cursor cursor = db.rawQuery("SELECT * FROM bts_table ", null);
        //넘버:장소|테마1|테마2|door
        while (cursor.moveToNext()) {
            result +=  cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getString(2)
                    + " | "
                    + cursor.getString(3)
                    + " | "
                    + cursor.getString(4)
                    + " \n ";
        }
        return result;
    }

    //실내인 경우의 여행지 선택
    public String InResults() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";


        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        //bts_table은 만들어져있는 테이블을 가지고 온것

        Cursor cursor = db.rawQuery("SELECT * FROM bts_table WHERE (theme1 = (SELECT select_theme1 FROM user_table) OR theme1 = (SELECT select_theme2 FROM user_table) OR theme2 = (SELECT select_theme1 FROM user_table) OR theme2 = (SELECT select_theme2 FROM user_table)) AND door = 'in'", null);
        //넘버:장소|테마1|테마2|door
        while (cursor.moveToNext()) {
            result +=  cursor.getString(1)  //colum 1의 값인 place 결과만 result에 저장한다.
                    + ",";
        }
        return result;
    }

    //실내외인 경우 여행지 선택
    public String InOutResults() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";


        ArrayList<String> a = new ArrayList<String>(); //장소(문자열)을 저장할 Array 생성

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        //bts_table은 만들어져있는 테이블을 가지고 온것
        //자동으로 값이 증가하는 _id 정수형 기본키 컬럼과 place, theme1, theme2, door 등의 문자열로 구성되어있음
        Cursor cursor = db.rawQuery("SELECT * FROM bts_table WHERE theme1 = (SELECT select_theme1 FROM user_table) OR theme1 = (SELECT select_theme2 FROM user_table) OR theme2 = (SELECT select_theme1 FROM user_table) OR theme2 = (SELECT select_theme2 FROM user_table)", null);
        //넘버:장소|테마1|테마2|door
        while (cursor.moveToNext()) {
            result +=  cursor.getString(1) //colum 1의 값인 place 결과만 result에 저장한다.
                    + ",";
        }
        return result;
    }

}