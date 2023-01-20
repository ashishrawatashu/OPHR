package com.cynoteck.petofyOPHR.activities;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.cynoteck.petofyOPHR.R;

public class CommonActivity extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        webview=findViewById(R.id.webview);

/*      Bundle extras = getIntent().getExtras();
        String fragment = extras.getString("fragment");

        switch (fragment){

            case "selectPetReportsFragment":

                SelectPetReportsFragment selectPetReportsFragment = new SelectPetReportsFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.common_activity_frame, selectPetReportsFragment);
                ft.addToBackStack(null);
                ft.commit();
                break;
        }*/
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            finish();
        else
            super.onBackPressed();
    }
}
