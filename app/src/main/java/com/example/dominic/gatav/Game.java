package com.example.dominic.gatav;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;

public class Game extends Activity {

    MediaPlayer jumpSound;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Flags für Fullscreen setzen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GamePanel(this));

        MediaPlayer loopingBackgroundMusic = MediaPlayer.create(this, R.raw.thechinesefarmerisrunning);
        loopingBackgroundMusic.setLooping(true);
        loopingBackgroundMusic.stop();

        try {
            loopingBackgroundMusic.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            //System.out.print("\n Well I don't know what's wrong... but it's an IllegalStateException and you're to blame!");
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.print("\n Well I don't know what's wrong... but it's an IOException and you're to blame!");
        }
        loopingBackgroundMusic.start();
    }


    public void PlayJumpSound()
    {
        jumpSound = MediaPlayer.create(this,R.raw.berndsagtmist);
        jumpSound.stop();
        try {
            jumpSound.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jumpSound.start();
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