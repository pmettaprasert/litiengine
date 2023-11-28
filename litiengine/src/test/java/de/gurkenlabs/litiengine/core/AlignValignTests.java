package de.gurkenlabs.litiengine.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.gurkenlabs.litiengine.Align;
import de.gurkenlabs.litiengine.Valign;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class AlignValignTests {

  @ParameterizedTest
  @EnumSource(Align.class)
  void testAlign(Align align) {
    assertEquals(align, Align.get(align.name()));
    assertEquals(align, Align.get(align.name().toLowerCase()));
  }

  @ParameterizedTest
  @EnumSource(Valign.class)
  void testValign(Valign valign) {
    assertEquals(valign, Valign.get(valign.name()));
    assertEquals(valign, Valign.get(valign.name().toLowerCase()));
  }

  @Test
  void testAlignGetWithEmptyAlignString() {
    assertEquals(Align.CENTER, Align.get(""));
    assertEquals(Align.CENTER, Align.get(null));
  }

  @Test
  void testGet() {
    assertEquals(Valign.DOWN, Valign.get(""));
    assertEquals(Valign.DOWN, Valign.get(null));
  }

  @Test
  void testAlignGetWithInvalidAlignString() {
    assertEquals(Align.CENTER, Align.get("INVALID_ALIGN"));
  }

  @Test
  void testEnumValues() {
    assertEquals(Valign.DOWN, Valign.get("DOWN"));
    assertEquals(Valign.MIDDLE, Valign.get("MIDDLE"));
    assertEquals(Valign.TOP, Valign.get("TOP"));
    assertEquals(Valign.MIDDLE_TOP, Valign.get("MIDDLE_TOP"));
    assertEquals(Valign.MIDDLE_DOWN, Valign.get("MIDDLE_DOWN"));

    // Test case-insensitive matching
    assertEquals(Valign.MIDDLE, Valign.get("middle"));

    // Test invalid input
    assertEquals(Valign.DOWN, Valign.get(null));
    assertEquals(Valign.DOWN, Valign.get(""));
    assertEquals(Valign.DOWN, Valign.get("INVALID"));
  }

//  ******** Integration Test  *********
//  @Test
//  void testProportionalValues() {
//    float height = 100f;
//
//    assertEquals(25f, Valign.MIDDLE_TOP.getValue(height));
//    assertEquals(50f, Valign.MIDDLE.getValue(height));
//    assertEquals(75f, Valign.MIDDLE_DOWN.getValue(height));
//    assertEquals(100f, Valign.DOWN.getValue(height));
//    assertEquals(0f, Valign.TOP.getValue(height));
//
//    // Test double and int values
//    assertEquals(25.0, Valign.MIDDLE_TOP.getValue(50.0));
//    assertEquals(25, Valign.MIDDLE_TOP.getValue(50));
//  }
//
//  @Test
//  void testLocationCalculation() {
//    double height = 100.0;
//    double objectHeight = 30.0;
//
//    assertEquals(10.0, Valign.MIDDLE_TOP.getLocation(height, objectHeight));
//    assertEquals(35.0, Valign.MIDDLE.getLocation(height, objectHeight));
//    assertEquals(60.0, Valign.MIDDLE_DOWN.getLocation(height, objectHeight));
//    assertEquals(85.0, Valign.DOWN.getLocation(height, objectHeight));
//    assertEquals(0.0, Valign.TOP.getLocation(height, objectHeight));
//
//    // Test when objectHeight is greater than height
//    assertEquals(0.0, Valign.MIDDLE_TOP.getLocation(height, height + 1.0));
//    assertEquals(height - objectHeight, Valign.MIDDLE.getLocation(height, height + 1.0));
//  }
}
