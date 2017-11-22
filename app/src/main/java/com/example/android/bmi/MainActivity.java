package com.example.android.bmi;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
        playAnimation();
        playFrameAnimation();
    }

    private Button calcbutton;
    private EditText fieldheight,fieldweight;
    private TextView view_result,view_suggest,tipText2,tipText3;
    private LinearLayout description;
    private ImageView imageView2,img;


    private void findViews() {
        description = (LinearLayout)findViewById(R.id.description);
        calcbutton = (Button) findViewById(R.id.submit);
        fieldheight = (EditText) findViewById(R.id.height);
        fieldweight = (EditText) findViewById(R.id.weight);
        view_result = (TextView) findViewById(R.id.result);
        view_suggest = (TextView) findViewById(R.id.suggest);
        tipText2 = (TextView) findViewById(R.id.tipText2);
        tipText3 = (TextView) findViewById(R.id.tipText3);
        imageView2 = (ImageView) findViewById(R.id.imageView);
    }

    // Listen for button clicks
    private void setListeners() {
        calcbutton.setOnClickListener(calcBMI);
        description.setOnClickListener(descListener);
    }

    private Button.OnClickListener calcBMI = new Button.OnClickListener() {
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");

            double height = Double
                    .parseDouble(fieldheight.getText().toString()) / 100;
            double weight = Double
                    .parseDouble(fieldweight.getText().toString());
            double BMI = weight / (height * height);
            // Present result
            view_result.setText(getText(R.string.bmi_result) + nf.format(BMI));

            // Give health advice
            view_suggest = (TextView) findViewById(R.id.suggest);
            if (BMI > 25) {
                view_suggest.setText(R.string.advice_heavy);
            } else if (BMI < 20) {
                view_suggest.setText(R.string.advice_light);
            } else {
                view_suggest.setText(R.string.advice_average);
            }
        }
    };

    //建立說明畫面所使用的監聽器
    private View.OnClickListener descListener = new View.OnClickListener(){
        int clickCount = 1;
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if(clickCount ==1){
                tipText2.setVisibility(View.VISIBLE);
                clickCount++;
            }else if(clickCount ==2){
                tipText3.setVisibility(View.VISIBLE);
                clickCount++;
            }else
                description.setVisibility(View.GONE);
        }
    };
    void playAnimation() {

        img.setAnimation(R.drawable.a);

        ObjectAnimator oaForward = ObjectAnimator.ofFloat(img, "x", 0, 400);
        oaForward.setDuration(3000);
        oaForward.setRepeatCount(ObjectAnimator.INFINITE);
        oaForward.setRepeatMode(ObjectAnimator.REVERSE);

        ObjectAnimator oaRotate = ObjectAnimator.ofFloat(img, "rotation", 0, 360);
        oaRotate.setDuration(1500);
        oaRotate.setRepeatCount(5);
        oaRotate.setRepeatMode(ObjectAnimator.REVERSE);
        oaRotate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Toast.makeText(MainActivity.this,"End",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        AnimatorSet as = new AnimatorSet();
        as.playSequentially(oaRotate,oaForward);
        as.start();
    }
    void playFrameAnimation(){
        AnimationDrawable anim = (AnimationDrawable) imageView2.getBackground();
//
//                loadAnimation(this, R.anim.anim);
        anim.start();
//        imageView2.startAnimation(anim);
    }
}
