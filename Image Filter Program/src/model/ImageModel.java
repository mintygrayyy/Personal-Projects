package model;


import filter.FilterFactory;

/**
 * Interface representing operations that can be done by the imageModel.
 */
public interface ImageModel {
  void applyFilter(FilterFactory filter, String originalName, String newName);

  Image findImage(String imageName) throws IllegalArgumentException;

  void putImage(String imageName, Image image);


}
