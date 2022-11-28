
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import controller.GuiController;
//import controller.ImageController;
//import controller.ImageControllerImpl;
//import model.ImageModelImpl;
//import view.ImageView;
//import view.ImageViewImpl;
//import view.JFrameView;
//
//
///**
// * Class that allows the user to run the image processor.
// */
//public class ImageProcessor {
//  /**
//   * Method to run the image processor.
//   *
//   * @param args arguments
//   */
//  public static void main(String[] args) {
//    ImageModelImpl model = new ImageModelImpl();
//    ImageView view;
//    JFrameView guiView;
//    Readable rd;
//    ImageController controller;
//    GuiController guiController;
//
////    if (args.length == 0) {
////      guiView = new JFrameView();
////      guiController = new GuiController(model, guiView);
////      guiController.imageProcessor(guiView);
////    }
//    if (args.length > 0 && args[0].equals("-text")) {
//      rd = new InputStreamReader(System.in);
//      view = new ImageViewImpl(System.out);
//      controller = new ImageControllerImpl(model, view, rd);
//      controller.imageProcessor();
//    } else if (args.length > 0 && args[0].equals("-file")) {
//      view = new ImageViewImpl(System.out);
//      try {
//        rd = new FileReader(args[1]);
//        controller = new ImageControllerImpl(model, view, rd);
//        controller.imageProcessor();
//      } catch (IOException e) {
//        throw new IllegalStateException("Cannot be read");
//      }
//    }
//  }
//}

import java.io.InputStreamReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;
import view.ImageView;
import view.ImageViewImpl;

/**
 * Class that allows the user to run the image processor.
 */
public class ImageProcessor {
  /**
   * Method to run the image processor.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    ImageModel model = new ImageModelImpl();
    ImageView view = new ImageViewImpl(System.out);
    Readable rd = new InputStreamReader(System.in);
    ImageController controller = new ImageControllerImpl(model, view, rd);

    controller.imageProcessor();
  }
}

