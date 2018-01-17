package com.guvi.gt.lataxidriver.model;

import java.util.List;

/**
 * Created by Jemsheer K D on 16 May, 2017.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxiDriver
 */

public class WeeklyEarningsBean extends BaseBean {

    private int weekOfYear;
    private long weekStart;
    private long weekEnd;
    private int year;
    private String totalPayout;
    private List<DailyEarningBean> dailyEarnings;


    public int getWeekOfYear() {
        return weekOfYear;
    }

    public void setWeekOfYear(int weekOfYear) {
        this.weekOfYear = weekOfYear;
    }

    public long getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(long weekStart) {
        this.weekStart = weekStart;
    }

    public long getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(long weekEnd) {
        this.weekEnd = weekEnd;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTotalPayout() {
        return totalPayout;
    }

    public void setTotalPayout(String totalPayout) {
        this.totalPayout = totalPayout;
    }

    public List<DailyEarningBean> getDailyEarnings() {
        return dailyEarnings;
    }

    public void setDailyEarnings(List<DailyEarningBean> dailyEarnings) {
        this.dailyEarnings = dailyEarnings;
    }
}
