package com.caverock.skia4j;

import java.io.OutputStream;

/**
 * <p>The SkPath class encapsulates compound (multiple contour) geometric paths consisting of straight line segments, quadratic curves, and cubic curves.</p>
 *
 * <p>SkPath is not thread safe unless you've first called {@link #updateBoundsCache()}.</p>
 *
 */

public class SkPath
{
   // Reference to the native sk_canvas object
   private long  nRef = 0;



   public enum Direction {
      kCW,
      kCCW
   }
   
   public enum FillType {
      kWinding,
      kEvenOdd,
      kInverseWinding,
      kInverseEvenOdd
   }
    
   public enum Convexity {
      kUnknown,
      kConvex,
      kConcave
   }
    
   public enum ArcSize {
      kSmall,
      kLarge
   }
    
   public enum AddPathMode {
      kAppend,
      kExtend
   }
    
   public static final int  kLine_SegmentMask = 1 << 0;
   public static final int  kQuad_SegmentMask = 1 << 1;
   public static final int  kConic_SegmentMask = 1 << 2;
   public static final int  kCubic_SegmentMask = 1 << 3;
    
   public enum Verb
   {
     kMove,
     kLine,
     kQuad,
     kConic,
     kCubic,
     kClose,
     kDone
   }



   /**
    * Create an empty path.
    */
   public SkPath()
   {
      this.nRef = nNew();
   }


   /**
    * Create a new path, copying the contents from the {@code src} path.
    */
   public SkPath(SkPath src)
   {
      this.nRef = nNew(src.nativeRef());
   }



   //--------------------------------------------------------------------------


   /**
    * Tidy up the native resource associated with this class.
    * Must be called, when this object is no longer needed, to avoid memory leaks.
    *
    * SkPath already has a close() method, so this class cannot support try-with-resources. 
    */
   public void  release() throws Exception
   {
      nUnref(nativeRef());
      nRef = 0;
   }


   protected long  nativeRef()
   {
      if (nRef == 0)
         throw new IllegalArgumentException("This SkPath object has already been released.");
      return nRef;
   }



   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (o == null)
         return false;
      if (getClass() != o.getClass())
         return false;
      SkPath other = (SkPath) o;
      return nEquals(nRef, other.nativeRef());
   }


   @Override
   public String toString()
   {
      return String.format("%s (ref:%x)", getClass().getSimpleName(), nRef);
   }


   //--------------------------------------------------------------------------


   /**
    * Return true if the paths contain an equal array of verbs and weights.
    * Paths with equal verb counts can be readily interpolated. If the paths contain one or more conics, the conics' weights must also match.
    */
   public boolean  isInterpolatable(SkPath compare)
   {
      if (compare == null)
         return false;
      return nIsInterpolatable(nRef, compare.nativeRef());
   }


   /**
    * Interpolate between two paths with same-sized point arrays.
    * The out path contains the verbs and weights of this path. The out points are a weighted average of this path and the ending path.
    * @return true if the paths could be interpolated
    */
   public boolean  interpolate(SkPath ending, float weight, SkPath out)
   {
      if (ending == null || out == null)
         return false;
      return nInterpolate(nRef, ending.nativeRef(), weight, out.nativeRef());
   }


   /**
    * Return the path's fill type.
    * This is used to define how "inside" is computed. The default value is kWinding.
    */
   public FillType  getFillType()
   {
      return FillType.values()[nGetFillType(nRef)];
   }

   /**
    * Set the path's fill type.
    * This is used to define how "inside" is computed. The default value is kWinding.
    */
   public void  setFillType(FillType ft)
   {
      nSetFillType(nRef, ft.ordinal());
   }


   /**
    * Returns true if the fill type is one of the Inverse variants.
    */
   public boolean  isInverseFillType()
   {
      return nIsInverseFillType(nRef);
   }


   /**
    * Toggle between inverse and normal fill types.
    */
   public void  toggleInverseFillType()
   {
      nToggleInverseFillType(nRef);
   }


   /**
    * Return the path's convexity, as stored in the path.
    *
    * If it is currently unknown, then this function will attempt to compute the convexity (and cache the result).
    */
   public Convexity  getConvexity()
   {
      return Convexity.values()[nGetConvexity(nRef)];
   }

   /**
    * Return the currently cached value for convexity, even if that is set to kUnknown_Convexity.
    *
    * Note: getConvexity() will automatically compute the convexity and cache its return value if the current setting is kUnknown.
    */
   public Convexity  getConvexityOrUnknown()
   {
      return Convexity.values()[nGetConvexity(nRef)];
   }

   /**
    * Store a convexity setting in the path.
    *
    * There is no automatic check to see if this value actually agrees with the return value
    * that would be computed by getConvexity().
    *
    * Note: even if this is set to a "known" value, if the path is later changed (e.g. lineTo(),
    * addRect(), etc.) then the cached value will be reset to kUnknown_Convexity.
    */
   public void  setConvexity(Convexity convexity)
   {
      nSetConvexity(nRef, convexity.ordinal());
   }


   /**
    * Computes convexity if required, and returns true if value is kConvex.
    * If setConvexity() was called with kConvex or kConcave, and
    * the path has not been altered, convexity is not recomputed.
    */
   public boolean  isConvex()
   {
      return nIsConvex(nRef);
   }


   /**
    * Returns true if the path is an oval.
    */
/*
   public boolean  isOval(SkRect *rect, Direction *dir=nullptr, *start=nullptr)
   {
      
   }
*/


   /**
    * Returns true if the path is a round rect.
    */
/*
   public boolean  isRRect(SkRRect *rrect, Direction *dir=nullptr, *start=nullptr)
   {
      
   }
*/


   /**
    * Returns true if the path specifies a rectangle.
    */
/*
   public boolean  isRect(SkRect *rect, boolean *isClosed=NULL, Direction *direction=NULL)
   {
      
   }
*/


   /**
    * Returns true if the path specifies a pair of nested rectangles, or would draw a pair of nested rectangles when filled. 
    */
/*
   public boolean  isNestedFillRects(SkRect rect[2], Direction dirs[2]=NULL)
   {
      
   }
*/


   /**
    * Clear any lines and curves from the path, making it empty.
    * 
    * This frees up internal storage associated with those segments.
    */
   public void  reset()
   {
      nReset(nRef);
   }


   /**
    * Similar to {@link #reset()}, in that all lines and curves are removed from the path.
    * 
    * However, any internal storage for those lines/curves is retained, making reuse of the path potentially faster.
    */
   public void  rewind()
   {
      nRewind(nRef);
   }


   /**
    * Returns true if the path is empty (contains no lines or curves)
    */
   public boolean  isEmpty()
   {
      return nIsEmpty(nRef);
   }


   /**
    * Return true if the last contour of this path ends with a close verb.
    */
   public boolean  isLastContourClosed()
   {
      return nIsLastContourClosed(nRef);
   }


   /**
    * Returns true if all of the points in this path are finite, meaning there are no infinities and no NaNs.
    */
   public boolean  isFinite()
   {
      return nIsFinite(nRef);
   }


   /**
    * Returns true if the path is volatile (i.e. should not be cached by devices.)
    */
   public boolean  isVolatile()
   {
      return nIsVolatile(nRef);
   }


   /**
    * Specify whether this path is volatile.
    *
    * Paths are not volatile by default. Temporary paths that are discarded or modified after use should be
    * marked as volatile. This provides a hint to the device that the path should not be cached. Providing
    * this hint when appropriate can improve performance by avoiding unnecessary overhead and resource
    * consumption on the device.
    */
   public void  setIsVolatile(boolean isVolatile)
   {
      nSetIsVolatile(nRef, isVolatile);
   }


   /**
    * Returns true if the path specifies a single line (i.e. it contains just a moveTo and a lineTo). If so,
    * and line[] is not null, it sets the 2 points in line[] to the end-points of the line. If the path is
    * not a line, returns false and ignores line[].
    */
/*
   public boolean  isLine(SkPoint line[2])
   {
      
   }
*/
   

   /**
    * Return the number of points in the path.
    */
   public int  countPoints()
   {
      return nCountPoints(nRef);
   }
   

   /**
    * Return the point at the specified index.
    *
    * If the index is out of range (i.e. is not 0 <= index < countPoints()) then the
    * returned coordinates will be (0,0)
    */
   public SkPoint  getPoint(int index)
   {
      float[]  pt = new float[2];
      nGetPoint(nRef, index, pt);
      return new SkPoint(pt[0], pt[1]);
   }


   /**
    * Returns the number of points in the path.  Up to max points are copied.
    * 
    * @param points If not null, receives up to max points
    * @param max The maximum number of points to copy into points
    * @return the actual number of points in the path
    */
   public int  getPoints(SkPoint points[], int max)
   {
      float[]  pts = new float[max * 2];
      int count = nGetPoints(nRef, pts, max);
      int p = 0;
      for (int i=0; i<count; i++) {
        points[i] = new SkPoint(pts[p], pts[p+1]);
        p += 2;
      }
      return count;      
   }


   /**
    * Return the number of verbs in the path.
    */
   public int  countVerbs()
   {
      return nCountVerbs(nRef);
   }


   /**
    * Returns the number of verbs in the path.
    *
    * Up to max verbs are copied. The verbs are copied as one byte per verb.
    * 
    * @param verbs If not null, receives up to max verbs
    * @param max The maximum number of verbs to copy into verbs
    * @return the actual number of verbs in the path
    */
   public int  getVerbs(byte[] verbs, int max)
   {
      return nGetVerbs(nRef, verbs, max);
   }


   /**
    * Swap contents of this and other.
    */
   public void  swap(SkPath other)
   {
      nSwap(nRef, other.nativeRef());
   }


   /**
    * Returns the bounds of the path's points.
    *
    * If the path contains zero points/verbs, this will return the "empty" rect [0, 0, 0, 0]. Note: this bounds may
    * be larger than the actual shape, since curves do not extend as far as their control points. Additionally this
    * bound encompasses all points, even isolated moveTos either preceding or following the last non-degenerate contour.
    */
   public SkRect  getBounds()
   {
      float[]  bounds = new float[4];
      nGetBounds(nRef, bounds);
      return SkRect.makeLTRB(bounds[0], bounds[1], bounds[2], bounds[3]);
   }


   /**
    * Calling this will, if the internal cache of the bounds is out of date, update it so that subsequent calls to
    * getBounds will be instantaneous.
    *
    * This also means that any copies or simple transformations of the path will inherit the cached bounds.
    */
   public void  updateBoundsCache()
   {
      nUpdateBoundsCache(nRef);
   }


   /**
    * Computes a bounds that is conservatively "snug" around the path.
    *
    * This assumes that the path will be filled. It does not attempt to collapse away contours that are
    * logically empty (e.g. moveTo(x, y) + lineTo(x, y)) but will include them in the calculation.
    *
    * It differs from {@link #getBounds()} in that it will look at the snug bounds of curves, whereas
    * {@code getBounds()} just returns the bounds of the control-points. Thus computing this may be slower
    * than just calling {@code getBounds()}.
    *
    * If the path is empty (i.e. no points or verbs), it will return {@code SkRect.makeEmpty()}.
    * @return
    */
   public SkRect  computeTightBounds()
   {
      float[]  bounds = new float[4];
      nComputeTightBounds(nRef, bounds);
      return SkRect.makeLTRB(bounds[0], bounds[1], bounds[2], bounds[3]);
   }


   /**
    * Does a conservative test to see whether a rectangle is inside a path.
    * 
    * Currently it only will ever return true for single convex contour paths. The empty-status of the rect
    * is not considered (e.g. a rect that is a point can be inside a path). Points or line segments where the
    * rect edge touches the path border are not considered containment violations.
    */
   public boolean  conservativelyContainsRect(SkRect rect)
   {
      return nConservativelyContainsRect(nRef, rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom());
   }


   /**
    * Hint to the path to prepare for adding more points.
    *
    * This can allow the path to more efficiently grow its storage.
    */
   public void  incReserve(int extraPtCount)
   {
      nIncReserve(nRef, extraPtCount);
   }


   /**
    * Set the beginning of the next contour to the point (x,y).
    * 
    * @param x The X coordinate of the start of a new contour
    * @param y The Y coordinate of the start of a new contour
    */
   public void  moveTo(float x, float y)
   {
      nMoveTo(nRef, x, y);
   }


   /**
    * Set the beginning of the next contour to the point.
    * 
    * @param p The start of a new contour
    */
/*
   public void  moveTo(SkPoint p)
   {
      nMoveTo(nRef, p.getX(), p.getY());
   }
*/


   /**
    * Set the beginning of the next contour relative to the last point on the previous contour.
    * 
    * @param dx The amount to add to the X coordinate of the end of the previous contour, to specify the start of a new contour
    * @param dy The amount to add to the Y coordinate of the end of the previous contour, to specify the start of a new contour
    */
   public void  rMoveTo(float dx, float dy)
   {
      nRMoveTo(nRef, dx, dy);
   }


   /**
    * Add a line from the last point to the specified point (x,y).
    * 
    * If no moveTo() call has been made for this contour, the first point is automatically set to (0,0).
    * 
    * @param x The X coordinate of the end of a line
    * @param y The Y coordinate of the end of a line
    */
   public void  lineTo(float x, float y)
   {
      nLineTo(nRef, x, y);
   }


   /**
    * Add a line from the last point to the specified point (x,y).\
    * 
    * If no moveTo() call has been made for this contour, the first point is automatically set to (0,0).
    *  
    * @param p The end of a line
    */
/*
   public void  lineTo(SkPoint p)
   {
      nLineTo(nRef, p.getX(), p.getY());
   }
*/


   /**
    * Same as lineTo, but the coordinates are considered relative to the last point on this contour.
    * 
    * If there is no previous point, then a moveTo(0,0) is inserted automatically.
    * 
    * @param dx The amount to add to the X coordinate of the previous point on this contour, to specify a line
    * @param dy The amount to add to the Y coordinate of the previous point on this contour, to specify a line
    */
   public void  rLineTo(float dx, float dy)
   {
      nRLineTo(nRef, dx, dy);
   }


   /**
    * Add a quadratic bezier from the last point, approaching control point (x1,y1), and ending at (x2,y2).
    * 
    * If no moveTo() call has been made for this contour, the first point is automatically set to (0,0).
    * 
    * @param x1 The X coordinate of the control point on a quadratic curve
    * @param y1 The Y coordinate of the control point on a quadratic curve
    * @param x2 The X coordinate of the end point on a quadratic curve
    * @param y2 The Y coordinate of the end point on a quadratic curve
    */
   public void  quadTo(float x1, float y1, float x2, float y2)
   {
      nQuadTo(nRef, x1, y1, x2, y2);
   }


   /**
    * Add a quadratic bezier from the last point, approaching control point (x1,y1), and ending at (x2,y2).
    * 
    * If no moveTo() call has been made for this contour, the first point is automatically set to (0,0).
    * 
    * @param p1 The control point on a quadratic curve
    * @param p2 The end point on a quadratic curve
    */
/*
   public void  quadTo(SkPoint p1, SkPoint p2)
   {
      
   }
*/


   /**
    * Same as quadTo, but the coordinates are considered relative to the last point on this contour.
    * 
    * If there is no previous point, then a moveTo(0,0) is inserted automatically.
    * 
    * @param dx1 The amount to add to the X coordinate of the last point on this contour, to specify the control point of a quadratic curve
    * @param dy1 The amount to add to the Y coordinate of the last point on this contour, to specify the control point of a quadratic curve
    * @param dx2 The amount to add to the X coordinate of the last point on this contour, to specify the end point of a quadratic curve
    * @param dy2 The amount to add to the Y coordinate of the last point on this contour, to specify the end point of a quadratic curve
    */
   public void  rQuadTo(float dx1, float dy1, float dx2, float dy2)
   {
      nRQuadTo(nRef, dx1, dy1, dx2, dy2);
   }


   /**
    * Adds a conic from the last point, using control Point, end Point, and conic weight.
    * 
    * Conic describes a conical section: a piece of an ellipse, or a piece of a parabola, or a piece of a hyperbola.
    * Conic begins at a start Point, curves towards a control Point, and then curves to an end Point. The influence
    * of the control Point is determined by {@code weight}.
    * 
    * Weight determines both the strength of the control point and the type of conic. Weight varies from zero to infinity.
    * At zero, weight causes the control point to have no effect. The conic is identical to a line segment from the start
    * point to the end point. If weight is less than one, the conic follows an elliptical arc. If weight is exactly one,
    * then conic is identical to quad - conic follows a parabolic arc. If weight is greater than one, the conic follows
    * a hyperbolic arc. If weight is infinity, the conic is identical to two line segments, connecting start point to
    * control point, and control point to end point.  A 90 degree circular arc has the weight 1 / sqrt(2).
    * 
    * @param x1 The X coordinate of the control point on a conic
    * @param y1 The Y coordinate of the control point on a conic
    * @param x2 The X coordinate of the end point on a conic
    * @param y2 The Y coordinate of the end point on a conic
    * @param weight The weight to be applied to the conic control point
    */
   public void  conicTo(float x1, float y1, float x2, float y2, float weight)
   {
      nConicTo(nRef, x1, y1, x2, y2, weight);
   }


   /**
    * 
    * @return
    */
/*
   public void  conicTo(SkPoint p1, SkPoint p2, float weight)
   {
      
   }
*/


   /**
    * Same as conicTo, but the coordinates are considered relative to the last point on this contour.
    * 
    * @param dx1 The amount to add to the X coordinate of the last point on this contour, to specify the X coordinate of the control point on a conic
    * @param dy1 The amount to add to the X coordinate of the last point on this contour, to specify the Y coordinate of the control point on a conic
    * @param dx2 The amount to add to the X coordinate of the last point on this contour, to specify the X coordinate of the end point on a conic
    * @param dy2 The amount to add to the X coordinate of the last point on this contour, to specify the Y coordinate of the end point on a conic
    * @param weight The weight to be applied to the conic control point
    */
   public void  rConicTo(float dx1, float dy1, float dx2, float dy2, float weight)
   {
      nRConicTo(nRef, dx1, dy1, dx2, dy2, weight);
   }


   /**
    * Add a cubic bezier from the last point, approaching control points (x1,y1) and (x2,y2), and ending at (x3,y3).
    * 
    * @param x1 The X coordinate of control point 1 on a cubic
    * @param y1 The Y coordinate of control point 1 on a cubic
    * @param x2 The X coordinate of control point 2 on a cubic
    * @param y2 The Y coordinate of control point 2 on a cubic
    * @param x3 The X coordinate of the end point on a cubic
    * @param y3 The Y coordinate of the end point on a cubic
    */
   public void  cubicTo(float x1, float y1, float x2, float y2, float x3, float y3)
   {
      nCubicTo(nRef, x1, y1, x2, y2, x3, y3);
   }


   /**
    * Add a cubic bezier from the last point, approaching control points p1 and p2, and ending at p3. 
    * @return
    */
/*
   public void  cubicTo(SkPoint &p1, SkPoint &p2, SkPoint &p3)
   {
      
   }
*/


   /**
    * Same as cubicTo, but the coordinates are considered relative to the current point on this contour.
    * 
    * @param dx1 The amount to add to the X coordinate of the last point on this contour, to specify the X coordinate of control point 1 on a cubic
    * @param dy1 The amount to add to the X coordinate of the last point on this contour, to specify the Y coordinate of control point 1 on a cubic
    * @param dx2 The amount to add to the X coordinate of the last point on this contour, to specify the X coordinate of control point 2 on a cubic
    * @param dy2 The amount to add to the X coordinate of the last point on this contour, to specify the Y coordinate of control point 2 on a cubic
    * @param dx3 The amount to add to the X coordinate of the last point on this contour, to specify the X coordinate of the end point on a cubic
    * @param dy3 The amount to add to the X coordinate of the last point on this contour, to specify the Y coordinate of the end point on a cubic
    */
   public void  rCubicTo(float dx1, float dy1, float dx2, float dy2, float dx3, float dy3)
   {
      nRCubicTo(nRef, dx1, dy1, dx2, dy2, dx3, dy3);
   }


   /**
    * Append the specified arc to the path.
    * 
    * If the start of the arc is different from the path's current last point, then an automatic {@link #lineTo(float,float)}
    * is added to connect the current contour to the start of the arc. However, if the path is empty, then we call
    * {@link #moveTo(float,float)} with the first point of the arc. The sweep angle is treated mod 360.
    * 
    * @param oval The bounding oval defining the shape and size of the arc
    * @param startAngle Starting angle (in degrees) where the arc begins
    * @param sweepAngle Sweep angle (in degrees) measured clockwise. This is treated mod 360.
    * @param forceMoveTo If true, always begin a new contour with the arc
    */
   public void  arcTo(SkRect oval, float startAngle, float sweepAngle, boolean forceMoveTo)
   {
      nArcTo(nRef, oval.getLeft(), oval.getTop(), oval.getRight(), oval.getBottom(), startAngle, sweepAngle, forceMoveTo);
   }


   /**
    * Append a line and arc to the current path.  This is the same as the Postscript {@code arct} command.
    * 
    * Inscribes a tangent arc of radius {@code radius} in the corner specified by the imaginary line segments
    * from the current point to (x1, y1) and from (x1, y1) to (x2, y2).  Before drawing the arc, a straight
    * line segment from the current point to the start of the arc is added, unless those points are the same.
    * 
    * @param x1 The X coordinate of the corner point
    * @param y1 The Y coordinate of the corner point
    * @param x2 The X coordinate of a line from the corner describing the tangent line for the arc
    * @param y2 The Y coordinate of a line from the corner describing the tangent line for the arc
    * @param radius The radius of the arc
    */
   public void  arcTo(float x1, float y1, float x2, float y2, float radius)
   {
      nArcTo(nRef, x1, y1, x2, y2, radius);
   }


   /**
    * Append a line and arc to the current path.
    * 
    * @param p1
    * @param p2
    * @param radius
    */
/*
   public void  arcTo(SkPoint p1, SkPoint p2, float radius)
   {
      
   }
*/


   /**
    * Append an elliptical arc from the current point in the format used by SVG.
    * 
    * @param rx The radius in the X direction.
    * @param ry The radius in the Y direction.
    * @param xAxisRotate The angle in degrees relative to the X axis.
    * @param largeArc Determines whether the smallest or largest arc possible is drawn.
    * @param sweep Determines if the arc should be swept in an anti-clockwise or clockwise direction. Note that this enum value is opposite the SVG arc sweep value.
    * @param x The destination X coordinate of the arc
    * @param y The destination Y coordinate of the arc
    */
   public void  arcTo(float rx, float ry, float xAxisRotate, ArcSize largeArc, Direction sweep, float x, float y)
   {
      nArcTo(nRef, rx, ry, xAxisRotate, largeArc.ordinal(), sweep.ordinal(), x, y);
   }


   /**
    * Append an elliptical arc from the current point in the format used by SVG.
    * 
    * @param r
    * @param xAxisRotate
    * @param largeArc
    * @param sweep
    * @param xy
    */
/*
   public void  arcTo(SkPoint r, float xAxisRotate, ArcSize largeArc, Direction sweep, SkPoint xy)
   {
      
   }
*/


   /**
    * Same as arcTo format used by SVG, but the destination coordinate is relative to the last point on this contour.
    * 
    * @param rx The radius in the X direction.
    * @param ry The radius in the Y direction.
    * @param xAxisRotate The angle in degrees relative to the X axis.
    * @param largeArc Determines whether the smallest or largest arc possible is drawn.
    * @param sweep Determines if the arc should be swept in an anti-clockwise or clockwise direction. Note that this enum value is opposite the SVG arc sweep value.
    * @param dx The destination X coordinate of the arc relative to the current point
    * @param dy The destination Y coordinate of the arc relative to the current point
    */
   public void  rArcTo(float rx, float ry, float xAxisRotate, ArcSize largeArc, Direction sweep, float dx, float dy)
   {
      nRArcTo(nRef, rx, ry, xAxisRotate, largeArc.ordinal(), sweep.ordinal(), dx, dy);
   }


   /**
    * Close the current contour.
    * 
    * If the current point is not equal to the first point of the contour, a line segment is automatically added.
    */
   public void  close()
   {
      nClose(nRef);
   }


   /**
    * Add a closed rectangle contour to the path.
    * 
    * Equivalent to {@code addRect(rect, dir, 0)}.
    * 
    * @param rect The rectangle to add as a closed contour to the path
    * @param dir The direction to wind the rectangle's contour. The default is kCW (clockwise)
    */
   public void  addRect(SkRect rect, Direction dir)
   {
      if (dir == null)
         dir = Direction.kCW;
      nAddRect(nRef, rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), dir.ordinal(), 0);
   }


   /**
    * Add a closed rectangle contour to the path.
    * 
    * @param rect The rectangle to add as a closed contour to the path
    * @param dir The direction to wind the rectangle's contour. The default is kCW (clockwise)
    * @param start Initial point of the contour (initial moveTo), expressed as a corner index, starting in the upper-left position, and progressing clock-wise.
    */
   public void  addRect(SkRect rect, Direction dir, int start)
   {
      if (dir == null)
         dir = Direction.kCW;
      nAddRect(nRef, rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), dir.ordinal(), start);
   }


   /**
    * Add a closed rectangle contour to the path.
    * 
    * @param left The left X coordinate
    * @param top The top Y coordinate
    * @param right The right X coordinate
    * @param bottom The bottom Y coordinate
    * @param dir The direction to wind the rectangle's contour. The default is kCW (clockwise)
    */
   public void  addRect(float left, float top, float right, float bottom, Direction dir)
   {
      if (dir == null)
         dir = Direction.kCW;
      nAddRect(nRef, left, top, right, bottom, dir.ordinal(), 0);
   }


   /**
    * Add a closed oval contour to the path.
    * 
    * @param oval The bounding oval to add as a closed contour to the path
    * @param dir The direction to wind the oval's contour. The default is kCW (clockwise)
    */
   public void  addOval(SkRect oval, Direction dir)
   {
      if (dir == null)
         dir = Direction.kCW;
      nAddOval(nRef, oval.getLeft(), oval.getTop(), oval.getRight(), oval.getBottom(), dir.ordinal(), 0);
   }


   /**
    * Add a closed oval contour to the path.
    * 
    * @param oval The bounding oval to add as a closed contour to the path
    * @param dir The direction to wind the oval's contour. The default is kCW (clockwise)
    * @param start Initial point of the contour (initial moveTo), expressed as an ellipse vertex index, starting at the top, clock-wise (90/0/270/180deg order)
    */
   public void  addOval(SkRect oval, Direction dir, int start)
   {
      if (dir == null)
         dir = Direction.kCW;
      nAddOval(nRef, oval.getLeft(), oval.getTop(), oval.getRight(), oval.getBottom(), dir.ordinal(), start);
   }


   /**
    * Add a closed circle contour to the path.
    * 
    * The circle contour begins at the right-most point (as though 1 were passed to addOval's 'start' param)
    *  
    * @param x The X coordinate of the center of a circle to add as a closed contour to the path
    * @param y The Y coordinate of the center of a circle to add as a closed contour to the path
    * @param radius The radius of a circle to add as a closed contour to the path
    * @param dir The direction to wind the circle's contour.
    */
   public void  addCircle(float x, float y, float radius, Direction dir)
   {
      if (dir == null)
         dir = Direction.kCW;
      nAddCircle(nRef, x, y, radius, dir.ordinal());
   }


   /**
    * Add the specified arc to the path as a new contour. 
    * 
    * @param oval The bounds of oval used to define the size of the arc
    * @param startAngle Starting angle (in degrees) where the arc begins
    * @param sweepAngle Sweep angle (in degrees) measured clockwise
    */
   public void  addArc(SkRect oval, float startAngle, float sweepAngle)
   {
      nAddArc(nRef, oval.getLeft(), oval.getTop(), oval.getRight(), oval.getBottom(), startAngle, sweepAngle);
   }


   /**
    * Add a closed round-rectangle contour to the path.
    * 
    * @param rect The bounds of a round-rectangle to add as a closed contour
    * @param rx The x-radius of the rounded corners on the round-rectangle
    * @param ry The y-radius of the rounded corners on the round-rectangle
    * @param dir The direction to wind the rectangle's contour.
    */
   public void  addRoundRect(SkRect rect, float rx, float ry, Direction dir)
   {
      if (dir == null)
         dir = Direction.kCW;
      nAddRoundRect(nRef, rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), rx, ry, dir.ordinal());
   }


   /**
    * Add a closed round-rectangle contour to the path.
    * 
    * Each corner receives two radius values [X, Y]. The corners are ordered top-left, top-right, bottom-right, bottom-left.
    * 
    * @param rect The bounds of a round-rectangle to add as a closed contour
    * @param radii Array of 8 scalars, 4 [X,Y] pairs for each corner
    * @param dir The direction to wind the rectangle's contour. Note: The radii here now go through the same constraint handling as the SkRRect radii (i.e., either radii at a corner being 0 implies a square corner and oversized radii are proportionally scaled down).
    */
   public void  addRoundRect(SkRect rect, float radii[], Direction dir)
   {
      if (radii.length < 8)
         return;
      if (dir == null)
         dir = Direction.kCW;
      nAddRoundRect(nRef, rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), radii, dir.ordinal());
   }


   /**
    * Add an SkRRect contour to the path.
    * 
    * @param rrect
    * @param dir
    */
/*
   public void  addRRect(SkRRect rrect, Direction dir)
   {
      if (dir == null)
         dir = Direction.kCW;
      // TODO      
   }
*/


   /**
    * Add an SkRRect contour to the path.
    * 
    * @param rrect
    * @param dir
    * @param start
    */
/*
   public void  addRRect(SkRRect rrect, Direction dir, int start)
   {
      if (dir == null)
         dir = Direction.kCW;
      // TODO      
   }
*/


   /**
    * Add a new contour made of just lines.
    * 
    * @param pts
    * @param count
    * @param close
    */
/*
   public void  addPoly(SkPoint pts[], int count, boolean close)
   {
      
   }
*/


   /**
    * Add a copy of src to the path.
    * 
    * @param src The path to add as a new contour
    * @param mode Determines how path is added. Default is kAppend
    */
   public void  addPath(SkPath src, AddPathMode mode)
   {
      if (src == null)
         return;
      nAddPath(nRef, src.nativeRef(), mode.ordinal());
   }


   /**
    * Add a copy of src to the path, offset by (dx,dy).
    * 
    * @param src The path to add as a new contour
    * @param dx The amount to translate the path in X as it is added
    * @param dy The amount to translate the path in Y as it is added
    * @param mode Determines how path is added. Default is kAppend
    */
   public void  addPath(SkPath src, float dx, float dy, AddPathMode mode)
   {
      if (src == null)
         return;
      nAddPath(nRef, src.nativeRef(), dx, dy, mode.ordinal());
   }


   /**
    * Add a copy of src to the path, transformed by matrix.
    * 
    * @param src The path to add as a new contour
    * @param matrix Transform applied to src
    * @param mode Determines how path is added
    * @throws IllegalArgumentException if matrix is null
    */
   public void  addPath(SkPath src, SkMatrix matrix, AddPathMode mode)
   {
      if (src == null)
         return;
      if (matrix == null)
         throw new IllegalArgumentException("matrix cannot be null");
      float[]  mat = matrix.getMatrix();
      nAddPath(nRef, src.nativeRef(), mat[0], mat[1], mat[2], mat[3], mat[4], mat[5], mat[6], mat[7], mat[8], mode.ordinal());
   }


   /**
    * Same as addPath(), but reverses the src input.
    * 
    * @param src The path to add as a new contour
    */
   public void  reverseAddPath(SkPath src)
   {
      nReverseAddPath(nRef, src.nativeRef());
   }


   /**
    * Offset the path by (dx,dy).
    * 
    * @param dx The amount in the X direction to offset the entire path
    * @param dy The amount in the Y direction to offset the entire path
    * @param dst The translated path is written here
    * @return dst
    * @throws IllegalArgumentException if dst is null
    */
   public SkPath  offset(float dx, float dy, SkPath dst)
   {
      if (dst == null)
         throw new IllegalArgumentException("dst cannot be null");
      nOffset(nRef, dx, dy, dst.nativeRef());
      return dst;
   }


   /**
    * Offset the path by (dx,dy)
    * 
    * @param dx The amount in the X direction to offset the entire path
    * @param dy The amount in the Y direction to offset the entire path
    */
   public void  offset(float dx, float dy)
   {
      nOffset(nRef, dx, dy);
   }


   /**
    * Transform the points in this path by matrix, and write the answer into dst.
    * 
    * @param matrix The matrix to apply to the path
    * @param dst The transformed path is written here
    * @return dst
    * @throws IllegalArgumentException if dst or matrix is null
    */
   public SkPath  transform(SkMatrix matrix, SkPath dst)
   {
      if (dst == null)
         throw new IllegalArgumentException("dst cannot be null");
      if (matrix == null)
         throw new IllegalArgumentException("matrix cannot be null");

      float[]  mat = matrix.getMatrix();
      nTransform(nRef, mat[0], mat[1], mat[2], mat[3], mat[4], mat[5], mat[6], mat[7], mat[8], dst.nativeRef());
      return dst;
   }


   /**
    * Transform the points in this path by matrix.
    * 
    * @param matrix The matrix to apply to the path
    * @throws IllegalArgumentException if matrix is null
    */
   public void  transform(SkMatrix matrix)
   {
      if (matrix == null)
         throw new IllegalArgumentException("matrix cannot be null");

      float[]  mat = matrix.getMatrix();
      nTransform(nRef, mat[0], mat[1], mat[2], mat[3], mat[4], mat[5], mat[6], mat[7], mat[8]);
   }


   /**
    * Return the last point on the path.
    * 
    * If no points have been added, (0,0) is returned. If there are no points, this returns false, otherwise it returns true.
    * 
    * @param lastPt Updated with the coordinates of the last point on the path
    */
/*
   public boolean  getLastPt(SkPoint lastPt)
   {
      
   }
*/


   /**
    * Set the last point on the path.
    * 
    * If no points have been added, moveTo(x,y) is automatically called.
    * 
    * @param x The new X coordinate for the last point
    * @param y The new Y coordinate for the last point
    */
   public void  setLastPt(float x, float y)
   {
      nSetLastPt(nRef, x, y);
   }


   /**
    * Set the last point on the path.
    * 
    * If no points have been added, moveTo(x,y) is automatically called.
    * 
    * @param p The new location for the last point
    */
/*
   public void  setLastPt(SkPoint p)
   {
      
   }
*/


   /**
    * Returns a mask, where each bit corresponding to a SegmentMask is set if the path contains 1 or more segments of that type.
    * 
    * Returns 0 for an empty path (no segments).
    *
    * @return A bit field which can be queried using set using the {@code xxx_SegmentMask} constants.
    */
   public int  getSegmentMasks()
   {
      return nGetSegmentMasks(nRef);
   }


   /**
    * Returns true if the point (x, y) is contained by the path, taking into account the FillType.
    * 
    * @return
    */
   public boolean  contains(float x, float y)
   {
      return nContains(nRef, x, y);
   }


   /**
    * Dumps the path data in textual form to the given output stream.
    */
   public void  dump(OutputStream stream)
   {
      // FIXME TODO
   }


   /**
    * Dumps the path data in textual form to {@code System.out}.
    * 
    * @return
    */
   public void  dump()
   {
      dump(System.out);
   }


   /*
    * Write (serialize) the path to the buffer, and return the number of bytes written.
    * 
    * If buffer is NULL, it still returns the number of bytes.
    * 
    * @param buffer A byte buffer to serialize the path data into. Or null to return the size of buffer required.
    * @return The number of bytes required, or that have been written.
    */
   public int  writeToMemory(byte[] buffer)
   {
      return nWriteToMemory(nRef, buffer);
   }


   /**
    * Initializes the path from the buffer.
    * 
    * @param buffer Buffer to read from
    * @param length Amount of memory available in the buffer
    * @return number of bytes read (must be a multiple of 4) or 0 if there was not enough memory available
    */
   public int  readFromMemory(byte[] buffer, int length)
   {
      return nReadFromMemory(nRef, buffer, length);
   }


   /**
    * Returns a non-zero, globally unique value corresponding to the set of verbs and points in the path (but not the fill type).
    * 
    * Each time the path is modified, a different generation ID will be returned.
    */
   public int  getGenerationID()
   {
      return nGetGenerationID(nRef);
   }
   


   //--------------------------------------------------------------------------
   // Native methods
   // include/c/sk_canvas.h

   native private static long     nNew();
   native private static long     nNew(long path);
   native private static void     nUnref(long path);
   native private static boolean  nEquals(long path, long otherPath);
   
   native private static boolean  nIsInterpolatable(long nRef, long compare);
   native private static boolean  nInterpolate(long path, long ending, float weight, long out);
   native private static int      nGetFillType(long path);
   native private static void     nSetFillType(long path, int ft);
   native private static boolean  nIsInverseFillType(long path);
   native private static void     nToggleInverseFillType(long path);
   native private static int      nGetConvexity(long path);
   native private static void     nSetConvexity(long path, int convexity);
   native private static boolean  nIsConvex(long path);
   native private static void     nReset(long path);
   native private static void     nRewind(long path);
   native private static boolean  nIsEmpty(long path);
   native private static boolean  nIsLastContourClosed(long path);
   native private static boolean  nIsFinite(long path);
   native private static boolean  nIsVolatile(long path);
   native private static void     nSetIsVolatile(long path, boolean isVolatile);
   native private static int      nCountPoints(long path);
   native private static void     nGetPoint(long path, int index, float[] pt);
   native private static int      nGetPoints(long path, float[] points, int max);
   native private static int      nCountVerbs(long path);
   native private static int      nGetVerbs(long path, byte[] verbs, int max);
   native private static void     nSwap(long path, long other);
   native private static void     nGetBounds(long path, float[] bounds);
   native private static void     nUpdateBoundsCache(long path);
   native private static void     nComputeTightBounds(long path, float[] bounds);
   native private static boolean  nConservativelyContainsRect(long path, float left, float top, float right, float bottom);
   native private static void     nIncReserve(long path, int extraPtCount);
   native private static void     nMoveTo(long path, float x, float y);
   native private static void     nRMoveTo(long path, float x, float y);
   native private static void     nLineTo(long path, float x, float y);
   native private static void     nRLineTo(long path, float x, float y);
   native private static void     nQuadTo(long path, float x1, float y1, float x2, float y2);
   native private static void     nRQuadTo(long path, float dx1, float dy1, float dx2, float dy2);
   native private static void     nConicTo(long path,float  x1, float y1, float x2, float y2, float weight);
   native private static void     nRConicTo(long path, float dx1, float dy1, float dx2, float dy2, float weight);
   native private static void     nCubicTo(long path, float x1, float y1, float x2, float y2, float x3, float y3);
   native private static void     nRCubicTo(long path, float dx1, float dy1, float dx2, float dy2, float dx3, float dy3);
   native private static void     nArcTo(long path, float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo);
   native private static void     nArcTo(long path, float x1, float y1, float x2, float y2, float radius);
   native private static void     nArcTo(long path, float rx, float ry, float xAxisRotate, int largeArc, int sweep, float x, float y);
   native private static void     nRArcTo(long path, float rx, float ry, float xAxisRotate, int largeArc, int sweep, float dx, float dy);
   native private static void     nClose(long path);
   native private static void     nAddRect(long path, float left, float top, float right, float bottom, int dir, int start);
   native private static void     nAddOval(long path, float left, float top, float right, float bottom, int dir, int start);
   native private static void     nAddCircle(long path, float x, float y, float radius, int dir);
   native private static void     nAddArc(long path, float left, float top, float right, float bottom, float startAngle, float sweepAngle);
   native private static void     nAddRoundRect(long path, float left, float top, float right, float bottom, float rx, float ry, int dir);
   native private static void     nAddRoundRect(long path, float left, float top, float right, float bottom, float[] radii, int dir);
   native private static void     nAddPath(long path, long src, int mode);
   native private static void     nAddPath(long path, long src, float dx, float dy, int mode);
   native private static void     nAddPath(long path, long src, float m0, float m1, float m2, float m3, float m4, float m5, float m6, float m7, float m8, int mode);
   native private static void     nReverseAddPath(long path, long src);
   native private static void     nOffset(long path, float dx, float dy, long dst);
   native private static void     nOffset(long path, float dx, float dy);
   native private static void     nTransform(long path, float m0, float m1, float m2, float m3, float m4, float m5, float m6, float m7, float m8, long dst);
   native private static void     nTransform(long path, float m0, float m1, float m2, float m3, float m4, float m5, float m6, float m7, float m8);
   native private static void     nSetLastPt(long path, float x, float y);
   native private static int      nGetSegmentMasks(long path);
   native private static boolean  nContains(long path, float x, float y);
   native private static int      nWriteToMemory(long path, byte[] buffer);
   native private static int      nReadFromMemory(long path, byte[] buffer, int length);
   native private static int      nGetGenerationID(long path);


}
