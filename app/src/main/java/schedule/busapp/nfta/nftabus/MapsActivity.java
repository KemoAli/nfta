package schedule.busapp.nfta.nftabus;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    String str;
    double _latitude;
    double _longitude;
    GPSDriver gps;
    ArrayList<Stops> arr;
    private static final String TAG = "testRun";
    Marker marker, marker2, marker1, marker3;
    HashMap<String, Stops> hashMap = new HashMap<String, Stops>();

//    private static String db_name="db_nfta";

    //  private static String conn_url ="jdbc:mysql://192.168.1.10/nfta_db";
    private static String url = "jdbc:mysql://192.254.250.166:3306/kemokhan_mysql_test"; // ?profileSQL=true";
    //jdbc:mysql://localhost:3306/sakila?profileSQL=true
    private static String password = "kemo_52";//ali_52
    private static String user_name = "kemokhan_kemo";
    private static String pathname = "C:\\Users\\kemok\\eclipse-workspace\\NFTA\\res\\google_transit\\stops.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //  str = getIntent().getExtras().getString("LOCATION_LAT_LON");
       /* Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        String str2[] = str.split(",");
        _latitude = Double.parseDouble(str2[0].trim());
        _longitude = Double.parseDouble(str2[1].trim());*/
        arr = new ArrayList<>();

        gps = new GPSDriver(MapsActivity.this);
        //getAddress =(TextView) findViewById(R.id.address_display);
        if (gps.locationIsEnable()) {
            _latitude = gps.getLatitude();
            _longitude = gps.getLongitude();
            Geocoder geocoder = new Geocoder(getApplicationContext());
            try {
                List<Address> addresses = geocoder.getFromLocation(_latitude, _longitude, 3);
                String addressLine = addresses.get(0).getAddressLine(0);
                String st = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();


            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            gps.displayLocationSetting();
        }
        closestStops();

    }

    public void closestStops() {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;

        try {


            inputStream = getAssets().open("stops.txt");
            inputStreamReader = new InputStreamReader(inputStream);
            br = new BufferedReader(inputStreamReader);
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String val[] = line.split(",");
                Stops s = new Stops(val[0], val[1], val[2], Double.parseDouble(val[4]), Double.parseDouble(val[5]), val[7]);
                arr.add(s);
                hashMap.put(val[0], s);
                line = br.readLine();
            }
            br.close();
            inputStreamReader.close();
            inputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        //  arr =dt.getArr();
        Collections.sort(arr, new SortStopsByLatitudeLongitude(_latitude, _longitude, gps));
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
        //    System.out.print(arr.get(0));

        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        Stops s = arr.get(0);
        LatLng lisbon = new LatLng(s.getStop_lat(), s.getStop_lon());
        Stops s1 = arr.get(1);
        LatLng bailey = new LatLng(s1.getStop_lat(), s1.getStop_lon());

        Stops s2 = arr.get(2);
        LatLng highgate = new LatLng(s2.getStop_lat(), s2.getStop_lon());
        Stops s3 = arr.get(3);
        LatLng rounds = new LatLng(s3.getStop_lat(), s3.getStop_lon());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        marker1 = mMap.addMarker(new MarkerOptions()
                .position(lisbon)
                .title(s.getStop_name()));
        marker1.setTag(s.getStop_id());

        marker2 = mMap.addMarker(new MarkerOptions()
                .position(bailey)
                .title(s1.getStop_name()));
        marker2.setTag(s1.getStop_id());

        marker = mMap.addMarker(new MarkerOptions()
                .position(highgate)
                .title(s2.getStop_name()));
        marker.setTag(s2.getStop_id());

        marker3 = mMap.addMarker(new MarkerOptions()
                .position(rounds)
                .title(s3.getStop_name()));
        marker3.setTag(s3.getStop_id());

        mMap.moveCamera(CameraUpdateFactory.newLatLng(bailey));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(lisbon));
        // mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
        //   mMap.setBuildingsEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f), 5000, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancel() {

            }
        });
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d(TAG, "onMarkerClick: is called");
        Intent intent = new Intent(MapsActivity.this, StopDetails.class);
        DataSource dataSource = new DataSource();
        dataSource.execute("");
        finish();
        intent.putExtra("stop_name", marker.getTitle());
        intent.putExtra("bus_direction", 1);
        intent.putExtra("buses", "13A, 19A");

        startActivity(intent);
        String markerTag = (String) marker.getTag();
        if (marker != null) {
            Toast.makeText(this, marker.getTitle() + " stop_id is " + markerTag, Toast.LENGTH_LONG).show();
        }

        return false;
    }

    private class DataSource extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {

            String success = "";
            Connection db_con = null;
            PreparedStatement preparedStatement = null;
            ResultSet db_result = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");

                db_con = DriverManager.getConnection(url, user_name, password);
                preparedStatement = db_con.prepareStatement("SELECT stop_id, stop_name from stops LIMIT 1");
                db_result= preparedStatement.executeQuery();

            //    System.out.println("stop_id,  stop_name");
                int count=0;
                while (db_result.next()) {
                  //  System.out.println(" " + db_result.getObject(1).toString() + "        "
                      //      + db_result.getObject(2).toString());
                    if (count++ == 5) {
                        break;
                    }
                }
                success ="SUCCESS!!!1";

                    db_con.close();
            } catch (SQLException e) {
                success = "SQLException!!";
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                success ="ClassNotFoundException";
            }
            Log.d(TAG, "doInBackground: " + success);

            return success;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(MapsActivity.this, "Collecting data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String string) {
            Toast.makeText(MapsActivity.this, string, Toast.LENGTH_LONG).show();
        }
    }
}