package filter;

import model.Image;
import model.Pixel;

/**
 * Changes all the image pixel's rgb values by brightening pixels with the given value.
 */
public class Brighten implements FilterFactory {
  private int value;

  public Brighten(int value) {
    this.value = value;
  }

  @Override
  public Image apply(Image original) {
    Pixel[][] pixelArray = original.getPixelArray();
    int width = pixelArray[0].length;
    int height = pixelArray.length;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        pixelArray[row][col].brighten(value);
      }
    }
    return new Image(pixelArray);
  }
}
