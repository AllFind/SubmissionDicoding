package com.example.submission;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.submission.model.Movie;
import com.example.submission.model.Show;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetailActivity extends YouTubeBaseActivity {

    private static final String OBJECT = "OBJECT";
    private static final int RECOVERY_REQUEST = 1;
    private static final String API_KEY = "0d33b1a95612ce97034487c11d07839c";
    ImageView imgPoster;
    YouTubePlayerView youTubePlayerView;
    TextView txtTitle, txtDesc, txtGenre;
    RatingBar ratingBar;
    Button btnBack;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        progressBar = findViewById(R.id.progress_bar);
        youTubePlayerView = findViewById(R.id.YoutubeVid);
        imgPoster = findViewById(R.id.Poster);
        txtDesc = findViewById(R.id.Description);
        txtTitle = findViewById(R.id.Title);
        ratingBar = findViewById(R.id.ratingBar);
        btnBack = findViewById(R.id.btnBack);
        txtGenre = findViewById(R.id.genre);

        youTubePlayerView.setVisibility(View.GONE);
        imgPoster.setVisibility(View.GONE);
        txtTitle.setVisibility(View.GONE);
        txtDesc.setVisibility(View.GONE);
        ratingBar.setVisibility(View.GONE);
        btnBack.setVisibility(View.GONE);
        txtGenre.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final Object o = getIntent().getParcelableExtra(OBJECT);
        if (o instanceof Movie) {
            Picasso.get().load(((Movie) o).getPhoto()).into(imgPoster);

            txtTitle.setText(((Movie) o).getTitle());
            txtDesc.setText(((Movie) o).getDescription());
            ratingBar.setRating((float) (((Movie) o).getRating() / 2));
            AsyncHttpClient client = new AsyncHttpClient();
            String url = "https://api.themoviedb.org/3/movie/" + ((Movie) o).getID() + "?api_key=" + API_KEY + "&language=en-US";
            client.get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String result = new String(responseBody);
                    String list_genre = "";
                    try {
                        JSONObject obj = new JSONObject(result);

                        JSONArray genre = obj.getJSONArray("genres");
                        for (int i = 0; i < genre.length(); i++) {
                            list_genre += genre.getJSONObject(i).getString("name");
                            if (i != genre.length() - 1)
                                list_genre += ", ";
                        }
                        txtGenre.setText(list_genre);

                        youTubePlayerView.setVisibility(View.VISIBLE);
                        imgPoster.setVisibility(View.VISIBLE);
                        txtTitle.setVisibility(View.VISIBLE);
                        txtDesc.setVisibility(View.VISIBLE);
                        ratingBar.setVisibility(View.VISIBLE);
                        btnBack.setVisibility(View.VISIBLE);
                        txtGenre.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                }
            });
            youTubePlayerView.initialize(Config.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                    if (!b) {
                        AsyncHttpClient client = new AsyncHttpClient();
                        String url = "https://api.themoviedb.org/3/movie/" + (((Movie) o).getID()) + "/videos?api_key=" + API_KEY + "&language=en-US";
                        client.get(url, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                String result = new String(responseBody);
                                try {
                                    JSONObject response = new JSONObject(result);
                                    JSONArray array = response.getJSONArray("results");
                                    JSONObject object;
                                    if (array.length() > 1) {
                                        object = array.getJSONObject(1);
                                    } else {
                                        object = array.getJSONObject(0);
                                    }
                                    youTubePlayer.cueVideo(object.getString("key"));


                                } catch (Exception e) {
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                            }
                        });

                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });

        } else if (o instanceof Show) {
            Picasso.get().load(((Show) o).getPhoto()).into(imgPoster);

            txtTitle.setText(((Show) o).getTitle());
            txtDesc.setText(((Show) o).getDescription());
            ratingBar.setRating((float) (((Show) o).getRating() / 2));
            AsyncHttpClient client = new AsyncHttpClient();
            String url = "https://api.themoviedb.org/3/tv/" + ((Show) o).getID() + "?api_key=" + API_KEY + "&language=en-US";
            client.get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String result = new String(responseBody);
                    String list_genre = "";
                    try {
                        JSONObject obj = new JSONObject(result);

                        JSONArray genre = obj.getJSONArray("genres");
                        for (int i = 0; i < genre.length(); i++) {
                            list_genre += genre.getJSONObject(i).getString("name");
                            if (i != genre.length() - 1)
                                list_genre += ", ";
                        }
                        txtGenre.setText(list_genre);

                        youTubePlayerView.setVisibility(View.VISIBLE);
                        imgPoster.setVisibility(View.VISIBLE);
                        txtTitle.setVisibility(View.VISIBLE);
                        txtDesc.setVisibility(View.VISIBLE);
                        ratingBar.setVisibility(View.VISIBLE);
                        btnBack.setVisibility(View.VISIBLE);
                        txtGenre.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            youTubePlayerView.initialize(Config.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                    if (!b) {
                        AsyncHttpClient client = new AsyncHttpClient();
                        String url = "https://api.themoviedb.org/3/tv/" + (((Show) o).getID()) + "/videos?api_key=" + API_KEY + "&language=en-US";
                        client.get(url, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                String result = new String(responseBody);
                                try {
                                    JSONObject response = new JSONObject(result);
                                    JSONArray array = response.getJSONArray("results");
                                    JSONObject object;
                                    if (array.length() > 1) {
                                        object = array.getJSONObject(1);
                                    } else {
                                        object = array.getJSONObject(0);
                                    }
                                    youTubePlayer.cueVideo(object.getString("key"));


                                } catch (Exception e) {
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                            }
                        });
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });


        }

    }

}
