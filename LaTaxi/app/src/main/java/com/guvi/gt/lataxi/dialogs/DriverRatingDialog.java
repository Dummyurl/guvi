package com.guvi.gt.lataxi.dialogs;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.guvi.gt.lataxi.R;
import com.guvi.gt.lataxi.activity.BaseAppCompatNoDrawerActivity;
import com.guvi.gt.lataxi.app.App;
import com.guvi.gt.lataxi.listeners.PolyPointsListener;
import com.guvi.gt.lataxi.model.FeedbackBean;
import com.guvi.gt.lataxi.model.PolyPointsBean;
import com.guvi.gt.lataxi.model.SuccessBean;
import com.guvi.gt.lataxi.net.DataManager;

import static android.content.ContentValues.TAG;

public class DriverRatingDialog extends BaseAppCompatNoDrawerActivity {

    private Activity mContext;
    private final SuccessBean successBean;
    private final FeedbackBean feedbackBean;
    private DialogDriverRatingListener dialogDriverRatingListener;
    private Dialog dialog;
    private TextView txtRatingPageDate;
    private TextView txtRatingPageTime;
    private TextView txtRatingPageDriverName;
    private TextView txtRatingPageFare;
    private Button btnRatingPageSubmit;
    private ImageView ivRatingPageDriverPhoto;
    private RatingBar rbRatingPageDriverRating;
    private GoogleMap mMap;
    private String sourceLatitude;
    private String sourceLongitude;
    private SupportMapFragment mapFragment;
    private MapView mapView;
    private LatLng newLatLng1;
    private LatLng newLatLng2;
    private String destinationLatitude;
    private String destinationLongitude;
    private PolyPointsBean polyPointsBean;
    private Polyline polyLine;

    public DriverRatingDialog(Activity mContext, SuccessBean successBean, FeedbackBean feedBackBean) {
        this.mContext = mContext;
        this.successBean = successBean;
        this.feedbackBean = feedBackBean;

        Log.i(TAG, "DriverRatingDialog: SUCCESSBEAN : " + new Gson().toJson(successBean));

        driverRatingDialog();


    }


    public void show() {
        dialog.show();
    }

    private void driverRatingDialog() {

        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_driver_rating);
        dialog.setCanceledOnTouchOutside(false);

        txtRatingPageDate = (TextView) dialog.findViewById(R.id.txt_rating_page_date);
        txtRatingPageTime = (TextView) dialog.findViewById(R.id.txt_rating_page_time);
        txtRatingPageDriverName = (TextView) dialog.findViewById(R.id.txt_rating_page_driver_name);
        txtRatingPageFare = (TextView) dialog.findViewById(R.id.txt_rating_page_fare);

        btnRatingPageSubmit = (Button) dialog.findViewById(R.id.btn_rating_page_next);

        ivRatingPageDriverPhoto = (ImageView) dialog.findViewById(R.id.iv_rating_page_driver_photo);

        rbRatingPageDriverRating = (RatingBar) dialog.findViewById(R.id.rb_rating_page_driver_rating);
/*

        mapFragment = (SupportMapFragment) mContext.getSupportFragmentManager()
                .findFragmentById(R.id.fragment_driver_rating_map);
*/

        mapView = (MapView) dialog.findViewById(R.id.mapview_driver_rating_map);
        MapsInitializer.initialize(mContext);
        mapView.onCreate(dialog.onSaveInstanceState());
        mapView.onResume();

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.getUiSettings().setMapToolbarEnabled(false);
//                mMap.setMinZoomPreference(9f);
//                mMap.setMaxZoomPreference(14f);
                onMapLoad();

            }
        });

        populateTipDetails();
    }

    private void onMapLoad() {
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                populateMap();
            }
        });
    }

    private void populateMap() {

        onPlotLatLng(Double.parseDouble(successBean.getSourceLatitude()), Double.parseDouble(successBean.getSourceLongitude()),
                Double.parseDouble(successBean.getDestinationLatitude()), Double.parseDouble(successBean.getDestinationLongitude()));
        mapAutoZoom();

    }

    public void populateTipDetails() {

        sourceLatitude = successBean.getSourceLatitude();
        sourceLongitude = successBean.getSourceLongitude();

        destinationLatitude = successBean.getDestinationLatitude();
        destinationLongitude = successBean.getDestinationLongitude();

        Log.i(TAG, "populateTipDetails: SourceLatitude:" + sourceLatitude);
        Log.i(TAG, "populateTipDetails: SourceLongitude:" + sourceLongitude);


        txtRatingPageDate.setText(App.getUserDateFromUnix(String.valueOf(successBean.getTime())));
        txtRatingPageTime.setText(App.getUserTimeFromUnix(String.valueOf(successBean.getTime())));
        txtRatingPageDriverName.setText(successBean.getDriverName());
        txtRatingPageFare.setText(successBean.getFare());

        Glide.with(mContext.getApplicationContext())
                .load(successBean.getDriverPhoto())
                .apply(new RequestOptions().circleCrop())
                .apply(new RequestOptions().error(R.drawable.ic_dummy_photo))
                .apply(new RequestOptions().fallback(R.drawable.ic_dummy_photo))
                .into(ivRatingPageDriverPhoto);

        btnRatingPageSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogDriverRatingListener.onDriverRatingSubmit(feedbackBean);
                dialog.dismiss();
            }
        });

        rbRatingPageDriverRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                feedbackBean.setRating(rating);

            }
        });

        show();
    }

    private void onPlotLatLng(double sourceLatitude, double sourceLongitude, double destinationLatitude, double destinationLongitude) {

        fetchPolyPoint();

        LatLng newLatLng = null;
        try {
            newLatLng = new LatLng(sourceLatitude, sourceLongitude);
            mMap.addMarker(new MarkerOptions().position(newLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_source_marker)));

            newLatLng = new LatLng(destinationLatitude, destinationLongitude);
            mMap.addMarker(new MarkerOptions().position(newLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_destination_marker)));

        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
    }

    public void mapAutoZoom() {

        newLatLng1 = new LatLng(Double.parseDouble(successBean.getSourceLatitude()), Double.parseDouble(successBean.getSourceLongitude()));
        newLatLng2 = new LatLng(Double.parseDouble(successBean.getDestinationLatitude()), Double.parseDouble(successBean.getDestinationLongitude()));


        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(newLatLng1);
        builder.include(newLatLng2);
        LatLngBounds bounds = builder.build();
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), 0));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));

        Log.i(TAG, "mapAutoZoom: " + bounds.getCenter());

    }

    public interface DialogDriverRatingListener {

        void onDriverRatingSubmit(FeedbackBean feedbackBean);

    }

    public void fetchPolyPoint() {

        HashMap<String, String> urlParams = new HashMap<>();

        urlParams.put("origin", sourceLatitude + "," + sourceLongitude);
        urlParams.put("destination", destinationLatitude + "," + destinationLongitude);
        urlParams.put("mode", "driving");
        urlParams.put("key", "AIzaSyBXZv9SRxxLKwEacQiYAe_YtvOju1ef8og");

        DataManager.fetchPolyPoints(urlParams, new PolyPointsListener() {

            @Override
            public void onLoadCompleted(PolyPointsBean polyPointsBeanWS) {
//                swipeView.setRefreshing(false);

                polyPointsBean = polyPointsBeanWS;
                populatePath();

            }

            @Override
            public void onLoadFailed(String error) {
//                swipeView.setRefreshing(false);
                /*Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_INDEFINITE)
                                        .setAction("Dismiss", snackBarDismissOnClickListener).show();*/
            }
        });
    }

    private void populatePath() {

        List<List<HashMap<String, String>>> routes = polyPointsBean.getRoutes();

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
            polyLineOptions.color((ContextCompat.getColor(mContext.getApplicationContext(), R.color.map_path)));

        }

        polyLine = mMap.addPolyline(polyLineOptions);
    }

    public DialogDriverRatingListener getDialogDriverRatingListener() {
        return dialogDriverRatingListener;
    }

    public void setDialogDriverRatingListener(DialogDriverRatingListener dialogDriverRatingListener) {
        this.dialogDriverRatingListener = dialogDriverRatingListener;

    }
}

