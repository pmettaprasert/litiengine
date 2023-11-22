package de.gurkenlabs.litiengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ValignTest {

  @Test
  void get_InvalidValue_ReturnsDefaultDown() {
    // Act
    Valign result = Valign.get("invalid");

    // Assert
    assertEquals(Valign.DOWN, result, "get method should return DOWN for invalid strings");
  }

  @Test
  void get_NullValue_ReturnsDefaultDown() {
    // Act
    Valign result = Valign.get(null);

    // Assert
    assertEquals(Valign.DOWN, result, "get method should return DOWN for null input");
  }

  @Test
  void get_EmptyValue_ReturnsDefaultDown() {
    // Act
    Valign result = Valign.get("");

    // Assert
    assertEquals(Valign.DOWN, result, "get method should return DOWN for empty string");
  }

  @Test
  void getValue_MiddleTopOnHeight50_ReturnsCorrectValue() {
    // Arrange
    Valign valign = Valign.MIDDLE_TOP; // portion = 0.25f
    float height = 50.0f;

    // Act
    float value = valign.getValue(height);

    // Assert
    assertEquals(12.5f, value, 0.001, "getValue should return correct proportional value");
  }

  @Test
  void getValue_DownOnHeight100_ReturnsCorrectValue() {
    // Arrange
    Valign valign = Valign.DOWN; // portion = 1.0f
    float height = 100.0f;

    // Act
    float value = valign.getValue(height);

    // Assert
    assertEquals(100.0f, value, 0.001, "getValue should return correct proportional value");
  }

  @Test
  void getValue_TopOnHeight10_ReturnsCorrectValue() {
    // Arrange
    Valign valign = Valign.TOP; // portion = 0.0f
    float height = 10.0f;

    // Act
    float value = valign.getValue(height);

    // Assert
    assertEquals(0.0f, value, 0.001, "getValue should return correct proportional value");
  }
}
