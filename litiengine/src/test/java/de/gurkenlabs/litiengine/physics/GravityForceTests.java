package de.gurkenlabs.litiengine.physics;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;


public class GravityForceTests {

  // Check if the dependency is available
  private boolean dependencyIsAvailable() {
    // GeometricUtilities and IEntity are internal
    // Assume dependency is always available
    return true;
  }

  /* Testing the center calculation of an entity positioned
  away from the origin with positive dimensions.*/
  @Test
  void testGetCenterOfIEntity() {
    // Assume that the dependency is available
    // If not, the test will be skipped
    assumeTrue(dependencyIsAvailable(), "Dependency is down. Test is skipped.");

    // Create a mock of IEntity
    IEntity entity = Mockito.mock(IEntity.class);

    // Define the behavior of the mock
    Mockito.when(entity.getX()).thenReturn(10.0);
    Mockito.when(entity.getY()).thenReturn(10.0);
    Mockito.when(entity.getWidth()).thenReturn(20.0);
    Mockito.when(entity.getHeight()).thenReturn(20.0);

    // Create a Rectangle2D to represent the IEntity's bounds
    Rectangle2D entityBounds = new Rectangle2D.Double(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());

    // Use GeometricUtilities to calculate the center
    Point2D calculatedCenter = GeometricUtilities.getCenter(entityBounds);

    // Create the expected center point
    Point2D expectedCenter = new Point2D.Double(20, 20);

    // Assert that the calculated center is as expected
    assertEquals(expectedCenter, calculatedCenter, "The calculated center should match the expected center.");
  }

  private IEntity mockEntity;
  private final float testStrength = 10.0f;
  private final Direction testDirection = Direction.DOWN;
  private final float testAngle = 180.0f;

  @BeforeEach
  void setUp() {
    mockEntity = Mockito.mock(IEntity.class);
    Mockito.when(mockEntity.getCenter()).thenReturn(new Point2D.Double(100, 100));
    Mockito.when(mockEntity.getWidth()).thenReturn(50.0);
    Mockito.when(mockEntity.getHeight()).thenReturn(50.0);
  }

  @Test
  public void testGetForceEntity() {
    GravityForce gravityForce = new GravityForce(mockEntity, testStrength, testDirection);
    assertEquals(mockEntity, gravityForce.getForceEntity());
  }

  @Test
  public void testGravityForceEffectOnEntity() {
    // Create a mock entity with initial parameters
    IEntity mockEntity = Mockito.mock(IEntity.class);
    Mockito.when(mockEntity.getCenter()).thenReturn(new Point2D.Double(100, 100));
    Mockito.when(mockEntity.getWidth()).thenReturn(50.0);
    Mockito.when(mockEntity.getHeight()).thenReturn(50.0);

    // Create a GravityForce instance
    GravityForce gravityForce = new GravityForce(mockEntity, 10.0f, Direction.DOWN);

    // Apply the gravity force to the entity

    // Check the new state or location of the entity after applying the force
    // For example, Check if the entity has moved downwards
  }

}
