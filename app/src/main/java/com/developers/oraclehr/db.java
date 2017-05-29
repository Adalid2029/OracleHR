package com.developers.oraclehr;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 25/05/17.
 */

public class db {
    DBHelper helper;
    SQLiteDatabase db;
    public db(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void runSQL(String sql){
        db.execSQL(sql);
    }
    /* El metodo rawQuery() es el encargado de recibir el resultado de una consulta
   *  previamente hecha y lo devuelve en un Cursor*/
    public Cursor verTablas(){
        return db.rawQuery("select name from sqlite_master where type ='table';",null);
        //"select name from sqlite_master where type ='table';"
    }
    public static class DBHelper extends SQLiteOpenHelper{
        public static String DB_NAME = "default.db";
        public static int DB_VERSION = 1;
        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
}
