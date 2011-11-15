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
        
        
        Button mainNext = (Button) findViewById(R.id.bmain1);
        
        mainNext.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent();                
                i.setClassName(v.getContext(), "id.rtx.realadventure.screen.StartAdventure");
                startActivity(i);
            }
        });
    }
}