package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.bankAccountResponse.GetBankAccoutsData;

import java.util.List;

public class GetBanksAccountsAdapter extends RecyclerView.Adapter<GetBanksAccountsAdapter.MyViewHolder> {
    Context context;
    List<GetBankAccoutsData> getBankAccoutsData;

    public GetBanksAccountsAdapter(Context context, List<GetBankAccoutsData> getBankAccoutsData) {
        this.context = context;
        this.getBankAccoutsData = getBankAccoutsData;
    }

    @NonNull
    @Override
    public GetBanksAccountsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_list, parent, false);
        GetBanksAccountsAdapter.MyViewHolder vh = new GetBanksAccountsAdapter.MyViewHolder(v);
        return vh;    }

    @Override
    public void onBindViewHolder(@NonNull GetBanksAccountsAdapter.MyViewHolder holder, int position) {
        holder.name_TV.setText(getBankAccoutsData.get(position).getBank_account().getName());
        holder.ifsc_TV.setText(getBankAccoutsData.get(position).getBank_account().getIfsc());
        holder.account_number_TV.setText(getBankAccoutsData.get(position).getBank_account().getAccount_number());
        holder.account_type_TV.setText(getBankAccoutsData.get(position).getAccount_type());

    }

    @Override
    public int getItemCount() {
        return getBankAccoutsData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_TV, ifsc_TV,account_number_TV,account_type_TV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_TV = itemView.findViewById(R.id.name_TV);
            ifsc_TV = itemView.findViewById(R.id.ifsc_TV);
            account_number_TV = itemView.findViewById(R.id.account_number_TV);
            account_type_TV = itemView.findViewById(R.id.account_type_TV);

        }
    }
}
