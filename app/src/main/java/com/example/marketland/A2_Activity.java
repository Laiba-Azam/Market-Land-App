package com.example.marketland;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class A2_Activity extends AppCompatActivity {
    TabLayout tabLayout; ViewPager viewPager; float v;{ v = 0; }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.a2_activity);
        FloatingActionButton Action_Button1, Action_Button2, Action_Button3;
        Action_Button1 = findViewById(R.id.floatingActionButton1);
        Action_Button2 = findViewById(R.id.floatingActionButton2);
        Action_Button3 = findViewById(R.id.floatingActionButton3);
        Action_Button1.setTranslationY(300);
        Action_Button2.setTranslationY(300);
        Action_Button3.setTranslationY(300);
        Action_Button1.setAlpha(v);
        Action_Button2.setAlpha(v);
        Action_Button3.setAlpha(v);
        Action_Button1.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400);
        Action_Button2.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400);
        Action_Button3.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("LOGIN"));
        tabLayout.addTab(tabLayout.newTab().setText("SIGNUP"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTranslationY(300);
        tabLayout.setAlpha(v);
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400);
        viewPager=findViewById(R.id.view_pager);
        final A2_Adapter adapter = new A2_Adapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
    public void Admin(View view) {
        Toast.makeText(A2_Activity.this, "Switching to Admin Interface", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, A2_Admin.class));
    }
    public void Forgotten_Pswd(View view){
        startActivity(new Intent(this, A2_Forgotten_Password.class));
    }
}