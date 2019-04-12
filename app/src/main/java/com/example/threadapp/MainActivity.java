package com.example.threadapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    TextView exerciseText;
    TextView testView;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testView = findViewById(R.id.textView);
        exerciseText = findViewById(R.id.exerciseText);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                exerciseText.setText("Filed loaded " + msg.what);

            }
        };





    }

    public void onButtonClick(View view) {
        switch(view.getId()) {
            case R.id.loadButton:
                new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
                        for (int i = 0; i < 10; i++) {
                            loadFile();
                            handler.sendEmptyMessage(i + 1);
                        }
                    }
                }).start();

                break;
            case R.id.testButton:
                testView.setText("Test is done.");
                break;
            default:
                break;
        }
    }

    private void loadFile() {
        try {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redirect(View view)
    {
        Intent i = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(i);
    }

    public void btnClicker(View view) {

        Intent i = new Intent(MainActivity.this, ThirdActivity.class);
        startActivity(i);

    }
}
