package im.dnn.labs.mjbestsongs.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import im.dnn.labs.mjbestsongs.R;
import im.dnn.labs.mjbestsongs.helpers.Picloader;
import im.dnn.labs.mjbestsongs.helpers.Utils;
import im.dnn.labs.mjbestsongs.iTunes.Track;

public class TrackItemAdapter extends RecyclerView.Adapter<TrackItemAdapter.ViewHolder> {

    private List<Track> tracklist;
    private Utils utils;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView trackName, collectionName;
        public ImageView artwork;

        public ViewHolder(View view) {
            super(view);

            this.view = view;
            trackName = view.findViewById (R.id.trackName);
            collectionName = view.findViewById (R.id.collectionName);
            artwork = view.findViewById (R.id.artwork);
        }
    }

    public TrackItemAdapter (List<Track> tracklist) {
        this.tracklist = tracklist;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from (parent.getContext ()).inflate (R.layout.adapter_track_item, parent, false);
        return new ViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        utils = new Utils (holder.view.getContext());

        Track track = tracklist.get(position);

        holder.trackName.setText (track.trackName);
        holder.collectionName.setText (track.collectionName);

        Picloader pic = new Picloader (holder.artwork);
            pic
                .placeholder (utils.getDrawable (R.drawable.artwork_placeholder))
                .error (utils.getDrawable (R.drawable.artwork_error))
                .load (track.artworkUrl100)
                .render ();

    }

    @Override
    public int getItemCount() {
        return tracklist.size();
    }
}
