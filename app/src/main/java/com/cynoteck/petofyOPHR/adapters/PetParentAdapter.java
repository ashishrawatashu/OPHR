package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.getPetParentResponse.GetPetParentListData;
import com.cynoteck.petofyOPHR.utils.RegisterRecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class PetParentAdapter extends RecyclerView.Adapter<PetParentAdapter.MyViewHolder> implements Filterable {

    Context context;
    List<GetPetParentListData> parentList;
    List<GetPetParentListData> filterParentList;
    RegisterRecyclerViewClickListener onProductItemClickListner;

    public PetParentAdapter(Context context, List<GetPetParentListData> parentList, RegisterRecyclerViewClickListener onProductItemClickListner) {
        this.context = context;
        this.parentList = parentList;
        this.onProductItemClickListner = onProductItemClickListner;
        filterParentList = new ArrayList<>(parentList);
    }

    @NonNull
    @Override
    public PetParentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_parent_list, parent, false);
        PetParentAdapter.MyViewHolder vh = new PetParentAdapter.MyViewHolder(v);
        return vh;    }

    @Override
    public void onBindViewHolder(@NonNull PetParentAdapter.MyViewHolder holder, int position) {
        holder.pet_parent_name.setText(parentList.get(position).getFirstName()+" "+parentList.get(position).getLastName());
//        if (parentList.get(position).getPhoneNumber().equals(" ")){
//            holder.pet_parent_phone.setText(" ");
//
//        }else {
            holder.pet_parent_phone.setText("(" + parentList.get(position).getPhoneNumber() + ")");
//        }
    }

    @Override
    public int getItemCount() {
        return parentList.size();
    }

    @Override
    public Filter getFilter() {
        return filterList;
    }
    private Filter filterList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<GetPetParentListData> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filterParentList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (GetPetParentListData item : filterParentList){
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
            parentList.clear();
            parentList.addAll((List<GetPetParentListData>)results.values);
            notifyDataSetChanged();

        }
    };
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pet_parent_name, pet_parent_phone;
        RelativeLayout list_RL;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pet_parent_name = itemView.findViewById(R.id.pet_parent_name);
            pet_parent_phone = itemView.findViewById(R.id.pet_parent_phone);
            list_RL=itemView.findViewById(R.id.list_RL);

            list_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onProductItemClickListner!=null){
                        onProductItemClickListner.onProductClick(getAdapterPosition());
                    }
                }
            });

        }
    }
}
