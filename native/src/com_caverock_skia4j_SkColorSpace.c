#include <jni.h>        // JNI header provided by JDK
//#include <stdio.h>      // C Standard IO Header
#include "include/c/sk_types.h"
#include "include/c/sk_colorspace.h"

#ifdef __cplusplus
extern "C" {
#endif


JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkColorSpace_nColorSpaceNewSrgb
   (JNIEnv *env, jclass cls)
{
    sk_colorspace_t* nativeObj = sk_colorspace_new_srgb();
    return (jlong) nativeObj;
}


#ifdef __cplusplus
}
#endif
