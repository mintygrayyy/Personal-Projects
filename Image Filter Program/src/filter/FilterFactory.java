package filter;

import model.Image;

/**
 * Interface that acts as a center to apply methods that filter an image.
 */
public interface FilterFactory {
  /**
   * Applies filter to image and returns a new image with the filter applied to it.
   *
   * @param original image
   * @return new filtered image
   */
  public Image apply(Image original);
}
