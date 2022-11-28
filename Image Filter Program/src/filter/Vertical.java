package filter;

import model.Image;
import model.Pixel;

/**
 * Flips the image vertically.
 */
public class Vertical implements FilterFactory {

  @Override
  public Image apply(Image original) {
    Pixel[][] pixelArray = original.getPixelArray();
    int width = pixelArray[0].length;
    int height = pixelArray.length;
    for (int row = 0; row < height / 2; row++) {
      for (int col = 0; col < width; col++) {
        Pixel tmp = pixelArray[row][col];
        pixelArray[row][col] = pixelArray[height - 1 - row][col];
        pixelArray[height - 1 - row][col] = tmp;
      }
    }
    return new Image(pixelArray);
  }
}
