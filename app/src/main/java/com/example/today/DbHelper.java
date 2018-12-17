package com.example.today;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DbHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Today.db";
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Database.CreateDB._CREATE1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Database.CreateDB._TABLENAME1);

            onCreate(db);
        }
    }

    public DbHelper(Context context) {
        this.mCtx = context;
    }

    public DbHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        mDB = mDBHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        mDB.close();
    }

    public void insert(String text,String hour,String bun) {

        SQLiteStatement p = mDB.compileStatement("insert into todaytable(hour,minute,memo) values(?,?,?)");

        p.bindString(1, text);
        p.bindString(2, hour);
        p.bindString(3, bun);
        p.execute();
    }

    public Cursor getAllColumns() {

        return mDB.query(Database.CreateDB._TABLENAME1, null, null, null, null, null, null);

    }
    public void updateset(int id,String content) {
        SQLiteStatement p = mDB.compileStatement("update capsuleSample set content=? where _ID="+id);

        p.bindString(1, content);
        p.execute();
    }
}
