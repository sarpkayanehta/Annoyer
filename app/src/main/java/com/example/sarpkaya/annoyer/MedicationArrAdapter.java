package com.example.sarpkaya.annoyer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by SarpKaya on 25/03/2015.
 */
public class MedicationArrAdapter extends BaseAdapter implements ListAdapter {
    private List<MedicationModel> list;
    private Context context;
    private MedicationArrAdapter currObj;



    public MedicationArrAdapter(Context context) {
        this.context = context;
        currObj = this;
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
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        timeText.setText(dateFormat.format(list.get(position).getMedicationTimeToBeTaken().getValue()));

        Button addBtn = (Button)view.findViewById(R.id.taken_btn);
        //addBtn.setEnabled(!list.get(position).isMedicationTaken());
        if (list.get(position).getMedicationTakenTime() != null) addBtn.setBackgroundColor(Color.YELLOW);
        if (list.get(position).isMedicationTaken()) addBtn.setBackgroundColor(Color.GREEN);

        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                list.get(position).takeMedication();
                FHIRInterface.getInstance().tookMedication(list.get(position), currObj);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}