package com.guvi.gt.lataxi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import com.guvi.gt.lataxi.R;
import com.guvi.gt.lataxi.app.App;
import com.guvi.gt.lataxi.config.Config;
import com.guvi.gt.lataxi.dialogs.PopupMessage;
import com.guvi.gt.lataxi.listeners.BasicListener;
import com.guvi.gt.lataxi.listeners.DriverDetailsListener;
import com.guvi.gt.lataxi.listeners.RequestRideListener;
import com.guvi.gt.lataxi.model.BasicBean;
import com.guvi.gt.lataxi.model.DriverBean;
import com.guvi.gt.lataxi.model.FareBean;
import com.guvi.gt.lataxi.model.RequestBean;
import com.guvi.gt.lataxi.net.DataManager;

;

public class RequestingPageActivity extends BaseAppCompatNoDrawerActivity {

    private static final String TAG = "RPA";
    private String source;
    private String destination;
    private TextView txtDummy;
    private String carType;
    private double sourceLatitude;
    private double sourceLongitude;
    private Handler mHandler = new Handler();
    private double destinationLatitude;
    private double destination_longitude;
    private RequestBean requestBean;
    private String id;
    private DriverBean driverBean;
    private FareBean bean;
    private TextView txtRequestingPageTotalFare;
    private String tripID;
    private String requestStatus;
    private boolean isRequestHandled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requesting_page);

        bean = (FareBean) getIntent().getSerializableExtra("bean");

        Bundle extras = getIntent().getExtras();

        source = getIntent().getStringExtra("source");
        destination = getIntent().getStringExtra("destination");
        carType = getIntent().getStringExtra("car_type");

        sourceLatitude = Double.parseDouble(getIntent().getStringExtra("source_latitude"));
        sourceLongitude = Double.parseDouble(getIntent().getStringExtra("source_longitude"));
        destinationLatitude = Double.parseDouble(getIntent().getStringExtra("destination_latitude"));
        destination_longitude = Double.parseDouble(getIntent().getStringExtra("destination_longitude"));

        Log.i(TAG, "onCreate: SourceLatitude" + sourceLatitude);
        Log.i(TAG, "onCreate: SourceLongitude" + sourceLongitude);
        Log.i(TAG, "onCreate: DestinationLatitude" + destinationLatitude);
        Log.i(TAG, "onCreate: DestinationLongitude" + destination_longitude);

        initViews();

        swipeView.setRefreshing(true);

        getSupportActionBar().hide();
        swipeView.setPadding(0, 0, 0, 0);

        performRequestRide();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (App.isNetworkAvailable()) {
                performRequestCancel();
            }
        }

        if (keyCode == KeyEvent.KEYCODE_MENU) {
            openOptionsMenu();
        }
        return true;
    }


    @Override
    protected void onDestroy() {

        if (!isRequestHandled)
            performRequestCancel();

        super.onDestroy();
    }

    private void initViews() {

//        Log.i(TAG, "initViews: Total Fare" + bean.getTotalFare());

        txtRequestingPageTotalFare = (TextView) findViewById(R.id.txt_requesting_page_total_fare);

        txtRequestingPageTotalFare.setText(bean.getTotalFare());
    }

    public void onCancelClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        performRequestCancel();
        isRequestHandled = true;
        mHandler.removeCallbacks(requestTask);
        finish();
    }

    Runnable requestTask = new Runnable() {
        @Override
        public void run() {

            if (!isRequestHandled) {
//                performRequestTriggering();
                fetchRequestStatus();
                mHandler.postDelayed(requestTask, 10000);
            }
        }
    };

    Runnable triggerTask = new Runnable() {
        @Override
        public void run() {

            if (!isRequestHandled) {
                performRequestTriggering();
                mHandler.postDelayed(triggerTask, 60000);
            }
        }
    };

    public void performRequestRide() {

        Log.i(TAG, "performRequestRide: AuthToken" + Config.getInstance().getAuthToken());

        swipeView.setRefreshing(true);
        JSONObject postData = getRequestRideJSObj();

        DataManager.performRequestRide(postData, new RequestRideListener() {

            @Override
            public void onLoadCompleted(RequestBean requestBeanWS) {

                requestBean = requestBeanWS;
                swipeView.setRefreshing(false);
                mHandler.post(triggerTask);
                mHandler.post(requestTask);
                fetchRequestStatus();
            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                finish();

            }
        });
    }

    private JSONObject getRequestRideJSObj() {

        JSONObject postData = new JSONObject();

        try {
            postData.put("source", source);
            postData.put("destination", destination);
            postData.put("car_type", carType);
            postData.put("source_latitude", String.valueOf(sourceLatitude));
            postData.put("source_longitude", String.valueOf(sourceLongitude));
            postData.put("destination_latitude", String.valueOf(destinationLatitude));
            postData.put("destination_longitude", String.valueOf(destination_longitude));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }

    public void performRequestTriggering() {

        swipeView.setRefreshing(true);
        JSONObject postData = getRequestTriggeringJSObj();

        DataManager.performRequestTriggering(postData, new BasicListener() {

            @Override
            public void onLoadCompleted(BasicBean basicBean) {

            }

            @Override
            public void onLoadFailed(String error) {
//                swipeView.setRefreshing(false);

                Toast.makeText(RequestingPageActivity.this, R.string.message_no_driver_available, Toast.LENGTH_SHORT).show();
                if (App.isNetworkAvailable()) {
                    performRequestCancel();
                }
                isRequestHandled = true;
                mHandler.removeCallbacks(requestTask);
                finish();

            }
        });
    }

    private JSONObject getRequestTriggeringJSObj() {

        JSONObject postData = new JSONObject();

        try {
            postData.put("id", requestBean.getID());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }

    public void fetchRequestStatus() {

        swipeView.setRefreshing(true);

        id = requestBean.getID();

        HashMap<String, String> urlParams = new HashMap<>();
        urlParams.put("id", id);

        DataManager.fetchRequestStatus(urlParams, new DriverDetailsListener() {

            @Override
            public void onLoadCompleted(DriverBean driverBeanWS) {

//                swipeView.setRefreshing(false);
                Log.i(TAG, "onLoadCompleted: DriverBean : " + driverBeanWS);

                driverBean = driverBeanWS;

                if (driverBean.getRequestStatus().equalsIgnoreCase("1")) {
                    isRequestHandled = true;
                    mHandler.removeCallbacks(requestTask);
                    Intent intent = new Intent();
                    intent.putExtra("bean", driverBeanWS);
                    setResult(RESULT_OK, intent);
                    finish();

                } else if (driverBean.getRequestStatus().equalsIgnoreCase("2")) {
                    isRequestHandled = true;
                    mHandler.removeCallbacks(requestTask);
                    Intent intent = new Intent();
//                    intent.putExtra("bean", driverBeanWS);
                    setResult(RESULT_CANCELED, intent);
                    finish();

                }
            }

            @Override
            public void onLoadFailed(String error) {
//                swipeView.setRefreshing(false);

                if (App.isNetworkAvailable()) {
                    performRequestCancel();
                }
                isRequestHandled = true;
                mHandler.removeCallbacks(requestTask);
                finish();
            }
        });
    }

    public void performRequestCancel() {

        swipeView.setRefreshing(true);
        JSONObject postData = getRequestCancelJSObj();

        DataManager.performRequestCancel(postData, new BasicListener() {

            @Override
            public void onLoadCompleted(BasicBean basicBean) {
                swipeView.setRefreshing(false);
                finish();
            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                PopupMessage popupMessage = new PopupMessage(RequestingPageActivity.this);
                popupMessage.setPopupActionListener(new PopupMessage.PopupActionListener() {
                    @Override
                    public void actionCompletedSuccessfully(boolean result) {
                        if (App.isNetworkAvailable()) {
                            performRequestCancel();
                        }
                    }

                    @Override
                    public void actionFailed() {

                    }
                });
                popupMessage.show(error, 0, getString(R.string.btn_retry));
            }
        });
    }

    private JSONObject getRequestCancelJSObj() {

        JSONObject postData = new JSONObject();

        try {
            if (requestBean != null) {
                postData.put("request_id", requestBean.getID());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }
}