package ilyatkachev.github.com.mycinema.movies;

import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.util.Constants;

public enum MediaFilterType {
    POPULAR, TOP_RATED, UPCOMING, NOW_PLAYING, AIRING_TODAY, ON_THE_AIR;

    public String toApiString() {
        switch (this) {
            case POPULAR:
                return Constants.ApiValues.POPULAR;
            case TOP_RATED:
                return Constants.ApiValues.TOP_RATED;
            case UPCOMING:
                return Constants.ApiValues.UPCOMING;
            case NOW_PLAYING:
                return Constants.ApiValues.NOW_PLAYING;
            case AIRING_TODAY:
                return Constants.ApiValues.AIRING_TODAY;
            case ON_THE_AIR:
                return Constants.ApiValues.ON_THE_AIR;
            default:
                return super.toString();
        }
    }

    public String toString() {
        switch (this) {
            case POPULAR:
                return Constants.UIValues.POPULAR_TAB;
            case TOP_RATED:
                return Constants.UIValues.TOP_RATED_TAB;
            case UPCOMING:
                return Constants.UIValues.UPCOMING_TAB;
            case NOW_PLAYING:
                return Constants.UIValues.IN_THEATERS_TAB;
            case AIRING_TODAY:
                return Constants.UIValues.AIRING_TODAY;
            case ON_THE_AIR:
                return Constants.UIValues.ON_THE_AIR;
            default:
                return super.toString();
        }
    }

}
