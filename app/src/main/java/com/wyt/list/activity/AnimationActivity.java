package com.wyt.list.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.wyt.list.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends AppCompatActivity {

    private static final String TAG = "AnimationActivity";

    @BindView(R.id.start_translate)
    Button startTranslate;
    @BindView(R.id.arrow)
    ImageView arrow;
    @BindView(R.id.start_alpha)
    Button startAlpha;
    @BindView(R.id.start_rotate)
    Button startRotate;
    @BindView(R.id.start_scale)
    Button startScale;

    private Animation translateAnimation;
    private Animation alphaAnimation;
    private Animation rotateAnimation;
    private Animation scaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));

        translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
        translateAnimation.setAnimationListener(new myAnimationListener());

        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        alphaAnimation.setAnimationListener(new myAnimationListener());

        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotateAnimation.setAnimationListener(new myAnimationListener());

        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
        scaleAnimation.setAnimationListener(new myAnimationListener());

    }

    @OnClick({R.id.start_translate, R.id.start_alpha, R.id.start_rotate, R.id.start_scale})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_translate:
                arrow.startAnimation(translateAnimation);
                break;
            case R.id.start_alpha:
                arrow.startAnimation(alphaAnimation);
                break;
            case R.id.start_rotate:
                arrow.startAnimation(rotateAnimation);
                break;
            case R.id.start_scale:
                arrow.startAnimation(scaleAnimation);
                break;
        }
    }

    private class myAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            Log.e(TAG, "onAnimationStart: onAnimationStart");
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Log.e(TAG, "onAnimationStart: onAnimationEnd");
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            Log.e(TAG, "onAnimationStart: onAnimationRepeat");
        }
    }
}
