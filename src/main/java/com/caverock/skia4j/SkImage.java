package com.caverock.skia4j;

import com.caverock.skia4j.SkImageInfo.SkAlphaType;
import com.caverock.skia4j.SkImageInfo.SkColorType;

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
   private SkImageInfo  info;


   //--------------------------------------------------------------------------


   protected SkImage(long ref, SkImageInfo info)
   {
      this.nRef = ref;
      this.info = info;
   }


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
      return info.getWidth();
   }

   public int  getHeight()
   {
      return info.getHeight();
   }

   public int  getUniqueID()
   {
      if (nRef == 0)
         throw new IllegalArgumentException("This SkImage object has already been released.");
      return nSkImageGetUniqueId(nativeRef());
   }

   public SkAlphaType  getAlphaType()
   {
      return info.getAlphaType();
   }

   public SkColorType  getColorType()
   {
      return info.getColorType();
   }

   public SkColorSpace  getColorSpace()
   {
      return info.getColorSpace();
   }


   public SkImageInfo  getImageInfo()
   {
      return this.info;
   }


   //--------------------------------------------------------------------------


   /**
    * Encodes SkImage pixels, returning result as SkData.
    *
    * Returns null if encoding fails, or if {@code encodedImageFormat} is not supported.
    *
    * {@code quality} is a platform and format specific metric trading off size and encoding error. When used, quality equaling 100 encodes with the least error. quality may be ignored by the encoder.
    * 
    * Note: at this time the only format supported in SkEncodedImageFormat.kPNG. The {@code quality} parameter is ignored.
    * 
    * @param encodedImageFormat the format into which the image should be encoded
    * @param quality quality setting (depends on the chosen image format)
    * @return an SkData object representing the encoded data
    */
   public SkData  encodeToData(SkEncodedImageFormat encodedImageFormat, int quality)
   {
      if (encodedImageFormat != SkEncodedImageFormat.kPNG)
         return null;
      long dataRef = nSkImageEncode(nativeRef());  // C API only supports PNG for now.
      return (dataRef != 0) ? new SkData(dataRef)
                            : null;
   }


   // TODO more NYI


   //--------------------------------------------------------------------------
   // Native methods
   // include/c/sk_paint.h

   /**
    *  Return a new image that has made a copy of the provided pixels, or NULL on failure.
    *  Balance with a call to sk_image_unref().
    */
   //SK_API sk_image_t* sk_image_new_raster_copy(const sk_imageinfo_t*, const void* pixels, size_t rowBytes);
   //native private static long  nSkImageNewRasterCopy(long imageInfo, byte[] pixels, int rowBytes);

   /**
    *  If the specified data can be interpreted as a compressed image (e.g. PNG or JPEG) then this
    *  returns an image. If the encoded data is not supported, returns NULL.
    *
    *  On success, the encoded data may be processed immediately, or it may be ref()'d for later
    *  use.
    */
   //SK_API sk_image_t* sk_image_new_from_encoded(const sk_data_t* encoded, const sk_irect_t* subset);

   /**
    *  Encode the image's pixels and return the result as a new PNG in a
    *  sk_data_t, which the caller must manage: call sk_data_unref() when
    *  they are done.
    *
    *  If the image type cannot be encoded, this will return NULL.
    */
   //SK_API sk_data_t* sk_image_encode(const sk_image_t*);
   native private static long  nSkImageEncode(long image);

   /**
    *  Increment the reference count on the given sk_image_t. Must be
    *  balanced by a call to sk_image_unref().
   */
   //SK_API void sk_image_ref(const sk_image_t*);
   native private static void  nSkImageRef(long image);

   /**
    *  Decrement the reference count. If the reference count is 1 before
    *  the decrement, then release both the memory holding the sk_image_t
    *  and the memory it is managing.  New sk_image_t are created with a
       reference count of 1.
   */
   //SK_API void sk_image_unref(const sk_image_t*);
   native private static void  nSkImageUnref(long image);

   /**
    *  Return the width of the sk_image_t/
    */
   //SK_API int sk_image_get_width(const sk_image_t*);
   native private static int  nSkImageGetWidth(long image);

   /**
    *  Return the height of the sk_image_t/
    */
   //SK_API int sk_image_get_height(const sk_image_t*);
   native private static int  nSkImageGetHeight(long image);

   /**
    *  Returns a non-zero value unique among all images.
    */
   //SK_API uint32_t sk_image_get_unique_id(const sk_image_t*);
   native private static int  nSkImageGetUniqueId(long image);

}
