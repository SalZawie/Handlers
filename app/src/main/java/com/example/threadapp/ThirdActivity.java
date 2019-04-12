package com.example.threadapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThirdActivity extends AppCompatActivity {

    TextView textView;
    Handler handler;
    Button thirdButton;

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
        thirdButton = findViewById(R.id.button3);

        handler = new Handler(){


            @Override
            public void handleMessage(Message msg){

                switch(msg.what){

                    case STATUS_NONE:
                    {
                        thirdButton.setEnabled(true);
                        textView.setText("Not connected");
                        break;
                    }
                    case STATUS_CONNECTING:
                    {
                        thirdButton.setEnabled(false);
                        textView.setText("Connecting");
                        break;
                    }
                    case STATUS_CONNECTED:
                    {
                        thirdButton.setEnabled(true);
                        textView.setText("Connected");
                        break;
                    }
                    case STATUS_DOWNLOAD_START:
                    {
                        thirdButton.setEnabled(true);
                        textView.setText("Download started " + msg.arg1 + " files");

                        break;
                    }
                    case STATUS_DOWNLOAD_FILE:
                    {
                        thirdButton.setEnabled(true);
                        textView.setText("Downloading " + msg.arg1 + " files. " + "Currently dowloading: " + msg.arg2);
                        try{
                            saveFile(msg.obj);
                        }
                        catch (Exception e){
                            e.fillInStackTrace();
                        }
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
           new Thread(new Runnable() {
               @Override
               public void run() {
                   try{
                       byte[] file;
                       Message msg;

                       //TODO 1.Connecting and let the thread to sleep for 1 sec
                       handler.sendEmptyMessage(STATUS_CONNECTING);
                       TimeUnit.SECONDS.sleep(1);

                       //TODO 2.Change status to "connected"
                       handler.sendEmptyMessage(STATUS_CONNECTED);
                       TimeUnit.SECONDS.sleep(1);

                       //TODO 3. Determine the amount of files to download
                       Random random = new Random();
                       int countFiles = random.nextInt(6);

                       //TODO 4. If amount of files = 0, send status "none" to handler
                       if(countFiles == 0){
                           handler.sendEmptyMessage(STATUS_NONE);
                           TimeUnit.SECONDS.sleep(1);
                           return;
                       }
                       msg = handler.obtainMessage(STATUS_DOWNLOAD_START, countFiles,0);
                       handler.sendMessage(msg);

                       for(int i = 0; i < countFiles; i++){
                           file = downloadFile();
                           msg = handler.obtainMessage(STATUS_DOWNLOAD_FILE, countFiles, (i+1), file);
                           handler.sendMessage(msg);
                       }
                   }
                   catch (Exception e){

                   }
               }
           }).start();
        }

        private byte[] downloadFile() throws Exception{
            TimeUnit.SECONDS.sleep(2);
            return new byte[2000];
        }

        private void saveFile(Object file) throws Exception{
            TimeUnit.SECONDS.sleep(1);
        }
}
