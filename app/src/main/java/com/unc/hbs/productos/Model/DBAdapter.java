package com.unc.hbs.productos.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hbs on 2/10/16.
 */

public class DBAdapter {
    static final String KEY_ROWID = "id";
    public static final String USER = "user";
    public static final String PASS = "pass";
    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "Product";
    static final String TABLENAME = "Users";
    static final int DATABASE_VERSION = 7;

    static final String DATABASE_CREATE =
            "create table "+TABLENAME+ " (" + USER + " TEXT, " + PASS +" TEXT)";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter (Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            }
            catch(SQLException e){

                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " +
                    newVersion + ", which will " +
                    "destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return null;
    }

    //closes the database
    public void close()
    {
        DBHelper.close();
    }

    //insert contacts
    public long insertUser(Usuario usuario)
    {
        String passMd5=getMD5(usuario.getPass());
        ContentValues initialValues = new ContentValues();
        initialValues.put(USER, usuario.getUser());
        initialValues.put(PASS, getMD5(usuario.getPass()));
        return db.insert(TABLENAME,null, initialValues);
    }
    public Cursor getAllUsers() {
        return DBHelper.getReadableDatabase()
                .query(
                        TABLENAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getUserUser(String user) {
        String columns[] = new String[]{USER, PASS};
        String selection = USER + " LIKE ?"; // WHERE id LIKE ?
        String selectionArgs[] = new String[]{user};
        return DBHelper.getReadableDatabase()
                .query(
                        TABLENAME,
                        columns,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null);
    }

    public static String getMD5(final String s) {
        try{
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for(int i = 0; i < messageDigest.length; i++){
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while(h.length() < 2){
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        }catch (NoSuchAlgorithmException e){
            Log.e("MD5", "md5() NoSuchAlgorithmException: " + e.getMessage());
        }
        return "";
    }
}
