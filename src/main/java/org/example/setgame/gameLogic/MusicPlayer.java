package org.example.setgame.gameLogic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

    private static MusicPlayer instance;
    private MediaPlayer mediaPlayer;

    private MusicPlayer() {
        String musicFile = getClass().getResource("/org/example/setgame/backgroundMusic.mp3").toString();
        Media media = new Media(musicFile);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public static synchronized MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }

    public void play() {
        mediaPlayer.play();
    }

    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public void toggleMute() {
        mediaPlayer.setMute(!mediaPlayer.isMute());
    }

    public boolean isMuted() {
        return mediaPlayer.isMute();
    }
}

