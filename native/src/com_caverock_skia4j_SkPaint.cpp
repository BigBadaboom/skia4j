#include <jni.h>        // JNI header provided by JDK

#include "include/com_caverock_skia4j_SkPaint.h"

#include "include/core/SkPaint.h"
#include "types.h"


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintNew
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintNew
  (JNIEnv *env, jclass cls)
{
	SkPaint*  paint = new SkPaint;
   return (paint != NULL) ? ToPaint(paint)
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
   delete AsPaint(nativeObj);
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintSetAntialias
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintSetAntialias
  (JNIEnv *env, jclass cls, jlong nativeObj, jboolean isAntialias)
{
	AsPaint(nativeObj)->setAntiAlias(isAntialias);
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintSetColor
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintSetColor
  (JNIEnv *env, jclass cls, jlong nativeObj, jint color)
{
	AsPaint(nativeObj)->setColor(color);
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintSetDither
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintSetDither
  (JNIEnv *env, jclass cls, jlong nativeObj, jboolean isDither)
{
	AsPaint(nativeObj)->setDither(isDither);
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintSetStrokeWidth
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintSetStrokeWidth
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat strokeWidth)
{
	AsPaint(nativeObj)->setStrokeWidth(strokeWidth);
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintSetStrokeMiter
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintSetStrokeMiter
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat miterLimit)
{
	AsPaint(nativeObj)->setStrokeMiter(miterLimit);
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintSetStrokeCap
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintSetStrokeCap
  (JNIEnv *env, jclass cls, jlong nativeObj, jint strokeCap)
{
	AsPaint(nativeObj)->setStrokeCap( static_cast<SkPaint::Cap>(strokeCap) );
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintSetStrokeJoin
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintSetStrokeJoin
  (JNIEnv *env, jclass cls, jlong nativeObj, jint strokeJoin)
{
	AsPaint(nativeObj)->setStrokeJoin( static_cast<SkPaint::Join>(strokeJoin) );
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintSetFilterQuality
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintSetFilterQuality
  (JNIEnv *env, jclass cls, jlong nativeObj, jint filterQuality)
{
	AsPaint(nativeObj)->setFilterQuality( static_cast<SkFilterQuality>(filterQuality) );
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintSetStyle
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintSetStyle
  (JNIEnv *env, jclass cls, jlong nativeObj, jint style)
{
	AsPaint(nativeObj)->setStyle( static_cast<SkPaint::Style>(style) );
}


/*
 * Class:     com_caverock_skia4j_SkPaint
 * Method:    nSkPaintSetBlendMode
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPaint_nSkPaintSetBlendMode
  (JNIEnv *env, jclass cls, jlong nativeObj, jint blendMode)
{
	AsPaint(nativeObj)->setBlendMode( static_cast<SkBlendMode>(blendMode) );
}

