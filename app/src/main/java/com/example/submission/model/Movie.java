package com.example.submission.model;

import android.os.Parcel;
import android.os.Parcelable;

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
    private String Title, Description;
    private double Rating;
    private String Photo;

    public Movie(String title, String description, double rating, String photo) {
        Title = title;
        Description = description;
        Rating = rating;
        Photo = photo;
    }

    protected Movie(Parcel in) {
        this.Title = in.readString();
        this.Description = in.readString();
        this.Rating = in.readDouble();
        this.Photo = in.readString();
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
    }
}
