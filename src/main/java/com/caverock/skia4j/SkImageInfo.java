package com.caverock.skia4j;

public class SkImageInfo  implements AutoCloseable
{
   private int           width;
   private int           height;
   private SkColorType   colorType = SkColorType.Unknown;
   private SkAlphaType   alphaType = SkAlphaType.Unknown;
   private SkColorSpace  colorSpace = null;     // NYI

   // Reference to the native sk_image_info object
   private long  nRef = 0;


   //--------------------------------------------------------------------------


   /**
    * Describes the type and precision of values to use for colour components.
    */
   public enum SkColorType
   {
      Unknown(0),

      /**
       * A 32-bit color with the format RGBA.
       */
      Rgba8888(1),

      /**
       * A 32-bit color with the format BGRA.
       */
      Bgra8888(2),

      /**
       * An 8-bit alpha-only color.
       */
      Alpha8(3),

      /**
       * An opaque 8-bit grayscale color.
       */
      Gray8(4),

      /**
       * A 16 bit floating-point based color with the format RGBA.
       */
      RgbaF16(5),

      /**
       * A 32 bit floating-point based color with the format RGBA.
       */
      RgbaF32(6);



      private int value = 0;
      
      private SkColorType(int val) {
         this.value = val;
      }
   }


   /**
    * Describes how to interpret the alpha component of a pixel.
    */
   public enum SkAlphaType
   {
      Unknown(0),
      /**
       * All pixels are opaque.
       */
      Opaque(1),
      /**
       * All pixels have their alpha pre-multiplied in their color components.
       */
      Premul(2),
      /**
       * <p>All pixels have their color components stored without any regard to the alpha. e.g. this is the default configuration for PNG images.</p>
       * <p>This alpha-type is ONLY supported for input images. Rendering cannot generate this on output.</p>
       */
      Unpremul(3);


      private int value = 0;
      
      private SkAlphaType(int val) {
         this.value = val;
      }
   }



   //--------------------------------------------------------------------------
 

   /**
    * Creates an instance of SkImageInfo with the specified dimensions, 8 bit
    * colour channels, and an 8 bit alpha channel.  The colour space will be sRGB.
    * 
    * @param width
    * @param height
    */
   public SkImageInfo(int width, int height)
   {
      this(width, height, SkColorType.Rgba8888, SkAlphaType.Premul, null);
   }


   public SkImageInfo(int width, int height, SkColorType colorType, SkAlphaType alphaType, SkColorSpace colorSpace)
   {
      this.width = width;
      this.height = height;
      this.colorType = colorType;
      this.alphaType = alphaType;
      this.colorSpace = (colorSpace != null) ? colorSpace
                                             : SkColorSpace.createSrgb();

/**/System.out.println("w="+this.width);
/**/System.out.println("h="+this.height);
/**/System.out.println("colorType="+this.colorType);
/**/System.out.println("alphaType="+this.alphaType);
/**/System.out.println("colorSpace="+this.colorSpace);
      this.nRef = nSkImageInfoNew(width, height, colorType.value, alphaType.value, this.colorSpace.nativeRef());
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
   native private static long  nSkImageInfoNew(int width, int height, int colorType, int alphaType, long colorSpace);
   //SK_API sk_imageinfo_t* sk_imageinfo_new(int width, int height, sk_colortype_t ct, sk_alphatype_t at,
   //                                        sk_colorspace_t* cs);

   /*
    * Free the imageinfo object. If it contains a reference to a colorspace, its owner-count will
    * be decremented automatically.
    */
   native private static void  nSkImageInfoDelete(long ref);
   //void sk_imageinfo_delete(sk_imageinfo_t*);

   /*
    * int32_t          sk_imageinfo_get_width(sk_imageinfo_t*);
    * int32_t          sk_imageinfo_get_height(sk_imageinfo_t*);
    * sk_colortype_t   sk_imageinfo_get_colortype(sk_imageinfo_t*);
    * sk_alphatype_t   sk_imageinfo_get_alphatype(sk_imageinfo_t*);
    */
   
   /*
    * Return the colorspace object reference contained in the imageinfo, or null if there is none.
    * Note: this does not modify the owner-count on the colorspace object. If the caller needs to
    * use the colorspace beyond the lifetime of the imageinfo, it should manually call
    * sk_colorspace_ref() (and then call unref() when it is done).
    */
   //sk_colorspace_t* sk_imageinfo_get_colorspace(sk_imageinfo_t*);

}
