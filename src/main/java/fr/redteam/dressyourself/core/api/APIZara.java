package fr.redteam.dressyourself.core.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import fr.redteam.dressyourself.common.filemanager.FileManager;
import fr.redteam.dressyourself.core.Bodypart;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;

/**
 * This class give an access to an homemade database of products from www.zara.fr
 * 
 * @author Alexandre Bonhomme
 * 
 */
public final class APIZara extends APIAbstractHelper implements API {

  private static final String DB_PROTOCOL = "http";
  private static final String DB_HOST = "dev.alexandrebonhomme.fr";
  private static final String DB_PAGE = "/api.php";

  /**
   * @param manager This {@link FileManager FileManager} object is used to write image on the device
   */
  public APIZara(FileManager manager) {
    super(manager);
  }

  @Override
  public Clothe findClotheById(int id) {
    Clothe product = new Clothe();

    try {
      String jsonContent = getContent(new URL(DB_PROTOCOL, DB_HOST, DB_PAGE + "?id=" + id));
      product = buildClotheFromJSONObject(new JSONArray(jsonContent).getJSONObject(0));

    } catch (MalformedURLException e) {
      throw new DressyourselfRuntimeException(e);
    } catch (JSONException e) {
      throw new DressyourselfRuntimeException(e);
    }

    return product;
  }

  /**
   * This method return a list of {@link Clothe Clothe} objects from the online API. The research is
   * performed with the <code>query</code> only on the selected columns by the <code>filters</code>
   * argument
   * 
   * @param filters Liste de filtres séparés par des virgules
   * @return Une liste d'objets Clothe
   */
  private List<Clothe> findClothesWithFilters(String filters, String query) {
    ArrayList<Clothe> listProducts = new ArrayList<Clothe>();

    try {
      URL url = new URL(DB_PROTOCOL, DB_HOST, DB_PAGE + "?filters=" + filters + "&q=" + query);
      String jsonContent = getContent(url);

      JSONArray arrayOfProducts = new JSONArray(jsonContent);
      for (int i = 0; i < arrayOfProducts.length(); i++) {
        Clothe product = buildClotheFromJSONObject(arrayOfProducts.getJSONObject(i));
        listProducts.add(product);
      }

    } catch (MalformedURLException e) {
      throw new DressyourselfRuntimeException(e);
    } catch (JSONException e) {
      throw new DressyourselfRuntimeException(e);
    }

    return listProducts;
  }

  @Override
  public List<Clothe> findClothesByModel(String modelName) {
    return findClothesWithFilters("model", modelName);
  }

  @Override
  public List<Clothe> findClothesByBrand(String brandName) {
    return findClothesWithFilters("brand", brandName);
  }

  @Override
  public List<Clothe> findClothesByColor(String colorName) {
    return findClothesWithFilters("color", colorName);
  }

  @Override
  public List<Clothe> findClothesByType(String typeName) {
    return findClothesWithFilters("type", typeName);
  }

  @Override
  public List<Clothe> findClothesByBodyPart(Bodypart bodyPart) {
    return findClothesWithFilters("bodies", bodyPart.toString());
  }

  @Override
  public List<Clothe> findAll(String query) {
    return findClothesWithFilters("model,brand,color,type,bodies", query);
  }
}
