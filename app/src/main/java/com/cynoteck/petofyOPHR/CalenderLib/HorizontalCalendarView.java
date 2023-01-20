package com.cynoteck.petofyOPHR.CalenderLib;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HorizontalCalendarView extends LinearLayout {


    View rootView;
    Context context;
    RecyclerView recyclerView;
    CalAdapter calAdapter;
    ArrayList<DayDateMonthYearModel> currentDayModelList;
    HorizontalPaginationScroller horizontalPaginationScroller;
    Calendar cal;
    Calendar calPrevious;
    DateFormat dateFormat;
    Date date;
    Date datePrevious;
    LinearLayoutManager linearLayoutManager;
    private boolean isLoading = false;

    LinearLayout mainBackground;

    public void init(){
        rootView = inflate(context, R.layout.custom_calender_layout, this);
        recyclerView =  findViewById(R.id.recycler_view);
        mainBackground =  findViewById(R.id.main_background);
        loadNextPage( );

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleIndex = linearLayoutManager.findLastVisibleItemPosition();
                int firstVisibleIndex = linearLayoutManager.findFirstVisibleItemPosition();
                if (dx > 0) {
                    for(int i = firstVisibleIndex ; i<lastVisibleIndex ; i++) {

                        if (currentDayModelList.get(i).month.compareTo(currentDayModelList.get(i+1).month) != 0) {
                            try {
                                CallBack cb = new CallBack(toCallBack, "updateMonthOnScroll");
                                cb.invoke(currentDayModelList.get(i+1));
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (dx < 0) {
                    for(int i = lastVisibleIndex ; i>firstVisibleIndex ; i--) {

                        if (currentDayModelList.get(i).month.compareTo(currentDayModelList.get(i+1).month) != 0) {
                            try {
                                CallBack cb = new CallBack(toCallBack, "updateMonthOnScroll");
                                cb.invoke(currentDayModelList.get(i));
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        linearLayoutManager.scrollToPosition(27);
    }

    public void loadNextPage( ){
        if(calAdapter==null){
            currentDayModelList = new ArrayList<>();
            dateFormat = new SimpleDateFormat("MMMM-EEE-yyyy-MM-dd");
            date = new Date();
            DayDateMonthYearModel currentDayModel=new DayDateMonthYearModel();
            String currentDate=dateFormat.format(date).toString();
            String[] parts = currentDate.split(" ");
            String[] partsDate = currentDate.split("-");
            currentDayModel.month = partsDate[0];
            currentDayModel.date = partsDate[4];
            currentDayModel.day = partsDate[1];
            currentDayModel.year = partsDate[2];
            currentDayModel.monthNumeric = partsDate[3];
            currentDayModel.isToday = true;
            calPrevious = Calendar.getInstance();
            cal = Calendar.getInstance();
            cal.setTime(date);
            calPrevious.setTime(date);


            for (int i = 0; i < 30; i++) {
                calPrevious.add(Calendar.DAY_OF_WEEK, -1);
                String nextDate = dateFormat.format(calPrevious.getTime());
                DayDateMonthYearModel previousDayMode=new DayDateMonthYearModel();
                String[] partsNextDate= nextDate.split("-");
                previousDayMode.month = partsNextDate[0];
                previousDayMode.date = partsNextDate[4];
                previousDayMode.day = partsNextDate[1];
                previousDayMode.year = partsNextDate[2];
                previousDayMode.monthNumeric = partsNextDate[3];
                previousDayMode.isToday= false;
                isLoading = false;
//                calAdapter.addPrevious(currentDayMode);
                currentDayModelList.add(0,previousDayMode);
            }
            currentDayModelList.add(currentDayModel);

            //SimpleDateFormat sdf = new SimpleDateFormat("MMM EEE yyyy-MM-dd");
            for (int i = 0; i < 30; i++) {
                cal.add(Calendar.DAY_OF_WEEK, 1);
                String nextDate = dateFormat.format(cal.getTime());
                DayDateMonthYearModel currentDayMode=new DayDateMonthYearModel();
                String[] partsNextDate= nextDate.split("-");
                currentDayMode.month = partsNextDate[0];
                currentDayMode.date = partsNextDate[4];
                currentDayMode.day = partsNextDate[1];
                currentDayMode.year = partsNextDate[2];
                currentDayMode.monthNumeric = partsNextDate[3];
                currentDayMode.isToday = false;
                currentDayModelList.add(currentDayMode);
                calAdapter = new CalAdapter(context , currentDayModelList);
                linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(calAdapter);
                horizontalPaginationScroller = new HorizontalPaginationScroller(linearLayoutManager) {
                    @Override
                    protected void loadMoreItems() {

                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                calAdapter.notifyDataSetChanged();
                                isLoading = true;
                                loadNextPage();
                            }
                        });

                    }

                    @Override
                    protected void loadMoreItemsOnLeft() {
//                        isLoading = true;
//                        Toast.makeText(context, "Reached Left", Toast.LENGTH_SHORT).show();
//                        loadPreviousPage();
                    }

                    @Override
                    public boolean isLoading() {
                        return isLoading;
                    }
                };
                recyclerView.addOnScrollListener(horizontalPaginationScroller);
                calAdapter.notifyDataSetChanged();

            }
        }else{
            for (int i = 0; i < 30; i++) {
                cal.add(Calendar.DAY_OF_WEEK, 1);
                String nextDate = dateFormat.format(cal.getTime());
                DayDateMonthYearModel currentDayMode=new DayDateMonthYearModel();
                String[] partsNextDate= nextDate.split("-");
                currentDayMode.month = partsNextDate[0];
                currentDayMode.date = partsNextDate[4];
                currentDayMode.day = partsNextDate[1];
                currentDayMode.year = partsNextDate[2];
                currentDayMode.monthNumeric = partsNextDate[3];
                currentDayMode.isToday= false;
                isLoading = false;
                calAdapter.add(currentDayMode);
            }
        }
    }

    public HorizontalCalendarView(Context context) {
        super(context);
        this.context = context;
        init();
    }


    public void setBackgroundColor(int color){
        mainBackground.setBackgroundColor(color);
    }


    Object toCallBack ;

    public void setContext( Object toCallBack){
        this.toCallBack = toCallBack;
        calAdapter.setCallback(toCallBack);
        Date date = new Date();
        //System.out.println("Day 1"+" "+dateFormat.format(date));
        DayDateMonthYearModel currentDayModel=new DayDateMonthYearModel();
        String currentDate=dateFormat.format(date).toString();
        String[] parts = currentDate.split(" ");
        String[] partsDate = currentDate.split("-");
        currentDayModel.month = partsDate[0];
        currentDayModel.date = partsDate[4];
        currentDayModel.day = partsDate[1];
        currentDayModel.year = partsDate[2];
        currentDayModel.monthNumeric = partsDate[3];
        currentDayModel.isToday = true;
        try {
            CallBack cb = new CallBack(toCallBack, "updateMonthOnScroll");
            cb.invoke(currentDayModel);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public HorizontalCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void changeAccent(int color ){
        calAdapter.changeAccent(color );
    }
}
