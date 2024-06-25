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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vishal.cinemate.R;
import com.vishal.cinemate.helper.Const;
import com.vishal.cinemate.model.Popular;

import java.util.ArrayList;

import soup.neumorphism.NeumorphCardView;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<Popular.Results> popularList;

    private ArrayList<Popular.Results> getPopularList() {
        return popularList;
    }

    public void setPopularList(ArrayList<Popular.Results> popularList) {
        this.popularList = popularList;
    }

    public void addPopularList(ArrayList<Popular.Results> results) {
        popularList.addAll(results);
    }

    public PopularAdapter(Context context) {
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
        final Popular.Results results = getPopularList().get(position);

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
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getPopularList().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        NeumorphCardView movie_cardView_poster; // Change CardView to NeumorphCardView
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
