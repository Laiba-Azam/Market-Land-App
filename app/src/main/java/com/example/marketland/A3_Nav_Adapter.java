package com.example.marketland;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class A3_Nav_Adapter extends RecyclerView.Adapter<A3_Nav_Adapter.ProductViewHolder>{
    Context context;List<A3_Nav_Class> CategoryList;
    public A3_Nav_Adapter(Context context, List<A3_Nav_Class> productCategoryList) {
        this.context = context;
        this.CategoryList = productCategoryList;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.a3_nav_bar, parent, false);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.category_Name.setText(CategoryList.get(position).getProductName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"NotifyDataSetChanged", "WrongConstant"})
            @Override
            public void onClick(View view) {
                String Type = CategoryList.get(holder.getAdapterPosition()).getProductName();
                Intent intent = new Intent(view.getContext(),A3_First_Screen.class);
                intent.putExtra("Type",Type);
                int FLAG_ACTIVITY_NO_ANIMATION = 65536;
                intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return CategoryList.size();
    }
    public static final class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView category_Name;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            category_Name = itemView.findViewById(R.id.nav_bar);
        }
    }
}