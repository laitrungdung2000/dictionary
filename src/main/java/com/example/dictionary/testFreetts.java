package com.example.dictionary;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class testFreetts {

    public static void main(String[] args) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin");
        ;
        if (voice != null) {
            voice.allocate();
        }
        try {
            voice.setRate(190);
            voice.setPitch(100);
            voice.setVolume(3);
            voice.speak("helo i am dung");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
