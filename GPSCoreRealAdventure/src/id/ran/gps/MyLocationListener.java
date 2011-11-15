package id.ran.gps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocationListener implements LocationListener	{
/**
	 * 
	 */
	private final searchLocationActivity searchLocationActivity;


	/**
	 * @param searchLocationActivity
	 */
	MyLocationListener(searchLocationActivity searchLocationActivity) {
		this.searchLocationActivity = searchLocationActivity;
	}


	@Override
	public void onLocationChanged(Location loc)
	{
		loc.getLatitude();
		loc.getLongitude();
		loc.getAltitude();
		loc.getAccuracy();
		this.searchLocationActivity.longitude = loc.getLatitude();
		this.searchLocationActivity.latitude = loc.getLongitude();
		this.searchLocationActivity.altitude = loc.getAltitude();
	}
	
	
	@Override
	public void onProviderDisabled(String provider)
	{
		Toast.makeText( this.searchLocationActivity.getApplicationContext(),
		"RAN Gps Disabled",
		Toast.LENGTH_SHORT ).show();
	}
	
	
	@Override
	public void onProviderEnabled(String provider)
	{
		Toast.makeText( this.searchLocationActivity.getApplicationContext(),
		"RAN Gps Enabled",
		Toast.LENGTH_SHORT).show();
	}
	
	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
	
	}

}/* End of Class MyLocationListener */