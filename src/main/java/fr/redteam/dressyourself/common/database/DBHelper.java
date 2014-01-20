package fr.redteam.dressyourself.common.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;
import fr.redteam.dressyourself.exceptions.DressyourselfDatabaseException;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class DBHelper implements IntDBHelper {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private final CreateSQLBase mbdd;
  private SQLiteDatabase bdd;

  public DBHelper(Context context) {
    super();
    mbdd = new CreateSQLBase(context, "GYSBdd", null, 2);

  }

  // pour pouvoir creer une bdd avec un nom particulier , mettre null pour en
  // memoire
  public DBHelper(Context context, String name) {
    super();
    mbdd = new CreateSQLBase(context, name, null, 1);

  }

  public void open() {
    // on ouvre la BDD en Ã©criture
    bdd = mbdd.getWritableDatabase();
  }

  public void close() {
    bdd.close();
  }

  public SQLiteDatabase getBDD() {
    return bdd;
  }

  private long insertColor(String couleur) {
    try {
      ContentValues values = new ContentValues();
      values.put("colorName", couleur);
      return bdd.insertWithOnConflict("COLOR", null, values, SQLiteDatabase.CONFLICT_IGNORE);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException" , e);
    }
  }

  private long insertWeather(String weather) {
    try {
      ContentValues values = new ContentValues();
      values.put("weatherName", weather);
      return bdd.insertWithOnConflict("WEATHER", null, values, SQLiteDatabase.CONFLICT_IGNORE);
    } catch ( RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException" , e);
    }
  }


  private long insertBodies(String bodies) {
    try {
      
      ContentValues values = new ContentValues();
      values.put("bodiesName", bodies);
      return bdd.insertWithOnConflict("BODIES", null, values, SQLiteDatabase.CONFLICT_IGNORE);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException" , e);
    }
  }


  private long insertType(String type, long idBodies) {
    try {
      ContentValues values = new ContentValues();
      values.put("typeName", type);
      values.put("ID_b", idBodies);
      return bdd.insertWithOnConflict("TYPE", null, values, SQLiteDatabase.CONFLICT_IGNORE);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException" , e);
    }

  }

  private long insertJustClothes(Clothe clothe) {
    try {
      ContentValues values = new ContentValues();
      values.put("model", clothe.getModel());
      if (clothe.getType() != null) {
        values.put("ID_t", getIDType(clothe.getType()));
      } else
        values.put("ID_t", 0);

      if (clothe.getColor() != null) {
        values.put("ID_c", getIDColor(clothe.getColor()));
      } else {
        values.put("ID_c", 0);
      }

      if (clothe.getBrand() != null) {
        values.put("ID_br", getIDBrand(clothe.getBrand()));
      } else {
        values.put("ID_br", 0);
      }
      if (clothe.getImageRelativePath() != null) {
        values.put("image", clothe.getImageRelativePath());
      } else {
        values.put("image", 0);
      }

      long r = bdd.insertWithOnConflict("CLOTHES", null, values, SQLiteDatabase.CONFLICT_IGNORE);

      return r;
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }

  }

  /*
   * values = new ContentValues(); for (int i = 0; i < clothe.getWeather().size(); i++) {
   * values.put("ID_c", r); values.put("ID_w", getIDWeather(clothe.getWeather().get(i)));
   * bdd.insert("WEATHER_CLOTHES", null, values); }
   */


  private long insertOutfit(String name, Clothe[] clothes) {
    return 0; // TODO
  }


  private long insertBrand(String brand) {
    try {
      ContentValues values = new ContentValues();
      values.put("brandName", brand);
      return bdd.insertWithOnConflict("BRAND", null, values, SQLiteDatabase.CONFLICT_IGNORE);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }

  }


  private long getIDColor(String color) {
    try {
      String query = "SELECT ID_color FROM COLOR WHERE colorName = \"" + color + "\"";
      Log.v("BDD", query);
      Cursor c = bdd.rawQuery(query, null);
      c.moveToFirst();
      return c.getLong(0);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }

  }


  private long getIDWeather(String weather) {
    try {
      String query = "SELECT ID_weather FROM WEATHER WHERE weatherName = \"" + weather + "\"";
      Log.v("BDD", query);
      Cursor c = bdd.rawQuery(query, null);
      c.moveToFirst();
      return c.getLong(0);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }


  private long getIDBodies(String bodies) {
    try {
      String query = "SELECT ID_bodies FROM BODIES WHERE bodiesName = \"" + bodies + "\"";
      Log.v("BDD", query);
      Cursor c = bdd.rawQuery(query, null);
      c.moveToFirst();
      return c.getLong(0);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }

  }


  private long getIDType(String type) {
    try {
      String query = "SELECT ID_type FROM TYPE WHERE typeName = \"" + type + "\"";
      Log.v("BDD", query);
      Cursor c = bdd.rawQuery(query, null);
      c.moveToFirst();
      return c.getLong(0);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }


  private long getIDClothes(String clothes) {
    try {
      String query = "SELECT ID_clothes FROM CLOTHES WHERE model = \"" + clothes + "\"";
      Log.v("BDD", query);
      Cursor c = bdd.rawQuery(query, null);
      c.moveToFirst();
      return c.getLong(0);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }

  }


  private long getIDOutfit(String outfit) {
    try {
      String query = "SELECT ID_outfit FROM OUTFIT WHERE outfitName = \"" + outfit + "\"";
      Log.v("BDD", query);
      Cursor c = bdd.rawQuery(query, null);
      c.moveToFirst();
      return c.getLong(0);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }


  private long getIDBrand(String brand) {
    try {
      String query = "SELECT ID_brand FROM BRAND WHERE brandName = \"" + brand + "\"";
      Log.v("BDD", query);
      Cursor c = bdd.rawQuery(query, null);
      c.moveToFirst();
      return c.getLong(0);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }


  private String getColor(long id) {
    try {
      String query = "SELECT colorName FROM COLOR WHERE ID_color = " + id;
      Log.v("BDD", query);
      Cursor c = bdd.rawQuery(query, null);
      c.moveToFirst();
      return c.getString(0);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }


  private String getBodies(long l) {
    try {
      String query = "SELECT bodiesName FROM BODIES WHERE ID_bodies = " + l;
      Log.v("BDD", query);
      Cursor c = bdd.rawQuery(query, null);
      c.moveToFirst();

      return c.getString(0);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  private String getWeather(long id) {
    try {
      String query = "SELECT weatherName FROM WEATHER WHERE ID_weather = " + id;
      Log.v("BDD", query);
      Cursor c = bdd.rawQuery(query, null);
      c.moveToFirst();

      return c.getString(0);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }


  private String getType(long id) {
    try {
      String query = "SELECT typeName FROM TYPE WHERE ID_type = " + id;
      Log.v("BDD", query);
      Cursor c = bdd.rawQuery(query, null);
      c.moveToFirst();

      return c.getString(0);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }


  private String getBrand(long id) {
    try {
      String query = "SELECT brandName FROM BRAND WHERE ID_brand = " + id;
      Log.v("BDD", query);
      Cursor c = bdd.rawQuery(query, null);
      c.moveToFirst();

      return c.getString(0);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }

  }

  private Clothe getClothe(long id) {
    String query=
        "SELECT CLOTHES.ID_clothes AS id, CLOTHES.model,TYPE.typeName, BODIES.bodiesName, BRAND.brandName,COLOR.colorName,WEATHER.weatherName,CLOTHES.image "
            + "FROM BRAND,COLOR,TYPE,BODIES, CLOTHES "
            + "LEFT JOIN WEATHER_CLOTHES ON CLOTHES.ID_clothes = WEATHER_CLOTHES.ID_c "
            + "LEFT JOIN WEATHER  ON WEATHER.ID_weather = WEATHER_CLOTHES.ID_w  "
            + "WHERE CLOTHES.ID_br= BRAND.ID_brand "
            + "AND CLOTHES.ID_c = COLOR.ID_color "
            + "AND CLOTHES.ID_t = TYPE.ID_type " + "AND TYPE.ID_b = BODIES.ID_bodies AND id=" + id;

    try {
    Cursor cursor = bdd.rawQuery(query, null);
    /*
     * placement des champs dans le curseur 0:ID_clothes 1: model 2: typeName 3:bodiesName 4:
     * brandName 5: colorName 6: weatherName 7:image
     */

    Clothe clothe = new Clothe();
    cursor.moveToFirst();
    ArrayList<String> weather = new ArrayList<String>();
    clothe.setId(cursor.getInt(0));
    clothe.setModel(cursor.getString(1));
    clothe.setType(cursor.getString(2));
    clothe.setBodies(cursor.getString(3));
    clothe.setBrand(cursor.getString(4));
    clothe.setColor(cursor.getString(5));;
    weather.add(cursor.getString(6));
    clothe.setImageRelativePath(cursor.getString(7));

    while (cursor.moveToNext()) {
      weather.add(cursor.getString(6));
    }
    clothe.setWeather(weather);

    return clothe;
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  @Override
  public List<Clothe> getListTop() {

    String query =
        "SELECT CLOTHES.ID_clothes AS id, CLOTHES.model,TYPE.typeName, BODIES.bodiesName, BRAND.brandName,COLOR.colorName,WEATHER.weatherName,CLOTHES.image "
            + "FROM BRAND,COLOR,TYPE,BODIES, CLOTHES "
            + "LEFT JOIN WEATHER_CLOTHES ON CLOTHES.ID_clothes = WEATHER_CLOTHES.ID_c "
            + "LEFT JOIN WEATHER  ON WEATHER.ID_weather = WEATHER_CLOTHES.ID_w  "
            + "WHERE CLOTHES.ID_br= BRAND.ID_brand "
            + "AND CLOTHES.ID_c = COLOR.ID_color "
            + "AND CLOTHES.ID_t = TYPE.ID_type "
            + "AND TYPE.ID_b = BODIES.ID_bodies AND bodiesName='Top'";

    /*
     * placement des champs dans le curseur 0:ID_clothes 1: model 2: typeName 3:bodiesName 4:
     * brandName 5: colorName 6: weatherName 7:image
     */
    try {
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<Clothe> listClothes = new ArrayList<Clothe>();
    Clothe clothe;
    int id = -1;
    clothe = new Clothe();
    ArrayList<String> weather = new ArrayList<String>();
    while (cursor.moveToNext()) {
      Log.v("BDD", Integer.toString(cursor.getInt(0)));
      if (cursor.getInt(0) != id) {
        if (id != -1) {
          listClothes.add(clothe);
        }
        clothe = new Clothe();
        weather = new ArrayList<String>();
        id = cursor.getInt(0);
        clothe.setId(cursor.getInt(0));
        clothe.setModel(cursor.getString(1));
        clothe.setType(cursor.getString(2));
        clothe.setBodies(cursor.getString(3));
        clothe.setBrand(cursor.getString(4));
        clothe.setColor(cursor.getString(5));
        clothe.setImageRelativePath(cursor.getString(7));
        weather.add(cursor.getString(6));
        clothe.setWeather(weather);
      } else {
        weather.add(cursor.getString(6));
      }
    }
    listClothes.add(clothe);

    return listClothes;
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  @Override
  public List<Clothe> getListBottom() {

    String query =
        "SELECT CLOTHES.ID_clothes AS id, CLOTHES.model,TYPE.typeName, BODIES.bodiesName, BRAND.brandName,COLOR.colorName,WEATHER.weatherName,CLOTHES.image "
            + "FROM BRAND,COLOR,TYPE,BODIES, CLOTHES "
            + "LEFT JOIN WEATHER_CLOTHES ON CLOTHES.ID_clothes = WEATHER_CLOTHES.ID_c "
            + "LEFT JOIN WEATHER  ON WEATHER.ID_weather = WEATHER_CLOTHES.ID_w  "
            + "WHERE CLOTHES.ID_br= BRAND.ID_brand "
            + "AND CLOTHES.ID_c = COLOR.ID_color "
            + "AND CLOTHES.ID_t = TYPE.ID_type "
            + "AND TYPE.ID_b = BODIES.ID_bodies "
            + "AND bodiesName='Bottom'";

    /*
     * placement des champs dans le curseur 0:ID_clothes 1: model 2: typeName 3:bodiesName 4:
     * brandName 5: colorName 6: weatherName 7:image
     */

    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<Clothe> listClothes = new ArrayList<Clothe>();
    Clothe clothe;
    int id = -1;
    clothe = new Clothe();
    ArrayList<String> weather = new ArrayList<String>();

    while (cursor.moveToNext()) {
      Log.v("BDD", Integer.toString(cursor.getInt(0)));
      if (cursor.getInt(0) != id) {
        if (id != -1) {
          listClothes.add(clothe);
        }
        clothe = new Clothe();
        weather = new ArrayList<String>();
        id = cursor.getInt(0);
        clothe.setId(cursor.getInt(0));
        clothe.setModel(cursor.getString(1));
        clothe.setType(cursor.getString(2));
        clothe.setBodies(cursor.getString(3));
        clothe.setBrand(cursor.getString(4));
        clothe.setColor(cursor.getString(5));
        clothe.setImageRelativePath(cursor.getString(7));
        weather.add(cursor.getString(6));
        clothe.setWeather(weather);
      } else {
        weather.add(cursor.getString(6));
      }
      listClothes.add(clothe);

    }

    return listClothes;
  }

  @Override
  public List<Clothe> getListFeet() {

    String query =
        "SELECT CLOTHES.ID_clothes AS id, CLOTHES.model,TYPE.typeName, BODIES.bodiesName, BRAND.brandName,COLOR.colorName,WEATHER.weatherName,CLOTHES.image "
            + "FROM BRAND,COLOR,TYPE,BODIES, CLOTHES "
            + "LEFT JOIN WEATHER_CLOTHES ON CLOTHES.ID_clothes = WEATHER_CLOTHES.ID_c "
            + "LEFT JOIN WEATHER  ON WEATHER.ID_weather = WEATHER_CLOTHES.ID_w  "
            + "WHERE CLOTHES.ID_br= BRAND.ID_brand "
            + "AND CLOTHES.ID_c = COLOR.ID_color "
            + "AND CLOTHES.ID_t = TYPE.ID_type "
            + "AND TYPE.ID_b = BODIES.ID_bodies "
            + "AND bodiesName='Shoes'";

    /*
     * placement des champs dans le curseur 0:ID_clothes 1: model 2: typeName 3:bodiesName 4:
     * brandName 5: colorName 6: weatherName 7:image
     */
    try {
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<Clothe> listClothes = new ArrayList<Clothe>();
    Clothe clothe;
    int id = -1;
    clothe = new Clothe();
    ArrayList<String> weather = new ArrayList<String>();

    while (cursor.moveToNext()) {
      Log.v("BDD", Integer.toString(cursor.getInt(0)));
      if (cursor.getInt(0) != id) {
        if (id != -1) {
          listClothes.add(clothe);
        }
        clothe = new Clothe();
        weather = new ArrayList<String>();
        id = cursor.getInt(0);
        clothe.setId(cursor.getInt(0));
        clothe.setModel(cursor.getString(1));
        clothe.setType(cursor.getString(2));
        clothe.setBodies(cursor.getString(3));
        clothe.setBrand(cursor.getString(4));
        clothe.setColor(cursor.getString(5));
        clothe.setImageRelativePath(cursor.getString(7));
        weather.add(cursor.getString(6));
        clothe.setWeather(weather);
      } else {
        weather.add(cursor.getString(6));
      }
      listClothes.add(clothe);
    }

    return listClothes;
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  @Override
  public List<Clothe> getListClothes() {
    try {
      String query =
          "SELECT CLOTHES.ID_clothes AS id, CLOTHES.model,TYPE.typeName, BODIES.bodiesName, BRAND.brandName,COLOR.colorName,WEATHER.weatherName,CLOTHES.image "
              + "FROM BRAND,COLOR,TYPE,BODIES, CLOTHES "
              + "LEFT JOIN WEATHER_CLOTHES ON CLOTHES.ID_clothes = WEATHER_CLOTHES.ID_c "
              + "LEFT JOIN WEATHER  ON WEATHER.ID_weather = WEATHER_CLOTHES.ID_w  "
              + "WHERE CLOTHES.ID_br= BRAND.ID_brand "
              + "AND CLOTHES.ID_c = COLOR.ID_color "
              + "AND CLOTHES.ID_t = TYPE.ID_type " 
              + "AND TYPE.ID_b = BODIES.ID_bodies";

      /*
       * placement des champs dans le curseur 0:ID_clothes 1: model 2: typeName 3:bodiesName 4:
       * brandName 5: colorName 6: weatherName 7:image
       */

      Cursor cursor = bdd.rawQuery(query, null);
      ArrayList<Clothe> listClothes = new ArrayList<Clothe>();
      Clothe clothe;
      int id = -1;
      clothe = new Clothe();
      ArrayList<String> weather = new ArrayList<String>();

      while (cursor.moveToNext()) {
        Log.v("BDD", Integer.toString(cursor.getInt(0)));
        if (cursor.getInt(0) != id) {
          if (id != -1) {
            listClothes.add(clothe);
          }
          clothe = new Clothe();
          weather = new ArrayList<String>();
          id = cursor.getInt(0);
          clothe.setId(cursor.getInt(0));
          clothe.setModel(cursor.getString(1));
          clothe.setType(cursor.getString(2));
          clothe.setBodies(cursor.getString(3));
          clothe.setBrand(cursor.getString(4));
          clothe.setColor(cursor.getString(5));
          clothe.setImageRelativePath(cursor.getString(7));
          weather.add(cursor.getString(6));
          clothe.setWeather(weather);
        } else {
          weather.add(cursor.getString(6));
        }
      }
      listClothes.add(clothe);

      return listClothes;

    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  @Override
  public List<String> getAllColors() {
    String query = "Select colorName FROM COLOR";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<String> colors = new ArrayList<String>();
    while (cursor.moveToNext()) {
      colors.add(cursor.getString(0));
    }
    return colors;
  }

  @Override
  public List<String> getAllTypes() {
    String query = "Select typeName FROM TYPE";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<String> types = new ArrayList<String>();
    while (cursor.moveToNext()) {
      types.add(cursor.getString(0));
    }
    return types;
  }

  @Override
  public long updateClothe(Clothe clothe) {
    try {
    ContentValues values = new ContentValues();

    if (clothe.getType() != null) {
      values.put("ID_t", getIDType(clothe.getType()));
    }
    else{	
      values.put("ID_t", 0);
    }

    if (clothe.getColor() != null){
      values.put("ID_c", getIDColor(clothe.getColor()));
    }else{
      values.put("ID_c", 0);
    }

    if (clothe.getBrand() != null){
      values.put("ID_br", getIDBrand(clothe.getBrand()));
    }else{
      values.put("ID_br", 0);
    }

    if (clothe.getImageRelativePath() != null){
      values.put("image", getIDBrand(clothe.getBrand()));
    }else{
      values.put("image", 0);
    }

    long r =
        bdd.update("CLOTHES", values, "ID_clothes = ?",
            new String[] {String.valueOf(clothe.getId())});
    return r;
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  @Override
  public Outfit getOutfit(long id) {
    return null;
  }

  @Override
  public long insertClothes(Clothe clothe) {
    try {
   
    this.insertBodies(clothe.getBodies());
    this.insertBrand(clothe.getBrand());
    this.insertColor(clothe.getColor());
    long l = this.getIDBodies(clothe.getBodies());
    this.insertType(clothe.getType(), l );
      for (int i = 0; i < clothe.getWeather().size(); i++) {
      this.insertWeather(clothe.getWeather().get(i));
      }
    l = insertJustClothes(clothe);
    return l;
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  @Override
  public void removeClothes(String clothes) {
    try {
      String query = "DELETE FROM CLOTHES" + "WHERE CLOTHES.model = " + clothes;
      bdd.rawQuery(query, null);

    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

}
