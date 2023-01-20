package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.immunizationListResponse.ImmunizationScheduleScheduleList;
import com.cynoteck.petofyOPHR.utils.ImmunizationOnclick;

import java.util.List;

public class ImmunizationChartAdapter extends RecyclerView.Adapter<ImmunizationChartAdapter.MyViewHolder> {
    Context context;
    List<ImmunizationScheduleScheduleList> immunizationScheduleScheduleLists;
    private ImmunizationOnclick immunizationOnclick;

    public ImmunizationChartAdapter(Context context, List<ImmunizationScheduleScheduleList> immunizationScheduleScheduleLists, ImmunizationOnclick immunizationOnclick) {
        this.context = context;
        this.immunizationScheduleScheduleLists = immunizationScheduleScheduleLists;
        this.immunizationOnclick = immunizationOnclick;
    }


    @NonNull
    @Override
    public ImmunizationChartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.immunization_chart_list, parent, false);
        ImmunizationChartAdapter.MyViewHolder vh = new ImmunizationChartAdapter.MyViewHolder(v);
        return vh;    }

    @Override
    public void onBindViewHolder(@NonNull ImmunizationChartAdapter.MyViewHolder holder, int position) {
        String min_age="",max_age;
        min_age =Double.toString(Math.floor(Double.parseDouble(immunizationScheduleScheduleLists.get(position).getMinimunAge())));
        max_age =Double.toString(Math.floor(Double.parseDouble(immunizationScheduleScheduleLists.get(position).getMaximunAge())));

        holder.age_group_TV.setText(min_age+"-"+max_age+"Days");
        if (immunizationScheduleScheduleLists.get(position).getRecommendedVaccinations().equals("PUPPY DP")){
            holder.delete_vaccine_IV.setVisibility(View.INVISIBLE);
            holder.edit_vaccine_LL.setVisibility(View.INVISIBLE);
        }
        holder.vaccine_name_TV.setText(immunizationScheduleScheduleLists.get(position).getRecommendedVaccinations());

    }

    @Override
    public int getItemCount() {
        return immunizationScheduleScheduleLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView age_group_TV,vaccine_name_TV;
        ImageView delete_vaccine_IV;
        LinearLayout edit_vaccine_LL;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            age_group_TV = itemView.findViewById(R.id.age_group_TV);
            vaccine_name_TV = itemView.findViewById(R.id.vaccine_name_TV);
            delete_vaccine_IV = itemView.findViewById(R.id.delete_vaccine_IV);
            edit_vaccine_LL = itemView.findViewById(R.id.edit_vaccine_LL);

            delete_vaccine_IV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (immunizationOnclick!=null){
                        immunizationOnclick.onDeleteButton(getAdapterPosition());
                    }
                }
            });
            edit_vaccine_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (immunizationOnclick!=null){
                        immunizationOnclick.onEditButton(getAdapterPosition());
                    }
                }
            });

        }
    }
}
