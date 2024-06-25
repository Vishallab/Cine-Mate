package com.vishal.cinemate.repositories;

import androidx.lifecycle.MutableLiveData;

import com.vishal.cinemate.helper.Const;
import com.vishal.cinemate.model.Movies;
import com.vishal.cinemate.model.NowPlaying;
import com.vishal.cinemate.model.Popular;
import com.vishal.cinemate.model.SimilarMovies;
import com.vishal.cinemate.model.TopRated;
import com.vishal.cinemate.model.Upcoming;
import com.vishal.cinemate.model.Videos;
import com.vishal.cinemate.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository repository;

    private MovieRepository() {

    }

    public static MovieRepository getInstance() {
        if (repository == null) {
            repository = new MovieRepository();
        }

        return repository;
    }

    public MutableLiveData<Movies> getMovieData(String movieId) {
        final MutableLiveData<Movies> result = new MutableLiveData<>();

        ApiService.endPoint().getMovieById(movieId, Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<NowPlaying> getNowPlayingData(String page) {
        final MutableLiveData<NowPlaying> result = new MutableLiveData<>();

        ApiService.endPoint().getNowPlaying(page, Const.API_KEY).enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<Upcoming> getUpcomingData(String page) {
        final MutableLiveData<Upcoming> result = new MutableLiveData<>();

        ApiService.endPoint().getUpcoming(page, Const.API_KEY).enqueue(new Callback<Upcoming>() {
            @Override
            public void onResponse(Call<Upcoming> call, Response<Upcoming> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Upcoming> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<Popular> getPopularData() {
        final MutableLiveData<Popular> result = new MutableLiveData<>();

        ApiService.endPoint().getPopular(Const.API_KEY).enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Popular> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<TopRated> getTopRatedData() {
        final MutableLiveData<TopRated> result = new MutableLiveData<>();

        ApiService.endPoint().getTopRated(Const.API_KEY).enqueue(new Callback<TopRated>() {
            @Override
            public void onResponse(Call<TopRated> call, Response<TopRated> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TopRated> call, Throwable t) {

            }
        });

        return result;
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

    // New method to fetch similar movies
    public MutableLiveData<SimilarMovies> getSimilarMovies(String movieId, int page) {
        MutableLiveData<SimilarMovies> data = new MutableLiveData<>();

        ApiService.endPoint().getSimilarMovies(movieId, Const.API_KEY, page).enqueue(new Callback<SimilarMovies>() {
            @Override
            public void onResponse(Call<SimilarMovies> call, Response<SimilarMovies> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    // Handle unsuccessful response here
                    // You might want to log the error or handle it in a different way
                }
            }

            @Override
            public void onFailure(Call<SimilarMovies> call, Throwable t) {
                // Handle failure here
                // For example, log the error or notify the UI about the failure
            }
        });

        return data;
    }
}