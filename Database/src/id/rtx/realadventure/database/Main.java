package id.rtx.realadventure.database;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
 
import id.rtx.realadventure.database.object.T_GPX;
import id.rtx.realadventure.database.object.T_GPX_ROUTE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class Main extends Activity {
	   private static final String DATABASE_NAME = "example.db";
	   private static final int DATABASE_VERSION = 1;
	   private static final String TABLE_NAME = "table1";
	   private TextView output, idtes;
	    
	   private CRUDatabase crud;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	         
	        this.output = (TextView) this.findViewById(R.id.t1);
	        this.idtes = (TextView) this.findViewById(R.id.t2);
	   
	        crud = new CRUDatabase(this);
	       
	        T_GPX_ROUTE idgpx = new T_GPX_ROUTE();
	        idgpx.setID_GPX("212345");
	        idgpx.setID_ROUTE("323456");
	        idgpx.setH_NAME("35436");
	        idgpx.setH_NUMBER("45648");
	        idgpx.setH_TYPE("57898");
	        
	       // crud.insertT_GPX_ROUTE(idgpx,"T_GPX_ROUTE");
	        
	        
	        String[] n = {"ID_GPX"};
	        List<String> query = crud.selectAll("T_GPX_ROUTE", n,null, null);
	        StringBuilder sb = new StringBuilder();
	        StringBuilder idt = new StringBuilder();
	        for (String QUERY : query) {
	           sb.append(QUERY + "\n");
	        }
	        
	        
	        this.idtes.setText(idt.toString());
	        this.output.setText(sb.toString());
	         
	    }
	}
