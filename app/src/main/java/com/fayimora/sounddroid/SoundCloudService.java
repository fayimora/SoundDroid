package com.fayimora.sounddroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fayimora on 15/03/2016.
 */
public interface SoundCloudService {

  String CLIENT_ID = "ce8b07111836f01f9cc97f6b444a40ed";

  @GET("/tracks?client_id="+CLIENT_ID)
  Call<List<Track>> searchSongs(@Query("q") String query, @Query("limit") int limit);
}
