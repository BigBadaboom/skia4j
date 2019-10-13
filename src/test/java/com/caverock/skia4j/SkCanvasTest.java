package com.caverock.skia4j;

import java.io.File;

import junit.framework.TestCase;

public class SkCanvasTest extends TestCase
{

   @Override
   protected void setUp() throws Exception
   {
      super.setUp();
      System.load(Config.LIBRARY_FILE);
   }


   public void testWritePixelsReadPixelsInt() throws Exception
   {
      try (SkSurface surface = SkSurface.makeRaster(SkImageInfo.makeN32Premul(4, 4)) ) {
         SkCanvas canvas = surface.getCanvas();
         canvas.clear(Color.RED);
         int[] checkers = { Color.BLACK, Color.WHITE,
                            Color.WHITE, Color.BLACK };
         boolean write = canvas.writePixels(SkImageInfo.makeN32Premul(2, 2), checkers, 2, 1, 1);
         assertTrue(write);

         int[] dst = new int[16];
         boolean read = canvas.readPixels(SkImageInfo.makeN32Premul(4, 4), dst, 4, 0, 0);
         assertTrue(read);
         assertEquals(Color.RED, dst[0]);
         assertEquals(Color.RED, dst[1]);
         assertEquals(Color.RED, dst[2]);
         assertEquals(Color.RED, dst[3]);
         assertEquals(Color.RED, dst[4]);
         assertEquals(Color.BLACK, dst[5]);
         assertEquals(Color.WHITE, dst[6]);
         assertEquals(Color.RED, dst[7]);
         assertEquals(Color.RED, dst[8]);
         assertEquals(Color.WHITE, dst[9]);
         assertEquals(Color.BLACK, dst[10]);
         assertEquals(Color.RED, dst[11]);
         assertEquals(Color.RED, dst[12]);
         assertEquals(Color.RED, dst[13]);
         assertEquals(Color.RED, dst[14]);
         assertEquals(Color.RED, dst[15]);
         /*
         for (int j=0; j<4; j++) {
            for (int i=0; i<4; i++) {
               System.out.printf("%08x ", dst[j*4+i]);
            }
            System.out.println();
         }
         */
      }
      
   }


}
