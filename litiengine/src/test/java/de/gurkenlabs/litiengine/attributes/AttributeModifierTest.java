package de.gurkenlabs.litiengine.attributes;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class AttributeModifierTest {

  @Test
  void testInitAttributeModifier() {
    final AttributeModifier<Integer> testAttributeModifier =
        new AttributeModifier<>(Modification.ADD, 5);
    assertEquals(Modification.ADD, testAttributeModifier.getModification());
    assertEquals(5.0, testAttributeModifier.getModifyValue());
    assertTrue(testAttributeModifier.isActive());
  }

  @Test
  void testModifyValueWithInactiveAttributeModifier() {
    final AttributeModifier<Integer> testAttributeModifierActive =
        new AttributeModifier<>(Modification.ADD, 5);
    final AttributeModifier<Integer> testAttributeModifierInactive =
        new AttributeModifier<>(Modification.ADD, 5);
    testAttributeModifierActive.setActive(true);
    testAttributeModifierInactive.setActive(false);

    assertEquals(7, testAttributeModifierActive.modify(2));
    assertEquals(2, testAttributeModifierInactive.modify(2));
  }

  // Test case for the `modify` method with an UNKNOWN modification type
  @Test
  public void testModifyWithUnknownModification() {
    // Arrange
    // Create an AttributeModifier object with Modification type as UNKNOWN and a modify value of 50
    AttributeModifier<Double> attributeModifier = new AttributeModifier<>(Modification.UNKNOWN, 50);
    Double originalValue = 100.0;

    // Act
    // Call the `modify` method and store the modified value
    Double modifiedValue = attributeModifier.modify(originalValue);

    // Assert
    // Check if the modified value is equal to the original value. It should be equal since the modification type is UNKNOWN
    assertEquals(originalValue, modifiedValue, "The value should not be modified for UNKNOWN modification");
  }

  // Test case for the `setModifyValue` method
  @Test
  public void testSetModifyValue() {
    // Arrange
    // Create an AttributeModifier object with an initial modify value of 50
    AttributeModifier<Double> attributeModifier = new AttributeModifier<>(Modification.ADD, 50);

    // Define a new modify value to set, which is 100.0
    double newModifyValue = 100.0;

    // Act
    // Call the `setModifyValue` method to update the modify value
    attributeModifier.setModifyValue(newModifyValue);

    // Assert
    // Check if the new modify value has been updated correctly
    assertEquals(newModifyValue, attributeModifier.getModifyValue(), "The modify value should be updated to 100.0");
  }

  // Test case for EnsureType method with unsupported type
  @Test
  public void testEnsureTypeReturnsNull() {
    // Arrange: Set up data and create an instance with unsupported type of Number (BigInteger).
    AttributeModifier<BigInteger> modifier = new AttributeModifier<>(Modification.ADD, 10);

    // Act: Call the method that uses ensureType with the unsupported type.
    BigInteger result = modifier.modify(BigInteger.TEN);

    // Assert: Check that the result is null as expected.
    assertNull(result);
  }
}
