package com.guvi.gt.lataxidriver.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guvi.gt.lataxidriver.R;

public class SoftwareLicenseActivity extends BaseAppCompatNoDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_license);

        getSupportActionBar().setTitle(R.string.label_software_licences);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

    }
}
