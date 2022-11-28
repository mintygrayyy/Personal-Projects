package controller;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import view.GuiView;
import view.JFrameView;

/**
 * GuiController that implements the features needed.
 */
public class GuiController implements Features {
  private ImageModel model;
  private GuiView view;


  private String originalName;


  /**
   * GuiController initializing model and view.
   *
   * @param model the model
   * @param view  the view
   */
  public GuiController(ImageModel model, GuiView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void red() {
    this.filter(new Red());
  }

  @Override
  public void green() {
    this.filter(new Green());
  }

  @Override
  public void blue() {
    this.filter(new Blue());
  }

  @Override
  public void blur() {
    this.filter(new Blur());
  }

  @Override
  public void brighten() {
    int increment = Integer.parseInt(JOptionPane.showInputDialog(null,
            "Input integer to increment by", JOptionPane.INFORMATION_MESSAGE));
    this.filter(new Brighten(increment));

  }

  @Override
  public void darken() {
    int increment = Integer.parseInt(JOptionPane.showInputDialog(null,
            "Input integer to increment by", JOptionPane.INFORMATION_MESSAGE));
    this.filter(new Darken(increment));
  }

  @Override
  public void greyscale() {
    this.filter(new Greyscale());
  }

  @Override
  public void horizontal() {
    this.filter(new Horizontal());
  }

  @Override
  public void intensity() {
    this.filter(new Intensity());
  }

  @Override
  public void luma() {
    this.filter(new Luma());
  }

  @Override
  public void sepia() {
    this.filter(new Sepia());
  }

  @Override
  public void sharpen() {
    this.filter(new Sharpen());
  }

  @Override
  public void value() {
    this.filter(new Value());
  }

  @Override
  public void vertical() {
    this.filter(new Vertical());
  }

  @Override
  public void load() {
    try {
      final JFileChooser fchooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPG & PNG & PPM Images", "jpg", "png", "ppm");
      fchooser.setFileFilter(filter);
      fchooser.showOpenDialog(null);
      File f = fchooser.getSelectedFile();
      String file = f.getAbsolutePath();
      String fileName = JOptionPane.showInputDialog(null,
              "Enter new file name", "", JOptionPane.INFORMATION_MESSAGE);
      if (file == null || fileName == null) {
        JOptionPane.showMessageDialog(null, "file or file name cannot be null");
      }
      f.getName();
      Image image = ImageUtil.read(file, fileName);

      model.putImage(fileName, image);
      view.updateImage(image);
      JOptionPane.showMessageDialog(null,
              "Loaded " + file);
      view.refresh();
      originalName = fileName;
    } catch (IOException e) {
      throw new IllegalStateException("Cannot print");
    }
  }

  @Override
  public void save() {
    final JFileChooser fchooser = new JFileChooser(".");
    fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fchooser.showOpenDialog(new JDialog());
    File f = fchooser.getSelectedFile();
    String filePath = f.getAbsolutePath();
    String newName = JOptionPane.showInputDialog(null,
            "Enter name of message to be saved", "",
            JOptionPane.INFORMATION_MESSAGE);
    Image newFile = model.findImage(originalName);
    try {
      utils.ImageUtil.save(filePath + "/" + newName, newFile);
    } catch (IOException e) {
      throw new IllegalStateException("Image cannot be saved.");
    }
    JOptionPane.showMessageDialog(null, "Saved " + newName);
  }

  //Method to apply filter on a copy of the original image
  private void filter(FilterFactory filter) {
    String newName = view.getName();
    model.applyFilter(filter, originalName, newName);
    Image image = model.findImage(newName);
    view.updateImage(image);

    JOptionPane.showMessageDialog(null,
            newName + " " + this.originalName + "-" + filter + '\n');
    view.refresh();

  }

  public void imageProcessor(JFrameView v) {
    view = v;
    view.addFeatures(this);
  }
}
