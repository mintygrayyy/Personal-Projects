package filter;

/**
 * Blurs image using kernelfilter.
 */
public class Blur extends KernelFilter implements FilterFactory {
  private double[][] kernelBlur = {{(double) 1 / 16, (double) 1 / 8, (double) 1 / 16},
                                   {(double) 1 / 8, (double) 1 / 4, (double) 1 / 8},
                                   {(double) 1 / 16, (double) 1 / 8, (double) 1 / 16}};

  public Blur() {
    this.kernel = kernelBlur;
  }


}
