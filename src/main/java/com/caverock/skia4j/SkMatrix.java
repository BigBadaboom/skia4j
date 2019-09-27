package com.caverock.skia4j;

public class SkMatrix
{
   // Skia matrices are of the form:
   //
   // | scaleX  skewX transX |
   // |  skewY scaleY transY |
   // | persp0 persp1 persp2 |

   private float[]  mat;


   public SkMatrix()
   {
      this.mat = new float[] { 1f, 0f, 0f,
                               0f, 1f, 0f,
                               0f, 0f, 1f };
   }


   protected float[] getMatrix()
   {
      return mat;
   }


   //--------------------------------------------------------------------------


   // FIXME missing methods
}
