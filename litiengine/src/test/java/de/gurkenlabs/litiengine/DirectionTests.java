package de.gurkenlabs.litiengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;

import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.geom.Point2D;

class DirectionTests {

  @ParameterizedTest(name = "fromAngle_Down angle={0}")
  @CsvSource({"0.0d", "0.1d", "44.9d", "315.0d", "315.1d", "359.9d", "360.0d"})
  void fromAngle_Down(double angle) {
    // assert
    assertEquals(Direction.DOWN, Direction.fromAngle(angle));
  }

  @ParameterizedTest(name = "fromAngle_Right angle={0}")
  @CsvSource({"45.0d", "45.1d", "134.9d"})
  void fromAngle_Right(double angle) {
    // assert
    assertEquals(Direction.RIGHT, Direction.fromAngle(angle));
  }

  @ParameterizedTest(name = "fromAngle_Up angle={0}")
  @CsvSource({"135.0d", "135.1d", "224.9d"})
  void fromAngle_Up(double angle) {
    // assert
    assertEquals(Direction.UP, Direction.fromAngle(angle));
  }

  @ParameterizedTest(name = "fromAngle_Left angle={0}")
  @CsvSource({"225.0d", "225.1d", "314.9d"})
  void fromAngle_Left(double angle) {
    // assert
    assertEquals(Direction.LEFT, Direction.fromAngle(angle));
  }

  @ParameterizedTest(name = "fromAngle_UsuallyNoUndefined angle={0}")
  @CsvSource({"-360.1d", "-360.0d", "-359.9d", "-0.1", "360.1", "719.9", "720.0", "720.1"})
  void fromAngle_UsuallyNoUndefined(double angle) {
    // assert
    assertEquals(Direction.DOWN, Direction.fromAngle(angle));
  }

  @ParameterizedTest(name = "fromAngle_ForceUndefined value={0}, angle={1}")
  @CsvSource({"-0.1d, 10.0d", "360.1d, 10.0d"})
  void fromAngle_ForceUndefined(double value, double angle) {
    // arrange
    MockedStatic<GeometricUtilities> geomUtilsMockStatic = mockStatic(GeometricUtilities.class);

    // assert
    geomUtilsMockStatic
        .when(() -> GeometricUtilities.normalizeAngle(anyDouble()))
        .thenReturn(value);
    assertEquals(Direction.UNDEFINED, Direction.fromAngle(angle));

    // cleanup
    geomUtilsMockStatic.close();
  }

  @ParameterizedTest
  @MethodSource("getOppositeParameters")
  void testGetOpposite(Direction initialDirection, Direction expectedOppositeDirection) {
    // act
    Direction actualOpposite = initialDirection.getOpposite();

    // assert
    assertEquals(expectedOppositeDirection, actualOpposite);
  }

  private static Stream<Arguments> getOppositeParameters() {
    // arrange
    return Stream.of(
        Arguments.of(Direction.LEFT, Direction.RIGHT),
        Arguments.of(Direction.RIGHT, Direction.LEFT),
        Arguments.of(Direction.UP, Direction.DOWN),
        Arguments.of(Direction.DOWN, Direction.UP),
        Arguments.of(Direction.UNDEFINED, Direction.UNDEFINED));
  }

  @Test
  void testFromFlagValue() {
    // Test case 1: Valid flag value for DOWN
    byte flagValueDown = Direction.DOWN.toFlagValue();
    Direction resultDown = Direction.fromFlagValue(flagValueDown);
    assertEquals(Direction.DOWN, resultDown);

    // Test case 2: Valid flag value for RIGHT
    byte flagValueRight = Direction.RIGHT.toFlagValue();
    Direction resultRight = Direction.fromFlagValue(flagValueRight);
    assertEquals(Direction.RIGHT, resultRight);

    // Test case 3: Valid flag value for UP
    byte flagValueUp = Direction.UP.toFlagValue();
    Direction resultUp = Direction.fromFlagValue(flagValueUp);
    assertEquals(Direction.UP, resultUp);

    // Test case 4: Valid flag value for LEFT
    byte flagValueLeft = Direction.LEFT.toFlagValue();
    Direction resultLeft = Direction.fromFlagValue(flagValueLeft);
    assertEquals(Direction.LEFT, resultLeft);

    // Test case 5: Invalid flag value (not associated with any direction)
    byte invalidFlagValue = 99;
    Direction resultInvalid = Direction.fromFlagValue(invalidFlagValue);
    assertEquals(Direction.UNDEFINED, resultInvalid);

    // Test case 6: Loop through all possible flag values to ensure coverage
    for (Direction direction : Direction.values()) {
      byte flagValue = direction.toFlagValue();
      Direction result = Direction.fromFlagValue(flagValue);
      assertEquals(direction, result);
    }
  }

  /*Testing Point Projection and Distance Calculation.*/
  @Test
  void testPointProjectionAndDistanceCalculation() {
    // Initial point
    Point2D startPoint = new Point2D.Double(50, 50);

    // Expected distance for projection
    double distance = 100;

    // Project the point at a 45-degree angle for the distance of 100
    Point2D projectedPoint = GeometricUtilities.project(startPoint, 45, distance);

    // Calculate the actual distance between the original and projected point
    double actualDistance = GeometricUtilities.distance(startPoint, projectedPoint);

    // Assert that the actual distance matches the expected distance
    assertEquals(distance, actualDistance, 0.01, "The distance between the points should match the projected distance.");
  }


}
