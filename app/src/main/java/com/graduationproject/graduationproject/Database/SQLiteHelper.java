package com.graduationproject.graduationproject.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String Medicine, String Clock, String TimeArrival , String Note){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO MEDICINE VALUES (NULL, ?, ?, ? , ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, Medicine);
        statement.bindString(2, Clock);
        statement.bindString(3, TimeArrival);
        statement.bindString(4, Note);
        statement.executeInsert();
    }

    public void updateData(String Medicine, String Clock, String TimeArrival , String Note , int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE MEDICINE SET Medicine = ?, Clock = ?, TimeArrival =? , Note=? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, Medicine);
        statement.bindString(2, Clock);
        statement.bindString(3, TimeArrival);
        statement.bindString(4, Note);
        statement.bindDouble(5, (double)id);
        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM MEDICINE WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
