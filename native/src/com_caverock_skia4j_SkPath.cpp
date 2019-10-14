#include <jni.h>        // JNI header provided by JDK

#include "include/com_caverock_skia4j_SkPath.h"

#include "include/core/SkPath.h"
#include "types.h"


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nNew
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkPath_nNew__
  (JNIEnv *env, jclass cls)
{
   SkPath*  path = new SkPath;
   return (path != NULL) ? ToPath(path)
                         : 0;
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nNew
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_caverock_skia4j_SkPath_nNew__J
  (JNIEnv *env, jclass cls, jlong otherNativeObj)
{
   SkPath*  other = AsPath(otherNativeObj);
   SkPath*  path = new SkPath(*other);
   return (path != NULL) ? ToPath(path)
                          : 0;
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nUnref
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nUnref
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   delete AsPath(nativeObj);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nEquals
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkPath_nEquals
  (JNIEnv *env, jclass cls, jlong nativeObj, jlong otherRef)
{
   SkPath*  path1 = AsPath(nativeObj);
   SkPath*  path2 = AsPath(otherRef);
   return *path1 == *path2;
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nIsInterpolatable
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkPath_nIsInterpolatable
  (JNIEnv *env, jclass cls, jlong nativeObj, jlong compareRef)
{
   SkPath*  compare = AsPath(compareRef);
   return AsPath(nativeObj)->isInterpolatable(*compare);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nInterpolate
 * Signature: (JJFJ)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkPath_nInterpolate
  (JNIEnv *env, jclass cls, jlong nativeObj, jlong endingRef, jfloat weight, jlong outRef)
{
   SkPath*  ending = AsPath(endingRef);
   SkPath*  out = AsPath(outRef);
   return AsPath(nativeObj)->interpolate(*ending, weight, out);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nGetFillType
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkPath_nGetFillType
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsPath(nativeObj)->getFillType();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nSetFillType
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nSetFillType
  (JNIEnv *env, jclass cls, jlong nativeObj, jint fillType)
{
   AsPath(nativeObj)->setFillType( static_cast<SkPath::FillType>(fillType) );
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nIsInverseFillType
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkPath_nIsInverseFillType
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsPath(nativeObj)->isInverseFillType();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nToggleInverseFillType
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nToggleInverseFillType
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   AsPath(nativeObj)->toggleInverseFillType();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nGetConvexity
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkPath_nGetConvexity
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsPath(nativeObj)->getConvexity();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nSetConvexity
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nSetConvexity
  (JNIEnv *env, jclass cls, jlong nativeObj, jint convexity)
{
   AsPath(nativeObj)->setConvexity( static_cast<SkPath::Convexity>(convexity) );
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nIsConvex
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkPath_nIsConvex
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsPath(nativeObj)->isConvex();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nReset
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nReset
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   AsPath(nativeObj)->reset();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nRewind
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nRewind
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   AsPath(nativeObj)->rewind();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nIsEmpty
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkPath_nIsEmpty
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsPath(nativeObj)->isEmpty();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nLastContourClosed
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkPath_nIsLastContourClosed
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsPath(nativeObj)->isLastContourClosed();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nIsFinite
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkPath_nIsFinite
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsPath(nativeObj)->isFinite();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nIsVolatile
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkPath_nIsVolatile
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsPath(nativeObj)->isVolatile();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nSetIsVolatile
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nSetIsVolatile
  (JNIEnv *env, jclass cls, jlong nativeObj, jboolean isVolatile)
{
   AsPath(nativeObj)->setIsVolatile(isVolatile);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nCountPoints
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkPath_nCountPoints
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsPath(nativeObj)->countPoints();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nGetPoint
 * Signature: (JI[F)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nGetPoint
  (JNIEnv *env, jclass cls, jlong nativeObj, jint index, jfloatArray pt)
{
   // Get a pointer to the float array data, and pin it
   jfloat *point = env->GetFloatArrayElements(pt, 0);
   SkPoint  skpt = AsPath(nativeObj)->getPoint(index);
   point[0] = skpt.x();
   point[1] = skpt.y();
   // Release the pinned memory after copying the read data back
   env->ReleaseFloatArrayElements(pt, point, 0);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nGetPoints
 * Signature: (J[FI)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkPath_nGetPoints
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloatArray pts, jint max)
{
   // Get a pointer to the float array data, and pin it
	// It should have a length twice that of <max>
   jfloat  *points = env->GetFloatArrayElements(pts, 0);
   // Safety check that the size of SkPoint is equivalent to two floats. Ie. Skia hasn't changed the size on us.
   if (sizeof(SkPoint) != (sizeof(jfloat) * 2))
   	return -1;
   int count = AsPath(nativeObj)->getPoints((SkPoint *)points, max);
   // Release the pinned memory after copying the read data back
   env->ReleaseFloatArrayElements(pts, points, 0);
   return count;
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nCountVerbs
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkPath_nCountVerbs
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsPath(nativeObj)->countVerbs();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nGetVerbs
 * Signature: (J[BI)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkPath_nGetVerbs
  (JNIEnv *env, jclass cls, jlong nativeObj, jbyteArray verbs, jint max)
{
   // Get a pointer to the byte array data, and pin it
   jbyte  *vbs = env->GetByteArrayElements(verbs, 0);
   int count = AsPath(nativeObj)->getVerbs((uint8_t *)vbs, max);
   // Release the pinned memory after copying the read data back
   env->ReleaseByteArrayElements(verbs, vbs, 0);
   return count;
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nSwap
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nSwap
  (JNIEnv *env, jclass cls, jlong nativeObj, jlong otherRef)
{
   SkPath*  other = AsPath(otherRef);
   AsPath(nativeObj)->swap(*other);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nGetBounds
 * Signature: (J[F)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nGetBounds
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloatArray bounds)
{
   // Get a pointer to the float array data, and pin it
   jfloat *fptr = env->GetFloatArrayElements(bounds, 0);
   SkRect  rect = AsPath(nativeObj)->getBounds();
   fptr[0] = rect.left();
   fptr[1] = rect.top();
   fptr[2] = rect.right();
   fptr[3] = rect.bottom();
   // Release the pinned memory after copying the read data back
   env->ReleaseFloatArrayElements(bounds, fptr, 0);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nUpdateBoundsCache
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nUpdateBoundsCache
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   AsPath(nativeObj)->updateBoundsCache();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nComputeTightBounds
 * Signature: (J[F)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nComputeTightBounds
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloatArray bounds)
{
   // Get a pointer to the float array data, and pin it
   jfloat *fptr = env->GetFloatArrayElements(bounds, 0);
   SkRect  rect = AsPath(nativeObj)->computeTightBounds();
   fptr[0] = rect.left();
   fptr[1] = rect.top();
   fptr[2] = rect.right();
   fptr[3] = rect.bottom();
   // Release the pinned memory after copying the read data back
   env->ReleaseFloatArrayElements(bounds, fptr, 0);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nConservativelyContainsRect
 * Signature: (JFFFF)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkPath_nConservativelyContainsRect
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom)
{
   SkRect  rect { left, top, right, bottom };
   return AsPath(nativeObj)->conservativelyContainsRect(rect);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nIncReserve
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nIncReserve
  (JNIEnv *env, jclass cls, jlong nativeObj, jint extraPtCount)
{
   AsPath(nativeObj)->incReserve(extraPtCount);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nMoveTo
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nMoveTo
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat x, jfloat y)
{
   AsPath(nativeObj)->moveTo(x, y);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nRMoveTo
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nRMoveTo
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat dx, jfloat dy)
{
   AsPath(nativeObj)->rMoveTo(dx, dy);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nLineTo
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nLineTo
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat x, jfloat y)
{
   AsPath(nativeObj)->lineTo(x, y);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nRLineTo
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nRLineTo
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat dx, jfloat dy)
{
   AsPath(nativeObj)->rLineTo(dx, dy);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nQuadTo
 * Signature: (JFFFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nQuadTo
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat x1, jfloat y1, jfloat x2, jfloat y2)
{
   AsPath(nativeObj)->quadTo(x1, y1, x2, y2);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nRQuadTo
 * Signature: (JFFFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nRQuadTo
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat dx1, jfloat dy1, jfloat dx2, jfloat dy2)
{
   AsPath(nativeObj)->rQuadTo(dx1, dy1, dx2, dy2);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nConicTo
 * Signature: (JFFFFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nConicTo
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat x1, jfloat y1, jfloat x2, jfloat y2, jfloat weight)
{
   AsPath(nativeObj)->conicTo(x1, y1, x2, y2, weight);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nRConicTo
 * Signature: (JFFFFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nRConicTo
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat dx1, jfloat dy1, jfloat dx2, jfloat dy2, jfloat weight)
{
   AsPath(nativeObj)->rConicTo(dx1, dy1, dx2, dy2, weight);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nCubicTo
 * Signature: (JFFFFFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nCubicTo
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat x1, jfloat y1, jfloat x2, jfloat y2, jfloat x3, jfloat y3)
{
   AsPath(nativeObj)->cubicTo(x1, y1, x2, y2, x3, y3);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nRCubicTo
 * Signature: (JFFFFFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nRCubicTo
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat dx1, jfloat dy1, jfloat dx2, jfloat dy2, jfloat dx3, jfloat dy3)
{
   AsPath(nativeObj)->rCubicTo(dx1, dy1, dx2, dy2, dx3, dy3);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nArcTo
 * Signature: (JFFFFFFZ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nArcTo__JFFFFFFZ
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jfloat startAngle, jfloat sweepAngle, jboolean forceMoveTo)
{
   SkRect  rect { left, top, right, bottom };
   AsPath(nativeObj)->arcTo(rect, startAngle, sweepAngle, forceMoveTo);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nArcTo
 * Signature: (JFFFFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nArcTo__JFFFFF
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat x1, jfloat y1, jfloat x2, jfloat y2, jfloat radius)
{
   AsPath(nativeObj)->arcTo(x1, y1, x2, y2, radius);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nArcTo
 * Signature: (JFFFIIFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nArcTo__JFFFIIFF
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat rx, jfloat ry, jfloat xAxisRotate, jint largeArc, jint sweep, jfloat x, jfloat y)
{
   AsPath(nativeObj)->arcTo(rx, ry, xAxisRotate, static_cast<SkPath::ArcSize>(largeArc), static_cast<SkPath::Direction>(sweep), x, y);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nRArcTo
 * Signature: (JFFFIIFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nRArcTo
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat rx, jfloat ry, jfloat xAxisRotate, jint largeArc, jint sweep, jfloat dx, jfloat dy)
{
   AsPath(nativeObj)->rArcTo(rx, ry, xAxisRotate, static_cast<SkPath::ArcSize>(largeArc), static_cast<SkPath::Direction>(sweep), dx, dy);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nClose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nClose
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   AsPath(nativeObj)->close();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nAddRect
 * Signature: (JFFFFII)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nAddRect
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jint dir, jint start)
{
   SkRect  rect { left, top, right, bottom };
   AsPath(nativeObj)->addRect(rect, static_cast<SkPath::Direction>(dir), start);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nAddOval
 * Signature: (JFFFFII)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nAddOval
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jint dir, jint start)
{
   SkRect  oval { left, top, right, bottom };
   AsPath(nativeObj)->addOval(oval, static_cast<SkPath::Direction>(dir), start);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nAddCircle
 * Signature: (JFFFI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nAddCircle
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat x, jfloat y, jfloat radius, jint dir)
{
   AsPath(nativeObj)->addCircle(x, y, radius, static_cast<SkPath::Direction>(dir));
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nAddArc
 * Signature: (JFFFFFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nAddArc
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jfloat startAngle, jfloat sweepAngle)
{
   SkRect  oval { left, top, right, bottom };
   AsPath(nativeObj)->addArc(oval, startAngle, sweepAngle);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nAddRoundRect
 * Signature: (JFFFFFFI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nAddRoundRect__JFFFFFFI
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jfloat rx, jfloat ry, jint dir)
{
   SkRect  rect { left, top, right, bottom };
   AsPath(nativeObj)->addRoundRect(rect, rx, ry, static_cast<SkPath::Direction>(dir));
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nAddRoundRect
 * Signature: (JFFFF[FI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nAddRoundRect__JFFFF_3FI
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat left, jfloat top, jfloat right, jfloat bottom, jfloatArray radii, jint dir)
{
   SkRect  rect { left, top, right, bottom };
   // Get a pointer to the float array data, and pin it
   jfloat *rads = env->GetFloatArrayElements(radii, 0);
   AsPath(nativeObj)->addRoundRect(rect, rads, static_cast<SkPath::Direction>(dir));
   // Unpin and release array data
   env->ReleaseFloatArrayElements(radii, rads, JNI_ABORT);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nAddPath
 * Signature: (JJI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nAddPath__JJI
  (JNIEnv *env, jclass cls, jlong nativeObj, jlong srcRef, jint mode)
{
   SkPath  *src = AsPath(srcRef);
   AsPath(nativeObj)->addPath(*src, static_cast<SkPath::AddPathMode>(mode));
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nAddPath
 * Signature: (JJFFI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nAddPath__JJFFI
  (JNIEnv *env, jclass cls, jlong nativeObj, jlong srcRef, jfloat dx, jfloat dy, jint mode)
{
   SkPath  *src = AsPath(srcRef);
   AsPath(nativeObj)->addPath(*src, dx, dy, static_cast<SkPath::AddPathMode>(mode));
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nAddPath
 * Signature: (JJFFFFFFFFFI)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nAddPath__JJFFFFFFFFFI
  (JNIEnv *env, jclass cls, jlong nativeObj, jlong srcRef, jfloat m0, jfloat m1, jfloat m2, jfloat m3, jfloat m4, jfloat m5, jfloat m6, jfloat m7, jfloat m8, jint mode)
{
   SkPath  *src = AsPath(srcRef);
   const SkMatrix  m = SkMatrix::MakeAll(m0, m1, m2, m3, m4, m5, m6, m7, m8);
   AsPath(nativeObj)->addPath(*src, m, static_cast<SkPath::AddPathMode>(mode));
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nReverseAddPath
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nReverseAddPath
  (JNIEnv *env, jclass cls, jlong nativeObj, jlong srcRef)
{
   SkPath  *src = AsPath(srcRef);
   AsPath(nativeObj)->reverseAddPath(*src);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nOffset
 * Signature: (JFFJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nOffset__JFFJ
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat dx, jfloat dy, jlong dstRef)
{
   SkPath  *dst = AsPath(dstRef);
   AsPath(nativeObj)->offset(dx, dy, dst);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nOffset
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nOffset__JFF
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat dx, jfloat dy)
{
   AsPath(nativeObj)->offset(dx, dy);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nTransform
 * Signature: (JFFFFFFFFFJ)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nTransform__JFFFFFFFFFJ
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat m0, jfloat m1, jfloat m2, jfloat m3, jfloat m4, jfloat m5, jfloat m6, jfloat m7, jfloat m8, jlong dstRef)
{
   SkPath  *dst = AsPath(dstRef);
   const SkMatrix  m = SkMatrix::MakeAll(m0, m1, m2, m3, m4, m5, m6, m7, m8);
   AsPath(nativeObj)->transform(m, dst);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nTransform
 * Signature: (JFFFFFFFFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nTransform__JFFFFFFFFF
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat m0, jfloat m1, jfloat m2, jfloat m3, jfloat m4, jfloat m5, jfloat m6, jfloat m7, jfloat m8)
{
   const SkMatrix  m = SkMatrix::MakeAll(m0, m1, m2, m3, m4, m5, m6, m7, m8);
   AsPath(nativeObj)->transform(m);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nSetLastPt
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_caverock_skia4j_SkPath_nSetLastPt
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat x, jfloat y)
{
   AsPath(nativeObj)->setLastPt(x, y);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nGetSegmentMasks
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkPath_nGetSegmentMasks
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
   return AsPath(nativeObj)->getSegmentMasks();
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nContains
 * Signature: (JFF)Z
 */
JNIEXPORT jboolean JNICALL Java_com_caverock_skia4j_SkPath_nContains
  (JNIEnv *env, jclass cls, jlong nativeObj, jfloat x, jfloat y)
{
   return AsPath(nativeObj)->contains(x, y);
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nWriteToMemory
 * Signature: (J[B)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkPath_nWriteToMemory
  (JNIEnv *env, jclass cls, jlong nativeObj, jbyteArray buffer)
{
	jbyte*  bptr = env->GetByteArrayElements(buffer, NULL);
   int result = AsPath(nativeObj)->writeToMemory(bptr);
   env->ReleaseByteArrayElements(buffer, bptr, JNI_ABORT);
   return result;
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nReadFromMemory
 * Signature: (J[BI)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkPath_nReadFromMemory
  (JNIEnv *env, jclass cls, jlong nativeObj, jbyteArray buffer, jint length)
{
	jbyte*  bptr = env->GetByteArrayElements(buffer, NULL);
	if (bptr == NULL)
	  return 0;
   int result = AsPath(nativeObj)->readFromMemory(bptr, length);
   // Release the pinned memory after copying the read data back
   env->ReleaseByteArrayElements(buffer, bptr, 0);
   return result;
}


/*
 * Class:     com_caverock_skia4j_SkPath
 * Method:    nGetGenerationID
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_caverock_skia4j_SkPath_nGetGenerationID
  (JNIEnv *env, jclass cls, jlong nativeObj)
{
  return AsPath(nativeObj)->getGenerationID();
}



