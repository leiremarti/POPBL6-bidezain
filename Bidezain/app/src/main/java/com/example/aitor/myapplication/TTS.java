package com.example.aitor.myapplication;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class TTS {
    TextToSpeech t1;

    public TTS(Context context){
        t1 = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("spa", "MEX"));
                }
            }
        });
    }

    public void speak(String s){
        t1.speak(s, TextToSpeech.QUEUE_FLUSH, null);
    }
}
