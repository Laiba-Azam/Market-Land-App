package com.example.marketland;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class A3_Product_Adapter extends RecyclerView.Adapter<A3_Product_Adapter.ProductViewHolder>{
    Context context;
    List<A3_Product_Class> productsList;
    public A3_Product_Adapter(Context context, List<A3_Product_Class> productsList) {
        this.context = context;
        this.productsList = productsList;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.a3_product_recycler_view, parent, false);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.prodImage.setImageResource(productsList.get(position).getImageUrl());
        holder.prodName.setText(productsList.get(position).getProductName());
        holder.prodQty.setText(productsList.get(position).getProductQty());
        holder.prodPrice.setText(productsList.get(position).getProductPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,A3_Product_Screen.class);
                intent.putParcelableArrayListExtra("DATA", (ArrayList<? extends Parcelable>) productsList);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return productsList.size();
    }
    public static final class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView prodImage;
        TextView prodName, prodQty, prodPrice;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            prodImage = itemView.findViewById(R.id.prod_image);
            prodName = itemView.findViewById(R.id.prod_name);
            prodPrice = itemView.findViewById(R.id.prod_price);
            prodQty = itemView.findViewById(R.id.prod_qty);
        }
    }
}