package view;

import java.io.IOException;

/**
 * Class that represents the view for the image.
 */
public class ImageViewImpl implements ImageView {
  Appendable append;

  /**
   * A new ImageViewImpl defaulting to System.out because no appendable
   */
  public ImageViewImpl() {
    this.append = System.out;
  }

  /**
   * A new ImageViewImpl with a given appendable.
   *
   * @param append the given appendable
   * @throws IllegalArgumentException if the appendable is null
   */
  public ImageViewImpl(Appendable append) throws IllegalArgumentException {
    if (append == null) {
      throw new IllegalArgumentException("This appendable is null");
    }
    this.append = append;
  }


  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.append.append(message);
    } catch (IOException e) {
      throw new IOException("Append failed");
    }
  }
}