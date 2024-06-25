package com.vishal.cinemate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vishal.cinemate.R;
import com.vishal.cinemate.model.Movies;

import java.util.ArrayList;

public class MovieDetailGenreAdapter extends RecyclerView.Adapter<MovieDetailGenreAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<Movies.Genres> movieDetailGenreList;
    private ArrayList<Movies.Genres> getMovieDetailGenreList() {
        return movieDetailGenreList;
    }
    public void setMovieDetailGenreList(ArrayList<Movies.Genres> movieDetailGenreList) {
        this.movieDetailGenreList = movieDetailGenreList;
    }

    public MovieDetailGenreAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_movie_detail_genre, parent, false);

        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Movies.Genres results = getMovieDetailGenreList().get(position);

        holder.movieDetail_textView_genre.setText(results.getName());
    }

    @Override
    public int getItemCount() {
        return getMovieDetailGenreList().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        TextView movieDetail_textView_genre;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            movieDetail_textView_genre = itemView.findViewById(R.id.movieDetail_textView_genre);
        }
    }
}
