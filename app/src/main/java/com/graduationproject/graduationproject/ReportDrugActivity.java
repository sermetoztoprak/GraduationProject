package com.graduationproject.graduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.graduationproject.graduationproject.Database.SQLiteHelper;

public class ReportDrugActivity extends AppCompatActivity {

    EditText edtMedicine, edtClock , edtTimeArrival , edtNote;
    Button btnAddMedicine, btnList;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_drug);

        init();

        sqLiteHelper = new SQLiteHelper(this, "medicineDB.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS MEDICINE(Id INTEGER PRIMARY KEY AUTOINCREMENT, Medicine VARCHAR, Clock VARCHAR, TimeArrival VARCHAR , Note VARCHAR)");


        btnAddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertData(
                            edtMedicine.getText().toString().trim(),
                            edtClock.getText().toString().trim(),
                            edtTimeArrival.getText().toString().trim(),
                            edtNote.getText().toString().trim()
                    );
                    Toast.makeText(getApplicationContext(), "Başarıyla Eklendi!", Toast.LENGTH_SHORT).show();
                    edtMedicine.setText("");
                    edtClock.setText("");
                    edtTimeArrival.setText("");
                    edtNote.setText("");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportDrugActivity.this, ReportDrugListActivity.class);
                startActivity(intent);
            }
        });
    }



    private void init(){
        edtMedicine =findViewById(R.id.edtMedicine);
        edtClock = findViewById(R.id.edtClock);
        edtTimeArrival =findViewById(R.id.edtTimeArrival);
        edtNote = findViewById(R.id.edtNote);
        btnAddMedicine = findViewById(R.id.btnAddMedicine);
        btnList = findViewById(R.id.btnIList);
    }


}
