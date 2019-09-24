package com.example.submission.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;

public class Show implements Parcelable {
    public static final Creator<Show> CREATOR = new Creator<Show>() {
        @Override
        public Show createFromParcel(Parcel source) {
            return new Show(source);
        }

        @Override
        public Show[] newArray(int size) {
            return new Show[size];
        }
    };
    private String Title, Description, Photo, Date;
    private double Rating;
    private int ID;
    private boolean Favorite;

    public Show(String title, String description, double rating, String photo, int id, String date) {
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

    protected Show(Parcel in) {
        this.Title = in.readString();
        this.Description = in.readString();
        this.Rating = in.readDouble();
        this.Photo = in.readString();
        this.ID = in.readInt();
        this.Date = in.readString();
    }

    public boolean isFavorite() {
        return Favorite;
    }

    public void setFavorite(boolean favorite) {
        Favorite = favorite;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
        dest.writeString(this.Date);
        dest.writeInt(this.ID);
    }
}
