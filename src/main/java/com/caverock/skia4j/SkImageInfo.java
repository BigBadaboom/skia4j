package com.caverock.skia4j;

public class SkImageInfo  implements AutoCloseable
{
   private int           width;
   private int           height;
   private SkColorType   colorType = SkColorType.kUnknown;
   private SkAlphaType   alphaType = SkAlphaType.kUnknown;
   private SkColorSpace  colorSpace = null;

   // Reference to the native sk_image_info object
   private long  nRef = 0;


   private SkImageInfo(int width, int height, SkColorType colorType, SkAlphaType alphaType, SkColorSpace colorSpace, long nRef)
   {
      this.width = width;
      this.height = height;
      this.colorType = colorType;
      this.alphaType = alphaType;
      this.colorSpace = colorSpace;
      this.nRef = nRef;

/**/System.out.println("w="+this.width);
/**/System.out.println("h="+this.height);
/**/System.out.println("colorType="+this.colorType);
/**/System.out.println("alphaType="+this.alphaType);
/**/System.out.println("colorSpace="+this.colorSpace);
   }


   //--------------------------------------------------------------------------
 

   /**
    * Creates an SkImageInfo instance that describes the colours, alpha and colour space of a surface.
    * 
    * @param width the image width
    * @param height the image height
    * @param colorType the colour type
    * @param alphaType the type of alpha storage to use
    * @param colorSpace the colour space. If this is null, sRGB will be used.
    * @return an instance of SkImageInfo
    */
   public static SkImageInfo  make(int width, int height, SkColorType colorType, SkAlphaType alphaType, SkColorSpace colorSpace)
   {
      long  csp = (colorSpace != null) ? colorSpace.nativeRef() : 0;
      
      long  nRef = nSkImageInfoMake(width, height, colorType.ordinal(), alphaType.ordinal(), csp);
      if (nRef == 0)
         return null;

      return new SkImageInfo(width, height, colorType, alphaType, colorSpace, nRef);
   }


   /**
    * Creates an SkImageInfo from width and height, native SkColorType
    * (ARGB or ABGR), specified SkAlphaType and SkColorSpace.
    * 
    * @param width the image width
    * @param height the image height
    * @param alphaType the alpha type
    * @param colorSpace the colour space to use
    * @return an instance of SkImageInfo
    */
   public static SkImageInfo  makeN32(int width, int height, SkAlphaType alphaType, SkColorSpace colorSpace)
   {
      long  csp = (colorSpace != null) ? colorSpace.nativeRef() : 0;

      long  nRef = nSkImageInfoMakeN32(width, height, alphaType.ordinal(), csp);
      if (nRef == 0)
         return null;

      SkColorType   ct = SkColorType.from( nSkImageInfoGetColorType(nRef) );

      return new SkImageInfo(width, height, ct, alphaType, colorSpace, nRef);
   }


   /**
    * Creates an SkImageInfo from width and height, native SkColorType
    * (ARGB or ABGR), specified SkAlphaType, with SkColorSpace set to sRGB.
    * 
    * @param width the image width
    * @param height the image height
    * @param alphaType the alpha type
    * @return an instance of SkImageInfo
    */
   public static SkImageInfo  makeS32(int width, int height, SkAlphaType alphaType)
   {
      long  nRef = nSkImageInfoMakeN32(width, height, alphaType.ordinal(), 0);
      if (nRef == 0)
         return null;
//SkColorSpace  csp = SkColorSpace.createSrgb();

      SkColorType   ct = SkColorType.from( nSkImageInfoGetColorType(nRef) );

    return new SkImageInfo(width, height, ct, alphaType, null, nRef);
   }


   /**
    * Creates an SkImageInfo from width and height, native SkColorType
    * (ARGB or ABGR), SkAlphaType.kPremul, and specified SkColorSpace.
    *  
    * @param width the image width
    * @param height the image height
    * @param colorSpace the colour space to use
    * @return an instance of SkImageInfo
    */
   public static SkImageInfo  makeN32Premul(int width, int height, SkColorSpace colorSpace)
   {
      long         csp = (colorSpace != null) ? colorSpace.nativeRef() : 0;
      SkAlphaType  at = SkAlphaType.kPremul;

      long  nRef = nSkImageInfoMakeN32(width, height, at.ordinal(), csp);
      if (nRef == 0)
         return null;

      SkColorType   ct = SkColorType.from( nSkImageInfoGetColorType(nRef) );

      return new SkImageInfo(width, height, ct, at, null, nRef);
   }


   /**
    * Creates an SkImageInfo from width and height, native SkColorType
    * (ARGB or ABGR), SkAlphaType.kPremul, with SkColorSpace set to sRGB.
    *  
    * @param width the image width
    * @param height the image height
    * @return an instance of SkImageInfo
    */
   public static SkImageInfo  makeN32Premul(int width, int height)
   {
      SkAlphaType  at = SkAlphaType.kPremul;

      long  nRef = nSkImageInfoMakeN32(width, height, at.ordinal(), 0);
      if (nRef == 0)
         return null;

      SkColorType   ct = SkColorType.from( nSkImageInfoGetColorType(nRef) );

      return new SkImageInfo(width, height, ct, at, null, nRef);
   }


   /**
    * Creates an SkImageInfo from width and height, SkColorType.kAlpha_8,
    * SkAlphaType.kPremul, with SkColorSpace set to sRGB.
    *  
    * @param width the image width
    * @param height the image height
    * @return an instance of SkImageInfo
    */
   public static SkImageInfo  makeA8(int width, int height)
   {
      SkColorType   ct = SkColorType.kAlpha_8;
      SkAlphaType   at = SkAlphaType.kPremul;

      long  nRef = nSkImageInfoMake(width, height, ct.ordinal(), at.ordinal(), 0);
      if (nRef == 0)
         return null;

      return new SkImageInfo(width, height, ct, at, null, nRef);
   }


   /**
    * Tidy up the native resource associated with this class.
    * Must be called, when this object is no longer needed, to avoid memory leaks.
    * 
    * You can call close() directly, or use try-with-resources.
    */
   @Override
   public void close() throws Exception
   {
      nSkImageInfoDelete(nativeRef());
      nRef = 0;
   }


   protected long  nativeRef()
   {
      if (nRef == 0)
         throw new IllegalArgumentException("This SkImageInfo object has already been released.");
      return nRef;
   }


   @Override
   public String toString()
   {
      return String.format("%s: w=%d h=%d colorType=%s alphaType=%s (ref:%x)", getClass().getSimpleName(), width, height, colorType, alphaType, nRef);
   }


   //--------------------------------------------------------------------------

  
   /**   
    * Gets the width.
   */
   public int  getWidth() {
      return width;
   }


   /**   
    * Gets the height.
   */
   public int  getHeight() {
      return height;
   }


   /**
    * Gets the colour type.
    */
   public SkColorType getColorType()
   {
      return colorType;
   }


   /**
    * Gets the alpha type.
    */
   public SkAlphaType  getAlphaType() {
      return alphaType;
   }


   /**
    * Gets the colour space.
    */
   public SkColorSpace getColorSpace()
   {
      return colorSpace;
   }


   /*
   IsOpaque        
   Gets a value indicating whether the configured alpha type is opaque.
   */


   //--------------------------------------------------------------------------
   // Native methods
   // include/c/sk_imageinfo.h

   /*
    * Allocate a new imageinfo object. If colorspace is not null, it's owner-count will be
    * incremented automatically.
    */
   native private static long  nSkImageInfoMake(int width, int height, int colorType, int alphaType, long colorSpace);

   native private static long  nSkImageInfoMakeN32(int width, int height, int alphaType, long colorSpace);

   /*
    * Free the imageinfo object. If it contains a reference to a colorspace, its owner-count will
    * be decremented automatically.
    */
   native private static void  nSkImageInfoDelete(long ref);

   /*
    * Get the SKColorType (as an ordinal)
    */
   native private static int  nSkImageInfoGetColorType(long ref);

}
