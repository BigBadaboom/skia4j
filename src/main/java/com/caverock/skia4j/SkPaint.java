package com.caverock.skia4j;

public class SkPaint  implements AutoCloseable
{
   private int              color = 0xff000000;   // Opaque black
   private boolean          isAntialias = false;
   private boolean          isDither = false;
   private Cap              strokeCap = Cap.Butt;
   private Join             strokeJoin = Join.Miter;
   private float            strokeMiter = 4f;
   private float            strokeWidth = 0f;
   private SkFilterQuality  filterQuality = SkFilterQuality.kNone;
   private Style            style = Style.Fill;

   // Reference to the native sk_image_info object
   private long  nRef = 0;


   public enum Style {
      Fill,
      Stroke,
      StrokeAndFill
   }


   public enum Cap {
      Butt,
      Round,
      Square
   }


   private enum Join {
      Miter,
      Round,
      Bevel
   }


   //--------------------------------------------------------------------------


   /**
    * Create a new paint with default settings:<br>
    *    antialias : false<br>
    *    stroke : false<br>
    *    stroke width : 0.0f (hairline)<br>
    *    stroke miter : 4.0f<br>
    *    stroke cap : BUTT_SK_STROKE_CAP<br>
    *    stroke join : MITER_SK_STROKE_JOIN<br>
    *    color : opaque black<br>
    *    shader : NULL<br>
    *    maskfilter : NULL<br>
    *    xfermode_mode : SRCOVER_SK_XFERMODE_MODE<br>
    *    font size : 12,
    *    hinting : normal
    *    blend mode :  srcOver
    *    filter quality : kNone
    */
   public SkPaint()
   {
      long ref = nSkPaintNew();
      if (ref == 0)
         throw new RuntimeException("Could not create Paint. Out of memory?");

      this.nRef = ref;
      this.isAntialias = false;
   }


   /**
    * Tidy up the native resource associated with this class.
    * Must be called, when this object is no longer needed, to avoid memory leaks.
    *
    * You can call close() directly, or use try-with-resources.
   */
   @Override
   public void  close() throws Exception
   {
      nSkPaintDelete(nativeRef());
      nRef = 0;
   }


   protected long  nativeRef()
   {
      if (nRef == 0)
         throw new IllegalArgumentException("This SkPaint object has already been released.");
      return nRef;
   }


   @Override
   public String toString()
   {
      return String.format("%s (ref:%x)", getClass().getSimpleName(), nRef);
   }


   //--------------------------------------------------------------------------


   public void  setAntialias(boolean isAntialias)
   {
      nSkPaintSetAntialias(nRef, isAntialias);
      this.isAntialias = isAntialias;
   }

   public boolean  isAntialias()
   {
      return isAntialias;
   }


   //--------------------------------------------------------------------------


   public int  getColor()
   {
      return this.getColor();
   }

   public void  setColor(int color)
   {
      nSkPaintSetColor(nRef, color);
      this.color = color;
   }


   //--------------------------------------------------------------------------


   public void  setDither(boolean isDither)
   {
      nSkPaintSetDither(nRef, isDither);
      this.isDither = isDither;
   }


   public boolean  isDither()
   {
      return isDither;
   }


   //--------------------------------------------------------------------------


   public Cap  getStrokeCap()
   {
      return strokeCap;
   }


   public void  setStrokeCap(Cap strokeCap)
   {
      nSkPaintSetStrokeCap(nRef, strokeCap.ordinal());
      this.strokeCap = strokeCap;
   }


   //--------------------------------------------------------------------------


   public Join  getStrokeJoin()
   {
      return strokeJoin;
   }


   public void  setStrokeJoin(Join strokeJoin)
   {
      nSkPaintSetStrokeJoin(nRef, strokeJoin.ordinal());
      this.strokeJoin = strokeJoin;
   }


   //--------------------------------------------------------------------------


   public float  getStrokeMiter()
   {
      return strokeMiter;
   }


   public void  setStrokeMiter(float miterLimit)
   {
      nSkPaintSetStrokeMiter(nRef, miterLimit);
      this.strokeMiter = miterLimit;
   }


   //--------------------------------------------------------------------------


   public float  getStrokeWidth()
   {
      return strokeWidth;
   }


   public void  setStrokeWidth(float strokeWidth)
   {
      nSkPaintSetStrokeWidth(nRef, strokeWidth);
      this.strokeWidth = strokeWidth;
   }


   //--------------------------------------------------------------------------


   public SkFilterQuality getFilterQuality()
   {
      return filterQuality;
   }


   public void setFilterQuality(SkFilterQuality filterQuality)
   {
      nSkPaintSetFilterQuality(nRef, filterQuality.ordinal());
      this.filterQuality = filterQuality;
   }


   //--------------------------------------------------------------------------


   public Style  getStyle()
   {
      return style;
   }


   public void  setStyle(Style style)
   {
      nSkPaintSetStyle(nRef, style.ordinal());
      this.style = style;
   }





   //--------------------------------------------------------------------------
   // Native methods
   // include/c/sk_paint.h

   /*
    * Create a new paint with default settings:
    *     antialias : false
    *     stroke : false
    *     stroke width : 0.0f (hairline)
    *     stroke miter : 4.0f
    *     stroke cap : BUTT_SK_STROKE_CAP
    *     stroke join : MITER_SK_STROKE_JOIN
    *     color : opaque black
    *     shader : NULL
    *     maskfilter : NULL
    *     xfermode_mode : SRCOVER_SK_XFERMODE_MODE
    */
   //SK_API sk_paint_t* sk_paint_new(void);
   native private static long  nSkPaintNew();

   /*
    * Release the memory storing the sk_paint_t and unref() all
    * associated objects.
    */
   //SK_API void sk_paint_delete(sk_paint_t*);
   native private static void  nSkPaintDelete(long ref);

   native private static void  nSkPaintSetAntialias(long ref, boolean isAntialias);
   native private static void  nSkPaintSetColor(long ref, int color);
   native private static void  nSkPaintSetDither(long ref, boolean isDither);
   native private static void  nSkPaintSetStrokeWidth(long ref, float width);
   native private static void  nSkPaintSetStrokeMiter(long ref, float miterLimit);
   native private static void  nSkPaintSetStrokeCap(long ref, int cap);
   native private static void  nSkPaintSetStrokeJoin(long ref, int join);
   native private static void  nSkPaintSetFilterQuality(long ref, int filterQuality);
   native private static void  nSkPaintSetStyle(long ref, int style);


}
