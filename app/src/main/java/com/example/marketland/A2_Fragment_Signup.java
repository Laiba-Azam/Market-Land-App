package com.example.marketland;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;

import java.time.LocalDate;

public class A2_Fragment_Signup extends Fragment {
    SharedPreferences Preference;public static final String Login_Preference = "Login_Preferences";
    String code;Button SignUp;
    EditText SignupFirst, SignUpLast, SignUpPass, SignUpEmail, SignUpContact;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.a2_fragment_signup, container, false);
        Preference = requireContext().getSharedPreferences(Login_Preference, Context.MODE_PRIVATE);
        SignupFirst = root.findViewById(R.id.SignUpFirstName);
        SignUpLast = root.findViewById(R.id.SignUpLastName);
        SignUpPass = root.findViewById(R.id.SignUpPass);
        SignUpEmail = root.findViewById(R.id.SignupEmailAddress);
        SignUpContact = root.findViewById(R.id.SignUpContact);
        SignUp = root.findViewById(R.id.SignUp);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String first = SignupFirst.getText().toString().trim();
                String last = SignUpLast.getText().toString().trim();
                String pass = SignUpPass.getText().toString().trim();
                String email = SignUpEmail.getText().toString().trim();
                String contact = SignUpContact.getText().toString().trim();
                if (TextUtils.isEmpty(first)) {
                    SignupFirst.setError("Enter First Name");
                } if (TextUtils.isEmpty(last)) {
                    SignUpLast.setError("Enter Last Name");
                } if (TextUtils.isEmpty(pass)) {
                    SignUpPass.setError("Enter Password");
                } if (TextUtils.isEmpty(email)) {
                    SignUpEmail.setError("Enter Email");
                } else if (!(email.contains("@gmail.com"))) {
                    SignUpEmail.setError("Improper Email Entered");
                } if (TextUtils.isEmpty(contact)) {
                    SignUpContact.setError("Enter Contact Number");
                } else if (!(contact.length() == 11)) {
                    SignUpContact.setError("Must be 11 digit");
                } else {
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
                    try {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(username));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse((email)));
                        message.setSubject("MARKET LAND APP - SIGNUP PROCESS");
                        message.setText("SIGN UP USING THE CODE : " + code + "\nThank You :)) \nDate: " + LocalDate.now());
                        Transport.send(message);
                        Toast.makeText(root.getContext(), "CODE IS EMAILED", Toast.LENGTH_LONG).show();
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(root.getContext());
                        View mView = getLayoutInflater().inflate(R.layout.a2_fragment_alert, null);
                        TextView text = mView.findViewById(R.id.SomeText);
                        text.setText("Code is emailed at "+email);
                        EditText edit_code = mView.findViewById(R.id.WriteCode);
                        Button submit = mView.findViewById(R.id.Submit);
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String code_entered = edit_code.getText().toString().trim();
                                if (TextUtils.isEmpty(code_entered)) {
                                    edit_code.setError("Write Code");
                                } else if (code_entered.equals(code)) {
                                    // Add User to Db.
                                    SharedPreferences.Editor editor = Preference.edit();
                                    editor.putString("NAME", first);
                                    editor.putString("PSWD", pass);
                                    editor.apply();
                                    Toast.makeText(requireContext().getApplicationContext(), "Successfully SignUp", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(requireContext().getApplicationContext(), A3_First_Screen.class);
                                    startActivity(intent);
                                } else {
                                    Snackbar snackbar = Snackbar.make(root.findViewById(android.R.id.content), "INCORRECT CODE", Snackbar.LENGTH_LONG);snackbar.show();
                                }
                            }
                        });
                        mBuilder.setView(mView);
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return root;
    }
}