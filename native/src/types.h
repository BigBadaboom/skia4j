/*
 * Copyright 2015 Google Inc.
 *
 * Use of this source code is governed by a BSD-style license that can be
 * found in the LICENSE file.
 */

#ifndef sk_types_priv_DEFINED
#define sk_types_priv_DEFINED

class SkCanvas;
class SkImage;
class SkImageInfo;
class SkMaskFilter;
class SkPaint;
class SkShader;
class SkSurface;
class SkSurfaceProps;


static SkCanvas* AsCanvas(jlong ccanvas) {
	if (ccanvas == 0)
		return NULL;
   return reinterpret_cast<SkCanvas*>(ccanvas);
}

static jlong ToCanvas(SkCanvas* ccanvas) {
	if (ccanvas == NULL)
		return 0;
   return reinterpret_cast<jlong>(ccanvas);
}


static SkColorSpace* AsColorSpace(jlong cspace) {
	if (cspace == 0)
		return NULL;
   return reinterpret_cast<SkColorSpace*>(cspace);
}

static jlong ToColorSpace(SkColorSpace* cspace) {
	if (cspace == NULL)
		return 0;
   return reinterpret_cast<jlong>(cspace);
}


/*
static SkData* AsData(const sk_data_t* cdata) {
    return reinterpret_cast<SkData*>(const_cast<sk_data_t*>(cdata));
}

static sk_data_t* ToData(SkData* data) {
    return reinterpret_cast<sk_data_t*>(data);
}
*/

static const SkImage* AsImage(jlong cimage) {
	if (cimage == 0)
		return NULL;
   return reinterpret_cast<const SkImage*>(cimage);
}

static jlong ToImage(SkImage* cimage) {
	if (cimage == NULL)
		return 0;
   return reinterpret_cast<jlong>(cimage);
}


static inline SkImageInfo* AsImageInfo(jlong cinfo) {
	if (cinfo == 0)
		return NULL;
   return reinterpret_cast<SkImageInfo*>(cinfo);
}

static jlong ToImageInfo(SkImageInfo* cinfo) {
	if (cinfo == NULL)
		return 0;
   return reinterpret_cast<jlong>(cinfo);
}


static inline SkSurfaceProps* AsSurfaceProps(jlong cprops) {
	if (cprops == 0)
		return NULL;
   return reinterpret_cast<SkSurfaceProps*>(cprops);
}


/*
static inline SkMaskFilter* AsMaskFilter(sk_maskfilter_t* cfilter) {
    return reinterpret_cast<SkMaskFilter*>(cfilter);
}

static inline sk_maskfilter_t* ToMaskFilter(SkMaskFilter* filter) {
    return reinterpret_cast<sk_maskfilter_t*>(filter);
}

static inline const SkPaint& AsPaint(const sk_paint_t& cpaint) {
    return reinterpret_cast<const SkPaint&>(cpaint);
}

static inline const SkPaint* AsPaint(const sk_paint_t* cpaint) {
    return reinterpret_cast<const SkPaint*>(cpaint);
}
*/

static inline SkPaint* AsPaint(jlong cpaint) {
	if (cpaint == 0)
		return NULL;
   return reinterpret_cast<SkPaint*>(cpaint);
}

static jlong ToPaint(SkPaint* cpaint) {
	if (cpaint == NULL)
		return 0;
   return reinterpret_cast<jlong>(cpaint);
}

/*
static inline SkShader* AsShader(sk_shader_t* cshader) {
    return reinterpret_cast<SkShader*>(cshader);
}

static sk_rect_t ToRect(const SkRect& rect) {
    return reinterpret_cast<const sk_rect_t&>(rect);
}

static const SkRect& AsRect(const sk_rect_t& crect) {
    return reinterpret_cast<const SkRect&>(crect);
}

static const SkPath& AsPath(const sk_path_t& cpath) {
    return reinterpret_cast<const SkPath&>(cpath);
}

static SkPath* as_path(sk_path_t* cpath) {
    return reinterpret_cast<SkPath*>(cpath);
}

static SkPictureRecorder* AsPictureRecorder(sk_picture_recorder_t* crec) {
    return reinterpret_cast<SkPictureRecorder*>(crec);
}

/*
static sk_picture_recorder_t* ToPictureRecorder(SkPictureRecorder* rec) {
    return reinterpret_cast<sk_picture_recorder_t*>(rec);
}

static const SkPicture* AsPicture(const sk_picture_t* cpic) {
    return reinterpret_cast<const SkPicture*>(cpic);
}

static SkPicture* AsPicture(sk_picture_t* cpic) {
    return reinterpret_cast<SkPicture*>(cpic);
}

static sk_picture_t* ToPicture(SkPicture* pic) {
    return reinterpret_cast<sk_picture_t*>(pic);
}
*/

static inline SkSurface* AsSurface(jlong csurface) {
	if (csurface == 0)
		return NULL;
   return reinterpret_cast<SkSurface*>(csurface);
}

static jlong ToSurface(SkSurface* csurface) {
	if (csurface == NULL)
		return 0;
   return reinterpret_cast<jlong>(csurface);
}



static unsigned char  u8clamp(int val) {
	return (val < 0) ? 0 : (val > 255) ? 255 : static_cast<unsigned char>(val);
}


#endif
