package com.guvi.gt.lataxidriver.net.WSAsyncTasks;

import android.os.AsyncTask;

import org.json.JSONObject;

import com.guvi.gt.lataxidriver.model.BasicBean;
import com.guvi.gt.lataxidriver.net.invokers.TripStartInvoker;

/**
 * Created by Jemsheer K D on 12 June, 2017.
 * Package com.guvi.gt.lataxidriver.net.WSAsyncTasks
 * Project LaTaxiDriver
 */

public class TripStartTask extends AsyncTask<String, Integer, BasicBean> {

    private TripStartTaskListener tripStartTaskListener;

    private JSONObject postData;

    public TripStartTask(JSONObject postData) {
        super();
        this.postData = postData;
    }

    @Override
    protected BasicBean doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        TripStartInvoker tripStartInvoker = new TripStartInvoker(null, postData);
        return tripStartInvoker.invokeTripStartWS();
    }

    @Override
    protected void onPostExecute(BasicBean result) {
        super.onPostExecute(result);
        if (result != null)
            tripStartTaskListener.dataDownloadedSuccessfully(result);
        else
            tripStartTaskListener.dataDownloadFailed();
    }

    public static interface TripStartTaskListener {
        void dataDownloadedSuccessfully(BasicBean basicBean);

        void dataDownloadFailed();
    }

    public TripStartTaskListener getTripStartTaskListener() {
        return tripStartTaskListener;
    }

    public void setTripStartTaskListener(TripStartTaskListener tripStartTaskListener) {
        this.tripStartTaskListener = tripStartTaskListener;
    }
}
