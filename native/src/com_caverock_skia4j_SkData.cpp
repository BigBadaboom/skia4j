#include <jni.h>        // JNI header provided by JDK

#include "include/com_caverock_skia4j_SkData.h"

#include "include/core/SkData.h"
#include "types.h"


/*
 * Class:     com_caverock_skia4j_SkData
 * Method:    nSkMakeWithCopy
 * Signature: ([BII)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkData_nSkMakeWithCopy
  (JNIEnv *env, jclass cls, jbyteArray data, jint offset, jint length)
{
	// Get a pointer to the byte data and pin the buffer in the VM so it is not GC'd
	jbyte*  bptr = env->GetByteArrayElements(data, NULL);
	if (bptr == NULL)
	  return 0;  // return "null"
	// Make an SKData using a copy of the byte data
   long  result = ToData( SkData::MakeWithCopy(bptr + offset, length).release() );
   // Release the pin on our byte data
   env->ReleaseByteArrayElements(data, bptr, JNI_ABORT);
   return result;
}


/*
 * Class:     com_caverock_skia4j_SkData
 * Method:    nSkMakeFromFileName
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkData_nSkMakeFromFileName
(JNIEnv *env, jclass cls, jstring filename)
{
	const char *str = env->GetStringUTFChars(filename, 0);

   jlong  result = ToData( SkData::MakeFromFileName(str).release() );

	env->ReleaseStringUTFChars(filename, str);
	return result;
}


/*
 * Class:     com_caverock_skia4j_SkData
 * Method:    nSkDataRef
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkData_nSkDataRef
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   SkSafeRef( AsData(nativeObj) );
}


/*
 * Class:     com_caverock_skia4j_SkData
 * Method:    nSkDataUnref
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkData_nSkDataUnref
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   SkSafeUnref( AsData(nativeObj) );
}


/*
 * Class:     com_caverock_skia4j_SkData
 * Method:    nSkDataGetSize
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkData_nSkDataGetSize
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsData(nativeObj)->size();
}


/*
 * Class:     com_caverock_skia4j_SkData
 * Method:    nSkDataCopyToByteArray
 * Signature: (J[B)V
 */
JNIEXPORT jbyteArray JNICALL Java_com_caverock_skia4j_SkData_nSkDataToByteArray
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   SkData*  data = AsData(nativeObj);
   const void* from = data->data();
   size_t      size = data->size();

   // Create the Java byte array
   jbyteArray bytes = env->NewByteArray(size);
   // Copy the data into it
   env->SetByteArrayRegion(bytes, 0, size, (jbyte*) from);

   return bytes;
}


