package de.vogella.android.sqlite.first;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
 
import java.util.List;
 
public class Main extends Activity {
    
	   private TextView output, clmn, idtes;
	    
	   private DataHelper dh;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	         
	        this.output = (TextView) this.findViewById(R.id.nm2);
	        this.clmn = (TextView) this.findViewById(R.id.tn2);
	        this.idtes = (TextView) this.findViewById(R.id.id1);
	        
	        this.dh = new DataHelper(this);
	        this.dh.deleteAll();
	        this.dh.insert("t1");
	        this.dh.insert("t2");
	        this.dh.insert("t3");
	        String[] n = {"tes"};
	        String[] i = {"id"};
	        List<String> names = this.dh.selectAll("table1", n, "id = 1 or id = 2", "tes asc", 0);
	        List<String> ids = this.dh.selectAll("table1", i, "id = 1 or id = 2", "tes asc", 0);
	        StringBuilder sb = new StringBuilder();
	        StringBuilder idt = new StringBuilder();
	        for (String tes : names) {
	           sb.append(tes + "\n");
	        }
	        for (String id : ids) {
		           idt.append(id + "\n");
		        }
	        
	         
	        //Log.d("EXAMPLE", "names size - " + names.size());
	        this.clmn.setText("tes");
	        this.idtes.setText(idt.toString());
	        this.output.setText(sb.toString());
	         
	    }
	}
