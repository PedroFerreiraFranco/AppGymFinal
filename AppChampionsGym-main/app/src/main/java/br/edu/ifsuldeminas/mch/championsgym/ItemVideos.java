package br.edu.ifsuldeminas.mch.championsgym;

public class ItemVideos {

    String nameExercicio;
    String description;
    int video;

    public ItemVideos(String nameExercicio, String description, int video) {
        this.nameExercicio = nameExercicio;
        this.description = description;
        this.video = video;
    }

    public String getNameExercicio() {
        return nameExercicio;
    }

    public void setNameExercicio(String nameExercicio) {
        this.nameExercicio = nameExercicio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }
}
