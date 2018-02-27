package schedule.busapp.nfta.nftabus;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    Button button;
    TextView getLocation, getAddress;
    private static final int REQUEST_CODE =2;
    String permision = Manifest.permission.ACCESS_FINE_LOCATION;
    FirebaseDatabase fBase;
    DatabaseReference dataRef;
    GPSDriver gps;
    String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            if (ActivityCompat.checkSelfPermission(this, permision) != MockPackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{permision}, REQUEST_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        final  FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference lonLat = database.getReference("Location");
        DatabaseReference addres = database.getReference("Address");
        DatabaseReference admin_area= database.getReference("Admin Area");
        DatabaseReference country = database.getReference("Country");
      //  lonLat.addValueEventListener(this);
       // addres.addValueEventListener(this);
       // admin_area.addValueEventListener(this);
       // country.addValueEventListener(this);

//        lonLat.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//             // val = dataSnapshot.getValue(String.class);
//            //  getLocation = (TextView) findViewById(R.id.lat_lon);
//            //  getLocation.setText(val);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        addres.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                val = dataSnapshot.getValue(String.class);
//                getLocation = (TextView) findViewById(R.id.lat_lon);
//                getLocation.setText(val);
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        gps = new GPSDriver(MainActivity.this);
        getAddress =(TextView) findViewById(R.id.address_display);
        String addressLine =null;
        String adminArea =null;
        String country =null;
        if (gps.locationIsEnable()){
         double retLat = gps.getLatitude();
            double retLon =gps.getLongitude();
            Geocoder geocoder = new Geocoder(getApplicationContext());
            try {
                List<Address> addresses = geocoder.getFromLocation(retLat, retLon, 3);
               addressLine= addresses.get(0).getAddressLine(0);
               adminArea= addresses.get(0).getAdminArea();
               country = addresses.get(0).getCountryName();
               getAddress.setText("Address line: "+addressLine+"\n Admin Area: "+adminArea+"\nCountry: "+country);
               //Intent intent = new Intent(MainActivity.this, MapsActivity.class);
               //intent.putExtra("LOCATION_LAT_LON", retLat +" "+retLon);


            } catch (IOException e) {
                e.printStackTrace();
            }

            getLocation.setText(retLat +", "+retLon);

            // location lat and lon
            fBase = FirebaseDatabase.getInstance();
            dataRef = fBase.getReference("Location");
            dataRef.setValue(retLat +","+retLon);

            //address
            fBase = FirebaseDatabase.getInstance();
            dataRef = fBase.getReference("Address");
            dataRef.setValue(addressLine);

            fBase = FirebaseDatabase.getInstance();
            dataRef = fBase.getReference("Admin Area");
            dataRef.setValue(adminArea);

            fBase = FirebaseDatabase.getInstance();
            dataRef = fBase.getReference("Country");
            dataRef.setValue(country);


          // Intent intent = new Intent(MainActivity.this, MapsActivity.class);
          //  intent.putExtra("LOCATION_LAT_LON", val);
          //  startActivity(intent);
        }else {
           gps.displayLocationSetting();
        }
    }

}