package com.guvi.gt.lataxi.net.WSAsyncTasks;


import android.os.AsyncTask;

import org.json.JSONObject;

import com.guvi.gt.lataxi.model.DriverRatingBean;
import com.guvi.gt.lataxi.net.invokers.DriverRatingInvoker;

public class DriverRatingTask extends AsyncTask<String, Integer, DriverRatingBean> {

    private DriverRatingTask.DriverRatingTaskListener driverRatingTaskListener;

    private JSONObject postData;

    public DriverRatingTask(JSONObject postData) {
        super();
        this.postData = postData;
    }

    @Override
    protected DriverRatingBean doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        DriverRatingInvoker driverRatingInvoker = new DriverRatingInvoker(null, postData);
        return driverRatingInvoker.invokeDriverRatingWS();
    }

    @Override
    protected void onPostExecute(DriverRatingBean result) {
        super.onPostExecute(result);
        if (result != null)
            driverRatingTaskListener.dataDownloadedSuccessfully(result);
        else
            driverRatingTaskListener.dataDownloadFailed();
    }

    public static interface DriverRatingTaskListener {

        void dataDownloadedSuccessfully(DriverRatingBean driverRatingBean);

        void dataDownloadFailed();
    }

    public DriverRatingTaskListener getDriverRatingTaskListener() {
        return driverRatingTaskListener;
    }

    public void setDriverRatingTaskListener(DriverRatingTaskListener driverRatingTaskListener) {
        this.driverRatingTaskListener = driverRatingTaskListener;
    }
}
