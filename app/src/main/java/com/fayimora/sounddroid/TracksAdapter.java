package com.fayimora.sounddroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fayimora on 16/03/2016.
 */
public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {

  public class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView;
    private final ImageView thumbnailImageView;

    ViewHolder(View v){
      super(v);
      titleTextView = (TextView) v.findViewById(R.id.track_title);
      thumbnailImageView = (ImageView) v.findViewById(R.id.track_thumbnail);
    }
  }

  private List<Track> tracks;
  TracksAdapter(List<Track> tracks){
    this.tracks = tracks;
  }

  @Override
  public int getItemCount() {
    return tracks.size();
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    String title = tracks.get(position).getTitle();
    holder.titleTextView.setText(title);
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_row, parent, false);
    return new ViewHolder(v);
  }
}
