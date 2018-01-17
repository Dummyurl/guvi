package com.guvi.gt.lataxi.activity;

import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.util.HashMap;

import com.guvi.gt.lataxi.R;
import com.guvi.gt.lataxi.adapter.TripDetailsRecyclerAdapter;
import com.guvi.gt.lataxi.listeners.TripListListener;
import com.guvi.gt.lataxi.model.TripBean;
import com.guvi.gt.lataxi.model.TripListBean;
import com.guvi.gt.lataxi.net.DataManager;

import static com.guvi.gt.lataxi.R.layout.activity_trips;

public class TripsActivity extends BaseAppCompatNoDrawerActivity {

    private Toolbar toolbarTrips;
    private TripBean recentSearchBean;
    private MapView mMapView;
    private TripListBean tripListBean;
    private TripDetailsRecyclerAdapter recyclerAdapter;
    private RecyclerView rvTrips;
    private GoogleMap map;
    private String tripList;

   /* private static GoogleMapOptions options = new GoogleMapOptions()
            .mapType(GoogleMap.MAP_TYPE_NORMAL)
            .compassEnabled(false)
            .rotateGesturesEnabled(false)
            .tiltGesturesEnabled(false)
            .zoomControlsEnabled(false)
            .scrollGesturesEnabled(false)
            .mapToolbarEnabled(false);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_trips);

        initViews();

        getSupportActionBar().setTitle(R.string.trip_list);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        swipeView.setRefreshing(true);

    }

    public void initViews() {

        fetchTripsList();

        rvTrips = (RecyclerView) findViewById(R.id.rv_trips);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTrips.setLayoutManager(layoutManager);
        rvTrips.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

//        mMapView = (MapView) findViewById(R.id.list_map_view);

    }

    public void fetchTripsList() {

        HashMap<String, String> urlParams = new HashMap<>();

        DataManager.fetchTripList(urlParams, new TripListListener() {

            @Override
            public void onLoadCompleted(TripListBean tripListBeanWS) {

                tripListBean = tripListBeanWS;
                populateTripList();
            }

            @Override
            public void onLoadFailed(String errorMsg) {
                Snackbar.make(coordinatorLayout, errorMsg, Snackbar.LENGTH_LONG)
                        .setAction("Dismiss", snackBarDismissOnClickListener).show();
            }

        });
    }

    private void populateTripList() {

        if (recyclerAdapter == null) {
            recyclerAdapter = new TripDetailsRecyclerAdapter(this, tripListBean);

            recyclerAdapter.setTripDetailsRecyclerAdapterListener(new TripDetailsRecyclerAdapter.TripDetailsRecyclerAdapterListener() {

                @Override
                public void onRequestNextPage(boolean isLoadMore, int currentPageNumber) {

                }

                @Override
                public void onItemSelected(TripBean bean) {

                }

                @Override
                public void onRefresh() {

                }

                @Override
                public void onSwipeRefreshingChange(boolean isSwipeResfreshing) {

                }

                @Override
                public void onSnackBarShow(String message) {

                }
            });

            rvTrips.setAdapter(recyclerAdapter);

        } else {

            recyclerAdapter.setTripListBean(tripListBean);
            recyclerAdapter.notifyDataSetChanged();
        }

        if (tripListBean.getTrips() == null || tripListBean.getTrips().isEmpty()) {
            setMessageScreenVisibility(true, "No Trips Taken Yet");
        } else {
            setMessageScreenVisibility(false, "No Trips Taken Yet");

        }

        setProgressScreenVisibility(false, false);
        swipeView.setRefreshing(false);
    }
}
