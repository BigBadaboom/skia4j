package com.caverock.skia4j;

public enum SkAlphaType
{
   kUnknown,   //!< uninitialized
   kOpaque,    //!< pixel is opaque
   kPremul,    //!< pixel components are premultiplied by alpha
   kUnpremul;  //!< pixel components are independent of alpha

   static final SkAlphaType values[] = values();

   // Convert an integer ordinal value to an enum entry.
   static SkAlphaType  from(int ordinal) {
      return (ordinal >= 0 && ordinal < values.length) ? values[ordinal] : kUnknown;
   }
}
