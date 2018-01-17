package com.guvi.gt.lataxi.net.WSAsyncTasks;


import android.os.AsyncTask;

import org.json.JSONObject;

import com.guvi.gt.lataxi.model.AuthBean;
import com.guvi.gt.lataxi.model.BasicBean;
import com.guvi.gt.lataxi.net.invokers.OTPResendCodeInvoker;
import com.guvi.gt.lataxi.net.invokers.RegistrationInvoker;

public class OTPResendCodeTask extends AsyncTask<String, Integer, BasicBean> {

    private OTPResendTaskListener otpResendTaskListener;
    private JSONObject postData;

    public OTPResendCodeTask(JSONObject postData) {
        super();
        this.postData = postData;
    }

    @Override
    protected BasicBean doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        OTPResendCodeInvoker otpResendCodeInvoker = new OTPResendCodeInvoker(null, postData);
        return otpResendCodeInvoker.invokeRegistrationWS();
    }

    @Override
    protected void onPostExecute(BasicBean result) {
        super.onPostExecute(result);
        if (result != null)
            otpResendTaskListener.dataDownloadedSuccessfully(result);
        else
            otpResendTaskListener.dataDownloadFailed();
    }

    public static interface OTPResendTaskListener {
        void dataDownloadedSuccessfully(BasicBean basicBean);

        void dataDownloadFailed();
    }

    public OTPResendCodeTask.OTPResendTaskListener getOtpResendTaskListener() {
        return otpResendTaskListener;
    }

    public void setOtpResendTaskListener(OTPResendCodeTask.OTPResendTaskListener otpResendTaskListener) {
        this.otpResendTaskListener = otpResendTaskListener;
    }
}
