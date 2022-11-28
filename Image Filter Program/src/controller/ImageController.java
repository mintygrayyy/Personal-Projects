package controller;


/**
 * Controller interface for the image controller.
 */
public interface ImageController {

  /**
   * Creates a new instance of an image processor.
   *
   * @throws IllegalArgumentException if input cannot be read or output cannot be transmitted
   */
  void imageProcessor() throws IllegalArgumentException;

}
