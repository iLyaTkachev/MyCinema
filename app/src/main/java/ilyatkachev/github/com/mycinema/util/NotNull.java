package ilyatkachev.github.com.mycinema.util;

public class NotNull {
    public static <T> T check(T reference) {
        if(reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }
}
