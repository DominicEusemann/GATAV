package com.example.dominic.gatav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.widget.TextViewCompat;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameOver extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        RelativeLayout relativeLayout;
        relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(0);
        setContentView(relativeLayout);


        final Context context = this;
        int navbarHeight = context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("navigation_bar_height", "dimen", "android"));

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int breite = size.x + navbarHeight;
        final int höhe = size.y;
        final Button retry= new Button(this);
        final Button end= new Button(this);
        final TextView game = new TextView(this);

        game.setAllCaps(true);
        game.setText("Game over!");
        game.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        game.setTextColor(Color.parseColor("#ffffff"));
        TextViewCompat.setAutoSizeTextTypeWithDefaults(game, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        RelativeLayout.LayoutParams paramsGame = new RelativeLayout.LayoutParams(breite/2, breite/6);
        paramsGame.leftMargin = breite/4;
        paramsGame.topMargin = höhe/20;

        retry.setBackground(getResources().getDrawable(R.drawable.endl));
        RelativeLayout.LayoutParams paramsRetry = new RelativeLayout.LayoutParams(breite/6, breite/6);
        paramsRetry.leftMargin = breite/6;
        paramsRetry.topMargin = höhe/2;
        retry.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                retry.setClickable(false);
                end.setClickable(false);
                Intent myIntent;
                myIntent = new Intent(context, Game.class);
                context.startActivity(myIntent);
            }
        });

        end.setBackground(getResources().getDrawable(R.drawable.endx));
        RelativeLayout.LayoutParams paramsEnd = new RelativeLayout.LayoutParams(breite/6, breite/6);
        paramsEnd.leftMargin = breite/6 * 4;
        paramsEnd.topMargin = höhe/2;
        end.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                finish();
            }
        });
        this.addContentView(game, paramsGame);
        this.addContentView(retry, paramsRetry);
        this.addContentView(end, paramsEnd);
    }

    @Override
    protected void onDestroy(){
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
}