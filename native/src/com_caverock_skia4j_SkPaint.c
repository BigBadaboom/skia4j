#include <jni.h>        // JNI header provided by JDK
//#include <stdio.h>      // C Standard IO Header
#include "include/c/sk_types.h"
#include "include/c/sk_paint.h"

#ifdef __cplusplus
extern "C" {
#endif


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintNew
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintNew
  (JNIEnv *env, jclass cls)
{
   sk_paint_t*  paint = sk_paint_new();
   return (paint != NULL) ? (jlong) paint
                          : 0;
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintDelete
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintDelete
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   sk_paint_delete((sk_paint_t*) nativeObj);
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintSetAntialias
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintSetAntialias
  (JNIEnv *env, jclass cls, jlong nativeObj, jboolean isAntialias)
{
   sk_paint_t*  paint = (sk_paint_t*) nativeObj;
   sk_paint_set_antialias(paint, (bool) isAntialias);
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintSetColor
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintSetColor
  (JNIEnv *env, jclass cls, jlong nativeObj, jint color)
{
   sk_paint_t*  paint = (sk_paint_t*) nativeObj;
   sk_paint_set_color(paint, (sk_color_t) color);
}


#ifdef __cplusplus
}
#endif
