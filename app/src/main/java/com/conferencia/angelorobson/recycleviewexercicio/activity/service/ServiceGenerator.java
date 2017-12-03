package com.conferencia.angelorobson.recycleviewexercicio.activity.service;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.conferencia.angelorobson.recycleviewexercicio.activity.constants.Constants.API_BASE_URL;


public class ServiceGenerator {

  public static <S> S createService(Class<S> serviceClass) {

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
      .readTimeout(15, TimeUnit.SECONDS);

    httpClient.addInterceptor(loggingInterceptor);

    // Instância do retrofit
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(API_BASE_URL)
      .addConverterFactory(GsonConverterFactory.create(new Gson()))
      .client(httpClient.build())
      .build();

    return retrofit.create(serviceClass);
  }

}
