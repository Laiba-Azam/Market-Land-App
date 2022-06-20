package com.example.marketland;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.util.Properties;
import java.util.Random;

public class A2_Forgotten_Password extends AppCompatActivity {
    SharedPreferences Preference;public static final String Login_Preference = "Login_Preferences";
    EditText WriteEmail; Button SendCode;
    EditText WriteCode;  Button SubmitCode;
    String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a2_forgotten_password);
        Preference = this.getSharedPreferences(Login_Preference, Context.MODE_PRIVATE);
        WriteEmail = findViewById(R.id.UserName);SendCode = findViewById(R.id.Login_Button_1);
        WriteCode = findViewById(R.id.Enter_Code);SubmitCode = findViewById(R.id.Login_Button_2);
        WriteCode.setEnabled(false);
        SubmitCode.setEnabled(false);
        SendCode.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                String email=WriteEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    WriteEmail.setError("Enter Email Address");
                }
                else{
                    final String username = "marketland.store@gmail.com";
                    final String password = "dbms_project";
                    Random rnd = new Random();
                    int number = rnd.nextInt(9999);
                    code = String.format("%04d", number);
                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");
                    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
                // Verify if there is any account with that email address or not.
                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse((WriteEmail.getText().toString())));
                    message.setSubject("MARKET LAND APP - LOGIN PROCESS");
                    message.setText("LOGIN USING THE CODE : "+code+"\nThank You :)) \nDate: "+ LocalDate.now());
                    Transport.send(message);
                    Toast.makeText(getApplicationContext(), "CODE IS EMAILED", Toast.LENGTH_LONG).show();
                    WriteCode.setEnabled(true);
                    SubmitCode.setEnabled(true);
                    SendCode.setEnabled(false);
                    WriteEmail.setEnabled(false);
                    SubmitCode.setOnClickListener(v1 -> {
                        String write=WriteCode.getText().toString().trim();
                        if (TextUtils.isEmpty(write)){
                            WriteCode.setError("Enter Code");
                        }
                        else {
                            String mycode = String.valueOf(WriteCode.getText());
                            if (mycode.equals(code)) {
                                SharedPreferences.Editor editor = Preference.edit();
                                // Fetch Username & Pswd from database to linked email.
                                // Show User His Name and Pswd.
                                editor.putString("NAME", "Fetch Name");
                                editor.putString("PSWD", "Pswd");
                                editor.apply();
                                Toast.makeText(getApplicationContext(),"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), A3_First_Screen.class));
                            } else {
                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "INCORRECT CODE", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        }
                    });
                }
                catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }}
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}