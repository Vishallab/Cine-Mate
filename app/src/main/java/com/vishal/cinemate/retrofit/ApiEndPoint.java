package com.vishal.cinemate.retrofit;

import com.vishal.cinemate.model.Backdrops;
import com.vishal.cinemate.model.Movies;
import com.vishal.cinemate.model.NowPlaying;
import com.vishal.cinemate.model.Popular;
import com.vishal.cinemate.model.SimilarMovies;
import com.vishal.cinemate.model.TopRated;
import com.vishal.cinemate.model.Trending;
import com.vishal.cinemate.model.Upcoming;
import com.vishal.cinemate.model.Videos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoint {

    //==Start of movie API

    //is vale se mivie ka sb kuch aajat ah jaisee geners comey h ya draam kya
    //uska title ,overviewuski popularety poster path sb kuch
    @GET("movie/{movie_id}")
    Call<Movies> getMovieById(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
    );


    // for now playing
    @GET("movie/now_playing")
    Call<NowPlaying> getNowPlaying(
            @Query("page") String page,
            @Query("api_key") String apiKey
    );

    //for upcomming
    @GET("movie/upcoming")
    Call<Upcoming> getUpcoming(
            @Query("page") String page,
            @Query("api_key") String apiKey
    );


    //popular show krne k liye
    @GET("movie/popular")
    Call<Popular> getPopular(
            @Query("api_key") String apiKey
    );

    //top rated show krne k liye
    @GET("movie/top_rated")
    Call<TopRated> getTopRated(
            @Query("api_key") String apiKey
    );



    // Define the method to get similar movies
    @GET("movie/{movie_id}/similar")
    Call<SimilarMovies> getSimilarMovies(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey,
            @Query("page") int page
    );



    //movie ke trailer  k liye us movie ka key mil jata h jo ki hum yt k
    //https://www.youtube.com/watch?v=" is k aage add krte hi ek ura link bn jata h

    @GET("movie/{movie_id}/videos")
    Call<Videos> getVideo(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
    );
    //==End of movie API



    //   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




    //==Start of genre API


//    ki typa ka genre h uski id or sb name ayega

    //==End of genre API


    //==Start of images API

    @GET("movie/{movie_id}/images")
    Call<Backdrops> getImages(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
    );





    //==Start of trending API
    @GET("trending/{media_type}/{time_window}")
    Call<Trending> getTrending(
            @Path("media_type") String mediaType,
            @Path("time_window") String timeWindow,
            @Query("api_key") String apiKey
    );
    //==End of trending API
}
