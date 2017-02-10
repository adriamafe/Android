package com.example.adriamartinez.finalproject;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.*;

import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */


public class MemoryFragment extends Fragment implements View.OnClickListener{
    String discovered;
    String dist;
    SharedPreferences sp;
    Map ImageMapping;
    CoolImageFlipper flipper;
    Drawable basket;
    Drawable bowling;
    Drawable car;
    Drawable coffe;
    Drawable cookies;
    Drawable cow;
    Drawable football;
    Drawable vegetables;
    View rootview;
    public MemoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ARRAYLIST que conté les strings de les imatges

        //inicialitzem els drawables i el flipper
        rootview = inflater.inflate(R.layout.fragment_memory,container,false);

        flipper = new CoolImageFlipper(getActivity().getApplicationContext());

        basket = getResources().getDrawable(R.drawable.bowling);
        bowling = getResources().getDrawable(R.drawable.ic_wb_sunny_black_24dp);
        car = getResources().getDrawable(R.drawable.ic_backspace_black_24dp);
        coffe = getResources().getDrawable(R.drawable.ic_wb_sunny_black_24dp);
        cookies = getResources().getDrawable(R.drawable.ic_backspace_black_24dp);
        cow = getResources().getDrawable(R.drawable.ic_audiotrack_black_24dp);
        football = getResources().getDrawable(R.drawable.ic_backspace_black_24dp);
        vegetables = getResources().getDrawable(R.drawable.ic_audiotrack_black_24dp);
        ImageMapping = new HashMap<Integer, Drawable>();
        ImageMapping.put(0,basket);
        ImageMapping.put(1,bowling);
        ImageMapping.put(2,car);
        ImageMapping.put(3,coffe);
        ImageMapping.put(4,cookies);
        ImageMapping.put(5,cow);
        ImageMapping.put(6,football);
        ImageMapping.put(7,vegetables);

        //listeners per les views
        ImageView p0 = (ImageView) rootview.findViewById(R.id.p0);
        ImageView p1 = (ImageView) rootview.findViewById(R.id.p1);
        ImageView p2 = (ImageView) rootview.findViewById(R.id.p2);
        ImageView p3 = (ImageView) rootview.findViewById(R.id.p3);
        ImageView p4 = (ImageView) rootview.findViewById(R.id.p4);
        ImageView p5 = (ImageView) rootview.findViewById(R.id.p5);
        ImageView p6 = (ImageView) rootview.findViewById(R.id.p6);
        ImageView p7 = (ImageView) rootview.findViewById(R.id.p7);
        ImageView p8 = (ImageView) rootview.findViewById(R.id.p8);
        ImageView p9 = (ImageView) rootview.findViewById(R.id.p9);
        ImageView p10 = (ImageView) rootview.findViewById(R.id.p10);
        ImageView p11 = (ImageView) rootview.findViewById(R.id.p11);
        ImageView p12 = (ImageView) rootview.findViewById(R.id.p12);
        ImageView p13 = (ImageView) rootview.findViewById(R.id.p13);
        ImageView p14 = (ImageView) rootview.findViewById(R.id.p14);
        ImageView p15 = (ImageView) rootview.findViewById(R.id.p15);;

        p0.setOnClickListener(this);
        p1.setOnClickListener(this);
        p2.setOnClickListener(this);
        p3.setOnClickListener(this);
        p4.setOnClickListener(this);
        p5.setOnClickListener(this);
        p6.setOnClickListener(this);
        p7.setOnClickListener(this);
        p8.setOnClickListener(this);
        p9.setOnClickListener(this);
        p10.setOnClickListener(this);
        p11.setOnClickListener(this);
        p12.setOnClickListener(this);
        p13.setOnClickListener(this);
        p14.setOnClickListener(this);
        p15.setOnClickListener(this);

        //agafem les shared preferences
        sp = getActivity().getSharedPreferences("Memory",Context.MODE_PRIVATE);
        dist = sp.getString("info",getRandom());
        discovered = sp.getString("info2","ffffffffffffffff");
        Log.v("Memory",dist);
        return rootview;
    }
    //Generates a String with numbers between 0 and 7 assigned randomly
    private String getRandom(){
        String s;
        Random randomGenerator = new Random();
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i = 0; i < 8; ++i){
            tmp.add(0);
        }
        int i = 0;
        int first = randomGenerator.nextInt(8);
        tmp.set(first,1);
        s = String.valueOf(first);
        ++i;
        while(i < 16) {
            int randomint = randomGenerator.nextInt(8);
            if (tmp.get(randomint)<2) {
                Integer x = tmp.get(randomint);
                tmp.set(randomint,x+1);
                s = s + randomint;
                ++i;
            }
        }
        return s;
    }

    public void flip_image(int i, final View v){
        if(discovered.charAt(i) == 'f') {
            int x = dist.charAt(i) - '0';
            Log.v("Memory", dist);
            Log.v("Memory", "accedeixo a la posició " + i + "on hi ha " + x);
            Drawable tmp = (Drawable) ImageMapping.get(x);
            flipper.flipImage(tmp, (ImageView) v);
        }
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flipper.flipImage((Drawable) getResources().getDrawable(R.mipmap.ic_launcher), (ImageView) v);
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.p0:
                flip_image(0,view);
                break;
            case R.id.p1:
                flip_image(1,view);
                break;
            case R.id.p2:
                flip_image(2,view);
                break;
            case R.id.p3:
                flip_image(3,view);
                break;
            case R.id.p4:
                flip_image(4,view);
                break;
            case R.id.p5:
                flip_image(5,view);
                break;
            case R.id.p6:
                flip_image(6,view);
                break;
            case R.id.p7:
                flip_image(7,view);
                break;
            case R.id.p8:
                flip_image(8,view);
                break;
            case R.id.p9:
                flip_image(9,view);
                break;
            case R.id.p10:
                flip_image(10,view);
                break;
            case R.id.p11:
                flip_image(11,view);
                break;
            case R.id.p12:
                flip_image(12,view);
                break;
            case R.id.p13:
                flip_image(13,view);
                break;
            case R.id.p14:
                flip_image(14,view);
                break;
            case R.id.p15:
                flip_image(15,view);
                break;
        }

    }
}
