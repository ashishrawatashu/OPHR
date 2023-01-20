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

public class UpdateXRayAdpater extends RecyclerView.Adapter<UpdateXRayAdpater.MyViewHolder> {

    Context context;
    List<PetTestsAndXrayList> petTestsAndXrayLists;
    ViewAndUpdateClickListener onProductItemClickListner;

    public UpdateXRayAdpater(Context context, List<PetTestsAndXrayList> petClinicVisitLists, ViewAndUpdateClickListener onProductItemClickListner) {
        this.context = context;
        this.petTestsAndXrayLists = petClinicVisitLists;
        this.onProductItemClickListner = onProductItemClickListner;
    }

    @NonNull
    @Override
    public UpdateXRayAdpater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_x_ray_list, parent, false);
        UpdateXRayAdpater.MyViewHolder vh = new UpdateXRayAdpater.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateXRayAdpater.MyViewHolder holder, int position) {
        holder.test_type_TV.setText(petTestsAndXrayLists.get(position).getTypeOfTest().getTestType());
        holder.result_TV.setText(petTestsAndXrayLists.get(position).getResults());
        holder.test_date_TV.setText(petTestsAndXrayLists.get(position).getDateTested());


    }

    @Override
    public int getItemCount() {
        return petTestsAndXrayLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView test_type_TV,result_TV,test_date_TV,update_TV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            test_type_TV = itemView.findViewById(R.id.test_type_TV);
            result_TV = itemView.findViewById(R.id.result_TV);
            test_date_TV = itemView.findViewById(R.id.test_date_TV);
            update_TV=itemView.findViewById(R.id.update_TV);

            update_TV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onProductItemClickListner!=null){
                        onProductItemClickListner.onUpdateXrayClick(getAdapterPosition());
                    }
                }
            });




        }
    }
}
