#include <jni.h>        // JNI header provided by JDK

#include "include/com_caverock_skia4j_SkCanvas.h"

#include "include/core/SkCanvas.h"
#include "types.h"



/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasSave
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasSave
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
    return AsCanvas(nativeObj)->save();
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasSaveLayer
 * Signature: (JJ)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasSaveLayer__JJ
  (JNIEnv *env, jclass cls, jlong nativeObj, jlong nativePaintObj)
{
   SkPaint*  paint = (nativePaintObj != 0) ? AsPaint(nativePaintObj)
                                            : NULL;
   return AsCanvas(nativeObj)->saveLayer(NULL, paint);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasSaveLayer
 * Signature: (JFFFFJ)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasSaveLayer__JFFFFJ
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jlong nativePaintObj)
{
   SkPaint*  paint = (nativePaintObj != 0) ? AsPaint(nativePaintObj)
                                            : NULL;
   SkRect  rect { left, top, right, bottom };
   return AsCanvas(nativeObj)->saveLayer(&rect, paint);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasSaveAlpha
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasSaveLayerAlpha__JI
  (JNIEnv *env, jclass cls, jlong nativeObj, jint alpha)
{
   return AsCanvas(nativeObj)->saveLayerAlpha(NULL, u8clamp(alpha));
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasSaveAlpha
 * Signature: (JFFFFI)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasSaveLayerAlpha__JFFFFI
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jint alpha)
{
   SkRect  rect { left, top, right, bottom };
   return AsCanvas(nativeObj)->saveLayerAlpha(&rect, u8clamp(alpha));
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasGetSaveCount
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasGetSaveCount
  (JNIEnv *, jclass, jlong nativeObj)
{
   return AsCanvas(nativeObj)->getSaveCount();
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasRestore
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasRestore
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   AsCanvas(nativeObj)->restore();
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasRestoreToCount
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasRestoreToCount
  (JNIEnv *env, jclass cls, jlong nativeObj, jint count)
{
   AsCanvas(nativeObj)->restoreToCount(count);
}


//-----------------------------------------------------------------------------


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasTranslate
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasTranslate
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat dx, jfloat dy)
{
	AsCanvas(nativeObj)->translate(dx, dy);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasScale
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasScale
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat sx, jfloat sy)
{
	AsCanvas(nativeObj)->scale(sx, sy);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasRotateDegrees
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasRotate
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat degrees)
{
   AsCanvas(nativeObj)->rotate(degrees);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasRotate
 * Signature: (JFFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasRotate__JFFF
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat degrees, jfloat px, jfloat py)
{
   AsCanvas(nativeObj)->rotate(degrees, px, py);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasSkew
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasSkew
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat sx, jfloat sy)
{
	AsCanvas(nativeObj)->skew(sx, sy);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasConcat
 * Signature: (JFFFFFFFFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasConcat
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat m0, jfloat m1, jfloat m2, jfloat m3, jfloat m4, jfloat m5, jfloat m6, jfloat m7, jfloat m8)
{
   const SkMatrix  m = SkMatrix::MakeAll(m0, m1, m2, m3, m4, m5, m6, m7, m8);
   AsCanvas(nativeObj)->concat(m);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawPaint
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawPaint
  (JNIEnv *env, jclass cls, jlong nativeObj, jlong nativePaintObj)
{
   SkPaint*  paint = AsPaint(nativePaintObj);
   AsCanvas(nativeObj)->drawPaint(*paint);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawRect
 * Signature: (JFFFFJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawRect
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jlong nativePaintObj)
{
   SkPaint*  paint = AsPaint(nativePaintObj);
   SkRect    rect{ left, top, right, bottom };
   AsCanvas(nativeObj)->drawRect(rect, *paint);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawCircle
 * Signature: (JFFFJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawCircle
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat cx, jfloat cy, jfloat radius, jlong nativePaintObj)
{
   SkPaint*  paint = AsPaint(nativePaintObj);
   AsCanvas(nativeObj)->drawCircle(cx, cy, radius, *paint);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawOval
 * Signature: (JFFFFJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawOval
(JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jlong nativePaintObj)
{
   SkPaint*  paint = AsPaint(nativePaintObj);
   SkRect    rect{ left, top, right, bottom };
   AsCanvas(nativeObj)->drawOval(rect, *paint);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawArc
 * Signature: (JFFFFFFZJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawArc
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jfloat startAngle, jfloat sweepAngle, jboolean useCenter, jlong nativePaintObj)
{
   SkPaint*  paint = AsPaint(nativePaintObj);
   SkRect    rect{ left, top, right, bottom };
   AsCanvas(nativeObj)->drawArc(rect, startAngle, sweepAngle, useCenter, *paint);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawLine
 * Signature: (JFFFFJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawLine
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat x0, jfloat y0, jfloat x1, jfloat y1, jlong nativePaintObj)
{
   SkPaint*  paint = AsPaint(nativePaintObj);
   AsCanvas(nativeObj)->drawLine(x0, y0, x1, y1, *paint);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawPoint
 * Signature: (JFFJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawPoint
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat x, jfloat y, jlong nativePaintObj)
{
   SkPaint*  paint = AsPaint(nativePaintObj);
   AsCanvas(nativeObj)->drawPoint(x, y, *paint);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawPoints
 * Signature: (JII[FJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawPoints
  (JNIEnv *env, jclass cls, jlong nativeObj, jint pointMode, jint count, jfloatArray pts, jlong nativePaintObj)
{
   SkPaint*  paint = AsPaint(nativePaintObj);

   // Get a pointer to the byte data and pin the buffer in the VM so it is not GC'd
	jfloat*  fptr = env->GetFloatArrayElements(pts, NULL);
	if (fptr == NULL)
	  return;  // return without doing anything

	// Create the SkPoint array that the next call needs
	SkPoint*  skpts = (SkPoint*) malloc(count * sizeof(SkPoint));
	jfloat*   from = fptr;
	SkPoint*  to = skpts;
	for (int i = 0; i < count; i++) {
		to->set(from[0], from[1]);
		to++;
		from += 2;
	}

   // Un-pin the float array memory
   env->ReleaseFloatArrayElements(pts, fptr, JNI_ABORT);

   // call drawPoints()
   AsCanvas(nativeObj)->drawPoints(static_cast<SkCanvas::PointMode>(pointMode), count, skpts, *paint);

   // Free our temp SkPoints array buffer
   free(skpts);
}


/*
 * Class:     com_caverock_skia4j_SkCanvas
 * Method:    nSkCanvasDrawImage
 * Signature: (JJFFJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkCanvas_nSkCanvasDrawImage
  (JNIEnv *env, jclass cls, jlong nativeObj, jlong imageNativeObj, jfloat left, jfloat top, jlong nativePaintObj)
{
	SkImage*  image = AsImage(imageNativeObj);
   SkPaint*  paint = AsPaint(nativePaintObj);
   AsCanvas(nativeObj)->drawImage(image, left, top, paint);
}

