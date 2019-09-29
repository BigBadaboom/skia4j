/*
 * Copyright 2015 Google Inc.
 *
 * Use of this source code is governed by a BSD-style license that can be
 * found in the LICENSE file.
 */

#ifndef sk_types_priv_DEFINED
#define sk_types_priv_DEFINED

class SkCanvas;
class SkColorSpace;
class SkData;
class SkImage;
class SkImageInfo;
class SkMaskFilter;
class SkPaint;
class SkShader;
class SkSurface;
class SkSurfaceProps;


static inline SkCanvas* AsCanvas(jlong ccanvas) {
	if (ccanvas == 0)
		return NULL;
   return reinterpret_cast<SkCanvas*>(ccanvas);
}

static inline jlong ToCanvas(SkCanvas* ccanvas) {
	if (ccanvas == NULL)
		return 0;
   return reinterpret_cast<jlong>(ccanvas);
}


static inline SkColorSpace* AsColorSpace(jlong cspace) {
	if (cspace == 0)
		return NULL;
   return reinterpret_cast<SkColorSpace*>(cspace);
}

static inline jlong ToColorSpace(SkColorSpace* cspace) {
	if (cspace == NULL)
		return 0;
   return reinterpret_cast<jlong>(cspace);
}


static inline SkData* AsData(jlong cdata) {
	if (cdata == 0)
		return NULL;
   return reinterpret_cast<SkData*>(cdata);
}

static inline jlong ToData(SkData* cdata) {
	if (cdata == NULL)
		return 0;
   return reinterpret_cast<jlong>(cdata);
}


static inline SkImage* AsImage(jlong cimage) {
	if (cimage == 0)
		return NULL;
   return reinterpret_cast<SkImage*>(cimage);
}

static inline jlong ToImage(SkImage* cimage) {
	if (cimage == NULL)
		return 0;
   return reinterpret_cast<jlong>(cimage);
}


static inline SkImageInfo* AsImageInfo(jlong cinfo) {
	if (cinfo == 0)
		return NULL;
   return reinterpret_cast<SkImageInfo*>(cinfo);
}

static inline jlong ToImageInfo(SkImageInfo* cinfo) {
	if (cinfo == NULL)
		return 0;
   return reinterpret_cast<jlong>(cinfo);
}


static inline SkSurfaceProps* AsSurfaceProps(jlong cprops) {
	if (cprops == 0)
		return NULL;
   return reinterpret_cast<SkSurfaceProps*>(cprops);
}


static inline SkPaint* AsPaint(jlong cpaint) {
	if (cpaint == 0)
		return NULL;
   return reinterpret_cast<SkPaint*>(cpaint);
}

static inline jlong ToPaint(SkPaint* cpaint) {
	if (cpaint == NULL)
		return 0;
   return reinterpret_cast<jlong>(cpaint);
}


static inline SkSurface* AsSurface(jlong csurface) {
	if (csurface == 0)
		return NULL;
   return reinterpret_cast<SkSurface*>(csurface);
}

static inline jlong ToSurface(SkSurface* csurface) {
	if (csurface == NULL)
		return 0;
   return reinterpret_cast<jlong>(csurface);
}



static inline unsigned char  u8clamp(int val) {
	return (val < 0) ? 0 : (val > 255) ? 255 : static_cast<unsigned char>(val);
}


#endif
