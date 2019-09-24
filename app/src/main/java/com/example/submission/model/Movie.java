package com.example.submission.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;

public class Movie implements Parcelable {
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private String Title, Description, Photo, Date;
    private double Rating;
    private int ID;
    private boolean favorite;

    public Movie(String title, String description, double rating, String photo, int id, String date) {
        Title = title;
        Description = description;
        Rating = rating;
        Photo = photo;
        ID = id;
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date newDate = input.parse(date);
            input = new SimpleDateFormat("dd MMM yyyy");
            Date = input.format(newDate);
        } catch (Exception e) {
        }
    }

    protected Movie(Parcel in) {
        this.Title = in.readString();
        this.Description = in.readString();
        this.Rating = in.readDouble();
        this.Photo = in.readString();
        this.ID = in.readInt();
        this.Date = in.readString();
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Title);
        dest.writeString(this.Description);
        dest.writeDouble(this.Rating);
        dest.writeString(this.Photo);
        dest.writeInt(this.ID);
        dest.writeString(this.Date);
    }
}
