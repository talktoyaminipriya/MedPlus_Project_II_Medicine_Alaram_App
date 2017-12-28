package com.example.priya.medplus;

/**
 * Created by Priya on 12/27/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";


    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_DESCR = "descr";
    public static final String CONTACTS_COLUMN_PRICE = "price";

    public static final String CONTACTS_TABLE_SHOP = "websites";
    public static final String CONTACTS_COLUMN_WEB = "websitename";
    public static final String CONTACTS_COLUMN_ID_SHOP = "shopid";
    private HashMap hp;

    public static final String CONTACTS_TABLE_ALARAM = "alarams";
    public static final String CONTACTS_COLUMN_ALARAMNAME = "alaramname";
    public static final String CONTACTS_COLUMN_ID_ALARAM = "alaramid";
    public static final String CONTACTS_COLUMN_DATE_ = "alaramdate";
    public static final String CONTACTS_COLUMN_TIME = "alaramtime";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key, name text,price text,descr text)"
        );

        db.execSQL(
                "create table websites " +
                        "(shopid integer primary key, websitename text)"
        );

        db.execSQL(
                "create table alarams " +
                        "(alaramid integer primary key, alaramname text,alaramdate date,alaramtime time)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("DROP TABLE IF EXISTS websites");
        db.execSQL("DROP TABLE IF EXISTS alarams");
        onCreate(db);
    }

    public boolean insertContact (String name, String price, String descr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("descr", descr);

        db.insert("contacts", null, contentValues);
        return true;
    }

    public boolean insertShop (String wname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("websitename", wname);


        db.insert("websites", null, contentValues);
        return true;
    }

    public boolean insertAlaram (String name, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("alaramname", name);
        contentValues.put("alaramdate", date);
        contentValues.put("alaramtime", time);

        db.insert("alarams", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public Cursor getShopData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res2 =  db.rawQuery( "select * from contacts where shopid="+id+"", null );
        return res2;
    }

    public Cursor getAlaramData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res3 =  db.rawQuery( "select * from contacts where alaramid="+id+"", null );
        return res3;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public int numberOfRowsShop(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_SHOP);
        return numRows;
    }

    public int numberOfRowsAlaram(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_ALARAM);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String price, String descr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("descr", descr);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public boolean updateContactShop (Integer id, String wname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("websitename", wname);
        db.update("websites", contentValues, "shopid = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateContactAlaram (Integer id, String aname,String date,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("alaramname", aname);
        db.update("alarams", contentValues, "alaramid = ? ", new String[] { Integer.toString(id) } );
        return true;
    }


    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer deleteContactShop (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("websites",
                "shopid = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer deleteContactAlaram (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("alarams",
                "alaramid = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllCotactsShop() {
        ArrayList<String> array_list2 = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res2 =  db.rawQuery( "select * from websites", null );
        res2.moveToFirst();

        while(res2.isAfterLast() == false){
            array_list2.add(res2.getString(res2.getColumnIndex(CONTACTS_COLUMN_WEB)));
            res2.moveToNext();
        }
        return array_list2;
    }

    public ArrayList<String> getAllAlarams() {
        ArrayList<String> array_list3 = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res3 =  db.rawQuery( "select * from alarams", null );
        res3.moveToFirst();

        while(res3.isAfterLast() == false){
            array_list3.add(res3.getString(res3.getColumnIndex(CONTACTS_COLUMN_ALARAMNAME)));
            res3.moveToNext();
        }
        return array_list3;
    }
}