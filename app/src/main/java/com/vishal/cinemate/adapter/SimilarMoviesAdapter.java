package com.vishal.cinemate.adapter;

import static android.provider.Settings.System.getString;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vishal.cinemate.R;
import com.vishal.cinemate.model.SimilarMovies;

import java.util.List;

public class SimilarMoviesAdapter extends RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder> {

    private Context context;
    private List<SimilarMovies.Movie> similarMoviesList;

    public SimilarMoviesAdapter(Context context) {
        this.context = context;
    }

    public void setSimilarMoviesList(List<SimilarMovies.Movie> similarMoviesList) {
        this.similarMoviesList = similarMoviesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_similar_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimilarMovies.Movie movie = similarMoviesList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return similarMoviesList != null ? similarMoviesList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView,synopsis,movieDetail_textView_orignal_title_card,movieDetail_textView_rating_card,movieDetail_textView_popularity_card;
        private ImageView backdropImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movie_title);
            backdropImageView = itemView.findViewById(R.id.movie_backdrop);
            synopsis=itemView.findViewById(R.id.synopsis);
            movieDetail_textView_orignal_title_card=itemView.findViewById(R.id.movieDetail_textView_orignal_title_card);
            movieDetail_textView_rating_card=itemView.findViewById(R.id.movieDetail_textView_rating_card);
            movieDetail_textView_popularity_card=itemView.findViewById(R.id.movieDetail_textView_popularity_card);
        }

        public void bind(SimilarMovies.Movie movie) {
            String voteAverage = String.valueOf(movie.getVote_average());
            String voteCount = String.valueOf(movie.getVote_count());

            // Correct way to retrieve string from resources using context
            String rating = context.getString(R.string.text_movieRating, voteAverage, voteCount);

            Log.d("SimilarMoviesAdapter", "Binding Movie Title: " + movie.getTitle());
            titleTextView.setText(movie.getTitle());

            // Use Glide to load the backdrop image
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.getBackdropPath())
                    .into(backdropImageView);

            synopsis.setText(movie.getOverview());

            movieDetail_textView_orignal_title_card.setText(movie.getOriginal_title());
            movieDetail_textView_rating_card.setText(rating);
            movieDetail_textView_popularity_card.setText(String.valueOf((int) movie.getPopularity())); // Cast popularity to int if necessary
        }

    }
}
