package com.vishal.cinemate.fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.vishal.cinemate.R;
import com.vishal.cinemate.adapter.NowPlayingAdapter;
import com.vishal.cinemate.model.NowPlaying;
import com.vishal.cinemate.viewmodel.MovieViewModel;

import java.util.ArrayList;


public class NowPlayingFragment extends Fragment {

    private View view;
    private ProgressBar nowPlaying_progressBar_fragment;
    private RecyclerView nowPlaying_recyclerView_fragment;
    private Dialog progressBarDialog;

    private NowPlayingAdapter adapter;
    private MovieViewModel viewModel;

    private int page, totalPage;


    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        setProgressBarDialog();
        initialize();
        setRecyclerView();
        setListener();

        return view;
    }

    private void setListener() {
        nowPlaying_recyclerView_fragment.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // TODO: Find alternative for endless scroll
                GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (manager.findLastVisibleItemPosition() > recyclerView.getAdapter().getItemCount() - 7) {
                    if ((page * 20) - recyclerView.getAdapter().getItemCount() < 3) {
                        if (page <= totalPage) {
                            page++;

                            viewModel.getNowPlaying(String.valueOf(page));
                            viewModel.getResultGetNowPlaying().observe(getActivity(), showResultNewNowPlaying);
                        }
                    }
                }
            }
        });
    }

    private void setRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        nowPlaying_recyclerView_fragment.setLayoutManager(manager);
    }

    private void initialize() {
        page = 1;

        nowPlaying_recyclerView_fragment = view.findViewById(R.id.nowPlaying_recyclerView_fragment);

        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.getNowPlaying(String.valueOf(page));
        viewModel.getResultGetNowPlaying().observe(getActivity(), showResultNowPlaying);
    }

    private void setProgressBarDialog() {
        progressBarDialog = new Dialog(getActivity());
        progressBarDialog.setContentView(R.layout.circular_progress_bar);
        progressBarDialog.setCancelable(false);
        progressBarDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        progressBarDialog.show();
    }

    private Observer<NowPlaying> showResultNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            totalPage = nowPlaying.getTotal_pages();

            adapter = new NowPlayingAdapter(getActivity());
            adapter.setNowPlayingList((ArrayList<NowPlaying.Results>) nowPlaying.getResults());
            nowPlaying_recyclerView_fragment.setAdapter(adapter);

            progressBarDialog.hide();




        }
    };

    private Observer<NowPlaying> showResultNewNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            adapter.addNowPlayingList((ArrayList<NowPlaying.Results>) nowPlaying.getResults());
            adapter.notifyItemRangeChanged(adapter.getItemCount() + 1, nowPlaying.getResults().size());
        }
    };
}