package com.example.bts;
import net.daum.mf.map.api.MapView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.ViewGroup;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import net.daum.mf.map.api.MapView;
import net.daum.mf.map.api.MapView;

import net.daum.mf.map.api.MapView;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

    }
}

