package id.rtx.realadventure.screen;




import id.rtx.realadventure.screen.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Main extends Activity {
	/** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }
    public void myClickHandler(View v) {
    	Intent a = new Intent(); 
		switch (v.getId()) {
		case R.id.bmain1:   a.setClassName(v.getContext(), "id.rtx.realadventure.screen.StarAdventure");
        					startActivity(a);
							break;
		case R.id.bmain2:	a.setClassName(v.getContext(), "id.rtx.realadventure.screen.Maps");
							startActivity(a);
							break;
		case R.id.bmain3:	a.setClassName(v.getContext(), "id.rtx.realadventure.screen.NavTool");
							startActivity(a);
							break;
		case R.id.bmain4:	a.setClassName(v.getContext(), "id.rtx.realadventure.screen.InvKit");
							startActivity(a);
							break;
		case R.id.bmain5:	a.setClassName(v.getContext(), "id.rtx.realadventure.screen.EmgTool");
							startActivity(a);
							break;
		case R.id.bmain6:	a.setClassName(v.getContext(), "id.rtx.realadventure.screen.History");
							startActivity(a);
							break;
		}
	}
}