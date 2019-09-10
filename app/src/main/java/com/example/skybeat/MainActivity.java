package com.example.skybeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.skybeat.R.anim.fadein;
import static com.example.skybeat.R.anim.fadeing;
import static com.example.skybeat.R.anim.fadeinga;
import static com.example.skybeat.R.anim.fadeinlogo;
import static com.example.skybeat.R.anim.slidemaintext;

public class MainActivity extends AppCompatActivity {
    private ImageView iva;
    private ImageView ivb;
    private TextView logoa;
    private TextView logo;
    private TextView slide;
    private TextView slidea;
    private TextView slideb;
    private TextView slidec;
    private Button btnslide;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        back =(Button) findViewById(R.id.back);
        logoa= (TextView) findViewById(R.id.logoa);
        logo= (TextView) findViewById(R.id.logo);
        Animation mytextanim= AnimationUtils.loadAnimation(this, fadeinlogo);
        back.startAnimation(mytextanim);
        logo.startAnimation(mytextanim);
        logoa.startAnimation(mytextanim);





        slide= (TextView) findViewById(R.id.slide);
        slidea= (TextView) findViewById(R.id.slidea);
        slideb= (TextView) findViewById(R.id.slideb);
        slidec= (TextView) findViewById(R.id.slidec);
        Animation slidetxt= AnimationUtils.loadAnimation(this, slidemaintext);
        slide.startAnimation(slidetxt);
        slidea.startAnimation(slidetxt);
        slideb.startAnimation(slidetxt);
        slidec.startAnimation(slidetxt);



         btnslide= (Button) findViewById(R.id.btnslide);
         Animation slidebtn= AnimationUtils.loadAnimation(this, R.anim.slidebtn);
         btnslide.startAnimation(slidebtn);





        ivb= (ImageView) findViewById(R.id.ivb);
        iva= (ImageView) findViewById(R.id.iva);
        Animation myanim= AnimationUtils.loadAnimation(this, fadeing);
        Animation myanimb= AnimationUtils.loadAnimation(this, fadeinga);
        iva.startAnimation(myanim);
        ivb.startAnimation(myanimb);

    }

    public void btn_list(View view) {
        startActivity(new Intent(getApplicationContext(),list.class));
    }

    public void btn_back(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);}
    }

