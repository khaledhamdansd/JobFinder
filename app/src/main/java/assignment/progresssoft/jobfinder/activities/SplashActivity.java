package assignment.progresssoft.jobfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import assignment.progresssoft.jobfinder.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {


    @BindView(R.id.splash_logo)
     ImageView logo;
    @BindView(R.id.tvSplach)
    TextView welcome_txt;

    Animation anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        getSupportActionBar().hide();


        anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_txt);
        logo.setAnimation(anim);
        welcome_txt.setAnimation(anim);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),FilterActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);
    }
}
