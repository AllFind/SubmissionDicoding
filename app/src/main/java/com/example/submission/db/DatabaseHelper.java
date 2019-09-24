package com.example.submission.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_FAVORITE_MOVIE =
            String.format("CREATE TABLE %s"
                            + "(%s INT PRIMARY KEY,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s DECIMAL(5,2) NOT NULL)",
                    DatabaseContract.TABLE_FAV_MOVIE,
                    DatabaseContract.FavoriteMovieColumns._ID,
                    DatabaseContract.FavoriteMovieColumns.TITLE,
                    DatabaseContract.FavoriteMovieColumns.DESCRIPTION,
                    DatabaseContract.FavoriteMovieColumns.PHOTO,
                    DatabaseContract.FavoriteMovieColumns.DATE,
                    DatabaseContract.FavoriteMovieColumns.RATING);
    private static final String SQL_CREATE_TABLE_TV_MOVIE =
            String.format("CREATE TABLE %s"
                            + "(%s INT PRIMARY KEY,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s DECIMAL(5,2) NOT NULL)",
                    DatabaseContract.TABLE_FAV_TV,
                    DatabaseContract.FavoriteTVColumns._ID,
                    DatabaseContract.FavoriteTVColumns.TITLE,
                    DatabaseContract.FavoriteTVColumns.DESCRIPTION,
                    DatabaseContract.FavoriteTVColumns.PHOTO,
                    DatabaseContract.FavoriteTVColumns.DATE,
                    DatabaseContract.FavoriteTVColumns.RATING);
    private static String DATABASE_NAME = "submission_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_FAVORITE_MOVIE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TV_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAV_MOVIE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAV_TV);
        onCreate(sqLiteDatabase);
    }
}
