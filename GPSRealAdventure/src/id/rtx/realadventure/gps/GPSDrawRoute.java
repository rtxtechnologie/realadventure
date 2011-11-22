package id.rtx.realadventure.gps;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class GPSDrawRoute extends Overlay{
	private GPSRealAdventureActivity gpsaa;

	
	GPSDrawRoute(GPSRealAdventureActivity gpsaa){
		this.gpsaa = gpsaa;
	}
	
	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(2);
		paint.setColor(255);
		paint.setStyle(Paint.Style.STROKE);
		
		Path mPath = new Path(); 
        Projection projection = mapView.getProjection(); 
        GeoPoint gP1 = new GeoPoint((int) (-6.237831 * 1E6), (int) (106.818008 * 1E6));
        GeoPoint gP2 = new GeoPoint((int) (-6.247831 * 1E6), (int) (106.848008 * 1E6));
        
        Point p1 = new Point(); 
        Point p2 = new Point(); 
        
        projection.toPixels(gP1, p1); 
        projection.toPixels(gP2, p2); 
        
        mPath.moveTo(p1.x, p1.y); 
        mPath.lineTo(p2.x,p2.y); 
	        
        canvas.drawPath(mPath, paint); 
		
		super.draw(canvas, mapView, shadow);
		return true;	
	}
}
