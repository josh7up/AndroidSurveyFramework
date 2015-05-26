package com.example.jaf50.survey.service;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayerService {

  private static AudioPlayerService instance = null;
  private MediaPlayer mediaPlayer;

  private AudioPlayerService() {}

  public static AudioPlayerService getInstance() {
    if (instance == null) {
      instance = new AudioPlayerService();
    }
    return instance;
  }

  public void play(Context context, int audioResourceId) {
    if (mediaPlayer != null) {
      mediaPlayer.stop();
      mediaPlayer.release();
    }
    try {
      mediaPlayer = MediaPlayer.create(context, audioResourceId);
      if (mediaPlayer != null) {
        mediaPlayer.start();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void stop() {
    if (mediaPlayer != null) {
      mediaPlayer.stop();
    }
  }
}