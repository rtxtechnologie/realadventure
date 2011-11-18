package de.vogella.android.sqlite.first;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
 
import java.util.List;
 
public class Main extends Activity {
    
	   private TextView output, clmn;
	    
	   private DataHelper dh;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	         
	        this.output = (TextView) this.findViewById(R.id.nm2);
	        this.clmn = (TextView) this.findViewById(R.id.tn2);
	         
	        this.dh = new DataHelper(this);
	        this.dh.deleteAll();
	        this.dh.insert("t1");
	        this.dh.insert("t2");
	        this.dh.insert("t3");
	        String n[] ={"name"};
	        List<String> names = this.dh.selectAll("table1", n, "id = 1 or id = 2", "name asc");
	        StringBuilder sb = new StringBuilder();
	        for (String name : names) {
	           sb.append(name + "\n");
	        }
	         
	        //Log.d("EXAMPLE", "names size - " + names.size());
	        this.clmn.setText("tes"); 
	        this.output.setText(sb.toString());
	         
	    }
	}
