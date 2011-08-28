package org.pasut.pruebas.android.greo.geo;

import java.io.IOException;
import java.util.Locale;

import android.app.Activity;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GeolocalizationActivity extends Activity implements LocationListener{

    private static String TAG = "geo";
    private LocationManager locationManager;
    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
        setContentView(R.layout.main);
        
        locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0, this);
    }

	@Override
	public void onLocationChanged(Location location) {
		TextView text = (TextView)findViewById(R.id.text);
		text.setText(location.getLongitude() + " -- " + location.getLatitude());
		Geocoder encoder = new Geocoder(this,new Locale("es_AR"));
		try {
			System.out.println(encoder.getFromLocation(location.getLatitude(), location.getLongitude(), 3));
		} catch (IOException e) {
			e.printStackTrace();
		}
		locationManager.removeUpdates(this);
	}

	@Override
	public void onProviderDisabled(String provider) {
		System.out.println(provider);
	}

	@Override
	public void onProviderEnabled(String provider) {
		System.out.println(provider);
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		System.out.println(extras);
		
	}

}

