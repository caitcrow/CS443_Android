package com.example.hihi_.soundboard_cs443;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SoundBoardActivity extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList<SoundObject> soundList = new ArrayList<>();
    RecyclerView SoundView;
    SoundboardRecyclerAdapter SoundAdapter = new SoundboardRecyclerAdapter(soundList);
    RecyclerView.LayoutManager SoundLayoutManager;
    private View mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);
        mLayout = findViewById(R.id.activity_soundboard);
        toolbar = (Toolbar) findViewById(R.id.soundboard_toolbar);
        setSupportActionBar(toolbar);
        List<String> nameList = Arrays.asList(getResources().getStringArray(R.array.soundNames));
        SoundObject[] soundItems = {
                new SoundObject(nameList.get(0), R.raw.piano1),new SoundObject(nameList.get(1), R.raw.piano2), new SoundObject(nameList.get(2), R.raw.piano3), new SoundObject(nameList.get(3), R.raw.piano4), new SoundObject(nameList.get(4), R.raw.piano5),
                new SoundObject(nameList.get(5), R.raw.drum2), new SoundObject(nameList.get(6), R.raw.drum5), new SoundObject(nameList.get(7), R.raw.drum3), new SoundObject(nameList.get(8), R.raw.drum4), new SoundObject(nameList.get(9), R.raw.drum1),
                new SoundObject(nameList.get(10), R.raw.guitar1), new SoundObject(nameList.get(11), R.raw.guitar2), new SoundObject(nameList.get(12), R.raw.guitar8), new SoundObject(nameList.get(13), R.raw.guitar9), new SoundObject(nameList.get(14), R.raw.guitar7),
                new SoundObject(nameList.get(15), R.raw.beat1), new SoundObject(nameList.get(16), R.raw.beat2),new SoundObject(nameList.get(17), R.raw.jazz1),new SoundObject(nameList.get(18), R.raw.pianobeat), new SoundObject(nameList.get(19), R.raw.pianobeat2),
            };
        soundList.addAll(Arrays.asList(soundItems));
        SoundView = (RecyclerView) findViewById(R.id.soundboardRecyclerView);
        SoundLayoutManager = new GridLayoutManager(this, 5);
        SoundView.setLayoutManager(SoundLayoutManager);
        SoundView.setAdapter(SoundAdapter);
        requestPermissions();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventHandlerClass.releaseMediaPlayer();
    }
    private void requestPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
            if (!Settings.System.canWrite(this)){
                Snackbar.make(mLayout, "Soundboard needs access to settings", Snackbar.LENGTH_INDEFINITE).setAction("OK",
                        new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {

                                    Context context = view.getContext();
                                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                                    intent.setData(Uri.parse("package: " + context.getPackageName()));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                            }
                        }).show();
            }
        }
    }
}
