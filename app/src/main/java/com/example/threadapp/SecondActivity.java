package com.example.threadapp;

import android.os.Handler;
import android.os.Message;
import android.print.PrinterId;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class SecondActivity extends AppCompatActivity
{
    private final int STATUS_NONE = 0;
    private final int STATUS_CONNECTING = 1;
    private final int STATUS_CONNECTED  = 2;

    private TextView textView;
    private Button btnClick;
    private ProgressBar proBar;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView)findViewById(R.id.tvTextView);
        btnClick = (Button)findViewById(R.id.btnClickMe);
        proBar = findViewById(R.id.progressBar);


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                switch (msg.what)
                {
                    case STATUS_NONE:
                    {
                        btnClick.setEnabled(true);
                        textView.setText("Not connected");
                        //proBar.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case STATUS_CONNECTING:
                    {
                        btnClick.setEnabled(false);
                        textView.setText("Connecting");
                        //proBar.setVisibility(View.VISIBLE);
                        break;
                    }
                    case STATUS_CONNECTED:
                    {
                        btnClick.setEnabled(true);
                        textView.setText("Connected");
                        //proBar.setVisibility(View.GONE);
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public void onStartThread(View view)
    {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

                try
                {
                    handler.sendEmptyMessage(STATUS_CONNECTING);
                    TimeUnit.SECONDS.sleep(2);

                    handler.sendEmptyMessage(STATUS_CONNECTED);
                    TimeUnit.SECONDS.sleep(2);

                    handler.sendEmptyMessage(STATUS_NONE);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        th.start();
    }
}
