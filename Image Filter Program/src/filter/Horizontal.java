package filter;

import model.Image;
import model.Pixel;

/**
 * Flips the image horizontally.
 */
public class Horizontal implements FilterFactory {

  @Override
  public Image apply(Image original) {
    Pixel[][] pixelArray = original.getPixelArray();
    int width = pixelArray[0].length;
    int height = pixelArray.length;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width / 2; col++) {
        Pixel tmp = pixelArray[row][col];
        pixelArray[row][col] = pixelArray[row][pixelArray[row].length - col - 1];
        pixelArray[row][pixelArray[row].length - col - 1] = tmp;
      }
    }
    return new Image(pixelArray);
  }
}
