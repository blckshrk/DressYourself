package main.java.fr.redteam.dressyourself.activites;

import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.common.DBHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DBHelper t = new DBHelper(this);
		t.open();
		
		final Button connection = (Button) findViewById(R.id.but_test);
	      connection.setOnClickListener(new OnClickListener() {
	      			
	    public void onClick(View v) {
	    	Intent intent = new Intent(MainActivity.this, OutfitActivity.class);
	        startActivity(intent);
	      	}
	      });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
