package model;

import filter.FilterFactory;

/**
 * This class implements Image operations needed for the image.
 */
public class Image {

  private Pixel[][] pixelArray;
  private int width;
  private int height;

  /**
   * Creates image using an array of pixels.
   *
   * @param load array of pixels
   */
  public Image(Pixel[][] load) {
    this.pixelArray = new Pixel[load.length][load[0].length];
    for (int row = 0; row < load.length; row++) {
      for (int col = 0; col < load[0].length; col++) {
        pixelArray[row][col] = load[row][col];
      }
    }
    this.width = load[0].length;
    this.height = load.length;
  }

  /**
   * Returns 2D pixel array.
   *
   * @return 2D pixel array from the image.
   */
  public Pixel[][] getPixelArray() {
    Pixel[][] copy = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Pixel pixel = new Pixel(this.pixelArray[i][j].getRed(), this.pixelArray[i][j].getGreen(),
                this.pixelArray[i][j].getBlue());
        copy[i][j] = pixel;
      }
    }
    return copy;
  }

  /**
   * Applies filter onto a copy of the original image.
   *
   * @param filter being applied to image.
   * @return filtered version of copy of image.
   */
  public Image filterCopy(FilterFactory filter) {
    Pixel[][] pixelArray = this.getPixelArray();
    Image copy = new Image(pixelArray.clone());
    return filter.apply(copy);
  }


  public int getWidth() {
    return this.width;
  }


  public int getHeight() {
    return this.height;
  }


}
