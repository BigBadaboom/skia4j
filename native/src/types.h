/*
 * Copyright 2015 Google Inc.
 *
 * Use of this source code is governed by a BSD-style license that can be
 * found in the LICENSE file.
 */

#ifndef sk_types_priv_DEFINED
#define sk_types_priv_DEFINED

class SkMaskFilter;
class SkPaint;
class SkShader;
class SkImage;
class SkCanvas;


static SkCanvas* AsCanvas(jlong ccanvas) {
   return reinterpret_cast<SkCanvas*>(ccanvas);
}

static jlong ToCanvas(SkCanvas* canvas) {
    return reinterpret_cast<jlong>(canvas);
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
    return reinterpret_cast<const SkImage*>(cimage);
}

static jlong ToImage(SkImage* cimage) {
    return reinterpret_cast<jlong>(cimage);
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
    return reinterpret_cast<SkPaint*>(cpaint);
}

static jlong ToPaint(SkPaint* cpaint) {
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


static unsigned char  u8clamp(int val) {
	return (val < 0) ? 0 : (val > 255) ? 255 : static_cast<unsigned char>(val);
}


#endif
