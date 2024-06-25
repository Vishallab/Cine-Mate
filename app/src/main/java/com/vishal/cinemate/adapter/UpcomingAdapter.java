package com.vishal.cinemate.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vishal.cinemate.R;
import com.vishal.cinemate.helper.Const;
import com.vishal.cinemate.model.Upcoming;

import java.util.ArrayList;

import soup.neumorphism.NeumorphCardView;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<Upcoming.Results> upcomingList;

    private ArrayList<Upcoming.Results> getUpcomingList() {
        return upcomingList;
    }

    public void setUpcomingList(ArrayList<Upcoming.Results> upcomingList) {
        this.upcomingList = upcomingList;
    }

    public void addUpcomingList(ArrayList<Upcoming.Results> results) {
        upcomingList.addAll(results);
    }

    public UpcomingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_movie_poster, parent, false);

        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Upcoming.Results results = getUpcomingList().get(position);

        holder.movie_textView_titlePlaceholder.setText(results.getTitle());
        Glide.with(context).load(Const.IMG_URL_500 + results.getPoster_path()).into(holder.movie_imageView_poster);

        if (!TextUtils.isEmpty(results.getPoster_path())) {
            holder.movie_textView_titlePlaceholder.setVisibility(View.INVISIBLE);
        } else {
            holder.movie_textView_titlePlaceholder.setVisibility(View.VISIBLE);
        }

        holder.movie_cardView_poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("movieId", String.valueOf(results.getId()));
                Navigation.findNavController(view).navigate(R.id.action_upcomingFragment_to_movieDetailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getUpcomingList().size() - (getUpcomingList().size() % 3);
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        NeumorphCardView movie_cardView_poster;
        ImageView movie_imageView_poster;
        TextView movie_textView_titlePlaceholder;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            movie_cardView_poster = itemView.findViewById(R.id.movie_cardView_poster);
            movie_imageView_poster = itemView.findViewById(R.id.movie_imageView_poster);
            movie_textView_titlePlaceholder = itemView.findViewById(R.id.movie_textView_titlePlaceholder);
        }
    }
}
