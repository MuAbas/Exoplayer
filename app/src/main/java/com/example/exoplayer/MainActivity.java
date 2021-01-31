package com.example.exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer2.ui.PlayerView;
import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class MainActivity extends AppCompatActivity {
    // Replace video id with your video Id
    private String mYoutubeLink = "https://www.youtube.com/watch?v=pzylyaGh9Zk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        extractYoutubeUrl();
    }
    private void extractYoutubeUrl() {
        @SuppressLint("StaticFieldLeak")
        YouTubeExtractor mExtractor = new YouTubeExtractor(this) {
            @Override
            protected void onExtractionComplete(SparseArray<YtFile> sparseArray, VideoMeta videoMeta) {
                if (sparseArray != null) {
                    int key = sparseArray.keyAt(0);
                    playVideo(sparseArray.get(key).getUrl());
                }
            }
        };
        mExtractor.extract(mYoutubeLink, true, true);
    }
    private void playVideo(String downloadUrl) {
        PlayerView mPlayerView = findViewById(R.id.mPlayerView);
        mPlayerView.setPlayer(ExoPlayerManager.getSharedInstance(MainActivity.this).getPlayerView().getPlayer());
        ExoPlayerManager.getSharedInstance(MainActivity.this).playStream(downloadUrl);
    }
}