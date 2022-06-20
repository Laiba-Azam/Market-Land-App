package com.example.marketland;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class A2_Admin extends AppCompatActivity {
    float v=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a2_admin);
        EditText Admin_name = findViewById(R.id.Admin_Name);
        EditText Admin_id   = findViewById(R.id.Admin_ID);
        EditText Admin_pswd = findViewById(R.id.Admin_Pswd);
        Button Admin_button = findViewById(R.id.Admin_Login);
        Admin_name.setTranslationY(300); Admin_name.setAlpha(v);
        Admin_id.setTranslationY(300); Admin_id.setAlpha(v);
        Admin_pswd.setTranslationY(300); Admin_pswd.setAlpha(v);
        Admin_button.setTranslationY(300); Admin_button.setAlpha(v);
        Admin_name.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400);
        Admin_id.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400);
        Admin_pswd.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400);
        Admin_button.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400);
        Admin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = Admin_name.getText().toString().trim();
                String ID   = Admin_id.getText().toString().trim();
                String Pswd = Admin_pswd.getText().toString().trim();
                if (TextUtils.isEmpty(Name)){
                    Admin_name.setError("Enter Name");
                }
                if (TextUtils.isEmpty(ID)){
                    Admin_id.setError("Enter ID");
                }
                if (TextUtils.isEmpty(Pswd)){
                    Admin_pswd.setError("Enter Password");
                }
                // it will have multiple admins, with different intents to switch to different activities.
                else {
                    Toast.makeText(A2_Admin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(A2_Admin.this, A4_Admin.class);
                    startActivity(intent);
                }
            }
        });
    }
}