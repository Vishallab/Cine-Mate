package com.vishal.cinemate.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.vishal.cinemate.R;

public class AnimeSplashActivity extends AppCompatActivity {
    LottieAnimationView splashview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_splash);

        splashview = findViewById(R.id.splash);


        splashview.animate().translationY(-3500).setDuration(2000).setStartDelay(4000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {
                //jb animation start ho rhi tb kuch krnah to
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                //ba jb animation edn ho jayegi to appnei ko kya krna jaise splash h to apne to intent pass krna h
                Intent intent = new Intent(AnimeSplashActivity.this, MainMenuActivity.class);
                startActivity(intent);

            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });

    }
}