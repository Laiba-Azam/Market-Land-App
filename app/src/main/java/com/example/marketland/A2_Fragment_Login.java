package com.example.marketland;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class A2_Fragment_Login extends Fragment {
    SharedPreferences Preference;public static final String Login_Preference = "Login_Preferences";
    float v=0;EditText LoginUser,LoginPswd;Button Login;TextView LoginFP;
    @SuppressLint("SetTextI18n")  @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.a2_fragment_login,container,false);
        LoginUser = root.findViewById(R.id.Login_UserName);
        LoginPswd = root.findViewById(R.id.Login_Password);
        Login = root.findViewById(R.id.Login);
        LoginFP = root.findViewById(R.id.Login_ForgetPswd);
        Preference = requireContext().getSharedPreferences(Login_Preference, Context.MODE_PRIVATE);
        LoginUser.setTranslationY(300); LoginUser.setAlpha(v);
        LoginPswd.setTranslationY(300); LoginPswd.setAlpha(v);
        Login.setTranslationY(300); Login.setAlpha(v);
        LoginFP.setTranslationY(300); LoginFP.setAlpha(v);
        LoginUser.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400);
        LoginPswd.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400);
        Login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400);
        LoginFP.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=LoginUser.getText().toString().trim();
                String password=LoginPswd.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    LoginUser.setError("Enter UserName");
                }
                if (TextUtils.isEmpty(password)){
                    LoginPswd.setError("Enter Password");
                }
                else {
                    // From db verify username and pswd.
                    SharedPreferences.Editor editor = Preference.edit();
                    editor.putString("NAME", username);
                    editor.putString("PSWD", password);
                    editor.apply();
                    Toast.makeText(requireContext().getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(requireContext().getApplicationContext(), A3_First_Screen.class));
                }
            }
        });
        return root;
    }
}