package com.vishal.cinemate.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vishal.cinemate.R;
import com.vishal.cinemate.helper.Const;
import com.vishal.cinemate.model.Backdrops;

import java.util.ArrayList;

public class ImagesVPAdapter extends RecyclerView.Adapter<ImagesVPAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<Backdrops.Backdrop> imagesList;
    private int movieId;

    public ImagesVPAdapter(Context context, int movieId) {
        this.context = context;
        this.movieId = movieId; // Initialize movieId
        this.imagesList = new ArrayList<>(); // Initialize imagesList as an empty list
    }

    private ArrayList<Backdrops.Backdrop> getImagesList() {
        return imagesList;
    }

    public void setImagesList(ArrayList<Backdrops.Backdrop> imagesList) {
        this.imagesList = imagesList;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_movie_backdrop_images_view_pager, parent, false);

        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Backdrops.Backdrop backdrop = getImagesList().get(position);

        Glide.with(context).load(Const.IMG_URL_780 + backdrop.getFile_path()).into(holder.movie_imageView_image_backdrop_viewPager);
        holder.movie_imageView_image_backdrop_viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("movieId", String.valueOf(movieId)); // Use the member variable movieId
//                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle); // Uncomment this line if navigation is needed
            }
        });
    }

    @Override
    public int getItemCount() {
        return (imagesList != null) ? imagesList.size() : 0; // Check for null before accessing imagesList
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        ImageView movie_imageView_image_backdrop_viewPager;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_imageView_image_backdrop_viewPager = itemView.findViewById(R.id.movie_imageView_image_backdrop_viewPager);
        }
    }
}
