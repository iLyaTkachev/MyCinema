package ilyatkachev.github.com.mycinema.data.remote.gson;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import ilyatkachev.github.com.mycinema.util.IOUtils;

public class ResponseParser implements ICinemaParser<Object>{

    /*private CinemaObjectParser mCinemaObjectParser;

    public ResponseParser() {
        mCinemaObjectParser = new CinemaObjectParser();
    }

    public List parse(final String pJsonString, final BaseMediaResponse pObject, final String pKey) throws Exception {
        final List<IBaseCinemaObject> resultList = new ArrayList<>();
        final JSONObject jsonResponse = new JSONObject(pJsonString);
        final JSONArray jsonObjectArray = new JSONArray(jsonResponse.get(pKey).toString());
        for (int i = 0; i < jsonObjectArray.length(); i++) {
            final JSONObject jsonObject = jsonObjectArray.getJSONObject(i);
            resultList.add(mCinemaObjectParser.parse(jsonObject.toString(), pObject));
        }
        return resultList;
    }*/

    public ResponseParser() {
    }

    @Override
    public Object parse(final InputStream pStream, final Object pObject) {
        try {
            final Reader reader = new InputStreamReader(pStream, "UTF-8");
            final Object mediaResponse  = new Gson().fromJson(reader, pObject.getClass());
            return mediaResponse;
        } catch (final UnsupportedEncodingException pE) {
            pE.printStackTrace();
        }
        finally {
            IOUtils.closeStream(pStream);
        }
        return null;
    }
}
