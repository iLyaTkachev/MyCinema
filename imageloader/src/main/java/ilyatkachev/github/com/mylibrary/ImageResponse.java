package ilyatkachev.github.com.mylibrary;


import android.graphics.Bitmap;

public class ImageResponse {

    private final ImageRequest request;
    private Bitmap bitmap;
    private Exception exception;

    public ImageResponse(ImageRequest request) {
        this.request = request;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public ImageRequest getRequest() {
        return request;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Exception getException() {
        return exception;
    }
}
