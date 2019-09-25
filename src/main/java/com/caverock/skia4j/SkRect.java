package com.caverock.skia4j;

public class SkRect
{
   private float left;
   private float top;
   private float right;
   private float bottom;


   public SkRect(float left, float top, float right, float bottom)
   {
      this.left = left;
      this.top = top;
      this.right = right;
      this.bottom = bottom;
   }


   public float getLeft()
   {
      return left;
   }

   public void setLeft(float x)
   {
      this.left = x;
   }


   public float getTop()
   {
      return top;
   }

   public void setTop(float y)
   {
      this.top = y;
   }


   
   public float getRight()
   {
      return right;
   }

   public void setRight(float right)
   {
      this.right = right;
   }


   public float getBottom()
   {
      return bottom;
   }

   public void setBottom(float bottom)
   {
      this.bottom = bottom;
   }


   public float getWidth()
   {
      return right - left;
   }


   public float getHeight()
   {
      return bottom - top;
   }

   
}
