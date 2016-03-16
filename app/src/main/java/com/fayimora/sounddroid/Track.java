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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStreamUrl() {
    return streamUrl;
  }

  public void setStreamUrl(String streamUrl) {
    this.streamUrl = streamUrl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
