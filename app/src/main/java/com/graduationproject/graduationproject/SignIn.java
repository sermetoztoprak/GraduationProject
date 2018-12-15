package com.graduationproject.graduationproject;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.graduationproject.graduationproject.Model.User;

public class SignIn extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhone=findViewById(R.id.edtPhone);
        edtPassword=findViewById(R.id.edtPassword);
        btnSignIn=findViewById(R.id.btnSignIn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user =database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Lütfen Bekleyiniz...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()){

                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())){
                                Toast.makeText(SignIn.this, "Giriş Başarılı !", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(SignIn.this, "Şifre Yan !!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "Kullanıcı mevcut değil !!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
