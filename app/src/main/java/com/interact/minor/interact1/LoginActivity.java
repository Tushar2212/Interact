package com.interact.minor.interact1;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView interact , subHead ,subText1 , subtext2;
    ImageView imgStudent , imgTeacher ,backStudent, backTeacher;
    EditText enroll ,techaerUser;
    Button loginBtn;
   // ProgressBar progressBar;
   boolean imgStuZoom = false;
    boolean imgTeaZoom = false;
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        layout = findViewById(R.id.login);
        interact = findViewById(R.id.interact);
        subHead =findViewById(R.id.subHead);
        loginBtn =findViewById(R.id.btnLogin);
        loginBtn.setAlpha(0f);
        backStudent =findViewById(R.id.backStudent);
        backStudent.setTranslationX(1200f);
        backTeacher=findViewById(R.id.backTeacher);
        backTeacher.setTranslationX(-1200f);
        enroll = findViewById(R.id.enroll);
        enroll.setAlpha(0f);
        techaerUser = findViewById(R.id.teacherId);
        techaerUser.setAlpha(0f);
        Typeface fontCustom = Typeface.createFromAsset(getAssets() , "fonts/newfont.ttf");
        interact.setTypeface(fontCustom);
        subHead.setTypeface(fontCustom);
      /*  subText1.setTypeface(fontCustom);
        subtext2.setTypeface(fontCustom);*/
        imgStudent =findViewById(R.id.ivStudent);
        imgTeacher = findViewById(R.id.ivTeacher);
        imgTeacher.setAlpha(0f);
        imgStudent.setAlpha(0f);
        subHead.setAlpha(0f);
    /*    subText1.setAlpha(0f);
        subtext2.setAlpha(0f);*/
   //     progressBar =findViewById(R.id.progressBar);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        interact.animate().translationYBy(-500f).setDuration(1000);
                      //  progressBar.animate().translationYBy(-300f).alpha(0f).setDuration(1000);
                        subHead.animate().alpha(1f).setDuration(2000);
                        imgStudent.animate().alpha(1f).translationZBy(100f).translationYBy(-100f).setDuration(2000);
                        imgTeacher.animate().alpha(1f).translationZBy(100f).translationYBy(-100f).setDuration(2000);
                      /*  subText1.animate().alpha(1f).setDuration(2000);
                        subtext2.animate().alpha(1f).setDuration(2000);*/
                    }
                },
                3000
        );
        imgStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backStudent.animate().translationXBy(-1200f).setDuration(2000);
                interact.animate().alpha(0f).setDuration(1000);
                subHead.animate().alpha(0f).setDuration(1000);
                imgTeacher.animate().alpha(0f).setDuration(1000);
                imgStudent.animate().translationYBy(-850f).translationXBy(275f).setDuration(2000);
                enroll.animate().alpha(1f).setDuration(2500);
                loginBtn.animate().alpha(1f).setDuration(2500);
            }
        });
        imgTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backTeacher.animate().translationXBy(1200f).setDuration(2000);
                interact.animate().alpha(0f).setDuration(1000);
                subHead.animate().alpha(0f).setDuration(1000);
                imgStudent.animate().alpha(0f).setDuration(1000);
                imgTeacher.animate().translationYBy(-850f).translationXBy(-275f).setDuration(2000);
                techaerUser.animate().alpha(1f).setDuration(2500);
                loginBtn.animate().alpha(1f).setDuration(2500);
            }
        });
    }
}
