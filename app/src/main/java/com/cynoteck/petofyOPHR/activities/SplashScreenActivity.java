package com.cynoteck.petofyOPHR.activities;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    Animation animation;
   static ImageView splash_logo;
    Methods methods;
    int internetChkCode=0;
    static LinearLayout something_wrong_splash;
    BroadcastReceiver broadcastReceiver;
    static boolean checkNet=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splash_logo= (ImageView) findViewById(R.id.splashlogo);
        something_wrong_splash=findViewById(R.id.something_wrong_splash);
        methods = new Methods(this);
        Button retr_BT;

      /*  ActionBar actionBar=getSupportActionBar();
        actionBar.hide();*/

        animation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.bounce);
        splash_logo.setAnimation(animation);
//        retr_BT=findViewById(R.id.rtr_BT);
//        retr_BT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(checkNet){
//                    something_wrong_splash.setVisibility(View.GONE);
//                    splash_logo.setVisibility(View.VISIBLE);
//
//                }
//                else{
//                    show();
//                }
//            }
//        });
        NetwordDetect();
        try {
            if (methods.isInternetOn()) {
                internetChkCode=1;
                updateMethod();

            } else {
                internetChkCode=0;
                methods.DialogInternet();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void NetwordDetect() {
        boolean WIFI = false;
        boolean MOBILE = false;
        ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] networkInfo = CM.getAllNetworkInfo();
        for (NetworkInfo netInfo : networkInfo) {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netInfo.isConnected())
                    WIFI = true;
            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (netInfo.isConnected())
                    MOBILE = true;
        }

        if (WIFI) {
            Config.IpAddress = GetDeviceipWiFiData();
//            Constants.EndUserIp= Config.IpAddress;
            Log.e("WIFI", Config.IpAddress + "");
        }

        if (MOBILE) {
            Config.IpAddress = getLocalIpAddress();
//          Constants.EndUserIp= Config.IpAddress;
            Log.e("MOBILE", Config.IpAddress + "");
        }
    }

    public String GetDeviceipWiFiData() {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        @SuppressWarnings("deprecation")
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        Log.e("sssss", ip + "");
                        return ip;
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }

    void updateMethod() { new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestMultiplePermissions();
            }
        },2500);
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        android.Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Log.d("STORAGE_DIALOG","All permissions are granted by user!");
                            intentActivity();
                        }else {
                            Log.d("STORAGE_DIALOG","storagePermissionDialog");
                            intentActivity();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            Log.d("STORAGE_DIALOG","openSettingsDialog");
                            startActivity(new Intent(SplashScreenActivity.this,PermissionCheckActivity.class));

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }


                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Log.d("STORAGE_DIALOG",error.toString());

                    }
                })
                .onSameThread()
                .check();
    }

    private void intentActivity() {
        internetChkCode=0;
        Intent intent;
        SharedPreferences sharedPreferences = getSharedPreferences("userdetails", 0);
        String loggedIn = sharedPreferences.getString("loggedIn", "");

        if (loggedIn.equals("loggedIn")){
            intent = new Intent(SplashScreenActivity.this,DashBoardActivity.class);
        }else {
            intent = new Intent(SplashScreenActivity.this, InstructionActivity.class);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

//    protected void registerBroadcast() {
////        registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        try {
//            unregisterReceiver(broadcastReceiver);
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//    }


//    public  static void isCon(boolean value) {
//        if (value) {
//            checkNet=true;
//            Log.e("Connected", "Yes ");
//        }
//        else {
//            checkNet=false;
//            show();
//        }
//    }
//
//    private static void show() {
//
//        something_wrong_splash.setVisibility(View.VISIBLE);
//        splash_logo.setVisibility(View.GONE);
//
//    }





    @Override
    protected void onResume() {
        super.onResume();
        if(internetChkCode==0)
        {
            try {
                if (methods.isInternetOn()) {
                    updateMethod();

                } else {
                    methods.DialogInternet();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}
