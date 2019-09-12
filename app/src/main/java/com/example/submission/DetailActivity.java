package com.example.submission;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.submission.model.Movie;
import com.example.submission.model.Show;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private static final String OBJECT = "OBJECT";
    ImageView imgPoster, imgBigPoster;
    TextView txtTitle, txtDesc;
    RatingBar ratingBar;
    Button btnBack;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        progressBar = findViewById(R.id.progress_bar);
        imgBigPoster = findViewById(R.id.BigPoster);
        imgPoster = findViewById(R.id.Poster);
        txtDesc = findViewById(R.id.Description);
        txtTitle = findViewById(R.id.Title);
        ratingBar = findViewById(R.id.ratingBar);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Object o = getIntent().getParcelableExtra(OBJECT);
        if (o instanceof Movie) {
            Picasso.get().load(((Movie) o).getPhoto()).into(imgPoster);
            Picasso.get().load(((Movie) o).getPhoto()).into(imgBigPoster);
            txtTitle.setText(((Movie) o).getTitle());
            txtDesc.setText(((Movie) o).getDescription());
            ratingBar.setRating((float) (((Movie) o).getRating() / 2));
        } else if (o instanceof Show) {
            Picasso.get().load(((Show) o).getPhoto()).into(imgPoster);
            Picasso.get().load(((Show) o).getPhoto()).into(imgBigPoster);
            txtTitle.setText(((Show) o).getTitle());
            txtDesc.setText(((Show) o).getDescription());
            ratingBar.setRating((float) (((Show) o).getRating() / 2));
        }

        new Task().execute();
    }

    class Task extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            imgBigPoster.setVisibility(View.GONE);
            imgPoster.setVisibility(View.GONE);
            txtTitle.setVisibility(View.GONE);
            txtDesc.setVisibility(View.GONE);
            ratingBar.setVisibility(View.GONE);
            btnBack.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            imgBigPoster.setVisibility(View.VISIBLE);
            imgPoster.setVisibility(View.VISIBLE);
            txtTitle.setVisibility(View.VISIBLE);
            txtDesc.setVisibility(View.VISIBLE);
            ratingBar.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            return null;
        }
    }
}
