package com.fayimora.sounddroid;


import com.google.gson.annotations.SerializedName;

/**
 * Created by fayimora on 15/03/2016.
 */
public class Track {
  @SerializedName("id")
  private int id;

  @SerializedName("title")
  private String title;

  @SerializedName("stream_url")
  private String streamUrl;

  @SerializedName("artwork_url")
  private String artworkUrl;

  public int getId() {
    return id;
  }

  public String getStreamUrl() {
    return streamUrl;
  }

  public String getTitle() {
    return title;
  }

  public String getArtworkUrl() {
    String avatarUrl = artworkUrl;
    if(avatarUrl != null)
      avatarUrl = artworkUrl.replace("large", "tiny");
    return avatarUrl;
  }
}
