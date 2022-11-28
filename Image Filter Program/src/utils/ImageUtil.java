package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import model.Image;
import model.Pixel;


/**
 * This class contains methods to read a PPM image from file and put its contents into a new image.
 * Also has a saving method where we take the new image and put it into the ppm file.
 */
public class ImageUtil {


  /**
   * Reads a ppm and adds the image to the model.
   *
   * @param file the path of the file
   * @param name the name of the file
   * @return an image
   */

  public static Image read(String file, String name) throws IOException {
    BufferedImage buffer;
    Pixel[][] array;

    try {
      buffer = ImageIO.read(new File(file));
    } catch (IOException e) {
      throw new IOException("File cannot be read");
    }

    try {
      Scanner sc = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found!");
    }


    if (file.endsWith("ppm")) {
      return readPPM(file, name);
    } else {
      array = new Pixel[buffer.getHeight()][buffer.getWidth()];
      for (int col = 0; col < buffer.getHeight(); col++) {
        for (int row = 0; row < buffer.getWidth(); row++) {
          Color color = new Color(buffer.getRGB(row, col));
          int red = color.getRed();
          int green = color.getGreen();
          int blue = color.getBlue();
          array[col][row] = new Pixel(red, green, blue);
        }
      }

      Image image = new Image(array);
      return image;
    }
  }

  private static Image readPPM(String file, String name) {
    Scanner sc;
    Pixel[][] pixelArray;
    try {
      sc = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      System.out.println("File " + name + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    pixelArray = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixelArray[i][j] = new Pixel(r, g, b);
      }
    }

    Image newImage = new Image(pixelArray);
    return newImage;
  }

  private static void savePPM(String fileName, Image image) throws IOException {
    FileWriter printer = new FileWriter(fileName);
    Pixel[][] pixelArray = image.getPixelArray();
    printer.write("P3" + "\n");
    printer.write("# New image" + "\n");
    printer.write(image.getWidth() + " " + image.getHeight() + "\n");
    printer.write("255\n");
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        printer.write(pixelArray[row][col].getRed() + "\n"
                + pixelArray[row][col].getGreen() + "\n"
                + pixelArray[row][col].getBlue() + "\n");
      }
    }
    printer.close();
  }

  /**
   * Saves image such as jpg and png into file.
   *
   * @param fileName name of file
   * @param image    image
   * @throws IOException if no input/output
   */
  public static void save(String fileName, Image image) throws IOException {
    String fileType = fileName.substring(fileName.length() - 3);
    Pixel[][] array = image.getPixelArray();
    int width = image.getWidth();
    int height = image.getHeight();
    if (fileType.equals("ppm")) {
      try {
        savePPM(fileName, image);
      } catch (IOException e) {
        throw new IOException("Cannot save PPM file");
      }
    } else {
      BufferedImage bufferedImage = makeBuffer(image);

      ImageIO.write(bufferedImage, fileType, new File(fileName));

    }
  }

  /**
   * Buffers an image and returns it.
   *
   * @param image the image that needs to be buffered
   * @return a bufferedImage
   */
  public static BufferedImage makeBuffer(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    Pixel[][] array = image.getPixelArray();

    BufferedImage bufferedImage;
    bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Pixel pixel = array[row][col];
        int r = pixel.getRed();
        int g = pixel.getGreen();
        int b = pixel.getBlue();

        int rgb = new Color(r, g, b).getRGB();
        bufferedImage.setRGB(col, row, rgb);
      }
    }
    return bufferedImage;
  }

}


