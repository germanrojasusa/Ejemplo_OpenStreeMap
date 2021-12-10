package com.example.openstreemapexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {
    private MapView myOpenMapView;
    private MapController myMapController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().setUserAgentValue(this.getPackageName());
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.v(null, "Permission is granted");
        } else {
            Log.v(null, "Permission is revoked");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        setContentView(R.layout.activity_main);

        GeoPoint ramiriqui = new GeoPoint(5.401018,-73.335909);
        myOpenMapView = (MapView) findViewById(R.id.openmapview);

        myOpenMapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setCenter(ramiriqui);
        myMapController.setZoom(16);
        addMarker(ramiriqui, "Ramiriquí");
        myOpenMapView.setMultiTouchControls(true);


    }
    public void addMarker (GeoPoint geoPoint,String name){
        Marker marker = new Marker(myOpenMapView);
        marker.setPosition(geoPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
        marker.setIcon(getResources().getDrawable(android.R.drawable.star_on, null));
        myOpenMapView.getOverlays().add(marker);
    }

}