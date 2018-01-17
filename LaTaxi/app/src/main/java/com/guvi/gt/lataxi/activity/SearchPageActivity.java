package com.guvi.gt.lataxi.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.data.DataBufferUtils;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.guvi.gt.lataxi.R;
import com.guvi.gt.lataxi.adapter.SearchResultsRecyclerAdapter;
import com.guvi.gt.lataxi.config.Config;
import com.guvi.gt.lataxi.listeners.LocationSaveListener;
import com.guvi.gt.lataxi.listeners.SavedLocationListener;
import com.guvi.gt.lataxi.model.LocationBean;
import com.guvi.gt.lataxi.model.PlaceBean;
import com.guvi.gt.lataxi.net.DataManager;
import com.guvi.gt.lataxi.util.AppConstants;

public class SearchPageActivity extends BaseAppCompatNoDrawerActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "search";
    private static final int REQ_SEARCH_HOME = 0;
    private static final int REQ_SEARCH_WORK = 1;
    private int locationSelect = AppConstants.LOCATION_SELECTED_ONITEMCLICK;
    private Toolbar toolbarSearch;
    private EditText etxtSearchPlace;
    private String txtInput;
    private SearchResultsRecyclerAdapter adapterSearch;
    private RecyclerView rvSearchResults;
    private LinearLayout llHome;
    private LinearLayout llWork;
    private View viewLine;
    private TextView txtHome;
    private TextView txtWork;
    private int searchType;
    private GoogleApiClient mGoogleApiClient;
    private int addHome;
    private int addWork;
    private PlaceBean workBean;
    private PlaceBean homeBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        initViews();

        searchType = getIntent().getIntExtra("search_type", 0);

        if (searchType == AppConstants.SEARCH_SOURCE) {

            etxtSearchPlace.setHint("Enter the Source");
        } else if (searchType == AppConstants.SEARCH_DESTINATION) {
            etxtSearchPlace.setHint("Enter the Destination");
        } else {
            etxtSearchPlace.setHint("Enter the Destination");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    public void initViews() {

        setProgressScreenVisibility(true, true);

        fetchSavedLocation();

        coordinatorLayout.removeView(toolbar);

        toolbarSearch = (Toolbar) getLayoutInflater().inflate(R.layout.toolbar_search_page, toolbar);
        coordinatorLayout.addView(toolbarSearch, 0);
        setSupportActionBar(toolbarSearch);

        etxtSearchPlace = (EditText) toolbarSearch.findViewById(R.id.etxt_search_place);

        rvSearchResults = (RecyclerView) findViewById(R.id.rv_search_results);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvSearchResults.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSearchResults.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        txtHome = (TextView) findViewById(R.id.txt_search_page_add_home);
        txtWork = (TextView) findViewById(R.id.txt_search_page_add_work);

        llHome = (LinearLayout) findViewById(R.id.ll_home);
        llWork = (LinearLayout) findViewById(R.id.ll_work);
        viewLine = (View) findViewById(R.id.view_line);


        etxtSearchPlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                txtInput = etxtSearchPlace.getText().toString();

                Log.i(TAG, "onTextChanged: Text" + txtInput);

                txtInputResponse();

                swipeView.setRefreshing(true);
//                populateSearchResults();

                PlaceListTask placesListTask = new PlaceListTask(txtInput);
                placesListTask.setStrAddress(txtInput);
                placesListTask.execute();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQ_SEARCH_HOME && resultCode == RESULT_OK) {

            homeBean = (PlaceBean) data.getSerializableExtra("location");

            txtHome.setText(homeBean.getName());

            Toast.makeText(getApplicationContext(), "Home Location Added",
                    Toast.LENGTH_LONG).show();

            performLocationSave();

        }

        if (requestCode == REQ_SEARCH_WORK && resultCode == RESULT_OK) {

            workBean = (PlaceBean) data.getSerializableExtra("location");

            txtWork.setText(workBean.getName());

            Toast.makeText(getApplicationContext(), "Work Location Added",
                    Toast.LENGTH_LONG).show();

            performLocationSave();

        }
    }

    private void performLocationSave() {

        JSONObject postData = getLocationSaveJSObj();

        DataManager.performLocationSave(postData, new LocationSaveListener() {

            @Override
            public void onLoadCompleted(LocationBean locationBean) {

                swipeView.setRefreshing(false);

                Config.getInstance().setAddHome(homeBean.getName());
                Config.getInstance().setHomeLatitude(homeBean.getLatitude());
                Config.getInstance().setHomeLongitude(homeBean.getLongitude());
                Config.getInstance().setAddWork(workBean.getName());
                Config.getInstance().setWorkLatitude(workBean.getLatitude());
                Config.getInstance().setWorkLongitude(workBean.getLongitude());

            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);

            }
        });
    }

    private JSONObject getLocationSaveJSObj() {
        JSONObject postData = new JSONObject();

        try {
            postData.put("home", homeBean.getName());
            postData.put("work", workBean.getName());
            postData.put("home_latitude", homeBean.getLatitude());
            postData.put("home_longitude", homeBean.getLongitude());
            postData.put("work_latitude", workBean.getLatitude());
            postData.put("work_longitude", workBean.getLongitude());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        PendingResult<AutocompletePredictionBuffer> result = Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient, "infopark", null, null);

        result.setResultCallback(new ResultCallback<AutocompletePredictionBuffer>() {
            @Override
            public void onResult(@NonNull AutocompletePredictionBuffer autocompletePredictions) {
                Log.i(TAG, "onConnected: Result" + autocompletePredictions);


            }
        });
    }


    private class PlaceListTask extends AsyncTask<String, Integer, ArrayList<AutocompletePrediction>> {

        private String strAddress;

        public PlaceListTask(String strAddress) {
            super();
            this.strAddress = strAddress;
        }

        public String getStrAddress() {
            return strAddress;
        }

        public void setStrAddress(String strAddress) {
            this.strAddress = strAddress;
        }

        @Override
        protected ArrayList<AutocompletePrediction> doInBackground(String... params) {

            if (mGoogleApiClient.isConnected()) {
                Log.i(TAG, "Starting autocomplete query for: " + strAddress);

                // Submit the query to the autocomplete API and retrieve a PendingResult that will
                // contain the results when the query completes.
                PendingResult<AutocompletePredictionBuffer> results =
                        Places.GeoDataApi
                                .getAutocompletePredictions(mGoogleApiClient, strAddress, null,
                                        null);

                // This method should have been called off the main UI thread. Block and wait for at most 60s
                // for a result from the API.
                AutocompletePredictionBuffer autocompletePredictions = results
                        .await(60, TimeUnit.SECONDS);

                // Confirm that the query completed successfully, otherwise return null
                final com.google.android.gms.common.api.Status status = autocompletePredictions.getStatus();
                if (!status.isSuccess()) {
//                Toast.makeText(getContext(), "Error contacting API: " + status.toString(),
//                        Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error getting autocomplete prediction API call: " + status.toString());
                    autocompletePredictions.release();
                    return null;
                }

                Log.i(TAG, "Query completed. Received " + autocompletePredictions.getCount()
                        + " predictions.");

                // Freeze the results immutable representation that can be stored safely.
                return DataBufferUtils.freezeAndClose(autocompletePredictions);
            }
            Log.e(TAG, "Google API client is not connected for autocomplete query.");
            return null;

        }

        @Override
        protected void onPostExecute(ArrayList<AutocompletePrediction> result) {
            super.onPostExecute(result);

            if (result != null) {

                populateSearchResults(result);

            } else {
                swipeView.setRefreshing(false);

            }
        }
    }


    private void populateSearchResults(ArrayList<AutocompletePrediction> result) {

        if (adapterSearch == null) {
            adapterSearch = new SearchResultsRecyclerAdapter(this, mGoogleApiClient, result);

            adapterSearch.setSearchResultsRecyclerAdapterListener(new SearchResultsRecyclerAdapter.SearchResultsRecyclerAdapterListener() {

                public PlaceBean placeBean;

                @Override
                public void onItemSelected(Place place) {

                    locationSelect = AppConstants.LOCATION_SELECTED_ONITEMCLICK;

                    placeBean = new PlaceBean();

                    placeBean.setAddress(String.valueOf(place.getAddress()));
                    placeBean.setLatitude(String.valueOf(place.getLatLng().latitude));
                    placeBean.setLongitude(String.valueOf(place.getLatLng().longitude));
                    placeBean.setName((String) place.getName());

                    Intent intent = new Intent();
                    intent.putExtra("bean", placeBean);
                    intent.putExtra("locationSelect", locationSelect);
                    setResult(RESULT_OK, intent);
                    finish();

                }

                @Override
                public void onSnackBarShow(String message) {

                }
            });
            rvSearchResults.setAdapter(adapterSearch);
        } else {

            adapterSearch.setmResultList(result);
            adapterSearch.notifyDataSetChanged();
        }

        setProgressScreenVisibility(false, false);
        swipeView.setRefreshing(false);

    }

    private void txtInputResponse() {

        if (etxtSearchPlace.getText().toString().length() >= 1) {

            llHome.setVisibility(View.GONE);
            llWork.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
        } else {
            llHome.setVisibility(View.VISIBLE);
            llWork.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.VISIBLE);

        }
    }


    public void onAddHomeClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        addHome = AppConstants.ADD_HOME;

        if (Config.getInstance().getAddHome() == null || Config.getInstance().getAddHome().equalsIgnoreCase("null")
                || Config.getInstance().getAddHome().equalsIgnoreCase("")) {
            Intent intent = new Intent(SearchPageActivity.this, SearchHomeWorkActivity.class);
            intent.putExtra("search_type", addHome);
            startActivityForResult(intent, REQ_SEARCH_HOME);

        } else {

            locationSelect = AppConstants.LOCATION_SELECTED_ONHOMECLICK;

            Intent intent = new Intent();

            PlaceBean placeBean = new PlaceBean();
            placeBean.setName(Config.getInstance().getAddHome());
            placeBean.setLatitude(Config.getInstance().getHomeLatitude());
            placeBean.setLongitude(Config.getInstance().getHomeLongitude());
            intent.putExtra("bean", placeBean);
            intent.putExtra("locationSelect", locationSelect);

            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void onAddWorkClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        addWork = AppConstants.ADD_WORK;

        if (Config.getInstance().getAddWork() == null || Config.getInstance().getAddWork().equalsIgnoreCase("null")
                || Config.getInstance().getAddWork().equalsIgnoreCase("")) {
            Intent intent = new Intent(SearchPageActivity.this, SearchHomeWorkActivity.class);
            intent.putExtra("search_type", addWork);
            startActivityForResult(intent, REQ_SEARCH_WORK);
        } else {

            locationSelect = AppConstants.LOCATION_SELECTED_ONWORKCLICK;

            Intent intent = new Intent();

            PlaceBean placeBean = new PlaceBean();
            placeBean.setName(Config.getInstance().getAddWork());
            placeBean.setLatitude(Config.getInstance().getWorkLatitude());
            placeBean.setLongitude(Config.getInstance().getWorkLongitude());
            intent.putExtra("bean", placeBean);
            intent.putExtra("locationSelect", locationSelect);


            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.e(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());

        // TODO(Developer): Check error code and notify the user of error state and resolution.
        Toast.makeText(this,
                "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();

    }

    public void fetchSavedLocation() {

        HashMap<String, String> urlParams = new HashMap<>();

        DataManager.fetchSavedLocation(urlParams, new SavedLocationListener() {

            @Override
            public void onLoadCompleted(LocationBean locationBean) {
                swipeView.setRefreshing(false);
                setLocationBean(locationBean);
                populateSavedLocation(locationBean);

                setProgressScreenVisibility(false, false);
            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);

            }
        });
    }

    private void setLocationBean(LocationBean locationBean) {

        Config.getInstance().setAddHome(locationBean.getHomeLocation());
        Config.getInstance().setHomeLatitude(locationBean.getHomeLatitude());
        Config.getInstance().setHomeLongitude(locationBean.getHomeLongitude());
        Config.getInstance().setAddWork(locationBean.getWorkLocation());
        Config.getInstance().setWorkLatitude(locationBean.getWorkLatitude());
        Config.getInstance().setWorkLongitude(locationBean.getWorkLongitude());

    }

    private void populateSavedLocation(LocationBean locationBean) {

        homeBean = new PlaceBean();
        workBean = new PlaceBean();
        homeBean.setName(locationBean.getHomeLocation());
        homeBean.setLatitude(locationBean.getHomeLatitude());
        homeBean.setLongitude(locationBean.getHomeLongitude());
        workBean.setName(locationBean.getHomeLocation());
        workBean.setLatitude(locationBean.getHomeLatitude());
        workBean.setLongitude(locationBean.getHomeLongitude());

        if (locationBean.getHomeLatitude().equalsIgnoreCase("null")) {
            txtHome.setText("Add Home");
        } else {
            txtHome.setText(locationBean.getHomeLocation());
        }

        if (locationBean.getWorkLatitude().equalsIgnoreCase("null")) {
            txtWork.setText("Add Work");
        } else {
            txtWork.setText(locationBean.getWorkLocation());
        }
    }
}

