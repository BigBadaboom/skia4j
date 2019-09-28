package com.caverock.skia4j;

public enum SkColorType
{
   kUnknown,       //!< uninitialized
   kAlpha_8,       //!< pixel with alpha in 8-bit byte
   kRGB_565,       //!< pixel with 5 bits red, 6 bits green, 5 bits blue, in 16-bit word
   kARGB_4444,     //!< pixel with 4 bits for alpha, red, green, blue; in 16-bit word
   kRGBA_8888,     //!< pixel with 8 bits for red, green, blue, alpha; in 32-bit word
   kRGB_888x,      //!< pixel with 8 bits each for red, green, blue; in 32-bit word
   kBGRA_8888,     //!< pixel with 8 bits for blue, green, red, alpha; in 32-bit word
   kRGBA_1010102,  //!< 10 bits for red, green, blue; 2 bits for alpha; in 32-bit word
   kRGB_101010x,   //!< pixel with 10 bits each for red, green, blue; in 32-bit word
   kGray_8,        //!< pixel with grayscale level in 8-bit byte
   kRGBA_F16Norm,  //!< pixel with half floats in [0,1] for red, green, blue, alpha; in 64-bit word
   kRGBA_F16,      //!< pixel with half floats for red, green, blue, alpha; in 64-bit word
   kRGBA_F32;      //!< pixel using C float for red, green, blue, alpha; in 128-bit word

   static final SkColorType values[] = values();

   // Convert an integer ordinal value to an enum entry.
   static SkColorType  from(int ordinal) {
      return (ordinal >= 0 && ordinal < values.length) ? values[ordinal] : kUnknown;
   }
}
