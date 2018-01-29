package ilyatkachev.github.com.mycinema.movies;

import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.util.Constants;

public enum MediaType {
    MOVIE, TV;

    public String toApiString() {
        switch (this) {
            case MOVIE:
                return Constants.ApiValues.MOVIE;
            case TV:
                return Constants.ApiValues.TV;
            default:
                return super.toString();
        }
    }

    public String toString() {
        switch (this) {
            case MOVIE:
                return Constants.UIValues.MOVIES_ACTIVITY_TITLE;
            case TV:
                return Constants.UIValues.TV_ACTIVITY_TITLE;
            default:
                return super.toString();
        }
    }


    public MediaFilterType[] getFilterTypes() {
        switch (this) {
            case MOVIE:
                return new MediaFilterType[]{MediaFilterType.POPULAR, MediaFilterType.TOP_RATED, MediaFilterType.UPCOMING, MediaFilterType.NOW_PLAYING};
            case TV:
                return new MediaFilterType[]{MediaFilterType.POPULAR, MediaFilterType.TOP_RATED, MediaFilterType.AIRING_TODAY, MediaFilterType.ON_THE_AIR};
            default:
                return MediaFilterType.values();
        }
    }
}
