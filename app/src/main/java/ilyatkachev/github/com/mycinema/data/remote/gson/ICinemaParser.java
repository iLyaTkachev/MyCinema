package ilyatkachev.github.com.mycinema.data.remote.gson;

public interface ICinemaParser <T extends IBaseCinemaObject> {
    T parse(String pLine, T object) throws Exception;
}
