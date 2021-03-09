package com.pollub.ism_lab02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView poleTekstowe=null;
    private EditText poleEdycyjne=null;
    private Button przycisk1=null,przycisk2=null,przycisk3=null;

    private int createCounter=0,startCounter=0,resumeCounter=0,
                stopCounter=0,restartCounter=0,destroyCounter=0;
    private int pauseCounter=0;

    private static String KEY_CREATE="Wartość licznika create",
                    KEY_START="Wartość licznika start",
                    KEY_RESUME="Wartość licznika reasume",
                    KEY_PAUSE="Wartość licznika pause",
                    KEY_STOP="Wartość licznika stop",
                    KEY_RESTART="Wartość licznika restart",
                    KEY_DESTROY="Wartość licznika destroy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poleTekstowe=(TextView) findViewById(R.id.textView2);
        poleEdycyjne=(EditText) findViewById(R.id.editText);
        przycisk1=(Button) findViewById(R.id.button1);
        przycisk2=(Button) findViewById(R.id.button2);
        przycisk3=(Button) findViewById(R.id.button3);

        poleEdycyjne.setEnabled(false);
        przycisk2.setText(getResources().getString(R.string.odblokuj));

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CREATE,createCounter);
        outState.putInt(KEY_START,startCounter);
        outState.putInt(KEY_RESUME,resumeCounter);
        outState.putInt(KEY_PAUSE,pauseCounter);
        outState.putInt(KEY_STOP,stopCounter);
        outState.putInt(KEY_RESTART,restartCounter);
        outState.putInt(KEY_DESTROY,destroyCounter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        createCounter=savedInstanceState.getInt(KEY_CREATE,0);
        startCounter=savedInstanceState.getInt(KEY_START,0);
        resumeCounter=savedInstanceState.getInt(KEY_RESUME,0);
        pauseCounter=savedInstanceState.getInt(KEY_PAUSE,0);
        stopCounter=savedInstanceState.getInt(KEY_STOP,0);
        restartCounter=savedInstanceState.getInt(KEY_RESTART,0);
        destroyCounter=savedInstanceState.getInt(KEY_DESTROY,0);

    }

    private void powiedzCoWykonujesz(String info){
        poleTekstowe.setText(info);
        poleEdycyjne.append(info+"\n");

    }

    public void wyczyscPoleEdycyjne(View view){
        poleEdycyjne.setText("");
    }

    public void zablokujOdblokujPoleEdycyjne(View view){
        poleEdycyjne.setEnabled(!poleEdycyjne.isEnabled());

        if(poleEdycyjne.isEnabled()){
            przycisk2.setText(getResources().getString(R.string.zablokuj));
        }
        else{
            przycisk2.setText(getResources().getString(R.string.odblokuj));
        }
    }


    public void wyswietlZmienne(View view){
        String info="create" +createCounter
                +" start="+stopCounter
                +" reasume="+resumeCounter
                +"\npause="+pauseCounter
                +" stop="+stopCounter
                +" restart="+restartCounter
                +"\ndestroy="+destroyCounter;

        poleEdycyjne.setText(info +"\n");
    }


    @Override
    protected void onStart() {
        super.onStart();
        powiedzCoWykonujesz("onCreate");
        startCounter++;
    }

    @Override
    protected void onStop() {
        super.onStop();
        powiedzCoWykonujesz("onStop");
        stopCounter++;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        powiedzCoWykonujesz("onDestroy");
        destroyCounter++;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        powiedzCoWykonujesz("onRestart");
        restartCounter++;
    }

    @Override
    protected void onResume() {
        super.onResume();
        powiedzCoWykonujesz("onResume");
        resumeCounter++;
    }

    @Override
    protected void onPause(){
        super.onPause();
        powiedzCoWykonujesz("onPause");
        pauseCounter++;
    }
}