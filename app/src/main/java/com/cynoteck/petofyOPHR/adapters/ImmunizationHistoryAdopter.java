package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;

import java.util.ArrayList;

public class ImmunizationHistoryAdopter extends RecyclerView.Adapter<ImmunizationHistoryAdopter.MyViewHolder> {
    Context context;
    ArrayList<String> nextVisitDateList;
    ArrayList<String> vaccineClassList;
    ArrayList<String> vaccineList;
    ArrayList<String> immunizationDateList;

    public ImmunizationHistoryAdopter(Context context,ArrayList<String> nextVisitDateList,ArrayList<String> vaccineClassList,ArrayList<String> vaccineList,ArrayList<String> immunizationDateList ) {
        this.context = context;
        this.nextVisitDateList = nextVisitDateList;
        this.vaccineClassList = vaccineClassList;
        this.vaccineList = vaccineList;
        this.immunizationDateList = immunizationDateList;
    }

    @NonNull
    @Override
    public ImmunizationHistoryAdopter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.immunization_history_adopter, parent, false);
        ImmunizationHistoryAdopter.MyViewHolder vh = new ImmunizationHistoryAdopter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ImmunizationHistoryAdopter.MyViewHolder holder, int position) {

      holder.immunization_dt.setText(immunizationDateList.get(position).substring(0,immunizationDateList.get(position).length()-9));
      holder.vaccine_cls.setText(vaccineClassList.get(position));
      holder.vaccine.setText(vaccineList.get(position));
      holder.nxt_due_dt.setText(nextVisitDateList.get(position));
    }

    @Override
    public int getItemCount() {
        return nextVisitDateList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView immunization_dt,vaccine_cls,vaccine,nxt_due_dt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            immunization_dt =itemView.findViewById(R.id.immunization_dt);
            vaccine_cls = itemView.findViewById(R.id.vaccine_cls);
            vaccine = itemView.findViewById(R.id.vaccine);
            nxt_due_dt = itemView.findViewById(R.id.nxt_due_dt);
        }
    }
}
