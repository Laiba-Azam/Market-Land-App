package com.example.marketland;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.stream.Stream;

public class A3_Product_Class extends ArrayList<Parcelable> implements Parcelable {
    Integer product_Id;
    String productName;
    String productQty;
    String productPrice;
    Integer imageUrl;
    String product_Type;
    public A3_Product_Class(Integer productid, String productName, String productQty, String productPrice, Integer imageUrl, String Product__type) {
        this.product_Id = productid;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.imageUrl = imageUrl;
        this.product_Type = Product__type;
    }

    protected A3_Product_Class(Parcel in) {
        if (in.readByte() == 0) {
            product_Id = null;
        } else {
            product_Id = in.readInt();
        }
        productName = in.readString();
        productQty = in.readString();
        productPrice = in.readString();
        if (in.readByte() == 0) {
            imageUrl = null;
        } else {
            imageUrl = in.readInt();
        }
        product_Type = in.readString();
    }

    public static final Creator<A3_Product_Class> CREATOR = new Creator<A3_Product_Class>() {
        @Override
        public A3_Product_Class createFromParcel(Parcel in) {
            return new A3_Product_Class(in);
        }

        @Override
        public A3_Product_Class[] newArray(int size) {
            return new A3_Product_Class[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProductid(Integer productid) { this.product_Id = productid; }

    public Integer getProductid() {
        return product_Id;
    }

    public void setProductType(String productType) { this.product_Type = productType; }

    public String getProductType() { return product_Type; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (product_Id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(product_Id);
        }
        dest.writeString(productName);
        dest.writeString(productQty);
        dest.writeString(productPrice);
        if (imageUrl == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(imageUrl);
        }
        dest.writeString(product_Type);
    }

    @NonNull
    @Override
    public Stream<Parcelable> stream() {
        return null;
    }
}