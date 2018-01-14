package ilyatkachev.github.com.mylibrary.cache;

import android.graphics.Bitmap;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;

import ilyatkachev.github.com.mylibrary.util.IOUtils;
import ilyatkachev.github.com.mylibrary.util.MD5;

public class BaseDiskCache implements DiskCache {

    private static final String TAG = "DiskCache";
    private static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;
    private static final int DEFAULT_COMPRESS_QUALITY = 80;
    private static final int BUFFER_SIZE = 4096;
    private static final String IMAGE_CACHE_DIR_NAME = "IMAGE_CACHE";
    private static final int MIN_DISK_CACHE_SIZE = 5 * 1024 * 1024;
    private static final int MAX_DISK_CACHE_SIZE = 50 * 1024 * 1024;
    private final File cacheDir;
    private final long cacheSize;

    public BaseDiskCache(final File pCacheDir) {
        //this.cacheDir = cacheDir;
        cacheDir = new File(pCacheDir.getPath(), IMAGE_CACHE_DIR_NAME);
        if (!cacheDir.exists()) {
            boolean mkdir = cacheDir.mkdirs();
            if (!mkdir) {
                throw new IllegalStateException("Can't create dir for images");
            }

        }
        cacheDir.setWritable(true);

        if (!cacheDir.canWrite()) {
            throw new IllegalStateException("Can't write into dir for images");
        }

        cacheSize = calculateDiskCacheSize(cacheDir);
        freeSpaceIfRequired();

    }

    @Override
    public File get(final String imageUri) throws IOException {
        final String fileName = MD5.hash(imageUri);
        File[] files = cacheDir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return fileName.equals(name);
            }
        });
        if (files != null && files.length == 1) {
            return files[0];
        } else {
            File file = new File(cacheDir, fileName);
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                Log.d("DiskCache", "get: file created :" + fileCreated);
            }
            return file;
        }
    }

    @Override
    public void save(final String imageUri, final Bitmap bitmap) throws IOException {
        File imageFile = get(imageUri);
        FileOutputStream out = new FileOutputStream(imageFile);
        OutputStream os = new BufferedOutputStream(out, BUFFER_SIZE);
        try {
            boolean savedSuccessfully = bitmap.compress(DEFAULT_COMPRESS_FORMAT, DEFAULT_COMPRESS_QUALITY, os);
            imageFile.setLastModified(System.currentTimeMillis());
        } finally {
            IOUtils.closeStream(os);
        }
    }

    private void freeSpaceIfRequired() {
        Log.d(TAG, "freeSpaceIfRequired() called");
        long currentCacheSize = getCurrentCacheSize();
        if (currentCacheSize > cacheSize) {
            File[] files = cacheDir.listFiles();
            Arrays.sort(files, new Comparator<File>() {

                @Override
                public int compare(File lhs, File rhs) {
                    return Long.valueOf(lhs.lastModified()).compareTo(rhs.lastModified());
                }
            });
            int i = 0;
            do {
                final File f = files[i];
                final long length = f.length();
                if (f.delete()) {
                    currentCacheSize -= length;
                }
                i++;
                Log.d(TAG, "freeSpaceIfRequired: after delete " + currentCacheSize);
            } while (currentCacheSize > cacheSize);
        }
        Log.d(TAG, "freeSpaceIfRequired() returned: " + currentCacheSize);
    }

    private long getCurrentCacheSize() {
        long currentSize = cacheDir.length();
        File[] files = this.cacheDir.listFiles();
        for (File f : files) {
            currentSize += f.length();
        }
        return currentSize;
    }

    static long calculateDiskCacheSize(final File dir) {
        long size = MIN_DISK_CACHE_SIZE;

        try {
            final StatFs statFs = new StatFs(dir.getAbsolutePath());
            final long available = ((long) statFs.getBlockCount()) * statFs.getBlockSize();
            size = available / 50;
        } catch (IllegalArgumentException ignored) {
        }

        return Math.max(Math.min(size, MAX_DISK_CACHE_SIZE), MIN_DISK_CACHE_SIZE);
    }
}
