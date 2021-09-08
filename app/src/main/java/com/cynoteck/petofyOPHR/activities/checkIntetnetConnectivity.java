package com.cynoteck.petofyOPHR.activities;

import static com.cynoteck.petofyOPHR.activities.DashBoardActivity.isConnected;
import static com.cynoteck.petofyOPHR.activities.MedicalHistoryActivity.isConn;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.fragments.HomeFragment;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.cynoteck.petofyOPHR.utils.internetUtlis;

public class checkIntetnetConnectivity extends BroadcastReceiver {

    DashBoardActivity dashBoardActivity = new DashBoardActivity();

    public void onReceive(Context context, Intent intent) {
        context= context.getApplicationContext();
        try {
            if (isOnline(context)) {
                isConnected(true);
                isConn(true);
                Log.e("checkInternet", "App is back to Online  ");
//                Toast.makeText(context, "app is online in broadcast receiver class", Toast.LENGTH_SHORT).show();
            } else {
//                dashBoardActivity.checks();
                isConnected(false);
                isConn(false);


//                Methods methods=new Methods(context);
//                Toast.makeText(context, "app is not online in broadcast receiver class", Toast.LENGTH_SHORT).show();


//                methods.DialogInternet();
                Log.e("checkInternet", "Conectivity Failure  !!! ");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }




    private boolean isOnline(Context context) {

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }

    }
}

//    public void showView(Context context)
//    {
//        {
//            View noConnection= LayoutInflater.from(context).inflate(R.layout.nointernetconnection,null);
//            Log.e("TAG", "showView: "+context );
//            noConnection.setVisibility(View.VISIBLE);
//            Button retry =noConnection.findViewById(R.id.retry_BT);
//            retry.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context.getApplicationContext(), "Retry button is clicked", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//    }







