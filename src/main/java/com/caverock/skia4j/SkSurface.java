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
/**/System.out.println(String.format("makeRaster ref=%x",info.nativeRef()));
      long ref = nSkSurfaceMakeRaster(info.nativeRef(),
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
      long imageRef = nSkSurfaceMakeImageSnapshot(nRef);
      if (imageRef == 0)
         return null;
      
      return new SkImage(nSkSurfaceMakeImageSnapshot(nRef), info);
   }



   //--------------------------------------------------------------------------
   // Native methods
   // include/c/sk_imageinfo.h

   // Return value of 0 represents null
   native private static long  nSkSurfaceMakeRaster(long info, long props);

   native private static long  nSkSurfaceMakeRasterDirect(long info, byte[] pixels, int rowBytes, long props);

   native private static void  nSkSurfaceUnref(long ref);

   native private static long  nSkSurfaceGetCanvas(long ref);

   native private static long  nSkSurfaceMakeImageSnapshot(long ref);

   
}
