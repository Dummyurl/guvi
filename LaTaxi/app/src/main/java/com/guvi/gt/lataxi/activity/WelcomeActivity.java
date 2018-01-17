package com.guvi.gt.lataxi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.guvi.gt.lataxi.R;
import com.guvi.gt.lataxi.app.App;
import com.guvi.gt.lataxi.listeners.BasicListener;
import com.guvi.gt.lataxi.model.BasicBean;
import com.guvi.gt.lataxi.net.DataManager;
import com.guvi.gt.lataxi.util.FileOp;

public class WelcomeActivity extends BaseAppCompatNoDrawerActivity {

    //    private AuthConfig authConfig;
    private String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().hide();
        swipeView.setPadding(0, 0, 0, 0);

        initViews();

        if (!checkForReadWritePermissions()) {
            getReadWritePermissions();
        } else {
            new FileOp(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.logout();
    }

    private void initViews() {

       /* AuthConfig.Builder builder = new AuthConfig.Builder();
        new Digits.Builder().withTheme(R.style.AppTheme).build();

        builder.withAuthCallBack(new AuthCallback() {

            @Override
            public void success(DigitsSession session, String phoneNumber) {

                *//*Toast.makeText(getApplicationContext(), "Your Phone Number Was Succesfully Verified",
                        Toast.LENGTH_LONG).show();*//*

                performMobileAvailabilityCheck(phoneNumber);

            }

            @Override
            public void failure(DigitsException exception) {

//                Toast.makeText(getApplicationContext(), "Phone Verification Failed..... Try Again!", Toast.LENGTH_LONG).show();

//                Log.i("Digits", "Sign in with Digits failure", exception);
            }
        });

        authConfig = builder.build();*/
    }

    public void onWelcomeLoginClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        //mVibrator.vibrate(25);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    public void onWelcomeSignUpClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        //mVibrator.vibrate(25);

//        Digits.authenticate(authConfig);
        Intent intent = new Intent(WelcomeActivity.this, RegistrationActivity.class);
        startActivity(intent);

    }

    public void performMobileAvailabilityCheck(final String phoneNumber) {

        setProgressScreenVisibility(true, true);

        swipeView.setRefreshing(true);

        JSONObject postData = getMobileAvailabilityCheckJSObj(phoneNumber);

        DataManager.performMobileAvailabilityCheck(postData, new BasicListener() {

            @Override
            public void onLoadCompleted(BasicBean basicBean) {
                swipeView.setRefreshing(false);

                if (basicBean.isPhoneAvailable()) {

                    setProgressScreenVisibility(false, false);

                    Toast.makeText(getApplicationContext(), "Phone Number Was Successfully Verified",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(WelcomeActivity.this, RegistrationActivity.class);
                    intent.putExtra("phone_number", phoneNumber);
                    startActivity(intent);
                    finish();

                } else {

                    setProgressScreenVisibility(false, false);
                    Toast.makeText(getApplicationContext(), "Phone Number Already Exists",
                            Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();

                setProgressScreenVisibility(false, false);
            }
        });
    }

    private JSONObject getMobileAvailabilityCheckJSObj(String phoneNumber) {

        JSONObject postData = new JSONObject();

        try {

            postData.put("phone", phoneNumber);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }
}
