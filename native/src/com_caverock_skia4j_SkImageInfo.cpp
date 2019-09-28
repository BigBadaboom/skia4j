#include <jni.h>        // JNI header provided by JDK

#include "include/com_caverock_skia4j_SkImageInfo.h"

#include "include/core/SkImageInfo.h"
#include "types.h"



/*
 * Class:     com_caverock_skia4j_SkImageInfo
 * Method:    nSkImageInfoMake
 * Signature: (IIIIJ)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkImageInfo_nSkImageInfoMake
   (JNIEnv *env, jclass cls, jint width, jint height, jint colorType, jint alphaType, jlong colorSpace)
{
	SkColorSpace*  csp = AsColorSpace(colorSpace);

   SkImageInfo*  nativeObj = new SkImageInfo( SkImageInfo::Make(width, height, static_cast<SkColorType>(colorType), static_cast<SkAlphaType>(alphaType), sk_ref_sp(csp)) );
   return ToImageInfo( nativeObj );
}


/*
 * Class:     com_caverock_skia4j_SkImageInfo
 * Method:    nSkImageInfoMakeN32
 * Signature: (IIIJ)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkImageInfo_nSkImageInfoMakeN32
  (JNIEnv *env, jclass cls, jint width, jint height, jint alphaType, jlong colorSpace)
{
	SkColorSpace*  csp = AsColorSpace(colorSpace);

	SkImageInfo*  nativeObj = new SkImageInfo( SkImageInfo::MakeN32(width, height, static_cast<SkAlphaType>(alphaType), sk_ref_sp(csp)) );
   return ToImageInfo( nativeObj );
}


/*
 * Class:     com_caverock_skia4j_SkImageInfo
 * Method:    nSkImageInfoDelete
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkImageInfo_nSkImageInfoDelete
   (JNIEnv *env, jclass cls, jlong nativeObj)
{
	delete AsImageInfo(nativeObj);
}


/*
 * Class:     com_caverock_skia4j_SkImageInfo
 * Method:    nSkImageInfoGetColorType
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkImageInfo_nSkImageInfoGetColorType
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
	return AsImageInfo(nativeObj)->colorType();
}

