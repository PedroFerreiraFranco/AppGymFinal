package br.edu.ifsuldeminas.mch.championsgym;

import android.net.Uri;

public class Exercise {
    private String name;
    private String description;
    private Uri videoUri;

    public Exercise(String name, String description, Uri videoUri) {
        this.name = name;
        this.description = description;
        this.videoUri = videoUri;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Uri getVideoUri() {
        return videoUri;
    }
}
