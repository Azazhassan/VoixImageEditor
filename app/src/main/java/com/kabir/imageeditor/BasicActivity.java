package com.kabir.imageeditor;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.outstarttech.kabir.eidcardeditor.R;

import java.util.Locale;

public class BasicActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech myTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initializeTextToSpeech()
    {
        myTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if(myTTS.getEngines().size() == 0)
                {
                    Toast.makeText(BasicActivity.this, "There Are No Voice Integrations In Your Device", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    myTTS.setLanguage(Locale.US);
                    speak("Hello, I'm Ready");
                }

            }

            private void speak(String msg)
            {
                if(Build.VERSION.SDK_INT >= 21)
                {
                    myTTS.speak(msg, TextToSpeech.QUEUE_FLUSH,null,null);
                }
                else
                {
                    myTTS.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        myTTS.shutdown();
    }

    @Override
    public void onInit(int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
