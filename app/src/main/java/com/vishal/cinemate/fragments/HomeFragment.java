package com.vishal.cinemate.fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vishal.cinemate.R;
import com.vishal.cinemate.adapter.PopularAdapter;
import com.vishal.cinemate.adapter.TopRatedAdapter;
import com.vishal.cinemate.adapter.TrendingVPAdapter;

import com.vishal.cinemate.model.Popular;
import com.vishal.cinemate.model.TopRated;
import com.vishal.cinemate.model.Trending;
import com.vishal.cinemate.viewmodel.MovieViewModel;
import com.vishal.cinemate.viewmodel.TrendingViewModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private View view;
    private ViewPager2 home_viewPager_trending_fragment;
    private RecyclerView  home_recyclerView_popular_fragment, home_recyclerView_topRated_fragment;
    private Dialog progressBarDialog;

    private TrendingVPAdapter trendingAdapter;
    private TrendingViewModel trendingViewModel;
    private PopularAdapter popularAdapter;
    private TopRatedAdapter topRatedAdapter;
    private MovieViewModel movieViewModel;

    private Handler handler;

    private int delay, page, totalPage;
    private String mediaType, timeWindow;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        setProgressBarDialog();
        initialize();
        setViewPager();
        setRecyclerView();

        return view;
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

    private void setRecyclerView() {
        RecyclerView.LayoutManager managerPopular = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        home_recyclerView_popular_fragment.setLayoutManager(managerPopular);

        RecyclerView.LayoutManager managerTopRated = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        home_recyclerView_topRated_fragment.setLayoutManager(managerTopRated);
    }

    private void setViewPager() {
        home_viewPager_trending_fragment.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
//                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, delay * 1000L);
            }
        });
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (home_viewPager_trending_fragment.getAdapter() != null &&
                    home_viewPager_trending_fragment.getCurrentItem() == home_viewPager_trending_fragment.getAdapter().getItemCount() - 1) {
                home_viewPager_trending_fragment.setCurrentItem(0);
            } else {
                home_viewPager_trending_fragment.setCurrentItem(home_viewPager_trending_fragment.getCurrentItem() + 1);
            }
        }
    };

    private void initialize() {
        delay = 3;
        mediaType = "movie";
        timeWindow = "week";

        handler = new Handler();

        home_viewPager_trending_fragment = view.findViewById(R.id.home_viewPager_trending_fragment);
        home_recyclerView_popular_fragment = view.findViewById(R.id.home_recyclerView_popular_fragment);
        home_recyclerView_topRated_fragment = view.findViewById(R.id.home_recyclerView_topRated_fragment);

        trendingViewModel = new ViewModelProvider(getActivity()).get(TrendingViewModel.class);
        trendingViewModel.getTrending(mediaType, timeWindow);
        trendingViewModel.getResultGetTrending().observe(getActivity(), showResultTrending);

        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        movieViewModel.getPopular();
        movieViewModel.getResultGetPopular().observe(getActivity(), showResultPopular);

        movieViewModel.getTopRated();
        movieViewModel.getResultGetTopRated().observe(getActivity(), showResultTopRated);
    }

    private void setProgressBarDialog() {
        progressBarDialog = new Dialog(getActivity());
        progressBarDialog.setContentView(R.layout.circular_progress_bar);
        progressBarDialog.setCancelable(false);
        progressBarDialog.getWindow().setBackgroundDrawable(new ColorDrawable(1));
        progressBarDialog.show();
    }

    private final Observer<Trending> showResultTrending = new Observer<Trending>() {
        @Override
        public void onChanged(Trending results) {
            trendingAdapter = new TrendingVPAdapter(getActivity());
            trendingAdapter.setTrendingList((ArrayList<Trending.Results>) results.getResults());
            home_viewPager_trending_fragment.setAdapter(trendingAdapter);
        }
    };


    private final Observer<Popular> showResultPopular = new Observer<Popular>() {
        @Override
        public void onChanged(Popular popular) {
            popularAdapter = new PopularAdapter(getActivity());
            popularAdapter.setPopularList((ArrayList<Popular.Results>) popular.getResults());
            home_recyclerView_popular_fragment.setAdapter(popularAdapter);
        }
    };


    private final Observer<TopRated> showResultTopRated = new Observer<TopRated>() {
        @Override
        public void onChanged(TopRated topRated) {
            topRatedAdapter = new TopRatedAdapter(getActivity());
            topRatedAdapter.setTopRatedList((ArrayList<TopRated.Results>) topRated.getResults());
            home_recyclerView_topRated_fragment.setAdapter(topRatedAdapter);

            progressBarDialog.hide();
        }
    };
}