package com.guvi.gt.lataxi.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.guvi.gt.lataxi.R;
import com.guvi.gt.lataxi.config.Config;
import com.guvi.gt.lataxi.model.TripDetailsBean;
import com.guvi.gt.lataxi.model.UserBean;

public class ReceiptPageActivity extends BaseAppCompatNoDrawerActivity {

    private TextView txtBaseFare;
    private TextView txtKilometerFare;
    private TextView txtMinuteFare;
    private TextView txtSubTotalFare;
    private TextView txtPromotionFare;
    private TextView txtTotalFare;
    private TextView txtKilometer;
    private TextView txtMinutes;
    private TripDetailsBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_page);

        bean = (TripDetailsBean) getIntent().getSerializableExtra("bean");

        initVIews();

        populateFareDetails();

        getSupportActionBar().setTitle("Receipt");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

    }

    public void initVIews() {

        txtBaseFare = (TextView) findViewById(R.id.txt_receipt_page_base_fare);
        txtKilometerFare = (TextView) findViewById(R.id.txt_receipt_page_kilometer_fare);
        txtMinuteFare = (TextView) findViewById(R.id.txt_receipt_page_minute_fare);
        txtSubTotalFare = (TextView) findViewById(R.id.txt_receipt_page_subtotal_fare);
        txtPromotionFare = (TextView) findViewById(R.id.txt_receipt_page_promotion_fare);
        txtTotalFare = (TextView) findViewById(R.id.txt_receipt_page_total_fare);
        txtKilometer = (TextView) findViewById(R.id.txt_receipt_page_kilometer);
        txtMinutes = (TextView) findViewById(R.id.txt_receipt_page_minute);

    }

    public void populateFareDetails() {

        txtBaseFare.setText(bean.getBaseFare());
        txtKilometerFare.setText(bean.getKilometerFare());
        txtMinuteFare.setText(bean.getMinutesFare());
        txtSubTotalFare.setText(bean.getSubTotalFare());
        txtPromotionFare.setText(bean.getPromotionFare());
        txtTotalFare.setText(bean.getTotalFare());
//        txtKilometer.setText(bean.getKilometer());
//        txtMinutes.setText(bean.getMinute());
    }
}
