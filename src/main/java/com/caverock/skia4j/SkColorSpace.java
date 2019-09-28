package com.caverock.skia4j;

public class SkColorSpace  implements AutoCloseable
{
   private long  nRef = 0;
   
   
   private SkColorSpace(long nRef)
   {
      this.nRef = nRef;
   }


   /**
    * Creates a new colour space instance of type sRGB.
    */
   public static SkColorSpace  makeSRGB()
   {
      long  nRef = nSkColorSpaceMakeSRGB();
      if (nRef == 0)
         return null;

      return new SkColorSpace(nRef);
      // Note: the above native SkColorSpace object will be automatically cleaned when SkImageInfo.close() is called.
   }


   /**
    * Creates a new colour space instance of type sRGB.
    */
   public static SkColorSpace  makeSRGBLinear()
   {
      long  nRef = nSkColorSpaceMakeSRGBLinear();
      if (nRef == 0)
         return null;

      return new SkColorSpace(nRef);
      // Note: the above native SkColorSpace object will be automatically cleaned when SkImageInfo.close() is called.
   }


   //--------------------------------------------------------------------------


   /**
    * Tidy up the native resource associated with this class.
    * Must be called, when this object is no longer needed, to avoid memory leaks.
    * 
    * You can call close() directly, or use try-with-resources.
    */
   @Override
   public void close() throws Exception
   {
      nSkColorSpaceUnref(nativeRef());
      nRef = 0;
   }


   protected long  nativeRef()
   {
      if (nRef == 0)
         throw new IllegalArgumentException("This SkColorSpace object has already been released.");
      return nRef;
   }



   @Override
   public String toString()
   {
      return String.format("%s (ref:%x)", getClass().getSimpleName(), nRef);
   }


   //--------------------------------------------------------------------------


   /**
    * Returns whether this colour space is a (non-linear) sRGB one.
    * @return true if it is
    */
   public boolean  isSRGB()
   {
      return nSkColorSpaceIsSRGB(nativeRef());
   }


   /**
    * Returns whether this colour space has a linear transfer function.  For example, if it was created with {@link #makeSRGBLinear()}. 
    * @return true if it is
    */
   public boolean  isLinearGamma()
   {
      return nSkColorSpaceGammaIsLinear(nativeRef());
   }


   //--------------------------------------------------------------------------
   // Native methods
   // include/c/sk_imageinfo.h

   native private static long  nSkColorSpaceMakeSRGB();
   native private static long  nSkColorSpaceMakeSRGBLinear();

   native private static void  nSkColorSpaceUnref(long ref);

   native private static boolean  nSkColorSpaceIsSRGB(long ref);
   native private static boolean  nSkColorSpaceGammaIsLinear(long ref);
}
