package com.fayimora.sounddroid;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fayimora on 15/03/2016.
 */
public class Track {
  private int id;
  private String title;
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

  public static Track parse(JSONObject jsonObject) {
    Track t = new Track();
    try {
      t.setTitle(jsonObject.getString("title"));
    } catch (JSONException e) {
      Log.e("Track", e.getMessage());
    }
    return t;
  }
}
