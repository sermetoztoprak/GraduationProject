package com.graduationproject.graduationproject;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.graduationproject.graduationproject.Database.Database;
import com.graduationproject.graduationproject.Model.Medicines;
import com.graduationproject.graduationproject.Model.Order;
import com.squareup.picasso.Picasso;

public class MedicineDetailActivity extends AppCompatActivity {

    TextView medicane_name,medicine_price,medicine_description;
    ImageView medicane_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;

    ElegantNumberButton numberButton;

    String medicineId="";

    FirebaseDatabase database;
    DatabaseReference medicane;

    Medicines currentMedicine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);

        database=FirebaseDatabase.getInstance();
        medicane=database.getReference("Medicines");

        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        medicineId,
                        currentMedicine.getName(),
                        numberButton.getNumber(),
                        currentMedicine.getPrice(),
                        currentMedicine.getDiscount()
                ));

                Toast.makeText(MedicineDetailActivity.this, "Sepete Eklendi", Toast.LENGTH_SHORT).show();
            }
        });

        medicine_description = findViewById(R.id.medicane_description);
        medicane_name = findViewById(R.id.medicane_name);
        medicine_price = findViewById(R.id.medicane_price);
        medicane_image = findViewById(R.id.img_medicine);

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);


        if(getIntent() != null){
            medicineId = getIntent().getStringExtra("MedicineId");
            if (!medicineId.isEmpty()){
                getDetailFood(medicineId);
            }
        }

    }

    private void getDetailFood(String medicineId) {
        medicane.child(medicineId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentMedicine = dataSnapshot.getValue(Medicines.class);

                Picasso.with(getBaseContext()).load(currentMedicine.getImage())
                        .into(medicane_image);
                collapsingToolbarLayout.setTitle(currentMedicine.getName());

                medicine_price.setText(currentMedicine.getPrice());
                medicane_name.setText(currentMedicine.getName());
                medicine_description.setText(currentMedicine.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
