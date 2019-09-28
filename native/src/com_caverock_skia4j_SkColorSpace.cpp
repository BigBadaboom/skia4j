#include <jni.h>        // JNI header provided by JDK

#include "include/com_caverock_skia4j_SkColorSpace.h"

#include "include/core/SkColorSpace.h"
#include "types.h"


/*
 * Class:     com_caverock_skia4j_SkColorSpace
 * Method:    nSkColorSpaceMakeSRGB
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkColorSpace_nSkColorSpaceMakeSRGB
  (JNIEnv *env, jclass cls)
{
   return ToColorSpace( SkColorSpace::MakeSRGB().release() );
}


/*
 * Class:     com_caverock_skia4j_SkColorSpace
 * Method:    nSkColorSpaceMakeSRGBLinear
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkColorSpace_nSkColorSpaceMakeSRGBLinear
  (JNIEnv *env, jclass cls)
{
   return ToColorSpace( SkColorSpace::MakeSRGBLinear().release() );
}


/*
 * Class:     com_caverock_skia4j_SkColorSpace
 * Method:    nSkColorSpaceUnref
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkColorSpace_nSkColorSpaceUnref
  (JNIEnv *env, jclass cls, jlong nativeRef)
{
   SkSafeUnref( AsColorSpace(nativeRef) );
}


/*
 * Class:     com_caverock_skia4j_SkColorSpace
 * Method:    nSkColorSpaceIsSRGB
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkColorSpace_nSkColorSpaceIsSRGB
  (JNIEnv *env, jclass cls, jlong nativeRef)
{
	return AsColorSpace(nativeRef)->isSRGB();
}


/*
 * Class:     com_caverock_skia4j_SkColorSpace
 * Method:    nSkColorSpaceGammaIsLinear
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkColorSpace_nSkColorSpaceGammaIsLinear
  (JNIEnv *env, jclass cls, jlong nativeRef)
{
	return AsColorSpace(nativeRef)->gammaIsLinear();
}

