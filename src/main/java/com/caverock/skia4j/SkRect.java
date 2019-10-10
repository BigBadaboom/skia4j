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


   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (o == null)
         return false;
      if (getClass() != o.getClass())
         return false;
      SkRect other = (SkRect) o;
      return left == other.left && top == other.top && right == other.right && bottom == other.bottom;
   }


   
   @Override
   public String toString()
   {
      return String.format("(%f %f %f %f, arg1)", left, top, right, bottom);
   }


   //--------------------------------------------------------------------------


   public static SkRect makeEmpty()
   {
      return new SkRect(0, 0, 0, 0);
   }


   public static SkRect makeWH(float w, float h)
   {
      return new SkRect(0f, 0f, w, h);
   }

   
   public static SkRect makeIWH(int w, int h)
   {
      return new SkRect(0f, 0f, (float) w, (float) h);
   }


   public static SkRect makeLTRB(float l, float t, float r, float b)
   {
      return new SkRect(l, t, r, b);
   }


   public static SkRect makeXYWH(float x, float y, float w, float h)
   {
      return new SkRect(x, y, x+w, y+h);
   }


   public boolean  isEmpty()
   {
      return !(left < right && top < bottom);
   }

   /**
    * Returns true if left is equal to or less than right, or if top is equal
    * to or less than bottom. Call {@link #sort()} to reverse rectangles with negative
    * width or height.
    *
    * @return  true if width or height are zero or positive
    */
   public boolean  isSorted()
   {
      return left <= right && top <= bottom;
   }


   /**
    * Returns average of left edge and right edge. Result does not change if SkRect
    * is sorted.
    * 
    * @return  midpoint on x-axis
    */
   public float  centerX()
   {
      // Adding halves to reduce the chance of overflow
      return left/2f + right/2;
   }

   /**
    * Returns average of top edge and bottom edge. Result does not change if SkRect
    * is sorted.
    *
    * @return  midpoint on y-axis
    */
   public float  centerY()
   {
      // Adding halves to reduce the chance of overflow
      return top/2f + bottom/2f;
   }


   /**
    * Sets SkRect to (0, 0, 0, 0).
    *
    * Many other rectangles are empty; if left is equal to or greater than right,
    * or if top is equal to or greater than bottom. Setting all members to zero
    * is a convenience, but does not designate a special empty rectangle.
    */
   public void setEmpty()
   {
      left = top = right = bottom = 0f;
   }


   /**
    * Sets SkRect to (left, top, right, bottom).
    * left and right are not sorted; left is not necessarily less than right.
    * top and bottom are not sorted; top is not necessarily less than bottom.
    *
    * @param left    the left value
    * @param top     the top value
    * @param right   the right value
    * @param bottom  the bottom value
    */
   public void  set(float left, float top, float right, float bottom)
   {
      this.left   = left;
      this.top    = top;
      this.right  = right;
      this.bottom = bottom;
   }


   /**
    * Behaves identically to {@link #set(float, float, float, float).
    *
    * @param left    the left value
    * @param top     the top value
    * @param right   the right value
    * @param bottom  the bottom value
    */
   public void  setLTRB(float left, float top, float right, float bottom)
   {
      set(left, top, right, bottom);
   }


   /**
    * Sets SkRect to (left, top, right, bottom).
    * 
    * All parameters are promoted from integer to float.
    * left and right are not sorted; left is not necessarily less than right.
    * top and bottom are not sorted; top is not necessarily less than bottom.
    *
    * @param left    the left value
    * @param top     the top value
    * @param right   the right value
    * @param bottom  the bottom value
    */
   public void  iset(int left, int top, int right, int bottom)
   {
      this.left   = (float) left;
      this.top    = (float) top;
      this.right  = (float) right;
      this.bottom = (float) bottom;
   }


   /**
    * Sets SkRect to (0, 0, width, height).
    * 
    * width and height may be zero or negative. width and height are promoted from
    * integer to float, large values may lose precision.
    *
    * @param width   the width value
    * @param height  the height value
    */
   public void  isetWH(int width, int height)
   {
      left = top = 0;
      right = (float) width;
      bottom = (float) height;
   }


   /**
    * Sets SkRect to (x, y, x + width, y + height).
    * Does not validate input; width or height may be negative.
    * 
    * @param x       stored in left
    * @param y       stored in top
    * @param width   added to x and stored in right
    * @param height  added to y and stored in bottom
    */
   public void  setXYWH(float x, float y, float width, float height)
   {
      left = x;
      top = y;
      right = x + width;
      bottom = y + height;
   }


   /**
    * Sets SkRect to (0, 0, width, height). Does not validate input;
    * width or height may be negative.
    * 
    * @param width   stored in right
    * @param height  stored in bottom
    */
   public void  setWH(float width, float height)
   {
      left = top = 0;
      right = width;
      bottom = height;
   }


   /**
    * Returns SkRect offset by (dx, dy).
    * <ul>
    * <li>If dx is negative, SkRect returned is moved to the left.
    * <li>If dx is positive, SkRect returned is moved to the right.
    * <li>If dy is negative, SkRect returned is moved upward.
    * <li>If dy is positive, SkRect returned is moved downward.
    * </ul>
    * 
    * @param dx  added to left and right
    * @param dy  added to top and bottom
    * @return    SkRect offset on axes, with original width and height
    */
   public SkRect  makeOffset(float dx, float dy)
   {
      return makeLTRB(left + dx, top + dy, right + dx, bottom + dy);
   }


   /** Returns SkRect, inset by (dx, dy).
    * <ul>
    * <li>If dx is negative, SkRect returned is wider.
    * <li>If dx is positive, SkRect returned is narrower.
    * <li>If dy is negative, SkRect returned is taller.
    * <li>If dy is positive, SkRect returned is shorter.
    * </ul>
    * 
    * @param dx  added to left and subtracted from right
    * @param dy  added to top and subtracted from bottom
    * @return    SkRect inset symmetrically left and right, top and bottom
    */
   public SkRect  makeInset(float dx, float dy)
   {
      return makeLTRB(left + dx, top + dy, right - dx, bottom - dy);
   }


   /**
    * Returns SkRect, outset by (dx, dy).
    * <ul>
    * <li>If dx is negative, SkRect returned is narrower.
    * <li>If dx is positive, SkRect returned is wider.
    * <li>If dy is negative, SkRect returned is shorter.
    * <li>If dy is positive, SkRect returned is taller.
    * </ul>
    * 
    * @param dx  subtracted from left and added to right
    * @param dy  subtracted from top and added to bottom
    * @return    SkRect outset symmetrically left and right, top and bottom
    */
   public SkRect  makeOutset(float dx, float dy)
   {
      return makeLTRB(left - dx, top - dy, right + dx, bottom + dy);
   }


   /**
    * Offsets SkRect by adding dx to left, right; and by adding dy to top, bottom.
    * <ul>
    * <li>If dx is negative, moves SkRect to the left.
    * <li>If dx is positive, moves SkRect to the right.
    * <li>If dy is negative, moves SkRect upward.
    * <li>If dy is positive, moves SkRect downward.
    * </ul>
    * 
    * @param dx  offset added to left and right
    * @param dy  offset added to top and bottom
    */
   public void  offset(float dx, float dy)
   {
      left   += dx;
      top    += dy;
      right  += dx;
      bottom += dy;
   }


   /**
    * Offsets SkRect so that left equals newX, and top equals newY. width and height
    * are unchanged.
    * 
    * @param newX  stored in left, preserving width
    * @param newY  stored in top, preserving height
    */
   public void  offsetTo(float newX, float newY)
   {
      right += newX - left;
      bottom += newY - top;
      left = newX;
      top = newY;
   }


   /**
    * Insets SkRect by (dx, dy).
    * <ul>
    * <li>If dx is positive, makes SkRect narrower.
    * <li>If dx is negative, makes SkRect wider.
    * <li>If dy is positive, makes SkRect shorter.
    * <li>If dy is negative, makes SkRect taller.
    * </ul>
    * 
    * @param dx  added to left and subtracted from right
    * @param dy  added to top and subtracted from bottom
    */
   public void  inset(float dx, float dy)
   {
      left   += dx;
      top    += dy;
      right  -= dx;
      bottom -= dy;
   }


   /**
    * Outsets SkRect by (dx, dy).
    * <ul>
    * <li>If dx is positive, makes SkRect wider.
    * <li>If dx is negative, makes SkRect narrower.
    * <li>If dy is positive, makes SkRect taller.
    * <li>If dy is negative, makes SkRect shorter.
    * </ul>
    * 
    * @param dx  subtracted to left and added from right
    * @param dy  subtracted to top and added from bottom
    */
   public void  outset(float dx, float dy)
   {
      inset(-dx, -dy);
   }



   private static boolean  intersects(float al, float at, float ar, float ab,
                                      float bl, float bt, float br, float bb) {
      float L = Float.max(al, bl);
      float R = Float.min(ar, br);
      float T = Float.max(at, bt);
      float B = Float.min(ab, bb);
      return L < R && T < B;
   }


   /**
    * Returns true if SkRect intersects this SkRect.
    *
    * @param left    x-axis minimum of constructed SkRect
    * @param top     y-axis minimum of constructed SkRect
    * @param right   x-axis maximum of constructed SkRect
    * @param bottom  y-axis maximum of constructed SkRect
    * @return        true if the given rectangle interects this SkRect
    */
   public boolean  intersects(float left, float top, float right, float bottom)
   {
      return intersects(this.left, this.top, this.right, this.bottom, left, top, right, bottom);
   }


   /**
    * Returns true if SkRect intersects r.
    * Returns false if either r or SkRect is empty, or do not intersect.
    *
    * @param r  SkRect to intersect
    * @return   true if r and SkRect have area in common
    */
   public boolean  intersects(final SkRect r)
    {
       return intersects(left, top, right, bottom, r.left, r.top, r.right, r.bottom);
    }


    /**
     * Returns true if a intersects b.
     * Returns false if either a or b is empty, or do not intersect.
     * 
     * @param a  SkRect to intersect
     * @param b  SkRect to intersect
     * @return   true if a and b have area in common
     */
    public static boolean  intersects(final SkRect a, final SkRect b)
    {
       return intersects(a.left, a.top, a.right, a.bottom,
                         b.left, b.top, b.right, b.bottom);
    }


   /**
    * If the specified rectangle intersects with this SkRect, then update this SkRect to
    * be the intersection of this rectangle and the specified rectangle.
    * 
    * @param left the left value of the other rectangle
    * @param top the top value of the other rectangle
    * @param right the right value of the other rectangle
    * @param bottom the bottom value of the other rectangle
    * @return true if the rectangles intersect
    */
   public boolean  intersect(float left, float top, float right, float bottom)
   {
      // Check intersection
      float L = Float.max(this.left, left);
      float R = Float.min(this.right, right);
      float T = Float.max(this.top, top);
      float B = Float.min(this.bottom, bottom);
      if (!(L < R && T < B))
         return false;

      setLTRB(L, T, R, B);
      return true;
   }


   /**
    * If the specified rectangle intersects with this SkRect, then update this SkRect to
    * be the intersection of this rectangle and the specified rectangle.
    * 
    * @param r the other rectangle
    * @return true if the rectangles intersect
    */
   public boolean  intersect(final SkRect r)
   {
      return intersect(r.left, r.top, r.right, r.bottom);
   }


   /**
    * If the two specified rectangles intersect, then update this SkRect to
    * be the intersection of the two rectangles.
    * 
    * @param a first rectangle
    * @param b second rectangle
    * @return true if the rectangles intersect
    */
   public boolean  intersect(final SkRect a, final SkRect b)
   {
      
      // Check intersection
      float L = Float.max(a.left, b.left);
      float R = Float.min(a.right, b.right);
      float T = Float.max(a.top, b.top);
      float B = Float.min(a.bottom, b.bottom);
      if (!(L < R && T < B))
         return false;

      setLTRB(L, T, R, B);
      return true;
   }


   /**
    * If the specified rectangle is not empty, then update this SkRect to
    * be the union of this and the specified rectangle.
    * 
    * @param left    the left value of the other rectangle
    * @param top     the top value of the other rectangle
    * @param right   the right value of the other rectangle
    * @param bottom  the bottom value of the other rectangle
    */
   public void  join(float left, float top, float right, float bottom)
   {
      // do nothing if the params are empty
      if (left >= right || top >= bottom) {
         return;
      }

      // if we are empty, just assign
      if (isEmpty()) {
         set(left, top, right, bottom);
      } else {
         this.left   = Float.min(this.left, left);
         this.top    = Float.min(this.top, top);
         this.right  = Float.max(this.right, right);
         this.bottom = Float.max(this.bottom, bottom);
      }
   }


   /**
    * Sets this SkRect to the union of itself and r.
    * 
    * Has no effect if r is empty. Otherwise, if this SkRect is empty, sets
    * SkRect to r.
    * 
    * @param r  the other SkRect
    */
   public void  join(final SkRect r)
   {
      join(r.left, r.top, r.right, r.bottom);
   }


   /**
    * Returns true if left <= x < right && top <= y < bottom.
    * Returns false if SkRect is empty.
    * 
    * @param x  test SkPoint x-coordinate
    * @param y  test SkPoint y-coordinate
    * @return   true if (x, y) is inside SkRect
    */
   public boolean  contains(float x, float y)
   {
      return x >= left && x < right && y >= top && y < bottom;
   }


   /**
    * Returns true if SkRect contains r.
    * Returns false if SkRect is empty or r is empty.
    * 
    * SkRect contains r when SkRect area completely includes r area.
    * 
    * @param r  SkRect the potentially contained other rect
    * @return   true if all sides of SkRect are outside r
    */
   public boolean  contains(final SkRect r)
   {
      return !r.isEmpty() && !this.isEmpty() &&
             left <= r.left && top <= r.top &&
             right >= r.right && bottom >= r.bottom;
   }


   /**
    * Swaps left and right if left is greater than right; and swaps
    * top and bottom if top is greater than bottom. Result may be empty;
    * width and height will be zero or positive.
    */
   public void  sort()
   {
      if (left > right) {
         float t = left; left = right; right = t;
      }
      if (top > bottom) {
         float t = top; top = bottom; bottom = t;
      }
   }


   /**
    * Returns SkRect with left and right swapped if left is greater than right; and
    * with top and bottom swapped if top is greater than bottom. Result may be empty;
    * and width and height will be zero or positive.
    *
    * @return  new SkRect containing the sorted values of this SkRect
    */
   public SkRect  makeSorted()
   {
      return makeLTRB(Math.min(left, right), Math.min(top, bottom),
                      Math.max(left, right), Math.max(top, bottom));
   }


}
