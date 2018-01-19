package com.example.android.a2018olympics;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Defines what the list in the category Courtesy should list and behave
 */

public class Courtesy extends AppCompatActivity {

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

        phrases.add(new Phrase("Thank you", "감사합니다", R.raw.thank_you));
        phrases.add(new Phrase("You're welcome", "천만에요", R.raw.youre_welcome));

        //TODO add the difference between the two excuse me phrases (Toast message)
        phrases.add(new Phrase("Excuse me", "실례합니다", R.raw.excuse_me_1));
        phrases.add(new Phrase("Excuse me", "잠시만요", R.raw.excuse_me_2));

        phrases.add(new Phrase("Over here", "여기요", R.raw.over_here));

        //TODO add the difference between the two sorry phrases (Toast message)
        phrases.add(new Phrase("I'm sorry", "죄송합니다", R.raw.sorry_1));
        phrases.add(new Phrase("I'm sorry", "미안합니다", R.raw.sorry_2));

        romaniz = new ArrayList<>();
        romaniz.add("gam-sa-ham-ni-da");
        romaniz.add("chun-mahn-eh-yo");
        romaniz.add("shil-leh-hap-nee-da");
        romaniz.add("jam-shi-mahn-yo");
        romaniz.add("yuh-gi-yo");
        romaniz.add("jweh-sung-hap-nee-da");
        romaniz.add("mi-ahn-hap-nee-da");

        PhraseAdapter adapter = new PhraseAdapter(this, phrases);

        ListView listView = (ListView)findViewById(R.id.phrases_list_view);

        listView.setAdapter(adapter);


        // displaying the items of adapter on the list
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                // Create and setup the MediaPlayer for the audio resource associated with the
                // current word
                mediaPlayer = MediaPlayer.create(Courtesy.this, phrases.get(i).getAudioId());

                //start the audio file
                mediaPlayer.start();

                if (i != 2 && i != 3 && i != 5 && i != 6) {
                    Log.v("Courtesy", "I'm being pressed yall");
                    Toast.makeText(getApplicationContext(), romaniz.get(i), Toast.LENGTH_LONG).show();
                }

                //Toast messages that explains the difference between the two goodbyes
                if (i == 2) {
                    Toast.makeText(getApplicationContext(), romaniz.get(i) + "\nVery polite phrase used to get someone’s attention, " +
                            "such as for when you approach a stranger for help.", Toast.LENGTH_LONG).show();
                }

                if (i == 3) {
                    Toast.makeText(getApplicationContext(), romaniz.get(i) + "\nMeans “Wait a moment” but can be used for situations." +
                            " Also used when you need to push past people in a busy crowd", Toast.LENGTH_LONG).show();
                }

                if (i == 5 || i == 6) {
                    Toast.makeText(getApplicationContext(), romaniz.get(i) + "\nBoth ways of apologizing to excuse your behavior" +
                            " is formal and can be used interchangeably", Toast.LENGTH_LONG).show();
                }



                // Set up a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
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
