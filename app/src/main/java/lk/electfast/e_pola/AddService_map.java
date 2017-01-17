package lk.electfast.e_pola;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddService_map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap2;
    double mlatitude2 = 0;
    double mlongitude2 = 0;
    BitmapDescriptor mBitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.marker);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment2 = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment2.getMapAsync(this);


        mMap2.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng point) {

                mlatitude2=point.latitude;
                mlongitude2=point.longitude;

                LatLng latlng= new LatLng(mlatitude2,mlongitude2);
                mMap2.setMyLocationEnabled(true);
                mMap2.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13));
                mMap2.addMarker(new MarkerOptions()
                        .title("My Location")
                        .position(point))
                        .setIcon(mBitmapDescriptor);

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
