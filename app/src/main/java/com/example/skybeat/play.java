package com.example.skybeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.skybeat.R.anim.fadein;

public class play extends AppCompatActivity {
private TextView tv;
private ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        tv= (TextView) findViewById(R.id.tv);
        iv=  (ImageView) findViewById(R.id.iv);
        Animation myanim= AnimationUtils.loadAnimation(this, fadein);
        tv.startAnimation(myanim);
        iv.startAnimation(myanim);
        final Intent i= new Intent(this,MainActivity.class);
        Thread timer= new Thread(){
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                  startActivity(i);
                  finish();
                }
            }
        };

        timer.start();
    }
}
