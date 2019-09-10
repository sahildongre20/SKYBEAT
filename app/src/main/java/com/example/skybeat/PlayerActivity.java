package com.example.skybeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {
    Button btn_next,btn_previous,btn_pause;
    TextView songLabel;
    SeekBar songSeekBar;

    static MediaPlayer myMediaPlayer;
    int position;

    ArrayList<File> mySong;
    String sname;
    Thread updateseekbar;



    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btn_next= (Button)findViewById(R.id.next);
        btn_previous=(Button)findViewById(R.id.previous);
        btn_pause=(Button) findViewById(R.id.pause);



        songLabel=(TextView)findViewById(R.id.label);


        songSeekBar=(SeekBar)findViewById(R.id.seekbar);



        updateseekbar=new Thread(){

            @Override
            public void run(){


                int totalduration= myMediaPlayer.getDuration();
                int currentposition= 0;

                while (currentposition<totalduration){

                    try{
                        sleep(500);
                        currentposition=myMediaPlayer.getCurrentPosition();
                        songSeekBar.setProgress(currentposition);

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }



            }

        };


        if(myMediaPlayer!=null){
            myMediaPlayer.stop();
            myMediaPlayer.release();
        }

        Intent i =getIntent();
        Bundle bundle= i.getExtras();

        mySong= (ArrayList) bundle.getParcelableArrayList("songs");
        sname=mySong.get(position).getName().toString();
        String songName= i.getStringExtra("songname");

        songLabel.setText(songName);
        songLabel.setSelected(true);


        position= bundle.getInt("pos",0);
        Uri u= Uri.parse(mySong.get(position).toString());

        myMediaPlayer= MediaPlayer.create(getApplicationContext(),u);
        myMediaPlayer.start();
        songSeekBar.setMax(myMediaPlayer.getDuration());

        updateseekbar.start();

        songSeekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);
        songSeekBar.getThumb().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);

        songSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            myMediaPlayer.seekTo(seekBar.getProgress());
            }
        });


        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songSeekBar.setMax(myMediaPlayer.getDuration());

                if (myMediaPlayer.isPlaying()){
                    btn_pause.setBackgroundResource(R.drawable.icon_play);
                    myMediaPlayer.pause();
                }
                else{
                    btn_pause.setBackgroundResource(R.drawable.icon_pause);
                    myMediaPlayer.start();
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMediaPlayer.stop();
                myMediaPlayer.release();
                position=((position+1)%mySong.size());
                Uri u= Uri.parse(mySong.get(position).toString());
                myMediaPlayer= MediaPlayer.create(getApplicationContext(),u);
                sname= mySong.get(position).getName().toString();
                songLabel.setText(sname);
                myMediaPlayer.start();
            }
        });


        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myMediaPlayer.stop();
                myMediaPlayer.release();
                position=((position-1)<0)?(mySong.size()-1):(position-1);
                Uri u= Uri.parse(mySong.get(position).toString());
                myMediaPlayer= MediaPlayer.create(getApplicationContext(),u);
                sname= mySong.get(position).getName().toString();
                songLabel.setText(sname);
                myMediaPlayer.start();


            }
        });



    }
}
