package view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.Image;
import model.Pixel;

/**
 * Class that creates a histogram with red,green,blue, intensity values for the image.
 */
public class Histogram extends JPanel {

  private int[] red;
  private int[] red2;
  private int[] green;
  private int[] green2;
  private int[] blue;
  private int[] blue2;
  private int[] intensity;
  private int[] intensity2;

  /**
   * Initializes the histogram.
   *
   * @param image used for histogram
   */
  public Histogram(Image image) {
    super();
    int width = image.getWidth();
    int height = image.getHeight();

    this.setSize(width, height);

    red = new int[256];
    green = new int[256];
    blue = new int[256];
    intensity = new int[256];

    Pixel[][] array = image.getPixelArray();
    for (int i = 0; i < 256; i++) {
      red[i] = 0;
      green[i] = 0;
      blue[i] = 0;
      intensity[i] = 0;
    }

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        red[array[i][j].getRed()]++;
        green[array[i][j].getGreen()]++;
        blue[array[i][j].getBlue()]++;
        intensity[array[i][j].getIntensity()]++;
      }
    }
    red2 = red;
    green2 = green;
    blue2 = blue;
    intensity2 = intensity;
  }

  /**
   * Paint the histogram.
   */
  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    this.setBackground(Color.BLACK);
    Graphics2D g2D = (Graphics2D) graphics;

    for (int i = 0; i < red2.length - 2; i++) {
      g2D.setColor(Color.RED);
      g2D.drawLine(6 * i, red2[i], 6 * (i + 1), red2[i + 1]);
    }

    for (int i = 0; i < green2.length - 2; i++) {
      g2D.setColor(Color.GREEN);
      g2D.drawLine(3 * i, green2[i], 3 * (i + 1), green2[i + 1]);
    }

    for (int i = 0; i < blue2.length - 2; i++) {
      g2D.setColor(Color.BLUE);
      g2D.drawLine(4 * i, blue2[i], 4 * (i + 1), blue2[i + 1]);
    }

    for (int i = 0; i < intensity2.length - 2; i++) {
      g2D.setColor(Color.WHITE);
      g2D.drawLine(2 * i, intensity2[i], 2 * (i + 1), intensity2[i + 1]);
    }
  }

  /**
   * Updates the histogram according to the image.
   *
   * @param image image used to make histogram
   */
  public void updateHistogram(Image image) {

    Pixel[][] array = image.getPixelArray();

    red = green = blue = intensity = new int[256];

    int width = getWidth();
    int height = getHeight();

    for (int i = 0; i < 256; i++) {
      red[i] = green[i] = blue[i] = intensity[i] = 0;
    }

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        red[array[i][j].getRed()]++;
        green[array[i][j].getGreen()]++;
        blue[array[i][j].getBlue()]++;
        intensity[array[i][j].getIntensity()]++;
      }
    }
    red2 = red;
    green2 = green;
    blue2 = blue;
    intensity2 = intensity;
  }
}





