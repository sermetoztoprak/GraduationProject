package com.graduationproject.graduationproject.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.graduationproject.graduationproject.Interface.ItemClickListener;
import com.graduationproject.graduationproject.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMedicineName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);

        txtMedicineName = itemView.findViewById(R.id.menu_name);
        imageView = itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);


    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
