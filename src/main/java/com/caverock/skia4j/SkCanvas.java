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

   /*
    * Save the current matrix and clip on the canvas.  When the
    * balancing call to sk_canvas_restore() is made, the previous matrix
    * and clip are restored.
    */
   //SK_API void sk_canvas_save(sk_canvas_t*);

   /*
    * This behaves the same as sk_canvas_save(), but in addition it
    * allocates an offscreen surface. All drawing calls are directed
    * there, and only when the balancing call to sk_canvas_restore() is
    * made is that offscreen transfered to the canvas (or the previous
    * layer).
    * @param sk_rect_t* (may be null) This rect, if non-null, is used as
    *                   a hint to limit the size of the offscreen, and
    *                   thus drawing may be clipped to it, though that
    *                   clipping is not guaranteed to happen. If exact
    *                   clipping is desired, use sk_canvas_clip_rect().
    * @param sk_paint_t* (may be null) The paint is copied, and is applied
    *                    to the offscreen when sk_canvas_restore() is
    *                    called.
    */
   //SK_API void sk_canvas_save_layer(sk_canvas_t*, const sk_rect_t*, const sk_paint_t*);

   /*
    * This call balances a previous call to sk_canvas_save() or
    * sk_canvas_save_layer(), and is used to remove all modifications to
    * the matrix and clip state since the last save call.  It is an
    * error to call sk_canvas_restore() more times than save and
    * save_layer were called.
    */
   //SK_API void sk_canvas_restore(sk_canvas_t*);

   /*
    * Preconcat the current coordinate transformation matrix with the
    * specified translation.
    */
   //SK_API void sk_canvas_translate(sk_canvas_t*, float dx, float dy);

   /*
    * Preconcat the current coordinate transformation matrix with the
    * specified scale.
    */
   //SK_API void sk_canvas_scale(sk_canvas_t*, float sx, float sy);

   /*
    * Preconcat the current coordinate transformation matrix with the
    * specified rotation in degrees.
    */
   //SK_API void sk_canvas_rotate_degrees(sk_canvas_t*, float degrees);

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

   /*
    * Preconcat the current coordinate transformation matrix with the
    * specified matrix.
    */
   //SK_API void sk_canvas_concat(sk_canvas_t*, const sk_matrix_t*);

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
