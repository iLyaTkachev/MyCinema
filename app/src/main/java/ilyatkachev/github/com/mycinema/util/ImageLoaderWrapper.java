package ilyatkachev.github.com.mycinema.util;

import android.widget.ImageView;

import java.io.File;

import ilyatkachev.github.com.mylibrary.ImageLib;

public final class ImageLoaderWrapper {

    public static void loadImage(final String pPath, final ImageView pImageView) {
        ImageLib.getInstance().load(pPath).into(pImageView);
    }
    public static void setConfig(final File pCacheDir) {
        ImageLib.getInstance().setConfig(new ImageLib.Config(pCacheDir));
    }
}
