package com.guvi.gt.lataxidriver.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guvi.gt.lataxidriver.R;

public class ProTipsActivity extends BaseAppCompatNoDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_tips);

        initViews();

        getSupportActionBar().setTitle("Pro Tips");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

    }

    private void initViews() {

    }
}
