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
import com.vishal.cinemate.model.NowPlaying;

import java.util.ArrayList;

import soup.neumorphism.NeumorphCardView;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<NowPlaying.Results> nowPlayingList;

    private ArrayList<NowPlaying.Results> getNowPlayingList() {
        return nowPlayingList;
    }

    public void setNowPlayingList(ArrayList<NowPlaying.Results> nowPlayingList) {
        this.nowPlayingList = nowPlayingList;
    }

    public void addNowPlayingList(ArrayList<NowPlaying.Results> results) {
        nowPlayingList.addAll(results);
    }

    public NowPlayingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.card_now_playing, parent, false);
        View view = layoutInflater.inflate(R.layout.card_movie_poster, parent, false);

        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final NowPlaying.Results results = getNowPlayingList().get(position);

        holder.movie_textView_titlePlaceholder.setText(results.getTitle());
//        holder.nowPlaying_textView_title.setText(results.getTitle());
//        holder.nowPlaying_textView_overview.setText(results.getOverview());
//        holder.nowPlaying_textView_releaseDate.setText(results.getRelease_date());
        Glide.with(context).load(Const.IMG_URL_500 + results.getPoster_path()).into(holder.movie_imageView_poster);

        if (!TextUtils.isEmpty(results.getPoster_path())) {
            holder.movie_textView_titlePlaceholder.setVisibility(View.INVISIBLE);
        } else {
            holder.movie_textView_titlePlaceholder.setVisibility(View.VISIBLE);
        }

        holder.movie_cardView_poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Activity
//                Intent intent = new Intent(context, MovieDetailActivity.class); //her elike i want to open activty not fragmet
//                intent.putExtra("movieId", String.valueOf(results.getId()));
//                context.startActivity(intent);

                //Fragment
                Bundle bundle = new Bundle();//movie id ko bundle passing k through bhej rhe h

                bundle.putString("movieId", String.valueOf(results.getId()));
                Navigation.findNavController(view).navigate(R.id.action_nowPlayingFragment_to_movieDetailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getNowPlayingList().size() - (getNowPlayingList().size() % 3);
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        NeumorphCardView movie_cardView_poster;
        ImageView nowPlaying_imageView_poster, movie_imageView_poster;
        TextView nowPlaying_textView_title, nowPlaying_textView_overview, nowPlaying_textView_releaseDate, movie_textView_titlePlaceholder;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_cardView_poster = itemView.findViewById(R.id.movie_cardView_poster);
//            nowPlaying_imageView_poster = itemView.findViewById(R.id.nowPlaying_imageView_poster);
//            nowPlaying_textView_title = itemView.findViewById(R.id.nowPlaying_textView_title);
//            nowPlaying_textView_overview = itemView.findViewById(R.id.nowPlaying_textView_overview);
//            nowPlaying_textView_releaseDate = itemView.findViewById(R.id.nowPlaying_textView_releaseDate);//in sb ki jb need ho us hisab se edit kr sakte h

            movie_imageView_poster = itemView.findViewById(R.id.movie_imageView_poster);
            movie_textView_titlePlaceholder = itemView.findViewById(R.id.movie_textView_titlePlaceholder);
        }
    }
}
