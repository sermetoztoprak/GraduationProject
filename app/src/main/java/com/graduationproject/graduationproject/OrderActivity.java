package com.graduationproject.graduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.graduationproject.graduationproject.Interface.ItemClickListener;
import com.graduationproject.graduationproject.Model.Medicines;
import com.graduationproject.graduationproject.ViewHolder.MenuViewHolder;
import com.squareup.picasso.Picasso;

public class OrderActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference medicines;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Medicines,MenuViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        database = FirebaseDatabase.getInstance();
        medicines = database.getReference("Medicines");


        //Load medicines
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadMenu();

    }

    private void loadMenu() {

          adapter = new FirebaseRecyclerAdapter<Medicines, MenuViewHolder>(
                Medicines.class,
                R.layout.medicine_item,
                MenuViewHolder.class,
                medicines) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Medicines model, int position) {
                viewHolder.txtMedicineName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                final Medicines clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent medicaneList = new Intent(OrderActivity.this,MedicineDetailActivity.class);
                        medicaneList.putExtra("MedicineId",adapter.getRef(position).getKey());
                        startActivity(medicaneList);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
