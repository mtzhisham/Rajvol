package com.moataz.rajvr;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import org.rajawali3d.view.SurfaceView;
import org.rajawali3d.view.ISurface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    RjRenderer rjRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SurfaceView surface = new SurfaceView(this);
        surface.setFrameRate(30.0);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        surface.setOnTouchListener(this);
        // Add mSurface to your root view
        addContentView(surface, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT));

        rjRenderer= new RjRenderer(this);
        surface.setSurfaceRenderer(rjRenderer);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            // this needs to be defined on the renderer:
            rjRenderer.getObjectAt(event.getX(), event.getY());
        }
        return super.onTouchEvent(event);
    }



}
