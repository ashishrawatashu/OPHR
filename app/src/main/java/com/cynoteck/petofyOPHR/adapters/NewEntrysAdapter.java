package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.recentEntrys.PetClinicVisitList;
import com.cynoteck.petofyOPHR.utils.NewEntryListClickListener;

import java.util.ArrayList;
import java.util.List;

public class NewEntrysAdapter extends RecyclerView.Adapter<NewEntrysAdapter.MyViewHolder> implements Filterable {
    Context context;
    List<PetClinicVisitList> getAllStaffData;
    private NewEntryListClickListener newEntryListClickListener;
    List<PetClinicVisitList> filterProfileList;

    public NewEntrysAdapter(Context context, List<PetClinicVisitList> getAllStaffData, NewEntryListClickListener newEntryListClickListener) {
        this.context = context;
        this.getAllStaffData = getAllStaffData;
        this.newEntryListClickListener = newEntryListClickListener;
        filterProfileList = new ArrayList<>(getAllStaffData);
    }

    @NonNull
    @Override
    public NewEntrysAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_entry_list, parent, false);
        NewEntrysAdapter.MyViewHolder vh = new NewEntrysAdapter.MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull NewEntrysAdapter.MyViewHolder holder, int position) {
        holder.pet_nm_prnt_nm.setText(getAllStaffData.get(position).getPetName()+"("+getAllStaffData.get(position).getPetUniqueId()+")"+
                "\n"+getAllStaffData.get(position).getPetParentName());
        holder.visit_dt.setText(getAllStaffData.get(position).getVisitDate());
        holder.nature_of_visit.setText(String.valueOf(getAllStaffData.get(position).getNatureOfVisit().getNature()));
        holder.follow_up_dt.setText(getAllStaffData.get(position).getFollowUpDate());

        if(getAllStaffData.get(position).getNatureOfVisit().getNature().equals("Immunization"))
        {
            holder.precription.setText(getAllStaffData.get(position).getNatureOfVisit().getNature());
        }
        else{
            holder.precription.setText("Prescription");
        }


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

            List<PetClinicVisitList> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filterProfileList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (PetClinicVisitList item : filterProfileList){
                    if (item.getPetUniqueId().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }else if (item.getPetName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }else if (item.getPetParentName().toLowerCase().contains(filterPattern)){
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
            getAllStaffData.addAll((List<PetClinicVisitList>)results.values);
            notifyDataSetChanged();

        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pet_nm_prnt_nm, visit_dt,nature_of_visit,follow_up_dt,precription /*download,edit*/;
        LinearLayout petDeatils_LL;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pet_nm_prnt_nm = itemView.findViewById(R.id.pet_nm_prnt_nm);
            visit_dt = itemView.findViewById(R.id.visit_dt);
            nature_of_visit = itemView.findViewById(R.id.nature_of_visit);
            follow_up_dt=itemView.findViewById(R.id.follow_up_dt);
            petDeatils_LL=itemView.findViewById(R.id.petDeatils_LL);
            precription=itemView.findViewById(R.id.prescription);

            /*download=itemView.findViewById(R.id.download);
            edit=itemView.findViewById(R.id.edit);*/


            petDeatils_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (newEntryListClickListener!=null){
                        newEntryListClickListener.onProductClick(getAdapterPosition());
                    }
                }
            });

            precription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(precription.getText().toString().equals("Prescription"))
                    newEntryListClickListener.onProductPrescriptionClick(getAdapterPosition());
                    else
                    newEntryListClickListener.onProductDownloadClick(getAdapterPosition());
                }
            });

           /* download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newEntryListClickListener.onProductDownloadClick(getAdapterPosition());
                }
            });*/

            /*edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newEntryListClickListener.onProductEditClick(getAdapterPosition());
                }
            });*/

        }
    }
}
