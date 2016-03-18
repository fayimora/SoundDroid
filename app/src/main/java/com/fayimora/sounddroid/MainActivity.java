package com.fayimora.sounddroid;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  static String TAG = "MainActivity";

  SoundCloudService service = SoundCloud.getService();
  private List<Track> tracks;
  TracksAdapter tracksAdapter;
  private TextView selectedTitleView;
  private ImageView selectedThumbnailView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final Toolbar playerToolbar = (Toolbar) findViewById(R.id.player_toolbar);
    selectedTitleView = (TextView) findViewById(R.id.selected_title);
    selectedThumbnailView = (ImageView) findViewById(R.id.selected_thumbnail);

    tracks = new ArrayList<>();
    tracksAdapter = new TracksAdapter(this, tracks);

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.songs_list);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(tracksAdapter);

    tracksAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Track selectedTrack = tracks.get(position);
        selectedTitleView.setText(selectedTrack.getTitle());
        Picasso.with(MainActivity.this).load(selectedTrack.getArtworkUrl()).into(selectedThumbnailView);
      }
    });

    service.searchSongs("call", 20).clone().enqueue(new Callback<List<Track>>() {
      @Override
      public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
        if (response.isSuccessful()) {
          tracks.clear();
          tracks.addAll(response.body());
          tracksAdapter.notifyDataSetChanged();
          //Log.d(TAG, "track 1 avatar URL: " + tracks.get(0).getArtworkUrl());
        } else {
          Log.e(TAG, response.message());
          Log.e(TAG, response.code()+"");
        }
      }

      @Override
      public void onFailure(Call<List<Track>> call, Throwable t) {
        Log.d(TAG, t.getMessage());
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
