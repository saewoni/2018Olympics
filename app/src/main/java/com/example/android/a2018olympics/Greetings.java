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
 * Defines what the list in the category Greetings should list and behave
 */

public class Greetings extends AppCompatActivity {

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

        phrases.add(new Phrase("Hello", "안녕하세요", R.raw.hello));
        phrases.add(new Phrase("Nice to meet you", "반갑습니다", R.raw.nice_to_meet_you));
        phrases.add(new Phrase("How are you?", "어떻게 지내세요?", R.raw.how_are_you));
        phrases.add(new Phrase("I am good", "잘 지내요", R.raw.i_am_good));
        phrases.add(new Phrase("My name is _____", "제 이름은 _____", R.raw.my_name_is));
        phrases.add(new Phrase("Goodbye", "안녕히 계세요", R.raw.goodbye_1));
        phrases.add(new Phrase("Goodbye", "안녕히 가세요", R.raw.goodbye_2));

        romaniz.add("ahn-nyung-ha-se-yo");
        romaniz.add("bahn-gap-seup-ni-da");
        romaniz.add("uh-dduh-keh ji-neh-seh-yo?");
        romaniz.add("jal-ji-neh-yo");
        romaniz.add("jeh ee-reum-un_____");
        romaniz.add("ahn-nyung-hee geh-seh-yo");
        romaniz.add("ahn-nyung-hee gah-seh-yo");

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

                // Create and setup the MediaPlayer for the audio resource associated with the
                // current word
                mediaPlayer = MediaPlayer.create(Greetings.this, phrases.get(i).getAudioId());

                //start the audio file
                mediaPlayer.start();

                if (i != 5 && i != 6) {
                    Toast.makeText(getApplicationContext(), romaniz.get(i), Toast.LENGTH_LONG).show();
                }
                //Toast messages that explains the difference between the two goodbyes
                if (i == 5) {
                    Toast.makeText(getApplicationContext(), romaniz.get(i) + "\nMeaning: Please stay well. Used when you are leaving" +
                            " but the other person is staying", Toast.LENGTH_LONG).show();
                }

                if (i == 6) {
                    Toast.makeText(getApplicationContext(), romaniz.get(i) + "\nMeaning: Please leave well. Used when you are staying, " +
                            "but the other person is leaving", Toast.LENGTH_LONG).show();
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
