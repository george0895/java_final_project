package com.example.user.afinal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tomoya on 4/5/17.
 */

public class Fragment2 extends Fragment implements View.OnClickListener{

    private Button startButton,start24;
    private Button stopButton,start14,setzero;
    private TextView text,hour,min,sec,sec24;
    private boolean flag_24=false;
    private int m=7,s=60,seccount=240;
    private boolean flagstrat=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment2, container, false);

        startButton = (Button) rootView.findViewById(R.id.startButton);
        stopButton = (Button) rootView.findViewById(R.id.stopButton);
        start24 = (Button)rootView.findViewById(R.id.start24);
        start14 = (Button)rootView.findViewById(R.id.start14);
        setzero = (Button)rootView.findViewById(R.id.button);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        start24.setOnClickListener(this);
        start14.setOnClickListener(this);
        setzero.setOnClickListener(this);
        min = (TextView)rootView.findViewById(R.id.textView3);
        sec = (TextView)rootView.findViewById(R.id.textView4);
        sec24 = (TextView)rootView.findViewById(R.id.textView5);



        Timer timer = new Timer();
        timer.schedule(tTask, 0, 1000);//每一秒執行一次，零秒後開始執行
        timer.schedule(task_24,0,100);

        return rootView;
    }

        public void onClick(View v) {
            //啟動服務
            switch (v.getId()){
                case R.id.startButton:
                    flagstrat=true;
                    break;
                case R.id.start24:
                    seccount=240;
                    break;
                case R.id.start14:
                    seccount=140;
                    break;
                case R.id.stopButton:
                    flagstrat=false;
                    break;
                case R.id.button:
                    flagstrat=false;
                    m=7;
                    s=59;
                    Message message = new Message();
                    message.what=1;
                    handler.sendMessage(message);
                    break;
            }
    };





    public TimerTask task_24 =new TimerTask() {
        @Override
        public void run() {
            Message message24 = new Message();
            if(seccount!=0) {
                seccount--;
                //sec24.setText(String.valueOf(seccount));

                message24.what=1;
            }
            else {
                seccount = 240;
            }
            handler2.sendMessage(message24);
        }
    };

    private Handler handler2=new Handler(){
        private int a,b;
        public void handleMessage(Message message24){
            super.handleMessage(message24);
            a=seccount/10;
            b=seccount%10;
            sec24.setText(String.valueOf(a+"."+b));
        }
    };

    public TimerTask tTask = new TimerTask(){
        public void run(){
            //每秒要執行的程式
            if(flagstrat==true){
                Message message = new Message();

                if(s!=0){
                    s--;
                    message.what=1;
                }
                else {
                    m--;
                    s=59;
                    message.what=1;
                    if(m==-1)
                    {
                        m=7;
                        s=59;
                        flagstrat=false;
                        message.what=3;
                    }

                }

                handler.sendMessage(message);

            }
        }

    };


    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                    if(s<10){
                        sec.setText("0"+String.valueOf(s));//秒數小於10補個零
                    }
                    else if(s==60){
                        sec.setText("00");
                    }
                    else{
                        sec.setText(String.valueOf(s));//用textview顯示秒數
                    }
                    if(m<10){
                        min.setText("0"+String.valueOf(m));//分數小於10補個零
                    }
                    else{
                        min.setText(String.valueOf(m));//用textview顯示分數
                    }
                    break;
                default:
            }

        }
    };
}

