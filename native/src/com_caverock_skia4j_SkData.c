#include <jni.h>        // JNI header provided by JDK
//#include <stdio.h>      // C Standard IO Header
#include "include/c/sk_types.h"
#include "include/c/sk_data.h"

#ifdef __cplusplus
extern "C" {
#endif


/*
 * Class:     com_caverock_skia4j_SkData
 * Method:    nSkDataRef
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkData_nSkDataRef
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_data_ref((sk_data_t*) nativeObj);
}


/*
 * Class:     com_caverock_skia4j_SkData
 * Method:    nSkDataUnref
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkData_nSkDataUnref
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_data_unref((sk_data_t*) nativeObj);
}


/*
 * Class:     com_caverock_skia4j_SkData
 * Method:    nSkDataGetSize
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkData_nSkDataGetSize
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_data_t*  data = (sk_data_t*) nativeObj;
   size_t      size = sk_data_get_size(data);
   return (jint) size;
}


/*
 * Class:     com_caverock_skia4j_SkData
 * Method:    nSkDataCopyToByteArray
 * Signature: (J[B)V
 */
JNIEXPORT jbyteArray JNICALL Java_com_caverock_skia4j_SkData_nSkDataToByteArray
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_data_t*  data = (sk_data_t*) nativeObj;
   const void* from = sk_data_get_data(data);
   size_t      size = sk_data_get_size(data);

   // Create the Java byte array
   jbyteArray bytes = (*env)->NewByteArray(env, size);
   // Copy the data into it
   (*env)->SetByteArrayRegion(env, bytes, 0, size, (jbyte*) from);

   return bytes;
}


#ifdef __cplusplus
}
#endif
