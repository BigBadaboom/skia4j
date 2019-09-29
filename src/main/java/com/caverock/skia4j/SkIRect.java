package com.caverock.skia4j;

public class SkIRect
{
   private int left;
   private int top;
   private int right;
   private int bottom;


   public SkIRect(int left, int top, int right, int bottom)
   {
      this.left = left;
      this.top = top;
      this.right = right;
      this.bottom = bottom;
   }


   public int getLeft()
   {
      return left;
   }

   public void setLeft(int x)
   {
      this.left = x;
   }


   public int getTop()
   {
      return top;
   }

   public void setTop(int y)
   {
      this.top = y;
   }


   
   public int getRight()
   {
      return right;
   }

   public void setRight(int right)
   {
      this.right = right;
   }


   public int getBottom()
   {
      return bottom;
   }

   public void setBottom(int bottom)
   {
      this.bottom = bottom;
   }


   //--------------------------------------------------------------------------


   public int getWidth()
   {
      return right - left;
   }


   public int getHeight()
   {
      return bottom - top;
   }


   public void setLTRB(int left, int top, int right, int bottom)
   {
      this.left = left;
      this.top = top;
      this.right = right;
      this.bottom = bottom;
   }


   public void setXYWH(int x, int y, int width, int height)
   {
      this.left = x;
      this.top = y;
      this.right = x + width;
      this.bottom = y + height;
   }

}
