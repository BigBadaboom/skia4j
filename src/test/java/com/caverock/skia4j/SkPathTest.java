package com.caverock.skia4j;

import java.io.File;

import junit.framework.TestCase;

public class SkPathTest extends TestCase
{

   @Override
   protected void setUp() throws Exception
   {
      super.setUp();
      System.load(Config.LIBRARY_FILE);
   }


   public void testPathEquality() throws Exception
   {
      SkPath path = new SkPath();
      assertTrue(path.equals(path));
      
      SkPath path2 = new SkPath();
      assertTrue(path.equals(path2));

      path2.moveTo(0, 0);
      assertFalse(path.equals(path2));

      path.moveTo(0, 0);
      assertTrue(path.equals(path2));

      path.reset();
      path2.rewind();
      assertTrue(path.equals(path2));
   }


   public void testPathEmpty() throws Exception
   {
      SkPath path = new SkPath();
      assertTrue(path.isEmpty());
      
      path.moveTo(0, 0);
      assertFalse(path.isEmpty());

      path.reset();
      assertTrue(path.isEmpty());

      path.moveTo(0, 0);
      assertFalse(path.isEmpty());

      path.rewind();
      assertTrue(path.isEmpty());
   }


   
}
