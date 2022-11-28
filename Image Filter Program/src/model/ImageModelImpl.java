package model;

import java.util.HashMap;
import java.util.Map;

import filter.FilterFactory;

/**
 * Implements the ImageModel.
 */
public class ImageModelImpl implements ImageModel {

  private Map<String, Image> currentImage;

  public ImageModelImpl() {
    this.currentImage = new HashMap<String, Image>();
  }

  public void putImage(String imageName, Image image) {
    this.currentImage.put(imageName, image);
  }

  /**
   * Looks for the image using the name it is given.
   *
   * @param imageName name of image to be found.
   * @return Image if the image name exists.
   * @throws IllegalArgumentException if image name does not exist.
   */
  public Image findImage(String imageName) throws IllegalArgumentException {
    Image foundImage = currentImage.get(imageName);
    if (foundImage == null) {
      throw new IllegalArgumentException("This image does not exist.");
    }
    return foundImage;
  }

  /**
   * Applies the filter onto the pixel array of the input image.
   *
   * @param filter       name of filter being called
   * @param originalName of image
   * @param newName      of image after being filtered
   */
  public void applyFilter(FilterFactory filter, String newName, String originalName) {
    Image original = this.findImage(originalName);
    Image pixelArray = new Image(original.getPixelArray());
    Image dest = pixelArray.filterCopy(filter);
    this.putImage(newName, dest);
  }


}
