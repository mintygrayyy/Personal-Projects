package filter;

/**
 * Sharpens image using kernelfilter.
 */
public class Sharpen extends KernelFilter implements FilterFactory {
  private double[][] sharpenKernel =
      {{(double) -1 / 8, (double) -1 / 8, (double) -1 / 8, (double) -1 / 8, (double) -1 / 8},
      {(double) -1 / 8, (double) 1 / 4, (double) 1 / 4, (double) 1 / 4, (double) -1 / 8},
      {(double) -1 / 8, (double) 1 / 4, 1, (double) 1 / 4, (double) -1 / 8},
      {(double) -1 / 8, (double) 1 / 4, (double) 1 / 4, (double) 1 / 4, (double) -1 / 8},
      {(double) -1 / 8, (double) -1 / 8, (double) -1 / 8, (double) -1 / 8, (double) -1 / 8}};

  public Sharpen() {
    this.kernel = sharpenKernel;
  }
}
