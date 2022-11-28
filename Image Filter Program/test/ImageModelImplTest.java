import org.junit.Before;
import org.junit.Test;

import filter.Blue;
import filter.Blur;
import filter.Brighten;
import filter.Darken;
import filter.Green;
import filter.Horizontal;
import filter.Intensity;
import filter.Luma;
import filter.Red;
import filter.Sepia;
import filter.Sharpen;
import filter.Value;
import filter.Vertical;
import model.Image;
import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Class for testing image model implementation.
 */
public class ImageModelImplTest {

  ImageModel model;
  Image original;

  Image testArray;
  Image horizontal;
  Image vertical;
  Image brighten10;
  Image brightenNeg10;
  Image red;
  Image blue;
  Image green;
  Image luma;
  Image value;
  Image intensity;
  Image sepia;

  Pixel p1;
  Pixel p2;
  Pixel p3;
  Pixel p4;
  Pixel p5;
  Pixel p6;
  Pixel p7;
  Pixel p8;
  Pixel p9;

  Pixel one = new Pixel(0, 0, 0);
  Pixel two = new Pixel(68, 54, 0);
  Pixel three = new Pixel(255, 0, 0);
  Pixel four = new Pixel(0, 255, 0);
  Pixel five = new Pixel(0, 0, 255);
  Pixel six = new Pixel(8, 15, 27);


  Pixel[][] arr = {{one, two, three}, {four, five, six}};
  Image img = new Image(arr);

  Pixel brightenOne = new Pixel(10, 10, 10);
  Pixel brightenTwo = new Pixel(78, 68, 10);
  Pixel brightenThree = new Pixel(255, 10, 10);
  Pixel[][] brightArray = {{brightenOne, brightenTwo, brightenThree}};
  Image brightenImage = new Image(brightArray);
  Pixel darkenFour = new Pixel(0, 245, 0);
  Pixel darkenFive = new Pixel(0, 0, 245);
  Pixel darkenSix = new Pixel(0, 5, 17);
  Pixel[][] darkArray = {{darkenFour, darkenFive, darkenSix}};
  Image darkenImage = new Image(darkArray);

  Pixel r1 = new Pixel(0, 0, 0);
  Pixel r2 = new Pixel(68, 68, 68);
  Pixel r3 = new Pixel(255, 255, 255);
  Pixel r4 = new Pixel(0, 0, 0);
  Pixel r5 = new Pixel(0, 0, 8);
  Pixel r6 = new Pixel(8, 8, 8);

  Pixel[][] redArray = {{r1, r2, r3}, {r4, r5, r6}};
  Image redImage = new Image(redArray);

  Pixel g1 = new Pixel(0, 0, 0);
  Pixel g2 = new Pixel(54, 54, 54);
  Pixel g3 = new Pixel(0, 0, 0);
  Pixel g4 = new Pixel(255, 255, 255);
  Pixel g5 = new Pixel(0, 0, 0);
  Pixel g6 = new Pixel(15, 15, 15);

  Pixel[][] greenArray = {{g1, g2, g3}, {g4, g5, g6}};
  Image greenImage = new Image(greenArray);

  Pixel b1 = new Pixel(0, 0, 0);
  Pixel b2 = new Pixel(0, 0, 0);
  Pixel b3 = new Pixel(0, 0, 0);
  Pixel b4 = new Pixel(0, 0, 0);
  Pixel b5 = new Pixel(255, 255, 255);
  Pixel b6 = new Pixel(27, 27, 27);

  Pixel[][] blueArray = {{b1, b2, b3}, {b4, b5, b6}};
  Image blueImage = new Image(blueArray);

  Pixel i1 = new Pixel(50, 50, 50);
  Pixel i2 = new Pixel(((150 + 250 + 50) / 3), ((150 + 250 + 50) / 3),
          ((150 + 250 + 50) / 3));
  Pixel i3 = new Pixel(((40 + 50 + 55) / 3), ((40 + 50 + 55) / 3),
          ((40 + 50 + 55) / 3));
  Pixel i4 = new Pixel(((50 + 57 + 56) / 3), ((50 + 57 + 56) / 3), ((50 + 57 + 56) / 3));
  Pixel i5 = new Pixel(((14 + 53 + 20) / 3), ((14 + 53 + 20) / 3), ((14 + 53 + 20) / 3));
  Pixel i6 = new Pixel(((01 + 23 + 23) / 3), ((01 + 23 + 23) / 3), ((01 + 23 + 23) / 3));

  Pixel[][] intensityArray = {{i1, i2, i3}, {i4, i5, i6}};
  Image intensityImage = new Image(intensityArray);

  int l1 = (int) (0.2126 * one.getRed() + 0.7152 * one.getGreen()
          + 0.0722 * one.getBlue());
  Pixel luma1 = new Pixel(l1, l1, l1);
  int l2 = (int) (0.2126 * two.getRed() + 0.7152 * two.getGreen()
          + 0.0722 * two.getBlue());
  Pixel luma2 = new Pixel(l2, l2, l2);
  int l3 = (int) (0.2126 * three.getRed() + 0.7152 * three.getGreen()
          + 0.0722 * three.getBlue());
  Pixel luma3 = new Pixel(l3, l3, l3);
  int l4 = (int) (0.2126 * four.getRed() + 0.7152 * four.getGreen()
          + 0.0722 * four.getBlue());
  Pixel luma4 = new Pixel(l4, l4, l4);
  int l5 = (int) (0.2126 * five.getRed() + 0.7152 * five.getGreen()
          + 0.0722 * five.getBlue());
  Pixel luma5 = new Pixel(l5, l5, l5);

  int l6 = (int) (0.2126 * six.getRed() + 0.7152 * six.getGreen()
          + 0.0722 * six.getBlue());


  Pixel luma6 = new Pixel(l6, l6, l6);

  Pixel[][] lumaArray = {{luma1, luma2, luma3}, {luma4, luma5, luma6}};
  Image lumaImage = new Image(lumaArray);

  Pixel v1 = new Pixel(50, 50, 50);
  Pixel v2 = new Pixel(250, 250, 250);
  Pixel v3 = new Pixel(55, 55, 55);
  Pixel v4 = new Pixel(57, 57, 57);
  Pixel v5 = new Pixel(53, 53, 53);
  Pixel v6 = new Pixel(23, 23, 23);


  Pixel[][] valueArray = {{v1, v2, v3}, {v4, v5, v6}};
  Image imageValue = new Image(valueArray);


  Pixel[][] flipVertical = {{four, five, six}, {one, two, three}};
  Image verticalImage = new Image(flipVertical);

  Pixel[][] flipHorizontal = {{three, two, one}, {six, five, four}};
  Image horizontalImage = new Image(flipHorizontal);


  /**
   * Setup for testing.
   */
  @Before
  public void setup() {
    this.p1 = new Pixel(23, 55, 40);
    this.p2 = new Pixel(0, 34, 0);
    this.p3 = new Pixel(255, 90, 89);
    this.p4 = new Pixel(43, 0, 43);
    this.p5 = new Pixel(255, 255, 0);
    this.p6 = new Pixel(15, 32, 8);

    Pixel sepia1 = p1.sepia();
    Pixel sepia2 = p2.sepia();
    Pixel sepia3 = p3.sepia();
    Pixel sepia4 = p4.sepia();
    Pixel sepia5 = p5.sepia();
    Pixel sepia6 = p6.sepia();


    Pixel bright1 = new Pixel(33, 65, 50);
    Pixel bright2 = new Pixel(10, 44, 10);
    Pixel bright3 = new Pixel(255, 100, 99);
    Pixel bright4 = new Pixel(53, 10, 53);
    Pixel bright5 = new Pixel(255, 255, 10);
    Pixel bright6 = new Pixel(25, 42, 18);

    Pixel dark1 = new Pixel(13, 45, 30);
    Pixel dark2 = new Pixel(0, 24, 0);
    Pixel dark13 = new Pixel(245, 80, 79);
    Pixel dark14 = new Pixel(33, 0, 33);
    Pixel dark5 = new Pixel(245, 245, 0);
    Pixel dark6 = new Pixel(5, 22, 0);


    int luma1 = (int) (0.2126 * 23 + 0.7152 * 55 + 0.0722 * 40);
    int luma2 = (int) (0.2126 * 0 + 0.7152 * 34 + 0.0722 * 0);
    int luma3 = (int) (0.2126 * 255 + 0.7152 * 90 + 0.0722 * 89);
    int luma4 = (int) (0.2126 * 43 + 0.7152 * 0 + 0.0722 * 43);
    int luma5 = (int) (0.2126 * 255 + 0.7152 * 255 + 0.0722 * 0);
    int luma6 = (int) (0.2126 * 15 + 0.7152 * 32 + 0.0722 * 8);

    Pixel lp1 = new Pixel(luma1, luma1, luma1);
    Pixel lp2 = new Pixel(luma2, luma2, luma2);
    Pixel lp3 = new Pixel(luma3, luma3, luma3);
    Pixel lp4 = new Pixel(luma4, luma4, luma4);
    Pixel lp5 = new Pixel(luma5, luma5, luma5);
    Pixel lp6 = new Pixel(luma6, luma6, luma6);


    int intensity1 = (23 + 55 + 40) / 3;
    int intensity2 = (0 + 34 + 0) / 3;
    int intensity3 = (255 + 90 + 89) / 3;
    int intensity4 = (43 + 0 + 43) / 3;
    int intensity5 = (255 + 255 + 0) / 3;
    int intensity6 = (15 + 32 + 8) / 3;

    Pixel ip1 = new Pixel(intensity1, intensity1, intensity1);
    Pixel ip2 = new Pixel(intensity2, intensity2, intensity2);
    Pixel ip3 = new Pixel(intensity3, intensity3, intensity3);
    Pixel ip4 = new Pixel(intensity4, intensity4, intensity4);
    Pixel ip5 = new Pixel(intensity5, intensity5, intensity5);
    Pixel ip6 = new Pixel(intensity6, intensity6, intensity6);

    Pixel max1 = new Pixel(55, 55, 55);
    Pixel max2 = new Pixel(34, 34, 34);
    Pixel max3 = new Pixel(255, 255, 255);
    Pixel max4 = new Pixel(43, 43, 43);
    Pixel max5 = new Pixel(255, 255, 255);
    Pixel max6 = new Pixel(32, 32, 32);

    Pixel rp1 = new Pixel(23, 23, 23);
    Pixel rp2 = new Pixel(0, 0, 0);
    Pixel rp3 = new Pixel(255, 255, 255);
    Pixel rp4 = new Pixel(43, 43, 43);
    Pixel rp5 = new Pixel(255, 255, 255);
    Pixel rp6 = new Pixel(15, 15, 15);


    Pixel gp1 = new Pixel(55, 55, 55);
    Pixel gp2 = new Pixel(34, 34, 34);
    Pixel gp3 = new Pixel(90, 90, 90);
    Pixel gp4 = new Pixel(0, 0, 0);
    Pixel gp5 = new Pixel(255, 255, 255);
    Pixel gp6 = new Pixel(32, 32, 32);


    Pixel bp1 = new Pixel(40, 40, 40);
    Pixel bp2 = new Pixel(0, 0, 0);
    Pixel bp3 = new Pixel(89, 89, 89);
    Pixel bp4 = new Pixel(43, 43, 43);
    Pixel bp5 = new Pixel(0, 0, 0);
    Pixel bp6 = new Pixel(8, 8, 8);


    this.model = new ImageModelImpl();

    this.original = this.makeArray(p1, p2, p3, p4, p5, p6);
    this.horizontal = this.makeArray(p3, p2, p1, p6, p5, p4);
    this.vertical = this.makeArray(p4, p5, p6, p1, p2, p3);
    this.brighten10 = this.makeArray(bright1, bright2, bright3, bright4, bright5, bright6);
    this.brightenNeg10 = this.makeArray(dark1, dark2, dark13, dark14, dark5, dark6);
    this.red = this.makeArray(rp1, rp2, rp3, rp4, rp5, rp6);
    this.green = this.makeArray(gp1, gp2, gp3, gp4, gp5, gp6);
    this.blue = this.makeArray(bp1, bp2, bp3, bp4, bp5, bp6);
    this.luma = this.makeArray(lp1, lp2, lp3, lp4, lp5, lp6);
    this.value = this.makeArray(max1, max2, max3, max4, max5, max6);
    this.intensity = this.makeArray(ip1, ip2, ip3, ip4, ip5, ip6);
    this.sepia = this.makeArray(sepia1, sepia2, sepia3, sepia4, sepia5, sepia6);

    // Add initial image to model
    model.putImage("original", original);
    model.putImage("array", testArray);
    model.putImage("source", img);
    model.putImage("horizontal", horizontalImage);
    model.putImage("vertical", verticalImage);
    model.putImage("red", redImage);
    model.putImage("green", greenImage);
    model.putImage("blue", blueImage);
    model.putImage("luma", lumaImage);
    model.putImage("value", imageValue);
    model.putImage("intensity", intensityImage);
    model.putImage("brighten", brightenImage);
    model.putImage("darken", darkenImage);

  }


  /**
   * Testing the Horizontal method.
   */
  @Test
  public void testHorizontalFlip() {
    model.applyFilter(new Horizontal(), "original",
            "testHorizontal");
    assertTrue(testPixel(model.findImage("testHorizontal"), horizontal));
  }

  /**
   * Testing the Vertical method.
   */
  @Test
  public void testVertical() {
    model.applyFilter(new Vertical(), "original",
            "testVertical");
    assertTrue(testPixel(model.findImage("testVertical"), vertical));
  }

  /**
   * Testing the Red method.
   */
  @Test
  public void testRed() {
    model.applyFilter(new Red(), "original",
            "redsource");
    assertTrue(testPixel(model.findImage("redsource"), red));

  }

  /**
   * Testing the Green method.
   */
  @Test
  public void testGreen() {
    model.applyFilter(new Green(), "original",
            "greensource");
    assertTrue(testPixel(model.findImage("greensource"), green));

  }

  /**
   * Testing the Blue method.
   */
  @Test
  public void testBlue() {
    model.applyFilter(new Blue(), "original",
            "bluesource");
    assertTrue(testPixel(model.findImage("bluesource"), blue));
  }

  /**
   * Testing the Value method.
   */
  @Test
  public void testValue() {
    model.applyFilter(new Value(), "original", "valuesource");
    assertTrue(testPixel(model.findImage("valuesource"), value));

  }

  /**
   * Testing the Luma method.
   */
  @Test
  public void testLuma() {
    model.applyFilter(new Luma(), "original",
            "lumasource");
    assertTrue(testPixel(model.findImage("lumasource"), luma));

  }


  /**
   * Testing the Intensity method.
   */
  @Test
  public void testIntensity() {
    model.applyFilter(new Intensity(), "original", "intensitysource");
    assertTrue(testPixel(model.findImage("intensitysource"), intensity));

  }

  /**
   * Testing the brighten method.
   */
  @Test
  public void testBrighten() {
    model.applyFilter(new Brighten(10), "original",
            "brightensource");
    assertTrue(testPixel(model.findImage("brightensource"), brighten10));
  }

  /**
   * Test darken.
   */
  @Test
  public void testDarken() {
    model.applyFilter(new Darken(10), "original",
            "darkensource");
    assertTrue(testPixel(model.findImage("darkensource"), brightenNeg10));

  }

  /**
   * Test blur.
   */
  @Test
  public void testBlur() {
    model.applyFilter(new Blur(), "source", "blur");
    int red = (int) (((double) one.getRed() / 4) + ((double) two.getRed() / 8)
            + ((double) four.getRed() / 8) + ((double) five.getRed() / 16));
    assertEquals(model.findImage("blur").getPixelArray()[0][0].getRed(), red);
    int green = (int) (((double) one.getGreen() / 4) + ((double) two.getGreen() / 8)
            + ((double) four.getGreen() / 8) + ((double) five.getGreen() / 16));
    assertEquals(model.findImage("blur").getPixelArray()[0][0].getGreen(), green);
    int blue = (int) (((double) one.getBlue() / 4) + ((double) two.getBlue() / 8)
            + ((double) four.getBlue() / 8) + ((double) five.getBlue() / 16));
    assertEquals(model.findImage("blur").getPixelArray()[0][0].getBlue(), blue);
  }

  /**
   * Test sharpen.
   */
  @Test
  public void testSharpen() {
    model.applyFilter(new Sharpen(), "source", "sharpen");
    int red = (int) (one.getRed() + ((double) two.getRed() / 4) + ((double) three.getRed() / -8)
            + ((double) four.getRed() / 4) + ((double) five.getRed() / 4)
            + ((double) six.getRed() / -8));
    assertEquals(model.findImage("sharpen").getPixelArray()[0][0].getRed(), red);
    int green = (int) (one.getGreen() + ((double) two.getGreen() / 4) +
            ((double) three.getGreen() / -8) + ((double) four.getGreen() / 4)
            + ((double) five.getGreen() / 4) + ((double) six.getGreen() / -8));
    assertEquals(model.findImage("sharpen").getPixelArray()[0][0].getGreen(), green);
    int blue =
            (int) (one.getBlue() + ((double) two.getBlue() / 4) + ((double) three.getBlue() / -8)
                    + ((double) four.getBlue() / 4) + ((double) five.getBlue() / 4)
                    + ((double) six.getBlue() / -8));
    assertEquals(model.findImage("sharpen").getPixelArray()[0][0].getBlue(), blue);
  }

  /**
   * Test sepia.
   */
  @Test
  public void testSepia() {
    model.applyFilter(new Sepia(), "original", "sepiasource");
    assertTrue(testPixel(model.findImage("sepiasource"), sepia));
  }

  private Image makeArray(Pixel one, Pixel two, Pixel three, Pixel four,
                          Pixel five, Pixel six) {
    Pixel[][] pixelArr = new Pixel[][]{{one, two, three}, {four, five, six}};
    return new Image(pixelArr);
  }

  private boolean testPixel(Image img1, Image img2) {
    boolean sameValue = true;
    Pixel[][] array1 = img1.getPixelArray();
    Pixel[][] array2 = img2.getPixelArray();
    for (int row = 0; row < array1.length; row++) {
      for (int col = 0; col < array1[row].length; col++) {
        if (array1[row][col].getRed() == array2[row][col].getRed()
                && array1[row][col].getGreen() == array2[row][col].getGreen()
                && array1[row][col].getBlue() == array2[row][col].getBlue()) {
          sameValue = true;
        } else {
          return false;
        }
      }
    }
    return sameValue;
  }

}
