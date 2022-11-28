package filter;

import model.Image;
import model.Pixel;

/**
 * Abstract class for image represented through the kernel filter.
 */
public abstract class KernelFilter implements FilterFactory {
  protected double[][] kernel;

  @Override
  public Image apply(Image sourceImage) {
    Pixel[][] array = sourceImage.getPixelArray();
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        array[row][col].convolution(kernel, sourceImage, row, col);
      }
    }
    return new Image(array);
  }
}
