package com.example.dominic.gatav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Process;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MenuActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        RelativeLayout relativeLayout;
        relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundResource(R.drawable.background);
        setContentView(relativeLayout);


        final Context context = this;
        int navbarHeight = context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("navigation_bar_height", "dimen", "android"));

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int breite = size.x + navbarHeight;
        final int höhe = size.y;
        final Button spielBeginnen= new Button(this);
        final Button spielBeenden= new Button(this);

        spielBeginnen.setText("Spiel beginnen");
        spielBeginnen.setTextSize(20);
        spielBeginnen.getBackground().setAlpha(0);
        RelativeLayout.LayoutParams paramsBeginnen = new RelativeLayout.LayoutParams(breite/4, höhe/4);
        paramsBeginnen.leftMargin = breite/8;
        paramsBeginnen.topMargin = höhe/3;
        spielBeginnen.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //spielBeenden.setClickable(false);
                //spielBeginnen.setClickable(false);
                spielBeginnen.setTextColor(Color.RED);
                Intent myIntent;
                myIntent = new Intent(context, Game.class);
                context.startActivity(myIntent);
            }
        });

        spielBeenden.setText("Spiel beenden");
        spielBeenden.setTextSize(20);
        spielBeenden.getBackground().setAlpha(0);
        RelativeLayout.LayoutParams paramsBeenden = new RelativeLayout.LayoutParams(breite/4, höhe/4);
        paramsBeenden.leftMargin = breite/8 * 5;
        paramsBeenden.topMargin = höhe/3;
        spielBeenden.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                spielBeenden.setTextColor(Color.RED);
                finish();
            }
        });
        this.addContentView(spielBeenden, paramsBeenden);
        this.addContentView(spielBeginnen, paramsBeginnen);
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
