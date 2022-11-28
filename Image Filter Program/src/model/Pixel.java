package model;

/**
 * Represents a pixel made up of rgb values.
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;

  /**
   * Pixel with given rgb values.
   *
   * @param red   red value
   * @param green green value
   * @param blue  blue value
   */
  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }


  //clamp RGB values
  private int clamp(int value) {
    if (value < 0) {
      return 0;
    } else if (value > 255) {
      return 255;
    } else {
      return value;
    }
  }

  //set grayscale using red values
  public void setRed() {
    this.green = red;
    this.blue = red;
  }

  //set grayscale using green values
  public void setGreen() {
    this.red = green;
    this.blue = green;
  }

  //set grayscale using blue values
  public void setBlue() {
    this.red = blue;
    this.green = blue;
  }

  /**
   * Grayscale image using the max value of the three RGB components.
   */
  public void setValue() {
    int maxRG = Math.max(this.red, this.green);
    int maxRGB = Math.max(maxRG, this.blue);

    this.red = maxRGB;
    this.green = maxRGB;
    this.blue = maxRGB;
  }

  /**
   * Grayscale image using the luma value of the RGB components.
   */
  public void setLuma() {
    double luma = this.red * 0.2126 + this.green * 0.7152 + this.blue * 0.0722;

    this.red = (int) luma;
    this.green = (int) luma;
    this.blue = (int) luma;
  }

  /**
   * Grayscale image using average value of RGB components.
   */
  public void setIntensity() {
    int average = (this.red + this.green + this.blue) / 3;

    this.red = average;
    this.green = average;
    this.blue = average;
  }

  /**
   * Brighten image using the value given.
   *
   * @param value of how much to brighten image.
   */
  public void brighten(int value) {

    this.red = clamp(red + value);
    this.green = clamp(green + value);
    this.blue = clamp(blue + value);
  }

  /**
   * Darken an image using the value given.
   *
   * @param value of how much to darken image.
   */
  //darken an image
  public void darken(int value) {
    this.red = clamp(red - value);
    this.green = clamp(green - value);
    this.blue = clamp(blue - value);
  }

  public int getRed() {
    return this.red;
  }

  public int getGreen() {
    return this.green;
  }

  public int getBlue() {
    return this.blue;
  }

  public int getIntensity() {
    return (this.red + this.green + this.blue) / 3;
  }

  /**
   * Applies sepia filter to a pixel.
   *
   * @return pixel
   */
  public Pixel sepia() {
    int r = this.getRed();
    int b = this.getBlue();
    int g = this.getGreen();
    Pixel pixel = new Pixel(r, g, b);
    pixel.color(0.393, 0.769, 0.189, 0.349, 0.686, 0.168,
            0.272, 0.534, 0.131);
    return pixel;
  }

  /**
   * Applies the kernel onto a small array taken from the image.
   *
   * @param kernel array of kernel
   * @param image  image
   * @param row    row
   * @param col    col
   */
  public void convolution(double[][] kernel, Image image, int row, int col) {
    double red = 0;
    double green = 0;
    double blue = 0;
    Pixel[][] smallArray = smallArray(row, col, image, kernel.length);
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        red += smallArray[i][j].getRed() * kernel[i][j];
        green += smallArray[i][j].getGreen() * kernel[i][j];
        blue += smallArray[i][j].getBlue() * kernel[i][j];
      }
    }
    this.red = this.clamp((int) red);
    this.green = this.clamp((int) green);
    this.blue = this.clamp((int) blue);
  }

  private Pixel[][] smallArray(int row, int col, Image image, int size) {
    Pixel[][] array = image.getPixelArray();
    int length = array.length;
    Pixel[][] smallArray = new Pixel[size][size];
    int shift = (size - 1) / 2;
    for (int i = -(shift); i < shift + 1; i++) {
      for (int j = -(shift); j < shift + 1; j++) {
        if ((row + i) < 0
                || (row + i) > length - 1
                || (col + j) < 0
                || (col + j) > length - 1) {
          smallArray[shift + i][shift + j] = new Pixel(0, 0, 0);
        } else {
          smallArray[shift + i][shift + j] = array[row + i][col + j];
        }
      }
    }
    return smallArray;
  }

  /**
   * Applies the color kernel onto the image using the image's RGB values.
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
  public void color(double a11, double a12, double a13, double a21, double a22, double a23,
                    double a31, double a32, double a33) {
    int red = this.getRed();
    int green = this.getGreen();
    int blue = this.getBlue();

    this.red = this.clamp(
            (int) (a11 * red) + (int) (a12 * green) + (int) (a13 * blue));
    this.green = this.clamp(
            (int) (a21 * red) + (int) (a22 * green) + (int) (a23 * blue));
    this.blue = this.clamp(
            (int) (a31 * red) + (int) (a32 * green) + (int) (a33 * blue));
  }
}
