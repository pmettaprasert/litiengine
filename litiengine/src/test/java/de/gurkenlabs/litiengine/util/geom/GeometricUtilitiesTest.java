package de.gurkenlabs.litiengine.util.geom;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GeometricUtilitiesTest {
  @Test
  void testExtrude_PositiveExtension() {
    // Arrange

    // Create an original Rectangle2D object with specified dimensions.
    Rectangle2D original = new Rectangle2D.Double(10, 10, 20, 20);
    // Set the extension amount. Positive extension should expand the rectangle.
    double extension = 5;

    // Act

    // Call the extrude method from the GeometricUtilities class.
    // This should return a new rectangle with expanded dimensions.
    Rectangle2D extruded = GeometricUtilities.extrude(original, extension);

    // Assert

    // Validate each dimension of the extruded rectangle.
    // The X and Y should have been reduced by the extension amount,
    // and the width and height should have increased by twice the extension amount.
    assertEquals(5, extruded.getX());
    assertEquals(5, extruded.getY());
    assertEquals(30, extruded.getWidth());
    assertEquals(30, extruded.getHeight());
  }

  @Test
  void testExtrude_NegativeExtension() {
    // Arrange

    // Create another original Rectangle2D object.
    Rectangle2D original = new Rectangle2D.Double(10, 10, 20, 20);
    // Set a negative extension amount. This should shrink the rectangle.
    double extension = -5;

    // Act

    // Call the extrude method to get the new rectangle dimensions.
    Rectangle2D extruded = GeometricUtilities.extrude(original, extension);

    // Assert

    // Verify the dimensions. For a negative extension, the X and Y should increase,
    // and the width and height should decrease.
    assertEquals(15, extruded.getX());
    assertEquals(15, extruded.getY());
    assertEquals(10, extruded.getWidth());
    assertEquals(10, extruded.getHeight());
  }

  @Test
  void testExtrude_ZeroExtension() {
    // Arrange

    // Create another original Rectangle2D object.
    Rectangle2D original = new Rectangle2D.Double(10, 10, 20, 20);
    // Set extension as zero. This should return a rectangle with the same dimensions.
    double extension = 0;

    // Act

    // Call the extrude method to get the new rectangle dimensions.
    Rectangle2D extruded = GeometricUtilities.extrude(original, extension);

    // Assert

    // Verify the dimensions. With zero extension, all dimensions should remain unchanged.
    assertEquals(10, extruded.getX());
    assertEquals(10, extruded.getY());
    assertEquals(20, extruded.getWidth());
    assertEquals(20, extruded.getHeight());
  }


}
