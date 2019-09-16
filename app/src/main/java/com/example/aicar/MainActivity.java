package com.example.aicar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView a = findViewById(R.id.Y0);
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
//                        int dx = (int) (event.getRawX() - lastRawX);//相对坐标
//                        int dy = (int) (event.getRawY() - lastRawY);//相对坐标
//                        a.layout(a.getLeft() + dx, a.getTop() + dy, a.getRight() + dx, a.getBottom() + dy);//设置位置
                        float dx = (event.getRawX() - lastRawX-80);
                        float dy = (event.getRawY() - lastRawY-245);
                        float i=event.getRawX()+dx,j=event.getRawY()+dy;
                       if (Math.sqrt(Math.pow(i-280,2)+Math.pow(j-555,2))<=120) {
                           v.setX(i);
                           v.setY(j);
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
