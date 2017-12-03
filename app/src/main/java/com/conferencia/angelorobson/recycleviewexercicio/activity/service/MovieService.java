package com.conferencia.angelorobson.recycleviewexercicio.activity.service;


import com.conferencia.angelorobson.recycleviewexercicio.activity.model.MovieResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Angelo on 03/12/2017.
 */

public interface MovieService {

  @GET("discover/movie")
  Call<MovieResults> getAllMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("sort_by") String sortBy, @Query("page") Integer page);

}
