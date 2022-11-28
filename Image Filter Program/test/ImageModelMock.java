import java.util.Objects;

import filter.FilterFactory;
import model.Image;
import model.ImageModel;

/**
 * Mock class for Image Model.
 */
public class ImageModelMock implements ImageModel {

  private final StringBuilder build;

  /**
   * Constructor for mock with the Stringbuilder.
   *
   * @param build input from user
   */
  public ImageModelMock(StringBuilder build) {
    this.build = Objects.requireNonNull(build);
  }

  @Override
  public void applyFilter(FilterFactory filter, String originalName, String newName) {
    build.append(String.format("applyFilter is called\n" + filter.getClass() +
            " is the type of command\n"
            + originalName + " is the name of the source\n"
            + newName + " is the name of the destination\n"));
  }

  @Override
  public Image findImage(String imageName) throws IllegalArgumentException {
    build.append(String.format("findImage\n" +
                    "imageName is the name of image we search for\n",
            imageName));
    return null;
  }

  @Override
  public void putImage(String imageName, Image image) {
    build.append(String.format("putImage is called\n"
                    + "imageName is the name of the image being added\n "
                    + "image is the name of Image we are adding\n",
            imageName, image));
  }
}
