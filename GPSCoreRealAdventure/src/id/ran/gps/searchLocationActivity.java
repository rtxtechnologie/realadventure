package id.ran.gps;

import java.util.List;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class searchLocationActivity extends MapActivity implements LocationListener {
/** Called when the activity is first created. */
	
	MyLocationOverlay myLocationOverlay = new MyLocationOverlay(this);
	
	EditText		txted			= null;	
	Button			btnSimple		= null;	
	MapView			gMapView		= null;	
	MapController	mc				= null;	
	Drawable		defaultMarker	= null;	
	GeoPoint		p				= null;	
	double			latitude		= 42.438878;
	double			longitude 		= -71.119277;
	double			altitude		= 0;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		/*Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.POWER_HIGH);
		criteria.setAltitudeRequired(true);
		criteria.setBearingRequired(true);
		criteria.setSpeedRequired(true);
		criteria.setCostAllowed(true);*/
		

		LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		//mlocManager.getBestProvider(criteria, true);

		
		LocationListener mlocListener = new MyLocationListener(this);
	
		mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
		
		// Creating TextBox displying Lat, Long
		txted = (EditText) findViewById(R.id.id1);

		String currentLocation = " latitude	: " + latitude + 
				 				 "\n longitude	: " + longitude +
				 				 "\n altitude	: " + altitude;
		
		txted.setText(currentLocation);
		
		// Creating and initializing Map
		gMapView = (MapView) findViewById(R.id.myGMap);
		p = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));

		//gMapView.setSatellite(true);
		gMapView.setSatellite(false);
		gMapView.setTraffic(false);
		
		mc = gMapView.getController();
		mc.setCenter(p);
		mc.setZoom(16);
		
		// Add a location mark
		
		List<Overlay> list = gMapView.getOverlays();
		list.add(myLocationOverlay);
		
		// Adding zoom controls to Map
		@SuppressWarnings("deprecation")
		ZoomControls zoomControls = (ZoomControls) gMapView.getZoomControls();
		zoomControls.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		
		gMapView.addView(zoomControls);
		gMapView.displayZoomControls(true);
		
		// Getting locationManager and reflecting changes over map if distance travel by
		// user is greater than 500m from current location.
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 5, this);
	}
	
	/* This method is called when use position will get changed */
	public void onLocationChanged(Location location) {
		if (location != null) {
			latitude  = location.getLatitude();
			longitude = location.getLongitude();
			altitude  = location.getAltitude();
						
			Toast.makeText(getApplicationContext(), "ALT:"+location.getAltitude() + 
													"|SPD:"+location.getSpeed() +
													"|BEA:"+location.getBearing(),
	                Toast.LENGTH_SHORT).show();

			
			String currentLocation = " latitude	: " + latitude + 
									 "\n longitude	: " + longitude +
									 "\n altitude	: " + altitude;
			
			txted.setText(currentLocation);
			p = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));

			mc.animateTo(p);			
		}
	}
	
	public void onProviderDisabled(String provider) {
		// required for interface, not used
	}
		
	public void onProviderEnabled(String provider) {
		// required for interface, not used
	}
	
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// required for interface, not used
	}
	
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onBackPressed() {
		Log.d("KEYCODE", "Apps exit... bye");
		finish();
		return;
	}
	
	/* User can zoom in/out using keys provided on keypad */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_I) {
			gMapView.getController().setZoom(gMapView.getZoomLevel() + 1);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_O) {
			gMapView.getController().setZoom(gMapView.getZoomLevel() - 1);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_S) {
			gMapView.setSatellite(true);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_T) {
			gMapView.setTraffic(true);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			onBackPressed();
		} else if (keyCode == KeyEvent.KEYCODE_A) {
			
		}
		return false;
	}
}