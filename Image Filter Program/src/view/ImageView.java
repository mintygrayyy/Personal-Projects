package view;

import java.io.IOException;

/**
 * Interface representing the image's view in the MVC.
 */
public interface ImageView {

  /**
   * Takes a message and appends it to the appendable.
   * @param message message to be rendered
   * @throws IOException when there is no appendable
   */
  void renderMessage(String message) throws IOException;
}
