package com.caverock.skia4j;

public class SkSurface implements AutoCloseable
{
   // Reference to the native sk_image_info object
   private long  nRef = 0;
   
   // Keep a record of the surface metadata
   private SkImageInfo  info;


   //--------------------------------------------------------------------------


   private SkSurface(long ref, SkImageInfo info) {
      this.nRef = ref;
      this.info = info;
   }


   /**
    * Creates a new surface with the specified image parameters.
    * @param info The image configuration parameters.
    * @return a new surface with the specified configuration.
    */
   public static SkSurface  makeRaster(SkImageInfo info/*, SkSurfaceprops props*/)
   {
      long ref = nSkSurfaceNewRaster(info.nativeRef(),
/*                                     (props != null) ? props.nativeRef :*/ 0); // FIXME
      if (ref == 0)
         throw new IllegalArgumentException("Surface could not be created. Unsupported configuration, or out of memory.");

      return new SkSurface(ref, info);
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
     nSkSurfaceUnref(nativeRef());
     nRef = 0;
   }


   protected long  nativeRef()
   {
      if (nRef == 0)
         throw new IllegalArgumentException("This SkSurface object has already been released.");
      return nRef;
   }


   @Override
   public String toString()
   {
      return String.format("%s (ref:%x)", getClass().getSimpleName(), nRef);
   }


   //--------------------------------------------------------------------------


   /**
    * Gets the canvas associated with this surface which you can use for drawing onto.
    *
    * Note: the canvas is owned by the surface, so the returned object is only valid
    * while the owning surface is valid.
    */
   public SkCanvas  getCanvas()
   {
      return new SkCanvas(nSkSurfaceGetCanvas(nRef));
   }



   //--------------------------------------------------------------------------


   /**
    * Returns an SkImage capturing the SkSurface contents. Subsequent drawing to
    * the SkSurface contents will not be reflected in the SkImage.
    */
   public SkImage  makeImageSnapshot()
   {
      long imageRef = nSkSurfaceNewImageSnapshot(nRef);
      if (imageRef == 0)
         return null;
      
      return new SkImage(nSkSurfaceNewImageSnapshot(nRef), info);
   }



   //--------------------------------------------------------------------------
   // Native methods
   // include/c/sk_imageinfo.h

   /*
    * Return a new surface, with the memory for the pixels automatically
    * allocated.  If the requested surface cannot be created, or the
    * request is not a supported configuration, NULL will be returned.
    * @param sk_imageinfo_t* Specify the width, height, color type, and
    *                        alpha type for the surface.
    * @param sk_surfaceprops_t* If not NULL, specify additional non-default
    *                           properties of the surface.
    */
   //SK_API sk_surface_t* sk_surface_new_raster(const sk_imageinfo_t*, const sk_surfaceprops_t*);
   // Return value of 0 represents null
   native private static long  nSkSurfaceNewRaster(long info, long props);

   /*
    * Create a new surface which will draw into the specified pixels
    * with the specified rowbytes.  If the requested surface cannot be
    * created, or the request is not a supported configuration, NULL
    * will be returned.
    * @param sk_imageinfo_t* Specify the width, height, color type, and
    *                        alpha type for the surface.
    * @param void* pixels Specify the location in memory where the
    *                     destination pixels are.  This memory must
    *                     outlast this surface.
    * @param size_t rowBytes Specify the difference, in bytes, between
    *                        each adjacent row.  Should be at least
    *                        (width * sizeof(one pixel)).
    * @param sk_surfaceprops_t* If not NULL, specify additional non-default
    *                           properties of the surface.
    */
   //SK_API sk_surface_t* sk_surface_new_raster_direct(const sk_imageinfo_t*,
   //                                                  void* pixels, size_t rowBytes,
   //                                                  const sk_surfaceprops_t* props);
   // Return value of 0 represents null
   native private static long  nSkSurfaceNewRasterDirect(long info, byte[] pixels, int rowBytes, long props);

   /*
    * Decrement the reference count. If the reference count is 1 before
    * the decrement, then release both the memory holding the
    * sk_surface_t and any pixel memory it may be managing.  New
    * sk_surface_t are created with a reference count of 1.
    */
   //SK_API void sk_surface_unref(sk_surface_t*);
   native private static void  nSkSurfaceUnref(long ref);

   /*
    * Return the canvas associated with this surface. Note: the canvas is owned by the surface,
    * so the returned object is only valid while the owning surface is valid.
    */
   //SK_API sk_canvas_t* sk_surface_get_canvas(sk_surface_t*);
   native private static long  nSkSurfaceGetCanvas(long ref);

   /*
    * Returns a bitmap snapshot of the current surface.
    */
   //SK_API sk_image_t* sk_surface_new_image_snapshot(sk_surface_t*);
   native private static long  nSkSurfaceNewImageSnapshot(long ref);

   
}
