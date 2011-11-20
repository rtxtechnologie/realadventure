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
	  
	   private TextView output1,output2,output3,output4,output5;
	    
	   private CRUDatabase crud;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	         
	        this.output1 = (TextView) this.findViewById(R.id.t1);
	        this.output2 = (TextView) this.findViewById(R.id.t2);
	        this.output3 = (TextView) this.findViewById(R.id.t3);
	        this.output4 = (TextView) this.findViewById(R.id.t4);
	        this.output5 = (TextView) this.findViewById(R.id.t5);
	   
	        crud = new CRUDatabase(this);
	       
	        T_GPX_ROUTE idgpx = new T_GPX_ROUTE();
	        idgpx.setID_GPX("92345");
	        idgpx.setID_ROUTE("83456");
	        idgpx.setH_NAME("75436");
	        idgpx.setH_NUMBER("65648");
	        idgpx.setH_TYPE("37898");
	        
	       //crud.insertT_GPX_ROUTE(idgpx,"T_GPX_ROUTE");
	        
	        
	        String[] n1 = {"ID_GPX"};
	        String[] n2 = {"ID_ROUTE"};
	        String[] n3 = {"H_NAME"};
	        String[] n4 = {"H_NUMBER"};
	        String[] n5 = {"H_TYPE"};
	        List<String> query1 = crud.selectAll("T_GPX_ROUTE", n1,null, null);
	        List<String> query2 = crud.selectAll("T_GPX_ROUTE", n2,null, null);
	        List<String> query3 = crud.selectAll("T_GPX_ROUTE", n3,null, null);
	        List<String> query4 = crud.selectAll("T_GPX_ROUTE", n4,null, null);
	        List<String> query5 = crud.selectAll("T_GPX_ROUTE", n5,null, null);
	        StringBuilder sb1 = new StringBuilder();
	        StringBuilder sb2 = new StringBuilder();
	        StringBuilder sb3 = new StringBuilder();
	        StringBuilder sb4 = new StringBuilder();
	        StringBuilder sb5 = new StringBuilder();
	        
	        for (String QUERY : query1) {
	           sb1.append(QUERY + "\n");
	        }
	        for (String QUERY : query2) {
		           sb2.append(QUERY + "\n");
		        }
	        for (String QUERY : query3) {
		           sb3.append(QUERY + "\n");
		        }
	        for (String QUERY : query4) {
		           sb4.append(QUERY + "\n");
		        }
	        for (String QUERY : query5) {
		           sb5.append(QUERY + "\n");
		        }
		        
	        
	        this.output1.setText(sb1.toString());
	        this.output2.setText(sb2.toString());
	        this.output3.setText(sb3.toString());
	        this.output4.setText(sb4.toString());
	        this.output5.setText(sb5.toString());
	         
	    }
	}
