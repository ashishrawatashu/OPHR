package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.getXRayReports.getPetTestAndXRayResponse.PetTestsAndXrayList;
import com.cynoteck.petofyOPHR.utils.ViewAndUpdateClickListener;

import java.util.List;

public class TestAndXRayAdpater extends RecyclerView.Adapter<TestAndXRayAdpater.MyViewHolder> {

    Context context;
    List<PetTestsAndXrayList> petTestsAndXrayLists;
    ViewAndUpdateClickListener onProductItemClickListner;

    public TestAndXRayAdpater(Context context, List<PetTestsAndXrayList> petClinicVisitLists, ViewAndUpdateClickListener onProductItemClickListner) {
        this.context = context;
        this.petTestsAndXrayLists = petClinicVisitLists;
        this.onProductItemClickListner = onProductItemClickListner;
    }

    @NonNull
    @Override
    public TestAndXRayAdpater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_and_x_ray_reports_list, parent, false);
        TestAndXRayAdpater.MyViewHolder vh = new TestAndXRayAdpater.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TestAndXRayAdpater.MyViewHolder holder, int position) {
        holder.test_type_TV.setText(petTestsAndXrayLists.get(position).getTypeOfTest().getTestType());
        holder.result_TV.setText(petTestsAndXrayLists.get(position).getResults());
        holder.test_date_TV.setText(petTestsAndXrayLists.get(position).getDateTested());


    }

    @Override
    public int getItemCount() {
        return petTestsAndXrayLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView test_type_TV,result_TV,test_date_TV,view_TV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            test_type_TV = itemView.findViewById(R.id.test_type_TV);
            result_TV = itemView.findViewById(R.id.result_TV);
            test_date_TV = itemView.findViewById(R.id.test_date_TV);
            view_TV=itemView.findViewById(R.id.view_TV);

            view_TV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onProductItemClickListner!=null){
                        onProductItemClickListner.onViewXrayClick(getAdapterPosition());
                    }
                }
            });




        }
    }
}
