package id.rtx.realadventure.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class InitDatabase extends SQLiteOpenHelper {

	   private static final String DATABASE_NAME = "example.db";
	   private static final int DATABASE_VERSION = 1;
	   private static final String T_GPX = "T_GPX";
	   
	 
	   
	   
	public InitDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
     }

	@Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE T_GPX (ID_GPX TEXT PRIMARY KEY, BOUNDS_MINLAT TEXT, BOUNDS_MINLOT TEXT, BOUNDS_MAXLAT TEXT, BOUNDS_MAXLON TEXT, META_NAME TEXT, META_DESC, META_AUTHOR TEXT, META_COPYRIGHT TEXT, META_TIME TEXT)");
       
       db.execSQL("CREATE TABLE T_GPX_WAYPOINT (ID_GPX TEXT, LAT TEXT, LON TEXT, ELE TEXT, TIME TEXT, NAME TEXT, DESC TEXT, SIM TEXT, TYPE TEXT)");
       db.execSQL("CREATE TABLE T_GPX_TRACK (ID_GPX TEXT, ID_TRACK INT PRIMARY KEY, H_NAME TEXT, H_NUMBER TEXT, H_TYPE TEXT)");
       db.execSQL("CREATE TABLE T_GPX_ROUTE (ID_GPX TEXT, ID_ROUTE INT PRIMARY KEY, H_NAME TEXT, H_NUMBER TEXT, H_TYPE TEXT)");
       db.execSQL("CREATE TABLE T_GPX_TRACK_CHILD (ID_TRACK INT, C_LAT INT, C_LON TEXT, C_ELE TEXT, C_TIME TEXT, C_SYM TEXT)");
       db.execSQL("CREATE TABLE T_GPX_ROUTE_CHILD (ID_ROUTE INT, C_LAT INT, C_LON TEXT, C_ELE TEXT, C_TIME TEXT, C_NAME TEXT, C_DESC TEXT, C_SYM TEXT, C_TYPE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       Log.w("Example", "Upgrading database, this will drop tables and recreate.");
       db.execSQL("DROP TABLE IF EXISTS " + T_GPX);
       onCreate(db);
    }

}
