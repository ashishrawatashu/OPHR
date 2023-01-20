package com.cynoteck.petofyOPHR.CalenderLib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.cynoteck.petofyOPHR.R;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CalAdapter extends RecyclerView.Adapter<CalAdapter.MyViewHolder> {
    List<View> itemViewList = new ArrayList<>();

    Context context;
    int possss;
    Object toCallBack;
    private int selectedPosition;

    DayDateMonthYearModel lastDaySelected;

    int color = R.color.whiteColor;

    ArrayList<DayDateMonthYearModel> dayModelList = new ArrayList<>();
    TextView clickedTextView = null;
    ArrayList<TextView> dateArrayList = new ArrayList<>();
    ArrayList<TextView> dayArrayList = new ArrayList<>();
    boolean isTodayDate = true;

    public CalAdapter(Context context, ArrayList<DayDateMonthYearModel> dayModelList) {
        this.context = context;
        this.dayModelList = dayModelList;
        selectedPosition = 0;
    }

    public void setCallback(Object toCallBack) {
        this.toCallBack = toCallBack;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView day, date;
        public ImageView haveAppointment;
        LinearLayout dateSelect;

        public MyViewHolder(View view) {
            super(view);
            day = view.findViewById(R.id.day);
            date = view.findViewById(R.id.date);
            dateSelect = view.findViewById(R.id.dateSelect);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_day_layout, parent, false);
        itemViewList.add(itemView); //to add all the 'list row item' views

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        String t = dayModelList.get(position).day.toString();
        if (selectedPosition == position && isTodayDate == false) {
            holder.dateSelect.setBackground(context.getResources().getDrawable(R.drawable.select_date_bg));
            holder.day.setTextColor(context.getResources().getColor(R.color.whiteColor));
            holder.date.setTextColor(context.getResources().getColor(R.color.whiteColor));

        }  else if (dayModelList.get(position).isToday == true && isTodayDate ==true) {
            holder.dateSelect.setBackground(context.getResources().getDrawable(R.drawable.select_date_bg));
            holder.day.setTextColor(context.getResources().getColor(R.color.whiteColor));
            holder.date.setTextColor(context.getResources().getColor(R.color.whiteColor));
            try {
                CallBack cb = new CallBack(toCallBack, "newDateSelected");
                cb.invoke(dayModelList.get(position));
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else {
            holder.dateSelect.setBackground(context.getResources().getDrawable(R.drawable.un_select_date_bg));
            holder.day.setTextColor(context.getResources().getColor(R.color.black_color));
            holder.date.setTextColor(context.getResources().getColor(R.color.gray_3));
        }






        holder.day.setText(t + " ");
        holder.date.setText(dayModelList.get(position).date);
        holder.date.setTag(position);
        dateArrayList.add(holder.date);
        dayArrayList.add(holder.day);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTodayDate = false;
                selectedPosition = position;
                notifyDataSetChanged();


//                if (clickedTextView == null) {
//                    Log.e("CLICK","1");
//                    clickedTextView = (TextView) v;
//                    holder.dateSelect.setBackground(context.getResources().getDrawable(R.drawable.select_date_bg));
//                    holder.day.setTextColor(context.getResources().getColor(R.color.whiteColor));
//                    clickedTextView.setTextColor(context.getResources().getColor(R.color.whiteColor));
//                    clickedTextView.setTypeface(clickedTextView.getTypeface(), Typeface.NORMAL);
//                } else
//                    {
//                        Log.e("CLICK","2");
////                    if(!dayModelList.get(pos).isToday) {
//                    if (lastDaySelected != null && lastDaySelected.isToday) {
//                        Log.e("CLICK","3");
//                        clickedTextView.setBackground(context.getResources().getDrawable(R.drawable.un_select_date_bg));
//                        clickedTextView.setTextColor(context.getResources().getColor(R.color.gray_3));
//                        holder.day.setTextColor(context.getResources().getColor(R.color.black));
//                        clickedTextView.setTypeface(clickedTextView.getTypeface(), Typeface.NORMAL);
//                    }
//                    else {
//                        Log.e("CLICK","4");
//                        clickedTextView.setBackground(context.getResources().getDrawable(R.drawable.un_select_date_bg));
//                        clickedTextView.setTextColor(context.getResources().getColor(R.color.gray_3));
//                        holder.day.setTextColor(context.getResources().getColor(R.color.black));
//                        clickedTextView.setTypeface(clickedTextView.getTypeface(), Typeface.NORMAL);
//                    }
//                    clickedTextView = (TextView) v;
//                    holder.dateSelect.setBackground(context.getResources().getDrawable(R.drawable.select_date_bg));
//                    clickedTextView.setTextColor(context.getResources().getColor(R.color.whiteColor));
//                    holder.day.setTextColor(context.getResources().getColor(R.color.whiteColor));
//                        clickedTextView.setTypeface(clickedTextView.getTypeface(), Typeface.NORMAL);
//
//                    }

                try {
                    CallBack cb = new CallBack(toCallBack, "newDateSelected");
                    cb.invoke(dayModelList.get(position));
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }


                lastDaySelected = dayModelList.get(position);


            }
        });


    }


    @Override
    public int getItemCount() {
        return dayModelList.size();
    }

    public void add(DayDateMonthYearModel DDMYModel) {
        dayModelList.add(DDMYModel);
        notifyItemInserted(dayModelList.size() - 1);
    }

    @Override
    public void onViewAttachedToWindow(MyViewHolder holder) {
        holder.setIsRecyclable(false);
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        holder.setIsRecyclable(false);
        super.onViewDetachedFromWindow(holder);
    }


    public void changeAccent(int color) {
        this.color = color;
        for (int i = 0; i < dateArrayList.size(); i++) {
            dayArrayList.get(i).setTextColor(context.getResources().getColor(color));
            dateArrayList.get(i).setTextColor(context.getResources().getColor(color));
        }
    }

}
