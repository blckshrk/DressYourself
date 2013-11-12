package fr.redteam.dressyourself.activities;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class ActivityClotheModifyTest
    extends ActivityInstrumentationTestCase2<ActivityClotheModify> {

  private ActivityClotheModify myActivity;
  private EditText modelEditText;
  private EditText brandEditText;
  private Spinner colorSpinner;
  private Spinner typeSpinner;
  private Clothe clotheToEdit;

  public ActivityClotheModifyTest(Class<ActivityClotheModify> activityClass) {
    super(activityClass);
    // TODO Auto-generated constructor stub
  }

  public ActivityClotheModifyTest() {
    super(ActivityClotheModify.class);
  }

  @Override
  protected void setUp() throws Exception {
    // TODO Auto-generated method stub
    super.setUp();

    /* creating a clothe to Edit */
    this.clotheToEdit = new Clothe("my zara red pull");
    this.clotheToEdit.setBrand("Zara");
    this.clotheToEdit.setColor("RED");
    this.clotheToEdit.setType("pull");

    /* passing the clothe through intent */
    Intent intent = new Intent(getInstrumentation().getTargetContext(), ActivityClotheModify.class);
    intent.putExtra("clothe", this.clotheToEdit);
    setActivityIntent(intent);

    /* retrieving the activity */
    this.myActivity = getActivity();

    /* retrieving some components from the activity */
    this.modelEditText = (EditText) this.myActivity.findViewById(R.id.modelEdit);
    this.brandEditText = (EditText) this.myActivity.findViewById(R.id.brandEdit);
    this.colorSpinner = (Spinner) this.myActivity.findViewById(R.id.colorSpinner);
    this.typeSpinner = (Spinner) this.myActivity.findViewById(R.id.typeSpinner);

    /* retrieving informations from intent */
    this.clotheToEdit = (Clothe) intent.getSerializableExtra("clothe");
  }

  /* the following tests check the initialization of editable fields */

  /* check if the clothe's model value is properly loaded into model EditText */
  public void testModelInitialValue() {
    assertEquals(this.clotheToEdit.getModel(), this.modelEditText.getText().toString());
  }

  /* check if the clothe's brand value is properly loaded into brand EditText */
  public void testBrandInitialValue() {
    assertEquals(this.clotheToEdit.getBrand(), this.brandEditText.getText().toString());
  }

  /* check if the clothe's color value is properly loaded as first element into color Spinner */
  public void testColorInitialValue() {
    assertEquals(this.clotheToEdit.getColor(), this.colorSpinner.getSelectedItem().toString());
  }

  /* check if the clothe's type value is properly loaded as first element into type Spinner */
  public void testTypeInitialValue() {
    assertEquals(this.clotheToEdit.getType(), this.typeSpinner.getSelectedItem().toString());
  }

  /*
   * the following tests check the values of clothe attributes after modifications to ensure it has
   * been saved into the clothe object
   */

  /* check if the modifications on model have been saved */
  @UiThreadTest
  public void testupdateClotheValuesOnModel() {
    this.modelEditText.setText("modified model!");
    this.myActivity.updateClotheValues(this.clotheToEdit);
    assertEquals(this.clotheToEdit.getModel(), this.modelEditText.getText().toString());
  }

  /* check if the modifications on brand have been saved */
  @UiThreadTest
  public void testupdateClotheValuesOnBrand() {
    this.brandEditText.setText("modified brand!");
    this.myActivity.updateClotheValues(this.clotheToEdit);
    assertEquals(this.clotheToEdit.getBrand(), this.brandEditText.getText().toString());
  }
  
  /* check if the modifications on color have been saved */
  @UiThreadTest
  public void testupdateClotheValuesOnColor() {
    ArrayAdapter<String> colorAdapter = (ArrayAdapter<String>) this.colorSpinner.getAdapter();
    this.colorSpinner.setSelection(colorAdapter.getPosition("BLUE"));
    this.myActivity.updateClotheValues(this.clotheToEdit);
    assertEquals(this.clotheToEdit.getColor(), this.colorSpinner.getSelectedItem().toString());
  }

  /* check if the modifications on type have been saved */
  @UiThreadTest
  public void testupdateClotheValuesOnType() {
    ArrayAdapter<String> typeAdapter = (ArrayAdapter<String>) this.typeSpinner.getAdapter();
    this.typeSpinner.setSelection(typeAdapter.getPosition("T-shirt"));
    this.myActivity.updateClotheValues(this.clotheToEdit);
    assertEquals(this.clotheToEdit.getType(), this.typeSpinner.getSelectedItem().toString());
  }
}
