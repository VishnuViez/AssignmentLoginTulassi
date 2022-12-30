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
    private static final String DATABASE_NAME = "details.db";
    private final static String tableusers = "users";
    private final static String tableprofession = "professions";
    private final static String radiooptions = "radiooptions";
    private final static String imagetable = "image";
    private final static String videotable = "video";

    public DbHelper(Context context) {
        super(context, "details.db", null, 1);
    }

    //creating tables
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table " +tableusers+" (username text primary key, password text, name text, phone text)");
        MyDB.execSQL("create Table " +tableprofession+" (options text)");
        MyDB.execSQL("create Table " +radiooptions+" (radio text)");
        MyDB.execSQL("create Table " +imagetable+" (image byte)");
        MyDB.execSQL("create Table " +videotable+" (video)");
        //MyDB.execSQL("create Table " +options+" (options)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists " + tableusers);
        MyDB.execSQL("drop Table if exists " + tableprofession);
        MyDB.execSQL("drop Table if exists " + radiooptions);
        MyDB.execSQL("drop Table if exists " + imagetable);
        MyDB.execSQL("drop Table if exists " + videotable);
        onCreate(MyDB);
    }

    public void insertProfessionValues() {
        //String query = "INSERT INTO " + tableprofession;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", "manager");
        values.put("title", "developer");
        values.put("title", "associate");
        values.put("title", "intern");
        values.put("title", "tester");
        values.put("title", "researcher");
        //db.execSQL(query, new String[]{tableprofession});
        db.insert(tableprofession, null, values);
        db.close();
    }

    /**
     * Getting all labels
     * returns list of labels
     *
     * @return*/
    public List<String> getAllLabels() {
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + tableprofession;

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("manager",tableprofession);
        values.put("developer",tableprofession);
        values.put("associate",tableprofession);
        values.put("intern",tableprofession);
        values.put("tester",tableprofession);
        values.put("researcher",tableprofession);
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public void addListItem(ArrayList<String> listItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        listItem.add("manager");
        for (int i = 0; i < listItem.size(); i++) {

            Log.e("value inserting==", "" + listItem.get(i));
            values.put(tableprofession, listItem.get(i));
            db.insert(tableprofession, null, values);

        }

        db.close(); // Closing database connection
    }

    public Cursor getListItem() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "Select * from " + tableprofession;

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
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
    public List<String> getProfessionsListData(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> labels = new ArrayList<String>();
        ContentValues cv = new ContentValues();
        cv.put("web",title);
        cv.put("",title);
        cv.put("",title);
        cv.put("",title);
        cv.put("",title);
        cv.put("",title);
        cv.put("",title);
        cv.put("",title);
        labels.add(title);
        long result = db.insert("profession",null,cv);
        // looping through all rows and adding to list
        db.close();

        return labels;

    }
}
