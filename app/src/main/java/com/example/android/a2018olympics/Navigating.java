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
 * Defines the behavior of the phrases in the Navigating class
 */

public class Navigating extends AppCompatActivity {
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

        phrases.add(new Phrase("Please call a taxi for me", "택시 불러 주세요", "tekshi bulluh juseyo", R.raw.call_taxi));
        phrases.add(new Phrase("left", "왼쪽", "oen-jjok", R.raw.left));
        phrases.add(new Phrase("right", "오른쪽", "oh-reun-jjok", R.raw.right));
        phrases.add(new Phrase("straight", "직진", "jik-jjin", R.raw.straight));
        phrases.add(new Phrase("I'm lost", "길을 잃었어요", "gil-eul  ilh-uht-suh-yo", R.raw.im_lost));
        phrases.add(new Phrase("Where is the subway station?", "지하철역 어디있어요?", "Jihachulyuk uhdi issuhyo?", R.raw.where_is_subway));
        phrases.add(new Phrase("Please take me to the airport", "공항으로 가주세요", "Gonghangeuro gajuseyo", R.raw.take_me_to_airport));
        phrases.add(new Phrase("Do you know where _____ is?", "_____ 어디인지 아세요?", "_____ uh-di-eehn-ji ah-seh-yo?", R.raw.where_is));
        phrases.add(new Phrase("I don't understand", "잘 모르겠네요", "jal mo-reu-geht-neh-yo", R.raw.i_dont_know));
        phrases.add(new Phrase("I don't speak Korean well", "한국말 잘 못해요", "hahn-guhk-mal jal moht-heh-yo", R.raw.cant_speak_korean));
        phrases.add(new Phrase("Can you speak English?", "영어 할 수 있어요?", "yung-uh  hal su-eet-suh-yo?", R.raw.can_u_speak_english));
        phrases.add(new Phrase("Please speak slowly", "천천히 말씀해 주세요", "chun-chun-hee mal-sseum-heh ju-seh-yo", R.raw.speak_slowly));
        phrases.add(new Phrase("Please take me to _____", "_____ (으)로 가주세요", "(ih)-roh gah-ju-seh-yo", R.raw.plz_take_me_to));
        phrases.add(new Phrase("Where is the bathroom?", "화장실이 어디예요?", "hwa-jang-shil-ee  uh-di-eh-yo?", R.raw.wheres_the_br));


        //romanization phrases used for Toast messages
        romaniz.add("tek-shi bul-luh ju-se-yo");
        romaniz.add("wen-jjok");
        romaniz.add("oh-reun-jjok");
        romaniz.add("jik-jjin");
        romaniz.add("gil-eul ilh-uht-suh-yo");
        romaniz.add("ji-ha-chul-yuk uh-di iss-uh-yo?");
        romaniz.add("gong-haang-eu-ro ga-ju-se-yo");
        romaniz.add("_____ uh-di-eehn-ji ah-seh-yo?");
        romaniz.add("jal mo-reu-geht-neh-yo");
        romaniz.add("hahn-guhk-mal jal moht-heh-yo");
        romaniz.add("yung-uh hal su-eet-suh-yo?");
        romaniz.add("chun-chun-hee mal-sseum-heh ju-seh-yo");
        romaniz.add("(ih)-roh gah-ju-seh-yo");
        romaniz.add("hwa-jang-shil-ee uh-di-eh-yo?");
        PhraseAdapter adapter = new PhraseAdapter(this, phrases);

        ListView listView = (ListView)findViewById(R.id.phrases_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                // Create and setup the MediaPlayer for the audio resource associated with the
                // current word
                mediaPlayer = MediaPlayer.create(Navigating.this, phrases.get(i).getAudioId());

                //start the audio file
                mediaPlayer.start();

                if (i != 12) {
                    Toast.makeText(getApplicationContext(), romaniz.get(i), Toast.LENGTH_LONG).show();
                } else {
                    // extra fact about "으" and "으로"
                    Toast.makeText(getApplicationContext(), romaniz.get(i) + "\n" +
                            "When the place name ends with a consonant, use “으로” \nOtherwise only use “로”", Toast.LENGTH_LONG).show();
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
