package de.gurkenlabs.litiengine.attributes;

import org.junit.jupiter.api.Test;

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
}
