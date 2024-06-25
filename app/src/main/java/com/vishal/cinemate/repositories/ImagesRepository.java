package com.vishal.cinemate.repositories;

import androidx.lifecycle.MutableLiveData;

import com.vishal.cinemate.helper.Const;
import com.vishal.cinemate.model.Backdrops;
import com.vishal.cinemate.model.Videos;
import com.vishal.cinemate.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagesRepository {
    public static ImagesRepository repository;

    public ImagesRepository() {

    }

    public static ImagesRepository getInstance() {
        if (repository == null) {
            repository = new ImagesRepository();
        }

        return repository;
    }
    public MutableLiveData<Videos> getVideoData(String movieId) {
        final MutableLiveData<Videos> result = new MutableLiveData<>();

        ApiService.endPoint().getVideo(movieId, Const.API_KEY).enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

            }
        });

        return result;
    }
    public MutableLiveData<Backdrops> getImagesData(String movieId) {
        final MutableLiveData<Backdrops> result = new MutableLiveData<>();

        ApiService.endPoint().getImages(movieId, Const.API_KEY).enqueue(new Callback<Backdrops>() {
            @Override
            public void onResponse(Call<Backdrops> call, Response<Backdrops> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Backdrops> call, Throwable t) {

            }
        });

        return result;
    }
}
