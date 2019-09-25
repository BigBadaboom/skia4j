#include <jni.h>        // JNI header provided by JDK
//#include <stdio.h>      // C Standard IO Header
#include "include/c/sk_types.h"
#include "include/c/sk_canvas.h"

#ifdef __cplusplus
extern "C" {
#endif


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawPaint
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawPaint
  (JNIEnv *env, jclass cls, jlong nativeObj, jlong nativePaintObj)
{
   sk_canvas_t*  canvas = (sk_canvas_t*) nativeObj;
   sk_paint_t*   paint = (sk_paint_t*) nativePaintObj;

   sk_canvas_draw_paint(canvas, paint);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawRect
 * Signature: (JFFFFJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawRect
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jlong nativePaintObj)
{
   sk_canvas_t*  canvas = (sk_canvas_t*) nativeObj;
   sk_paint_t*   paint = (sk_paint_t*) nativePaintObj;

   sk_rect_t  rect;
   rect.left = (float) left;
   rect.top = (float) top;
   rect.right = (float) right;
   rect.bottom = (float) bottom;

   sk_canvas_draw_rect(canvas, &rect, paint);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawCircle
 * Signature: (JFFFJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawCircle
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat cx, jfloat cy, jfloat radius, jlong nativePaintObj)
{
   sk_canvas_t*  canvas = (sk_canvas_t*) nativeObj;
   sk_paint_t*   paint = (sk_paint_t*) nativePaintObj;

   sk_canvas_draw_circle(canvas, cx, cy, radius, paint);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawOval
 * Signature: (JFFFFJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawOval
(JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jlong nativePaintObj)
{
 sk_canvas_t*  canvas = (sk_canvas_t*) nativeObj;
 sk_paint_t*   paint = (sk_paint_t*) nativePaintObj;

 sk_rect_t  rect;
 rect.left = (float) left;
 rect.top = (float) top;
 rect.right = (float) right;
 rect.bottom = (float) bottom;

 sk_canvas_draw_oval(canvas, &rect, paint);
}


#ifdef __cplusplus
}
#endif
