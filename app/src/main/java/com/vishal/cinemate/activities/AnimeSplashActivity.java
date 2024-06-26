package com.vishal.cinemate.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.vishal.cinemate.R;

public class AnimeSplashActivity extends AppCompatActivity {
    private static final String TAG = "AnimeSplashActivity";
    LottieAnimationView splashview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_splash);

        splashview = findViewById(R.id.splash);

        // Load the Lottie animation and handle errors


        splashview.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(AnimeSplashActivity.this, MainMenuActivity.class);
                startActivity(intent);

                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
}
