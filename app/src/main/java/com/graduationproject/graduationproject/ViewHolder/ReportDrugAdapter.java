package com.graduationproject.graduationproject.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.graduationproject.graduationproject.Model.ReportDrug;
import com.graduationproject.graduationproject.R;

import java.util.ArrayList;

public class ReportDrugAdapter extends BaseAdapter{

    private Context context;
    private  int layout;
    private ArrayList<ReportDrug> tarimList;

    public ReportDrugAdapter(Context context, int layout, ArrayList<ReportDrug> tarimList) {
        this.context = context;
        this.layout = layout;
        this.tarimList = tarimList;
    }

    @Override
    public int getCount() {
        return tarimList.size();
    }

    @Override
    public Object getItem(int position) {
        return tarimList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtMedicine, txtClock,txtTimeArrival,txtNote;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtMedicine =  row.findViewById(R.id.txtMedicine);
            holder.txtClock =  row.findViewById(R.id.txtClock);
            holder.txtTimeArrival = row.findViewById(R.id.txtTimeArrival);
            holder.txtNote =  row.findViewById(R.id.txtNote);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        ReportDrug food = tarimList.get(position);

        holder.txtMedicine.setText(food.getMedicine());
        holder.txtClock.setText(food.getClock());
        holder.txtTimeArrival.setText(food.getTimeArrival());
        holder.txtNote.setText(food.getNote());

        return row;
    }
}
