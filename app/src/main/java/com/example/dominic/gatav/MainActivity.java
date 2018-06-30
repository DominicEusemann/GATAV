package com.example.dominic.gatav;

import android.app.Activity;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.IOException;



public class MainActivity extends Activity {

    /** Ausgangscode Bernd_2 Uebung
    private MediaPlayer mPlayerAahhh;
    private MediaPlayer mPlayerBooom;
    private MediaPlayer mPlayerMist;
    private ImageView bernd;
    private ImageView explosion;
    private ImageView haus;
    private Animation drop;
    private Animation scroll;
    private Animation explode;
    **/


    //Image Resources
    private ImageView background;
    private ImageView scrollViewLayer_0;
    private ImageView scrollViewLayer_1;
    private ImageView scrollViewLayer_2;
    private ImageView scrollViewLayer_3;
    private ImageView scrollViewLayer_4;

    //Animation Rexources
    private Animation scroll_layer_0;
    private Animation scroll_layer_1;
    private Animation scroll_layer_2;
    private Animation scroll_layer_3;
    private Animation scroll_layer_4;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    /** Ausgangscode Bernd_2 Uebung
        bernd = (ImageView) findViewById(R.id.berndView);
        drop = AnimationUtils.loadAnimation(this, R.anim.falldown);

        haus = (ImageView) findViewById(R.id.houseView);
        scroll = AnimationUtils.loadAnimation( this, R.anim.house);

        explosion = (ImageView) findViewById(R.id.explosionView);
        explode = AnimationUtils.loadAnimation(this, R.anim.explosion);

        // Sound
        mPlayerAahhh = MediaPlayer.create(this, R.raw.berndsaahhh);
        mPlayerAahhh.setLooping(false);

        mPlayerBooom = MediaPlayer.create(this, R.raw.explosion);
        mPlayerBooom.setLooping(false);

        mPlayerMist = MediaPlayer.create(this, R.raw.berndsagtmist);
        mPlayerMist.setLooping(false);
    **/
        //static background picture
        background = (ImageView) findViewById(R.id.backgroundView);

        //first slow scrolling background-layer
        scrollViewLayer_0 = (ImageView) findViewById(R.id.scrollViewLayer_0);
        scroll_layer_0 = AnimationUtils.loadAnimation(this, R.anim.scroll_layer_0);

    /** Ausgangscode Bernd_2 Uebung
        // Redo durch click auf Bernd
        bernd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bernd.setVisibility(android.view.View.INVISIBLE);

                mPlayerAahhh.stop();
                try {
                    mPlayerAahhh.prepare();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                mPlayerBooom.stop();
                try {
                    mPlayerBooom.prepare();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                mPlayerMist.stop();
                try {
                    mPlayerMist.prepare();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                startFalling();
            }
        } );

        // Und ab geht die Post
        startFalling();
     **/
        startScrollViewLayer_0();
     }

     /** Ausgangscode Bernd_2 Uebung
    private void startExplosion () {

        explode.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                bernd.setVisibility(android.view.View.VISIBLE);
                mPlayerMist.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationStart(Animation animation) {}
        });

        mPlayerBooom.start();
        explosion.startAnimation(explode);
    }

    private void startFalling () {

        drop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                startExplosion();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationStart(Animation animation) { startScrolling(); }
        });

        mPlayerAahhh.start();
        bernd.startAnimation(drop);
    }

    **/


    private void startScrollViewLayer_0() {

        scroll_layer_0.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        scrollViewLayer_0.startAnimation(scroll_layer_0);
    }
}
