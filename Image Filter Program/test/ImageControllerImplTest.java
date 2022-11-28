import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.Image;
import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for ImageModelImpl.
 */
public class ImageControllerImplTest {

  StringBuilder build1;
  StringBuilder build2;
  StringBuilder build3;
  StringBuilder build4;
  StringBuilder build5;
  StringBuilder build6;
  StringBuilder build7;
  StringBuilder build8;
  StringBuilder build9;

  ImageModel mock1;
  ImageModel mock2;
  ImageModel mock3;
  ImageModel mock4;
  ImageModel mock5;
  ImageModel mock6;
  ImageModel mock7;
  ImageModel mock8;
  ImageModel mock9;

  StringReader rd1;
  StringReader rd2;
  StringReader rd3;
  StringReader rd4;
  StringReader rd5;
  StringReader rd6;
  StringReader rd7;
  StringReader rd8;
  StringReader rd9;

  ImageView view1;
  ImageView view2;
  ImageView view3;
  ImageView view4;
  ImageView view5;
  ImageView view6;
  ImageView view7;
  ImageView view8;
  ImageView view9;

  ImageController control1;
  ImageController control2;
  ImageController control3;
  ImageController control4;
  ImageController control5;
  ImageController control6;
  ImageController control7;
  ImageController control8;
  ImageController control9;

  @Before
  public void setup() {
    this.build1 = new StringBuilder();
    this.build2 = new StringBuilder();
    this.build3 = new StringBuilder();
    this.build4 = new StringBuilder();
    this.build5 = new StringBuilder();
    this.build6 = new StringBuilder();
    this.build7 = new StringBuilder();
    this.build8 = new StringBuilder();
    this.build9 = new StringBuilder();

    this.mock1 = new ImageModelMock(build1);
    this.rd1 = new StringReader("blue Koala KoalaBlue");
    this.view1 = new ImageViewImpl();
    this.control1 = new ImageControllerImpl(mock1, view1, rd1);

    this.mock2 = new ImageModelMock(build2);
    this.rd2 = new StringReader("brighten 10 Koala KoalaBright");
    this.view2 = new ImageViewImpl();
    this.control2 = new ImageControllerImpl(mock2, view2, rd2);

    this.mock3 = new ImageModelMock(build3);
    this.rd3 = new StringReader("green Koala KoalaGreen");
    this.view3 = new ImageViewImpl();
    this.control3 = new ImageControllerImpl(mock3, view3, rd3);

    this.mock4 = new ImageModelMock(build4);
    this.rd4 = new StringReader("horizontal Koala KoalaHorizontal");
    this.view4 = new ImageViewImpl();
    this.control4 = new ImageControllerImpl(mock4, view4, rd4);

    this.mock5 = new ImageModelMock(build5);
    this.rd5 = new StringReader("intensity Koala KoalaIntensity");
    this.view5 = new ImageViewImpl();
    this.control5 = new ImageControllerImpl(mock5, view5, rd5);

    this.mock6 = new ImageModelMock(build6);
    this.rd6 = new StringReader("luma Koala KoalaLuma");
    this.view6 = new ImageViewImpl();
    this.control6 = new ImageControllerImpl(mock6, view6, rd6);

    this.mock7 = new ImageModelMock(build7);
    this.rd7 = new StringReader("value Koala KoalaValue");
    this.view7 = new ImageViewImpl();
    this.control7 = new ImageControllerImpl(mock7, view7, rd7);

    this.mock8 = new ImageModelMock(build8);
    this.rd8 = new StringReader("red Koala KoalaRed");
    this.view8 = new ImageViewImpl();
    this.control8 = new ImageControllerImpl(mock8, view8, rd8);

    this.mock9 = new ImageModelMock(build9);
    this.rd9 = new StringReader("vertical Koala KoalaVertical");
    this.view9 = new ImageViewImpl();
    this.control9 = new ImageControllerImpl(mock9, view9, rd9);
  }

  /**
   * A class testing that the controller is properly receiving inputs and calling the
   * correct methods.
   */
  @Test
  public void testLog() {
    control1.imageProcessor();
    assertEquals("applyFilter is called\n"
            + "class filter.Blue is the type of command\n"
            + "Koala is the name of the source\n"
            + "KoalaBlue is the name of the destination\n", build1.toString());
    control2.imageProcessor();
    assertEquals("applyFilter is called\n"
            + "class filter.Brighten is the type of command\n"
            + "Koala is the name of the source\n"
            + "KoalaBright is the name of the destination\n", build2.toString());
    control3.imageProcessor();
    assertEquals("applyFilter is called\n"
            + "class filter.Green is the type of command\n"
            + "Koala is the name of the source\n"
            + "KoalaGreen is the name of the destination\n", build3.toString());
    control4.imageProcessor();
    assertEquals("applyFilter is called\n"
            + "class filter.Horizontal is the type of command\n"
            + "Koala is the name of the source\n"
            + "KoalaHorizontal is the name of the destination\n", build4.toString());
    control5.imageProcessor();
    assertEquals("applyFilter is called\n"
            + "class filter.Intensity is the type of command\n"
            + "Koala is the name of the source\n"
            + "KoalaIntensity is the name of the destination\n", build5.toString());
    control6.imageProcessor();
    assertEquals("applyFilter is called\n"
            + "class filter.Luma is the type of command\n"
            + "Koala is the name of the source\n"
            + "KoalaLuma is the name of the destination\n", build6.toString());
    control7.imageProcessor();
    assertEquals("applyFilter is called\n"
            + "class filter.Value is the type of command\n"
            + "Koala is the name of the source\n"
            + "KoalaValue is the name of the destination\n", build7.toString());
    control8.imageProcessor();
    assertEquals("applyFilter is called\n"
            + "class filter.Red is the type of command\n"
            + "Koala is the name of the source\n"
            + "KoalaRed is the name of the destination\n", build8.toString());
    control9.imageProcessor();
    assertEquals("applyFilter is called\n"
            + "class filter.Vertical is the type of command\n"
            + "Koala is the name of the source\n"
            + "KoalaVertical is the name of the destination\n", build9.toString());

  }

  /**
   * Tests that png, jpg, and ppm are properly loaded and saved.
   */
  @Test
  public void testLoadThenSave() {
    Readable input1 = new StringReader("load res\\test.ppm test \n"
            + "luma test lumatest \n"
            + "save res\\testluma.ppm lumatest \n");
    ImageModel model = new ImageModelImpl();
    ImageView view = new ImageViewImpl();
    ImageController controller = new ImageControllerImpl(model, view, input1);

    controller.imageProcessor();

    try {
      assertTrue(testPixel(model.findImage("lumatest"),
              utils.ImageUtil.read("res\\testluma.ppm", "testluma")));
    } catch (IOException e) {
      throw new IllegalStateException("Images not equivalent");
    }

    Readable input2 = new StringReader("load res\\test.jpg test \n"
            + "blue test bluetest \n"
            + "save res\\testblue.jpg bluetest \n");
    ImageModel model2 = new ImageModelImpl();
    ImageView view2 = new ImageViewImpl();
    ImageController controller2 = new ImageControllerImpl(model2, view2, input2);

    controller2.imageProcessor();

    try {
      assertTrue(testPixel(model2.findImage("bluetest"),
              utils.ImageUtil.read("res\\testblue.png", "testblue")));
    } catch (IOException e) {
      throw new IllegalStateException("Images not equivalent");
    }

    Readable input3 = new StringReader("load res\\test.jpg test \n"
            + "red test redtest \n"
            + "save res\\redtest.png redtest \n");
    ImageModel model3 = new ImageModelImpl();
    ImageView view3 = new ImageViewImpl();
    ImageController controller3 = new ImageControllerImpl(model3, view3, input3);

    controller3.imageProcessor();

    try {
      assertTrue(testPixel(model3.findImage("redtest"),
              utils.ImageUtil.read("res\\redtest.png", "testred")));
    } catch (IOException e) {
      throw new IllegalStateException("Images not equivalent");
    }

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
