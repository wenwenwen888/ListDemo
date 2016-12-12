package com.wyt.list.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @BindView(R.id.start_translate)
    Button startTranslate;
    @BindView(R.id.arrow)
    ImageView arrow;

    private Animation myanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));

        myanimation = AnimationUtils.loadAnimation(this, R.anim.translate);
        myanimation.setAnimationListener(new myAnimationListener());

    }

    @OnClick(R.id.start_translate)
    public void onClick() {
        arrow.startAnimation(myanimation);
    }

    private class myAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            arrow.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
