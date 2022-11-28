package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;


import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controller.Features;
import model.Image;
import model.Pixel;
import utils.ImageUtil;

/**
 * Displays the GuiView with buttons, image, and histogram.
 */
public class JFrameView extends JFrame implements GuiView {
  private final JPanel imagePanel;


  private final JButton loadButton;
  private final JButton saveButton;


  private final JButton redButton;
  private final JButton greenButton;
  private final JButton blueButton;
  private final JButton blurButton;
  private final JButton brightenButton;
  private final JButton darkenButton;
  private final JButton greyScaleButton;
  private final JButton horizontalButton;
  private final JButton verticalButton;
  private final JButton intensityButton;
  private final JButton lumaButton;
  private final JButton sepiaButton;
  private final JButton sharpenButton;
  private final JButton valueButton;

  private JScrollPane scrollPane;

  private ImageIcon icon;

  private Image image;
  GridBagConstraints constraints;

  private Pixel[] arr1 = {new Pixel(0, 0, 0)};
  private Pixel[][] nullPixel = {arr1};


  /**
   * Displays the view of the gui.
   */
  public JFrameView() {
    super();
    this.setLayout(new BorderLayout());
    this.image = new Image(nullPixel);
    JLabel fileOpenDisplay;
    JLabel fileSaveDisplay;

    //grid bag panel
    this.setLayout(new GridBagLayout());
    constraints = new GridBagConstraints();

    //button grid panel
    JPanel buttons = new JPanel();
    buttons.setLayout(new GridLayout(4, 4));
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weightx = 0.5;
    constraints.gridx = 0;
    constraints.gridy = 0;
    this.add(buttons, constraints);

    //image panel
    imagePanel = new JPanel();
    imagePanel.setPreferredSize(new Dimension(400, 400));
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weightx = 0.5;
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 3;
    this.add(imagePanel, constraints);


    JLabel label = new JLabel();
    icon = new ImageIcon();
    label.setIcon(icon);
    scrollPane = new JScrollPane(label);


    //Loading image
    JPanel fileopenPanel = new JPanel();
    loadButton = new JButton("Load");
    loadButton.setActionCommand("Load");
    buttons.add(loadButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    //Saving image
    saveButton = new JButton("Save");
    JPanel fileSavePanel = new JPanel();
    saveButton.setActionCommand("Save");
    buttons.add(saveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    fileSavePanel.add(fileSaveDisplay);

    redButton = new JButton("Red");
    redButton.setActionCommand("Red");
    buttons.add(redButton);

    greenButton = new JButton("Green");
    greenButton.setActionCommand("Green");
    buttons.add(greenButton);

    blueButton = new JButton("Blue");
    blueButton.setActionCommand("Blue");
    buttons.add(blueButton);

    blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur");
    buttons.add(blurButton);

    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("Brighten");
    buttons.add(brightenButton);

    darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("Darken");
    buttons.add(darkenButton);

    greyScaleButton = new JButton("GreyScale");
    greyScaleButton.setActionCommand("GreyScale");
    buttons.add(greyScaleButton);

    horizontalButton = new JButton("Horizontal");
    horizontalButton.setActionCommand("Horizontal");
    buttons.add(horizontalButton);

    verticalButton = new JButton("Vertical");
    verticalButton.setActionCommand("Vertical");
    buttons.add(verticalButton);

    intensityButton = new JButton("Intensity");
    intensityButton.setActionCommand("Intensity");
    buttons.add(intensityButton);

    lumaButton = new JButton("Luma");
    lumaButton.setActionCommand("Luma");
    buttons.add(lumaButton);

    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia");
    buttons.add(sepiaButton);

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen");
    buttons.add(sharpenButton);

    valueButton = new JButton("Value");
    valueButton.setActionCommand("Value");
    buttons.add(valueButton);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    loadButton.addActionListener(evt -> features.load());
    saveButton.addActionListener(evt -> features.save());
    redButton.addActionListener(evt -> features.red());
    greenButton.addActionListener(evt -> features.green());
    blueButton.addActionListener(evt -> features.blue());
    blurButton.addActionListener(evt -> features.blur());
    brightenButton.addActionListener(evt -> features.brighten());
    darkenButton.addActionListener(evt -> features.darken());
    greyScaleButton.addActionListener(evt -> features.greyscale());
    horizontalButton.addActionListener(evt -> features.horizontal());
    verticalButton.addActionListener(evt -> features.vertical());
    intensityButton.addActionListener(evt -> features.intensity());
    lumaButton.addActionListener(evt -> features.luma());
    sepiaButton.addActionListener(evt -> features.sepia());
    sharpenButton.addActionListener(evt -> features.sharpen());
    valueButton.addActionListener(evt -> features.value());
  }

  @Override
  public void updateImage(Image image) {
    Histogram histogram;
    this.image = image;
    BufferedImage bufferedImage = ImageUtil.makeBuffer(image);
    icon.setImage(bufferedImage);
    scrollPane.setPreferredSize(new Dimension(400, 400));
    imagePanel.add(scrollPane, 0);

    //histogram
    histogram = new Histogram(image);
    histogram.setLayout(new BorderLayout());
    histogram.setPreferredSize(new Dimension(600, 600));

    JScrollPane histoPanel = new JScrollPane(histogram);
    histoPanel.setPreferredSize(new Dimension(400, 400));
    histoPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    histoPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weightx = 0.5;
    constraints.ipady = 40;
    constraints.gridx = 2;
    constraints.gridy = 0;
    this.add(histoPanel, constraints);
    histogram.updateHistogram(image);

  }

  /**
   * Gets the name of the file.
   *
   * @return file name
   */
  public String getName() {
    String fileName = JOptionPane.showInputDialog(null,
            "Enter new file name", "input", JOptionPane.QUESTION_MESSAGE);
    if (fileName == null) {
      JOptionPane.showMessageDialog(null, "file or file name cannot be null");
    }
    return fileName;
  }

  public void refresh() {
    this.validate();
    this.repaint();
  }

}
