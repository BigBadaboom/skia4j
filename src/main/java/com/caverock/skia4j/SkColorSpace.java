package com.caverock.skia4j;

public class SkColorSpace
{
   private long  nRef = 0;
   
   
   private SkColorSpace() {}


   /**
    * Creates a new colour space instance of type sRGB.
    */
   public static SkColorSpace  createSrgb()
   {
      SkColorSpace result = new SkColorSpace();
      result.nRef = nColorSpaceNewSrgb();
      // Note: the above native SkColorSpace object will be automatically cleaned when SkImageInfo.close() is called.
      return result;
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
   // Native methods
   // include/c/sk_imageinfo.h

   native private static long  nColorSpaceNewSrgb();
   //SK_API sk_colorspace_t* sk_colorspace_new_srgb();

   //SK_API void sk_colorspace_ref(sk_colorspace_t*);
   //SK_API void sk_colorspace_unref(sk_colorspace_t*);

}
