package com.example.marketland;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

public class A3_First_Screen extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    RecyclerView NavRecycler, RecyclerProduct1, RecyclerProduct2;
    Button Cart;
    ImageView Image1, Image2;
    SharedPreferences preferences;
    AlertDialog dialog;
    public static final String login_preferences = "Login_Preferences";
    int Rate;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a3_first_screen);
        preferences = getSharedPreferences(login_preferences, Context.MODE_PRIVATE);
        String Name = preferences.getString("NAME", "-");
        Intent intent; intent = getIntent();
        Boolean cart = intent.getBooleanExtra("CARTISEMPTY",Boolean.TRUE);
        TextView User = findViewById(R.id.text_1);
        User.setText("Hello, " + Name + "!!");
        Cart = findViewById(R.id.cart);
        if (cart == Boolean.FALSE){ Cart.setText("         VIEW CART ITEMS      "); }
        else{ Cart.setEnabled(Boolean.FALSE); }
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cart == Boolean.FALSE){
                    Toast.makeText(getApplicationContext(), "NEW SCREEN, WHERE REMOVAL OF ITEMS CAN BE MADE POSSIBLE", Toast.LENGTH_LONG).show();
                }
            }
        });
        Image1 = findViewById(R.id.search_btn);
        Image2 = findViewById(R.id.menu_lines);
        registerForContextMenu(Image2);

        List<A3_Nav_Class> nav_catalog = new ArrayList<>();
        nav_catalog.add(new A3_Nav_Class(1, "Packages"));
        nav_catalog.add(new A3_Nav_Class(2, "Produce"));
        nav_catalog.add(new A3_Nav_Class(3, "Fruits"));
        nav_catalog.add(new A3_Nav_Class(4, "Vegetables"));
        nav_catalog.add(new A3_Nav_Class(5, "Toys"));
        nav_catalog.add(new A3_Nav_Class(6, "Cosmetics"));
        nav_catalog.add(new A3_Nav_Class(7, "Medicines"));
        setNav(nav_catalog);

        List<A3_Product_Class> Product_List = new ArrayList<>();
        Product_List.add(new A3_Product_Class(1, "Packages", "100 ml", "$ 17.00", R.drawable.pepsi,"Packages"));
        Product_List.add(new A3_Product_Class(2, "Produce", "350 ml", "$ 25.00", R.drawable.coke,"Produce"));
        Product_List.add(new A3_Product_Class(1, "Fruits", "250 ml", "$ 17.00", R.drawable.pepsi,"Fruits"));
        Product_List.add(new A3_Product_Class(2, "Vegetables", "350 ml", "$ 25.00", R.drawable.coke,"Vegetables"));
        Product_List.add(new A3_Product_Class(1, "Toys", "10kg", "$ 172.00", R.drawable.coke,"Toys"));
        Product_List.add(new A3_Product_Class(1, "Packages2", "100 ml", "$ 17.00", R.drawable.pepsi,"Packages"));
        Product_List.add(new A3_Product_Class(2, "Produce2", "350 ml", "$ 25.00", R.drawable.coke,"Produce"));
        Product_List.add(new A3_Product_Class(1, "Fruits2", "250 ml", "$ 17.00", R.drawable.pepsi,"Fruits"));
        Product_List.add(new A3_Product_Class(2, "Vegetables2", "350 ml", "$ 25.00", R.drawable.coke,"Vegetables"));
        Product_List.add(new A3_Product_Class(1, "Packages2", "10kg", "$ 172.00", R.drawable.coke,"Packages"));
        setRecycler1(Product_List);

        try{
            String Type = intent.getStringExtra("Type");
            List<A3_Product_Class> products_Select = new ArrayList<>();
            for (int counter = 0; counter < Product_List.size(); counter++) {
                if (Product_List.get(counter).getProductType().equals(Type)){
                    products_Select.add(Product_List.get(counter));
                }
            }
            Toast.makeText(getApplicationContext(), "Loading Products...", Toast.LENGTH_SHORT).show();
            setRecycler2(products_Select);
        }
        catch (Exception exception) {
            Toast.makeText(getApplicationContext(), String.valueOf(exception), Toast.LENGTH_SHORT).show();
        }
    }
    private void setNav(List<A3_Nav_Class> NavList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        NavRecycler = findViewById(R.id.nav_recycler);
        NavRecycler.setLayoutManager(layoutManager);
        NavRecycler.setAdapter(new A3_Nav_Adapter(this, NavList));
    }
    private void setRecycler1(List<A3_Product_Class> ProductList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        RecyclerProduct1 = findViewById(R.id.product_recycler);
        RecyclerProduct1.setLayoutManager(layoutManager);
        RecyclerProduct1.setAdapter(new A3_Product_Adapter(this, ProductList));
    }
    private void setRecycler2(List<A3_Product_Class> productsList2) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        RecyclerProduct2 = findViewById(R.id.product_recycler2);
        RecyclerProduct2.setLayoutManager(layoutManager);
        RecyclerProduct2.setAdapter(new A3_Product_Adapter(this, productsList2));
    }
    public void Search(View view) {
        Toast.makeText(this, "SEARCH", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Action Denied",Toast.LENGTH_LONG).show();
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
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
                View mView = getLayoutInflater().inflate(R.layout.a3_feedback, null);
                ImageView S1,S2,S3,S4,S5;
                Button Submit;
                S1 = mView.findViewById(R.id.star1);
                S2 = mView.findViewById(R.id.star2);
                S3 = mView.findViewById(R.id.star3);
                S4 = mView.findViewById(R.id.star4);
                S5 = mView.findViewById(R.id.star5);
                Submit = mView.findViewById(R.id.Submit_Feedback);
                S1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rate = 1;
                        Submit.setEnabled(Boolean.TRUE);
                        S1.setImageResource(R.drawable.ic_star_filled);
                        S2.setImageResource(R.drawable.ic_star_unfilled);
                        S3.setImageResource(R.drawable.ic_star_unfilled);
                        S4.setImageResource(R.drawable.ic_star_unfilled);
                        S5.setImageResource(R.drawable.ic_star_unfilled);
                    }
                });
                S2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rate = 2;
                        Submit.setEnabled(Boolean.TRUE);
                        S1.setImageResource(R.drawable.ic_star_filled);
                        S2.setImageResource(R.drawable.ic_star_filled);
                        S3.setImageResource(R.drawable.ic_star_unfilled);
                        S4.setImageResource(R.drawable.ic_star_unfilled);
                        S5.setImageResource(R.drawable.ic_star_unfilled);
                    }
                });
                S3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rate = 3;
                        Submit.setEnabled(Boolean.TRUE);
                        S1.setImageResource(R.drawable.ic_star_filled);
                        S2.setImageResource(R.drawable.ic_star_filled);
                        S3.setImageResource(R.drawable.ic_star_filled);
                        S4.setImageResource(R.drawable.ic_star_unfilled);
                        S5.setImageResource(R.drawable.ic_star_unfilled);

                    }
                });
                S4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rate = 4;
                        Submit.setEnabled(Boolean.TRUE);
                        S1.setImageResource(R.drawable.ic_star_filled);
                        S2.setImageResource(R.drawable.ic_star_filled);
                        S3.setImageResource(R.drawable.ic_star_filled);
                        S4.setImageResource(R.drawable.ic_star_filled);
                        S5.setImageResource(R.drawable.ic_star_unfilled);
                    }
                });
                S5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rate = 5;
                        Submit.setEnabled(Boolean.TRUE);
                        S1.setImageResource(R.drawable.ic_star_filled);
                        S2.setImageResource(R.drawable.ic_star_filled);
                        S3.setImageResource(R.drawable.ic_star_filled);
                        S4.setImageResource(R.drawable.ic_star_filled);
                        S5.setImageResource(R.drawable.ic_star_filled);
                    }
                });
                Submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),String.valueOf(Rate),Toast.LENGTH_LONG).show();

                    }
                });
                mBuilder.setView(mView);
                dialog = mBuilder.create();
                dialog.show();
                return true;
            case R.id.Delete:
                Toast.makeText(this, "Account Deleted!!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Update:
                Toast.makeText(this, "Change your credentials!!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Contact_Us:
                AlertDialog.Builder Builder = new AlertDialog.Builder(this);
                View View = getLayoutInflater().inflate(R.layout.a3_contact_us, null);
                Builder.setView(View);
                dialog = Builder.create();
                dialog.show();
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
}