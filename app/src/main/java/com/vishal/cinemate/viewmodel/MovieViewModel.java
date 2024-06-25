package com.vishal.cinemate.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vishal.cinemate.model.Movies;
import com.vishal.cinemate.model.NowPlaying;
import com.vishal.cinemate.model.Popular;
import com.vishal.cinemate.model.TopRated;

import com.vishal.cinemate.model.SimilarMovies;
import com.vishal.cinemate.model.Upcoming;
import com.vishal.cinemate.model.Videos;
import com.vishal.cinemate.repositories.MovieRepository;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance();
    }

    //==Start of view model get movie by id
    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();
    public void getMovieById(String movieId) {
        resultGetMovieById = repository.getMovieData(movieId);
    }
    public LiveData<Movies> getResultGetMovieById() {
        return resultGetMovieById;
    }
    //==End of view model get movie by id

    //==Start of view model get now playing
    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlaying(String page) {
        resultGetNowPlaying = repository.getNowPlayingData(page);
    }
    public LiveData<NowPlaying> getResultGetNowPlaying() {
        return resultGetNowPlaying;
    }
    //==End of view model get now playing

    //==Start of view model get upcoming
    private MutableLiveData<Upcoming> resultGetUpcoming = new MutableLiveData<>();
    public void getUpcoming(String page) {
        resultGetUpcoming = repository.getUpcomingData(page);
    }
    public LiveData<Upcoming> getResultGetUpcoming() {
        return resultGetUpcoming;
    }
    //==End of view model get upcoming

    //==Start of view model get popular
    private MutableLiveData<Popular> resultGetPopular = new MutableLiveData<>();
    public void getPopular() {
        resultGetPopular = repository.getPopularData();
    }
    public LiveData<Popular> getResultGetPopular() {
        return resultGetPopular;
    }
    //==End of view model get popular

    //==Start of view model get top rated
    private MutableLiveData<TopRated> resultGetTopRated = new MutableLiveData<>();
    public void getTopRated() {
        resultGetTopRated = repository.getTopRatedData();
    }
    public LiveData<TopRated> getResultGetTopRated() {
        return resultGetTopRated;
    }
    //==End of view model get top rated

    //==Start of view model get video by id
    private MutableLiveData<Videos> resultGetVideoById = new MutableLiveData<>();
    public void getVideoById(String movieId) {
        resultGetVideoById = repository.getVideoData(movieId);
    }
    public LiveData<Videos> getResultGetVideoById() {
        return resultGetVideoById;
    }
    //==End of view model get video by id


    // Inside MovieViewModel

    // Start of view model get similar movies

    // Start of view model get similar movies

    private MutableLiveData<SimilarMovies> resultGetSimilarMovies = new MutableLiveData<SimilarMovies>();

    public void getSimilarMovies(String movieId, int page) {
        resultGetSimilarMovies = repository.getSimilarMovies(movieId, page);
    }

    public LiveData<SimilarMovies> getResultGetSimilarMovies() {
        return resultGetSimilarMovies;
    }

    // End of view model get similar movies
}
