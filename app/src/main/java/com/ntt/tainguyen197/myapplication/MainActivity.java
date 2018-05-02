package com.ntt.tainguyen197.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * The Class GallarySample.
 */
public class MainActivity extends AppCompatActivity
        //implements LocationListener, OnMapReadyCallback
{

    Button btnContacts, btnPhotos;
    TextView tvname, tvgps, tvbattery, tvwifi;
    LocationManager locationManager;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AnhXa();

        drawerLayout = (DrawerLayout) findViewById(R.id.activitymain);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvmenu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(navigationView);

        /*MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.fragmentmap);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {


            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 3);
            }
            Toast.makeText(this, "KO DC", Toast.LENGTH_SHORT).show();
        } else {
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            onLocationChanged(location);
            Toast.makeText(this, Double.toString(location.getLatitude()) + " " + Double.toString(location.getLongitude()),
                    Toast.LENGTH_SHORT).show();
        }*/
        //HienThiThongTinThietBi();

       /* btnPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPhoto = new Intent(MainActivity.this, GetPhoto.class);
                startActivity(intentPhoto);

            }
        });

        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContact = new Intent(MainActivity.this, GetContact.class);
                startActivity(intentContact);
            }
        });*/

    }

    public void selectedItemsDrawer(MenuItem item) throws IllegalAccessException, InstantiationException {
        android.support.v4.app.Fragment myFragment = null;
        Class fragmentClass;
        switch (item.getItemId()){
            case R.id.menuct:
                fragmentClass = FragmentContact.class;
                break;
            case R.id.menupt:
                fragmentClass = FragmentPhoto.class;
                break;
            case R.id.menudb:
                fragmentClass = FragmentDardboard.class;
                break;
            case R.id.menuvideo:
                fragmentClass = FragmentVideo.class;
                break;
            default:
                fragmentClass = FragmentContact.class;
                break;
        }

        try{
            myFragment = (android.support.v4.app.Fragment) fragmentClass.newInstance();
        }
        catch (Exception e){
            Toast.makeText(this, "Loi", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flmain,myFragment).commit();
        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();

    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                try {
                    selectedItemsDrawer(item);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    /*private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            tvbattery.setText("Pin: " + String.valueOf(level) + "%");
        }
    };

    public boolean getStateGPS() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public String getWifiName(Context context) {
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (manager.isWifiEnabled()) {
            WifiInfo wifiInfo = manager.getConnectionInfo();
            if (wifiInfo != null) {
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                    return wifiInfo.getSSID();
                }
            }
        }
        return null;
    }

    public void HienThiThongTinThietBi() {
        tvname.setText("Tên thiết bị: " + Build.MANUFACTURER + " " + Build.MODEL);
        tvwifi.setText("Wifi: " + getWifiName(MainActivity.this));
        boolean state = getStateGPS();
        if (state)
            tvgps.setText("GSP: On");
        else
            tvgps.setText("GSP: Off");
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }


    void AnhXa() {
        btnContacts = (Button) findViewById(R.id.btnContact);
        btnPhotos = (Button) findViewById(R.id.btnPhoto);

        tvname = (TextView) findViewById(R.id.tvnamedevice);
        tvbattery = (TextView) findViewById(R.id.tvbattery);
        tvwifi = (TextView) findViewById(R.id.tvwifi);
        tvgps = (TextView) findViewById(R.id.tvgps);


    }*/

    /*@Override
    public void onLocationChanged(Location location) {
        double longtitude = location.getLongitude();
        double latitude = location.getLatitude();
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.867, 151.206);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        googleMap.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
    }*/
}