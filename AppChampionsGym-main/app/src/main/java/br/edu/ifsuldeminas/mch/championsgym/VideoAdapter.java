package br.edu.ifsuldeminas.mch.championsgym;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;

public class VideoAdapter extends BaseAdapter {

    private Context context;
    private List<Exercise> exercises;
    private LayoutInflater inflater;

    public VideoAdapter(Context context, List<Exercise> exercises) {
        this.context = context;
        this.exercises = exercises;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Override
    public Object getItem(int position) {
        return exercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_view_video, parent, false);
            holder = new ViewHolder();
            holder.videoView = convertView.findViewById(R.id.videoView);
            holder.exerciseName = convertView.findViewById(R.id.exerciseName);
            holder.exerciseDescription = convertView.findViewById(R.id.exerciseDescription);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Exercise exercise = exercises.get(position);

        holder.exerciseName.setText(exercise.getName());
        holder.exerciseDescription.setText(exercise.getDescription());

        Uri videoUri = exercise.getVideoUri();
        holder.videoView.setVideoURI(videoUri);

        // Configurar o MediaController
        MediaController mediaController = new MediaController(context);
        holder.videoView.setMediaController(mediaController);
        mediaController.setAnchorView(holder.videoView);

        return convertView;
    }

    private static class ViewHolder {
        VideoView videoView;
        TextView exerciseName;
        TextView exerciseDescription;
    }
}
