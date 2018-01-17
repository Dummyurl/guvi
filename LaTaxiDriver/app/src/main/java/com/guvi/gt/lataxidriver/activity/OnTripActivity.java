package com.guvi.gt.lataxidriver.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import com.guvi.gt.lataxidriver.R;
import com.guvi.gt.lataxidriver.app.App;
import com.guvi.gt.lataxidriver.config.Config;
import com.guvi.gt.lataxidriver.listeners.BasicListener;
import com.guvi.gt.lataxidriver.listeners.PolyPointListener;
import com.guvi.gt.lataxidriver.listeners.TripDetailsListener;
import com.guvi.gt.lataxidriver.model.BasicBean;
import com.guvi.gt.lataxidriver.model.PathBean;
import com.guvi.gt.lataxidriver.model.PolyPointBean;
import com.guvi.gt.lataxidriver.model.TripBean;
import com.guvi.gt.lataxidriver.net.DataManager;
import com.guvi.gt.lataxidriver.util.AppConstants;

public class OnTripActivity extends BaseAppCompatNoDrawerActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMyLocationButtonClickListener, com.google.android.gms.location.LocationListener,
        android.location.LocationListener {


    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 100;
    private static final String TAG = "OnTripA";
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private GoogleApiClient mGoogleApiClient;
    private static final LocationRequest mLocationRequest = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


    private static GoogleMapOptions options = new GoogleMapOptions()
            .mapType(GoogleMap.MAP_TYPE_NORMAL)
            .compassEnabled(true)
            .rotateGesturesEnabled(true)
            .tiltGesturesEnabled(true)
            .zoomControlsEnabled(true)
            .scrollGesturesEnabled(true)
            .mapToolbarEnabled(true);
    private Bitmap mapPin;
    private ArrayList<Object> plotList;
    private HashMap markerMap;
    private FragmentManager myFragmentManager;
    private SupportMapFragment myMapFragment;
    private ViewGroup.LayoutParams param;
    private GoogleMap mMap;
    private LatLng current;
    private double dLatitude;
    private double dLongitude;
    private LatLng center;
    private boolean isInit;
    private boolean isArrived = false;
    private boolean isTripStarted = false;
    private PolyPointBean polyPointBean;
    private Polyline polyLine;


    private LinearLayout lytBottomSheet;
    private View lytBeforeTrip;
    private ImageView ivBottomSheetProfilePhoto;
    private ImageView ivBeforeTripProfilePhoto;
    private TextView txtBottomSheetCustomerName;
    private TextView txtBottomSheetFare;
    private TextView txtDestination;
    private TextView txtBeforeTripCustomerName;
    private BottomSheetBehavior<LinearLayout> bottomSheetBehavior;
    private LinearLayout llCompleteTrip;
    private LinearLayout llConfirmArrival;
    private LinearLayout llStartTrip;
    private TripBean tripBean;
    private Calendar calStart;
    private Calendar calEnd;
    private ArrayList<PathBean> pathList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_trip);


        if (getIntent().hasExtra("bean"))
            tripBean = (TripBean) getIntent().getSerializableExtra("bean");

        initViews();
        initMap();

        populateTrip();

        getSupportActionBar().setTitle(R.string.title_on_trip);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                toolbar.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                //                mVibrator.vibrate(25);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initViews() {

        lytBottomSheet = (LinearLayout) findViewById(R.id.ll_bottom_sheet_on_trip);
        lytBeforeTrip = findViewById(R.id.lyt_on_trip_before_trip);

        ivBottomSheetProfilePhoto = (ImageView) findViewById(R.id.iv_on_trip_profile_photo);
        ivBeforeTripProfilePhoto = (ImageView) findViewById(R.id.iv_on_trip_before_trip_profile_photo);

        txtBottomSheetCustomerName = (TextView) lytBottomSheet.findViewById(R.id.txt_on_trip_customer_name);
        txtBottomSheetFare = (TextView) lytBottomSheet.findViewById(R.id.txt_on_trip_total_fare);

        txtDestination = (TextView) findViewById(R.id.txt_on_trip_destination);
        txtBeforeTripCustomerName = (TextView) findViewById(R.id.txt_on_trip_before_trip_customer_name);

        llCompleteTrip = (LinearLayout) lytBottomSheet.findViewById(R.id.ll_on_trip_complete_trip);
        llConfirmArrival = (LinearLayout) findViewById(R.id.ll_on_trip_before_trip_confirm_arrival);
        llStartTrip = (LinearLayout) findViewById(R.id.ll_on_trip_before_trip_start);


        lytBottomSheet.setVisibility(View.GONE);
        lytBeforeTrip.setVisibility(View.VISIBLE);

        llConfirmArrival.setVisibility(View.VISIBLE);
        llStartTrip.setVisibility(View.GONE);
        ivBottomSheetProfilePhoto.setVisibility(View.GONE);


        bottomSheetBehavior = BottomSheetBehavior.from(lytBottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                /*if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_DRAGGING
                        || bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_SETTLING) {
                    param = myMapFragment.getView().getLayoutParams();
                    param.height = (int) (height - getStatusBarHeight() - mActionBarHeight - bottomSheet.getHeight());
                    Log.i(TAG, "onSlide: PAram Height : " + param.height);
                    myMapFragment.getView().setLayoutParams(param);
                } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    param = myMapFragment.getView().getLayoutParams();
                    param.height = (int) (height - getStatusBarHeight() - mActionBarHeight - bottomSheet.getHeight());
                    Log.i(TAG, "onSlide: PAram Height : " + param.height);
                    myMapFragment.getView().setLayoutParams(param);
                } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    param = myMapFragment.getView().getLayoutParams();
                    param.height = (int) (height - getStatusBarHeight() - mActionBarHeight - bottomSheet.getHeight());
                    Log.i(TAG, "onSlide: PAram Height : " + param.height);
                    myMapFragment.getView().setLayoutParams(param);
                }*/
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i(TAG, "onSlide: offset : " + slideOffset);
//                mapFragmentView.animate().scaleX(1 - slideOffset).scaleY(1 - slideOffset).setDuration(0).start();
            }
        });


    }

    private void initMap() {

        plotList = new ArrayList<>();
        markerMap = new HashMap();
/*
        frame = BitmapFactory.decodeResource(getResources(), R.drawable.bg_map_pin);
        mapPin = BitmapFactory.decodeResource(getResources(), R.drawable.ic_hunt_green);
        mapPinRed = BitmapFactory.decodeResource(getResources(), R.drawable.ic_hunt_red);
*/

        mapPin = BitmapFactory.decodeResource(getResources(), R.drawable.circle_app);

        myFragmentManager = getSupportFragmentManager();
        myMapFragment = (SupportMapFragment) myFragmentManager.findFragmentById(R.id.fragment_map);
        //	mMap = myMapFragment.getMap();

        /*param = myMapFragment.getView().getLayoutParams();
        param.height = (int) (height - getStatusBarHeight() - mActionBarHeight - (80 * px));
        Log.i(TAG, "onSlide: Param Height : " + param.height);
        myMapFragment.getView().setLayoutParams(param);
*/
        myMapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap mapTemp) {

                Log.i(TAG, "onMapReady: MAP IS LOADED");

                mMap = mapTemp;
                mMap.setPadding(0, 0/*(int) ((100 * px) + mActionBarHeight + getStatusBarHeight())*/, 0, (int) (60 * px));
                initMapOnLoad();
                if (Config.getInstance().getCurrentLatitude() != null
                        && !Config.getInstance().getCurrentLatitude().equals("")
                        && Config.getInstance().getCurrentLongitude() != null
                        && !Config.getInstance().getCurrentLongitude().equals("")) {
                    populateMap();
                }

            }
        });

    }

    private void initMapOnLoad() {

        current = new LatLng(0.0, 0.0);

        if (Config.getInstance().getCurrentLatitude() != null && !Config.getInstance().getCurrentLatitude().equals("")
                && Config.getInstance().getCurrentLongitude() != null && !Config.getInstance().getCurrentLongitude().equals("")) {
            dLatitude = Double.parseDouble(Config.getInstance().getCurrentLatitude());
            dLongitude = Double.parseDouble(Config.getInstance().getCurrentLongitude());
            current = new LatLng(dLatitude, dLongitude);
        }

        //	mMap=mapView.getMap();
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (!checkForLocationPermissions()) {
                getLocationPermissions();
            }
            checkLocationSettingsStatus();
        }
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        //myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //myMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);


/*        if (placesBean != null) {
            dLatitude = Double.parseDouble(placesBean.getLatitude());
            dLongitude = Double.parseDouble(placesBean.getLongitude());

            newLatLng = new LatLng(dLatitude, dLongitude);

            mMap.addMarker(new MarkerOptions()
                    .position(newLatLng)
                    .title(placesBean.getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 12));
        }*/

        center = mMap.getCameraPosition().target;

       /* mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng arg0) {
                if (llMapDetails.isShown()) {
                    llMapDetails.setVisibility(View.GONE);
                }

            }
        });*/
/*
        try {
            populatePlotList();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    private void populateTrip() {

        txtDestination.setText(tripBean.getDestinationLocation());
        txtBeforeTripCustomerName.setText(tripBean.getCustomerName());
        txtBottomSheetCustomerName.setText(tripBean.getCustomerName());

        Glide.with(getApplicationContext())
                .load(tripBean.getCustomerPhoto())
                .apply(new RequestOptions().circleCrop())
                .apply(new RequestOptions().error(R.drawable.ic_profile_photo_default))
                .into(ivBeforeTripProfilePhoto);

        Glide.with(getApplicationContext())
                .load(tripBean.getCustomerPhoto())
                .apply(new RequestOptions().circleCrop())
                .apply(new RequestOptions().error(R.drawable.ic_profile_photo_default))
                .into(ivBottomSheetProfilePhoto);

        setCurrentDriverStatus(tripBean.getDriverStatus());

    }

    private void setCurrentDriverStatus(int driverStatus) {

        switch (driverStatus) {
            case AppConstants.DRIVER_STATUS_ACCEPTED:
                llConfirmArrival.setVisibility(View.VISIBLE);
                llStartTrip.setVisibility(View.GONE);
                lytBeforeTrip.setVisibility(View.VISIBLE);
                lytBottomSheet.setVisibility(View.GONE);
                ivBottomSheetProfilePhoto.setVisibility(View.GONE);
                break;
            case AppConstants.DRIVER_STATUS_ARRIVED:
                llConfirmArrival.setVisibility(View.GONE);
                llStartTrip.setVisibility(View.VISIBLE);
                lytBeforeTrip.setVisibility(View.VISIBLE);
                lytBottomSheet.setVisibility(View.GONE);
                ivBottomSheetProfilePhoto.setVisibility(View.GONE);
                break;
            case AppConstants.DRIVER_STATUS_STARTED:
                llConfirmArrival.setVisibility(View.GONE);
                llStartTrip.setVisibility(View.VISIBLE);
                lytBeforeTrip.setVisibility(View.GONE);
                lytBottomSheet.setVisibility(View.VISIBLE);
                ivBottomSheetProfilePhoto.setVisibility(View.VISIBLE);
                break;
            case AppConstants.DRIVER_STATUS_ENDED:
                startActivity(new Intent(OnTripActivity.this, TripSummaryActivity.class)
                        .putExtra("trip_id", tripBean.getId()));
                break;
            case AppConstants.DRIVER_STATUS_CASH_ACCEPTED:
                startActivity(new Intent(OnTripActivity.this, TripSummaryActivity.class)
                        .putExtra("trip_id", tripBean.getId()));
                break;
            default:

                break;


        }

    }

    public void onOnTripBottomSheetClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        //mVibrator.vibrate(25);

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    public void onOnTripCompleteTripClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        //mVibrator.vibrate(25);

        if (App.isNetworkAvailable()) {
            performTripCompletion();
        } else {
            Snackbar.make(coordinatorLayout, AppConstants.NO_NETWORK_AVAILABLE, Snackbar.LENGTH_LONG)
                    .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
        }

    }

    public void onOnTripConfirmArrivalClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        //mVibrator.vibrate(25);

        if (App.isNetworkAvailable()) {
            performArrivalConfirmation();
        } else {
            Snackbar.make(coordinatorLayout, AppConstants.NO_NETWORK_AVAILABLE, Snackbar.LENGTH_LONG)
                    .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
        }

    }

    public void onOnTripStartTripClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        //mVibrator.vibrate(25);

        if (App.isNetworkAvailable()) {
            performTripStart();
        } else {
            Snackbar.make(coordinatorLayout, AppConstants.NO_NETWORK_AVAILABLE, Snackbar.LENGTH_LONG)
                    .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
        }

    }

    public void onOnTripNavigateClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        //mVibrator.vibrate(25);

        Intent navigation;
        if (isArrived) {
            navigation = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=" +
                    String.valueOf(Config.getInstance().getCurrentLatitude()) + "," +
                    String.valueOf(Config.getInstance().getCurrentLongitude()) + "&daddr=" +
                    String.valueOf(tripBean.getDestinationLatitude())
                    + "," + String.valueOf(tripBean.getDestinationLongitude())));
        } else {
            navigation = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=" +
                    String.valueOf(Config.getInstance().getCurrentLatitude()) + "," +
                    String.valueOf(Config.getInstance().getCurrentLongitude()) + "&daddr=" +
                    String.valueOf(tripBean.getSourceLatitude())
                    + "," + String.valueOf(tripBean.getSourceLongitude())));
        }


        navigation.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(navigation);

    }

    private void performTripStart() {
        swipeView.setRefreshing(true);

        JSONObject postData = getTripStartJSObj();

        DataManager.performTripStart(postData, new BasicListener() {
            @Override
            public void onLoadCompleted(BasicBean basicBean) {
                swipeView.setRefreshing(false);

                calStart = Calendar.getInstance();
                calStart.setTimeZone(TimeZone.getTimeZone("UTC"));

                pathList = new ArrayList<>();
                if (Config.getInstance().getCurrentLongitude() != null
                        && !Config.getInstance().getCurrentLongitude().equalsIgnoreCase("")
                        && Config.getInstance().getCurrentLatitude() != null
                        && !Config.getInstance().getCurrentLatitude().equalsIgnoreCase("")) {
                    PathBean pathBean = new PathBean();
                    pathBean.setLatitude(Config.getInstance().getCurrentLatitude());
                    pathBean.setLongitude(Config.getInstance().getCurrentLongitude());
                    pathBean.setIndex(0);
                    pathBean.setTime(calStart.getTimeInMillis() / 1000);
                    pathList.add(pathBean);
                }

                lytBeforeTrip.setVisibility(View.GONE);
                lytBottomSheet.setVisibility(View.VISIBLE);

                Snackbar.make(coordinatorLayout, R.string.message_trip_started, Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
            }
        });


    }

    private JSONObject getTripStartJSObj() {
        JSONObject postData = new JSONObject();

        try {
            postData.put("trip_id", tripBean.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }

    private void performArrivalConfirmation() {
        swipeView.setRefreshing(true);

        JSONObject postData = getArrivalConfirmationJSObj();

        DataManager.performArrivalConfirmation(postData, new BasicListener() {
            @Override
            public void onLoadCompleted(BasicBean basicBean) {
                swipeView.setRefreshing(false);
                Snackbar.make(coordinatorLayout, R.string.message_arrival_confirmed, Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();

                llConfirmArrival.setVisibility(View.GONE);
                llStartTrip.setVisibility(View.VISIBLE);
                isArrived = true;

            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();

            }
        });
    }

    private JSONObject getArrivalConfirmationJSObj() {
        JSONObject postData = new JSONObject();

        try {
            postData.put("trip_id", tripBean.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }


    private void performTripCompletion() {
        swipeView.setRefreshing(true);

        calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("UTC"));

        if (Config.getInstance().getCurrentLongitude() != null
                && !Config.getInstance().getCurrentLongitude().equalsIgnoreCase("")
                && Config.getInstance().getCurrentLatitude() != null
                && !Config.getInstance().getCurrentLatitude().equalsIgnoreCase("")) {
            PathBean pathBean = new PathBean();
            pathBean.setLatitude(Config.getInstance().getCurrentLatitude());
            pathBean.setLongitude(Config.getInstance().getCurrentLongitude());
            pathBean.setIndex(pathList.size());
            pathBean.setTime(calEnd.getTimeInMillis() / 1000);
            pathList.add(pathBean);
        }

        JSONObject postData = getTripCompletionJSObj();

        DataManager.performTripCompletion(postData, new TripDetailsListener() {
            @Override
            public void onLoadCompleted(TripBean tripBean) {
                swipeView.setRefreshing(false);

                Toast.makeText(OnTripActivity.this, R.string.message_trip_ended_please_collect_cash, Toast.LENGTH_LONG).show();
                startActivity(new Intent(OnTripActivity.this, TripSummaryActivity.class)
                        .putExtra("trip_id", tripBean.getId()));

            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
            }
        });

    }

    private JSONObject getTripCompletionJSObj() {
        JSONObject postData = new JSONObject();

        try {
            postData.put("trip_id", tripBean.getId());
            postData.put("start_time", calStart.getTimeInMillis() / 1000);
            postData.put("end_time", calEnd.getTimeInMillis() / 1000);

            JSONArray pathArray = new JSONArray();
            if (pathList != null) {
                for (PathBean bean : pathList) {
                    JSONObject path = new JSONObject();

                    try {
                        path.put("index", bean.getIndex());
                        path.put("time", bean.getTime());
                        path.put("latitude", bean.getLatitude());
                        path.put("longitude", bean.getLongitude());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pathArray.put(path);
                }
            }

            postData.put("path", pathArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }

    private void populateMap() {
        mMap.clear();
        if (isArrived) {
            onPlotLatLng(Double.parseDouble(Config.getInstance().getCurrentLatitude()),
                    Double.parseDouble(Config.getInstance().getCurrentLongitude()),
                    tripBean.getDDestinationLatitude(), tripBean.getDDestinationLongitude());
        } else {
            onPlotLatLng(Double.parseDouble(Config.getInstance().getCurrentLatitude()),
                    Double.parseDouble(Config.getInstance().getCurrentLongitude()),
                    tripBean.getDSourceLatitude(), tripBean.getDSourceLongitude());
        }
        mapAutoZoom();
    }

    private void onPlotLatLng(double sourceLatitude, double sourceLongitude, double destinationLatitude, double destinationLongitude) {

        fetchPolyPoint();

        LatLng newLatLng = null;
        try {
            newLatLng = new LatLng(sourceLatitude, sourceLongitude);

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 18));

//            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//            mMap.addMarker(new MarkerOptions().position(newLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_driver_location)));


            newLatLng = new LatLng(destinationLatitude, destinationLongitude);
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 11));
            mMap.addMarker(new MarkerOptions().position(newLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_destination)));

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void mapAutoZoom() {

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        if (isArrived) {
            builder.include(new LatLng(Double.parseDouble(Config.getInstance().getCurrentLatitude()),
                    Double.parseDouble(Config.getInstance().getCurrentLongitude())));
            builder.include(tripBean.getDestinationLatLng());
        } else {
            builder.include(new LatLng(Double.parseDouble(Config.getInstance().getCurrentLatitude()),
                    Double.parseDouble(Config.getInstance().getCurrentLongitude())));
            builder.include(tripBean.getSourceLatLng());
        }

        LatLngBounds bounds = builder.build();
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, (int) (50 * px)));

    }

    public void fetchPolyPoint() {

        HashMap<String, String> urlParams = new HashMap<>();

        urlParams.put("origin", Config.getInstance().getCurrentLatitude() + "," + Config.getInstance().getCurrentLongitude());
        if (isArrived) {
            urlParams.put("destination", tripBean.getDestinationLatitude() + "," + tripBean.getDDestinationLongitude());
        } else {
            urlParams.put("destination", tripBean.getSourceLatitude() + "," + tripBean.getSourceLongitude());
        }
        urlParams.put("mode", "driving");
        urlParams.put("key", getString(R.string.browser_api_key));

        DataManager.fetchPolyPoints(urlParams, new PolyPointListener() {

            @Override
            public void onLoadCompleted(PolyPointBean polyPointBeanWS) {
                swipeView.setRefreshing(false);

                polyPointBean = polyPointBeanWS;
                populatePath();

            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
            }
        });
    }

    private void populatePath() {

        List<List<HashMap<String, String>>> routes = polyPointBean.getRoutes();

        ArrayList<LatLng> points = null;
        PolylineOptions polyLineOptions = null;

        // traversing through routes
        for (int i = 0; i < routes.size(); i++) {
            points = new ArrayList<LatLng>();
            polyLineOptions = new PolylineOptions();
            List path = routes.get(i);

            for (int j = 0; j < path.size(); j++) {
                HashMap point = (HashMap) path.get(j);

                double lat = Double.parseDouble((String) point.get("lat"));
                double lng = Double.parseDouble((String) point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                points.add(position);
            }

            polyLineOptions.addAll(points);
            polyLineOptions.width(8);
            polyLineOptions.color(ContextCompat.getColor(getApplicationContext(), R.color.map_path));

        }

        polyLine = mMap.addPolyline(polyLineOptions);
    }

    public void setUpLocationClientIfNeeded() {
        if (App.getInstance().getGoogleApiClient() == null) {

            mGoogleApiClient = new GoogleApiClient.Builder(App.getInstance().getApplicationContext())
                    .addApi(LocationServices.API)
                    .enableAutoManage(this, this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            App.getInstance().setGoogleApiClient(mGoogleApiClient);
        } else {
            mGoogleApiClient = App.getInstance().getGoogleApiClient();
        }

        if (isInit) {
            mGoogleApiClient.registerConnectionCallbacks(this);
            mGoogleApiClient.registerConnectionFailedListener(this);
            isInit = false;
        }
    }

    public void getCurrentLocation() {
        setUpLocationClientIfNeeded();
        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
        if (ActivityCompat.checkSelfPermission(App.getInstance(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(App.getInstance(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (!checkForLocationPermissions()) {
                getLocationPermissions();
            }
            checkLocationSettingsStatus();
        } else {
            if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {

                if (LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient) != null) {
                    Config.getInstance().setCurrentLatitude(""
                            + LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient).getLatitude());
                    Config.getInstance().setCurrentLongitude(""
                            + LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient).getLongitude());
                }
            /*else{
                System.out.println("Last Location : " + mockLocation);
				currentLatitude = ""+mockLocation.getLatitude();
				currentLongitude = ""+mockLocation.getLongitude();
			}*/
            }
        }

        locationManager = (LocationManager) App.getInstance().getSystemService(Context.LOCATION_SERVICE);
        if (hasLocationPermissions) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            } else {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
            }
        }
       /* if ((Config.getInstance().getCurrentLatitude() == null
                || Config.getInstance().getCurrentLongitude() == null)
                || (Config.getInstance().getCurrentLatitude().equals("")
                || Config.getInstance().getCurrentLatitude().equals(""))) {
            //Toast.makeText(MapActivity.this, "Retrieving Current Location...", Toast.LENGTH_SHORT).show();
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            if (hasLocationPermissions) {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                } else {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                }
            }

            //			mHandler.postDelayed(periodicTask, 3000);
        } else {
            if (mGoogleApiClient != null) {
                mGoogleApiClient.disconnect();
            }
        }*/
    }

    @Override
    public void onLocationChanged(Location location) {
/*        if ((Config.getInstance().getCurrentLatitude() == null || Config.getInstance().getCurrentLongitude() == null)
                || (Config.getInstance().getCurrentLatitude().equals("") || Config.getInstance().getCurrentLatitude().equals(""))) {
            Config.getInstance().setCurrentLatitude("" + location.getLatitude());
            Config.getInstance().setCurrentLongitude("" + location.getLongitude());
            moveToCurrentLocation();
        } else {
            if (mGoogleApiClient != null) {
                mGoogleApiClient.disconnect();
            }
        }*/
        Config.getInstance().setCurrentLatitude("" + location.getLatitude());
        Config.getInstance().setCurrentLongitude("" + location.getLongitude());

        Log.i(TAG, "onLocationChanged: LATITUDE : " + location.getLatitude());
        Log.i(TAG, "onLocationChanged: LONGITUDE : " + location.getLongitude());


        if (isTripStarted) {
            if (pathList == null) {
                pathList = new ArrayList<>();
            }
            PathBean bean = new PathBean();
            bean.setIndex(pathList.size());
            bean.setTime(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis() / 1000);
            bean.setLatitude(Config.getInstance().getCurrentLatitude());
            bean.setLongitude(Config.getInstance().getCurrentLongitude());

            pathList.add(bean);

        }

        populateMap();

    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onConnectionFailed(ConnectionResult status) {
    }

    @Override
    public void onConnected(Bundle arg0) {

        try {
            if (ActivityCompat.checkSelfPermission(App.getInstance().getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(App.getInstance().getApplicationContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (!checkForLocationPermissions()) {
                    getLocationPermissions();
                }
                checkLocationSettingsStatus();
            } else {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                if (LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient) != null) {
                    Config.getInstance().setCurrentLatitude(""
                            + LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient).getLatitude());
                    Config.getInstance().setCurrentLongitude(""
                            + LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient).getLongitude());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //	mGoogleApiClient.requestLocationUpdates(mLocationRequest,MapActivity.this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }


    @Override
    public void onProviderDisabled(String provider) {

    }


    @Override
    public void onConnectionSuspended(int arg0) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //            onBackPressed();
        }
        if (keyCode == KeyEvent.KEYCODE_APP_SWITCH) {
            //            onBackPressed();
        }
        if (keyCode == KeyEvent.KEYCODE_VOICE_ASSIST) {
            //            onBackPressed();
        }

        if (keyCode == KeyEvent.KEYCODE_HOME) {
            //            onBackPressed();
        }

        if (keyCode == KeyEvent.KEYCODE_MENU) {
            openOptionsMenu();
        }
        return true;
    }

}
