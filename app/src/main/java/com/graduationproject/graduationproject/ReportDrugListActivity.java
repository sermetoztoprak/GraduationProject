package com.graduationproject.graduationproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.graduationproject.graduationproject.Model.ReportDrug;
import com.graduationproject.graduationproject.ViewHolder.ReportDrugAdapter;

import java.util.ArrayList;

public class ReportDrugListActivity extends AppCompatActivity {

    Button addMButton;
    GridView gridView;
    ArrayList<ReportDrug> list;
    ReportDrugAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_drug_list);

        addMButton = findViewById(R.id.addMButton);
        gridView =  findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new ReportDrugAdapter(this, R.layout.report_items, list);
        gridView.setAdapter(adapter);


        // get all data from sqlite
        Cursor cursor = ReportDrugActivity.sqLiteHelper.getData("SELECT * FROM MEDICINE");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String Medicine = cursor.getString(1);
            String Clock = cursor.getString(2);
            String TimeArrival = cursor.getString(3);
            String Note = cursor.getString(4);

            list.add(new ReportDrug(id,Medicine,Clock, TimeArrival,Note));
        }
        adapter.notifyDataSetChanged();



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Güncelle", "Sil"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ReportDrugListActivity.this);

                dialog.setTitle("Seçim Yap");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = ReportDrugActivity.sqLiteHelper.getData("SELECT id FROM MEDICINE");
                            ArrayList<Integer> arrID = new ArrayList<>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(ReportDrugListActivity.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = ReportDrugActivity.sqLiteHelper.getData("SELECT id FROM MEDICINE");
                            ArrayList<Integer> arrID = new ArrayList<>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();

            }
        });



        addMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportDrugListActivity.this , ReportDrugActivity.class);
                startActivity(intent);
            }
        });

    }


    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_medicine_activity);
        dialog.setTitle("Güncelle");
        final EditText edtMedicine = dialog.findViewById(R.id.edtMedicine);
        final EditText edtClock = dialog.findViewById(R.id.edtClock);
        final EditText edtTimeArrival = dialog.findViewById(R.id.edtTimeArrival);
        final EditText edtNote =  dialog.findViewById(R.id.edtNote);

        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ReportDrugActivity.sqLiteHelper.updateData(
                            edtMedicine.getText().toString().trim(),
                            edtClock.getText().toString().trim(),
                            edtTimeArrival.getText().toString().trim(),
                            edtNote.getText().toString().trim(),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Güncelleme Yapıldı !", Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Güncelleme Hata", error.getMessage());
                }
                updateFoodList();
            }
        });
    }

    private void showDialogDelete(final int idFood){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ReportDrugListActivity.this);

        dialogDelete.setTitle("Dikkat!!");
        dialogDelete.setMessage("Silmek istiyor musunuz ?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    ReportDrugActivity.sqLiteHelper.deleteData(idFood);
                    Toast.makeText(getApplicationContext(), "Silindi !", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateFoodList();
            }
        });

        dialogDelete.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateFoodList(){
        // get all data from sqlite
        Cursor cursor = ReportDrugActivity.sqLiteHelper.getData("SELECT * FROM MEDICINE");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String Medicine = cursor.getString(1);
            String Clock = cursor.getString(2);
            String TimeArrival = cursor.getString(3);
            String Note = cursor.getString(4);

            list.add(new ReportDrug(id,Medicine, Clock, TimeArrival,Note));
        }
        adapter.notifyDataSetChanged();
    }



}