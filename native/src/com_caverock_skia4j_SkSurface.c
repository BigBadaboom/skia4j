#include <jni.h>        // JNI header provided by JDK
//#include <stdio.h>      // C Standard IO Header
#include "include/c/sk_types.h"
#include "include/c/sk_surface.h"

#ifdef __cplusplus
extern "C" {
#endif


/**
    Return a new surface, with the memory for the pixels automatically
    allocated.  If the requested surface cannot be created, or the
    request is not a supported configuration, NULL will be returned.

    @param sk_imageinfo_t* Specify the width, height, color type, and
                           alpha type for the surface.

    @param sk_surfaceprops_t* If not NULL, specify additional non-default
                              properties of the surface.
*/
/*
 * Class:     com_caverock_skia4j_SkSurface
 * Method:    nSkSurfaceNewRaster
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkSurface_nSkSurfaceNewRaster
  (JNIEnv *env, jclass cls, jlong imageInfo, jlong surfaceProps)
{
   sk_imageinfo_t*     info = (sk_imageinfo_t*) imageInfo;
   sk_surfaceprops_t*  props = (sk_surfaceprops_t*) surfaceProps;
   sk_surface_t*       nativeObj = sk_surface_new_raster(info, props);
   return (jlong) nativeObj;
}

/**
    Create a new surface which will draw into the specified pixels
    with the specified rowbytes.  If the requested surface cannot be
    created, or the request is not a supported configuration, NULL
    will be returned.

    @param sk_imageinfo_t* Specify the width, height, color type, and
                           alpha type for the surface.
    @param void* pixels Specify the location in memory where the
                        destination pixels are.  This memory must
                        outlast this surface.
     @param size_t rowBytes Specify the difference, in bytes, between
                           each adjacent row.  Should be at least
                           (width * sizeof(one pixel)).
    @param sk_surfaceprops_t* If not NULL, specify additional non-default
                              properties of the surface.
*/
/*
 * Class:     com_caverock_skia4j_SkSurface
 * Method:    nSkSurfaceNewRasterDirect
 * Signature: (J[BIJ)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkSurface_nSkSurfaceNewRasterDirect
  (JNIEnv *env, jclass cls, jlong imageInfo, jbyteArray pixels, jint rowBytes, jlong surfaceProps)
{
   sk_imageinfo_t*     info = (sk_imageinfo_t*) imageInfo;
   sk_surfaceprops_t*  props = (sk_surfaceprops_t*) surfaceProps;
   sk_surface_t*       nativeObj = sk_surface_new_raster_direct(info, (void*) pixels, rowBytes, props);
   return (jlong) nativeObj;
}

/**
    Decrement the reference count. If the reference count is 1 before
    the decrement, then release both the memory holding the
    sk_surface_t and any pixel memory it may be managing.  New
    sk_surface_t are created with a reference count of 1.
*/
/*
 * Class:     com_caverock_skia4j_SkSurface
 * Method:    nSkSurfaceUnref
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkSurface_nSkSurfaceUnref
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_surface_unref((sk_surface_t*) nativeObj);
}

/**
 *  Return the canvas associated with this surface. Note: the canvas is owned by the surface,
 *  so the returned object is only valid while the owning surface is valid.
 */
/*
 * Class:     com_caverock_skia4j_SkSurface
 * Method:    nSkSurfaceGetCanvas
 * Signature: (J)Ljava/lang/Long;
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkSurface_nSkSurfaceGetCanvas
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_surface_t*  surface = (sk_surface_t*) nativeObj;
   sk_canvas_t*   canvasNativeObj = sk_surface_get_canvas(surface);
   return (jlong) canvasNativeObj;
}

/**
   Returns an sk_image object which abstracts the rectangle of pixels representing the image on this surface.
 */
/*
 * Class:     com_caverock_skia4j_SkSurface
 * Method:    nSkSurfaceNewImageSnapshot
 * Signature: (J)Ljava/lang/Long;
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkSurface_nSkSurfaceNewImageSnapshot
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_surface_t*  surface = (sk_surface_t*) nativeObj;
   sk_image_t*    imageNativeObj = sk_surface_new_image_snapshot(surface);
   return (imageNativeObj != NULL) ? (jlong) imageNativeObj
                                   : 0;
}


#ifdef __cplusplus
}
#endif
