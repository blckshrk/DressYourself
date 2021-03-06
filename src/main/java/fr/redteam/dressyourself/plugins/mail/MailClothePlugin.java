package fr.redteam.dressyourself.plugins.mail;

import android.app.Activity;
import fr.redteam.dressyourself.core.clothes.Clothe;

/**
 * class spécialized to mail a clothe
 */
public class MailClothePlugin extends MailPlugin {

  private Clothe clothe;
  private String textBody;
/**
 * 
 * @param clothe
 * @param subject
 * @param textBody
 * @param receiver
 * @param activity
 */
  public MailClothePlugin(Clothe clothe, String subject, String textBody, String receiver,
      Activity activity) {
    super(subject, receiver, activity);
    this.clothe = clothe;
    this.textBody = textBody;
  }

  /**
   * redefine the method Create Mail
   */
  public void createMail() {
    super.createMail();
    this.body();
    this.launchMailAgent();
  }

  /**
   * Write the body of mail
   */
  protected void body() {
    this.textBody +=
        "\n - a\\an " + clothe.getType() + " from the brand " + clothe.getBrand() + " and it's "
            + clothe.getColor() + " " + clothe.getModel() + ".\n";
    /* Add text */
    this.getMailIntent().putExtra(android.content.Intent.EXTRA_TEXT, this.textBody);
  }
}
