package ilyatkachev.github.com.mylibrary;


import android.widget.ImageView;

import java.lang.ref.WeakReference;

class ImageRequest {
    String url;
    WeakReference<ImageView> target;
    int width;
    int height;

    private ImageRequest(Builder builder) {
        url = builder.url;
        target = builder.target;
    }

    public static final class Builder {
        private final ImageLib mImageLib;
        private String url;
        private WeakReference<ImageView> target;

        Builder(ImageLib pImageLib) {
            this.mImageLib = pImageLib;
        }

        Builder load(String val) {
            url = val;
            return this;
        }

        public void into(ImageView val) {
            target = new WeakReference<>(val);
            mImageLib.enqueue(this.build());
        }

        ImageRequest build() {
            return new ImageRequest(this);
        }
    }
}
