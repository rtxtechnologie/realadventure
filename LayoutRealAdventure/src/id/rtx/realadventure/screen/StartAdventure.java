package id.rtx.realadventure.screen;

import id.rtx.realadventure.screen.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartAdventure extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startadventure);
        
        Button mainNext = (Button) findViewById(R.id.sa4);
        
        mainNext.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent a = new Intent();                
                a.setClassName(v.getContext(), "id.rtx.realadventure.screen.ConfigAdventure");
                startActivity(a);
            }
        });
        
	}

}
