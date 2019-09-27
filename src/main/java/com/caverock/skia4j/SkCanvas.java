package com.caverock.skia4j;

public class SkCanvas
{
   // Reference to the native sk_canvas object
   private long  nRef = 0;


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


   //--------------------------------------------------------------------------
   // Drawing operations


   public void  drawColor(int color)
   {
      try (SkPaint paint = new SkPaint())
      {
         paint.setColor(color);
         nSkCanvasDrawPaint(nRef, paint.nativeRef());
      }
      catch (Exception e)
      {
         // FIXME decide what to do about these situations
         System.out.println("Exception closing resource: " + e.getMessage());
      }
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
    * @param rect
    * @param paint
    */
   public void drawOval(SkRect rect, SkPaint paint)
   {
      nSkCanvasDrawOval(nRef, rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), paint.nativeRef());
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


   /*
    * Preconcat the current coordinate transformation matrix with the
    * specified translation.
    */
   //SK_API void sk_canvas_translate(sk_canvas_t*, float dx, float dy);
   native private static void  nSkCanvasTranslate(long canvas, float dx, float dy);

   /*
    * Preconcat the current coordinate transformation matrix with the
    * specified scale.
    */
   //SK_API void sk_canvas_scale(sk_canvas_t*, float sx, float sy);
   native private static void  nSkCanvasScale(long canvas, float sx, float sy);

   /*
    * Preconcat the current coordinate transformation matrix with the
    * specified rotation in degrees.
    */
   //SK_API void sk_canvas_rotate_degrees(sk_canvas_t*, float degrees);
   native private static void  nSkCanvasRotate(long canvas, float degrees);

   native private static void  nSkCanvasRotate(long canvas, float degrees, float px, float py);

   /*
    * Preconcat the current coordinate transformation matrix with the
    * specified rotation in radians.
    */
   //SK_API void sk_canvas_rotate_radians(sk_canvas_t*, float radians);

   /*
    * Preconcat the current coordinate transformation matrix with the
    * specified skew.
    */
   //SK_API void sk_canvas_skew(sk_canvas_t*, float sx, float sy);
   native private static void  nSkCanvasSkew(long canvas, float sx, float sy);

   /*
    * Preconcat the current coordinate transformation matrix with the
    * specified matrix.
    */
   //SK_API void sk_canvas_concat(sk_canvas_t*, const sk_matrix_t*);
   native private static void  nSkCanvasConcat(long canvas, float m0, float m1, float m2, float m3, float m4, float m5, float m6, float m7, float m8);


   /*
    * Modify the current clip with the specified rectangle.  The new
    * current clip will be the intersection of the old clip and the
    * rectange.
    */
   //SK_API void sk_canvas_clip_rect(sk_canvas_t*, const sk_rect_t*);

   /*
    * Modify the current clip with the specified path.  The new
    * current clip will be the intersection of the old clip and the
    * path.
    */
   //SK_API void sk_canvas_clip_path(sk_canvas_t*, const sk_path_t*);


   /*
    * Fill the entire canvas (restricted to the current clip) with the
    * specified paint.
    */
   //SK_API void sk_canvas_draw_paint(sk_canvas_t*, const sk_paint_t*);
   native private static void  nSkCanvasDrawPaint(long canvas, long paint);

   /*
    * Draw the specified rectangle using the specified paint. The
    * rectangle will be filled or stroked based on the style in the
    * paint.
    */
   //SK_API void sk_canvas_draw_rect(sk_canvas_t*, const sk_rect_t*, const sk_paint_t*);
   native private static void nSkCanvasDrawRect(long canvas, float left, float top, float right, float bottom, long paint);

   /*
    * Draw the circle centered at (cx, cy) with radius rad using the specified paint.
    * The circle will be filled or framed based on the style in the paint
    */
   //SK_API void sk_canvas_draw_circle(sk_canvas_t*, float cx, float cy, float rad, const sk_paint_t*);
   native private static void nSkCanvasDrawCircle(long canvas, float cx, float cy, float radius, long paint);

   /*
    * Draw the specified oval using the specified paint. The oval will be
    * filled or framed based on the style in the paint
    */
   //SK_API void sk_canvas_draw_oval(sk_canvas_t*, const sk_rect_t*, const sk_paint_t*);
   native private static void nSkCanvasDrawOval(long canvas, float left, float top, float right, float bottom, long paint);

   /*
    * Draw the specified path using the specified paint. The path will be
    * filled or framed based on the style in the paint
    */
   //SK_API void sk_canvas_draw_path(sk_canvas_t*, const sk_path_t*, const sk_paint_t*);

   /*
    * Draw the specified image, with its top/left corner at (x,y), using
    * the specified paint, transformed by the current matrix.
    * @param sk_paint_t* (may be NULL) the paint used to draw the image.
    */
   //SK_API void sk_canvas_draw_image(sk_canvas_t*, const sk_image_t*,
   //                                 float x, float y, const sk_paint_t*);

   /*
    * Draw the specified image, scaling and translating so that it fills
    * the specified dst rect. If the src rect is non-null, only that
    * subset of the image is transformed and drawn.
    * @param sk_paint_t* (may be NULL) The paint used to draw the image.
    */
   //SK_API void sk_canvas_draw_image_rect(sk_canvas_t*, const sk_image_t*,
   //                                      const sk_rect_t* src,
   //                                      const sk_rect_t* dst, const sk_paint_t*);

   /*
    * Draw the picture into this canvas (replay the pciture's drawing commands).
    * @param sk_matrix_t* If non-null, apply that matrix to the CTM when
    *                     drawing this picture. This is logically
    *                     equivalent to: save, concat, draw_picture,
    *                     restore.
    * @param sk_paint_t* If non-null, draw the picture into a temporary
    *                    buffer, and then apply the paint's alpha,
    *                    colorfilter, imagefilter, and xfermode to that
    *                    buffer as it is drawn to the canvas.  This is
    *                    logically equivalent to save_layer(paint),
    *                    draw_picture, restore.
    */
   //SK_API void sk_canvas_draw_picture(sk_canvas_t*, const sk_picture_t*,
   //                                   const sk_matrix_t*, const sk_paint_t*);

}
