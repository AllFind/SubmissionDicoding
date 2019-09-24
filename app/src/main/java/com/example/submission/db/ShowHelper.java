package com.example.submission.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.submission.model.Show;

import java.util.ArrayList;

public class ShowHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_FAV_TV;
    private static DatabaseHelper databaseHelper;
    private static ShowHelper INSTANCE;

    private static SQLiteDatabase database;

    private ShowHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static ShowHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ShowHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen()) {
            database.close();
        }
    }

    public Show getShow(int id) {
        Show show = null;
        Cursor cursor = database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + DatabaseContract.FavoriteTVColumns._ID + "=" + id, null);
        if (cursor.moveToFirst()) {
            show = new Show(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.DESCRIPTION)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.RATING)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.PHOTO)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.DATE)));
        }
        return show;
    }

    public long insertFavShow(Show show) {
        ContentValues args = new ContentValues();
        args.put(DatabaseContract.FavoriteTVColumns.TITLE, show.getTitle());
        args.put(DatabaseContract.FavoriteTVColumns.DATE, show.getDate());
        args.put(DatabaseContract.FavoriteTVColumns.DESCRIPTION, show.getDescription());
        args.put(DatabaseContract.FavoriteTVColumns.PHOTO, show.getPhoto());
        args.put(DatabaseContract.FavoriteTVColumns.RATING, show.getRating());
        args.put(DatabaseContract.FavoriteTVColumns._ID, show.getID());

        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteFavShow(int id) {
        return database.delete(DatabaseContract.TABLE_FAV_TV, DatabaseContract.FavoriteTVColumns._ID + " = '" + id + "'", null);
    }

    public ArrayList<Show> getAllFavShow() {
        ArrayList<Show> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null, null, null, null, null, null);
        cursor.moveToFirst();
        Show show;
        if (cursor.getCount() > 0) {
            do {
                show = new Show(
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.DESCRIPTION)),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.RATING)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.PHOTO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns._ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.DATE)));

                arrayList.add(show);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
}
