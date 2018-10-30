package com.kabir.imageeditor;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.outstarttech.kabir.eidcardeditor.R;

import java.util.List;
import java.util.Locale;

public class BasicActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech myTTS;
    private SpeechRecognizer speechRecognizer;

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
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                speechRecognizer.startListening(intent);
            }
        });

        initializeTextToSpeech();
        initializeSpeechRecognizer();
    }

    private void initializeSpeechRecognizer() {

        if(SpeechRecognizer.isRecognitionAvailable(this)){
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float v) {

                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int i) {

                }

                @Override
                public void onResults(Bundle bundle) {

                    List<String> results = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    processResults(results.get(0));

                }

                @Override
                public void onPartialResults(Bundle bundle) {

                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            });
        }

    }

    private void processResults(String command) {

        command = command.toLowerCase();

        if(command.indexOf("Main") != -1)
        {
            speak("Opening The Main Activity");
            startActivity(new Intent(BasicActivity.this, MainActivity.class));
        }

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

        });
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
