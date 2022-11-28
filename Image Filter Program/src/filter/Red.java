package filter;

import model.Image;
import model.Pixel;

/**
 * Changes all the image pixel's rgb value to red's value.
 */
public class Red implements FilterFactory {

  @Override
  public Image apply(Image original) {
    Pixel[][] pixelArray = original.getPixelArray();
    int width = pixelArray[0].length;
    int height = pixelArray.length;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        pixelArray[row][col].setRed();
      }
    }
    return new Image(pixelArray);
  }
}