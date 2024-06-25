package com.vishal.cinemate.adapter;

import android.content.Context;
import android.os.Bundle;
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
import com.vishal.cinemate.model.Trending;

import java.util.ArrayList;

public class TrendingVPAdapter extends RecyclerView.Adapter<TrendingVPAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<Trending.Results> trendingList;

    private ArrayList<Trending.Results> getTrendingList() {
        return trendingList;
    }

    public void setTrendingList(ArrayList<Trending.Results> trendingList) {
        this.trendingList = trendingList;
    }

    public TrendingVPAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_movie_backdrop_view_pager, parent, false);

        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Trending.Results results = getTrendingList().get(position);

        holder.movie_textView_title_viewPager.setText(results.getTitle());
        Glide.with(context).load(Const.IMG_URL_780 + results.getBackdrop_path()).into(holder.movie_imageView_backdrop_viewPager);
        holder.movie_imageView_backdrop_viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("movieId", String.valueOf(results.getId()));
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle);//this is responsible fo the tranding VP clikc
            }
        });
    }

    @Override
    public int getItemCount() {
        return getTrendingList().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        TextView movie_textView_title_viewPager;
        ImageView movie_imageView_backdrop_viewPager;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            movie_textView_title_viewPager = itemView.findViewById(R.id.movie_textView_title_viewPager);
            movie_imageView_backdrop_viewPager = itemView.findViewById(R.id.movie_imageView_backdrop_viewPager);
        }
    }
}
