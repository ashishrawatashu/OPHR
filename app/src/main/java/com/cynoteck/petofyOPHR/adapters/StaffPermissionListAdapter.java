package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.StaffPermissionResponseModel;
import com.cynoteck.petofyOPHR.utils.OperatingHoursClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

public class StaffPermissionListAdapter extends RecyclerView.Adapter<StaffPermissionListAdapter.MyViewHolder>  {

    Context context;
    List<StaffPermissionResponseModel> staffPermissionResponseModels;
    private OperatingHoursClickListener operatingHoursClickListener;


    public StaffPermissionListAdapter(Context context, List<StaffPermissionResponseModel> staffPermissionResponseModels, OperatingHoursClickListener operatingHoursClickListener) {
        this.context = context;
        this.staffPermissionResponseModels = staffPermissionResponseModels;
        this.operatingHoursClickListener = operatingHoursClickListener;
    }

    @NonNull
    @Override
    public StaffPermissionListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.operating_hours_adapter, parent, false);
        StaffPermissionListAdapter.MyViewHolder vh = new StaffPermissionListAdapter.MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull StaffPermissionListAdapter.MyViewHolder holder,  int position) {
        holder.setIsRecyclable(false);

        holder.permission_name.setText(staffPermissionResponseModels.get(position).getPermissionName());

    if(staffPermissionResponseModels.get(position).getIsSelected().equals("true"))
         holder.permission_switch.setChecked(true);
    else {
        holder.permission_switch.setChecked(false);
    }
    }

    @Override
    public void onViewRecycled(@NonNull StaffPermissionListAdapter.MyViewHolder holder) {
        super.onViewRecycled(holder);
        holder.permission_switch.setOnCheckedChangeListener(null);
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return staffPermissionResponseModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView permission_name;
        SwitchCompat permission_switch;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            permission_name = itemView.findViewById(R.id.permission_name);
            permission_switch = itemView.findViewById(R.id.permission_switch);

            permission_switch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(((CompoundButton) view).isChecked()){
                        operatingHoursClickListener.onViewSetTime(getAdapterPosition(),staffPermissionResponseModels.get(getAdapterPosition()).getId(),"true");
                        //notifyDataSetChanged();
                        staffPermissionResponseModels.get(getAdapterPosition()).setIsSelected("true");
                    }
                    else
                    {
                        operatingHoursClickListener.onViewSetTime(getAdapterPosition(),staffPermissionResponseModels.get(getAdapterPosition()).getId(),"false");
//                        notifyDataSetChanged();
                        staffPermissionResponseModels.get(getAdapterPosition()).setIsSelected("false");

                    }
                }
            });

        }
    }
}
