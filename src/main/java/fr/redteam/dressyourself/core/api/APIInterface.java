package main.java.fr.redteam.dressyourself.core.api;

import java.util.ArrayList;

import main.java.fr.redteam.dressyourself.core.clothes.Clothe;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
public interface APIInterface {

  public ArrayList<Clothe> getClothesByCategory(String category);

  public Clothe getClothe(String id);
}
