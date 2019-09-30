package com.caverock.skia4j;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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


   SkData(long ref)
   {
      this.nRef = ref;
      this.size = nSkDataGetSize(ref);
   }


   SkData(long ref, int size)
   {
      this.nRef = ref;
      this.size = size;
   }


   //--------------------------------------------------------------------------


   /**
    * Create a new SkData by copying the specified data.
    * 
    * @param data an array of bytes
    * @return new SkData object
    */
   public static SkData  makeWithCopy(byte[] data)
   {
      long ref = nSkMakeWithCopy(data, 0, data.length);
      if (ref == 0)
         return null;

      return new SkData(ref, data.length);
   }


   /**
    * Create a new SkData by copying the specified data.
    * 
    * @param data an array of bytes
    * @param offset the offset into the buffer to start the copy from
    * @param length the number of bytes to copy
    * @return new SkData object
    */
   public static SkData  makeWithCopy(byte[] data, int offset, int length)
   {
      if (data == null || data.length < (offset + length))
         return null;

      long  ref = nSkMakeWithCopy(data, offset, length);
      if (ref == 0)
         return null;
      return new SkData(ref, length);
   }


   /**
    * Create a new SkData from the contents of the specified file.
    * 
    * @param filename the name of the file to read
    * @return new SkData object, or null if the file could not be found or read.
    */
   public static SkData  makeFromFileName(String filename)
   {
      long  ref = nSkMakeFromFileName(filename);
      if (ref == 0)
         return null;
      return new SkData(ref);
   }


   /**
    * Create a new SkData from the contents of the specified file.
    * 
    * @param file File object to read
    * @return new SkData object, or null if the file could not be found or read.
    */
   public static SkData  makeFromFile(File file)
   {
      if (file == null || !file.exists() || !file.isFile())
         return null;

      long  ref = nSkMakeFromFileName(file.getAbsolutePath());
      if (ref == 0)
         return null;
      return new SkData(ref);
   }


   /**
    * Create a new SkData from the contents of the specified input stream.
    * 
    * Caller is responsible for closing the stream if necessary.
    * 
    * @param input the input stream
    * @param size the number of bytes to read from the stream
    * @return
    * @throws EOFException if this input stream reaches the end before reading all the bytes.
    * @throws IOException the stream has been closed and the contained input stream does not support reading after close, or another I/O error occurs.
    */
   public static SkData  makeFromStream(InputStream input, int size) throws IOException
   {
      if (size < 0)
         throw new IllegalArgumentException("size must be positive");

      byte[]  buf = new byte[size];
      DataInputStream  dis = new DataInputStream(input);
      dis.readFully(buf);
      
      return makeWithCopy(buf);
   }

   
   //--------------------------------------------------------------------------


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
      return this.size;
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

   native private static long  nSkMakeWithCopy(byte[] data, int offset, int length);
   native private static long  nSkMakeFromFileName(String filename);

   native private static void  nSkDataRef(long data);
   native private static void  nSkDataUnref(long data);

   native private static int  nSkDataGetSize(long data);

   native private static byte[]  nSkDataToByteArray(long data);

}
