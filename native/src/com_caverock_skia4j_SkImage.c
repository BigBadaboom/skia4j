#include <jni.h>        // JNI header provided by JDK
//#include <stdio.h>      // C Standard IO Header
#include "include/c/sk_types.h"
#include "include/c/sk_image.h"

#ifdef __cplusplus
extern "C" {
#endif


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageEncode
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkImage_nSkImageEncode
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_image_t*  image = (sk_image_t*) nativeObj;
   sk_data_t*  encoded = sk_image_encode(image);
   return (encoded != NULL) ? (jlong) encoded
                            : 0;
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageRef
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkImage_nSkImageRef
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_image_ref((sk_image_t*) nativeObj);
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageUnref
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkImage_nSkImageUnref
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_image_unref((sk_image_t*) nativeObj);
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageGetWidth
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkImage_nSkImageGetWidth
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_image_t*  image = (sk_image_t*) nativeObj;
   return (jint) sk_image_get_width(image);
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageGetHeight
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkImage_nSkImageGetHeight
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_image_t*  image = (sk_image_t*) nativeObj;
   return (jint) sk_image_get_height(image);
}


/*
 * Class:     com_caverock_skia4j_SkImage
 * Method:    nSkImageGetUniqueId
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkImage_nSkImageGetUniqueId
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_image_t*  image = (sk_image_t*) nativeObj;
   return (jint) sk_image_get_unique_id(image);
}


#ifdef __cplusplus
}
#endif
