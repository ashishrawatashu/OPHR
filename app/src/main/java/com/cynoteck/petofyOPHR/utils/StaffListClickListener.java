package com.cynoteck.petofyOPHR.utils;

import android.widget.ImageView;
import android.widget.TextView;

public interface StaffListClickListener {
    public void onViewDetailsClick(int position);
    public void onStausClick(int position, TextView button, ImageView imageView);

}
