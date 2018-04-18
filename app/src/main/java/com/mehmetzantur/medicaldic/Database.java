package com.mehmetzantur.medicaldic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.sql.SQLException;

/**
 * Created by mehme on 11.05.2016.
 */
public class Database {
    private static final String DATABASE_NAME = "medicaldicDB";
    private static final String DATABASE_TABLE = "Words";
    private static final int DATABASE_VERSION = 1;

    private final Context myContext;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase myDb;

    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_TITLE = "Title";
    public static final String KEY_DEFINITION = "Definition";


    public Database(Context c) {
        this.myContext = c;
    }

    public Database connOpen() throws SQLException {
        dbHelper = new DatabaseHelper(myContext);
        myDb = dbHelper.getWritableDatabase();
        return this;
    }

    public void connClose() {
        dbHelper.close();
    }

    public void SaveWord(String title, String defi) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, title);
        cv.put(KEY_DEFINITION, defi);
        myDb.insert(DATABASE_TABLE, null, cv);
    }


    public ArrayAdapter<String> GetAllList(Context context){
        String[] columns = new String[]{KEY_ROW_ID, KEY_TITLE, KEY_DEFINITION};
        Cursor c = myDb.query(DATABASE_TABLE, columns, null, null, null, null, null);
        int keyRowId = c.getColumnIndex(KEY_ROW_ID);
        int keyTitle = c.getColumnIndex(KEY_TITLE);
        int keyDefi = c.getColumnIndex(KEY_DEFINITION);

        String arrayRec[] = new String[c.getCount()];
        int counter = 0;
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            arrayRec[counter] = c.getString(keyRowId) + "   " + c.getString(keyTitle) + "   " + c.getString(keyDefi);
            counter++;
        }

        ArrayAdapter AA = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,arrayRec);
        return AA;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                            KEY_ROW_ID + " INTEGER PRIMARY KEY, " +
                            KEY_TITLE + " TEXT NOT NULL, " +
                            KEY_DEFINITION + " TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

}
