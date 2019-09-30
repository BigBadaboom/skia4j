package com.caverock.skia4j;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

public class SkDataTest extends TestCase
{
   private File  mandrillFile;


   @Override
   protected void setUp() throws Exception
   {
      super.setUp();
      System.load(Config.LIBRARY_FILE);
      
      mandrillFile = new File(Config.MANDRILL_PNG);
   }


   public void  testMakeWithCopy()
   {
      byte[] buf = new byte[42];
      SkData data = SkData.makeWithCopy(buf);
      assertNotNull(data);
      assertEquals(42, data.size());
   }


   public void  testMakeWithCopy2()
   {
      byte[] buf = new byte[42];
      try (SkData data = SkData.makeWithCopy(buf,10,20); ) {
         assertNotNull(data);
         assertEquals(20, data.size());
      } catch (Exception e) {
         fail("Unexpected exception");
      }
      
      try (SkData data = SkData.makeWithCopy(buf,42,1);) {
         assertNull(data);
      } catch (Exception e) {
         fail("Unexpected exception");
      }
   }


   public void  testMakeFromFileName()
   {
      SkData data = SkData.makeFromFileName(mandrillFile.getAbsolutePath());
      assertNotNull(data);
      assertEquals(40561, data.size());
   }


   public void  testMakeFromFile()
   {
      SkData data = SkData.makeFromFile(mandrillFile);
      assertNotNull(data);
      assertEquals(40561, data.size());
   }


   public void  testMakeFromStream()
   {
      try {
         FileInputStream  fis = new FileInputStream(mandrillFile);
         SkData data = SkData.makeFromStream(fis, (int)mandrillFile.length());
         assertNotNull(data);
         assertEquals(40561, data.size());
         fis.close();
      } catch (Exception e) {
         fail("Unexpected exception");
      }
   }


}
