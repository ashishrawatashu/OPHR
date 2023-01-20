package com.cynoteck.petofyOPHR.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.getStaffResponse.GetAllStaffData;
import com.cynoteck.petofyOPHR.utils.StaffListClickListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllStaffAdapter extends RecyclerView.Adapter<AllStaffAdapter.MyViewHolder> implements Filterable {

    Context context;
    List<GetAllStaffData> getAllStaffData;
    List<GetAllStaffData> filterGetAllStaffData;

    private StaffListClickListener onProductItemClickListner;

    public AllStaffAdapter(Context context, List<GetAllStaffData> getAllStaffData, StaffListClickListener onProductItemClickListner) {
        this.context = context;
        this.getAllStaffData = getAllStaffData;
        this.onProductItemClickListner = onProductItemClickListner;
        filterGetAllStaffData = new ArrayList<>(getAllStaffData);

    }

    @NonNull
    @Override
    public AllStaffAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_staff_list, parent, false);
        AllStaffAdapter.MyViewHolder vh = new AllStaffAdapter.MyViewHolder(v);
        return vh;

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AllStaffAdapter.MyViewHolder holder, int position) {

        if (getAllStaffData.get(position).getIsActive().equals("false")){
            holder.staff_status_TV.setTextColor(context.getResources().getColor(R.color.deactivate_red));
            holder.staff_status_TV.setText("Deactivate");
            holder.arrow_Staff_IV.setColorFilter(context.getResources().getColor(R.color.deactivate_red));

        }else {
            holder.staff_status_TV.setTextColor(context.getResources().getColor(R.color.dark_green));
            holder.staff_status_TV.setText("Active");
            holder.arrow_Staff_IV.setColorFilter(context.getResources().getColor(R.color.dark_green));

        }
        holder.staff_qualification_TV.setText(getAllStaffData.get(position).getVetQualification());
        holder.staff_name_TV.setText(getAllStaffData.get(position).getFirstName()+" "+getAllStaffData.get(position).getLastName());
        holder.staff_post_TV.setText(getAllStaffData.get(position).getEmail());
        holder.staff_phone_TV.setText(getAllStaffData.get(position).getPhoneNumber());

        Glide.with(context)
                .load(getAllStaffData.get(position).getProfileImageUrl())
                .placeholder(R.drawable.empty_vet_image)
                .into(holder.staff_image_CIV);

    }

    @Override
    public int getItemCount() {
        return getAllStaffData.size();
    }

    @Override
    public Filter getFilter() {
        return filterList;
    }
    private Filter filterList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<GetAllStaffData> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filterGetAllStaffData);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (GetAllStaffData item : filterGetAllStaffData){
                    if (item.getFirstName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            getAllStaffData.clear();
            getAllStaffData.addAll((List<GetAllStaffData>)results.values);
            notifyDataSetChanged();

        }
    };
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView staff_name_TV, staff_qualification_TV,staff_post_TV,staff_phone_TV,staff_status_TV;
        LinearLayout staff_status_LL,view_details_LL;
        CircleImageView staff_image_CIV;
        ImageView arrow_Staff_IV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            staff_name_TV = itemView.findViewById(R.id.staff_name_TV);
            staff_qualification_TV=itemView.findViewById(R.id.staff_qualification_TV);
            staff_post_TV = itemView.findViewById(R.id.staff_post_TV);
            view_details_LL = itemView.findViewById(R.id.view_details_LL);
            staff_status_LL=itemView.findViewById(R.id.staff_status_LL);
            staff_phone_TV = itemView.findViewById(R.id.staff_phone_TV);
            staff_status_TV=itemView.findViewById(R.id.staff_status_TV);
            staff_image_CIV=itemView.findViewById(R.id.staff_image_CIV);
            arrow_Staff_IV = itemView.findViewById(R.id.arrow_Staff_IV);

            view_details_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onProductItemClickListner!=null){
                        onProductItemClickListner.onViewDetailsClick(getAdapterPosition());
                    }
                }
            });
            staff_status_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onProductItemClickListner!=null){
                        onProductItemClickListner.onStausClick(getAdapterPosition(),staff_status_TV,arrow_Staff_IV);
                    }
                }
            });
        }
    }
}
