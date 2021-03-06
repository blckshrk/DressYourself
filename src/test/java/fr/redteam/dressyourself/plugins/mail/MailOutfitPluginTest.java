package fr.redteam.dressyourself.plugins.mail;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import fr.redteam.dressyourself.activities.ActivityMail;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;

/**
 * This class is made in order to test MailOutfitPlugin
 * 
 */
@RunWith(RobolectricTestRunner.class)
public class MailOutfitPluginTest {

  private Activity activity;
  private Outfit outfit;

  /**
   * Configure the environnement's test.
   */
  @Before
  public void setUp() throws Exception {
    Outfit outfit = new Outfit();
    Clothe clothe = new Clothe();
    outfit.addClothe(clothe);
    this.outfit = outfit;

    Intent intent =
        new Intent(Robolectric.getShadowApplication().getApplicationContext(),
            ActivityMail.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("outfit", outfit);
    intent.putExtras(bundle);
    this.activity =
        Robolectric.buildActivity(ActivityMail.class).withIntent(intent).create().visible()
            .get();
  }

  /**
   * this test made a simple test if the test return true if the mail adress is valid.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressValid() throws Throwable {
    MailOutfitPlugin mailOutfitPlugin =
        new MailOutfitPlugin(this.outfit, "", "", "toto@free.fr", this.activity);
    mailOutfitPlugin.createMail();
    assertEquals(true, mailOutfitPlugin.isValidMail());
  }

  /**
   * this test made a simple test if the test return false if the mail adress is false.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressFalse() throws Throwable {
    MailOutfitPlugin mailOutfitPlugin =
        new MailOutfitPlugin(this.outfit, "", "", "to@to@free.fr", this.activity);
    mailOutfitPlugin.createMail();
    assertEquals(false, mailOutfitPlugin.isValidMail());
  }

  /**
   * this test made a simple test if the test return true if all mail adress is valid.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressesValid() throws Throwable {
    MailOutfitPlugin mailOutfitPlugin =
        new MailOutfitPlugin(this.outfit, "", "", "toto@free.fr;jerm@live.com;foufou@gmail.com",
            this.activity);
    mailOutfitPlugin.createMail();
    assertEquals(true, mailOutfitPlugin.isValidMail());
  }

  /**
   * this test made a simple test if the test return false if the one of mail adress isn't valid.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressesFalse() throws Throwable {
    MailOutfitPlugin mailOutfitPlugin0 =
        new MailOutfitPlugin(this.outfit, "", "",
            "toto@free.fr;jerm@tu@tu.live.com;foufou@gmail.com", this.activity);
    mailOutfitPlugin0.createMail();
    assertEquals(false, mailOutfitPlugin0.isValidMail());
  }

}
