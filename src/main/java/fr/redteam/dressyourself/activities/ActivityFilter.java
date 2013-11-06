package fr.redteam.dressyourself.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import fr.redteam.dressyourself.R;

public class ActivityFilter extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_filter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.filter, menu);

    Button buttonOutfit = (Button) findViewById(R.id.validationFiltre);
    buttonOutfit.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO redirected to the 'filters page'
        Intent intent = new Intent(ActivityFilter.this, ActivityOutfit.class);
        startActivity(intent);
      }
    });

    return true;
  }
}
