package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetListResponse.PetList;
import com.cynoteck.petofyOPHR.utils.SearchInterface;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    Context context;
    ArrayList<PetList> profileList;
    private SearchInterface onProductItemClickListner;

    public SearchAdapter(Context context, ArrayList<PetList> profileList, SearchInterface onProductItemClickListner) {
        this.context = context;
        this.profileList = profileList;
        this.onProductItemClickListner = onProductItemClickListner;
        //filterProfileList = new ArrayList<>(profileList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pet_name_TV.setText(profileList.get(position).getPetName() + " (" + profileList.get(position).getPetSex() + ")");
        holder.pet_reg__id_TV.setText(profileList.get(position).getPetUniqueId());
        Glide.with(context)
                .load(profileList.get(position).getPetProfileImageUrl())
                .placeholder(R.drawable.dummy_dog_image)
                .into(holder.pet_image_CIV);
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pet_name_TV,pet_reg__id_TV;
        CircleImageView pet_image_CIV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pet_name_TV = itemView.findViewById(R.id.pet_name_TV);
            pet_image_CIV = itemView.findViewById(R.id.pet_image_CIV);
            pet_reg__id_TV=itemView.findViewById(R.id.pet_reg__id_TV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onProductItemClickListner != null) {
                        onProductItemClickListner.onViewDetailsClick(getAdapterPosition());
                    }
                }
            });


        }
    }
}
