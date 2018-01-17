package com.guvi.gt.lataxi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import com.guvi.gt.lataxi.R;
import com.guvi.gt.lataxi.dialogs.DriverBadRatingDialog;
import com.guvi.gt.lataxi.dialogs.DriverFeedbackDialog;
import com.guvi.gt.lataxi.dialogs.DriverGoodRatingDiialog;
import com.guvi.gt.lataxi.dialogs.DriverRatingDialog;
import com.guvi.gt.lataxi.listeners.DriverRatingListener;
import com.guvi.gt.lataxi.listeners.SuccessListener;
import com.guvi.gt.lataxi.model.DriverRatingBean;
import com.guvi.gt.lataxi.model.FeedbackBean;
import com.guvi.gt.lataxi.model.SuccessBean;
import com.guvi.gt.lataxi.net.DataManager;

public class DriverRatingActivity extends BaseAppCompatNoDrawerActivity {

    private static final String TAG = "";
    private SuccessBean successBean;
    private FeedbackBean feedbackBean;
    private String tripID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_rating);

        initViews();

        tripID = getIntent().getStringExtra("id");

        Log.i(TAG, "onCreate: TripId" + tripID);

        fetchTripCompletionDetails();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(this, SplashActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        if (keyCode == KeyEvent.KEYCODE_MENU) {
            openOptionsMenu();
        }
        return true;
    }


    public void initViews() {

        feedbackBean = new FeedbackBean();

    }

    public void fetchTripCompletionDetails() {

        HashMap<String, String> urlParams = new HashMap<>();

        urlParams.put("id", tripID);

        DataManager.fetchTripCompletionDetails(urlParams, new SuccessListener() {

            @Override
            public void onLoadCompleted(SuccessBean successBeanWS) {
//                populateUserInfo(successBean);

                successBean = successBeanWS;

                driverRatingDialog();
            }

            @Override
            public void onLoadFailed(String errorMsg) {


            }
        });
    }

    public void driverRatingDialog() {

        DriverRatingDialog driverRatingDialog = new DriverRatingDialog(this, successBean, feedbackBean);
        driverRatingDialog.setDialogDriverRatingListener(new DriverRatingDialog.DialogDriverRatingListener() {

            private FeedbackBean feedbackBean;

            @Override
            public void onDriverRatingSubmit(FeedbackBean feedbackBean) {

                this.feedbackBean = feedbackBean;
                driverRatingBadRemarksDialog(feedbackBean, successBean);
            }
        });
        driverRatingDialog.show();
    }

    public void driverRatingBadRemarksDialog(final FeedbackBean feedbackBean, final SuccessBean successBean) {

        DriverBadRatingDialog driverBadRatingDialog = new DriverBadRatingDialog(this, this.successBean, this.feedbackBean);
        driverBadRatingDialog.setDialogBadDriverRatingListener(new DriverBadRatingDialog.DialogBadDriverRatingListener() {

            private FeedbackBean feedbackBean;

            @Override
            public void onDriverBadRatingSubmit(FeedbackBean feedbackBean) {

                this.feedbackBean = feedbackBean;
                driverRatingGoodRemarksDialog(feedbackBean, successBean);
            }
        });
        driverBadRatingDialog.show();

    }

    public void driverRatingGoodRemarksDialog(final FeedbackBean feedbackBean, final SuccessBean successBean) {

        DriverGoodRatingDiialog driverGoodRatingDiialog = new DriverGoodRatingDiialog(this, this.successBean, this.feedbackBean);
        driverGoodRatingDiialog.setDialogGoodDriverRatingListener(new DriverGoodRatingDiialog.DialogGoodDriverRatingListener() {

            private FeedbackBean feedbackBean;

            @Override
            public void onDriverGoodFeedbackSubmit(FeedbackBean feedbackBean) {

                this.feedbackBean = feedbackBean;
                driverRatingFeedBackDialog(feedbackBean, successBean);
            }
        });
        driverGoodRatingDiialog.show();
    }

    public void driverRatingFeedBackDialog(FeedbackBean feedbackBean, SuccessBean successBean) {

        DriverFeedbackDialog driverFeedbackDialog = new DriverFeedbackDialog(this, this.successBean, this.feedbackBean);
        driverFeedbackDialog.setDriverFeedbackDialogListener(new DriverFeedbackDialog.DriverFeedbackDialogListener() {

            private FeedbackBean feedbackBean;

            @Override
            public void onDriverFeedbackSubmit(FeedbackBean feedbackBean) {

                this.feedbackBean = feedbackBean;
                performDriverRating();
                finish();
            }
        });
        driverFeedbackDialog.show();
    }

    public void performDriverRating() {

        JSONObject postData = getFeedbackJSObj();

        DataManager.performDriverRating(postData, new DriverRatingListener() {

            @Override
            public void onLoadCompleted(DriverRatingBean driverRatingBean) {

                swipeView.setRefreshing(false);
                startActivity(new Intent(DriverRatingActivity.this, SplashActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);

            }
        });
    }

    private JSONObject getFeedbackJSObj() {
        JSONObject postData = new JSONObject();
        JSONArray badFeedback = new JSONArray();
        JSONArray goodFeedback = new JSONArray();

        try {
            postData.put("id", tripID);
            postData.put("rating", feedbackBean.getRating());

            for (String str1 : feedbackBean.getBadFeedBackList()) {
                badFeedback.put(str1);
            }
            postData.put("bad_feedback", badFeedback);

            for (String str2 : feedbackBean.getGoodFeedBackList()) {
                goodFeedback.put(str2);
            }
            postData.put("good_feedback", goodFeedback);
            postData.put("driver_feedback", feedbackBean.getFeedBack());

            Log.i(TAG, "getFeedbackJSObj: Rating" + feedbackBean.getRating());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }
}

