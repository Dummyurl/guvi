package com.guvi.gt.lataxidriver.net.WSAsyncTasks;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.List;

import com.guvi.gt.lataxidriver.model.BasicBean;
import com.guvi.gt.lataxidriver.net.invokers.ProfileUpdateInvoker;

/**
 * Created by Jemsheer K D on 23 May, 2017.
 * Package com.guvi.gt.lataxidriver.net.WSAsyncTasks
 * Project LaTaxiDriver
 */

public class ProfileUpdateTask extends AsyncTask<String, Integer, BasicBean> {

    private final List<String> fileList;
    private ProfileUpdateTaskListener profileUpdateTaskListener;

    private JSONObject postData;

    public ProfileUpdateTask(JSONObject postData, List<String> fileList) {
        super();
        this.postData = postData;
        this.fileList = fileList;
    }

    @Override
    protected BasicBean doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        ProfileUpdateInvoker profileUpdateInvoker = new ProfileUpdateInvoker(null, postData);
        return profileUpdateInvoker.invokeProfileUpdateWS(fileList);
    }

    @Override
    protected void onPostExecute(BasicBean result) {
        super.onPostExecute(result);
        if (result != null)
            profileUpdateTaskListener.dataDownloadedSuccessfully(result);
        else
            profileUpdateTaskListener.dataDownloadFailed();
    }

    public static interface ProfileUpdateTaskListener {
        void dataDownloadedSuccessfully(BasicBean basicBean);

        void dataDownloadFailed();
    }

    public ProfileUpdateTaskListener getProfileUpdateTaskListener() {
        return profileUpdateTaskListener;
    }

    public void setProfileUpdateTaskListener(ProfileUpdateTaskListener profileUpdateTaskListener) {
        this.profileUpdateTaskListener = profileUpdateTaskListener;
    }
}
