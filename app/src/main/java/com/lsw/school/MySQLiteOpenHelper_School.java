package com.lsw.school;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper_School extends SQLiteOpenHelper {

    public MySQLiteOpenHelper_School(Context context, String name,
                                     SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table school (" +
                "idx integer primary key autoincrement, " +
                "num text, " +
                "name text, " +
                "date text, " +
                "price text, " +
                "many text, " +
                "person text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql ="drop table if exists school";
        db.execSQL(sql);    //기존의 동일한 이름의 테이블이 있다면 지움
        onCreate(db); // 테이블을 지웠으므로 다시 테이블을 만들어주는 과정
    }
}
