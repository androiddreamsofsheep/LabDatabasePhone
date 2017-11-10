package edu.dlsu.mobapde.labdatabasephone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by G301 on 11/7/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String SCHEMA = "phones";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // ROLE : create the tables of this schema
        // will only be called once at the start by the system
        // when there is no db, it is called by the system

        /*
        * CREATE TABLE phone (
        *   _id INTEGER AUTOINCREMENT PRIMARY KEY,
        *   size TEXT,
        *   resolution INTEGER,
        *   manufacturer TEXT
        *   );
        * */

        String sql = "CREATE TABLE " + Phone.TABLE_NAME + " ("
                + Phone.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Phone.COLUMN_SIZE + " TEXT, "
                + Phone.COLUMN_RESOLUTION + " INTEGER, "
                + Phone.COLUMN_MANUFACTURER + " TEXT "
                + ");";
        db.execSQL(sql);

        addPhone(db, new Phone("200x300", 300, "Cherry"));
        addPhone(db, new Phone("300x400", 330, "Huawei"));
        // do not close conn
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int i, int i1) {
        // will only be called if there is change in version numbers

        // you want to migrate to the new db
        // drop the current tables
        String sql = "DROP TABLE IF EXISTS " + Phone.TABLE_NAME + ";";
        db.execSQL(sql);

        // call onCreate to get the latest db design
        onCreate(db);
    }

    // add phone
    public boolean addPhone(Phone phone){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Phone.COLUMN_SIZE, phone.getSize());
        contentValues.put(Phone.COLUMN_RESOLUTION, phone.getResolution());
        contentValues.put(Phone.COLUMN_MANUFACTURER, phone.getManufacturer());

        // id = -1 if fail
        long id = db.insert(Phone.TABLE_NAME,
                null,
                contentValues);
        db.close();
        return (id != -1);
    }

    public boolean addPhone(SQLiteDatabase db, Phone phone){

        ContentValues contentValues = new ContentValues();
        contentValues.put(Phone.COLUMN_SIZE, phone.getSize());
        contentValues.put(Phone.COLUMN_RESOLUTION, phone.getResolution());
        contentValues.put(Phone.COLUMN_MANUFACTURER, phone.getManufacturer());

        // id = -1 if fail
        long id = db.insert(Phone.TABLE_NAME,
                null,
                contentValues);
        db.close();
        return (id != -1);
    }

    // edit phone
    public boolean editPhone(long currentId, Phone newPhoneDetails){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Phone.COLUMN_SIZE, newPhoneDetails.getSize());
        contentValues.put(Phone.COLUMN_RESOLUTION, newPhoneDetails.getResolution());
        contentValues.put(Phone.COLUMN_MANUFACTURER, newPhoneDetails.getManufacturer());

        int rows = db.update(Phone.TABLE_NAME,
                contentValues,
                Phone.COLUMN_ID + "=?",
                new String[]{currentId+""});
        db.close();
        return rows >0;
    }

    // remove phone
    public boolean removePhone(long id){
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(Phone.TABLE_NAME,
                Phone.COLUMN_ID + " =?",
                new String[]{id+""});
        db.close();
        return rows > 0;
    }

    // retrieve phone
    public Phone getPhone(long id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Phone.TABLE_NAME,
                null, // SELECT *
                Phone.COLUMN_ID + " =? ", // where clause
                new String[]{id+""}, // where args
                null, // groupby
                null, // having
                null); // orderby

        Phone p = null;
        if(c.moveToFirst()){
            p = new Phone();
            String size= c.getString(c.getColumnIndex(Phone.COLUMN_SIZE));
            int resolution = c.getInt(c.getColumnIndex(Phone.COLUMN_RESOLUTION));
            String manufacturer = c.getString(c.getColumnIndex(Phone.COLUMN_MANUFACTURER));

            p.setSize(size);
            p.setResolution(resolution);
            p.setManufacturer(manufacturer);
            p.setId(id);
        }

        c.close();
        db.close();

        return p;
    }

    // retrieve all phones
    public ArrayList<Phone> getAllPhones(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c= db.query(Phone.TABLE_NAME, null,null,null,null,null,null);

        ArrayList<Phone> phones = null;

        if(c.moveToFirst()){
            do {
                Phone p = null;
                String size= c.getString(c.getColumnIndex(Phone.COLUMN_SIZE));
                int resolution = c.getInt(c.getColumnIndex(Phone.COLUMN_RESOLUTION));
                String manufacturer = c.getString(c.getColumnIndex(Phone.COLUMN_MANUFACTURER));
                long id = c.getLong(c.getColumnIndex(Phone.COLUMN_ID));

                p.setSize(size);
                p.setResolution(resolution);
                p.setManufacturer(manufacturer);
                p.setId(id);
            }while(c.moveToNext());
        }

        c.close();
        db.close();

        return phones;
    }

    public Cursor getAllPhonesCursor(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Phone.TABLE_NAME, null,null,null,null,null,null);
    }
}





