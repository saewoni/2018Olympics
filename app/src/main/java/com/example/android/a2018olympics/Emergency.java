package com.example.android.a2018olympics;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Defines how the class Emergency should behave. Defines all of the items that this class
 * should list
 */

public class Emergency extends AppCompatActivity {

    private MediaPlayer mediaPlayer ;

    private ArrayList<Phrase> phrases;
    private ArrayList<String> romaniz;

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
        romaniz = new ArrayList<>();
        phrases.add(new Phrase("Please help me!", "도와주세요!", R.raw.help_me));
        phrases.add(new Phrase("It’s an emergency", "긴급 상황이에요", R.raw.its_emergency));
        phrases.add(new Phrase("Call the police","경찰을 부르세요", R.raw.call_the_police));
        phrases.add(new Phrase("I think I ate something bad", "뭔가 잘못 먹은 거 같아요", R.raw.ate_something_bad));
        phrases.add(new Phrase("Where is the hospital?", "병원 어딨어요?", R.raw.where_is_hospital));
        phrases.add(new Phrase("It hurts here", "여기가 아파요", R.raw.it_hurts_here));
        phrases.add(new Phrase("I need a doctor", "의사가 필요해요", R.raw.i_need_doctor));

        romaniz.add("doh-oah-ju-seh-yo!");
        romaniz.add("kin-geup-sahng-hwang-ee-eh-yo");
        romaniz.add("kyung-chal-eul bu-reu-seh-yo");
        romaniz.add("mwon-ga jal-mot meog-eun geo gat-ayo");
        romaniz.add("byung-won uh-di iss-uhyo?");
        romaniz.add("yuh-gi-gah ah-pah-yo");
        romaniz.add("uie-sah-gah pil-yo-he-yo");


        PhraseAdapter adapter = new PhraseAdapter(this, phrases);
        ListView listView = (ListView)findViewById(R.id.phrases_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(Emergency.this, phrases.get(i).getAudioId());

                mediaPlayer.start();
                Toast.makeText(getApplicationContext(), romaniz.get(i), Toast.LENGTH_LONG).show();

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
