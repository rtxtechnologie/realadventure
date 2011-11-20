package de.vogella.android.sqlite.first;
 
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
 
import java.util.ArrayList;
import java.util.List;
 
public class DataHelper {
 
   private static final String DATABASE_NAME = "example.db";
   private static final int DATABASE_VERSION = 1;
   private static final String TABLE_NAME = "table1";
 
   private Context context;
   private SQLiteDatabase db;
 
   private SQLiteStatement insertStmt;
   //private static final String INSERT = "insert into " + TABLE_NAME + "(name) values (?)";
 
   public DataHelper(Context context) {
      this.context = context;
      OpenHelper openHelper = new OpenHelper(this.context);
      this.db = openHelper.getWritableDatabase();
      //this.insertStmt = this.db.compileStatement(INSERT);
   }
 
   public long insert(String name) {
	  this.insertStmt = this.db.compileStatement("insert into " + TABLE_NAME + "(tes) values (?)"); 
      this.insertStmt.bindString(1, name);
      return this.insertStmt.executeInsert();
   }
 
   public void deleteAll() {
      this.db.delete(TABLE_NAME, null, null);
   }
 
   public List<String> selectAll(String t,String[] c,String w, String ob, int ids) {
      List<String> list = new ArrayList<String>();
      Cursor cursor = this.db.query(t, c, w, null, null, null, ob);
     // Cursor cursor = this.db.query(TABLE_NAME, new String[] { "name" },null, null, null, null, "name asc");
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
 
   private static class OpenHelper extends SQLiteOpenHelper {
 
      OpenHelper(Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
      }
 
      @Override
      public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, tes TEXT)");
      }
 
      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         Log.w("Example", "Upgrading database, this will drop tables and recreate.");
         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
         onCreate(db);
      }
   }
}
