package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.getVaccinationResponse.GetVaccineResponseModel;
import com.cynoteck.petofyOPHR.utils.ImmunizationOnclickListener;

import java.util.ArrayList;

public class VaccineTypeAdapter extends RecyclerView.Adapter<VaccineTypeAdapter.MyViewHolder> {
    Context context;
    ArrayList<GetVaccineResponseModel> getVaccineResponseModels;
    private ImmunizationOnclickListener immunizationOnclickListener;

    public VaccineTypeAdapter(Context context, ArrayList<GetVaccineResponseModel> getVaccineResponseModels, ImmunizationOnclickListener immunizationOnclickListener) {
        this.context = context;
        this.getVaccineResponseModels = getVaccineResponseModels;
        this.immunizationOnclickListener = immunizationOnclickListener;
    }

    @NonNull
    @Override
    public VaccineTypeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaccination_chart_list, parent, false);
        VaccineTypeAdapter.MyViewHolder vh = new VaccineTypeAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final VaccineTypeAdapter.MyViewHolder holder, int position) {
        String min_age = getVaccineResponseModels.get(position).getVaccinationSchedule().getMinimunAge().substring(0, getVaccineResponseModels.get(position).getVaccinationSchedule().getMinimunAge().length() - 3);
        String max_age = getVaccineResponseModels.get(position).getVaccinationSchedule().getMaximunAge().substring(0, getVaccineResponseModels.get(position).getVaccinationSchedule().getMaximunAge().length() - 3);
        for (int i = 0; i < getVaccineResponseModels.get(position).getVaccineChartDetails().size(); i++) {
            if (getVaccineResponseModels.get(position).getVaccineChartDetails().get(i).getVaccineType().equals("Primary")) {
                if (getVaccineResponseModels.get(position).getVaccineChartDetails().get(i).getStatus().equals("true")) {
                    holder.primary_IV.setImageResource(R.drawable.immunization_given_icon);
                    holder.primary_given__TV.setVisibility(View.VISIBLE);
                    holder.primary_pending_TV.setVisibility(View.GONE);
                } else  {
                    holder.primary_IV.setImageResource(R.drawable.immunization_pending_icon);
                    holder.primary_pending_TV.setVisibility(View.VISIBLE);
                    holder.primary_given__TV.setVisibility(View.GONE);
                }
            }
            if (getVaccineResponseModels.get(position).getVaccineChartDetails().get(i).getVaccineType().equals("BoosterOne")) {
                if (getVaccineResponseModels.get(position).getVaccineChartDetails().get(i).getStatus().equals("true")) {
                    holder.booster_one_IV.setImageResource(R.drawable.immunization_given_icon);
                    holder.booster_one_given_TV.setVisibility(View.VISIBLE);
                    holder.booster_one_pending_TV.setVisibility(View.GONE);
                } else {
                    holder.booster_one_IV.setImageResource(R.drawable.immunization_pending_icon);
                    holder.booster_one_pending_TV.setVisibility(View.VISIBLE);
                    holder.booster_one_given_TV.setVisibility(View.GONE);
                }
            }
            if (getVaccineResponseModels.get(position).getVaccineChartDetails().get(i).getVaccineType().equals("BoosterTwo")) {
                if (getVaccineResponseModels.get(position).getVaccineChartDetails().get(i).getStatus().equals("true")) {
                    holder.booster_two_IV.setImageResource(R.drawable.immunization_given_icon);
                    holder.booster_two_given_TV.setVisibility(View.VISIBLE);
                    holder.booster_two_pending_TV.setVisibility(View.GONE);
                } else  {
                    holder.booster_two_IV.setImageResource(R.drawable.immunization_pending_icon);
                    holder.booster_two_pending_TV.setVisibility(View.VISIBLE);
                    holder.booster_two_given_TV.setVisibility(View.GONE);
                }
            }
        }

        holder.age_group.setText(min_age + " - " + max_age + " Days");
        holder.vaccine_name.setText(getVaccineResponseModels.get(position).getVaccinationSchedule().getPrimaryVaccine());
        holder.down_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.up_IV.setVisibility(View.VISIBLE);
                holder.down_IV.setVisibility(View.GONE);
                holder.vaccine_status_CL.setVisibility(View.VISIBLE);

            }
        });

        holder.up_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.up_IV.setVisibility(View.GONE);
                holder.down_IV.setVisibility(View.VISIBLE);
                holder.vaccine_status_CL.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return getVaccineResponseModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView vaccine_name, age_group, primary_pending_TV, primary_given__TV, booster_one_pending_TV, booster_one_given_TV, booster_two_given_TV, booster_two_pending_TV;
        ImageView primary_IV, booster_one_IV, booster_two_IV, up_IV, down_IV;
        ConstraintLayout vaccine_status_CL;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vaccine_name = itemView.findViewById(R.id.vaccine_name);
            age_group = itemView.findViewById(R.id.age_group);
            primary_given__TV = itemView.findViewById(R.id.primary_given_TV);
            booster_one_given_TV = itemView.findViewById(R.id.booster_one_given_TV);
            booster_two_given_TV = itemView.findViewById(R.id.booster_two_given_TV);
            primary_pending_TV = itemView.findViewById(R.id.primary_pending_TV);
            booster_one_pending_TV = itemView.findViewById(R.id.booster_one_pending_TV);
            booster_two_pending_TV = itemView.findViewById(R.id.booster_two_pending_TV);
            primary_IV = itemView.findViewById(R.id.primary_IV);
            booster_one_IV = itemView.findViewById(R.id.booster_one_IV);
            booster_two_IV = itemView.findViewById(R.id.booster_two_IV);
            up_IV = itemView.findViewById(R.id.up_IV);
            down_IV = itemView.findViewById(R.id.down_IV);
            vaccine_status_CL = itemView.findViewById(R.id.vaccine_status_CL);


        }
    }
}
