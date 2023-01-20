package com.cynoteck.petofyOPHR.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.params.addPetClinicParamRequest.VaccinationModel;
import com.cynoteck.petofyOPHR.utils.ImmunizationOnclickListener;

import java.util.ArrayList;
import java.util.StringTokenizer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImmunazationVaccineAdopter extends RecyclerView.Adapter<ImmunazationVaccineAdopter.MyViewHolder> {

    Context context;
    ArrayList<VaccinationModel> vaccinationModelArrayList;
    private ImmunizationOnclickListener immunizationOnclickListener;

    public ImmunazationVaccineAdopter(Context context, ImmunizationOnclickListener immunizationOnclickListener, ArrayList<VaccinationModel> petClinicVisitLists) {
        this.context = context;
        this.vaccinationModelArrayList = petClinicVisitLists;
        this.immunizationOnclickListener = immunizationOnclickListener;
    }

    @NonNull
    @Override
    public ImmunazationVaccineAdopter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.immunization_add_layout, parent, false);
        ImmunazationVaccineAdopter.MyViewHolder vh = new ImmunazationVaccineAdopter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ImmunazationVaccineAdopter.MyViewHolder holder, int position) {
        
        holder.vaccine_brand_type.setText(vaccinationModelArrayList.get(position).getVaccineType());
        holder.vaccine_name_TV.setText(vaccinationModelArrayList.get(position).getVaccine());
        holder.vaccine_date.setText(vaccinationModelArrayList.get(position).getImmunizationDate());

    }

    @Override
    public int getItemCount() {
        return vaccinationModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView vaccine_brand_type,vaccine_name_TV,vaccine_date;
        ImageView vaccine_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vaccine_brand_type = itemView.findViewById(R.id.vaccine_brand_type);
            vaccine_name_TV = itemView.findViewById(R.id.vaccine_name_TV);
            vaccine_date = itemView.findViewById(R.id.vaccine_date);
            vaccine_delete = itemView.findViewById(R.id.vaccine_delete);

            vaccine_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(context)
                            .setTitle("Delete entry")
                            .setMessage("Are you sure you want to delete this entry?")

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation
                                    immunizationOnclickListener.onItemClick(getAdapterPosition());
                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            });

        }
    }
}
