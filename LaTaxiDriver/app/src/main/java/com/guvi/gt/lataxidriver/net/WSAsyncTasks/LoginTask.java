package com.guvi.gt.lataxidriver.net.WSAsyncTasks;

import android.os.AsyncTask;

import org.json.JSONObject;

import com.guvi.gt.lataxidriver.model.AuthBean;
import com.guvi.gt.lataxidriver.net.invokers.LoginInvoker;

/**
 * Created by Jemsheer K D on 28 April, 2017.
 * Package com.guvi.gt.lataxidriver.net.WSAsyncTasks
 * Project LaTaxiDriver
 */

public class LoginTask extends AsyncTask<String, Integer, AuthBean> {

    private LoginTaskListener loginTaskListener;

    private JSONObject postData;

    public LoginTask(JSONObject postData) {
        super();
        this.postData = postData;
    }

    @Override
    protected AuthBean doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        LoginInvoker loginInvoker = new LoginInvoker(null, postData);
        return loginInvoker.invokeLoginWS();
    }

    @Override
    protected void onPostExecute(AuthBean result) {
        super.onPostExecute(result);
        if (result != null)
            loginTaskListener.dataDownloadedSuccessfully(result);
        else
            loginTaskListener.dataDownloadFailed();
    }

    public static interface LoginTaskListener {
        void dataDownloadedSuccessfully(AuthBean authBean);

        void dataDownloadFailed();
    }

    public LoginTaskListener getLoginTaskListener() {
        return loginTaskListener;
    }

    public void setLoginTaskListener(LoginTaskListener loginTaskListener) {
        this.loginTaskListener = loginTaskListener;
    }
}
