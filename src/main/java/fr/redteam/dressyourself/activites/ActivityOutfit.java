package main.java.fr.redteam.dressyourself.activites;

import java.util.ArrayList;

import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.common.DBHelper;
import main.java.fr.redteam.dressyourself.core.clothes.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityOutfit extends Activity {

  private TextView textViewTop;
  private TextView textViewBottom;
  private TextView textViewFeet;
  private Button buttonRefreshTop;
  private Button buttonRefreshBottom;
  private Button buttonRefreshFeet;
  private DBHelper db = new DBHelper(this);

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.outfit_layout);
    textViewTop = (TextView) findViewById(R.id.top_text);
    textViewBottom = (TextView) findViewById(R.id.bottom_text);
    textViewFeet = (TextView) findViewById(R.id.feet_text);

    // connexion bdd
    db.open();

    // recup top
    ArrayList<Clothe> listTop = new ArrayList<Clothe>();
    listTop = db.getListTop();
    System.out.println(listTop);

    // recup bottom
    ArrayList<Clothe> listBottom = new ArrayList<Clothe>();
    listBottom = db.getListBottom();
    System.out.println(listBottom);

    // recup bottom
    ArrayList<Clothe> listFeet = new ArrayList<Clothe>();
    listFeet = db.getListFeet();
    System.out.println(listFeet);

    // vetements statique TODO: récupérer en bdd
    Clothe clothe = new Clothe("Pull beige");
    textViewTop.setText(clothe.getModel());
    textViewBottom.setText("slim bleu fonce");
    textViewFeet.setText("Basket camel");

    buttonRefreshTop = (Button) findViewById(R.id.top_refresh_button);
    buttonRefreshTop.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        refreshTop();
      }
    });

    buttonRefreshBottom = (Button) findViewById(R.id.bottom_refresh_button);
    buttonRefreshBottom.setOnClickListener(new OnClickListener() {

      public void onClick(View v) {
        refreshBottom();
      }
    });

    buttonRefreshFeet = (Button) findViewById(R.id.feet_refresh_button);
    buttonRefreshFeet.setOnClickListener(new OnClickListener() {

      public void onClick(View v) {
        refreshFeet();
      }
    });
    db.close();
  }

  private void refreshTop() {
    // TODO recuperation en base
    textViewTop.setText(textViewTop.getText() + " ");
  }

  private void refreshBottom() {
    // TODO recuperation en base
    textViewBottom.setText(textViewBottom.getText() + " ");
  }

  private void refreshFeet() {
    // TODO recuperation en base
    textViewFeet.setText(textViewFeet.getText() + " ");
  }
}