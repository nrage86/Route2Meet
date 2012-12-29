package com.simov.trabalho2.route2meet;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Menu;

public class LocationOverlayAdapter extends ItemizedOverlay<OverlayItem> {
	
	private List<GeoPoint> locations;
	private List<GeoPoint> specialLocations;
	
	public LocationOverlayAdapter(Drawable marker) {
		super( boundCenterBottom(marker) );
	}
	
	public void setItems(ArrayList<GeoPoint> items,ArrayList<GeoPoint> items2) {
		locations = items;
		specialLocations = items2;
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return new OverlayItem(specialLocations.get(i), null, null);
	}
	
	@Override
	public int size() {
		return specialLocations.size();
	}
	
	@Override
	protected boolean onTap(int i) {
		//Handle a tap event here
		return true;
	}
	
	@Override
    public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
          long when) {
       super.draw(canvas, mapView, shadow);
       Paint paint = new Paint();
       paint.setColor(Color.RED);
       paint.setAntiAlias(true);
       paint.setStyle(Style.STROKE);
       paint.setStrokeWidth(4);
       paint.setAlpha(80);
       paint.setPathEffect(new CornerPathEffect(10));
       
       
       
       
       //Point pt1 = new Point();
       //Point pt2[] = new Point();
       Path path = new Path();
       Point startPoint = null; 
       Point endPoint = null;
       Projection projection = mapView.getProjection();
       

       
       
       
       for (int i = 0; i < locations.size(); i++) {
    	   GeoPoint gPointA = locations.get(i);
    	   Point pointA = new Point();
    	   projection.toPixels(gPointA, pointA);
    	   if (i == 0) { //This is the start point
	    	   startPoint = pointA;
	    	   path.moveTo(pointA.x, pointA.y);
    	   } else 
    	   {
    		   if (i == locations.size() - 1)//This is the end point
    			   endPoint = pointA;
    		   path.lineTo(pointA.x, pointA.y);
    	   }
	   }
       
       
       
       
       canvas.drawPath(path, paint);
       return true;
    }
}
