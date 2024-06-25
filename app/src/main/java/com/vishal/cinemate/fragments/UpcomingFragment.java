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
import com.vishal.cinemate.adapter.UpcomingAdapter;
import com.vishal.cinemate.model.Upcoming;
import com.vishal.cinemate.viewmodel.MovieViewModel;

import java.util.ArrayList;


public class UpcomingFragment extends Fragment {

    private View view;
    private RecyclerView upcoming_recyclerView_fragment;
    private Dialog progressBarDialog;

    private UpcomingAdapter adapter;
    private MovieViewModel viewModel;

    private int page, totalPage;


    public UpcomingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        setProgressBarDialog();
        initialize();
        setRecyclerView();
        setListener();

        return view;
    }

    private void setListener() {
        upcoming_recyclerView_fragment.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // ! This seems stupid
                // TODO: Find alternative for endless scroll
                GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (manager.findLastVisibleItemPosition() > recyclerView.getAdapter().getItemCount() - 7) {
                    if ((page * 20) - recyclerView.getAdapter().getItemCount() < 3) {

                        if (page <= totalPage) {
                            page++;

                            viewModel.getUpcoming(String.valueOf(page));
                            viewModel.getResultGetUpcoming().observe(getActivity(), showResultNewUpcoming);
                        }
                    }
                }
            }
        });
    }


    private void setRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);//span count ki kitni grid layout bnaye h 1 row me
        upcoming_recyclerView_fragment.setLayoutManager(manager);
    }

    private void initialize() {
        page = 1;

        upcoming_recyclerView_fragment = view.findViewById(R.id.upcoming_recyclerView_fragment);

        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.getUpcoming(String.valueOf(page));
        viewModel.getResultGetUpcoming().observe(getActivity(), showResultUpcoming);
    }

    private void setProgressBarDialog() {
        progressBarDialog = new Dialog(getActivity());
        progressBarDialog.setContentView(R.layout.circular_progress_bar);
        progressBarDialog.setCancelable(false);
        progressBarDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        progressBarDialog.show();
    }

    private Observer<Upcoming> showResultUpcoming = new Observer<Upcoming>() {
        @Override
        public void onChanged(Upcoming upcoming) {
            totalPage = upcoming.getTotal_pages();

            adapter = new UpcomingAdapter(getActivity());
            adapter.setUpcomingList((ArrayList<Upcoming.Results>) upcoming.getResults());
            upcoming_recyclerView_fragment.setAdapter(adapter);

            progressBarDialog.hide();
        }
    };

    private Observer<Upcoming> showResultNewUpcoming = new Observer<Upcoming>() {
        @Override
        public void onChanged(Upcoming upcoming) {
            adapter.addUpcomingList((ArrayList<Upcoming.Results>) upcoming.getResults());
            adapter.notifyItemRangeChanged(adapter.getItemCount() + 1, upcoming.getResults().size());
        }
    };
}