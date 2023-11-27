package de.gurkenlabs.litiengine.configuration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Locale;

class ClientConfigurationTests {
  @Test
  /*It should test the default values of the constructor.*/
  void testConstructor() {
    ClientConfiguration config = new ClientConfiguration();

    // Assert
    assertEquals(60, config.getMaxFps(), "Default max FPS should be 60");
    assertFalse(config.showGameMetrics(), "Default for showGameMetrics should be false");
    assertFalse(config.exitOnError(), "Default for exitOnError should be false");
    assertEquals(Locale.getDefault().getLanguage(), config.getLanguage(), "Default language should match system default");
    assertEquals(Locale.getDefault().getCountry(), config.getCountry(), "Default country should match system default");
  }

  @Test
  /* It should test the getLocate method under different conditions.*/
  void testGetLocale() {
    ClientConfiguration config = new ClientConfiguration();

    config.setLanguage("en");
    config.setCountry("US");

    // Assert
    assertEquals(new Locale("en", "US"), config.getLocale(), "getLocale should return correct Locale with country and language set");

    config.setCountry("");

    // Assert
    assertEquals(new Locale("en"), config.getLocale(), "getLocale should return correct Locale with only language set");
  }
}
