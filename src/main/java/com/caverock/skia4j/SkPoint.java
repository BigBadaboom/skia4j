package com.caverock.skia4j;

public class SkPoint
{
   private float  x;
   private float  y;

   
   public SkPoint(float x, float y)
   {
      super();
      this.x = x;
      this.y = y;
   }



   public float getX()
   {
      return x;
   }

   public void setX(float x)
   {
      this.x = x;
   }


   public float getY()
   {
      return y;
   }
   public void setY(float y)
   {
      this.y = y;
   }



   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (o == null)
         return false;
      if (getClass() != o.getClass())
         return false;
      SkPoint other = (SkPoint) o;
      return x == other.x && y == other.y;
   }


   
   @Override
   public String toString()
   {
      return String.format("(%f %f)", x, y);
   }


   //--------------------------------------------------------------------------


}
