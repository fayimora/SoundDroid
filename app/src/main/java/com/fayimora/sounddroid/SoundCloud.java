package com.fayimora.sounddroid;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fayimora on 16/03/2016.
 */
public class SoundCloud {
  private static final String API_URL = "http://api.soundcloud.com";
  private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

  private static OkHttpClient.Builder httpClient =
      new OkHttpClient.Builder().addInterceptor(logging);

  private static Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(API_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .client(httpClient.build())
      .build();

  private static SoundCloudService service = retrofit.create(SoundCloudService.class);

  private SoundCloud() {}

  public static SoundCloudService getService() {
    return service;
  }
}
