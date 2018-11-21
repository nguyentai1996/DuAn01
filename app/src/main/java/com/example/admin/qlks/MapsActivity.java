package com.example.admin.qlks;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Activity implements OnMapReadyCallback{
 GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapFragment mapFragment= (MapFragment)  getFragmentManager().findFragmentById(R.id.MyMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        LatLng HOTEL= new LatLng(21.185842, 105.779279);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HOTEL,13));
        map.addMarker(new MarkerOptions().title("HOTEL FLASH").position(HOTEL));
    }
}
