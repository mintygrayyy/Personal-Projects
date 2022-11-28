package view;

import controller.Features;
import model.Image;

/**
 * Interface that represents the Guiview.
 */
public interface GuiView {
  void addFeatures(Features features);

  void updateImage(Image image);

  String getName();

  void refresh();
}
