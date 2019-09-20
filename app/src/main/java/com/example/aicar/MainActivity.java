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
        a.setOnTouchListener(new View.OnTouchListener() {
            float lastRawX, lastRawY;
            float m1=280,m2=555;//摇杆起始点
            byte p1 = '0' ,p2 = '0';
            Client c= new Client(); //新建客户端对象
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastRawX = event.getRawX();
                        lastRawY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx = (event.getRawX() - lastRawX-80);
                        float dy = (event.getRawY() - lastRawY-245);
                        float i=event.getRawX()+dx,j=event.getRawY()+dy;
//                       byte distance =(byte)Math.sqrt(Math.pow(i-280,2)+Math.pow(j-555,2));
                       if (Math.sqrt(Math.pow(i-280,2)+Math.pow(j-555,2))<=110) {
                           v.setX(i);
                           v.setY(j);
//                           byte cosAngle = (byte) ((i-280)/distance);
//                           byte rad = (byte) Math.acos(cosAngle);
//                           if (j < 555) {
//                               rad =(byte) -rad;
//                           }
                           if (i>280)
                               p2='2';
                           else if (i<280)
                               p2='1';
                           else p2='0';
                           p1='1';
                           new Thread(new Runnable() {   //新的线程来执行客户端
                               @Override
                               public void run() {    //运行run方法
                                   c.send(p1, p2);   //调用send方法
                                   p1=p2=0;
                               }
                           }).start();
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
