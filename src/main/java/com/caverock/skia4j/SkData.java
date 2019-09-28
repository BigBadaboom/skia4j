package com.caverock.skia4j;


/**
 * SkData represents an immutable data buffer.
 */
public class SkData implements AutoCloseable
{
   // Reference to the native sk_image object
   private long  nRef = 0;

   // Keep a record of the surface metadata
   private int  size = 0;


   //--------------------------------------------------------------------------


   protected SkData(long ref)
   {
      this.nRef = ref;
      this.size = nSkDataGetSize(ref);
   }


   /**
    * Tidy up the native resource associated with this class.
    * Must be called, when this object is no longer needed, to avoid memory leaks.
    *
    * You can call close() directly, or use try-with-resources.
   */
   @Override
   public void  close() throws Exception
   {
      nSkDataUnref(nativeRef());
      nRef = 0;
   }


   protected long  nativeRef()
   {
      if (nRef == 0)
         throw new IllegalArgumentException("This SkData object has already been released.");
      return nRef;
   }


   @Override
   public String toString()
   {
      return String.format("%s size=%d (ref:%x)", getClass().getSimpleName(), size, nRef);
   }


   //--------------------------------------------------------------------------


   /**
    * The size of the data in this SkData object.
    * @return
    */
   public int  size()
   {
      return this.size();
   }


   /**
    * Copy the data from this SkData object to a Java byte array.
    * @return the byte array
    */
   public byte[]  toByteArray()
   {
      if (nRef == 0)
         throw new IllegalArgumentException("This SkData object has already been released.");
      return nSkDataToByteArray(nRef);
   }





   //--------------------------------------------------------------------------
   // Native methods
   // include/c/sk_paint.h

   /**
   Returns a new empty sk_data_t.  This call must be balanced with a call to
   sk_data_unref().
    */
   //SK_API sk_data_t* sk_data_new_empty(void);

   /**
   Returns a new sk_data_t by copying the specified source data.
   This call must be balanced with a call to sk_data_unref().
    */
   //SK_API sk_data_t* sk_data_new_with_copy(const void* src, size_t length);

   /**
   Pass ownership of the given memory to a new sk_data_t, which will
   call free() when the refernce count of the data goes to zero.  For
   example:
       size_t length = 1024;
       void* buffer = malloc(length);
       memset(buffer, 'X', length);
       sk_data_t* data = sk_data_new_from_malloc(buffer, length);
   This call must be balanced with a call to sk_data_unref().
    */
   //SK_API sk_data_t* sk_data_new_from_malloc(const void* memory, size_t length);

   /**
   Returns a new sk_data_t using a subset of the data in the
   specified source sk_data_t.  This call must be balanced with a
   call to sk_data_unref().
    */
   //SK_API sk_data_t* sk_data_new_subset(const sk_data_t* src, size_t offset, size_t length);

   /**
   Increment the reference count on the given sk_data_t. Must be
   balanced by a call to sk_data_unref().
    */
   //SK_API void sk_data_ref(const sk_data_t*);
   native private static void  nSkDataRef(long data);

   /**
   Decrement the reference count. If the reference count is 1 before
   the decrement, then release both the memory holding the sk_data_t
   and the memory it is managing.  New sk_data_t are created with a
   reference count of 1.
    */
   //SK_API void sk_data_unref(const sk_data_t*);
   native private static void  nSkDataUnref(long data);

   /**
   Returns the number of bytes stored.
    */
   //SK_API size_t sk_data_get_size(const sk_data_t*);
   native private static int  nSkDataGetSize(long data);

   /**
   Returns the pointer to the data.
    */
   //SK_API const void* sk_data_get_data(const sk_data_t*);
   //native private static long  nSkDataGetData(long data);

   
   /*
    * Convert the data from an SkData object into a byte array
    */
   native private static byte[]  nSkDataToByteArray(long data);

}
