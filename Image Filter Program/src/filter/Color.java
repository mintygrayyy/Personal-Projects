package filter;

import model.Image;
import model.Pixel;

/**
 * Abstract class that applies color kernel.
 */
public abstract class Color implements FilterFactory {
  private double a11;
  private double a12;
  private double a13;
  private double a21;
  private double a22;
  private double a23;
  private double a31;
  private double a32;
  private double a33;

  /**
   * Constructor for the 3x3 color kernel.
   *
   * @param a11 kernel pixel
   * @param a12 kernel pixel
   * @param a13 kernel pixel
   * @param a21 kernel pixel
   * @param a22 kernel pixel
   * @param a23 kernel pixel
   * @param a31 kernel pixel
   * @param a32 kernel pixel
   * @param a33 kernel pixel
   */
  public Color(double a11, double a12, double a13, double a21, double a22, double a23,
               double a31, double a32, double a33) {
    this.a11 = a11;
    this.a12 = a12;
    this.a13 = a13;
    this.a21 = a21;
    this.a22 = a22;
    this.a23 = a23;
    this.a31 = a31;
    this.a32 = a32;
    this.a33 = a33;

  }

  @Override
  public Image apply(Image original) {
    Pixel[][] array = original.getPixelArray();
    int length = array.length;

    for (int row = 0; row < length; row++) {
      for (int col = 0; col < array[row].length; col++) {
        array[row][col].color(a11, a12, a13, a21, a22, a23, a31, a32, a33);
      }
    }
    return new Image(array);
  }

}
