package controller;

import java.io.IOException;
import java.util.Scanner;

import filter.Blue;
import filter.Blur;
import filter.Brighten;
import filter.Darken;
import filter.FilterFactory;
import filter.Green;
import filter.Greyscale;
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
import utils.ImageUtil;
import view.ImageView;

/**
 * Controller class that takes in user inputs and pushes the filter to the image.
 */
public class ImageControllerImpl implements ImageController {
  private ImageModel model;
  private ImageView view;
  private Readable rd;

  /**
   * Constructor that checks to see if the model,view,or rd are null.
   *
   * @param model image model
   * @param view  image view
   * @param rd    readable
   * @throws IllegalArgumentException if model,view,readable null
   */
  public ImageControllerImpl(ImageModel model, ImageView view, Readable rd)
          throws IllegalArgumentException {
    if (model == null || view == null || rd == null) {
      throw new IllegalArgumentException("Model, view, or readable cannot be null");
    }
    this.model = model;
    this.view = view;
    this.rd = rd;
  }

  @Override
  public void imageProcessor() throws IllegalArgumentException {
    Scanner sc = new Scanner(this.rd);
    System.out.println("Enter command: quit, load, blue, brighten, darken, green, "
            + "horizontal, intensity, luma, red, value, vertical, sharpen, blur, greyscale," +
            "sepia, or save");
    while (sc.hasNext()) {
      String input = sc.next();
      try {
        switch (input) {
          case "quit":
            return;
          case "load":
            String file = sc.next();
            String fileName = sc.next();
            Image image = ImageUtil.read(file, fileName);
            if (image == null) {
              this.view.renderMessage("Image cannot be null");
              break;
            }
            model.putImage(fileName, image);
            this.view.renderMessage("Loaded " + file + " as " + fileName + "\n");
            break;
          case "red":
            this.filter(new Red(), sc);
            this.view.renderMessage("Gray scaled using red values");
            break;
          case "green":
            this.filter(new Green(), sc);
            this.view.renderMessage("Gray scaled using green values");
            break;
          case "blue":
            this.filter(new Blue(), sc);
            this.view.renderMessage("Gray scaled using blue values");
            break;
          case "value":
            this.filter(new Value(), sc);
            this.view.renderMessage("Gray scaled using average values");
            break;
          case "luma":
            this.filter(new Luma(), sc);
            this.view.renderMessage("Gray scaled using luma values");
            break;
          case "intensity":
            this.filter(new Intensity(), sc);
            this.view.renderMessage("Gray scaled using intensity values");
            break;
          case "brighten":
            int brightenValue = sc.nextInt();
            this.filter(new Brighten(brightenValue), sc);
            this.view.renderMessage("Brightened by " + brightenValue);
            break;
          case "darken":
            int darkenValue = sc.nextInt();
            this.filter(new Darken(darkenValue), sc);
            this.view.renderMessage("Darkened by " + darkenValue);
            break;
          case "horizontal":
            this.filter(new Horizontal(), sc);
            this.view.renderMessage("Flipped horizontally");
            break;
          case "vertical":
            this.filter(new Vertical(), sc);
            this.view.renderMessage("Flipped vertically");
            break;
          case "blur":
            this.filter(new Blur(), sc);
            this.view.renderMessage("Blurred image");
            break;
          case "sharpen":
            this.filter(new Sharpen(), sc);
            this.view.renderMessage("Sharpened image");
            break;
          case "greyscale":
            this.filter(new Greyscale(), sc);
            this.view.renderMessage("Greyscaled image");
            break;
          case "sepia":
            this.filter(new Sepia(), sc);
            this.view.renderMessage("Changed image to sepia");
            break;
          case "save":
            String filePath = sc.next();
            String newName = sc.next();
            Image newImage = model.findImage(newName);
            ImageUtil.save(filePath, newImage);
            this.view.renderMessage("Saved " + newName + " to " + filePath);
            break;
          default:
            this.view.renderMessage("Please input valid command");
            break;
        }
      } catch (IOException e) {
        throw new IllegalStateException("Message cannot be rendered");
      }
    }
  }

  //Method to apply filter on a copy of the original image
  private void filter(FilterFactory filter, Scanner sc) {
    String newName = sc.next();
    String originalName = sc.next();
    this.model.applyFilter(filter, originalName, newName);
    try {
      this.view.renderMessage(newName + " " + originalName + "-" + filter + '\n');
    } catch (IOException e) {
      throw new IllegalStateException("Message cannot be sent to view");
    }
  }
}
