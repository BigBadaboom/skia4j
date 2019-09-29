#include <jni.h>        // JNI header provided by JDK

#include "include/com_caverock_skia4j_SkImage.h"

#include "include/core/SkImage.h"
#include "include/core/SkData.h"
#include "types.h"


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageEncode
 * Signature: (JII)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkImage_nSkImageEncode
  (JNIEnv *env, jclass cls, jlong nativeObj, jint encodedImageFormat, jint quality)
{
   SkImage*  image = AsImage(nativeObj);
   SkData*   data = image->encodeToData(static_cast<SkEncodedImageFormat>(encodedImageFormat), quality).release();
   return ToData( data );
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkMakeFromEncoded
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkImage_nSkMakeFromEncoded__J
  (JNIEnv *env, jclass cls, jlong dataNativeObj)
{
   SkData*  data = AsData(dataNativeObj);
   return ToImage(SkImage::MakeFromEncoded(sk_ref_sp(data), NULL).release());
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkMakeFromEncoded
 * Signature: (JIIII)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkImage_nSkMakeFromEncoded__JIIII
  (JNIEnv *env, jclass cls, jlong dataNativeObj, jint left, jint top, jint right, jint bottom)
{
   SkData*  data = AsData(dataNativeObj);

   SkIRect  rect { left, top, right, bottom };
   return ToImage(SkImage::MakeFromEncoded(sk_ref_sp(data), &rect).release());
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageRef
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkImage_nSkImageRef
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
	SkSafeRef(AsImage(nativeObj));
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageUnref
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkImage_nSkImageUnref
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
	SkSafeUnref(AsImage(nativeObj));
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageGetWidth
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkImage_nSkImageGetWidth
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsImage(nativeObj)->width();
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageGetHeight
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkImage_nSkImageGetHeight
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsImage(nativeObj)->height();
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageGetAlphaType
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkImage_nSkImageGetAlphaType
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsImage(nativeObj)->alphaType();
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageGetColorSpace
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkImage_nSkImageGetColorSpace
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
	const SkImage*  image = AsImage(nativeObj);
   return ToColorSpace(image->colorSpace());
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageGetUniqueId
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkImage_nSkImageGetUniqueId
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsImage(nativeObj)->uniqueID();
}

