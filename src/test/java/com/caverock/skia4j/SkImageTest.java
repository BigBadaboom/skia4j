package com.caverock.skia4j;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class SkImageTest extends TestCase
{
   private File  mandrillFile;


   @Override
   protected void setUp() throws Exception
   {
      super.setUp();
      System.load(Config.LIBRARY_FILE);
      
      mandrillFile = new File(Config.MANDRILL_PNG);
   }
   

   public void testMakeFromFile()
   {
      assertTrue(mandrillFile.exists());
      SkImage test = SkImage.makeFromEncoded(mandrillFile);
      assertNotNull(test);

      assertEquals(128, test.getWidth());
      assertEquals(128, test.getHeight());
      assertEquals(SkColorType.kUnknown, test.getColorType());
      assertEquals(SkAlphaType.kOpaque, test.getAlphaType());
      assertNotNull(test.getColorSpace());

      try {
         test.close();
      } catch (Exception e) {
         fail("Exception not expected");
      }
   }

}
