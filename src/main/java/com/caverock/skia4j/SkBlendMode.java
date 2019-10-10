package com.caverock.skia4j;

public enum SkBlendMode
{
   // Porter-Duff blend modes
   kClear,                           //!< replaces destination with zero: fully transparent
   kSrc,                             //!< replaces destination
   kDst,                             //!< preserves destination
   kSrcOver,                         //!< source over destination
   kDstOver,                         //!< destination over source
   kSrcIn,                           //!< source trimmed inside destination
   kDstIn,                           //!< destination trimmed by source
   kSrcOut,                          //!< source trimmed outside destination
   kDstOut,                          //!< destination trimmed outside source
   kSrcATop,                         //!< source inside destination blended with destination
   kDstATop,                         //!< destination inside source blended with source
   kXor,                             //!< each of source and destination trimmed outside the other
   kPlus,                            //!< sum of colors
   kModulate,                        //!< product of premultiplied colors; darkens destination
   kScreen,                          //!< multiply inverse of pixels, inverting result; brightens destination
   
   // Other blend modes
   kOverlay,                         //!< multiply or screen, depending on destination
   kDarken,                          //!< darker of source and destination
   kLighten,                         //!< lighter of source and destination
   kColorDodge,                      //!< brighten destination to reflect source
   kColorBurn,                       //!< darken destination to reflect source
   kHardLight,                       //!< multiply or screen, depending on source
   kSoftLight,                       //!< lighten or darken, depending on source
   kDifference,                      //!< subtract darker from lighter with higher contrast
   kExclusion,                       //!< subtract darker from lighter with lower contrast
   kMultiply,                        //!< multiply source with destination, darkening image

   kHue,                             //!< hue of source with saturation and luminosity of destination
   kSaturation,                      //!< saturation of source with hue and luminosity of destination
   kColor,                           //!< hue and saturation of source with luminosity of destination
   kLuminosity,                      //!< luminosity of source with hue and saturation of destination
}
