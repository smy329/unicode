package com.unicode.app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.unicode.app.R;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 3000;

    Animation topAnimation, bottomAnimation;
    ImageView unicodeLogo;
    TextView unicodeName,unicodeSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getting rid of statusbar on Splash Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        unicodeLogo = (ImageView) findViewById(R.id.unicodeLogoImageView);
        unicodeName = (TextView) findViewById(R.id.unicodeNameTextView);
        unicodeSlogan = (TextView) findViewById(R.id.unicodeSloganTextView);

        unicodeLogo.setAnimation(topAnimation);
        unicodeName.setAnimation(bottomAnimation);
        unicodeSlogan.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String> (unicodeLogo, "unicodeLogo");
                pairs[1] = new Pair<View, String> (unicodeName, "UnicodeText");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);

                startActivity(intent, options.toBundle());
                finish();

            }
        }, SPLASH_SCREEN);
    }
}