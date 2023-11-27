package de.gurkenlabs.litiengine.configuration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

class GraphicConfigurationTests {

  @Test
  void testGetResolutionInFullscreenMode() {
    GraphicConfiguration config = new GraphicConfiguration();
    config.setDisplayMode(DisplayMode.FULLSCREEN);

    Dimension expectedResolution = getFullScreenResolution();
    Dimension actualResolution = config.getResolution();

    // Assert
    assertEquals(expectedResolution, actualResolution, "Resolution should match fullscreen resolution");
  }

  private Dimension getFullScreenResolution() {
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    int width = gd.getDisplayMode().getWidth();
    int height = gd.getDisplayMode().getHeight();
    return new Dimension(width, height);
  }

  @Test
  void testGetResolutionWidth() {
    GraphicConfiguration config = new GraphicConfiguration();

    // Set value for resolution width
    int expectedWidth = 1920;
    config.setResolutionWidth(expectedWidth);

    // Retrieve the resolution width using the getter
    int actualWidth = config.getResolutionWidth();

    // Assert
    assertEquals(expectedWidth, actualWidth, "getResolutionWidth should return the set resolution width");
  }

  @Test
  void testReduceFramesWhenNotFocused() {
    GraphicConfiguration config = new GraphicConfiguration();

    // Set value for reduceFramesWhenNotFocused
    config.setReduceFramesWhenNotFocused(true);

    // Assert
    assertTrue(config.reduceFramesWhenNotFocused(), "reduceFramesWhenNotFocused should return true when set to true");

    config.setReduceFramesWhenNotFocused(false);

    // Assert
    assertFalse(config.reduceFramesWhenNotFocused(), "reduceFramesWhenNotFocused should return false when set to false");
  }
}



