package fr.redteam.dressyourself.plugins.weather;

import java.util.Arrays;
import java.util.List;

// TODO Modify the entire class
public class WeatherIdentifier {

  public enum WeatherGroup {
    HARDCORE, HOT, TEMPERATE, COLD, NOTFOUND
  }

  private static List<String> listHardcore;
  private static List<String> listHot;
  private static List<String> listTemperate;
  private static List<String> listCold;

  public static void fillLists() {
    String[] arrayHardcore =
        {"tornado", "storm", "hurricane", "thunderstorms", "rain", "snow", "sleet", "showers",
            "flurries", "hail", "dust", "blustery", "thundershowers"};

    String[] arrayHot = {"sunny", "hot",};

    String[] arrayTemperate = {"cloudy", "clear", "fair"};

    String[] arrayCold = {"drizzle", "foggy", "haze", "cold", "windy", "smoky", "fog"};

    listHardcore = Arrays.asList(arrayHardcore);
    listHot = Arrays.asList(arrayHot);
    listTemperate = Arrays.asList(arrayTemperate);
    listCold = Arrays.asList(arrayCold);
  }

  public static WeatherGroup identifyGroup(String weather) {

    WeatherGroup result = WeatherGroup.NOTFOUND;
    for (String s : listHardcore) {
      if (weather.contains(s)) {
        result = WeatherGroup.HARDCORE;
        break;
      }
    }

    for (String s : listHot) {
      if (weather.contains(s)) {
        result = WeatherGroup.HOT;
        break;
      }
    }

    for (String s : listTemperate) {
      if (weather.contains(s)) {
        result = WeatherGroup.TEMPERATE;
        break;
      }
    }

    for (String s : listCold) {
      if (weather.contains(s)) {
        result = WeatherGroup.COLD;
        break;
      }
    }

    return result;
  }

}
