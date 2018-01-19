package com.example.android.a2018olympics;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Defines what the list in the category Sports should list and behave.
 * Didn't add romanization for this class because essentially all sports names are based on
 * English pronunciations
 */

public class Sports extends AppCompatActivity {

    private MediaPlayer mediaPlayer ;

    private ArrayList<Phrase> phrases;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.phrase_list);

        phrases = new ArrayList<>();

        phrases.add(new Phrase("Alpine Skiing", "알파인 스키", R.raw.alpine_skiing));
        phrases.add(new Phrase("Biathlon", "바이애슬론", R.raw.biathlon));
        phrases.add(new Phrase("Bobsleigh", "봅슬레이", R.raw.bobsleigh));
        phrases.add(new Phrase("Cross Country Skiing", "크로스컨트리", R.raw.cross_country));
        phrases.add(new Phrase("Curling", "컬링", R.raw.curling));
        phrases.add(new Phrase("Figure Skating", "피겨스케이팅", R.raw.figure_skating));
        phrases.add(new Phrase("Freestyle Skiing", "프리스타일", R.raw.free_style));
        phrases.add(new Phrase("Ice Hockey", "아이스하키", R.raw.ice_hockey));
        phrases.add(new Phrase("Luge", "루지", R.raw.luge));
        phrases.add(new Phrase("Nordic Combined", "노르딕복합", R.raw.nordic_combined));
        phrases.add(new Phrase("Short Track Speed Skating", "쇼트트랙", R.raw.short_track));
        phrases.add(new Phrase("Skeleton", "스켈레톤", R.raw.skeleton));
        phrases.add(new Phrase("Ski Jumping", "스키점프", R.raw.ski_jump));
        phrases.add(new Phrase("Snowboard", "스노보드", R.raw.snowboard));
        phrases.add(new Phrase("Speed Skating", "스피드스케이팅", R.raw.speed_skating));
        // PhraseAdapter extends to ArrayAdapter
        PhraseAdapter adapter = new PhraseAdapter(this, phrases);

        // Displaying the vertically-scrollable collection of views
        ListView listView = (ListView)findViewById(R.id.phrases_list_view);

        // displaying the items of adapter on the list
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                mediaPlayer = MediaPlayer.create(Sports.this, phrases.get(i).getAudioId());

                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(completionListener);
            }
        });
    }

    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }


    }
}
