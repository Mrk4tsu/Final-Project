package com.ndthang.quanlykhohang.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndthang.quanlykhohang.R;
import com.ndthang.quanlykhohang.entities.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private List<Product> productList;
    private IClickItemProduct clickUpdateProduct;
    public interface IClickItemProduct{
        void updateProduct(Product product);
        void deleteProduct(Product product);
    }
    public ProductAdapter(){

    }
    public ProductAdapter(IClickItemProduct clickUpdateProduct) {
        this.clickUpdateProduct = clickUpdateProduct;
    }

    public void setData(List<Product> list) {
        this.productList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product product = productList.get(position);
        if (product == null) {
            return;
        }
        holder.textViewProductName.setText(product.getName());
        holder.textViewProductQuantity.setText("Số lượng:" + product.getQuantity());
        holder.textViewProductPrice.setText("Giá:" + product.getPrice() + "đ");

        holder.buttonUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickUpdateProduct.updateProduct(product);
            }
        });

        holder.buttonDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUpdateProduct.deleteProduct(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView textViewProductName;
        TextView textViewProductQuantity;
        TextView textViewProductPrice;
        ImageButton buttonUpdateProduct;
        ImageButton buttonDeleteProduct;

        /**
         * Đây là một comment sử dụng cú pháp giống như Javadoc trong XML.
         * @param itemView Tham số ví dụ
         **/
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
            textViewProductQuantity = itemView.findViewById(R.id.textViewProductQuantity);
            buttonUpdateProduct = itemView.findViewById(R.id.buttonUpdateProduct);
            buttonDeleteProduct = itemView.findViewById(R.id.buttonDeleteProduct);
        }
    }
}
