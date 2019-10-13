package com.caverock.skia4j;

public class SkCanvas
{
   // Reference to the native sk_canvas object
   private long  nRef = 0;


   public enum PointMode
   {
      /**
       * Draws each point separately. Point size and shape is determined by paint strokeWidth and strokeCap settings.
       */
      kPoints,
      /**
       * Draws each pair of points as a line segment. For N points, N/2 lines will be drawn.
       */
      kLines,
      /**
       * Draws the array of points as a polyline, or open polygonal chain. For N points, N-1 lines will be drawn. 
       */
      kPolygon
   }


   /**
    * Controls the behavior at the edge of the src-rect, when specified in drawImageRect, trading off speed for exactness.
    *
    * When filtering is enabled (in the Paint), skia may need to sample in a neighborhood around the pixels in the image.
    * If there is a src-rect specified, it is intended to restrict the pixels that will be read. However, for performance
    * reasons, some implementations may slow down if they cannot read 1-pixel past the src-rect boundary at times.
    *
    * This enum allows the caller to specify if such a 1-pixel "slop" will be visually acceptable. If it is, the caller
    * should pass kFast, and it may result in a faster draw. If the src-rect must be strictly respected, the caller
    * should pass kStrict.
    */
   public enum SrcRectConstraint
   {
      /**
       * If kStrict is specified, the implementation must respect the src-rect (if specified) strictly, and will never
       * sample outside of those bounds during sampling even when filtering.  This may be slower than kFast.
       */
      kStrict,
      /**
       * If kFast is specified, the implementation may sample outside of the src-rect (if specified) by half the width
       * of the filter.  This allows greater flexibility to the implementation and can make the draw much faster.
       */
      kFast
   }

   //--------------------------------------------------------------------------


   protected SkCanvas(long ref)
   {
      this.nRef = ref;
   }


   protected long  nativeRef()
   {
      if (nRef == 0)
         throw new IllegalArgumentException("This SkSurface object has already been released.");
      return nRef;
   }


   @Override
   public String toString()
   {
      return String.format("%s (ref:%x)", getClass().getSimpleName(), nRef);
   }


   //--------------------------------------------------------------------------
   // Save and restore


   /**
    * This call saves the current matrix, clip, and drawFilter, and pushes a copy onto a private stack.
    *
    * Subsequent calls to {@link #translate()}, {@link #scale()}, {@link #rotate()}, {@link #skew()},
    * {@link #concat()}, or {@link #clipRect()}, {@link #clipPath()} and {@link #setDrawFilter()} all
    * operate on this copy. When the balancing call to {@link #restore()} is made, the previous matrix,
    * clip, and drawFilter are restored.
    * 
    * @return The value to pass to {@link #restoreToCount()} to balance this {@code #save()}
    */
   public int  save()
   {
      return nSkCanvasSave(nRef);
   }


   /**
    * This behaves the same as {@link #save()}, but in addition it
    * allocates an off-screen botmap. All drawing calls are directed
    * there, and only when the balancing call to {@link #restore()} is
    * made is that off-screen transferred to the canvas (or the previous
    * layer).
    * 
    * @param bounds (may be null) This rect, if non-null, is used as
    *                   a hint to limit the size of the offscreen, and
    *                   thus drawing may be clipped to it, though that
    *                   clipping is not guaranteed to happen. If exact
    *                   clipping is desired, use {@link #clipRect(}).
    * @param paint (may be null) The paint is copied, and is applied
    *                    to the offscreen when {@code restore()} is
    *                    called.
    * 
    * @return The value to pass to {@link #restoreToCount()} to balance this {@code #save()}
    */
   public int  saveLayer(SkRect bounds, SkPaint paint)
   {
      long nativePaintRef = (paint != null) ? paint.nativeRef() : 0; 
      if (bounds == null)
         return nSkCanvasSaveLayer(nRef, nativePaintRef);
      else
         return nSkCanvasSaveLayer(nRef, bounds.getLeft(), bounds.getTop(), bounds.getRight(), bounds.getBottom(), nativePaintRef);
   }


   /**
    * This behaves the same as {@link #save()}, but in addition it
    * allocates an off-screen botmap. All drawing calls are directed
    * there, and only when the balancing call to {@link #restore()} is
    * made is that off-screen transferred to the canvas (or the previous
    * layer).
    * 
    * @param bounds (may be null) This rect, if non-null, is used as
    *                   a hint to limit the size of the offscreen, and
    *                   thus drawing may be clipped to it, though that
    *                   clipping is not guaranteed to happen. If exact
    *                   clipping is desired, use {@link #clipRect(}).
    * @param alpha This opacity value is applied to the offscreen buffer
    *              when {@code restore()} is called.  The alpha value
    *              must be between 0 and 255.
    * 
    * @return The value to pass to {@link #restoreToCount()} to balance this {@code #save()}
    */
   public int  saveLayerAlpha(SkRect bounds, int alpha)
   {
      if (bounds == null)
         return nSkCanvasSaveLayerAlpha(nRef, alpha);
      else
         return nSkCanvasSaveLayerAlpha(nRef, bounds.getLeft(), bounds.getTop(), bounds.getRight(), bounds.getBottom(), alpha);
   }


   /**
    * <p>Returns the number of matrix/clip states on the SkCanvas' private stack.</p>
    * 
    * <p>This will equal: number of {@link #save()} calls - {@link #restore()} calls + 1. The save count on a new canvas is 1.</p>
    * 
    * @return the count
    */
   public int  getSaveCount()
   {
      return nSkCanvasGetSaveCount(nRef);
   }


   /**
    * This call balances a previous call to {@link #save()} or
    * {@link saveLayer()}, and is used to remove all modifications to
    * the matrix and clip state since the last save call.  It is an
    * error to call {@link #restore()} more times than {@code save()} and
    * {@code saveLayer()} were called.
    */
   public void  restore()
   {
      nSkCanvasRestore(nRef);
   }


   /**
    * This call balances a previous call to {@link #save()} or
    * {@link saveLayer()}, and is used to remove all modifications to
    * the matrix and clip state since the last save call.  It is an
    * error to call {@link #restore()} more times than {@code save()} and
    * {@code saveLayer()} were called.
    */
   public void  restoreToCount(int count)
   {
      nSkCanvasRestoreToCount(nRef, count);
   }


   //--------------------------------------------------------------------------
   // Transformations


   /**
    * Preconcat the current coordinate transformation matrix with the
    * specified translation.
    * 
    * @param dx The distance to translate in X
    * @param dy The distance to translate in Y
    */
   public void  translate(float dx, float dy)
   {
      nSkCanvasTranslate(nRef, dx, dy);
   }


   /**
    * Preconcat the current coordinate transformation matrix with the
    * specified scale.
    * 
    * @param dx The amount to scale in X
    * @param dy The amount to scale in Y
    */
   public void  scale(float sx, float sy)
   {
      nSkCanvasScale(nRef, sx, sy);
   }


   /**
    * Preconcat the current coordinate transformation matrix with the
    * specified rotation.
    * 
    * @param degrees The amount to rotate in degrees
    */
   public void  rotate(float degrees)
   {
      nSkCanvasRotate(nRef, degrees);
   }
   


   /**
    * Preconcat the current coordinate transformation matrix with the
    * specified rotation.
    * 
    * @param degrees The amount to rotate in degrees
    * @param px The X coord for the pivot point (unchanged by the rotation)
    * @param py The Y coord for the pivot point (unchanged by the rotation)
    */
   public void  rotate(float degrees, float px, float py)
   {
      nSkCanvasRotate(nRef, degrees, px, py);
   }


   /**
    * Preconcat the current coordinate transformation matrix with the
    * specified skew.
    * 
    * @param sx The amount to skew in X
    * @param sy The amount to skew in Y
    */
   public void  skew(float sx, float sy)
   {
      nSkCanvasSkew(nRef, sx, sy);
   }


   /**
    * Preconcat the current coordinate transformation matrix with the
    * specified matrix.
    * 
    * @param matrix The transform matrix to concatenate.
    */
   public void  concat(SkMatrix matrix) {
      if (matrix == null)
         return;
      float[]  mat = matrix.getMatrix();
      // More efficient to pass the array elements rather than unmarshalling in the C code
      nSkCanvasConcat(nRef, mat[0], mat[1], mat[2], mat[3], mat[4], mat[5], mat[6], mat[7], mat[8]);
   }


   /**
    * Replace the current matrix with a copy of the specified matrix.
    * 
    * @param matrix The replacement transform matrix.
    */
   public void  setMatrix(SkMatrix matrix) {
      if (matrix == null)
         return;
      float[]  mat = matrix.getMatrix();
      // More efficient to pass the array elements rather than unmarshalling in the C code
      nSkCanvasSetMatrix(nRef, mat[0], mat[1], mat[2], mat[3], mat[4], mat[5], mat[6], mat[7], mat[8]);
   }


   /**
    * Resets the current matrix to tyhe identity matrix.
    */
   public void  resetMatrix() {
      nSkCanvasResetMatrix(nRef);
   }


   //--------------------------------------------------------------------------
   // Drawing operations


   public void clear(int color)
   {
      nSkCanvasClear(nRef, color);
   }


   public void drawColor(int color, SkBlendMode blendMode)
   {
      nSkCanvasDrawColor(nRef, color, blendMode.ordinal());
   }


   public void drawPaint(SkPaint paint)
   {
      nSkCanvasDrawPaint(nRef, paint.nativeRef());
   }


   /**
   /* Draws a rectangle using the specified paint.
    * 
    * @param rect
    * @param paint
    */
   public void drawRect(SkRect rect, SkPaint paint)
   {
      nSkCanvasDrawRect(nRef, rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), paint.nativeRef());
   }



   /**
   /* Draws a circle using the specified paint.
    * 
    * @param cx the X coordinate of the centre of the circle.
    * @param cy the Y coordinate of the centre of the circle.
    * @param radius the radius of the circle
    * @param paint
    */
   public void drawCircle(float cx, float cy, float radius, SkPaint paint)
   {
      nSkCanvasDrawCircle(nRef, cx, cy, radius, paint.nativeRef());
   }



   /**
   /* Draws an oval inscribed in the specified rectangle using the specified paint.
    * 
    * @param rect the bounds of the rectangle to be drawn
    * @param paint
    */
   public void drawOval(SkRect rect, SkPaint paint)
   {
      nSkCanvasDrawOval(nRef, rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), paint.nativeRef());
   }



   /**
    * <p>Draw the specified arc, which will be scaled to fit inside the specified oval.</p>
    *
    * Sweep angles are not treated as modulo 360 and thus can exceed a full sweep of the oval. Note that this
    * differs slightly from SkPath::arcTo, which treats the sweep angle mod 360. If the oval is empty or the
    * sweep angle is zero nothing is drawn. If useCenter is true the oval center is inserted into the implied
    * path before the arc and the path is closed back to the, center forming a wedge. Otherwise, the implied
    * path contains just the arc and is not closed.
    *
    * @param left The left X coordinate of the bounds of the oval
    * @param top  The top Y coordinate of the bounds of the oval
    * @param right  The right X coordinate of the bounds of the oval
    * @param bottom The bottom Y coordinate of the bounds of the oval
    * @param startAngle Starting angle (in degrees) where the arc begins
    * @param sweepAngle Sweep angle (in degrees) measured clockwise.
    * @param useCenter true means include the center of the oval.
    * @param paint The paint used to draw the arc
    */
   public void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, SkPaint paint)
   {
      nSkCanvasDrawArc(nRef, left, top, right, bottom, startAngle, sweepAngle, useCenter, paint.nativeRef());
   }



   /**
    * Draw a line segment with the specified start and stop x,y coordinates, using the specified paint.
    * The paint stroke width describes the line thickness. The cap draws the end rounded or square.
    * The paint style is ignored, as if were set to {@code Style.Stroke}.
    * 
    * @param x0 The X coordinate of the start point of the line
    * @param y0 The Y coordinate of the start point of the line
    * @param x1 The X coordinate of the end point of the line
    * @param y1 The Y coordinate of the end point of the line
    * @param paint The paint used to draw the line
    */
   public void drawLine(float x0, float y0, float x1, float y1, SkPaint paint)
   {
      nSkCanvasDrawLine(nRef, x0, y0, x1, y1, paint.nativeRef());
   }



   /**
    * <p>Draw a single point. The point is centered at the coordinate specified by x and y.
    * Its diameter is specified by the paint's stroke width (as transformed by the canvas' CTM), with
    * special treatment for a stroke width of 0, which always draws exactly 1 pixel (or at most 4 if
    * antialiasing is enabled).</p>
    * <p>The shape of the point is controlled by the paint's Cap type. The shape is a square, unless
    * the cap type is Round, in which case the shape is a circle. The paint style is ignored, as if
    * were set to {@code Style.Stroke}.</p>
    * 
    * @param x
    * @param y
    * @param paint
    */
   public void drawPoint(float x, float y, SkPaint paint)
   {
      nSkCanvasDrawPoint(nRef, x, y, paint.nativeRef());
   }


   /**
    * Convenience function for {@link #drawPoints(PointMode, float[], SkPaint)PointMode,int,float[],SkPaint)}
    * which assumes you want to draw the entire point array.
    * 
    * @param mode
    * @param pts
    * @param paint
    */
   public void drawPoints(PointMode mode, float[] pts, SkPaint paint)
   {
      nSkCanvasDrawPoints(nRef, mode.ordinal(), Math.floorDiv(pts.length, 2), pts, paint.nativeRef());
   }


   /**
    * <p>Draw a series of points. Each point is centered at the coordinate specified by pts[], and its
    * diameter is specified by the paint's stroke width (as transformed by the canvas' CTM), with
    * special treatment for a stroke width of 0, which always draws exactly 1 pixel (or at most 4 if
    * antialiasing is enabled).</p>
    * <p>The shape of the point is controlled by the paint's Cap type. The shape is a square, unless
    * the cap type is Round, in which case the shape is a circle. The paint style is ignored, as if
    * were set to {@code Style.Stroke}.</p>
    * 
    * @param mode
    * @param count
    * @param pts
    * @param paint
    */
   public void drawPoints(PointMode mode, int count, float[] pts, SkPaint paint)
   {
      int ptCount = Math.floorDiv(pts.length, 2);
      if (count > ptCount)
         count = ptCount;
      nSkCanvasDrawPoints(nRef, mode.ordinal(), count, pts, paint.nativeRef());
   }



   /**
    * Draw the specified image, with its top/left corner at (x,y), using the specified paint, transformed by the current matrix.
    * 
    * If paint is supplied, apply SkColorFilter, alpha, SkImageFilter, SkBlendMode, and SkDrawLooper. If image is SkColorType.kAlpha_8,
    * apply SkShader. If paint contains SkMaskFilter, generate mask from image bounds. If generated mask extends beyond image bounds,
    * replicate image edge colors, just as SkShader made from SkImage::makeShader with SkShader::kClamp_TileMode set replicates
    * the image edge color when it samples outside of its bounds.
    * 
    * @param image the image to be drawn to the canvas.
    * @param left the X position
    * @param top the Y position
    * @param paint  The paint used to draw the image. May be null.
    */
   public void drawImage(SkImage image, float left, float top, SkPaint paint)
   {
      long  paintRef = (paint != null) ? paint.nativeRef() : 0;
      nSkCanvasDrawImage(nRef, image.nativeRef(), left, top, paintRef);
   }



   /**
    * Draw the specified image, scaling and translating so that it fills the specified dst rect.
    *
    * If the src rect is non-null, only that subset of the image is transformed and drawn.
    * 
    * @param image The image to be drawn to the canvas.
    * @param src The subset of the image to be drawn. Optional.
    * @param dst The destination rectangle where the scaled/translated image will be drawn
    * @param paint  The paint used to draw the image. May be null.
    * @param constraint Control the tradeoff between speed and exactness w.r.t. the src rect. Defaults to {@code kStrict}.
    */
   public void drawImageRect(SkImage image, SkRect src, SkRect dst, SkPaint paint, SrcRectConstraint constraint)
   {
      long  paintRef = (paint != null) ? paint.nativeRef() : 0;
      if (constraint == null)
         constraint = SrcRectConstraint.kStrict;
      if (src != null) {
         nSkCanvasDrawImageRect(nRef, image.nativeRef(),
                                src.getLeft(), src.getTop(), src.getRight(), src.getBottom(),
                                dst.getLeft(), dst.getTop(), dst.getRight(), dst.getBottom(),
                                paintRef, constraint.ordinal());
      } else {
         nSkCanvasDrawImageRect(nRef, image.nativeRef(),
                                0f, 0f, (float) image.getWidth(), (float) image.getHeight(),
                                dst.getLeft(), dst.getTop(), dst.getRight(), dst.getBottom(),
                                paintRef, constraint.ordinal());
      }
   }



   /**
    * <p>Write raw pixel values onto the canvas.</p>
    * 
    * <p>This method affects the pixels in the base-layer, and operates in pixel coordinates, ignoring the matrix and clip.</p>
    * 
    * <p>The specified ImageInfo and (x,y) offset specifies a rectangular target equivalent to (x, y, info.width(), info.height().
    * The target is intersected with the bounds of the base-layer. If this intersection is not empty, then this call
    * replaces the surface pixels with the corresponding provided pixels, performing any colortype/alphatype transformations needed.</p>
    * 
    * <p>This call can fail, returning false, for several reasons:</p>
    * <ul>
    * <li>If the src colortype/alphatype cannot be converted to the canvas' types
    * <li>If this canvas is not backed by pixels (e.g. picture or PDF)
    * </ul>
    * 
    * @param info Details of the colour type, alpha type, and colour space of the provided pixels
    * @param pixels A one dimensional {@code int} array of pixel values
    * @param pixelsPerRow The number of pixels per row in {@code pixels}.  The difference in array index
    *                     between the start of one row and the start of the next.
    * @param x The X coordinate of the position on the canvas to place this image data
    * @param y The Y coordinate of the position on the canvas to place this image data
    * @return true if the pixels were successfully written to the canvas
    */
   public boolean writePixels(SkImageInfo info, int[] pixels, int pixelsPerRow, int x, int y)
   {
      return nSkCanvasWritePixelsInt(nRef, info.nativeRef(), pixels, pixelsPerRow, x, y);
   }

   

   /**
    * <p>Read raw pixel values from the canvas.</p>
    * 
    * <p>Copy the pixels from the canvas into the specified buffer, converting them into the requested format.</p>
    * 
    * <p>The surface pixels are read starting at the specified (srcX,srcY) location in the coordinate system of the base-layer.</p>
    * 
    * <p>The specified ImageInfo and (x,y) offset specifies a source rectangle equivalent to (x, y, info.width(), info.height().
    * The source rectangle is intersected with the bounds of the base-layer. If this intersection is not empty, then this call
    * copies the pixels into the {@code dstPixels} array, performing any colortype/alphatype transformations needed.</p>
    * 
    * <p>This call can fail, returning false, for several reasons:</p>
    * <ul>
    * <li>The source rectangle does not intersect the base-layer bounds
    * <li>If the requested colortype/alphatype cannot be converted from the canvas' types
    * <li>If this canvas is not backed by pixels (e.g. picture or PDF)
    * </ul>
    * 
    * @param dstInfo Details of the colour type, alpha type, and colour space of the destination pixels
    * @param dstPixels A one dimensional {@code int} array for the pixel values
    * @param dstPixelsPerRow The number of pixels per row in {@code pixels}.  The difference in array index
    *                        between the start of one row and the start of the next.
    * @param srcX The X coordinate of the position on the canvas to copy the pixel values from
    * @param srcY The Y coordinate of the position on the canvas to copy the pixel values from
    * @return true if the pixels were successfully read from the canvas
    */
   public boolean readPixels(SkImageInfo dstInfo, int[] dstPixels, int dstPixelsPerRow, int srcX, int srcY)
   {
      // Space check
      if (dstPixels.length < (dstInfo.getHeight() * dstPixelsPerRow))
         return false;
      return nSkCanvasReadPixelsInt(nRef, dstInfo.nativeRef(), dstPixels, dstPixelsPerRow, srcX, srcY);
   }



   //--------------------------------------------------------------------------
   // Native methods
   // include/c/sk_canvas.h

   native private static int  nSkCanvasSave(long canvas);
   
   native private static int  nSkCanvasSaveLayer(long canvas, long paint);
   native private static int  nSkCanvasSaveLayer(long canvas, float left, float top, float right, float bottom, long paint);

   native private static int  nSkCanvasSaveLayerAlpha(long canvas, int alpha);
   native private static int  nSkCanvasSaveLayerAlpha(long canvas, float left, float top, float right, float bottom, int alpha);

   native private static int  nSkCanvasGetSaveCount(long canvas);

   native private static void  nSkCanvasRestore(long canvas);

   native private static void  nSkCanvasRestoreToCount(long canvas, int count);


   native private static void  nSkCanvasTranslate(long canvas, float dx, float dy);
   native private static void  nSkCanvasScale(long canvas, float sx, float sy);
   native private static void  nSkCanvasRotate(long canvas, float degrees);
   native private static void  nSkCanvasRotate(long canvas, float degrees, float px, float py);
   native private static void  nSkCanvasSkew(long canvas, float sx, float sy);
   native private static void  nSkCanvasConcat(long canvas, float m0, float m1, float m2, float m3, float m4, float m5, float m6, float m7, float m8);
   native private static void  nSkCanvasSetMatrix(long canvas, float m0, float m1, float m2, float m3, float m4, float m5, float m6, float m7, float m8);
   native private static void  nSkCanvasResetMatrix(long canvas);


   native private static void  nSkCanvasClear(long canvas, int colour);
   native private static void  nSkCanvasDrawColor(long canvas, int colour, int blendMode);
   native private static void  nSkCanvasDrawPaint(long canvas, long paint);

   native private static void  nSkCanvasDrawRect(long canvas, float left, float top, float right, float bottom, long paint);
   native private static void  nSkCanvasDrawCircle(long canvas, float cx, float cy, float radius, long paint);
   native private static void  nSkCanvasDrawOval(long canvas, float left, float top, float right, float bottom, long paint);
   native private static void  nSkCanvasDrawArc(long canvas, float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, long paint);
   native private static void  nSkCanvasDrawLine(long canvas, float x0, float y0, float x1, float y1, long paint);
   native private static void  nSkCanvasDrawPoint(long canvas, float x, float y, long paint);
   native private static void  nSkCanvasDrawPoints(long canvas, int mode, int count, float[] pts, long paint);

   native private static void  nSkCanvasDrawImage(long canvas, long image, float left, float top, long paint);
   native private static void  nSkCanvasDrawImageRect(long canvas, long image, float srcLeft, float srcTop, float srcRight, float srcBottom, float dstLeft, float dstTop, float dstRight, float dstBottom, long paint, int constraint);

   native private static boolean nSkCanvasWritePixelsInt(long canvas, long imageInfo, int[] pixels, int pixelsPerRow, int x, int y);
   native private static boolean nSkCanvasReadPixelsInt(long canvas, long dstInfo, int[] dstPixels, int dstPixelsPerRow, int srcX, int srcY);

}
