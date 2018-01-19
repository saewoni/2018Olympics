package com.example.android.a2018olympics;

/**
 * The Phrase class has 4 instances: koreanTranslation, defaultTranslation, romanization, and
 * audioId. The String romanization is used all throughout the classes except for Sports class.
 * Has public methods that return the readable-copies of the private instances.
 *
 * All of the phrases are based off of
 * https://www.fluentu.com/blog/korean/korean-travel-phrases/
 *
 * And some other phrases I found online. I don't own the copyright to those phrases but
 * this app was made for a good practice in Android Development :)
 */

public class Phrase {

    private String koreanTranslation;
    private String defaultTranslation;
    private String romanization;
    private int audioId;


    public Phrase(String defaultTranslationIn, String koreanTranslationIn, int audioIdIn) {
        defaultTranslation = defaultTranslationIn;
        koreanTranslation = koreanTranslationIn;
        audioId = audioIdIn;
    }

    public Phrase(String defaultTranslationIn, String koreanTranslationIn, String romanizationIn, int audioIdIn) {
        defaultTranslation = defaultTranslationIn;
        koreanTranslation = koreanTranslationIn;
        audioId = audioIdIn;
        romanization = romanizationIn;
    }


    public String getKoreanTranslation() {
        return koreanTranslation;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public int getAudioId() {
        return audioId;
    }
    @Override
    public String toString() {
        return "Korean translation: " + koreanTranslation + "\n"
                + "is pronounced like " + romanization + "\n"
                + "English translation: " + defaultTranslation;
    }
}
