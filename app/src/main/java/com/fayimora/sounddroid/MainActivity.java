package com.fayimora.sounddroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  static String TAG = "MainActivity";

  SoundCloudService service = SoundCloud.getService();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    service.searchSongs("7 years").clone().enqueue(new Callback<List<Track>>() {
      @Override
      public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
        if (response.isSuccessful()) {
          List<Track> tracks = response.body();
          Log.d(TAG, "Track title is " + tracks.get(0).getTitle());
        } else {
          Log.e(TAG, response.message());
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
