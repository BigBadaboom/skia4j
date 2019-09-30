package com.caverock.skia4j;

import java.io.File;

/**
 * An SkImage describes a two dimensional array of pixels to draw. The pixels may be decoded in a raster bitmap,
 * encoded in a Picture or compressed data stream, or located in GPU memory as a GPU texture.
 *
 * SkImage cannot be modified after it is created. Image may allocate additional storage as needed; for instance,
 * an encoded Image may decode when drawn.
 *
 */
public class SkImage implements AutoCloseable
{
   // Reference to the native sk_image object
   private long  nRef = 0;

   // Keep a record of the surface metadata
   private int           width;
   private int           height;
   private SkColorType   colorType = SkColorType.kUnknown;
   private SkAlphaType   alphaType = SkAlphaType.kUnknown;
   private SkColorSpace  colorSpace = null;


   //--------------------------------------------------------------------------


   SkImage(long ref, SkImageInfo info)
   {
      this.nRef = ref;
      this.width = info.getWidth();
      this.height = info.getHeight();
      this.colorType = info.getColorType();
      this.alphaType = info.getAlphaType();
      this.colorSpace = info.getColorSpace();
   }


   SkImage(long ref, int width, int height, SkColorType colorType, SkAlphaType alphaType, SkColorSpace colorSpace)
   {
      this.nRef = ref;
      this.width = width;
      this.height = height;
      this.colorType = colorType;
      this.alphaType = alphaType;
      this.colorSpace = colorSpace;
   }


   //--------------------------------------------------------------------------


   /**
    * Create an SkImage from the (encoded) contents of an SkData object.
    * 
    * @param encodedData a data object containing an encoded image such as a PNG or JPEG
    * @return an SkImage containing a decoded bitmap
    */
   public static SkImage  makeFromEncoded(SkData encodedData)
   {
      return makeFromEncoded(encodedData, null);
   }


   /**
    * Create an SkImage from the (encoded) contents of an SkData object.
    * 
    * @param encodedData a data object containing an encoded image such as a PNG or JPEG
    * @param subset rectangle describing the portion of the image to create the image from. Leave null for entire image.
    * @return an SkImage containing a decoded bitmap
    */
   public static SkImage  makeFromEncoded(SkData encodedData, SkIRect subset)
   {
      if (encodedData == null)
         return null;
      long  nRef = (subset != null) ? nSkMakeFromEncoded(encodedData.nativeRef(), subset.getLeft(), subset.getTop(), subset.getRight(), subset.getBottom())
                                    : nSkMakeFromEncoded(encodedData.nativeRef());
      if (nRef == 0)
         return null;

      // Populate the metadata fields
      int  width = nSkImageGetWidth(nRef);
      int  height = nSkImageGetHeight(nRef);
      SkAlphaType  alphaType = SkAlphaType.from( nSkImageGetAlphaType(nRef) );

      long csRef = nSkImageGetColorSpace(nRef);
      SkColorSpace  colourSpace = (csRef != 0) ? new SkColorSpace(csRef) : null;
      
      // Their doesn't seem to be a way to get the ColorType.  So we'll just have to use kUnknown.
      return new SkImage(nRef, width, height, SkColorType.kUnknown, alphaType, colourSpace);

   }


   /**
    * <p>Convenience method to read an encoded image file, such as a PNG or JPEG,
    * and return an SkImage object.</p>
    * 
    * <p>Internally, this method creates an {@code SkData} from the file, creates
    * an {@code SkImage} from it, and then closes the data object.
    * 
    * @param file the file to load 
    * @return an SkImage containing a decoded bitmap
    */
   public static SkImage  makeFromEncoded(File file)
   {
      try (SkData  data = SkData.makeFromFile(file)) {
         SkImage image = SkImage.makeFromEncoded(data, null);
         return image;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }



   //--------------------------------------------------------------------------


   /**
    * Tidy up the native resource associated with this class.
    * Must be called, when this object is no longer needed, to avoid memory leaks.
    *
    * You can call close() directly, or use try-with-resources.
   */
   @Override
   public void  close() throws Exception
   {
      nSkImageUnref(nativeRef());
      nRef = 0;
   }


   protected long  nativeRef()
   {
      if (nRef == 0)
         throw new IllegalArgumentException("This SkImage object has already been released.");
      return nRef;
   }


   @Override
   public String toString()
   {
      return String.format("%s (ref:%x)", getClass().getSimpleName(), nRef);
   }


   //--------------------------------------------------------------------------


   public int  getWidth()
   {
      return this.width;
   }

   public int  getHeight()
   {
      return this.height;
   }

   public SkAlphaType  getAlphaType()
   {
      return this.alphaType;
   }

   public SkColorType  getColorType()
   {
      return this.colorType;
   }

   public SkColorSpace  getColorSpace()
   {
      return this.colorSpace;
   }


   public int  getUniqueID()
   {
      if (nRef == 0)
         throw new IllegalArgumentException("This SkImage object has already been released.");
      return nSkImageGetUniqueId(nativeRef());
   }



   //--------------------------------------------------------------------------


   /**
    * Encodes SkImage pixels, returning result as SkData.
    *
    * Returns null if encoding fails, or if {@code encodedImageFormat} is not supported.
    * 
    * This method is equivalent to {@code encodeToData(encodedImageFormat, 100)}
    *
    * @param encodedImageFormat the format into which the image should be encoded
    * @return an SkData object representing the encoded data
    */
   public SkData  encodeToData(SkEncodedImageFormat encodedImageFormat)
   {
      return encodeToData(encodedImageFormat, 100);
   }


   /**
    * Encodes SkImage pixels, returning result as SkData.
    *
    * Returns null if encoding fails, or if {@code encodedImageFormat} is not supported.
    *
    * {@code quality} is a platform and format specific metric trading off size and encoding error.
    * When used, quality equaling 100 encodes with the least error. Some encoders do not use the quality
    * parameter. In those cases, the value will be ignored.
    * 
    * @param encodedImageFormat the format into which the image should be encoded.
    * @param quality the quality level to use for enoding. This is in the range 0-100.
    * @return an SkData object representing the encoded data.
    */
   public SkData  encodeToData(SkEncodedImageFormat encodedImageFormat, int quality)
   {
      long dataRef = nSkImageEncode(nativeRef(), encodedImageFormat.ordinal(), quality);
      return (dataRef != 0) ? new SkData(dataRef)
                            : null;
   }


   // TODO more NYI


   //--------------------------------------------------------------------------
   // Native methods
   // include/c/sk_paint.h


   native private static long  nSkImageEncode(long image, int format, int quality);

   native private static long  nSkMakeFromEncoded(long dataRef);
   native private static long  nSkMakeFromEncoded(long dataRef, int left, int top, int right, int bottom);

   native private static void  nSkImageRef(long ref);
   native private static void  nSkImageUnref(long ref);

   native private static int  nSkImageGetWidth(long ref);
   native private static int  nSkImageGetHeight(long ref);

   native private static int   nSkImageGetAlphaType(long ref);
   native private static long  nSkImageGetColorSpace(long ref);

   native private static int  nSkImageGetUniqueId(long ref);


}
