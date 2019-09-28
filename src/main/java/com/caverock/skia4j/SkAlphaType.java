package com.caverock.skia4j;

public enum SkAlphaType
{
   kUnknown,   //!< uninitialized
   kOpaque,    //!< pixel is opaque
   kPremul,    //!< pixel components are premultiplied by alpha
   kUnpremul   //!< pixel components are independent of alpha
}
