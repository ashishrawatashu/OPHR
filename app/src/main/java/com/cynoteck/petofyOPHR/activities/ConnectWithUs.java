package com.cynoteck.petofyOPHR.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.cynoteck.petofyOPHR.R;

public class ConnectWithUs extends AppCompatActivity {
    ImageButton instagram,twitter,facebook,linkedin,youtube,googlemap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_with_us);


        instagram= (ImageButton)findViewById(R.id.instagram);
        twitter=(ImageButton)findViewById(R.id.twitter);
        facebook=(ImageButton)findViewById(R.id.facebook);
        linkedin=(ImageButton)findViewById(R.id.linkedin);
        youtube=(ImageButton)findViewById(R.id.youtube);
        googlemap=(ImageButton)findViewById(R.id.googleMap);



        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.instagram.com/accounts/login/");
                Intent instagram= new Intent(Intent.ACTION_VIEW,uri);
                instagram.setPackage("com.instagram.android");

                try {
                    startActivity(instagram);
                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/accounts/login/")));
                }

            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://twitter.com/petofyhelp");
                Intent twitter= new Intent(Intent.ACTION_VIEW,uri);
                twitter.setPackage("com.twitter.android");

                try {
                    startActivity(twitter);
                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://twitter.com/petofyhelp")));
                }


            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://www.facebook.com/petofy");
                Intent facebook= new Intent(Intent.ACTION_VIEW,uri);
                facebook.setPackage("com.facebook.katana");

                try {
                    startActivity(facebook);
                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/petofy")));
                }


            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://www.linkedin.com/authwall?trk=gf&trkInfo=AQHETd0vMta2EgAAAXxKAkQ4uP93VwxId5hckI5r1v2I0JskvGSzCNjhiCcajvigY69R-Zrmi6rE-79Aq5NqnE3yWqZJLVXL-AEmEC61ak-0fYIprKfm76DnIchI1HbJOcU4DXY=&originalReferer=https://www.petofy.com/&sessionRedirect=https%3A%2F%2Fwww.linkedin.com%2Fcompany%2Fpetofy-india%2F");
                Intent linkedin= new Intent(Intent.ACTION_VIEW,uri);
                linkedin.setPackage("com.linkedin.android");

                try {
                    startActivity(linkedin);
                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.linkedin.com/authwall?trk=gf&trkInfo=AQHETd0vMta2EgAAAXxKAkQ4uP93VwxId5hckI5r1v2I0JskvGSzCNjhiCcajvigY69R-Zrmi6rE-79Aq5NqnE3yWqZJLVXL-AEmEC61ak-0fYIprKfm76DnIchI1HbJOcU4DXY=&originalReferer=https://www.petofy.com/&sessionRedirect=https%3A%2F%2Fwww.linkedin.com%2Fcompany%2Fpetofy-india%2F")));
                }

            }
        });






        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Uri uri = Uri.parse("https://www.youtube.com/channel/UCWS_bYg6jK4rNGnOpoH7hUg");
                Intent youtube= new Intent(Intent.ACTION_VIEW,uri);
                youtube.setPackage("com.youtube.android");

                try {
                    startActivity(youtube);
                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/channel/UCWS_bYg6jK4rNGnOpoH7hUg")));
                }

            }
        });




        googlemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://bit.ly/3usLOU0");
                Intent googlemap= new Intent(Intent.ACTION_VIEW,uri);
                googlemap.setPackage("com.googlemap.android");

                try {
                    startActivity(googlemap);
                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://bit.ly/3usLOU0")));
                }
            }
        });













    }
}