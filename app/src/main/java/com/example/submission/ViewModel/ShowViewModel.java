package com.example.submission.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submission.model.Show;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ShowViewModel extends ViewModel {
    private static final String API_KEY = "0d33b1a95612ce97034487c11d07839c";
    private MutableLiveData<ArrayList<Show>> listShows = new MutableLiveData<>();

    public void setShow() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";
        final ArrayList<Show> shows = new ArrayList<>();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject response = new JSONObject(result);
                    JSONArray array = response.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String imageUrl = "https://image.tmdb.org/t/p/w780" + object.getString("poster_path");
                        Show show = new Show(object.getString("name")
                                , object.getString("overview")
                                , object.getDouble("vote_average")
                                , imageUrl
                                , object.getInt("id")
                                , object.getString("first_air_date"));
                        shows.add(show);
                    }
                    listShows.postValue(shows);
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    public void searchShow(String keyword){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.themoviedb.org/3/search/tv?api_key="+API_KEY+"&language=en-US&query="+keyword;
        final ArrayList <Show> shows  = new ArrayList<>();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject response = new JSONObject(result);
                    JSONArray array = response.getJSONArray("results");
                    for(int i =0; i< array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        String imageUrl = "https://image.tmdb.org/t/p/w780" + object.getString("poster_path");
                        Show show = new Show(object.getString("name")
                                , object.getString("overview")
                                , object.getDouble("vote_average")
                                , imageUrl
                                , object.getInt("id")
                                , object.getString("first_air_date"));
                        shows.add(show);
                    }
                    listShows.postValue(shows);

                }
                catch(Exception e){

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    public LiveData<ArrayList<Show>> getShow() {
        return listShows;
    }
}
