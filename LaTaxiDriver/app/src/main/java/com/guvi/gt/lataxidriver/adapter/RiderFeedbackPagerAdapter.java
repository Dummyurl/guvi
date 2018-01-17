package com.guvi.gt.lataxidriver.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.guvi.gt.lataxidriver.fragments.CommentsFragment;
import com.guvi.gt.lataxidriver.fragments.IssuesFragment;

/**
 * Created by Jemsheer K D on 18 May, 2017.
 * Package com.guvi.gt.lataxidriver.adapter
 * Project LaTaxiDriver
 */

public class RiderFeedbackPagerAdapter extends FragmentPagerAdapter {


    private IssuesFragment issuesFragment;
    private CommentsFragment commentsFragment;

    public RiderFeedbackPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (issuesFragment == null) {
                    issuesFragment = new IssuesFragment();
                }
                return issuesFragment;
            case 1:
                if (commentsFragment == null) {
                    commentsFragment = new CommentsFragment();
                }
                return commentsFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}