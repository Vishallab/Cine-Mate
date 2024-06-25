package com.vishal.cinemate.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.vishal.cinemate.R;
import com.vishal.cinemate.adapter.SimilarMoviesAdapter;
import com.vishal.cinemate.model.SimilarMovies;
import com.vishal.cinemate.viewmodel.MovieViewModel;

import java.util.List;

public class similarActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSimilarMovies;
    private MovieViewModel viewModel;
    private SimilarMoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar);

        Toolbar toolbar = findViewById(R.id.movieDetail_similar_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String movieId = getIntent().getStringExtra("MOVIE_ID");

        recyclerViewSimilarMovies = findViewById(R.id.nowPlaying_recyclerView_fragment_similar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewSimilarMovies.setLayoutManager(layoutManager);
        adapter = new SimilarMoviesAdapter(this);
        recyclerViewSimilarMovies.setAdapter(adapter);

        // Attach the SnapHelper
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewSimilarMovies);

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        int page = 1; // Assuming you want to fetch the first page of similar movies

        viewModel.getSimilarMovies(movieId, page);
        viewModel.getResultGetSimilarMovies().observe(this, new Observer<SimilarMovies>() {
            @Override
            public void onChanged(SimilarMovies similarMoviesResponse) {
                if (similarMoviesResponse != null) {
                    List<SimilarMovies.Movie> similarMoviesList = similarMoviesResponse.getResults();
                    adapter.setSimilarMoviesList(similarMoviesList);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
