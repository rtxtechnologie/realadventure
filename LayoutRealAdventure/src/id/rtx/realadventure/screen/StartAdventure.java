package id.rtx.realadventure.screen;

import id.rtx.realadventure.screen.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartAdventure extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startadventure);
        
	}
	
    public void myClickHandler(View v) {
    	Intent a = new Intent(); 
		switch (v.getId()) {
		case R.id.sa1:   a.setClassName(v.getContext(), "id.rtx.realadventure.screen.RecordRoute");
        					startActivity(a);
							break;
		case R.id.sa2:	a.setClassName(v.getContext(), "id.rtx.realadventure.screen.RouteView");
							startActivity(a);
							break;
		case R.id.sa3:	a.setClassName(v.getContext(), "id.rtx.realadventure.screen.ViewRecordRoute");
							startActivity(a);
							break;
		case R.id.sa4:	a.setClassName(v.getContext(), "id.rtx.realadventure.screen.ConfigAdventure");
							startActivity(a);
							break;

		}
	}

}
