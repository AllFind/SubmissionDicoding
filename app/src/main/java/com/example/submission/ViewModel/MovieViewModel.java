package com.example.submission.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submission.model.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {
    private static final String API_KEY = "0d33b1a95612ce97034487c11d07839c";
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

    public void setMovies() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US";
        final ArrayList<Movie> movies = new ArrayList<>();

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
                        Movie movie = new Movie(object.getString("original_title")
                                , object.getString("overview")
                                , object.getDouble("vote_average")
                                , imageUrl
                                , object.getInt("id")
                                , object.getString("release_date"));
                        movies.add(movie);
                    }
                    listMovies.postValue(movies);
                    MovieViewModel.this.notifyAll();
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

    public void searchMovie(String keyword){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&language=en-US&query="+keyword;
        final ArrayList <Movie> movies  = new ArrayList<>();
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
                        Movie movie = new Movie(object.getString("original_title")
                                , object.getString("overview")
                                , object.getDouble("vote_average")
                                , imageUrl
                                , object.getInt("id")
                                , object.getString("release_date"));
                        movies.add(movie);
                    }
                    listMovies.postValue(movies);
                    //ovieViewModel.this.notifyAll();
                }
                catch(Exception e){

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    public LiveData<ArrayList<Movie>> getMovies() {
        return listMovies;
    }
}
