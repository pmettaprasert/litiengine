package de.gurkenlabs.litiengine.graphics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import de.gurkenlabs.litiengine.util.Imaging;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class RenderEngineTests {

  //Notes:
  //Graphics2D is class in java.awt package which deals with 2D graphics,
  // like drawinglines


  @Test
  public void testDrawText() {

    //create a mock of the class Graphics2D
    Graphics2D graphics = mock(Graphics2D.class);

    //Draw a line
    TextRenderer.render(graphics, "abc", 50.0, 100.0);

    //Check if the line is drawn at the correct position
    verify(graphics).drawString("abc", 50f, 100f);
  }

  @Test
  public void testRenderImage() {
    Graphics2D graphics = mock(Graphics2D.class);

    final Image img = Imaging.getCompatibleImage(5, 5);
    ImageRenderer.render(graphics, img, new Point2D.Double(10, 20));

    ArgumentCaptor<Image> captor = ArgumentCaptor.forClass(Image.class);
    ArgumentCaptor<AffineTransform> transCaptor = ArgumentCaptor.forClass(AffineTransform.class);
    verify(graphics).drawImage(captor.capture(), transCaptor.capture(), any());

    assertEquals(img, captor.getValue());
    assertEquals(10, transCaptor.getValue().getTranslateX());
    assertEquals(20, transCaptor.getValue().getTranslateY());
  }
}
