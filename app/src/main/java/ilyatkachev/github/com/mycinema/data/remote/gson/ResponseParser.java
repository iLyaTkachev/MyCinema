package ilyatkachev.github.com.mycinema.data.remote.gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResponseParser {

    private CinemaObjectParser mCinemaObjectParser;

    public ResponseParser() {
        mCinemaObjectParser = new CinemaObjectParser();
    }

    public List parse(final String pJsonString, final IBaseCinemaObject pObject, final String pKey) throws Exception {
        final List<IBaseCinemaObject> resultList = new ArrayList<>();
        final JSONObject jsonResponse = new JSONObject(pJsonString);
        final JSONArray jsonObjectArray = new JSONArray(jsonResponse.get(pKey).toString());
        for (int i = 0; i < jsonObjectArray.length(); i++) {
            final JSONObject jsonObject = jsonObjectArray.getJSONObject(i);
            resultList.add(mCinemaObjectParser.parse(jsonObject.toString(), pObject));
        }
        return resultList;
    }
}
