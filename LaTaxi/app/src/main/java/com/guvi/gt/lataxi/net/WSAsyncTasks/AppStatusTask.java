package com.guvi.gt.lataxi.net.WSAsyncTasks;


import android.os.AsyncTask;

import java.util.HashMap;

import com.guvi.gt.lataxi.model.DriverBean;
import com.guvi.gt.lataxi.net.invokers.AppStatusInvoker;

public class AppStatusTask extends AsyncTask<String, Integer, DriverBean> {

    private AppStatusTaskListener appStatusTaskListener;

    private HashMap<String, String> urlParams;

    public AppStatusTask(HashMap<String, String> urlParams) {
        super();
        this.urlParams = urlParams;
    }

    @Override
    protected DriverBean doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        AppStatusInvoker appStatusInvoker = new AppStatusInvoker(urlParams, null);
        return appStatusInvoker.invokeAppStatusWS();
    }

    @Override
    protected void onPostExecute(DriverBean result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        if (result != null)
            appStatusTaskListener.dataDownloadedSuccessfully(result);
        else
            appStatusTaskListener.dataDownloadFailed();
    }

    public static interface AppStatusTaskListener {

        void dataDownloadedSuccessfully(DriverBean driverBean);

        void dataDownloadFailed();
    }

    public AppStatusTaskListener getAppStatusTaskListener() {
        return appStatusTaskListener;
    }

    public void setAppStatusTaskListener(AppStatusTaskListener appStatusTaskListener) {
        this.appStatusTaskListener = appStatusTaskListener;
    }
}
