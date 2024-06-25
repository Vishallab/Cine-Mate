package com.vishal.cinemate.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.vishal.cinemate.R;
import com.vishal.cinemate.helper.Const;
import com.vishal.cinemate.model.Movies;

import java.util.ArrayList;

public class MovieDetailProductionCompanyAdapter extends RecyclerView.Adapter<MovieDetailProductionCompanyAdapter.CardViewViewHolder> {

    private FragmentActivity fragmentActivity;
    private ArrayList<Movies.ProductionCompanies> productionCompaniesList;

    private ArrayList<Movies.ProductionCompanies> getProductionCompaniesList() {
        return productionCompaniesList;
    }

    public void setProductionCompaniesList(ArrayList<Movies.ProductionCompanies> productionCompaniesList) {
        this.productionCompaniesList = productionCompaniesList;
    }

    public MovieDetailProductionCompanyAdapter(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_movie_detail_production_company, parent, false);

        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Movies.ProductionCompanies results = getProductionCompaniesList().get(position);

        holder.movieDetail_textView_productionCompany.setText(results.getName());
        Glide.with(fragmentActivity).load(Const.IMG_URL_185 + results.getLogo_path()).into(holder.movieDetail_imageView_productionCompany);

        if (!TextUtils.isEmpty(results.getLogo_path())) {
            holder.movieDetail_textView_productionCompany.setVisibility(View.INVISIBLE);
        }

        holder.movieDetail_imageView_productionCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // * Make snackbar or we can say toast
                Snackbar snackbar = Snackbar.make(view, results.getName(), Snackbar.LENGTH_SHORT);
                snackbar.setAnchorView(fragmentActivity.findViewById(R.id.main_bottomNavView));

//                 * Trick to set margin from adapter with context
//                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) snackbar.getView().getLayoutParams();
//                params.setMargins(0, 0, 0, 56);
//                snackbar.getView().setLayoutParams(params);

                snackbar.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getProductionCompaniesList().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        TextView movieDetail_textView_productionCompany;
        ImageView movieDetail_imageView_productionCompany;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            movieDetail_textView_productionCompany = itemView.findViewById(R.id.movieDetail_textView_productionCompany);
            movieDetail_imageView_productionCompany = itemView.findViewById(R.id.movieDetail_imageView_productionCompany);
        }
    }
}
