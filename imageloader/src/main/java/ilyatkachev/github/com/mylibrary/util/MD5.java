package ilyatkachev.github.com.mylibrary.util;


import android.util.Log;

import java.security.NoSuchAlgorithmException;

public final class MD5 {

    public static String hash(final String value) {
        try {
            final java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            final byte[] array = md.digest(value.getBytes());
            final StringBuilder sb = new StringBuilder();
            for (final byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (final NoSuchAlgorithmException e) {
            Log.e("MD5", "hash: ", e);
        }
        return value;
    }
}
