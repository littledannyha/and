package com.example.android.hotspotmapper;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView gpsCoordTextView;
    private LocationManager locManager;

    private WifiManager wifiManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpslocator);
        gpsCoordTextView = (TextView) findViewById(R.id.gpsCoordID);
        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {


            @Override
            public void onLocationChanged(Location location) {
                String locString = Double.toString(location.getLatitude()) + ", " + Double.toString(location.getLongitude());
                System.out.println(locString);
                gpsCoordTextView.setText(locString);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                System.out.println("provider enabled: " + provider);

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locManager.requestLocationUpdates(locManager.NETWORK_PROVIDER, 0, 0, locationListener);

        this.wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gpslocator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
