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
 * Created by saewonkwak on 1/16/18.
 */

public class DineAndShop extends AppCompatActivity {

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
        //phrases for dining
        phrases.add(new Phrase("Please give me _____", "_____ 주세요", R.raw.plz_give_me));
        phrases.add(new Phrase("Menu, please", "메뉴 주세요", R.raw.menu_plz));
        phrases.add(new Phrase("Bill, please", "계산서 주세요", R.raw.bill_plz));
        phrases.add(new Phrase("Please wrap the leftovers", "싸 주세요", R.raw.wrap_leftover));
        phrases.add(new Phrase("I have an allergy", "저 알러지 있어요", R.raw.have_allergy));

        //phrases for shopping
        phrases.add(new Phrase("Do you have _____?", "_____ 있어요?", R.raw.do_you_have_));
        phrases.add(new Phrase("How much is it?", "얼마에요?", R.raw.how_much_is_it));
        phrases.add(new Phrase("Do you take credit cards?", "카드 받으세요?", R.raw.do_you_take_credit_card));
        phrases.add(new Phrase("Please give me a refund", "환불해 주세요", R.raw.refund_plz));
        phrases.add(new Phrase("Please give me an exchange","교환해 주세요", R.raw.gimme_exchange));

        //romanization for dining
        romaniz.add("_____ ju-seh-yo");
        romaniz.add("meh-nyu ju-seh-yo");
        romaniz.add("keh-san-suh ju-seh-yo");
        romaniz.add("ssa ju-seh-yo");
        romaniz.add("juh ahl-luh-ji eet-suh-yo");

        //romanization for shopping
        romaniz.add("eess-uh-yo?");
        romaniz.add("uhl-mah-eh-yo?");
        romaniz.add("kah-deu bat-euh-seh-yo?");
        romaniz.add("hwan-bul-heh ju-seh-yo");
        romaniz.add("gyo-hwan-heh ju-seh-yo");

        PhraseAdapter adapter = new PhraseAdapter(this, phrases);
        ListView listView = (ListView)findViewById(R.id.phrases_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                mediaPlayer = MediaPlayer.create(DineAndShop.this, phrases.get(i).getAudioId());
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
