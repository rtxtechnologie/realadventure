package id.rtx.realadventure.database;

import id.rtx.realadventure.database.object.T_GPX;
import id.rtx.realadventure.database.object.T_GPX_ROUTE;
import id.rtx.realadventure.database.object.T_GPX_ROUTE_CHILD;
import id.rtx.realadventure.database.object.T_GPX_TRACK;
import id.rtx.realadventure.database.object.T_GPX_TRACK_CHILD;
import id.rtx.realadventure.database.object.T_GPX_WAYPOINT;


import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class CRUDatabase {
	   
	
	   private Context context;
	   private SQLiteDatabase db;
	 
	   private SQLiteStatement insertStmt;
	   private InitDatabase id;
	   
	   public CRUDatabase(Context context) {
		   	  this.context = context;
		      InitDatabase init = new InitDatabase(this.context);
		      this.db = init.getWritableDatabase();
		      //this.insertStmt = this.db.compileStatement(INSERT);
		   }
	   public long insertT_GPX(T_GPX value,String table) {
			 //this.insertStmt = this.db.compileStatement("insert into T_GPX (ID_GPX,BOUNDS_MINLAT) values (?,?)");
			  
				  this.insertStmt = this.db.compileStatement("insert into T_GPX (ID_GPX,BOUNDS_MINLAT,BOUNDS_MINLOT,BOUNDS_MAXLAT," +
				  		"BOUNDS_MAXLON,META_NAME,META_DESC,META_AUTHOR,META_COPYRIGHT,META_TIME) values (?,?,?,?,?,?,?,?,?,?)");
				  //this.insertStmt.bindString(1, (String)value.get("ID_GPX"));
				  this.insertStmt.bindString(1, value.getID_GPX());
				  this.insertStmt.bindString(2, value.getBOUNDS_MINLAT());
				  this.insertStmt.bindString(3, value.getBOUNDS_MINLOT());
				  this.insertStmt.bindString(4, value.getBOUNDS_MAXLAT());
				  this.insertStmt.bindString(5, value.getBOUNDS_MAXLON());
				  this.insertStmt.bindString(6, value.getMETA_NAME());
				  this.insertStmt.bindString(7, value.getMETA_DESC());
				  this.insertStmt.bindString(8, value.getMETA_AUTHOR());
				  this.insertStmt.bindString(9, value.getMETA_COPYRIGHT());
				  this.insertStmt.bindString(10, value.getMETA_TIME());
				  return this.insertStmt.executeInsert(); 
			  }
	   public long insertT_GPX_WAYPOINT(T_GPX_WAYPOINT value,String table) {
		  		this.insertStmt = this.db.compileStatement("insert into T_GPX_WAYPOINT (ID_GPX,LAT,LON,ELE,TIME,NAME," +
			  		"DESC,SYM,TYPE) values (?,?,?,?,?,?,?,?,?)");
		  		this.insertStmt.bindString(1, value.getID_GPX());
		  		this.insertStmt.bindString(2, value.getLAT());
		  		this.insertStmt.bindString(3, value.getLON());
		  		this.insertStmt.bindString(4, value.getELE());
		  		this.insertStmt.bindString(5, value.getTIME());
		  		this.insertStmt.bindString(6, value.getNAME());
		  		this.insertStmt.bindString(7, value.getDESC());
		  		this.insertStmt.bindString(8, value.getSYM());
		  		this.insertStmt.bindString(9, value.getTYPE());
				  return this.insertStmt.executeInsert(); 
			  }
	   public long insertT_GPX_TRACK(T_GPX_TRACK value,String table) {
		   this.insertStmt = this.db.compileStatement("insert into T_GPX_TRACK (ID_GPX,ID_TRACK,H_NAME,H_NUMBER,H_TYPE) values (?,?,?,?,?)");
			  this.insertStmt.bindString(1, value.getID_GPX());
			  this.insertStmt.bindString(2, value.getID_TRACK());
			  this.insertStmt.bindString(3, value.getH_NAME());
			  this.insertStmt.bindString(4, value.getH_NUMBER());
			  this.insertStmt.bindString(5, value.getH_TYPE());
				  return this.insertStmt.executeInsert(); 
			  }
	   public long insertT_GPX_ROUTE(T_GPX_ROUTE value,String table) {
		   this.insertStmt = this.db.compileStatement("insert into T_GPX_ROUTE (ID_GPX,ID_ROUTE,H_NAME,H_NUMBER,H_TYPE) values (?,?,?,?,?)");
			  this.insertStmt.bindString(1, value.getID_GPX());
			  this.insertStmt.bindString(2, value.getID_ROUTE());
			  this.insertStmt.bindString(3, value.getH_NAME());
			  this.insertStmt.bindString(4, value.getH_NUMBER());
			  this.insertStmt.bindString(5, value.getH_TYPE());
				  return this.insertStmt.executeInsert(); 
			  }
	   public long insertT_GPX_TRACK_CHILD(T_GPX_TRACK_CHILD value,String table) {
		   this.insertStmt = this.db.compileStatement("insert into T_GPX_TRACK_CHILD (ID_TRACK,C_LAT,C_LON,C_ELE,C_TIME," +
		   		"C_SYM) values (?,?,?,?,?,?)");
			  this.insertStmt.bindString(1, value.getID_TRACK());
			  this.insertStmt.bindString(2, value.getC_LAT());
			  this.insertStmt.bindString(3, value.getC_LON());
			  this.insertStmt.bindString(4, value.getC_ELE());
			  this.insertStmt.bindString(5, value.getC_TIME());
			  this.insertStmt.bindString(6, value.getC_SYM());
				  return this.insertStmt.executeInsert(); 
			  }
	   public long insertT_GPX_ROUTE_CHILD(T_GPX_ROUTE_CHILD value,String table) {
		   this.insertStmt = this.db.compileStatement("insert into T_GPX_ROUTE_CHILD (ID_ROUTE,C_LAT,C_LON,C_ELE,C_TIME," +
		   		"C_NAME,C_DESC,C_SYM,C_TYPE) values (?,?,?,?,?,?,?,?,?)");
			  this.insertStmt.bindString(1, value.getID_ROUTE());
			  this.insertStmt.bindString(2, value.getC_LAT());
			  this.insertStmt.bindString(3, value.getC_LON());
			  this.insertStmt.bindString(4, value.getC_ELE());
			  this.insertStmt.bindString(5, value.getC_TIME());
			  this.insertStmt.bindString(6, value.getC_NAME());
			  this.insertStmt.bindString(7, value.getC_DESC());
			  this.insertStmt.bindString(8, value.getC_SYM());
			  this.insertStmt.bindString(9, value.getC_TYPE());
				  return this.insertStmt.executeInsert(); 
			  }
	   


			  
	   public List<String> selectAll(String t,String[] c,String w, String ob) {
		      List<String> list = new ArrayList<String>();
		      Cursor cursor = this.db.query(t, c, w, null, null, null, ob);
		    if (cursor.moveToFirst()) {
		         do {
		            list.add(cursor.getString(0)); 
		         } while (cursor.moveToNext());
		      }
		      if (cursor != null && !cursor.isClosed()) {
		         cursor.close();
		      }
		      return list;
		   }
}
