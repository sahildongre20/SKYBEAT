package com.example.skybeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class list extends AppCompatActivity {


    ListView myListViewForSongs;
    String []items;




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);



    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Songs");

        myListViewForSongs= (ListView) findViewById(R.id.mysonglistview);
        runtimePermission();
    }

    public void  runtimePermission(){
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        display();

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                           token.continuePermissionRequest();
                    }
                }).check();

    }
    public ArrayList<File> findSong (File file){
        ArrayList<File> arrayList=new ArrayList<>();
        File[] files= file.listFiles();
        for (File singleFile: files){

            if (singleFile.isDirectory() && !singleFile.isHidden()){
                arrayList.addAll(findSong(singleFile));
            }
            else {
                if (singleFile.getName().endsWith(".mp3")||
                   singleFile.getName().endsWith(".wav")){
                     arrayList.add(singleFile);
                }
            }

        }
        return arrayList;
    }


    void display(){
        final ArrayList<File> mySong= findSong(Environment.getExternalStorageDirectory());
        items=new String[mySong.size()];
        for(int i=0;i<mySong.size();i++)
    {
        items[i] = mySong.get(i).getName().toString().replace("mp3", "").replace("wav", "");
    }
        ArrayAdapter<String> myAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,items);
        myListViewForSongs.setAdapter(myAdapter);

        myListViewForSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override



            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String songName= myListViewForSongs.getItemAtPosition(i).toString();

                startActivity(new Intent(getApplicationContext(),PlayerActivity.class).putExtra("songs",mySong).putExtra("songname",songName)
                .putExtra("pos",i));
            }
        });



    }
}
