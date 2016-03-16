package com.fayimora.sounddroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    tracks = new ArrayList<>();
    tracksAdapter = new TracksAdapter(tracks);

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.songs_list);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(tracksAdapter);

    service.searchSongs("call").clone().enqueue(new Callback<List<Track>>() {
      @Override
      public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
        if (response.isSuccessful()) {
          tracks.clear();
          tracks.addAll(response.body());
          tracksAdapter.notifyDataSetChanged();

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
