import org.junit.Test;

import java.io.IOException;

import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing image view implementation.
 */

public class ImageViewImplTest {
  StringBuilder output = new StringBuilder();
  ImageView view = new ImageViewImpl(output);
  StringBuilder output2 = new StringBuilder();
  ImageView view2 = new ImageViewImpl(output2);
  StringBuilder output3 = new StringBuilder();
  ImageView view3 = new ImageViewImpl(output3);
  StringBuilder message = new StringBuilder("Message");
  StringBuilder load = new StringBuilder("Image loaded");
  StringBuilder exception = new StringBuilder("Vido exception thrown");

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions() throws Exception {
    new ImageViewImpl(null);
  }

  @Test
  public void testConstructor() throws IOException {
    StringBuilder output = new StringBuilder();
    ImageView test = new ImageViewImpl(output);
    test.renderMessage("testing");
    assertEquals(output.toString(), "testing");

    StringBuilder output2 = new StringBuilder(" ");
    ImageView test2 = new ImageViewImpl(output2);
    assertEquals(output2.toString(), " ");

    test.renderMessage("Holly 3");
    assertEquals(output.toString(), "testingHolly 3");
  }

  @Test
  public void testRenderMessage() throws IOException {
    view.renderMessage("Message");
    assertEquals(output.toString(), message.toString());
    view2.renderMessage("Image loaded");
    assertEquals(output2.toString(), load.toString());
    view3.renderMessage("Vido exception thrown");
    assertEquals(output3.toString(), exception.toString());
  }


}
