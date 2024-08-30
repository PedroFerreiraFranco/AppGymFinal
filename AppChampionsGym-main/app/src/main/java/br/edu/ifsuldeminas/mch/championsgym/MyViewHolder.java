package br.edu.ifsuldeminas.mch.championsgym;

import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    VideoView videoView;
    TextView nameExercicio,descriptionExercicio;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        videoView = itemView.findViewById(R.id.videoView);
    }
}
