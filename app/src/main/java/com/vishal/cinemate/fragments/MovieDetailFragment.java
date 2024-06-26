package com.vishal.cinemate.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.snackbar.Snackbar;
import com.vishal.cinemate.R;
import com.vishal.cinemate.adapter.ImagesVPAdapter;
import com.vishal.cinemate.adapter.MovieDetailGenreAdapter;
import com.vishal.cinemate.adapter.MovieDetailProductionCompanyAdapter;
import com.vishal.cinemate.adapter.TrendingVPAdapter;
import com.vishal.cinemate.helper.Const;
import com.vishal.cinemate.model.Backdrops;
import com.vishal.cinemate.model.Movies;
import com.vishal.cinemate.model.Videos;
import com.vishal.cinemate.activities.similarActivity;
import com.vishal.cinemate.viewmodel.ImagesViewModel;
import com.vishal.cinemate.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class MovieDetailFragment extends Fragment {

    private ViewPager2 home_viewPager_images_backdrop_fragment;
    private ImagesViewModel imagesViewModel;
    private ImagesVPAdapter  imagesVPAdapter;

    private Handler handler;
    private View view;
    private ImageView movieDetail_imageView_backdrop_fragment, movieDetail_imageView_poster_fragment;
    private TextView movieDetail_textView_titlePlaceholder_fragment, movieDetail_textView_caption_fragment, movieDetail_textView_title_fragment, movieDetail_textView_rating_fragment, movieDetail_textView_popularity_fragment, movieDetail_textView_productionCompanyStatic_fragment, movieDetail_textView_description_fragment, movieDetail_textView_budget_fragment,movieDetail_textView_orignal_title_fragment, movieDetail_textView_revenue_fragment;
    private Button movieDetail_button_watchTrailer_fragment,movieDetail_button_similar_fragment;
    private RecyclerView movieDetail_recyclerView_productionCompany_fragment, movieDetail_recyclerView_genre_fragment,movieDetail_recyclerView_similar_fragment;
    private Dialog progressBarDialog;
    private String mediaType, timeWindow;

    private int delay, page, totalPage;

    private MovieViewModel viewModel;
    private TrendingVPAdapter trendingAdapter;

    private String movieId, videoSite, videoKey;

    public MovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay * 1000L);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        initialize();
        setViewPager();

        setRecyclerView();
        setListener();

        return view;
    }

    private void setListener() {
        movieDetail_button_watchTrailer_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ? YouTube
                if (TextUtils.equals(videoSite, "YouTube")) {
                    // * Set intent to launch YouTube
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(Const.YOUTUBE_WATCH_URL + videoKey));

                    // * Check if YouTube installed
                    PackageManager manager = getActivity().getPackageManager();
                    ArrayList<ResolveInfo> info = (ArrayList<ResolveInfo>) manager.queryIntentActivities(intent, 0);
                    if (info.size() > 0) {
                        // * YouTube installed
                        intent.setPackage("com.google.android.youtube");
                    } else {
                        // * Youtube not installed
                    }

                    // * Launch YouTube
                    startActivity(intent);
                } else {
                    // * Make snackbar
                    Snackbar snackbar = Snackbar.make(view, getString(R.string.text_videoFailed), Snackbar.LENGTH_SHORT);
                    snackbar.setAnchorView(getActivity().findViewById(R.id.main_bottomNavView));

                    // * Set action
                    snackbar.setAction(getString(R.string.text_dismiss), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            snackbar.dismiss();
                        }
                    });

                    snackbar.show();
                }
            }
        });

        movieDetail_button_similar_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), similarActivity.class);
                // Pass the movie ID to the similarActivity
                intent.putExtra("MOVIE_ID", movieId);
                startActivity(intent);
            }
        });
    }

    //view paggr ++++++++++++++++++++++++++++++++++++++++++++++

    private void setViewPager() {
        home_viewPager_images_backdrop_fragment.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
//                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, delay * 1000);
            }
        });
    }

    //runnable
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (home_viewPager_images_backdrop_fragment.getAdapter() != null &&
                    home_viewPager_images_backdrop_fragment.getCurrentItem() == home_viewPager_images_backdrop_fragment.getAdapter().getItemCount() - 1) {
                home_viewPager_images_backdrop_fragment.setCurrentItem(0);
            } else {
                home_viewPager_images_backdrop_fragment.setCurrentItem(home_viewPager_images_backdrop_fragment.getCurrentItem() + 1);
            }
        }
    };

    private void setRecyclerView() {
        //==Start of genre RV
        FlexboxLayoutManager managerGenre = new FlexboxLayoutManager(getActivity(), FlexDirection.ROW, FlexWrap.WRAP);
        managerGenre.setJustifyContent(JustifyContent.FLEX_START);
        movieDetail_recyclerView_genre_fragment.setLayoutManager(managerGenre);
        //==End of genre RV

        //==Start of production company RV
        RecyclerView.LayoutManager managerProductionCompany = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        movieDetail_recyclerView_productionCompany_fragment.setLayoutManager(managerProductionCompany);
        //==End of production company RV
    }

    private void initialize() {
        videoSite = "";
        videoKey = "";

        delay = 2;
        mediaType = "movie";
        timeWindow = "week";

        handler = new Handler();

        movieId = getArguments().getString("movieId");
        setProgressBarDialog();

        home_viewPager_images_backdrop_fragment = view.findViewById(R.id.home_viewPager_images_backdrop_fragment);
        imagesViewModel = new ViewModelProvider(getActivity()).get(ImagesViewModel.class);
        imagesViewModel.getImages(movieId);
        imagesViewModel.getResultGetTrending().observe(getActivity(), showResultImages);

        movieDetail_imageView_poster_fragment = view.findViewById(R.id.movieDetail_imageView_poster_fragment);
        movieDetail_textView_titlePlaceholder_fragment = view.findViewById(R.id.movieDetail_textView_titlePlaceholder_fragment);
        movieDetail_textView_caption_fragment = view.findViewById(R.id.movieDetail_textView_caption_fragment);
        movieDetail_button_watchTrailer_fragment = view.findViewById(R.id.movieDetail_button_watchTrailer_fragment);
        movieDetail_textView_title_fragment = view.findViewById(R.id.movieDetail_textView_title_fragment);
        movieDetail_textView_rating_fragment = view.findViewById(R.id.movieDetail_textView_rating_fragment);
        movieDetail_textView_popularity_fragment = view.findViewById(R.id.movieDetail_textView_popularity_fragment);
        movieDetail_textView_description_fragment = view.findViewById(R.id.movieDetail_textView_description_fragment);
        movieDetail_textView_productionCompanyStatic_fragment = view.findViewById(R.id.movieDetail_textView_productionCompanyStatic_fragment);
        movieDetail_recyclerView_productionCompany_fragment = view.findViewById(R.id.movieDetail_recyclerView_productionCompany_fragment);
        movieDetail_recyclerView_genre_fragment = view.findViewById(R.id.movieDetail_recyclerView_genre_fragment);
        movieDetail_textView_revenue_fragment = view.findViewById(R.id.movieDetail_textView_revenue_fragment);
        movieDetail_textView_budget_fragment = view.findViewById(R.id.movieDetail_textView_budget_fragment);
        movieDetail_textView_orignal_title_fragment=view.findViewById(R.id.movieDetail_textView_orignal_title_fragment);
        movieDetail_button_similar_fragment = view.findViewById(R.id.movieDetail_button_similarMovies_fragment);

        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.getMovieById(movieId);
        viewModel.getResultGetMovieById().observe(getActivity(), showResultMovieDetail);

        viewModel.getVideoById(movieId);
        viewModel.getResultGetVideoById().observe(getActivity(), showResultVideo);
    }

    private void setProgressBarDialog() {
        progressBarDialog = new Dialog(getActivity());
        progressBarDialog.setContentView(R.layout.circular_progress_bar);
        progressBarDialog.setCancelable(false);
        progressBarDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        progressBarDialog.show();
    }

    private final Observer<Movies> showResultMovieDetail = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if (movies != null) {
                String date = movies.getRelease_date().substring(0, 10);
                int runtime = movies.getRuntime();

                String runtimeStr = String.valueOf(runtime);

                String title = movies.getTitle();
                String original_title = movies.getOriginal_title(); // Original title
                String voteAverage = String.valueOf(movies.getVote_average());
                String voteCount = String.valueOf(movies.getVote_count());
                String popularity = String.valueOf(movies.getPopularity());
                ArrayList<Movies.Genres> genreList = (ArrayList<Movies.Genres>) movies.getGenres();
                ArrayList<Movies.ProductionCompanies> productionCompaniesList = (ArrayList<Movies.ProductionCompanies>) movies.getProduction_companies();
                String tagline = movies.getTagline();
                String synopsis = movies.getOverview();

                String hour = String.valueOf(runtime / 60);
                String minute = String.valueOf(runtime % 60);

                String caption = getString(R.string.text_movieCaption, date, hour, minute, runtimeStr);

                String rating = getString(R.string.text_movieRating, voteAverage, voteCount);
                String description = getString(R.string.text_movieDescription, tagline, synopsis);

                // Format budget and revenue to Indian format
                String budget = formatNumberToIndianFormat(movies.getBudget());
                String revenue = formatNumberToIndianFormat(movies.getRevenue());

                if (TextUtils.isEmpty(tagline)) {
                    description = synopsis;
                }

                movieDetail_textView_titlePlaceholder_fragment.setText(title);
                movieDetail_textView_caption_fragment.setText(caption);
                movieDetail_textView_title_fragment.setText(title);
                movieDetail_textView_rating_fragment.setText(rating);
                movieDetail_textView_popularity_fragment.setText(popularity);
                movieDetail_textView_description_fragment.setText(description);
                movieDetail_textView_revenue_fragment.setText(revenue);
                movieDetail_textView_budget_fragment.setText(budget);
                movieDetail_textView_orignal_title_fragment.setText(original_title);

                Glide.with(getActivity()).load(Const.IMG_URL_500 + movies.getPoster_path()).into(movieDetail_imageView_poster_fragment);

                emptyHandler(movies, productionCompaniesList);

                //==Start of genre adapter
                MovieDetailGenreAdapter adapterGenre = new MovieDetailGenreAdapter(getActivity());
                adapterGenre.setMovieDetailGenreList(genreList);
                movieDetail_recyclerView_genre_fragment.setAdapter(adapterGenre);
                //==End of genre adapter

                //==Start of production company adapter
                MovieDetailProductionCompanyAdapter adapterProductionCompany = new MovieDetailProductionCompanyAdapter(getActivity());
                adapterProductionCompany.setProductionCompaniesList(productionCompaniesList);
                movieDetail_recyclerView_productionCompany_fragment.setAdapter(adapterProductionCompany);
                //==End of production company adapter
            }
        }
    };

    private Observer<Backdrops> showResultImages = new Observer<Backdrops>() {
        @Override
        public void onChanged(Backdrops results) {
            imagesVPAdapter = new ImagesVPAdapter(getActivity(),Integer.valueOf(movieId));

            imagesVPAdapter.setImagesList((ArrayList<Backdrops.Backdrop>) results.getBackdrops());
            home_viewPager_images_backdrop_fragment.setAdapter(imagesVPAdapter);
        }
    };


    private Observer<Videos> showResultVideo = new Observer<Videos>() {
        @Override
        public void onChanged(Videos videos) {
            ArrayList<Videos.Results> videoList = (ArrayList<Videos.Results>) videos.getResults();
            if (videoList.size() != 0) {
                videoSite = videoList.get(0).getSite();
                videoKey = videoList.get(0).getKey();
            }

            progressBarDialog.hide();
        }
    };

    // * Handle view for empty data
    private void emptyHandler(Movies movies, ArrayList<Movies.ProductionCompanies> productionCompaniesList) {
        // ? Backdrop
        // Nothing

        // ? Poster
        if (movies.getPoster_path() != null) {
            movieDetail_textView_titlePlaceholder_fragment.setVisibility(View.INVISIBLE);
        }

        // ? Genree
        // Nothing

        // ? Production company
        // Uncomment if needed
        // if (productionCompaniesList.size() < 1) {
        //     movieDetail_textView_productionCompanyStatic_fragment.setVisibility(View.INVISIBLE);
        // }
    }

    // Helper method to format number to Indian format
    private String formatNumberToIndianFormat(int number) {
        String[] units = {"", "K", "L", "Cr"};
        int divisor = 1;
        int unitIndex = 0;

        if (number >= 10000000) {
            divisor = 10000000;
            unitIndex = 3; // Cr
        } else if (number >= 100000) {
            divisor = 100000;
            unitIndex = 2; // L
        } else if (number >= 1000) {
            divisor = 1000;
            unitIndex = 1; // K
        }

        double value = number / (double) divisor;
        return String.format("%.2f %s", value, units[unitIndex]);
    }
}