package com.caverock.skia4j;

/*
 * Used in {@code SkImage#encodeAsData()}.
 * 
 * Note that Skia4J has support for PNG, JPEG and WEBP only. 
 */
public enum SkEncodedImageFormat
{
   kBMP,    
   kGIF,    
   kICO,    
   kJPEG,   
   kPNG,    
   kWBMP,   
   kWEBP,   
   kPKM,    
   kKTX,    
   kASTC,   
   kDNG,   
   kHEIF
}
