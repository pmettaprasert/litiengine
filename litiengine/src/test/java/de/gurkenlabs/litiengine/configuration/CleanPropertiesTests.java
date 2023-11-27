package de.gurkenlabs.litiengine.configuration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Enumeration;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
class CleanPropertiesTests {
  @Test
  /*It should return the keys in a sorted order,
  as it uses a TreeSet to sort the keySet of the Properties object.*/
  void testKeysOrder() {
    CleanProperties props = new CleanProperties();
    props.setProperty("b", "value1");
    props.setProperty("a", "value2");
    props.setProperty("c", "value3");

    Enumeration<Object> keys = props.keys();

    // Assert
    assertEquals("a", keys.nextElement(), "First key should be 'a'");
    assertEquals("b", keys.nextElement(), "Second key should be 'b'");
    assertEquals("c", keys.nextElement(), "Third key should be 'c'");
  }

  @Test
  /*It should store the properties without the first line and in sorted order.
  It requires intercepting the output stream to verify the stored content.*/
  void testStoreWithoutFirstLine() throws IOException {
    CleanProperties props = new CleanProperties();
    props.setProperty("a", "value1");
    props.setProperty("b", "value2");
    props.setProperty("c", "value3");

    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    props.store(outStream, null);

    String storedContent = new String(outStream.toByteArray());

    // Assert
    assertFalse(storedContent.startsWith("#"), "Stored content should not start with a comment line");
    assertTrue(storedContent.contains("a=value1\n"), "Stored content should contain 'a=value1'");
    assertTrue(storedContent.contains("b=value2\n"), "Stored content should contain 'b=value2'");
    assertTrue(storedContent.contains("c=value3\n"), "Stored content should contain 'c=value3'");
  }
}

