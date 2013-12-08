package fr.redteam.dressyourself.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.os.Environment;
import android.util.AndroidException;
import fr.redteam.dressyourself.exceptions.DressyourselfIOException;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;


/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
public class AndroidFileManager {

  /**
   * Cette méthode indique si la mémoire interne du device est accessible AU MOINS en lecture
   * 
   * @return
   */
  private static boolean isExternalStorageReadAccess() {
    String state = Environment.getExternalStorageState();

    return Environment.MEDIA_MOUNTED.equals(state)
        || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
  }

  /**
   * Cette méthode indique si la mémoire interne du device est accessible en lecture ET en écriture
   * 
   * @return
   */
  private static boolean isExternalStorageReadWriteAccess() {
    String state = Environment.getExternalStorageState();

    return Environment.MEDIA_MOUNTED.equals(state);
  }

  /**
   * 
   * @param context
   * @param imageDirectory
   * @param imageStream
   * @throws AndroidException
   */
  protected static void writeFileToExternalStorage(Context context, String imagePath,
      InputStream imageStream) {
    if (!isExternalStorageReadWriteAccess()) {
      throw new DressyourselfIOException("External storage need to be in READ/WRITE access");
    }

    if (imagePath.endsWith("/")) {
      throw new DressyourselfRuntimeException("imageRelitivePath should not be ended by a '/'");
    }

    // HACK: used a temporary File object to slip path and file name
    File fullPath = new File(imagePath);

    // relative directory from the root of the storage
    File dir = new File(context.getExternalFilesDir(null), fullPath.getParent());
    dir.mkdirs();

    File file = new File(dir, fullPath.getName());
    FileOutputStream outStream = null;

    try {
      file.createNewFile();
      outStream = new FileOutputStream(file);
      byte[] data = new byte[imageStream.available()];

      imageStream.read(data);
      outStream.write(data);

    } catch (IOException e) {
      throw new DressyourselfIOException(e);
    } finally {
      // always close stream
      try {
        imageStream.close();
        outStream.close();
      } catch (IOException e) {
        throw new DressyourselfIOException(e);
      } catch (NullPointerException e) {
        throw new DressyourselfRuntimeException(e);
      }

    }
  }

  /**
   * 
   * @param context
   * @param imagePath
   * @param imageStream
   * @return
   */
  protected static File loadFileFromExternalStorage(Context context, String imagePath) {
    if (!isExternalStorageReadAccess()) {
      throw new DressyourselfIOException("External storage need to be in READ (minimum) access");
    }

    // writing image to external storage (local to us application)
    File file = new File(context.getExternalFilesDir(null), imagePath);
    
    // verify if the file really exists
    try {
      file.exists();
    } catch(NullPointerException e) {
      throw new DressyourselfIOException(e);
    }
    
    return file;
  }

  /**
   * 
   * @param context
   * @param imagePath
   */
  protected static void deleteFileFromExternalStorage(Context context, String imagePath) {
    if (!isExternalStorageReadWriteAccess()) {
      throw new DressyourselfIOException("External storage need to be in READ & WRITE access");
    }

    // writing image to external storage (local to us application)
    File file = new File(context.getExternalFilesDir(null), imagePath);

    // delete if the file exists
    try {
      file.delete();
    } catch (NullPointerException e) {
      throw new DressyourselfIOException(e);
    }
  }
}

