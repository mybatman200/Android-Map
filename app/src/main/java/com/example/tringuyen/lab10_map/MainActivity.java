package com.example.tringuyen.lab10_map;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.widget.*;
import com.google.android.gms.maps.*;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    final static String LATITUDE = "editText_LATITUDE";
    final static String LONGITUDE = "editText_LONGITUDE";
    final static String LOCATION = "editText_LOCATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.locationInfo);

    }

    public void navigateOnClick(View view){
        String location = editText.getText().toString();

        List<Address> geocodeMatches = null;
        double latitude = 30;    // Lat/Long for ??
        double longitude= -30;

        try {
            geocodeMatches =  new Geocoder(this).getFromLocationName(location, 1);
            if (!geocodeMatches.isEmpty())
            {
                latitude = geocodeMatches.get(0).getLatitude();
                longitude = geocodeMatches.get(0).getLongitude();
            }

            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra(LATITUDE, latitude);
            intent.putExtra(LONGITUDE, longitude);
            intent.putExtra(LOCATION,location);
            startActivity(intent);

        } catch (IOException e) {
            Toast.makeText(this,"Location not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }




    }
}
