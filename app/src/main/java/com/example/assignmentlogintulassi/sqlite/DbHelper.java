package com.example.assignmentlogintulassi.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "login_details.db";
    private final static String tableusers = "users";
    private final static String tableprofession = "profession";
    private final static String title = "title";


    private final static String colusers = "username";
    private final static String colpass = "password";
    private final static String colname = "name";
    private final static String colphone = "phone";


    public String getTableprofession() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+tableprofession, null, null);
        while (cursor.moveToNext()) {
            String[] values = {"Web Developer", "Computer Support Specialist", "Computer Hardware Engineer", "Computer & Information Research Scientist",
                    "Big Data Engineer", "Software Systems Developer", "Blockchain Developer", "Software Applications Developer", "Computer Network Architect",
                    "Information Security Analyst", "Computer Systems Analyst", "Database Administrator", "Network & Computer System Administrators"};
            cursor.close();
        }
        return getTableprofession();
    }

    public DbHelper(Context context) {
        super(context, "login_details.db", null, 1);
    }

    //creating tables
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table " +tableusers+" (username text primary key, password text, name text, phone text)");
        MyDB.execSQL("create Table " +tableprofession+" (title text primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists " + tableusers);
        MyDB.execSQL("drop Table if exists " + tableprofession);
        onCreate(MyDB);
    }

    public void insertProfessionValues(Data data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(title, data.getTitle());
        db.insert(tableprofession, null, contentValues);

    }

    public Cursor getProfessionValues() {
        SQLiteDatabase db = getReadableDatabase();
        /*for (String value : values) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("Web Developer", value);
            contentValues.put("Computer Support Specialist", value);
            contentValues.put("Computer Hardware Engineer", value);
            contentValues.put("Computer & Information Research Scientist", value);
            contentValues.put("Big Data Engineer", value);
            contentValues.put("Software Systems Developer", value);
            contentValues.put("Blockchain Developer", value);
            contentValues.put("Software Applications Developer", value);
            contentValues.put("Computer Network Architect", value);
            contentValues.put("Information Security Analyst", value);
            contentValues.put("Computer Systems Analyst", value);
            contentValues.put("Database Administrator", value);
            contentValues.put("Network & Computer System Administrators", value);*/
       // }
            return db.query(tableprofession, null, null, null, null, null, null);

        }



    public Cursor fetch(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //String selectQuery = "select * from " + tableusers + "order by " + colusers + " desc";
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users " , null);
        return cursor;

    }

    public Boolean insertData(String username, String password, String name, String phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public void insertVideo(String table_name, Object o, ContentValues values) {

    }

    @SuppressLint("Range")
    public List<String> getProfessionsListData(){
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + tableprofession;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return labels;

    }
}
