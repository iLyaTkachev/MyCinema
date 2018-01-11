package ilyatkachev.github.com.mycinema.data.remote.gson;

import com.google.gson.Gson;

public class CinemaObjectParser implements ICinemaParser {

    @Override
    public IBaseCinemaObject parse(String pLine, IBaseCinemaObject object) throws Exception {
        final IBaseCinemaObject result = new Gson().fromJson(pLine, object.getClass());
        return result;
    }
}
