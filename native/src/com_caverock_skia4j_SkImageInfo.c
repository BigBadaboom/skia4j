#include <jni.h>        // JNI header provided by JDK
//#include <stdio.h>      // C Standard IO Header
#include "include/c/sk_types.h"
#include "include/c/sk_imageinfo.h"

#ifdef __cplusplus
extern "C" {
#endif


JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkImageInfo_nSkImageInfoNew
   (JNIEnv *env, jclass cls, jint width, jint height, jint colorType, jint alphaType, jlong colorSpace)
{
   sk_imageinfo_t* nativeObj = sk_imageinfo_new(width, height, (sk_colortype_t) colorType, (sk_alphatype_t) alphaType, (sk_colorspace_t*) colorSpace);
   return (jlong) nativeObj;
}



JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkImageInfo_nSkImageInfoDelete
   (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_imageinfo_delete((sk_imageinfo_t*) nativeObj);
}


#ifdef __cplusplus
}
#endif
