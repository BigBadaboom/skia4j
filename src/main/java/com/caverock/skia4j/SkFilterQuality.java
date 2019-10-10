package com.caverock.skia4j;

/**
 *  Controls how much filtering to be done when scaling/transforming complex colors
 *  e.g. images
 */
public enum SkFilterQuality
{
   /**
    * Fastest but lowest quality, typically nearest-neighbor
    */
   kNone, 
   /**
    * Typically bilerp
    */
   kLow,
   /**
    * Typically bilerp + mipmaps for down-scaling
    */
   kMedium, 
   /**
    * Slowest but highest quality, typically bicubic or better
    */
   kHigh 
}
