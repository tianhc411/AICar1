package com.example.aicar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView a = findViewById(R.id.Y0);
//        SocketThread socketThread = new SocketThread();

//        try {
//            Socket socket = serverClient.getLink();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        a.setOnTouchListener(new View.OnTouchListener() {
            float lastRawX, lastRawY;
            float m1=280,m2=555;//摇杆起始点
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastRawX = event.getRawX();
                        lastRawY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx = (event.getRawX() - lastRawX-80);
                        float dy = (event.getRawY() - lastRawY-245);
                        float i=event.getRawX()+dx,j=event.getRawY()+dy;
                       if (Math.sqrt(Math.pow(i-280,2)+Math.pow(j-555,2))<=110) {
                           v.setX(i);
                           v.setY(j);
                           double number = Math.atan2((i- 280), (j - 555));
                           double angle = number*(180/Math.PI);
                           SocketThread socketThread = new SocketThread(angle);
                           socketThread.start();
                       }
                        lastRawX = event.getRawX();
                        lastRawY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setX(m1);
                        v.setY(m2);
                        break;
                }
                return true;

            }
        });
    }
}
