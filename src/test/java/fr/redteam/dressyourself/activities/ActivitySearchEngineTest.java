package fr.redteam.dressyourself.activities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowListView;
import org.robolectric.util.ActivityController;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import fr.redteam.dressyourself.views.ListViewClothes;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
@RunWith(RobolectricTestRunner.class)
public class ActivitySearchEngineTest {

  ActivityController<ActivitySearchEngine> controler;
  Context context;

  @Before
  public void setUp() {
    controler = Robolectric.buildActivity(ActivitySearchEngine.class);
    context = Robolectric.getShadowApplication().getApplicationContext();
  }

  @Test
  public void testSearchManager() {
    controler.create();

    SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
    assertNotNull(searchManager);

    Intent searchIntent = new Intent(Intent.ACTION_SEARCH);
    searchIntent.putExtra(SearchManager.QUERY, "jeans");

    controler.newIntent(searchIntent);
    assertEquals(93, controler.get().getListView().getCount());

    controler.destroy();
  }

  @Test
  public void testOnListItemClick() {
    controler.create();

    SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
    assertNotNull(searchManager);

    Intent searchIntent = new Intent(Intent.ACTION_SEARCH);
    searchIntent.putExtra(SearchManager.QUERY, "jeans");

    controler.newIntent(searchIntent);
    ShadowListView listView = Robolectric.shadowOf(controler.get().getListView());
    // XXX CHECK
    listView.addView(new ListViewClothes(context), 0, null);

    listView.performItemClick(0);

    controler.destroy();
  }
}