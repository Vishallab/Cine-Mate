package com.vishal.cinemate.repositories;

import androidx.lifecycle.MutableLiveData;

import com.vishal.cinemate.helper.Const;
import com.vishal.cinemate.model.Trending;
import com.vishal.cinemate.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingRepository {
    public static TrendingRepository repository;

    public TrendingRepository() {

    }

    public static TrendingRepository getInstance() {
        if (repository == null) {
            repository = new TrendingRepository();
        }

        return repository;
    }

    public MutableLiveData<Trending> getTrendingData(String mediaType, String timeWindow) {
        final MutableLiveData<Trending> result = new MutableLiveData<>();

        ApiService.endPoint().getTrending(mediaType, timeWindow, Const.API_KEY).enqueue(new Callback<Trending>() {
            @Override
            public void onResponse(Call<Trending> call, Response<Trending> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Trending> call, Throwable t) {

            }
        });

        return result;
    }
}
