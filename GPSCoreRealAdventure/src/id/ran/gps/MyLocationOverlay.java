package id.ran.gps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.provider.Settings.System;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Projection;

/* Class overload draw method which actually plot a marker,text etc. on Map */
class MyLocationOverlay extends com.google.android.maps.Overlay {
	
	/**
	 * 
	 */
	private searchLocationActivity searchLocationActivity;

	/**
	 * @param searchLocationActivity
	 */
	MyLocationOverlay(searchLocationActivity searchLocationActivity) {
		this.searchLocationActivity = searchLocationActivity;
	}

	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(3);
		paint.setARGB(150, 51, 0, 153);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		
		Path mPath = new Path(); 
	    Projection projection = mapView.getProjection(); 
	    String[] lonT = null;
        String[] latT = null;
        
	    File file = new File("/sdcard/realadventure/sample.gpx");
	    
		try {
			String testHtml = FileUtils.readFileToString(file);

			String[] tds = StringUtils.substringsBetween(testHtml, "<rtept", "</rtept>");

			lonT = new String[tds.length];
			latT = new String[tds.length];
			
			for (int i=0;i<tds.length;i++) {
				String td = tds[i];
				td = "<rtept" + td;
				String attr =  StringUtils.substringBetween(td, "<rtept", ">");
				
				attr = attr.trim();
				attr = attr.replace(" ", "::");

				String[] atArray = attr.split("::");

				String lat = null;
				String lon = null;
				
				String latlon = null;
				
				for(int x=0;x<atArray.length;x++){
					latlon = atArray[x];
					
					if(latlon.contains("lat")){
						lat = StringUtils.substringBetween(latlon, "\"", "\"");
						latT[i] = lat;
					}
					if(latlon.contains("lon")){
						lon = StringUtils.substringBetween(latlon, "\"", "\"");
						lonT[i] = lon;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
        /*Path p = new Path();
        for (int i = 0; i < geoPointsArray.size(); i++) {
	        if (i == geoPointsArray.size() - 1) {
	            break;
	        }
	        Point from = new Point();
	        Point to = new Point();
	        projection.toPixels(geoPointsArray.get(i), from);
	        projection.toPixels(geoPointsArray.get(i + 1), to);
	        p.moveTo(from.x, from.y);
	        p.lineTo(to.x, to.y);
        }
        Paint mPaint = new Paint();
        mPaint.setStyle(Style.FILL_AND_STROKE);
        mPaint.setColor(0x66FF33);
        mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(4);
        canvas.drawPath(p, mPaint);
        super.draw(canvas, mapView, shadow);*/
		
        int l = lonT.length;        
        Point p = new Point();
        Point pprev = new Point();
        GeoPoint gp = null;
        double lon = 0.0;
        double lat = 0.0;
        Point pnext = new Point();
        for (int i = 0; i < l; i++) {
        	lon = Double.parseDouble(lonT[i]);
            lat = Double.parseDouble(latT[i]);            
        	if(i == 0){
	        	gp = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));	        	 
	        	projection.toPixels(gp, p); 
	        	mPath.moveTo(p.x, p.y);
	        	pprev = p;
        	}else{
	        	gp = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
	        	pnext = new Point(); 
	        	projection.toPixels(gp, pnext); 
	        	
	        	if(i != 0)
	        		mPath.moveTo(pprev.x, pprev.y);
	        	
	        	mPath.lineTo(pnext.x,pnext.y); 	        	
	        	pprev = pnext;
        	}        	
        }    
        canvas.drawPath(mPath, paint); 		
		super.draw(canvas, mapView, shadow);
		// Converts lat/lng-Point to OUR coordinates on the screen.
		Paint paintbmp = new Paint();
		paintbmp.setAntiAlias(true);
		paintbmp.setStrokeWidth(4);
		paintbmp.setARGB(255, 0, 0, 0);
		paintbmp.setStyle(Paint.Style.FILL);
		
		Point myScreenCoords = new Point();
		mapView.getProjection().toPixels(this.searchLocationActivity.p, myScreenCoords);
		
		Bitmap bmp = BitmapFactory.decodeResource(this.searchLocationActivity.getResources(), R.drawable.marker);
		
		canvas.drawBitmap(bmp, myScreenCoords.x, myScreenCoords.y, paintbmp);
		canvas.drawText("Max:"+mapView.getMaxZoomLevel() +" Zoom:"+ mapView.getZoomLevel(), myScreenCoords.x, myScreenCoords.y, paintbmp);
		//canvas.drawText("I am here...", myScreenCoords.x, myScreenCoords.y, paint);
		return true;
	}
}
/*GeoPoint gP1 = new GeoPoint((int) (-4.123 * 1E6), (int) (106.999 * 1E6));
GeoPoint gP2 = new GeoPoint((int) (-4.987 * 1E6), (int) (106.123 * 1E6));
GeoPoint gP3 = new GeoPoint((int) (-4.835 * 1E6), (int) (106.536 * 1E6));
Point p1 = new Point(); 
Point p2 = new Point(); 
Point p3 = new Point(); 
projection.toPixels(gP1, p1); 
projection.toPixels(gP2, p2); 
projection.toPixels(gP3, p3); 
mPath.moveTo(p1.x, p1.y); 
mPath.lineTo(p2.x,p2.y); 
mPath.moveTo(p2.x, p2.y); 
mPath.lineTo(p3.x,p3.y); 
mPath.moveTo(p3.x, p3.y); */