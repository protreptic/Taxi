package name.peterbukhal.android.taxi.partner.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;
import java.util.List;

import name.peterbukhal.android.taxi.partner.R;
import name.peterbukhal.android.taxi.partner.service.TaximeterService;

/**
 * Created by
 *      petronic on 05.06.16.
 */
public final class OsmFragment extends Fragment {

    public static final String FRAGMENT_TAG = "fragment_tag_osm";

    public static Fragment newInstance() {
        Bundle arguments = new Bundle();

        Fragment fragment = new OsmFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    private MapView mMapView;
    private CheckBox mTrack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup content = (ViewGroup) inflater.inflate(R.layout.f_osm_map, container, false);

        if (content != null) {
            mMapView = (MapView) content.findViewById(R.id.map);
            mMapView.setTileSource(TileSourceFactory.MAPNIK);
            mMapView.setBuiltInZoomControls(true);
            mMapView.setMultiTouchControls(true);

            mTrack = (CheckBox) content.findViewById(R.id.track);
        }

        return content;
    }

    private LocalBroadcastManager mBroadcastManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());

        GeoPoint centerPoint = new GeoPoint(55.67740293D, 37.28444338D);

        IMapController mapController = mMapView.getController();
        mapController.setZoom(14);
        mapController.setCenter(centerPoint);
    }

    private BroadcastReceiver mTaximeterDataReceiver = new BroadcastReceiver() {

        private List<GeoPoint> convert(List<Location> source) {
            List<GeoPoint> result = new ArrayList<>();

            for (Location location : source) {
                result.add(new GeoPoint(location.getLatitude(), location.getLongitude()));
            }

            return result;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!isAdded()) return;

            if (intent.getExtras() != null
                    && intent.getExtras().containsKey(TaximeterService.EXTRA_GPS_TRACK)) {
                List<Location> gpsTrack = intent.getParcelableArrayListExtra(TaximeterService.EXTRA_GPS_TRACK);
                List<Location> networkTrack = intent.getParcelableArrayListExtra(TaximeterService.EXTRA_NETWORK_TRACK);

                Polyline polyline1 = new Polyline(context);
                polyline1.setColor(Color.RED);
                polyline1.setWidth(3.5F);
                polyline1.setPoints(convert(gpsTrack));

                Location startPoint = gpsTrack.get(0);
                Location finishPoint = gpsTrack.get(gpsTrack.size() - 1);

                Marker startMarker = new Marker(mMapView, context);
                startMarker.setPosition(
                        new GeoPoint(startPoint.getLatitude(), startPoint.getLongitude()));
                startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

                Marker finishMarker = new Marker(mMapView, context);
                finishMarker.setPosition(
                        new GeoPoint(finishPoint.getLatitude(), finishPoint.getLongitude()));
                finishMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

                Polyline polyline2 = new Polyline(context);
                polyline2.setColor(Color.BLUE);
                polyline2.setWidth(3.5F);
                polyline2.setPoints(convert(networkTrack));

                mMapView.getOverlays().clear();
                mMapView.getOverlays().add(startMarker);
                mMapView.getOverlays().add(polyline1);
                mMapView.getOverlays().add(polyline2);
                mMapView.getOverlays().add(finishMarker);
                mMapView.invalidate();

                if (mTrack.isChecked()) {
                    mMapView.getController()
                            .animateTo(new GeoPoint(
                                    finishPoint.getLatitude(), finishPoint.getLongitude()));
                    mMapView.getController().setZoom(16);
                }
            }
        }

    };

    @Override
    public void onStart() {
        super.onStart();

        mBroadcastManager.registerReceiver(mTaximeterDataReceiver,
                new IntentFilter(TaximeterService.ACTION_DATA));
    }

    @Override
    public void onStop() {
        super.onStop();

        mBroadcastManager.unregisterReceiver(mTaximeterDataReceiver);
    }
}
