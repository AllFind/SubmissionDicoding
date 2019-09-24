package com.example.submission.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_FAV_TV = "favorites_tv";
    static String TABLE_FAV_MOVIE = "favorites_movie";

    static final class FavoriteTVColumns implements BaseColumns {
        static String TITLE = "title";
        static String DESCRIPTION = "description";
        static String PHOTO = "photo";
        static String DATE = "date";
        static String RATING = "rating";
    }

    static final class FavoriteMovieColumns implements BaseColumns {
        static String TITLE = "title";
        static String DESCRIPTION = "description";
        static String PHOTO = "photo";
        static String DATE = "date";
        static String RATING = "rating";
    }

}
