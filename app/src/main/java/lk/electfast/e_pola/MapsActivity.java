package lk.electfast.e_pola;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    double mlatitude = 0;
    double mlongitude = 0;
    double clatitude = 0;
    double clongitude = 0;
    private int PROXIMITY_RADIUS = 5000;
    private UiSettings mUiSettings;

    private String jsonData;
    private ProgressDialog pDialog;
    String type_serch;
    ArrayList<HashMap<String, String>> LocationList;
    String sName,sEmail;

    private String createtext="192.168.137.25";
    String JsonResponse;

    String Token;
    private AddListOnMap mATask = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("LogToken", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        Token = pref.getString("key", null);


        System.out.println(Token);


        Intent intent= getIntent();
         type_serch = intent.getStringExtra("id");



        //SupportMapFragment supportMapFragment =
        //   (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mMap = mapFragment.getMap();
        mMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        final Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        final Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
           onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);


        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng point) {

                mlatitude=point.latitude;
                mlongitude=point.longitude;
                mMap.clear();
                LatLng latlng= new LatLng(mlatitude,mlongitude);
                mMap.setMyLocationEnabled(true);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13));
                mMap.addMarker(new MarkerOptions()
                        .title("My Location")
                        .snippet("Use This location for find near Services ")
                        .position(point));

                mATask = new AddListOnMap();
                mATask.execute((Void) null);

            }
        });

        mATask = new AddListOnMap();
        mATask.execute((Void) null);

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
        /*mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));*/



    }

    @Override
    public void onLocationChanged(Location location) {
       // TextView locationTv = (TextView) findViewById(R.id.latlongLocation);

         mlatitude = location.getLatitude();
         mlongitude = location.getLongitude();
        LatLng latLng = new LatLng(mlatitude, mlongitude);
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
        //locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
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
    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }


    public class AddListOnMap extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MapsActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void doInBackground(Void... params) {

            JSONObject Jbody = new JSONObject();

            try {
                Jbody.put("serviceCId", type_serch);
                Jbody.put("lat", mlatitude);
                Jbody.put("lng", mlongitude);


            } catch (Exception e) {
                e.printStackTrace();
            }

            String JsonDATA = String.valueOf(Jbody);

            Log.i("TAG_jDATA", JsonDATA);


            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("http://" + createtext + ":3000/requestService/findServices");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                // is output buffer writter
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("x-access-token",Token);
//set headers and method
                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                writer.write(JsonDATA);
// json data
                writer.close();
                InputStream inputStream = urlConnection.getInputStream();
//input stream
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String inputLine;
                while ((inputLine = reader.readLine()) != null)
                    buffer.append(inputLine + "\n");
                if (buffer.length() == 0) {
                    // Stream was empty. No point in parsing.
                    return null;
                }
                JsonResponse = buffer.toString();


                String JString = type_serch;

                if (JString != null) {

                    try {
                        JSONObject jsonObject = new JSONObject(JString);

                        JSONArray Jlist = jsonObject.getJSONArray("list");

                        for (int i = 0; i < Jlist.length(); i++) {
                            final HashMap<String, String> Location = new HashMap<>();
                            JSONObject c = Jlist.getJSONObject(i);

                            String serviceName = c.getString("serviceName");
                            String email = c.getString("email");
                            String add = c.getString("address");
                            String Lat = c.getString("latitude");
                            String Lag = c.getString("longitude");
                            String status = c.getString("status");

                            clatitude = Double.parseDouble(Lat);
                            clongitude = Double.parseDouble(Lag);
                            sName = serviceName;
                            sEmail = email;


                            Location.put("name", serviceName);
                            Location.put("email", email);
                            Location.put("address", add);
                            Location.put("lat", Lat);
                            Location.put("lag", Lag);
                            Location.put("status", status);

                            LatLng point = new LatLng(clatitude, clatitude);
                            mMap.setMyLocationEnabled(true);
                            //mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
                            mMap.addMarker(new MarkerOptions()
                                    .title(sName)
                                    .snippet(sEmail)
                                    .position(point));


                            LocationList.add(Location);

                        /*runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                System.out.println("thiwanka    dsffff");


                            }
                        });*/
                        }

                    } catch (final JSONException e) {
                        Log.e("TAG", "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }
                }


                return null;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
            @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
                mATask=null;
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();


        }
    }

}
