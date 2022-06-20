package com.example.marketland;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class A1_Animations extends AppCompatActivity {
    SharedPreferences preferences; public static final String login_preferences = "Login_Preferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a1_animations);
        Animation anim1 = AnimationUtils.loadAnimation(A1_Animations.this, R.anim.animation1);
        Animation anim2 = AnimationUtils.loadAnimation(A1_Animations.this, R.anim.animation2);
        TextView text1 = findViewById(R.id.text1);text1.startAnimation(anim2);
        TextView text2 = findViewById(R.id.text2);text2.startAnimation(anim1);
        TextView text3 = findViewById(R.id.text3);text3.startAnimation(anim2);
        preferences = getSharedPreferences(login_preferences, Context.MODE_PRIVATE);
        new Handler().postDelayed(() -> {
            String Name = preferences.getString("NAME", "");
            String Pswd = preferences.getString("PSWD", "");
            if ((Name.equals("")) || (Pswd.equals(""))) {
                startActivity(new Intent(this, A2_Activity.class));
            }
            // debug
            else {
                // verify Name & Pswd from db, as what if user have deleted his account from some other device.
                startActivity(new Intent(this, A3_First_Screen.class));
            }
        }, 1800);
    }
}