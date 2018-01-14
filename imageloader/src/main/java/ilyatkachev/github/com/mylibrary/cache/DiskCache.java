package ilyatkachev.github.com.mylibrary.cache;

import android.graphics.Bitmap;

import java.io.File;
import java.io.IOException;

public interface DiskCache {

	File get(String imageUri) throws IOException;

	void save(String imageUri, Bitmap bitmap) throws IOException;
}
