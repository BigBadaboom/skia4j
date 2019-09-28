#include <jni.h>        // JNI header provided by JDK

#include "include/com_caverock_skia4j_SkSurface.h"

#include "include/core/SkSurface.h"
#include "types.h"


/*
 * Class:     com_caverock_skia4j_SkSurface
 * Method:    nSkSurfaceMakeRaster
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkSurface_nSkSurfaceMakeRaster
  (JNIEnv *env, jclass cls, jlong imageInfo, jlong surfaceProps)
{
   SkImageInfo*     info = AsImageInfo(imageInfo);
   SkSurfaceProps*  props = AsSurfaceProps(surfaceProps);
   return ToSurface( SkSurface::MakeRaster(*info, props).release() );
}


/*
 * Class:     com_caverock_skia4j_SkSurface
 * Method:    nSkSurfaceMakeRasterDirect
 * Signature: (J[BIJ)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkSurface_nSkSurfaceMakeRasterDirect
  (JNIEnv *env, jclass cls, jlong imageInfo, jbyteArray pixels, jint rowBytes, jlong surfaceProps)
{
   SkImageInfo*     info = AsImageInfo(imageInfo);
   SkSurfaceProps*  props = AsSurfaceProps(surfaceProps);
   return ToSurface( SkSurface::MakeRasterDirect(*info, (void*) pixels, rowBytes, props).release() );
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
   SkSafeUnref( AsSurface(nativeObj) );
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
   return ToCanvas( AsSurface(nativeObj)->getCanvas() );
}

/**
   Returns an sk_image object which abstracts the rectangle of pixels representing the image on this surface.
 */
/*
 * Class:     com_caverock_skia4j_SkSurface
 * Method:    nSkSurfaceNewImageSnapshot
 * Signature: (J)Ljava/lang/Long;
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkSurface_nSkSurfaceMakeImageSnapshot
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
	return ToImage( AsSurface(nativeObj)->makeImageSnapshot().release() );
}


