package com.example.adriamartinez.finalproject;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

public class Memory extends AppCompatActivity {
    
    CoolImageFlipper flipper;
    Drawable ic;
    Drawable other;
    boolean is_ic = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        
        flipper = new CoolImageFlipper(this);
        ic = getResources().getDrawable(R.drawable.ic_wb_sunny_black_24dp);
        other = getResources().getDrawable(R.drawable.ic_audiotrack_black_24dp);
    }
    
    public void clicked(View view){
        if(is_ic){
            flipper.flipImage(other, ((ImageView) view));
            
        }else{
            flipper.flipImage(ic, ((ImageView) view));

        }
        is_ic = !is_ic;
    }
}
