package com.example.sarpkaya.annoyer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SarpKaya on 25/03/2015.
 */
public class MedicationArrAdapter extends BaseAdapter implements ListAdapter {
    private List<MedicationModel> list;
    private Context context;



    public MedicationArrAdapter(Context context) {
        this.context = context;
        list = PatientMedications.getInstance().getMedicationModelList();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return list.get(pos).getSctid();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.medication_layout, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position).getName());

        TextView timeText = (TextView)view.findViewById(R.id.timeText);
        timeText.setText(list.get(position).getMedicationTimeToBeTaken().toString());

        Button addBtn = (Button)view.findViewById(R.id.taken_btn);
        addBtn.setEnabled(!list.get(position).isMedicationTaken());

        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                list.get(position).takeMedication();
                notifyDataSetChanged();
            }
        });

        return view;
    }
}