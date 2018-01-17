package com.guvi.gt.lataxidriver.net.WSAsyncTasks;


import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.List;

import com.guvi.gt.lataxidriver.model.BasicBean;
import com.guvi.gt.lataxidriver.net.invokers.ProfilePhotoUploadInvoker;

public class ProfilePhotoUploadTask extends AsyncTask<String, Integer, BasicBean>{

    private final List<String> fileList;
    private JSONObject postData;

    private ProfilePhotoUploadTaskListener profilePhotoUploadTaskListener;

    public ProfilePhotoUploadTask(JSONObject postData, List<String> fileList) {
        super();
        this.postData = postData;
        this.fileList = fileList;
    }

    @Override
    protected BasicBean doInBackground(String... params) {

        System.out.println(">>>>>>>>>doInBackground");
        ProfilePhotoUploadInvoker profilePhotoSaveInvoker = new ProfilePhotoUploadInvoker(null, postData);
        return profilePhotoSaveInvoker.invokeProfilePhotoUploadWS(fileList);
    }

    @Override
    protected void onPostExecute(BasicBean result) {
        super.onPostExecute(result);

        if (result != null)
            profilePhotoUploadTaskListener.dataDownloadedSuccessfully(result);
        else
            profilePhotoUploadTaskListener.dataDownloadFailed();
    }

    public interface ProfilePhotoUploadTaskListener {

        void dataDownloadedSuccessfully(BasicBean basicBean);

        void dataDownloadFailed();
    }

    public ProfilePhotoUploadTaskListener getProfilePhotoUploadTaskListener() {
        return profilePhotoUploadTaskListener;
    }

    public void setProfilePhotoUploadTaskListener(ProfilePhotoUploadTaskListener profilePhotoUploadTaskListener) {
        this.profilePhotoUploadTaskListener = profilePhotoUploadTaskListener;
    }
}
