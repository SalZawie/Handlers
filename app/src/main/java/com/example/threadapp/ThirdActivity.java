package com.example.threadapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView textView;
    Handler handler;

    private final int STATUS_NONE = 0;
    private final int STATUS_CONNECTING = 1;
    private final int STATUS_CONNECTED  = 2;
    private final int STATUS_DOWNLOAD_START = 3;
    private final int STATUS_DOWNLOAD_FILE = 4;
    private final int STATUS_DOWNLOAD_STOP  = 5;
    private final int STATUS_DOWNLOAD_END = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        textView = findViewById(R.id.textView2);



        handler = new Handler(){


            @Override
            public void handleMessage(Message msg){

                switch(msg.what){

                    case STATUS_NONE:
                    {
                        break;
                    }
                    case STATUS_CONNECTING:
                    {
                        break;
                    }
                    case STATUS_CONNECTED:
                    {
                        break;
                    }
                    case STATUS_DOWNLOAD_START:
                    {
                        break;
                    }
                    case STATUS_DOWNLOAD_FILE:
                    {
                        break;
                    }
                    case STATUS_DOWNLOAD_STOP:
                    {
                        break;
                    }

                    case STATUS_DOWNLOAD_END:
                    {
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


        public void ClickThirdButton(View view)
        {




        }


}
