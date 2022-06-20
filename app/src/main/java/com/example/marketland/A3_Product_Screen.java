package com.example.marketland;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class A3_Product_Screen extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    TextView number,name, size_prize;ImageView plus,minus, image;
    Intent intent;Button cart;Boolean CartIsEmpty = Boolean.TRUE;
    SharedPreferences preferences; public static final String login_preferences = "Login_Preferences";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a3_product);

        preferences = getSharedPreferences(login_preferences, Context.MODE_PRIVATE);
        intent = getIntent();
        List<A3_Product_Class> product = intent.getParcelableArrayListExtra("DATA");
        int pos = intent.getIntExtra("position",0);

        number = findViewById(R.id.quantity);
        cart = findViewById(R.id.add_to_cart);
        name = findViewById(R.id.p_name);
        name.setText(product.get(pos).productName);
        size_prize = findViewById(R.id.P_Size_Prize);
        size_prize.setText(product.get(pos).productQty +"                                     "+product.get(pos).productPrice);
        image = findViewById(R.id.P_Image);
        image.setImageResource(product.get(pos).getImageUrl());

        minus = findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = parseInt(number.getText().toString());
                if (quantity == 0){
                }
                else {
                    quantity -= 1;
                    number.setText(String.valueOf(quantity));
                }
            }
        });
        plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = parseInt(number.getText().toString());
                quantity += 1;
                number.setText(String.valueOf(quantity));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = parseInt(number.getText().toString());
                CartIsEmpty = Boolean.FALSE;
                // cart data will be saved in db. Name, Quantity, Prize, Total, Everything
                Toast.makeText(getApplicationContext(),product.get(pos).productName + " Added to Cart",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void onBackPressed() {
        // nothing to do.
    }
    public void Pop(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu);
        popupMenu.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Locations:
                Toast.makeText(this, "Opening Our Locations!!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Help:
                Toast.makeText(this, "Help!!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Feedback:
                Toast.makeText(this, "Feedback!!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Delete:
                Toast.makeText(this, "Account Deleted!!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Update:
                Toast.makeText(this, "Change your credentials!!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Contact_Us:
                Toast.makeText(this, "Contact Us!!", Toast.LENGTH_SHORT).show();
//                Intent intent2 = new Intent(this, Pop_Contact.class);
//                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);startActivity(intent2);
                return true;
            case R.id.Policy:
                Toast.makeText(this, "Policies!!", Toast.LENGTH_SHORT).show();
//                Intent intent3 = new Intent(this, Pop_Policies.class);
//                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);startActivity(intent3);
                return true;
            case R.id.Logout:
                preferences = getSharedPreferences(login_preferences, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("NAME", "");editor.putString("PSWD", "");editor.apply();
                Toast.makeText(this, "Logging Out!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, A1_Animations.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void back(View view) {
        Intent intent = new Intent(this,A3_First_Screen.class);
        intent.putExtra("CARTISEMPTY",CartIsEmpty);
        startActivity(intent);
    }
}