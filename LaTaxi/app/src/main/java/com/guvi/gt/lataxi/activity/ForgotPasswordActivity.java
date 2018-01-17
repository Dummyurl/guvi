package com.guvi.gt.lataxi.activity;

import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.guvi.gt.lataxi.R;
import com.guvi.gt.lataxi.app.App;
import com.guvi.gt.lataxi.listeners.BasicListener;
import com.guvi.gt.lataxi.listeners.FreeRideListener;
import com.guvi.gt.lataxi.model.BaseBean;
import com.guvi.gt.lataxi.model.BasicBean;
import com.guvi.gt.lataxi.model.FreeRideBean;
import com.guvi.gt.lataxi.net.DataManager;
import com.guvi.gt.lataxi.util.AppConstants;

import static com.guvi.gt.lataxi.R.string.email;

public class ForgotPasswordActivity extends BaseAppCompatNoDrawerActivity {

    private EditText etxtEmail;
    private String email;
    private String emailPattern;
    private View.OnClickListener snackBarRefreshOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        }

        getSupportActionBar().hide();
        swipeView.setPadding(0, 0, 0, 0);

        initViews();

    }

    private void initViews() {

        etxtEmail = (EditText) findViewById(R.id.etxt_email_forgot_password);
    }

    private boolean validateEmail() {

        email = etxtEmail.getText().toString();
        emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (!email.matches(emailPattern)) {
            Snackbar.make(coordinatorLayout, "Enter a Valid Email", Snackbar.LENGTH_LONG)
                    .setAction("Dismiss", snackBarDismissOnClickListener).show();
            return false;
        }
        return true;
    }


    public void performNewPassword() {

        swipeView.setRefreshing(true);
        JSONObject postData = getNewPasswordJSObj();

        DataManager.performNewPassword(postData, new BasicListener() {

            @Override
            public void onLoadCompleted(BasicBean basicBean) {

                swipeView.setRefreshing(false);
                Toast.makeText(getApplicationContext(), "Your New Password is sent to the given Email Address.",
                        Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                finish();

            }
        });
    }

    private JSONObject getNewPasswordJSObj() {

        JSONObject postData = new JSONObject();

        try {

            postData.put("email", etxtEmail);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }

    public void onSubmitButtonClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        if (validateEmail())
            performNewPassword();

    }
}

