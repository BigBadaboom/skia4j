package com.caverock.skia4j;

public class Color
{
   // SOme predefined ARGB colours
   public static final int BLACK       = 0xFF000000;
   public static final int DKGRAY      = 0xFF444444;
   public static final int GRAY        = 0xFF888888;
   public static final int LTGRAY      = 0xFFCCCCCC;
   public static final int WHITE       = 0xFFFFFFFF;
   public static final int RED         = 0xFFFF0000;
   public static final int GREEN       = 0xFF00FF00;
   public static final int BLUE        = 0xFF0000FF;
   public static final int YELLOW      = 0xFFFFFF00;
   public static final int CYAN        = 0xFF00FFFF;
   public static final int MAGENTA     = 0xFFFF00FF;
   public static final int TRANSPARENT = 0;


   /**
    * <p>Creates a colour from the specified integer.</p>
    * 
    * <p>A color int always defines a color in the {@link ColorSpace.Named#SRGB sRGB}
    * color space using 4 components packed in a single 32 bit integer value:</p>
    *
    * <p>The four components of a color int are encoded in the following way:</p>
    * <pre>
    * int color = (A & 0xff) << 24 | (R & 0xff) << 16 | (G & 0xff) << 16 | (B & 0xff);
    * </pre>
    * 
    * @param colour
    */
   public Color()
   {
   }


   //--------------------------------------------------------------------------


   /**
    * Return a color-int from red, green, blue components.
    * The alpha component is implicitly 255 (fully opaque).
    * These component values should be \([0..255]\), but there is no
    * range check performed, so if they are out of range, the
    * returned color is undefined.
    *
    * @param red  Red component \([0..255]\) of the color
    * @param green Green component \([0..255]\) of the color
    * @param blue  Blue component \([0..255]\) of the color
    */
   public static int rgb(int red, int green, int blue)
   {
      return 0xff000000 | (red << 16) | (green << 8) | blue;
   }

   /**
    * Return a color-int from alpha, red, green, blue components.
    * These component values should be \([0..255]\), but there is no
    * range check performed, so if they are out of range, the
    * returned color is undefined.
    * @param alpha Alpha component \([0..255]\) of the color
    * @param red Red component \([0..255]\) of the color
    * @param green Green component \([0..255]\) of the color
    * @param blue Blue component \([0..255]\) of the color
    */
   public static int argb(int alpha, int red, int green, int blue)
   {
      return (alpha << 24) | (red << 16) | (green << 8) | blue;
   }

}
