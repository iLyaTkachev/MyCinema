package ilyatkachev.github.com.mycinema.data.remote.gson2;

import org.json.JSONException;

import java.util.List;

public interface ICinemaParser <T extends IBaseCinemaObject> {
    T parse(String pLine, T object) throws Exception;
}
