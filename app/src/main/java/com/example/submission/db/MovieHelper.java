package com.example.submission.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.submission.model.Movie;

import java.util.ArrayList;

public class MovieHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_FAV_MOVIE;
    private static DatabaseHelper databaseHelper;
    private static MovieHelper INSTANCE;
    private static SQLiteDatabase database;

    private MovieHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public long insertFavMovie(Movie movie) {
        ContentValues args = new ContentValues();
        args.put(DatabaseContract.FavoriteMovieColumns.TITLE, movie.getTitle());
        args.put(DatabaseContract.FavoriteMovieColumns.DATE, movie.getDate());
        args.put(DatabaseContract.FavoriteMovieColumns.DESCRIPTION, movie.getDescription());
        args.put(DatabaseContract.FavoriteMovieColumns.PHOTO, movie.getPhoto());
        args.put(DatabaseContract.FavoriteMovieColumns.RATING, movie.getRating());
        args.put(DatabaseContract.FavoriteMovieColumns._ID, movie.getID());

        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteFavMovie(int id) {
        return database.delete(DatabaseContract.TABLE_FAV_MOVIE, DatabaseContract.FavoriteMovieColumns._ID + " = '" + id + "'", null);
    }

    public ArrayList<Movie> getAllFavMovie() {
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null, null, null, null, null, null);
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie(
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.DESCRIPTION)),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.RATING)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.PHOTO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns._ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.DATE)));

                arrayList.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public Movie getMovie(int id) {
        Movie movie = null;
        Cursor cursor = database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + DatabaseContract.FavoriteMovieColumns._ID + "=" + id, null);
        if (cursor.moveToFirst()) {
            movie = new Movie(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.DESCRIPTION)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.RATING)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.PHOTO)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.DATE)));
        }
        cursor.close();
        return movie;
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

}
